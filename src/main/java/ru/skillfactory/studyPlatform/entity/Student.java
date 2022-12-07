package ru.skillfactory.studyPlatform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "students_tab")
public class Student {

    @Hidden
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "is_active")
    @JsonProperty("active")
    private boolean isActive;
    @Hidden
    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "course_id",referencedColumnName = "id")}
    )
    private Set<Course> courses = new HashSet<>();
    @Hidden
    @OneToMany
    @JoinColumn(name = "student_id")
    private Set<Score> scores = new HashSet<>();
    @Hidden
    @OneToMany
    @JoinColumn(name = "student_id")
    private Set<StudentRate> rates = new HashSet<>();

    public void addCourse(Course course) {
        this.courses.add(course);
    }
    public void addCourse(Set<Course> course) {
        this.courses.addAll(course);
    }
    public void deleteCourse(Course course) {this.courses.remove(course);}

    public void addScore(Score score) {
        scores.add(score);
    }

    public void addRate(StudentRate rate) {
        rates.add(rate);
    }

}
