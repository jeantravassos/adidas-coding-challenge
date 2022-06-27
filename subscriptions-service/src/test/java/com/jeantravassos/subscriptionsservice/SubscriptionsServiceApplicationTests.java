package com.jeantravassos.subscriptionsservice;

import com.jeantravassos.subscriptionsservice.dto.SubscriptionRequestDto;
import com.jeantravassos.subscriptionsservice.model.Subscription;
import com.jeantravassos.subscriptionsservice.service.SubscriptionService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SystemStubsExtension.class)
class SubscriptionsServiceApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@MockBean
	private SubscriptionService subscriptionService;

	@Test
	public void find_all_subscriptions_empty() {
		when(subscriptionService.getAllSubscriptions())
				.thenReturn(new ArrayList<>());

		ResponseEntity<List> response =
				restTemplate.exchange("/api/subscriptions/",
						HttpMethod.GET, HttpEntity.EMPTY, List.class);

		assertThat(response).isNotNull();
		assertThat(response.getBody()).isNull();
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
				restTemplate.exchange("/api/subscriptions/",
						HttpMethod.GET, HttpEntity.EMPTY, List.class);
		assertThat(response).isNotNull();

		List responseBody = response.getBody();
		assertThat(responseBody.size()).isEqualTo(1);
		Map<String, String> resultMap = (Map<String, String>) responseBody.get(0);
		assertThat(resultMap.get("id")).isEqualTo(s.getId());
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
				restTemplate.exchange("/api/subscriptions/",
						HttpMethod.GET, HttpEntity.EMPTY, List.class);
		assertThat(response).isNotNull();

		List responseBody = response.getBody();
		assertThat(responseBody.size()).isEqualTo(2);

		Map<String, String> resultMap = (Map<String, String>) responseBody.get(0);
		assertThat(resultMap.get("id")).isEqualTo(s1.getId());

		resultMap = (Map<String, String>) responseBody.get(1);
		assertThat(resultMap.get("id")).isEqualTo(s2.getId());
	}

	@Test
	public void get_subscription_by_id_with_no_id() {
		ResponseEntity response =
				restTemplate.exchange("/api/subscriptions/ ",
						HttpMethod.GET, HttpEntity.EMPTY, Void.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void get_subscription_by_id_ok() {
		Subscription s = Subscription.builder()
				.id("123")
				.consent(true)
				.firstName("Jean")
				.email("abc@def.ghi")
				.newsletterId(123L)
				.dateOfBirth(LocalDate.of(1985, Month.JANUARY, 25))
				.gender(1)
				.build();

		when(subscriptionService.findById("123"))
				.thenReturn(java.util.Optional.ofNullable(s));
		ResponseEntity<Subscription> response =
				restTemplate.exchange("/api/subscriptions/123",
						HttpMethod.GET, HttpEntity.EMPTY, Subscription.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		Subscription subscription = response.getBody();
		assertThat(subscription.getId()).isEqualTo(s.getId());
	}


	@Test
	public void cancel_subscription_no_id() {
		ResponseEntity response =
				restTemplate.exchange("/api/subscriptions/cancel/ ",
						HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void cancel_subscription_ok() {
		doNothing().when(subscriptionService).cancelSubscription("123");

		ResponseEntity<Void> response =
				restTemplate.exchange("/api/subscriptions/cancel/123",
						HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void create_subscription_ok() throws JSONException {
		SubscriptionRequestDto s = SubscriptionRequestDto.builder()
				.consent(true)
				.firstName("Adi")
				.email("adi@adidas.com")
				.newsletterId(23L)
				.dateOfBirth(LocalDate.of(1900, Month.NOVEMBER, 3))
				.gender(1)
				.build();

		when(subscriptionService.createSubscription(s))
				.thenReturn("123");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject subs = new JSONObject();
		subs.put("firstName","Adi");
		subs.put("email","adi@adidas.com");
		subs.put("newsletterId",23L);
		subs.put("gender",1);
		subs.put("consent",true);
		subs.put("dateOfBirth",LocalDate.of(1900, Month.NOVEMBER, 3));

		HttpEntity request =
				new HttpEntity(subs.toString(), headers);
		ResponseEntity<String> response =
				restTemplate.exchange("/api/subscriptions/",
						HttpMethod.POST, request, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("123");
	}


}

