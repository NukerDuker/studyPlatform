package ru.skillfactory.studyPlatform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "student_id")},
            inverseJoinColumns = { @JoinColumn(name = "course_id")}
    )
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Scores> scores = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<StudentRate> rates = new HashSet<>();



}
