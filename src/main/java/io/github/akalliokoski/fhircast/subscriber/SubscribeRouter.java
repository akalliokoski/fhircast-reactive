package io.github.akalliokoski.fhircast.subscriber;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SubscribeRouter {
    @Bean
    public RouterFunction<ServerResponse> subscribeRoutes(SubscriptionHandler subscriptionHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/subscribe")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN))
                                .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
                        subscriptionHandler::subscribe)
                .andRoute(RequestPredicates.POST("/unsubscribe")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN))
                                .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
                        subscriptionHandler::unsubscribe);

    }
}
