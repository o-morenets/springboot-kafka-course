package net.javaguides.springboot.event;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.dto.WikimediaEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class WikimediaChangesProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, WikimediaEvent> kafkaTemplate;

    public void startPublishing() throws InterruptedException {

        // to read real-time stream data from wikimedia, we use an event source:
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topicName);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        
        try (EventSource eventSource = builder.build()) {
            eventSource.start();
            TimeUnit.MINUTES.sleep(10);
        }
    }
}
