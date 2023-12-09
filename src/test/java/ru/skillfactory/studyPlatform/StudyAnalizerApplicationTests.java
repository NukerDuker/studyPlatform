package ru.skillfactory.studyPlatform;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class StudyAnalizerApplicationTests {

	@Value("${spring.datasource.jdbcUrl}")
	private String url;

	@Test
	void contextLoads() {
		log.info(url);
	}

}
