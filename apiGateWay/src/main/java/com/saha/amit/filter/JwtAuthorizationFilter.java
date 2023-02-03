package com.saha.amit.filter;

import com.saha.amit.constants.ApiGatewayCosntants;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

public class JwtAuthorizationFilter implements WebFilter {

    private final Logger LOG = Logger.getLogger(JWTFilter.class.getName());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        List<String> apiKeyHeader = exchange.getRequest().getHeaders().get(ApiGatewayCosntants.JWT_HEADER);
        LOG.info("apiKeyHeader ---> "+ apiKeyHeader);
        return chain.filter(exchange);
    }
}
