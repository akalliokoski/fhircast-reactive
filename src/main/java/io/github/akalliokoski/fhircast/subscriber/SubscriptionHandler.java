package io.github.akalliokoski.fhircast.subscriber;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SubscriptionHandler {
    public Mono<ServerResponse> subscribe(ServerRequest request) {
        return ServerResponse
                .accepted()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("subscribed"));
    }

    public Mono<ServerResponse> unsubscribe(ServerRequest request) {
        return ServerResponse
                .accepted()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("unsubscribed"));
    }

    public Mono<ServerResponse> verifySubscription(ServerRequest request) {
        String challenge = "hub.challenge"; // TODO: use parameter
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject(challenge));
    }

    public Mono<ServerResponse> notifyEvent(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject(""));
    }
}
