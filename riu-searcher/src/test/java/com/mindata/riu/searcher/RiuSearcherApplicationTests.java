package com.mindata.riu.searcher;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@ActiveProfiles("test")
class RiuSearcherApplicationTests {

	@Test
	void contextLoads() {
		try (MockedStatic<SpringApplication> springApplication = mockStatic(SpringApplication.class)) {
			var args = new String[]{};

			RiuSearcherApplication.main(args);

			springApplication.verify(() -> {
				SpringApplication.run(RiuSearcherApplication.class, args);
			});
		}
	}

}
