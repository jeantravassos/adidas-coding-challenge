package com.jeantravassos.publicservice.service;

import com.jeantravassos.publicservice.dto.SubscriptionRequestDto;
import com.jeantravassos.publicservice.model.Subscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HystrixClientFallbackFactoryBean implements SubscriptionClient {

    private final Throwable cause;

    public HystrixClientFallbackFactoryBean(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<Subscription> getAllSubscriptions(String header) {
        log.error("Fallback called getAllSubscriptions.... ERROR: ", cause);
        throw new RuntimeException(cause);
    }

    @Override
    public Subscription getSubscriptionsById(String header, String id) {
        log.error("Fallback called getSubscriptionsById.... ERROR: ", cause);
        throw new RuntimeException(cause);
    }

    @Override
    public String createSubscription(String header, SubscriptionRequestDto subscriptionRequestDto) {
        log.error("Fallback called createSubscription.... ERROR: ", cause);
        throw new RuntimeException(cause);
    }

    @Override
    public String cancelSubscription(String header, String id) {
        log.error("Fallback called cancelSubscription.... ERROR: ", cause);
        throw new RuntimeException(cause);
    }
}
