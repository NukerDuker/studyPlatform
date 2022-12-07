package ru.skillfactory.studyPlatform.entity;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@Table(name = "lessons_tab")
public class Lesson {

    @Hidden
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String title;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "max_score")
    private double maxScore;
}
