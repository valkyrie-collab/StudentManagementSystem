package com.valkyrie.authentication_service.service;

import com.valkyrie.authentication_service.config.TokenConfiguration;
import com.valkyrie.authentication_service.feign.StudentFeignController;
import com.valkyrie.authentication_service.feign.TeacherFeignController;
import com.valkyrie.authentication_service.model.Store;
import com.valkyrie.authentication_service.model.Student;
import com.valkyrie.authentication_service.model.Teacher;
import com.valkyrie.authentication_service.model.User;
import com.valkyrie.authentication_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder(12);

    private UserRepository repo;
    @Autowired
    private void setRepository(UserRepository repo) {this.repo = repo;}

    private AuthenticationManager authenticationManager;
    @Autowired
    private void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private StudentFeignController studentFeign;
    @Autowired
    private void setStudentFeign(StudentFeignController studentFeign) {
        this.studentFeign = studentFeign;
    }

    private TeacherFeignController teacherFeign;
    @Autowired
    private void setTeacherFeign(TeacherFeignController teacherFeign) {
        this.teacherFeign = teacherFeign;
    }

    private TokenConfiguration configuration;
    @Autowired
    private void setConfiguration(TokenConfiguration configuration) {
        this.configuration  = configuration;
    }

    public Store<String> signIn(User user) {

        if (repo.findById(user.getUsername()).orElse(null) != null) {
            return Store.initialize(HttpStatus.OK, "The user is Already saved please log In.....");
        }

        repo.save(user.setPassword(ENCODER.encode(user.getPassword())));
        return repo.findById(user.getUsername()).orElse(null) != null?
                Store.initialize(HttpStatus.ACCEPTED, "The User saved Successfully......") :
                Store.initialize(HttpStatus.BAD_REQUEST, "The User not saved......");
    }

    public Store<String> logIn(User user) {
        String token = "No token generated";
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            token = configuration.generateToken(user.getUsername(), authentication.getAuthorities());
            return Store.initialize(HttpStatus.OK, token);
        }

        return Store.initialize(HttpStatus.BAD_REQUEST, token);
    }

    public Store<User> getUser(String username) {
        User user = repo.findById(username).orElse(null);

        return Store.initialize(HttpStatus.OK, user);
    }

    public Store<String> getTeacherResponse(Teacher teacher, String role, String password) {
        ResponseEntity<String> response = teacherFeign.save(teacher);

        String email = teacher.getEmail();
        StringBuilder id = new StringBuilder();

        for (char character : email.toCharArray()) {
            if (character == '@') {
                break;
            }
            id.append(character);
        }

        User user = new User().setUsername(id.toString()).setPassword(password).setRole(role);

        if (repo.findById(user.getUsername()).orElse(null) != null) {
            return Store.initialize(HttpStatus.OK, "The user is Already saved please log In.....");
        }

        repo.save(user.setPassword(ENCODER.encode(user.getPassword())));
        return repo.findById(user.getUsername()).orElse(null) != null &&
                response.getStatusCode().equals(HttpStatusCode.valueOf(200))?
                Store.initialize(HttpStatus.ACCEPTED, "The User saved Successfully......") :
                Store.initialize(HttpStatus.BAD_REQUEST, "The User not saved......");
    }

    public Store<String> getStudentResponse(Student student, String role, String password) {
        ResponseEntity<List<String>> response = studentFeign.save(student);

        String email = student.getEmail();
        StringBuilder id = new StringBuilder();

        for (char character : email.toCharArray()) {
            if (character == '@') {
                break;
            }
            id.append(character);
        }

        User user = new User().setUsername(id.toString()).setPassword(password).setRole(role);

        if (repo.findById(user.getUsername()).orElse(null) != null) {
            return Store.initialize(HttpStatus.OK, "The user is Already saved please log In.....");
        }

        repo.save(user.setPassword(ENCODER.encode(user.getPassword())));
        return repo.findById(user.getUsername()).orElse(null) != null &&
                response.getStatusCode().equals(HttpStatusCode.valueOf(200))?
                Store.initialize(HttpStatus.ACCEPTED, "The User saved Successfully......") :
                Store.initialize(HttpStatus.BAD_REQUEST, "The User not saved......");
    }
}

