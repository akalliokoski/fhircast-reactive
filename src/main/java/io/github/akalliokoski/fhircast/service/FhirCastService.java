package io.github.akalliokoski.fhircast.service;

import reactor.core.publisher.Mono;

public interface FhirCastService {
    Mono<String> subscribe();
}
