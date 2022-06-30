package com.jeantravassos.subscriptionsservice.service;

import com.jeantravassos.subscriptionsservice.dto.SubscriptionRequestDto;
import com.jeantravassos.subscriptionsservice.model.Subscription;
import com.jeantravassos.subscriptionsservice.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class SubscriptionService {

    private String createHeaders(){
        String username = "adidas";
        String password = "challenge";

        byte[] encodedBytes = Base64Utils.encode((username + ":" + password).getBytes());

        String authHeader = "Basic " + new String(encodedBytes);
        return authHeader;
    }

    @Qualifier("com.jeantravassos.subscriptionsservice.service.EmailClient")
    @Autowired
    private EmailClient emailClient;

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> getAllSubscriptions() {
        log.info("subscriptions-service - SubscriptionService - getAllSubscriptions()");

        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> findById(String id) {
        log.info("subscriptions-service - SubscriptionService - findById()");

        return subscriptionRepository.findById(id);
    }

    public String createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        log.info("subscriptions-service - SubscriptionService - updateSubscription()");

        Subscription subscription = Subscription.builder()
                .firstName(subscriptionRequestDto.getFirstName())
                .consent(subscriptionRequestDto.getConsent())
                .gender(subscriptionRequestDto.getGender())
                .email(subscriptionRequestDto.getEmail())
                .dateOfBirth(subscriptionRequestDto.getDateOfBirth())
                .newsletterId(subscriptionRequestDto.getNewsletterId())
                .build();

        subscription = subscriptionRepository.save(subscription);

        return subscription.getId();
    }


    public void cancelSubscription(String id) {
        log.info("subscriptions-service - SubscriptionService - cancelSubscription()");

        subscriptionRepository.deleteById(id);
    }

    public void sendEmail(String email) {
        log.info("subscriptions-service - SubscriptionService - sendEmail()");
        emailClient.sendEmail(createHeaders(), email);
    }
}
