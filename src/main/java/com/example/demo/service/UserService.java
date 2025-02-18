package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import lombok.Data;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserService {
    @Autowired UserRepository userRepository;
    private PasswordEncoder bCryptPasswordEncoder;

    public User registerUser (User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername (String username){
        return userRepository.findByUsername(username);
    }

}
