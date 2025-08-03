package com.valkyrie.api_gateway.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class Api {
    private RouterFunction<ServerResponse> get(String routeName, String pattern, String routeTo) {
        return route(routeName).GET(pattern, http()).before(uri(routeTo)).build();
    }

    private RouterFunction<ServerResponse> post(String routeName, String pattern, String routeTo) {
        return route(routeName).POST(pattern, http()).before(uri(routeTo)).build();
    }

    private RouterFunction<ServerResponse> delete(String routeName, String pattern, String routeTo) {
        return route(routeName).DELETE(pattern, http()).before(uri(routeTo)).build();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return get("get-teacher", "/teacher/**", "http://localhost:8081/")
                .and(
                        post("post-teacher", "/teacher/**", "http://localhost:8081/")
                ).and(
                        delete("delete-teacher", "/teacher/**", "http://localhost:8081")
                ).and(
                        get("get-student", "/student/**", "http://localhost:8082/")
                ).and(
                        post("post-student", "/student/**", "http://localhost:8082/")
                ).and(
                        delete("delete-student", "/student/**", "http://localhost:8082/")
                ).and(
                        get("get-marks", "/marks/**", "http://localhost:8083/")
                ).and(
                        post("post-marks", "/marks/**", "http://localhost:8083/")
                ).and(
                        delete("delete-marks", "/marks/**", "http://localhost:8083/")
                ).and(
                        post("user", "/user/**", "http://localhost:8084/")
                );
    }
}
