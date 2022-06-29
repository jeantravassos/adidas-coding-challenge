package com.jeantravassos.subscriptionsservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FallbackHystrix implements EmailClient{

    @Override
    public void sendEmail(String header, String email) {
        log.info("Fallback called sendEmail....");
    }
}
