package io.github.akalliokoski.fhircast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FhirCastServiceImpl implements FhirCastService {

    private final WebClient webClient;
    private final String hubBaseUrl;

    @Autowired
    public FhirCastServiceImpl(WebClient.Builder webClientBuilder, Environment env) {
        hubBaseUrl = env.getProperty("hubBaseUrl");
        this.webClient = webClientBuilder.baseUrl(hubBaseUrl).build();
    }

    @Override
    public Mono<String> subscribe() {
        String topicId = "FC1551351137204";

        String subscribeUrl = hubBaseUrl + "/fhircast/" + topicId + "/websub";
        System.out.println(subscribeUrl);

        WebClient.RequestBodySpec uri = webClient
                .post()
                .uri(subscribeUrl);

        String topicUrl = hubBaseUrl + "/" + topicId;
        String topic = "demo";
        String callbackUrl = "http://localhost:8080/callback/ " + topic;

        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("hub.callback", callbackUrl);
        map.add("hub.mode", "subscribe");
        map.add("hub.topic",topicUrl);
        map.add("hub.secret", "secret");
        map.add("hub.events", "open-patient-chart");
        BodyInserters.MultipartInserter bodyInserter = BodyInserters.fromMultipartData(map);

        // TODO: Should use content type APPLICATION_FORM_URLENCODED_VALUE
        return uri.body(bodyInserter)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }
}
