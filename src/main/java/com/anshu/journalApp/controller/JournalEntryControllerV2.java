package com.anshu.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService service;


    @GetMapping
    public List<JournalEntry> getAll() {
           
        return service.getAllEntries();
    }


    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry entry) {

        entry.setDate(LocalDateTime.now());
        service.saveEntry(entry);
        return entry;
    }

    
    @GetMapping("id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myid) {
        return service.getEntryById(myid).orElse(null);
    }

    @DeleteMapping("id/{myid}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myid) {
        service.deleteEntryById(myid);
        return true;
    }

    @PutMapping("id/{myid}")
    public JournalEntry updateEntry(@PathVariable ObjectId myid, @RequestBody JournalEntry entry) {
        
        JournalEntry oldEntry = service.getEntryById(myid).orElse(null);
        if(oldEntry!=null){
           oldEntry.setTitle(entry.getTitle()!=null && !entry.getTitle().equals("")? entry.getTitle(): oldEntry.getTitle());
           oldEntry.setContent(entry.getContent()!=null && !entry.getContent().equals("")? entry.getContent(): oldEntry.getContent());
        }
        service.saveEntry(oldEntry);
        return oldEntry;
    }
}

