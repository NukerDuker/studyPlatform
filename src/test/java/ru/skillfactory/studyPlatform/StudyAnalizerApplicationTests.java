package ru.skillfactory.studyPlatform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudyAnalizerApplicationTests {

	@Value("${spring.datasourse.password}")
	private String pass;

	@Test
	void contextLoads() {

	}

}
