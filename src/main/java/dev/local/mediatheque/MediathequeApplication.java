package dev.local.mediatheque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MediathequeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediathequeApplication.class, args);
    }
}
