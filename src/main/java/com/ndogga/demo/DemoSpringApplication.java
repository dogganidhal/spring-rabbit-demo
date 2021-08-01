package com.ndogga.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootApplication
public class DemoSpringApplication {

  private static final Logger LOG = LoggerFactory.getLogger(DemoSpringApplication.class);

  private static final AtomicInteger dummyCounter = new AtomicInteger(0);

  @Bean
  public Supplier<String> sendDemo() {
    return () -> dummyCounter.getAndIncrement() % 5 == 0 ?
            UUID.randomUUID().toString() :
            null;
  }

  @Bean
  public Consumer<String> receiveDemo() {
    return (String payload) -> LOG.info("Received new demo message {}", payload);
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoSpringApplication.class, args);
  }

}
