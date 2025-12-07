package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repos.JournalEntryRepo;
import net.engineeringdigest.journalApp.repos.UserRepo;
import org.assertj.core.util.Strings;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
//@SpringBootTest


@ExtendWith(MockitoExtension.class)
public class JournalEntryTest {

    /*@Mock
    private UserRepo  userRepo;
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService; // build a real class that has the mock inside it
    @Test
    public void testloadUserByUsername(){

        //building a fake user
        User fakeUser = new User();
        fakeUser.setUserName("nick");
        fakeUser.setPassword("nick"); // or encoded password
        fakeUser.setRoles(Arrays.asList("USER", "ADMIN"));

        when(userRepo.findByUserName("nick")).thenReturn(fakeUser); // mock userRepo

        UserDetails ud = customUserDetailsService.loadUserByUsername("nick"); *//*
        checking the customUserDetailsService hoping it returns something.
        *//*

       assertNotNull(ud,"Could not find the detials of the user ");
    }*/
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @MockitoBean
    UserRepo userRepo;
    @Test
    void testloadUserByUsername(){
        when(userRepo.findAll(ArgumentMatchers.anyString())).thenReturn(User.build()("bob","fsfse",Arrays.asList("USER")))
    }
}
