package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.EmailRequest;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.EmailService;
import net.engineeringdigest.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")  // Expose this method as an HTTP GET endpoint
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsersList = userService.returnALLEntries();

        if (allUsersList != null && !allUsersList.isEmpty()) {
            // 200 OK with list of users in response body
            return ResponseEntity.ok(allUsersList);
        } else {
            // 404 Not Found with a message in response body
            return ResponseEntity.status(404).body("Could not return any users");
        }
    }
    @GetMapping("/getUserToSentimentAnalysis")
    public ResponseEntity<List<User>> gettSAUS() {
        List<User> users = userService.getUsersForSentimentAnalysis();

        if (users != null && !users.isEmpty()) {
            // Return the list of users with HTTP 200 OK
            return ResponseEntity.ok(users);
        } else {
            // Return HTTP 404 Not Found if no users exist
            return ResponseEntity.notFound().build();
        }
    }
    //Post mapping
    @PostMapping("/create-new-admin")
    public ResponseEntity<String> createAdmin(@RequestBody User user){

        if(userService.findByUserName(user.getUserName()) != null){
            log.debug("Entry already exists.Killing all bugs");
            log.trace("there can only be one admin with that userName");
            log.error("Entry already exists for {}",user.getUserName());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        else{
        userService.saveNewAdmin(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Added the new Admin");
        }
    }
    @Autowired
    EmailService emailService;
    @PostMapping("/sendEmailForSA")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){
        if (request.getTo() != null && request.getSubject() != null && request.getBody() != null) {
            emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
            return ResponseEntity.ok("Email sent successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Missing required email fields");
        }

    }
}
