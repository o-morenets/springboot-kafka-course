package net.javaguides.springboot.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class WikimediaChangesHandler implements EventHandler {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) {
        String data = messageEvent.getData();
        
        try {
            // Extract the key from the message (title_url for partitioning by page)
            JsonNode jsonNode = objectMapper.readTree(data);
            String key = jsonNode.has("title_url") 
                ? jsonNode.get("title_url").asText() 
                : null;
            
            log.info("Event received with key: '{}', sending to topic: '{}'", key, topic);
            kafkaTemplate.send(topic, key, data);
            
        } catch (Exception e) {
            log.error("Error parsing event data, sending without key: {}", e.getMessage());
            kafkaTemplate.send(topic, data);
        }
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
