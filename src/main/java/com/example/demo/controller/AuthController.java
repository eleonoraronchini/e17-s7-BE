package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.payload.LoginRequest;
import com.example.demo.security.jwtUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@EnableMethodSecurity
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final jwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, jwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public String register(@RequestBody User user){
        userService.registerUser(user);
        return "user register successfully!👍";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        Optional<User> user = userService.getUserByUsername(loginRequest.getUsername());
        if(user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(),user.get().getPassword())){
            String token = jwtUtil.generateToken(user.get().getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("invalid credential!😒");

    }

}
