package com.student.q2.repository;

import com.student.q2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByEmailIgnoreCase(String email);
    List<Student> findByCourseNameContainingIgnoreCase(String courseName);

    @Query("SELECT s FROM Student s ORDER BY (s.programmingScore + s.mathematicsScore) DESC")
    List<Student> findMeritList();

    @Query("SELECT s FROM Student s JOIN s.technicalSkills skill WHERE LOWER(skill) = LOWER(:skill)")
    List<Student> findStudentsWithSkill(@Param("skill") String skill);

    @Query("SELECT s FROM Student s WHERE s.programmingScore < 50 AND s.mathematicsScore >= 75")
    List<Student> findProgrammingNeedsImprovement();

    @Query("SELECT skill FROM Student s JOIN s.technicalSkills skill GROUP BY skill HAVING COUNT(s) > 1")
    List<String> findSharedSkillNames();

    @Query("SELECT s FROM Student s JOIN s.technicalSkills skill WHERE LOWER(skill) = LOWER(:skill)")
    List<Student> findStudentsSharingSkill(@Param("skill") String skill);
}
