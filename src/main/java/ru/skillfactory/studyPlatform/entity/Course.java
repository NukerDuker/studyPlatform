package ru.skillfactory.studyPlatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "courses_tab")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons = new HashSet<>();

    public void addLesson(Lesson lesson) {lessons.add(lesson);}
    public void addLesson(Set<Lesson> lessonsSet) {
        lessons.addAll(lessonsSet);
    }


}
