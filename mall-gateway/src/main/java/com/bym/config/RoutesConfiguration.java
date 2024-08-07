package com.bym.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route.path("/gateway/test-user/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://mall-user"))
                .route(route -> route.path("/gateway/test-order/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://mall-order"))
                .build();
    }
}
