package com.jeantravassos.subscriptionsservice.utils;

import com.jeantravassos.subscriptionsservice.model.Subscription;
import com.jeantravassos.subscriptionsservice.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SubscriptionRepository repository) {

        return args -> {
            log.info("Preloading 1 " + repository.save(Subscription.builder()
                            .id("123")
                            .email("adi@adidas.com")
                            .firstName("Adi")
                            .consent(true)
                            .dateOfBirth(LocalDate.of(1900, Month.NOVEMBER, 03))
                            .gender(1)
                            .newsletterId(1L)
                    .build()));
            log.info("Preloading 2 " + repository.save(Subscription.builder()
                            .id("456")
                            .email("adolf@adidas.com")
                            .firstName("Adolf")
                            .consent(false)
                            .dateOfBirth(LocalDate.of(1900, Month.NOVEMBER, 03))
                            .gender(1)
                            .newsletterId(2L)
                    .build()));
            log.info("Preloading 3 " + repository.save(Subscription.builder()
                            .id("789")
                            .email("dassler@adidas.com")
                            .firstName("Dassler")
                            .consent(true)
                            .dateOfBirth(LocalDate.of(1900, Month.NOVEMBER, 03))
                            .gender(1)
                            .newsletterId(3L)
                    .build()));
        };
    }
}