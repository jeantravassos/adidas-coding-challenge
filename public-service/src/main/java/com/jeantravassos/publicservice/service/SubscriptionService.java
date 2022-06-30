package com.jeantravassos.publicservice.service;

import com.jeantravassos.publicservice.dto.SubscriptionRequestDto;
import com.jeantravassos.publicservice.model.Subscription;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("com.jeantravassos.publicservice.service.SubscriptionClient")
    @Autowired
    private SubscriptionClient subscriptionClient;

    public List<Subscription> getAllSubscriptions() throws HystrixRuntimeException {
        log.info("public-service - SubscriptionService - getAllSubscriptions()");

        return subscriptionClient.getAllSubscriptions(createHeaders());
    }

    public Subscription findById(String id) throws HystrixRuntimeException {
        log.info("public-service - SubscriptionService - findById()");

        return subscriptionClient.getSubscriptionsById(createHeaders(), id);
    }

    public String createSubscription(SubscriptionRequestDto subscriptionRequestDto) throws HystrixRuntimeException {
        log.info("public-service - SubscriptionService - createSubscription()");

        return subscriptionClient.createSubscription(createHeaders(), subscriptionRequestDto);
    }


    public void cancelSubscription(String id) throws HystrixRuntimeException {
        log.info("public-service - SubscriptionService - cancelSubscription()");

        subscriptionClient.cancelSubscription(createHeaders(), id);
    }

}
