package com.jeantravassos.emailservice;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SystemStubsExtension.class)
class EmailServiceApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	private HttpHeaders createHeaders(String username, String password){
		return new HttpHeaders() {{
			String auth = username + ":" + password;
			byte[] encodedAuth = Base64.encodeBase64(
					auth.getBytes(Charset.forName("US-ASCII")) );
			String authHeader = "Basic " + new String( encodedAuth );
			set( "Authorization", authHeader );
		}};
	}


	@Test
	public void sendEmail_ok() {
		ResponseEntity<String> response =
				restTemplate.exchange("/api/emails/send/adidas@adidas.com",
						HttpMethod.GET, new HttpEntity(createHeaders("adidas", "challenge")), String.class);

		assertThat(response).isNotNull();
		assertThat(response.getBody()).isNotNull();
	}

}
