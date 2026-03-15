package com.mzo.gatewayservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GatewayRoutesController {
    @Autowired
    private RouteLocator routeLocator;

    @GetMapping("/services")
    public Flux<Map<String, Object>> getRoutes() {

        return routeLocator.getRoutes().map(route -> {
            Map<String, Object> map = new HashMap<>();

            map.put("service", route.getUri().toString());
            map.put("path", route.getPredicate().toString());
            map.put("port", route.getMetadata().get("management.port"));
            return map;
        });
    }

    @GetMapping("/services-clean")
    public Flux<Map<String, Object>> getRoutesClean() {

        return routeLocator.getRoutes().map(route -> {

            Map<String, Object> map = new HashMap<>();

            String service = route.getUri().getHost();
            String predicate = route.getPredicate().toString();

            String path = predicate.substring(
                    predicate.indexOf("[") + 1,
                    predicate.indexOf("]")
            );

            map.put("service", service);
            map.put("path", path);
            map.put("port", route.getMetadata().get("management.port"));

            return map;
        });
    }
}
