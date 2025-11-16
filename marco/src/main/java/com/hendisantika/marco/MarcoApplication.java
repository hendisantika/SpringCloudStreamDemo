package com.hendisantika.marco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Supplier;

@SpringBootApplication
public class MarcoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcoApplication.class, args);
	}

    @Bean
    public Supplier<Flux<String>> marcoSupplier() {
        return () -> Flux.interval(Duration.ofSeconds(1))
                .map(tick -> "MARCO: " + System.currentTimeMillis());
    }
}
