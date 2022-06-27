package com.jeantravassos.subscriptionsservice.dao;

import com.jeantravassos.subscriptionsservice.SubscriptionsServiceApplication;
import com.jeantravassos.subscriptionsservice.model.Subscription;
import com.jeantravassos.subscriptionsservice.repository.SubscriptionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SubscriptionsServiceApplication.class)
public class SubscriptionDaoTest {


    @Autowired
    private SubscriptionRepository repository;

    @Test
    public void whenFindById_thenReturnSubscription() {
        List<Subscription> subscriptionList = repository.findAll();
        Subscription subs = subscriptionList.get(0);

        Optional<Subscription> foundEntity = repository
                .findById(subs.getId());

        assertNotNull(foundEntity);
        assertThat(foundEntity.get().getFirstName())
                .isEqualTo(subs.getFirstName());
    }

    @Test
    public void whenFindAll_thenReturnSubscriptions() {
        List<Subscription> subscriptionList = repository.findAll();
        Assert.assertNotNull(subscriptionList);

        // then
        assertThat(subscriptionList.get(0).getFirstName())
                .isEqualTo("Adi");
        assertThat(subscriptionList.get(1).getFirstName())
                .isEqualTo("Adolf");
    }

    @Test
    public void whenDeleteById_thenReturnMessage() {
        List<Subscription> subscriptionList = repository.findAll();
        Assert.assertNotNull(subscriptionList);

        int countSubscriptions = subscriptionList.size();
        repository.deleteById(subscriptionList.get(0).getId());

        subscriptionList = repository.findAll();
        Assert.assertNotNull(subscriptionList);

        assertThat(subscriptionList.size())
                .isEqualTo(countSubscriptions - 1);
    }

    @Test
    public void whenCreateSubscription_thenReturnSubscription() {
        Subscription s = Subscription.builder()
                .consent(true)
                .firstName("Martina")
                .email("martinahingis@adidas.com")
                .newsletterId(4L)
                .dateOfBirth(LocalDate.of(1980, Month.SEPTEMBER, 4))
                .gender(0)
                .build();
        s = repository.save(s);

        Optional<Subscription> found = repository
                .findById(s.getId());

        assertNotNull(found);
        assertThat(found.get().getId())
                .isEqualTo(s.getId());
    }


}
