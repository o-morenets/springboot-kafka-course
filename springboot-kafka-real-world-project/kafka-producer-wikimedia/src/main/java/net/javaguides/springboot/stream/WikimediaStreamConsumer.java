package net.javaguides.springboot.stream;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.producer.WikimediaProducer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WikimediaStreamConsumer {

    private final WebClient webClient;
    private final WikimediaProducer producer;

    /**
     * Consume messages from WebClient and publish them via WikimediaProducer
     */
    public void consumeStreamAndPublish() {
        webClient.get()
                .uri("/stream/recentchange")
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(producer::sendMessage);
    }
}

