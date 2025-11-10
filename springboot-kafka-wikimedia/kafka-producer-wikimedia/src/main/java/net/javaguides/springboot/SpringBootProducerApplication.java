package net.javaguides.springboot;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.event.WikimediaChangesProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProducerApplication.class);
    }

    private final WikimediaChangesProducer wikimediaChangesProducer;

    @Override
    public void run(String... args) throws Exception {

        // Uncomment the following line if you want a fire-and-forget 10-minutes job that runs on startup:
//        wikimediaChangesProducer.startPublishing();
    }
}
