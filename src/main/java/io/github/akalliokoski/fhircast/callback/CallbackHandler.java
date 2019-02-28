package io.github.akalliokoski.fhircast.callback;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CallbackHandler {
    public Mono<ServerResponse> verifySubscription(ServerRequest request) {
        System.out.println("CALLBACK: verify sub");
        String challenge = "hub.challenge"; // TODO: use parameter
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject(challenge));
    }

    public Mono<ServerResponse> notifyEvent(ServerRequest request) {
        System.out.println("CALLBACK: event");
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject(""));
    }
}
