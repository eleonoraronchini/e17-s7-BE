package com.example.demo;

import com.example.demo.security.jwtUtil;
import com.example.demo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    jwtUtil jwtUtil;

    public AuthController(UserService userService, com.example.demo.security.jwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public AuthController() {
    }

    @PostMapping("/register")
    public String register (@RequestBody User user){
        userService.registerUser(user);
        return "utente registrato";
    }

}
