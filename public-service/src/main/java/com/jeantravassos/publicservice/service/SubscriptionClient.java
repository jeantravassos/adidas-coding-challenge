package com.jeantravassos.publicservice.service;

import com.jeantravassos.publicservice.dto.SubscriptionRequestDto;
import com.jeantravassos.publicservice.model.Subscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "subscriptions-service", fallbackFactory = HystrixClientFallbackFactory.class)
public interface SubscriptionClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/subscriptions/")
    List<Subscription> getAllSubscriptions(@RequestHeader("Authorization") String header);

    @RequestMapping(method = RequestMethod.GET, value = "/api/subscriptions/{id}")
    Subscription getSubscriptionsById(
            @RequestHeader("Authorization") final String header,
            @PathVariable final String id);

    @RequestMapping(method = RequestMethod.POST, value = "/api/subscriptions/")
    String createSubscription(
            @RequestHeader("Authorization") final String header,
            @RequestBody SubscriptionRequestDto subscriptionRequestDto);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/subscriptions/cancel/{id}")
    String cancelSubscription(
            @RequestHeader("Authorization") final String header,
            @PathVariable final String id);

}