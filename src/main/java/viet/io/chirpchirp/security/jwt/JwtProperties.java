package viet.io.chirpchirp.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for JWT (JSON Web Token).
 * This class holds the properties related to JWT configuration,
 * such as issuer, secret key, and expiration times for access and refresh tokens.
 */
@Getter
@Setter
@Configuration
public class JwtProperties {

    /**
     * The issuer of the JWT.
     */
    @Value("${spring.jwt.issuer}")
    private String issuer;

    /**
     * The secret key used for signing the JWT.
     */
    @Value("${spring.jwt.secret-key}")
    private String secretKey;

    /**
     * The expiration time (in minutes) for the access token.
     */
    @Value("${spring.jwt.access-token-expiration-minute}")
    private long accessTokenExpirationMinute;

    /**
     * The expiration time (in minutes) for the refresh token.
     */
    @Value("${spring.jwt.refresh-token-expiration-minute}")
    private long refreshTokenExpirationMinute;

}