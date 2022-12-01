package ru.skillfactory.studyPlatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "course_id")
    private long courseId;

    @Column(unique = true)
    private String title;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "max_score")
    private double maxScore;
}
