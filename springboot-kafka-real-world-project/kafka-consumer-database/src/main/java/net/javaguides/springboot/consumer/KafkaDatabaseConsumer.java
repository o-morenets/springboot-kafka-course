package net.javaguides.springboot.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.dto.WikimediaEvent;
import net.javaguides.springboot.entity.WikimediaData;
import net.javaguides.springboot.repository.WikimediaDataRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaDatabaseConsumer {

    private final WikimediaDataRepository dataRepository;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(WikimediaEvent event) {
        log.info("Event received: '{}'", event);

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(event.title_url());

        dataRepository.save(wikimediaData);
    }
}
