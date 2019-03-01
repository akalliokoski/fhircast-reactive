package io.github.akalliokoski.fhircast.hub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class ConnectRouter {
    @Bean
    public RouterFunction<ServerResponse> connectRoutes(ConnectHandler connectHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/connect"), connectHandler::connect);
    }
}
