package ru.skillfactory.studyPlatform.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "scores_tab")
public class Score {

    @Hidden
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @Column(name = "lesson_id")
    private long lessonId;

    @Column(name = "score")
    private double score;

}
