package ru.skillfactory.studyPlatform.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@Table(name = "students_rates_tab")
public class StudentRate {

    @Hidden
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "course_id")
    private long courseId;

    @Column
    private double rate;
}
