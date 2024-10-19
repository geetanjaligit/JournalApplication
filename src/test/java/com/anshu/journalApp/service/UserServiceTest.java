package com.anshu.journalApp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.anshu.journalApp.entity.User;
import com.anshu.journalApp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;

//use of JUnit framework for testing

@SpringBootTest//this annontation signify that start the application context as running the code so that it can inject dependency
public class UserServiceTest {

    //it will consist all the test cases for the UserService class

    @Autowired  
    private UserRepository userRepository;

    @Disabled// disabled this test for some time bcz i have to run other tests.
    @Test
    public void testFindByUsername()
    {
        // assertEquals(4, 2+2);
        assertNotNull(userRepository.findByUsername("Ram"));
        User user=userRepository.findByUsername("Ram");
        assertFalse(user.getJournalEntries().isEmpty());
        // assertTrue(5>3);
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "2,10,12",
        "3,3,9"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
