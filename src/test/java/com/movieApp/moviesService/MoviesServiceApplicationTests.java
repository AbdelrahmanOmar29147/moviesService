package com.movieApp.moviesService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoviesServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void applicationContextTest() {
		MoviesServiceApplication.main(new String[] {});
	}

}
