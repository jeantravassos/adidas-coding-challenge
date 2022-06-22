package com.jeantravassos.publicservice.service;

import com.jeantravassos.publicservice.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {
    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionService.class);

    public List<Subscription> getAllSubscriptions() {
        LOG.info("public-service - SubscriptionService - getAllSubscriptions()");
        LOG.info("method called");

        //TODO - Call subscriptions-service

        Subscription s = Subscription.builder()
                .id("12AB")
                .consent(true)
                .firstName("Jean")
                .email("abc@def.ghi")
                .newsletterId(123L)
                .dateOfBirth(LocalDate.of(1985, Month.JANUARY, 25))
                .gender(1)
                .build();

        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(s);

//        return subscriptionList;
        return null;
    }
}
