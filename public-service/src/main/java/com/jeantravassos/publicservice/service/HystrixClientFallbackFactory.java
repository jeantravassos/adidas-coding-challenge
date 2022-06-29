package com.jeantravassos.publicservice.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<SubscriptionClient> {
    @Override
    public SubscriptionClient create(Throwable cause) {
        return new HystrixClientFallbackFactoryBean(cause);
    }
}