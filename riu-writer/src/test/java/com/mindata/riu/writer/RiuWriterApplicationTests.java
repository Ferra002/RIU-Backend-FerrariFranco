package com.mindata.riu.writer;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@ActiveProfiles("test")
class RiuWriterApplicationTests {

	@Test
	void contextLoads() {
		try (MockedStatic<SpringApplication> springApplication = mockStatic(SpringApplication.class)) {
			var args = new String[]{};

			RiuWriterApplication.main(args);

			springApplication.verify(() -> {
				SpringApplication.run(RiuWriterApplication.class, args);
			});
		}
	}

}
