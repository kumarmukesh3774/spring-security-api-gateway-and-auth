//package org.mik.auth.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class JwtHeaderFilter implements GlobalFilter {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange,
//                             GatewayFilterChain chain) {
//
//        return exchange.getPrincipal()
//                .cast(JwtAuthenticationToken.class)
//                .flatMap(auth -> {
//
//                    Jwt jwt = auth.getToken();
//
//                    ServerHttpRequest request = exchange.getRequest()
//                            .mutate()
//                            .header("X-User-Id", jwt.getSubject())
//                            .header("X-Roles",
//                                    String.join(",", jwt.getClaimAsStringList("roles")))
//                            .build();
//
//                    return chain.filter(
//                            exchange.mutate().request(request).build()
//                    );
//                });
//    }
//}
//
