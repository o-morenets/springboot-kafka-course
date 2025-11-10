package net.javaguides.springboot.stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.publisher.KafkaPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;

@Slf4j
@Service
@RequiredArgsConstructor
public class WikimediaStreamConsumer {

    private final WebClient webClient;
    private final KafkaPublisher producer;
    
    private Disposable subscription;

    /**
     * Consume messages from WebClient and publish them via WikimediaProducer
     */
    public void consumeStreamAndPublish() {
        if (subscription != null && !subscription.isDisposed()) {
            log.warn("Stream is already running");
            return;
        }
        
        subscription = webClient.get()
                .uri("/stream/recentchange")
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(
                        producer::sendMessage,
                        error -> log.error("Error consuming stream: {}", error.getMessage(), error),
                        () -> log.info("Stream completed")
                );
        
        log.info("Started consuming Wikimedia stream");
    }
    
    public void stopConsuming() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
            log.info("Stopped consuming Wikimedia stream");
        }
    }
}

