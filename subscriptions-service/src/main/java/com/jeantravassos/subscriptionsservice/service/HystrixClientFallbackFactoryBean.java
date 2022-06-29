package com.jeantravassos.subscriptionsservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HystrixClientFallbackFactoryBean implements EmailClient {

    private final Throwable cause;

    public HystrixClientFallbackFactoryBean(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public void sendEmail(String header, String email) {
        log.error("Fallback called sendEmail.... ERROR: ", cause);
        throw new RuntimeException(cause);
    }
}
