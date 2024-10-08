package viet.io.chirpchirp.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import viet.io.chirpchirp.exception.TokenExpiredException;
import viet.io.chirpchirp.model.User;
import viet.io.chirpchirp.security.dto.LoginResponse;
import viet.io.chirpchirp.security.dto.RefreshRequest;
import viet.io.chirpchirp.security.service.UserService;
import viet.io.chirpchirp.security.dto.LoginRequest;
import viet.io.chirpchirp.service.TokenStore;

/**
 * Service class for handling JWT token operations.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;
    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;
    private final JwtProperties jwtProperties;

    /**
     * Authenticates the user and generates JWT tokens.
     *
     * @param loginRequest the login request containing username and password
     * @return a LoginResponse containing the username, access token, and refresh token
     */
    public LoginResponse login(LoginRequest loginRequest) {
        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Retrieve the user details
        User user = userService.findByUsername(loginRequest.getUsername());

        // Generate JWT tokens
        String accessToken = jwtTokenManager.generateAccessToken(user);
        String refreshToken = jwtTokenManager.generateRefreshToken(user);

        tokenStore.storeToken("access:" + user.getUsername(), accessToken, jwtProperties.getAccessTokenExpirationMinute());
        tokenStore.storeToken("refresh:" + user.getUsername(), refreshToken, jwtProperties.getRefreshTokenExpirationMinute());

        // Log the successful login
        log.info("{} has successfully logged in!", user.getUsername());

        // Return the login response
        return new LoginResponse(user.getUsername(), accessToken, refreshToken);
    }

    /**
     * Generates a new access token using the refresh token.
     *
     * @param refreshRequest the refresh token
     * @return a LoginResponse containing the username and the new access token
     */
    public LoginResponse refresh(RefreshRequest refreshRequest) {
        final String refreshToken = refreshRequest.getRefreshToken();
        if (jwtTokenManager.isTokenExpired(refreshToken)) {
            throw new TokenExpiredException("Refresh token is expired");
        }
        // Retrieve the username from the refresh token
        String username = jwtTokenManager.getUsernameFromToken(refreshToken);

        // Retrieve the user details
        User user = userService.findByUsername(username);

        // Generate a new access token
        String accessToken = jwtTokenManager.generateAccessToken(user);

        tokenStore.storeToken("access:" + user.getUsername(), accessToken, jwtProperties.getAccessTokenExpirationMinute());

        // Log the successful token refresh
        log.info("{} has successfully refreshed the access token!", user.getUsername());

        // Return the login response
        return new LoginResponse(user.getUsername(), accessToken, refreshToken);
    }

    /**
     * Logs out the user by deleting the refresh token.
     *
     * @param accessToken the access token
     * @return a message indicating that the user has been logged out
     */
    public String logout(String accessToken) {
        // Retrieve the username from the refresh token
        String username = jwtTokenManager.getUsernameFromToken(accessToken);

        // Delete the access and refresh tokens
        tokenStore.deleteToken("access:" + username);
        tokenStore.deleteToken("refresh:" + username);

        // Log the successful logout
        log.info("{} has successfully logged out!", username);

        // Return the logout message
        return "User has been logged out";
    }
}