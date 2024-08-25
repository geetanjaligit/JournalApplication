package com.anshu.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anshu.journalApp.entity.JournalEntry;
import com.anshu.journalApp.entity.User;
import com.anshu.journalApp.repository.JournalEntryRepository;

@Service

public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;

    @Autowired
    private UserService userService;
     
    // @Transactional
    public void saveEntry(JournalEntry entry ,String username) {
    try{
        User user=userService.findByUsername(username);
        entry.setDate(LocalDateTime.now());
        JournalEntry saved=repository.save(entry);
        user.getJournalEntries().add(saved);
        user.setUsername(username);
        userService.saveEntry(user);
    } catch(Exception e){
        System.out.println(e);
        throw new RuntimeException("Error while saving entry",e);
    }
    }

    public void saveEntry(JournalEntry entry) {
        entry.setDate(LocalDateTime.now());
        repository.save(entry);
    }
    

    public List<JournalEntry> getAllEntries() {
        return repository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return repository.findById(id);
    }

    public void deleteEntryById(ObjectId id,String username) {
        User user=userService.findByUsername(username); 
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user);
        repository.deleteById(id);
    }

} 
 