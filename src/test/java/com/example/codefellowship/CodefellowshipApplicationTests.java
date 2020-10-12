package com.example.codefellowship;

import com.example.codefellowship.controllers.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;

// https://stackoverflow.com/questions/15203485/spring-test-security-how-to-mock-authentication TODO: seems a good walk thru for straightforward user testing. Maybe for later in the week.

// https://spring.io/guides/gs/testing-web/
// ^^ only tests

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // _RANDOM_PORT_
public class CodefellowshipApplicationTests { // the public modifier is not necessary.

	@Autowired
	private HomeController controller; // standard

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort private int port;

	@Test
	void contextLoads() {} // sanity check. ensures that it runs at all.

	@Test
	public void homePageLoad() { // one step above a sanity check. Proves that there is something on - rendered to - the screen.
		assertThat(this.restTemplate.getForObject("http://localhost:"+ port + "/", String.class)) // tests for some String on the screen.
												  		.contains("Login Here!");
	}

	@Test void signUpLoad() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login", String.class))
														.contains("username");
	}

}
