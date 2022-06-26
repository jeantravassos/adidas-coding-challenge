package com.jeantravassos.publicservice.web.controller;

import com.jeantravassos.publicservice.model.Subscription;
import com.jeantravassos.publicservice.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

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

        List<Subscription> subscriptionList = subscriptionService.getAllSubscriptions();

        if (subscriptionList == null || subscriptionList.isEmpty()) {
            LOG.info("nothing found");
            return ResponseEntity.ok().build();
        }

        LOG.info("count of subscriptions found: {}", subscriptionList.size());
        return ResponseEntity.ok(subscriptionList.stream().map(Subscription::getId).collect(Collectors.toList()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteSubscription(
            @PathVariable final String id) {
        LOG.info("public-service - PublicController - deleteSubscription()");
        if (isBlank(id)) {
            LOG.error("Mandatory param ID is missing");
            return ResponseEntity.unprocessableEntity().body("ID is missing");
        }

        subscriptionService.deleteSubscription(id);

        return ResponseEntity.ok().build();
    }

}
