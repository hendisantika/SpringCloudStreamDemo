package com.hendisantika.polo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;

@SpringBootApplication
@EnableBinding(Sink.class)
public class PoloApplication {

    private static Logger logger = LoggerFactory.getLogger(PoloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PoloApplication.class, args);
	}

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void loggerSink(Object payload) throws Exception {
        logger.info("POLO: " + payload);
        Thread.sleep(60000);
    }
}
