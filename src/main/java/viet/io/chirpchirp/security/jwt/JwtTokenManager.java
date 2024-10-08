package viet.io.chirpchirp.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import viet.io.chirpchirp.model.User;

import java.util.Date;

/**
 * Component responsible for managing JWT (JSON Web Token) operations such as
 * generating access and refresh tokens, and validating tokens.
 */
@Component
@RequiredArgsConstructor
public class JwtTokenManager {
    private final JwtProperties jwtProperties;

    /**
     * Generates an access token for the given user.
     *
     * @param user the user for whom the token is generated
     * @return the generated access token
     */
    public String generateAccessToken(User user) {
        return generateToken(user, jwtProperties.getAccessTokenExpirationMinute());
    }

    /**
     * Generates a refresh token for the given user.
     *
     * @param user the user for whom the token is generated
     * @return the generated refresh token
     */
    public String generateRefreshToken(User user) {
        return generateToken(user, jwtProperties.getRefreshTokenExpirationMinute());
    }

    /**
     * Generates a token for the given user with the specified expiration time.
     *
     * @param user the user for whom the token is generated
     * @param expirationMinutes the expiration time in minutes
     * @return the generated token
     */
    private String generateToken(User user, long expirationMinutes) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("id", user.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMinutes * 60 * 1000))
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes()));
    }

    /**
     * Extracts the username from the given token.
     *
     * @param token the JWT token
     * @return the username extracted from the token
     */
    public String getUsernameFromToken(String token) {
        return getDecodedJWT(token).getSubject();
    }

    /**
     * Extracts the user ID from the given token.
     *
     * @param token the JWT token
     * @return the user ID extracted from the token
     */
    public String getUserIdFromToken(String token) {
        return getDecodedJWT(token).getClaim("id").asString();
    }

    /**
     * Validates the given token against the authenticated username.
     *
     * @param token the JWT token
     * @param authenticatedUsername the authenticated username to validate against
     * @return true if the token is valid and not expired, false otherwise
     */
    public boolean validateToken(String token, String authenticatedUsername) {
        return getUsernameFromToken(token).equals(authenticatedUsername) && !isTokenExpired(token);
    }

    /**
     * Checks if the given token is expired.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        return getDecodedJWT(token).getExpiresAt().before(new Date());
    }

    /**
     * Decodes the given JWT token.
     *
     * @param token the JWT token
     * @return the decoded JWT
     */
    private DecodedJWT getDecodedJWT(String token) {
        final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes())).build();
        return jwtVerifier.verify(token);
    }
}