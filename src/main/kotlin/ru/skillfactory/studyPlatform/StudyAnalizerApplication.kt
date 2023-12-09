package ru.skillfactory.studyPlatform

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@OpenAPIDefinition(
    info = Info(
        title = "StudyPlatform",
        version = "0.85",
        description = """
Back-end system for collecting lists of students, courses, lessons, scores, and analysing students results.
1. Create student;
2. Create course;
3. Create lessons;
4. Add lessons to course (one lesson at a time)
5. Add course to student
6. Make score;
7. Get student to see his rate;
"""
    )
)
@SpringBootApplication
object StudyAnalizerApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(StudyAnalizerApplication::class.java, *args)
    }
}
