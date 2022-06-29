package com.jeantravassos.subscriptionsservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "email-service", fallbackFactory = HystrixClientFallbackFactory.class)
public interface EmailClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/emails/send/{email}")
    void sendEmail(
            @RequestHeader("Authorization") final String header,
            @PathVariable final String email);

}
