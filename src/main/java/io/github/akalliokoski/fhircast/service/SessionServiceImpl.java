package io.github.akalliokoski.fhircast.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    private final UUID testTopic = UUID.randomUUID();

    @Override
    public UUID getSessionId(String user, String secret) {
        return testTopic;
    }
}
