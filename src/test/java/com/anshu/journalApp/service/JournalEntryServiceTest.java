package com.anshu.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.anshu.journalApp.entity.JournalEntry;
import com.anshu.journalApp.entity.User;
import com.anshu.journalApp.repository.JournalEntryRepository;
import com.anshu.journalApp.repository.UserRepository;

//testing saveEntry method of JournalEntryService class using mockito

public class JournalEntryServiceTest {


    @InjectMocks
    private JournalEntryService journalEntryService;

    @Mock
    private JournalEntryRepository journalEntryRepository; // Mocked dependency

    @Mock
    private UserRepository userRepository; 

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }
    @Test
    public void testSaveEntry(){

        JournalEntry entry= new JournalEntry();
        entry.setId(new ObjectId());
        entry.setTitle("Title");
        entry.setContent("Content");

        User user= new User();
        user.setUsername("Ram");
        user.setPassword("password");

        when(userService.findByUsername(ArgumentMatchers.anyString())).thenReturn(user); 
        when(journalEntryRepository.save(entry)).thenReturn(entry);

        journalEntryService.saveEntry(entry, "Ram");

        verify(journalEntryRepository, times(1)).save(entry);
        verify(userService, times(1)).saveEntry(user); 

        assertEquals("Title", entry.getTitle());
        

    }

}
