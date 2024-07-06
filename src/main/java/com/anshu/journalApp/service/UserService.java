package com.anshu.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.anshu.journalApp.entity.User;
import com.anshu.journalApp.repository.UserRepository;

@Component

public class UserService {

    @Autowired
    private UserRepository userRepository;
     
    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public List<User> getAllEntries() {
        return userRepository.findAll();
    }

    public Optional<User> getEntryById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id) {
        userRepository.deleteById(id);
    }

}
