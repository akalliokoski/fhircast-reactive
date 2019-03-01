package io.github.akalliokoski.fhircast.service;

import java.util.UUID;

public interface SessionService {
    UUID getSessionId(String user, String secret);
}
