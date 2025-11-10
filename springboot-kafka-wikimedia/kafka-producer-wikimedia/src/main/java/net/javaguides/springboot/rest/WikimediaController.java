package net.javaguides.springboot.rest;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.stream.WikimediaStreamConsumer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wikimedia")
public class WikimediaController {

    private final WikimediaStreamConsumer streamConsumer;

    @PostMapping("/start")
    public ResponseEntity<String> startPublishing() {
        streamConsumer.consumeStreamAndPublish();
        return ResponseEntity.ok("Wikimedia stream consumption started");
    }
    
    @PostMapping("/stop")
    public ResponseEntity<String> stopPublishing() {
        streamConsumer.stopConsuming();
        return ResponseEntity.ok("Wikimedia stream consumption stopped");
    }
}
