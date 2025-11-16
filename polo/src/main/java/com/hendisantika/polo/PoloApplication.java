package com.hendisantika.polo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class PoloApplication {

    private static final Logger logger = LoggerFactory.getLogger(PoloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PoloApplication.class, args);
	}

    @Bean
    public Consumer<String> poloConsumer() {
        return payload -> {
            logger.info("POLO: " + payload);
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread interrupted", e);
            }
        };
    }
}
