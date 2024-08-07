package com.bym.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class GatewayService {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired
    private ApplicationEventPublisher publisher;

    public void updateRoutes(List<RouteDefinition> routeDefinitions) {
        if (CollectionUtils.isEmpty(routeDefinitions)) {
            log.info("No routes found");
            return;
        }

        routeDefinitions.forEach(r -> {
            try {
                routeDefinitionWriter.save(Mono.just(r)).subscribe();
            } catch (Exception e) {
                log.error("cannot update route, id={}", r.getId());
            }
        });
        publisher.publishEvent(new RefreshRoutesEvent(this));  // 放到循环外面，以免报错
    }
}
