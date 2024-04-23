package com.simform;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqsIntegrationApplication {

	private final Logger logger = LoggerFactory.getLogger(SqsIntegrationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SqsIntegrationApplication.class, args);
	}

	@SqsListener("${aws.sqs.name}")
	public void loadMessageFromSqsQueue(String message) {
		logger.info("Messages is " + message);
	}
}
