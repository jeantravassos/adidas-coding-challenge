package com.jeantravassos.subscriptionsservice.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<EmailClient> {
    @Override
    public EmailClient create(Throwable cause) {
        return new com.jeantravassos.subscriptionsservice.service.HystrixClientFallbackFactoryBean(cause);
    }
}