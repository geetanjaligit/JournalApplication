package com.anshu.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.anshu.journalApp.entity.User;
import com.anshu.journalApp.repository.UserRepository;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public void saveEntry(User user) {
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    // public void saveNewUser(User user) {
    //     userRepository.save(user);
    // }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getEntryById(ObjectId Id) {
        return userRepository.findById(Id);
    }

    public void deleteEntryById(ObjectId username) {
        userRepository.deleteById(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
