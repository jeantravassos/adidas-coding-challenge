package com.jeantravassos.publicservice.service;

import com.jeantravassos.publicservice.dto.SubscriptionRequestDto;
import com.jeantravassos.publicservice.model.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SubscriptionService {

    public List<Subscription> getAllSubscriptions() {
        log.info("public-service - SubscriptionService - getAllSubscriptions()");

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

    public Subscription findById(String id) {
        log.info("public-service - SubscriptionService - findById()");

        //TODO - Call subscriptions-service

        //TODO - Treat exceptions

        return null;
    }

    public String createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        log.info("public-service - SubscriptionService - updateSubscription()");

//        Subscription subscription = findById(id);
//        subscription.setConsent(subscriptionRequestDto.getConsent());
//        subscription.setEmail(subscriptionRequestDto.getEmail());
//        subscription.setDateOfBirth(subscriptionRequestDto.getDateOfBirth());
//        subscription.setGender(subscriptionRequestDto.getGender());
//        subscription.setFirstName(subscriptionRequestDto.getFirstName());
//        subscription.setNewsletterId(subscriptionRequestDto.getNewsletterId());

        //TODO - Call subscriptions-service
        //save(subscriptionRequestDto);

        return null;
    }


    public void cancelSubscription(String id) {
        log.info("public-service - SubscriptionService - cancelSubscription()");
        //TODO - Call subscriptions-service

    }

}
