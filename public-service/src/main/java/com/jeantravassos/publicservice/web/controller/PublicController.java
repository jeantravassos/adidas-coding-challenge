package com.jeantravassos.publicservice.web.controller;

import com.jeantravassos.publicservice.dto.SubscriptionRequestDto;
import com.jeantravassos.publicservice.model.Subscription;
import com.jeantravassos.publicservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping("/api/public/subscriptions")
@Slf4j
public class PublicController {

    private SubscriptionService subscriptionService;

    public PublicController(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/")
    public ResponseEntity getAllSubscriptions() {
        log.info("public-service - PublicController - getAllSubscriptions()");

        List<Subscription> subscriptionList = subscriptionService.getAllSubscriptions();

        if (subscriptionList == null || subscriptionList.isEmpty()) {
            log.info("nothing found");
            return ResponseEntity.ok().build();
        }

        log.info("count of subscriptions found: {}", subscriptionList.size());
        return ResponseEntity.ok(subscriptionList.stream().map(Subscription::getId).collect(Collectors.toList()));
    }

    @PostMapping("/")
    public ResponseEntity createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        log.info("public-service - PublicController - createSubscription()");
        //TODO - Debug and check which error will be thrown with no mandatory fields

        //TODO - Treat exceptions

        String subscriptionId = subscriptionService.createSubscription(subscriptionRequestDto);

        return ResponseEntity.ok(subscriptionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSubscriptionById(@PathVariable String id) {
        log.info("public-service - PublicController - getSubscriptionById()");
        if (isBlank(id)) {
            log.error("Mandatory param ID is missing");
            return ResponseEntity.unprocessableEntity().body("ID is missing");
        }

        Subscription subscription = subscriptionService.findById(id);

        return ResponseEntity.ok(subscription);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity cancelSubscription(
            @PathVariable final String id) {
        log.info("public-service - PublicController - cancelSubscription()");
        if (isBlank(id)) {
            log.error("Mandatory param ID is missing");
            return ResponseEntity.unprocessableEntity().body("ID is missing");
        }

        subscriptionService.cancelSubscription(id);

        return ResponseEntity.ok("Subscription cancelled");
    }

}
