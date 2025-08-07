package com.valkyrie.teacher_service.controller;

import com.valkyrie.teacher_service.model.TeacherWrapper;
import com.valkyrie.teacher_service.model.UpperCaseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.valkyrie.teacher_service.model.Teacher;
import com.valkyrie.teacher_service.model.Store;
import com.valkyrie.teacher_service.service.TeacherService;
//import com.valkyrie.teacher_service.model.TeacherUpdater;

//import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService service;
    @Autowired
    private void setService(TeacherService service) {this.service = service;}

    @PostMapping("/save-teacher")
    public ResponseEntity<String> save(@RequestBody List<Teacher> teachers) {
        System.out.println("this is working");
        Store<String> store = service.save(teachers);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-teacher")
    public ResponseEntity<String> update(@RequestBody List<Teacher> teachers) {
        return save(teachers);
    }

    @GetMapping("/check-for-teacher")
    public ResponseEntity<String> checkForTeacher(@RequestParam String id) {
        Store<String> store = service.checkTeacherById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-teacher-by-class")
    public ResponseEntity<String> findTeacherId(@RequestParam String classTeacher) {
        Store<String> store = service.findTeacherByClass(classTeacher);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-teacher-by-id")
    public ResponseEntity<TeacherWrapper> findTeacherById(@RequestParam String id) {
        Store<TeacherWrapper> store = service.findTeacherById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-teachers-by-name")
    public ResponseEntity<List<TeacherWrapper>> findTeachersByName(@RequestParam(required = false) String firstName,
                                                            @RequestParam(required = false) String secondName) {
        Store<List<TeacherWrapper>> store = null;
//
        store = service.findTeachersByName(firstName, secondName);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-teachers-by-father-name")
    public ResponseEntity<List<TeacherWrapper>> findTeachersByFatherName(@RequestParam(required = false) String firstName,
                                                                   @RequestParam(required = false) String secondName) {
        Store<List<TeacherWrapper>> store = null;
//
        store = service.findTeachersByFatherName(firstName, secondName);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-teachers-by-mother-name")
    public ResponseEntity<List<TeacherWrapper>> findTeachersByMotherName(@RequestParam(required = false) String firstName,
                                                                   @RequestParam(required = false) String secondName) {
        Store<List<TeacherWrapper>> store = null;
//
        store = service.findTeachersByMotherName(firstName, secondName);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

//    @GetMapping("/find-teachers-by-subject")
//    public ResponseEntity<List<TeacherWrapper>> findTeachersBySubject(@RequestParam String subject) {
//        Store<List<TeacherWrapper>> store = service.findTeachersBySubject(subject);
//
//        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
//    }

    @DeleteMapping("/delete-teacher-by-id")
    public ResponseEntity<String> deleteTeacherById(@RequestParam String id) {
        Store<String> store = service.removeTeacherById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

//    @DeleteMapping("/delete-teachers-by-subject")
//    public ResponseEntity<String> deleteTeachersBySubject(@RequestParam String subject) {
//        Store<String> store = service.removeTeacherBySubject(subject);
//
//        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
//    }
}
