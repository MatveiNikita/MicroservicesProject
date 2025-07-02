package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder locatorBuilder){
        return locatorBuilder.routes()
                .route("user-service", r -> r.path("/user-api/**")
                        .uri("http://localhost:8080"))
                .route("account-service", r -> r.path("/account-api/**")
                        .uri("http://localhost:8081"))
                .build();
    }
}
