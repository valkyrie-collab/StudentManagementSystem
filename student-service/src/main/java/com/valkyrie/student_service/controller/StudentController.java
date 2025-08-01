package com.valkyrie.student_service.controller;

import com.valkyrie.student_service.model.Student;
import com.valkyrie.student_service.model.Store;
import com.valkyrie.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService service;
    @Autowired
    private void setService(StudentService service) {this.service = service;}

    @PostMapping("/save-student")
    public ResponseEntity<List<String>> save(@RequestBody List<Student> students) {
        Store<List<String>> store = service.save(students);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-student")
    public ResponseEntity<List<String>> update(@RequestBody List<Student> students) {return save(students);}

    @GetMapping("/find-student-by-id")
    public ResponseEntity<Student> findById(@RequestParam String id) {
        Store<Student> store = service.findStudentById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-students-by-father-name")
    public ResponseEntity<List<Student>> findByFatherName(@RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) String secondName) {
        Store<List<Student>> store = service.findStudentByFatherName(firstName, secondName);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping()

    @DeleteMapping()

    @DeleteMapping()
}
