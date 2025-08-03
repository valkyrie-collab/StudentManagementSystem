package com.valkyrie.authentication_service.service;

import com.valkyrie.authentication_service.config.TokenConfiguration;
import com.valkyrie.authentication_service.model.Store;
import com.valkyrie.authentication_service.model.User;
import com.valkyrie.authentication_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
