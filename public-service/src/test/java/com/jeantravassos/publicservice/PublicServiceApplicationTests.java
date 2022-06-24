package com.jeantravassos.publicservice;

import com.jeantravassos.publicservice.model.Subscription;
import com.jeantravassos.publicservice.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SystemStubsExtension.class)
class PublicServiceApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@MockBean
	private SubscriptionService subscriptionService;

	@Test
	public void find_all_subscriptions_empty() {
		when(subscriptionService.getAllSubscriptions())
				.thenReturn(new ArrayList<>());

		ResponseEntity<List> response =
				restTemplate.exchange("/api/public/subscriptions/",
						HttpMethod.GET, HttpEntity.EMPTY, List.class);

		assertThat(response).isNotNull();
		assertThat(response.getBody().size()).isEqualTo(0);
	}

	@Test
	public void find_all_subscriptions_one_result() {
		Subscription s = Subscription.builder()
				.id("123")
				.consent(true)
				.firstName("Jean")
				.email("abc@def.ghi")
				.newsletterId(123L)
				.dateOfBirth(LocalDate.of(1985, Month.JANUARY, 25))
				.gender(1)
				.build();

		List results = new ArrayList<>();
		results.add(s);

		when(subscriptionService.getAllSubscriptions())
				.thenReturn(results);

		ResponseEntity<List> response =
				restTemplate.exchange("/api/public/subscriptions/",
						HttpMethod.GET, HttpEntity.EMPTY, List.class);
		assertThat(response).isNotNull();

		List responseBody = response.getBody();
		assertThat(responseBody.size()).isEqualTo(1);
		assertThat(responseBody.get(0)).isEqualTo("123");
	}

	@Test
	public void find_all_subscriptions_multiple_results() {
		Subscription s1 = Subscription.builder()
				.id("123")
				.consent(true)
				.firstName("Pele")
				.email("abc@def.ghi")
				.newsletterId(123L)
				.dateOfBirth(LocalDate.of(1965, Month.JANUARY, 25))
				.gender(1)
				.build();

		Subscription s2 = Subscription.builder()
				.id("456")
				.consent(true)
				.firstName("Hortencia")
				.email("abc@def.ghi")
				.newsletterId(123L)
				.dateOfBirth(LocalDate.of(1975, Month.JUNE, 11))
				.gender(0)
				.build();

		List results = new ArrayList<>();
		results.add(s1);
		results.add(s2);

		when(subscriptionService.getAllSubscriptions())
				.thenReturn(results);

		ResponseEntity<List> response =
				restTemplate.exchange("/api/public/subscriptions/",
						HttpMethod.GET, HttpEntity.EMPTY, List.class);
		assertThat(response).isNotNull();

		List responseBody = response.getBody();
		assertThat(responseBody.size()).isEqualTo(2);
		assertThat(responseBody.get(0)).isEqualTo("123");
		assertThat(responseBody.get(1)).isEqualTo("456");
	}

}
