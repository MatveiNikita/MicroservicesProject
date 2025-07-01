package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    public RouteLocator customRouteLocator(RouteLocatorBuilder locatorBuilder){
        return locatorBuilder.routes()
                .route("Path", r -> r.path("/user-api/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
