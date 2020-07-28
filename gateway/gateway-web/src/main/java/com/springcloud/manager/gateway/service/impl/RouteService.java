package com.springcloud.manager.gateway.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.springcloud.manager.gateway.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RouteService implements IRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();

    private void loadRouteDefinition() {
        Set<String> gatewayKeys = stringRedisTemplate.keys(GATEWAY_ROUTES + "*");

        if (CollectionUtils.isEmpty(gatewayKeys)) {
            return;
        }

        List<String> gatewayRoutes = Optional.ofNullable(stringRedisTemplate.opsForValue().multiGet(gatewayKeys)).orElse(Lists.newArrayList());
        gatewayRoutes.forEach(value -> {
            try{
                RouteDefinition routeDefinition = JSON.parseObject(value, RouteDefinition.class);
                routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
            }catch (Exception e){
                e.printStackTrace();
            }

        });
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        loadRouteDefinition();
        return Flux.fromIterable(routeDefinitionMaps.values());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> routeDefinitionMono) {
        return routeDefinitionMono.flatMap(routeDefinition -> {
            routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeDefinitionMaps.remove(id);
            return Mono.empty();
        });
    }
}
