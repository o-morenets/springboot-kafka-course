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

        WikimediaData wikimediaData = WikimediaData.builder()
                .schema(event.schema())
                .meta(event.meta())
                .type(event.type())
                .namespace(event.namespace())
                .title(event.title())
                .title_url(event.title_url())
                .comment(event.comment())
                .timestamp(event.timestamp())
                .user(event.user())
                .bot(event.bot())
                .notify_url(event.notify_url())
                .minor(event.minor())
                .patrolled(event.patrolled())
                .length(event.length())
                .revision(event.revision())
                .server_url(event.server_url())
                .server_name(event.server_name())
                .server_script_path(event.server_script_path())
                .wiki(event.wiki())
                .parsedcomment(event.parsedcomment())
                .build();

        dataRepository.save(wikimediaData);
    }
}
