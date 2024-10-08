package viet.io.chirpchirp.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import viet.io.chirpchirp.security.service.impl.UserDetailsServiceImpl;

import java.io.IOException;
import java.util.Objects;

/**
 * Filter that handles JWT authentication.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Filters incoming requests and performs JWT authentication.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException if an error occurs during filtering
     * @throws IOException if an I/O error occurs during filtering
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");

        String id = null;
        String username = null;
        String jwt = null;

        // Check if the Authorization header is present and starts with "Bearer "
        if (Objects.nonNull(header) && header.startsWith("Bearer ")) {
            jwt = header.substring(7);
            try {
                id = jwtTokenManager.getUserIdFromToken(jwt);
                username = jwtTokenManager.getUsernameFromToken(jwt);
            } catch (Exception e) {
                log.error("An error occurred while parsing JWT token", e);
                filterChain.doFilter(request, response);
                return;
            }
        }

        // Validate the token and set the authentication in the security context
        if (Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenManager.validateToken(jwt, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}