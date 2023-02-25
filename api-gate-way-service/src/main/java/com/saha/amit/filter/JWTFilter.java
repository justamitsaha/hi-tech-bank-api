package com.saha.amit.filter;

import com.saha.amit.constants.ApiGateWayConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;


public class JWTFilter implements GlobalFilter {

    private final Logger LOG = Logger.getLogger(JWTFilter.class.getName());
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> apiKeyHeader = exchange.getRequest().getHeaders().get(ApiGateWayConstants.JWT_HEADER);
        LOG.info("apiKeyHeader ---> "+ apiKeyHeader);
        return chain.filter(exchange);
    }

}
