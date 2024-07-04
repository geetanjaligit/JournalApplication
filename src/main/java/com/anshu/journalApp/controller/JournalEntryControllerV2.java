package com.anshu.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshu.journalApp.entity.JournalEntry;
import com.anshu.journalApp.service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.ArrayList; // Import the ArrayList class
import java.util.HashMap; // Import the HashMap class
import java.util.List; // Import the List class
import java.util.Optional;

@RestController // Rest Controller handles HTTP requests and return JSON response(generally)
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService service;

//ResponseEntity<?>:- This indicates that the return type can be any type of object.it denotes a wildcard.
    @GetMapping
    public ResponseEntity<?> getAll() {
           
         List<JournalEntry> all=service.getAllEntries();
         if(all!=null && !all.isEmpty()){
             return new ResponseEntity<>(all,HttpStatus.OK);
         }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
   
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
    try{
        entry.setDate(LocalDateTime.now());
        service.saveEntry(entry);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }
    catch(Exception e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    }

    
    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myid) {
         Optional<JournalEntry> journalEntry= service.getEntryById(myid);
         if(journalEntry.isPresent()){
             return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}")
    public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable ObjectId myid) {
        service.deleteEntryById(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId myid, @RequestBody JournalEntry entry) {
        
        JournalEntry oldEntry = service.getEntryById(myid).orElse(entry);
        if(oldEntry!=null){
           oldEntry.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")? entry.getTitle(): oldEntry.getTitle());
           oldEntry.setContent(entry.getContent()!=null && !entry.getContent().equals("")? entry.getContent(): oldEntry.getContent());
           service.saveEntry(oldEntry);
           return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        
    }
}

