package com.jeantravassos.subscriptionsservice.web.controller;

import com.jeantravassos.subscriptionsservice.dto.SubscriptionRequestDto;
import com.jeantravassos.subscriptionsservice.model.Subscription;
import com.jeantravassos.subscriptionsservice.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity findAllSubscriptions() {
        log.info("subscriptions-service - SubscriptionController - findAllSubscriptions()");

        List<Subscription> subscriptionList = subscriptionService.getAllSubscriptions();

        if (subscriptionList == null || subscriptionList.isEmpty()) {
            log.info("nothing found");
            return ResponseEntity.ok().build();
        }

        log.info("count of subscriptions found: {}", subscriptionList.size());
        return ResponseEntity.ok(subscriptionList);
    }

    @PostMapping("/")
    public ResponseEntity createSubscription(@Valid @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        log.info("subscriptions-service - SubscriptionController - createSubscription()");

        if (!validate(subscriptionRequestDto)) {
            throw new IllegalArgumentException("One or more mandatory fields are missing");
        }

        String subscriptionId = subscriptionService.createSubscription(subscriptionRequestDto);
        return ResponseEntity.ok(subscriptionId);
    }

    private boolean validate(SubscriptionRequestDto subscriptionRequestDto) {
        if (subscriptionRequestDto.getConsent() == null || isBlank(subscriptionRequestDto.getEmail()) ||
                subscriptionRequestDto.getDateOfBirth() == null || subscriptionRequestDto.getNewsletterId() == null) {
            return false;
        }

        return true;
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
