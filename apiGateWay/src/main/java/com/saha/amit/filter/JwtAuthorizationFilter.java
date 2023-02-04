package com.saha.amit.filter;

import com.saha.amit.constants.ApiGatewayCosntants;
import com.thoughtworks.xstream.security.ForbiddenClassException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

public class JwtAuthorizationFilter implements WebFilter {

    private final Logger LOG = Logger.getLogger(JWTFilter.class.getName());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        String path = String.valueOf(exchange.getRequest().getPath());
        List<String> authHeader = httpHeaders.get(ApiGatewayCosntants.JWT_HEADER);
        if (path.contains("private")) {
            String jwt = authHeader.get(0);
            LOG.info("apiKeyHeader ---> " + jwt + "Path --> " + path);
            if (!jwt.isEmpty()) {
                try {
                    SecretKey key = Keys.hmacShaKeyFor(
                            ApiGatewayCosntants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
                    Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();
                    String username = String.valueOf(claims.get("username"));
                    String authorities = (String) claims.get("authorities");
                    Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (Exception e) {
                    LOG.info(e.toString());
                    throw new BadCredentialsException("Invalid Token received!");
                }

            } else {
                throw new BadCredentialsException("Invalid Token received!");
            }
        }
        return chain.filter(exchange);
    }
}
