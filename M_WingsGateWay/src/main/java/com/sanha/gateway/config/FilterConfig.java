package com.sanha.gateway.config;

import com.sanha.gateway.filter.CustomFilter;
import com.sanha.gateway.filter.LoggingFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

//java에서 Spring Cloud Gateway 설정

//@Configuration
public class FilterConfig {
//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, CustomFilter customFilter, LoggingFilter loggingFilter){
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                            .filters(f -> f.addRequestHeader("first-request","first-request-header")
                            .addResponseHeader("first-response","first-response-header")
                                            .filter(customFilter.apply(new CustomFilter.Config()))
                                            .filter(loggingFilter.apply(new LoggingFilter.Config("message",true,true)))
                            )
//                            .uri("http://localhost:8081/")
                                .uri("lb://MY-FIRST-SERVICE")
                )
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request","second-request-header")
                                .addResponseHeader("second-response","second-response-header")
                                .filter(customFilter.apply(new CustomFilter.Config()))
                                .filter(loggingFilter.apply(new LoggingFilter.Config("message",true,true)))
                        )
//                        .uri("http://localhost:8082/")
                                .uri("lb://MY-SECOND-SERVICE")
                )
                .build();

    }
}
