package io.github.akalliokoski.fhircast.hub;

import io.github.akalliokoski.fhircast.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Component
public class ConnectHandler {

    private final SessionService sessionService;

    @Autowired
    public ConnectHandler(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public Mono<ServerResponse> connect(ServerRequest request) {
        Optional<String> user = request.queryParam("user");
        Optional<String> secret = request.queryParam("secret");

        UUID topic = sessionService.getSessionId(user.get(), secret.get());

        return ServerResponse
            .ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(BodyInserters.fromObject(topic.toString()));
    }
}
