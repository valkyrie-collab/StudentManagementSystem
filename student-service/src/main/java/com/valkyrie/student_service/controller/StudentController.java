package com.valkyrie.student_service.controller;

import com.valkyrie.student_service.config.TokenConfiguration;
import com.valkyrie.student_service.model.Student;
import com.valkyrie.student_service.model.Store;
import com.valkyrie.student_service.model.StudentWrapper;
import com.valkyrie.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService service;
    @Autowired
    private void setService(StudentService service) {this.service = service;}

    private TokenConfiguration config;
    @Autowired
    private void setConfig(TokenConfiguration config) {this.config = config;}

    @PostMapping("/save-student")
    public ResponseEntity<List<String>> save(@RequestBody Student student) {
//        String id = token;
//        try {
//            id = config.getUsername(id);
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
        String email = student.getEmail();
        StringBuilder id = new StringBuilder();

        for (char character : email.toCharArray()) {
            if (character == '@') {
                break;
            }
            id.append(character);
        }
        Store<List<String>> store = service.save(id.toString(), student);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-student")
    public ResponseEntity<List<String>> update(@RequestBody Student student) {
        Store<List<String>> store = service.save(null, student);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-student-by-id")
    public ResponseEntity<StudentWrapper> findById(@RequestParam (required = false) String token,
                                                   @RequestParam (required = false) String id,
                                                   @RequestParam boolean doFeign) {

        try{

            if (id == null) {
                id = config.getUsername(token);
            } else {
                id = new String(Base64.getDecoder().decode(id));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Store<StudentWrapper> store = service.findStudentById(id, doFeign);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/check-student-present")
    public ResponseEntity<Boolean> checkStudentPresent(@RequestParam (required = false) String token,
                                                       @RequestParam (required = false) String id) {

        try{

            if (id == null) {
                id = config.getUsername(token);
            } else {
                id = new String(Base64.getDecoder().decode(id));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Store<Boolean> store = service.checkStudentPresent(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-students-by-father-name")
    public ResponseEntity<List<StudentWrapper>> findByFatherName(@RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) String secondName,
                                                                 @RequestParam boolean doFeign) {
        Store<List<StudentWrapper>> store = service.findStudentByFatherName(firstName, secondName, doFeign);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-student-by-mother-name")
    public ResponseEntity<List<StudentWrapper>> findByMotherName(@RequestParam(required = false) String firstName,
                                                          @RequestParam(required = false) String secondName,
                                                                 @RequestParam boolean doFeign) {
        Store<List<StudentWrapper>> store = service.findStudentByMotherName(firstName, secondName, doFeign);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-students-by-name")
    public ResponseEntity<List<StudentWrapper>> findByName(@RequestParam(required = false) String firstName,
                                                    @RequestParam(required = false) String secondName,
                                                           @RequestParam boolean doFeign) {
        Store<List<StudentWrapper>> store = service.findStudentByName(firstName, secondName, doFeign);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-student-by-stander-and-section")
    public ResponseEntity<List<StudentWrapper>> findByStanderAndSection(@RequestParam(required = false) String stander,
                                                                 @RequestParam(required = true) char section,
                                                                        @RequestParam boolean doFeign) {
        Store<List<StudentWrapper>> store = service.findStudentByStanderAndSection(stander, section, doFeign);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-students-by-teacher-id")
    public ResponseEntity<List<StudentWrapper>> findByTeacherId(@RequestParam String teacherId,
                                                                @RequestParam boolean doFeign) {

        try{
            teacherId = new String(Base64.getDecoder().decode(teacherId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Store<List<StudentWrapper>> store = service.findStudentsByClassTeacherId(teacherId, doFeign);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

//    @GetMapping("/find-students-by-teacher-name")
//    public ResponseEntity<List<StudentWrapper>> findByTeacherName(@RequestParam String teacherName) {
//        Store<List<StudentWrapper>> store = service.findStudentsByClassTeacherName(teacherName);
//
//        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
//    }

    @DeleteMapping("/delete-student-by-id")
    public ResponseEntity<String> deleteStudentById(@RequestParam String id) {

        try{
            id = new String(Base64.getDecoder().decode(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Store<String> store = service.deleteStudentById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/delete-students-by-stander-and-section")
    public ResponseEntity<String> deleteStudentsByStanderAndSection(@RequestParam String stander,
                                                                    @RequestParam char section) {
        Store<String> store = service.deleteStudentsByStanderAndSection(stander, section);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
