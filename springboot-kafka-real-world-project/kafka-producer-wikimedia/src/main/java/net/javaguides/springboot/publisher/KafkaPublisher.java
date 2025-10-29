package net.javaguides.springboot.publisher;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaPublisher {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(String message) {
        try {
            // Extract the key from the message (server_name for partitioning by wiki)
            JsonNode jsonNode = objectMapper.readTree(message);
            String key = jsonNode.has("server_name") 
                ? jsonNode.get("server_name").asText() 
                : null;
            
            log.info("Sending message with key: '{}' to topic: '{}'", key, topicName);
            kafkaTemplate.send(topicName, key, message);
            
        } catch (Exception e) {
            log.error("Error parsing message, sending without key: {}", e.getMessage());
            kafkaTemplate.send(topicName, message);
        }
    }
}
