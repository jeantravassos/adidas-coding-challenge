package com.jeantravassos.subscriptionsservice.web.controller;

import com.jeantravassos.subscriptionsservice.dto.SubscriptionRequestDto;
import com.jeantravassos.subscriptionsservice.model.Subscription;
import com.jeantravassos.subscriptionsservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping("/api/subscriptions")
@Slf4j
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    public SubscriptionController(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/")
    public ResponseEntity getAllSubscriptions() {
        log.info("subscriptions-service - SubscriptionController - getAllSubscriptions()");

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
        log.info("subscriptions-service - SubscriptionController - createSubscription()");
        //TODO - Debug and check which error will be thrown with no mandatory fields

        //TODO - Treat exceptions

        String subscriptionId = subscriptionService.createSubscription(subscriptionRequestDto);

        return ResponseEntity.ok(subscriptionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSubscriptionById(@PathVariable String id) {
        log.info("subscriptions-service - SubscriptionController - getSubscriptionById()");
        if (isBlank(id)) {
            log.error("Mandatory param ID is missing");
            return ResponseEntity.unprocessableEntity().body("ID is missing");
        }

        Optional<Subscription> subscriptionOptional = subscriptionService.findById(id);
        if (subscriptionOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(subscriptionOptional.get());
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity cancelSubscription(
            @PathVariable final String id) {
        log.info("subscriptions-service - SubscriptionController - cancelSubscription()");
        if (isBlank(id)) {
            log.error("Mandatory param ID is missing");
            return ResponseEntity.unprocessableEntity().body("ID is missing");
        }

        subscriptionService.cancelSubscription(id);

        return ResponseEntity.ok("Subscription cancelled");
    }

}
