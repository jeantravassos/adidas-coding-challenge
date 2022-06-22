package com.jeantravassos.publicservice.web.controller;

import com.jeantravassos.publicservice.model.Subscription;
import com.jeantravassos.publicservice.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public/subscriptions")
public class PublicController {
    private static final Logger LOG = LoggerFactory.getLogger(PublicController.class);

    private SubscriptionService subscriptionService;

    public PublicController(final SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/")
    public ResponseEntity getAllSubscriptions() {
        LOG.info("public-service - PublicController - getAllSubscriptions()");
        LOG.info("method called");

        List<Subscription> subscriptionList = subscriptionService.getAllSubscriptions();

        if (subscriptionList == null || subscriptionList.isEmpty()) {
            LOG.info("not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No subscriptions found");
        }

        LOG.info("count of subscriptions found: {}", subscriptionList.size());
        return ResponseEntity.ok(subscriptionList.stream().map(Subscription::getId).collect(Collectors.toList()));
    }

}
