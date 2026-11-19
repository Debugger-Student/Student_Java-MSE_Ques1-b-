package com.student.q2.controller;

import com.student.q2.model.Student;
import com.student.q2.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student add(@Valid @RequestBody Student student) { return service.addStudent(student); }

    @GetMapping
    public List<Student> all() { return service.getAll(); }

    @GetMapping("/id/{id}")
    public Student byId(@PathVariable String id) { return service.getById(id); }

    @GetMapping("/email")
    public Student byEmail(@RequestParam String email) { return service.getByEmail(email); }

    @GetMapping("/course")
    public List<Student> byCourse(@RequestParam String course) { return service.searchByCourse(course); }

    @GetMapping("/merit-list")
    public List<Student> meritList() { return service.meritList(); }

    @GetMapping("/shared-skills")
    public Map<String, List<Student>> sharedSkills() { return service.studentsGroupedBySharedSkill(); }

    @GetMapping("/programming-needs-improvement")
    public List<Student> programmingNeedsImprovement() { return service.programmingNeedsImprovement(); }

    @PutMapping("/{id}")
    public Student update(@PathVariable String id, @Valid @RequestBody Student student) {
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) { service.deleteStudent(id); }
}
