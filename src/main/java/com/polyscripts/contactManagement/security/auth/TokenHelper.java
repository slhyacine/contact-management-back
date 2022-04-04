package com.polyscripts.contactManagement.security.auth;


import com.polyscripts.contactManagement.security.config.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenHelper {

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    private final SecurityProperties securityProperties;

    public Optional<String> getUsernameFromToken(String token) {
        String username = null;

            final Optional<Claims> claims = getAllClaimsFromToken(token);
            if (claims.isPresent()) {
                username = claims.get().getSubject();
            }

        return Optional.ofNullable(username);
    }

    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .claim("roles", roles)
                .signWith(signatureAlgorithm, securityProperties.getJwtSecret())
                .compact();
    }

    private Optional<Claims> getAllClaimsFromToken(String token) {

        Claims claims = Jwts.parser()
                    .setSigningKey(securityProperties.getJwtSecret())
                    .parseClaimsJws(token)
                    .getBody();

        return Optional.ofNullable(claims);
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + securityProperties.getExpiresInSeconds() * 1000);
    }

    public Boolean isValidToken(String token, UserDetails userDetails) {
        final Optional<Claims> claims = getAllClaimsFromToken(token);

        if (!claims.isPresent()) return false;

        String username = claims.get().getSubject();

        return userDetails.getUsername().equals(username)
                && userDetails.isEnabled()
                && !isTokenExpired(claims.get());
    }

    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public Optional<String> getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return Optional.of(authHeader.substring(7));
        }

        return Optional.empty();
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(securityProperties.getAuthHeader());
    }

    public long getExpiredIn() {
        return securityProperties.getExpiresInSeconds();
    }
}
