package net.javaguides.springboot.rest;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.stream.WikimediaStreamConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wikimedia")
public class WikimediaController {

    private final WikimediaStreamConsumer streamConsumer;

    @GetMapping("/start")
    public void startPublishing() {
        streamConsumer.consumeStreamAndPublish();
    }
}
