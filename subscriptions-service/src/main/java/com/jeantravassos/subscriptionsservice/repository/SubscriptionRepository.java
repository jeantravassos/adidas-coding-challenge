package com.jeantravassos.subscriptionsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeantravassos.subscriptionsservice.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String>{

}