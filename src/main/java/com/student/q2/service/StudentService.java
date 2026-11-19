package com.student.q2.service;

import com.student.q2.model.Student;
import com.student.q2.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) { this.repository = repository; }

    public Student addStudent(Student student) {
        if (repository.existsById(student.getStudentId()))
            throw new IllegalArgumentException("Student ID already exists: " + student.getStudentId());
        if (repository.findByEmailIgnoreCase(student.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already exists: " + student.getEmail());
        return repository.save(student);
    }

    public List<Student> getAll() { return repository.findAll(); }

    public Student getById(String id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Student not found: " + id));
    }

    public Student getByEmail(String email) {
        return repository.findByEmailIgnoreCase(email)
            .orElseThrow(() -> new NoSuchElementException("Student not found for email: " + email));
    }

    public List<Student> searchByCourse(String course) {
        return repository.findByCourseNameContainingIgnoreCase(course);
    }

    public List<Student> meritList() { return repository.findMeritList(); }

    public List<String> sharedSkillNames() { return repository.findSharedSkillNames(); }

    public Map<String, List<Student>> studentsGroupedBySharedSkill() {
        Map<String, List<Student>> result = new LinkedHashMap<>();
        for (String skill : sharedSkillNames()) {
            result.put(skill, repository.findStudentsSharingSkill(skill));
        }
        return result;
    }

    public List<Student> programmingNeedsImprovement() {
        return repository.findProgrammingNeedsImprovement();
    }

    @Transactional
    public Student updateStudent(String id, Student updated) {
        Student current = getById(id);
        if (!current.getEmail().equalsIgnoreCase(updated.getEmail())
                && repository.findByEmailIgnoreCase(updated.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + updated.getEmail());
        }
        current.setStudentName(updated.getStudentName());
        current.setEmail(updated.getEmail());
        current.setCourseName(updated.getCourseName());
        current.setProgrammingScore(updated.getProgrammingScore());
        current.setMathematicsScore(updated.getMathematicsScore());
        current.setTechnicalSkills(updated.getTechnicalSkills());
        return repository.save(current);
    }

    public void deleteStudent(String id) { repository.delete(getById(id)); }
}
