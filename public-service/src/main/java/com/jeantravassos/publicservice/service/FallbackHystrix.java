package com.jeantravassos.publicservice.service;

import com.jeantravassos.publicservice.dto.SubscriptionRequestDto;
import com.jeantravassos.publicservice.model.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class FallbackHystrix implements SubscriptionClient{

    @Override
    public List<Subscription> getAllSubscriptions(String header) {
        log.info("Fallback called getAllSubscriptions....");
        return null;
    }

    @Override
    public Subscription getSubscriptionsById(String header, String id) {
        log.info("Fallback called getSubscriptionsById....");
        return null;
    }

    @Override
    public String createSubscription(String header, SubscriptionRequestDto subscriptionRequestDto) {
        log.info("Fallback called createSubscription....");
        return null;
    }

    @Override
    public String cancelSubscription(String header, String id) {
        log.info("Fallback called cancelSubscription....");
        return null;
    }
}