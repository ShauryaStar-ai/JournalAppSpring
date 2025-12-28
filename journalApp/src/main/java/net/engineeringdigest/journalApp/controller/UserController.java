package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.UserService;
import net.engineeringdigest.journalApp.services.Weather.WeatherResponse;
import net.engineeringdigest.journalApp.services.Weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    WeatherService weatherService;
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        try {
            List<User> entries = userService.returnALLEntries();

            if (entries.isEmpty()) {
                // No entries found → return 204 No Content
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            // Entries found → return 200 OK + data
            return ResponseEntity.ok(entries);

        } catch (Exception e) {
            // Something went wrong → return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping("/greeting")
    public ResponseEntity<String> helloToUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weather = weatherService.getWeather("Atlanta"); // Assuming this returns a String

        if (authentication != null && weather != null && authentication.isAuthenticated()) {
            String userName = authentication.getName();
            String response = "Hi " + userName + ", the temp in your city is " + weather.getCurrent().getTemperature()+" degree celcuis";

            // Return 200 OK with the response body
            return ResponseEntity.ok(response);
        } else {
            // Return 401 Unauthorized with a custom message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("The user is not even in the db");
        }
    }
    @DeleteMapping
    public ResponseEntity<String> deleteUser(){
         try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
            }
            String userName = authentication.getName();

            // Step 2: Find user in DB
            User user = userService.findByUserName(userName);

            if (user != null) {
                // Step 3: Delete user
                userService.deleteEntryByID(user.getId());
                return ResponseEntity.ok("Deleted the entry");
            } else {
                // Step 4: User not found in DB
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("The user you are trying to delete is no longer there");
            }
        }
        catch (Exception e) {
            // Step 5: Any unexpected runtime exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not retrieve the user: " + e.getMessage());
        }


    }
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        try {
            User userInDB = userService.findByUserName(userName);
            if (userInDB != null) {
                if (user.getUserName() != null) {
                    userInDB.setUserName(user.getUserName());
                }
                if (user.getPassword() != null) {
                    userInDB.setPassword(user.getPassword());
                }
                userService.updateUserAndThenSaving(userInDB);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Update the Entry");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                    .body("Failed to update entry: " + e.getMessage());
        }
        return null;
    }

}





