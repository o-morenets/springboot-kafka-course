package net.javaguides.springboot.event;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor
public class WikimediaChangesHandler implements EventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        log.info(String.format("event data -> %s", messageEvent.getData()));
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onOpen() throws Exception {
    }

    @Override
    public void onClosed() throws Exception {
    }

    @Override
    public void onComment(String s) throws Exception {
    }

    @Override
    public void onError(Throwable throwable) {
    }
}
