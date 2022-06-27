package com.jeantravassos.subscriptionsservice.service;

import com.jeantravassos.subscriptionsservice.dto.SubscriptionRequestDto;
import com.jeantravassos.subscriptionsservice.model.Subscription;
import com.jeantravassos.subscriptionsservice.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class SubscriptionService {

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

}
