package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.repos.JournalEntryRepo;
import net.engineeringdigest.journalApp.repos.UserRepo;
import org.assertj.core.util.Strings;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class JournalEntryTest {
    @Autowired
    UserRepo u;
    @ParameterizedTest
    @ValueSource(strings = {
        "bob",
        "shaurya",
        "don",
        "febifewbij"
    })
    public void findUser(String name){
        assertNotNull(u.findByUserName(name), "User should exist in the database");

    }
    @Test
    public void testAdd(){
        assertEquals(5,2+3,"The sum in not equal");

    }

}
