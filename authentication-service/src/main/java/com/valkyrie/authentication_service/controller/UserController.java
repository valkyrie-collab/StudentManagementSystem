package com.valkyrie.authentication_service.controller;

import com.valkyrie.authentication_service.model.Store;
import com.valkyrie.authentication_service.model.Student;
import com.valkyrie.authentication_service.model.Teacher;
import com.valkyrie.authentication_service.model.User;
import com.valkyrie.authentication_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService service;
    @Autowired
    private void setService(UserService service) {this.service = service;}

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody User user) {
        Store<String> store = service.signIn(user.setRole("ROLE_" + user.getRole().toUpperCase()));

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/log-in")
    public ResponseEntity<String> logIn(@RequestBody User user) {
        Store<String> store = service.logIn(user.setRole("ROLE_" + user.getRole().toUpperCase()));

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/get-user")
    public ResponseEntity<User> getUser(@RequestParam String username) {
        Store<User> store = service.getUser(username);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/save-teacher")
    public ResponseEntity<String> saveTeacher(@RequestParam String role,
                                              @RequestParam String password,
                                              @RequestBody Teacher teacher) {
        Store<String> store = service.getTeacherResponse(teacher, "ROLE_" + role.toUpperCase(), password);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/save-student")
    public ResponseEntity<String> saveStudent(@RequestParam String role,
                                              @RequestParam String password,
                                              @RequestBody Student student) {
        Store<String> store = service.getStudentResponse(student, "ROLE_" + role.toUpperCase(), password);

        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
