package ru.skillfactory.studyPlatform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "scores_tab")
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "lesson_id")
    private long lessonId;

    @Column(name = "score")
    private double score;
}
