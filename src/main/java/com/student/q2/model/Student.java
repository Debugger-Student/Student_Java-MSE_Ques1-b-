package com.student.q2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Student {
    @Id
    @Column(name = "student_id", nullable = false, unique = true)
    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Student name is required")
    @Column(nullable = false)
    private String studentName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Course is required")
    @Column(nullable = false)
    private String courseName;

    @Min(value = 0, message = "Programming score must be between 0 and 100")
    @Max(value = 100, message = "Programming score must be between 0 and 100")
    private int programmingScore;

    @Min(value = 0, message = "Mathematics score must be between 0 and 100")
    @Max(value = 100, message = "Mathematics score must be between 0 and 100")
    private int mathematicsScore;

    @ElementCollection
    @CollectionTable(name = "student_technical_skills",
        joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "skill_name", nullable = false)
    private List<String> technicalSkills = new ArrayList<>();

    public Student() {}

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getProgrammingScore() { return programmingScore; }
    public void setProgrammingScore(int programmingScore) { this.programmingScore = programmingScore; }
    public int getMathematicsScore() { return mathematicsScore; }
    public void setMathematicsScore(int mathematicsScore) { this.mathematicsScore = mathematicsScore; }
    public List<String> getTechnicalSkills() { return technicalSkills; }
    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills == null ? new ArrayList<>() : new ArrayList<>(technicalSkills);
    }

    @Transient
    public double getAverageScore() {
        return (programmingScore + mathematicsScore) / 2.0;
    }

    @Transient
    public String getPerformanceCategory() {
        double avg = getAverageScore();
        if (avg >= 85) return "Excellent";
        if (avg >= 70) return "Good";
        if (avg >= 50) return "Satisfactory";
        return "Needs Improvement";
    }

    @Transient
    public String getStrongerSubject() {
        if (programmingScore > mathematicsScore) return "Programming";
        if (mathematicsScore > programmingScore) return "Mathematics";
        return "Both are equal";
    }
}
