package com.jeantravassos.publicservice.service;

import com.jeantravassos.publicservice.dto.SubscriptionRequestDto;
import com.jeantravassos.publicservice.model.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.List;

@Service
@Slf4j
public class SubscriptionService {

    private String createHeaders(){
        String username = "adidas";
        String password = "challenge";

        byte[] encodedBytes = Base64Utils.encode((username + ":" + password).getBytes());

        String authHeader = "Basic " + new String(encodedBytes);
        return authHeader;
    }

    @Autowired
    private SubscriptionClient subscriptionClient;

    public List<Subscription> getAllSubscriptions() {
        log.info("public-service - SubscriptionService - getAllSubscriptions()");

        List<Subscription> subscriptionList = subscriptionClient.getAllSubscriptions(createHeaders());

        return subscriptionList;
    }

    public Subscription findById(String id) {
        log.info("public-service - SubscriptionService - findById()");

        Subscription subscription = subscriptionClient.getSubscriptionsById(createHeaders(), id);

        return subscription;
    }

    public String createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        log.info("public-service - SubscriptionService - updateSubscription()");

        String subscriptionId = subscriptionClient.createSubscription(createHeaders(), subscriptionRequestDto);

        return subscriptionId;
    }


    public void cancelSubscription(String id) {
        log.info("public-service - SubscriptionService - cancelSubscription()");

        subscriptionClient.cancelSubscription(createHeaders(), id);
    }

}
