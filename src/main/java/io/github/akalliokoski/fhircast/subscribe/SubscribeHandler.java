package io.github.akalliokoski.fhircast.subscribe;

import io.github.akalliokoski.fhircast.service.FhirCastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SubscribeHandler {

    private final FhirCastService fhirCastService;

    @Autowired
    public SubscribeHandler(FhirCastService fhirCastService) {
        this.fhirCastService = fhirCastService;
    }

    public Mono<ServerResponse> subscribe(ServerRequest request) {
        fhirCastService.subscribe()
                .log()
                .subscribe(System.out::println);
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
}
