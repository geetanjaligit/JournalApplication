package com.anshu.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.anshu.journalApp.entity.JournalEntry;
import com.anshu.journalApp.repository.JournalEntryRepository;

@Component

public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;
     
    public void saveEntry(JournalEntry entry) {
        repository.save(entry);
    }

    public List<JournalEntry> getAllEntries() {
        return repository.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return repository.findById(id);
    }

    public void deleteEntryById(ObjectId id) {
        repository.deleteById(id);
    }

}
