package com.valkyrie.teacher_service.controller;

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
        Store<String> store = service.save(teachers);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-teacher")
    public ResponseEntity<String> update(@RequestBody List<Teacher> teachers) {
        return save(teachers);
    }

    @GetMapping("/find-teacher-by-id")
    public ResponseEntity<Teacher> findTeacherById(@RequestParam String id) {
        Store<Teacher> store = service.findTeacherById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-teachers-by-name")
    public ResponseEntity<List<Teacher>> findTeachersByName(@RequestParam(required = false) String firstName,
                                                            @RequestParam(required = false) String secondName) {
        Store<List<Teacher>> store = null;
//        System.out.println("firstname="+firstName);
//        System.out.println("secondname="+secondName);

        if (firstName == null) {
            store = service.findTeachersBySecondName(secondName);
        } else if (secondName == null) {
            store = service.findTeachersByFirstName(firstName);
        } else {
            store = service.findTeachersByName(firstName, secondName);
        }

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-teachers-by-subject")
    public ResponseEntity<List<Teacher>> findTeachersBySubject(@RequestParam String subject) {
        Store<List<Teacher>> store = service.findTeachersBySubject(subject);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/delete-teacher-by-id")
    public ResponseEntity<String> deleteTeacherById(@RequestParam String id) {
        Store<String> store = service.removeTeacherById(id);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/delete-teachers-by-subject")
    public ResponseEntity<String> deleteTeachersBySubject(@RequestParam String subject) {
        Store<String> store = service.removeTeacherBySubject(subject);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
