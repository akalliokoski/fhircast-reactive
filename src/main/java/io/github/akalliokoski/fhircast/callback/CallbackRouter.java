package io.github.akalliokoski.fhircast.callback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CallbackRouter {
    @Bean
    public RouterFunction<ServerResponse> callbackRoutes(CallbackHandler callbackHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/callback/{id}")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        callbackHandler::verifySubscription)
                .andRoute(RequestPredicates.POST("/callback/{id}")
                                .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
                        callbackHandler::notifyEvent);
    }
}
