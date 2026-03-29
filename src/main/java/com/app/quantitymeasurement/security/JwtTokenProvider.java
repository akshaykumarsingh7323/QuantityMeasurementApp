package com.app.quantitymeasurement.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Utility component for creating and validating JSON Web Tokens (JWTs).
 *
 * <p>Tokens are signed using HMAC-SHA512 with a secret key defined in
 * {@code application.properties}. The token subject is the authenticated user's
 * database ID, which is used to look up the user on subsequent requests.</p>
 */
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.auth.tokenSecret}")
    private String tokenSecret;

    @Value("${app.auth.tokenExpirationMsec}")
    private long tokenExpirationMsec;

    /**
     * Generates a signed JWT for an authenticated principal.
     *
     * @param authentication the Spring Security Authentication object
     * @return a compact, URL-safe JWT string
     */
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date expiryDate = new Date(System.currentTimeMillis() + tokenExpirationMsec);

        return Jwts.builder()
                .subject(Long.toString(userPrincipal.getId()))
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extracts the user ID from a valid JWT.
     *
     * @param token the JWT string
     * @return the user's database ID as a Long
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * Validates a JWT token — checks signature and expiry.
     *
     * @param authToken the JWT string to validate
     * @return true if valid, false otherwise
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty");
        }
        return false;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(tokenSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
