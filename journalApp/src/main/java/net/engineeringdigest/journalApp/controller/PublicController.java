package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PublicController {
    @Autowired
    private UserService userService;
    @PostMapping("/public_user")

    private ResponseEntity<String> addUser(@RequestBody User user) {
        if(userService.findByUserName(user.getUserName()) != null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        else {
            userService.saveNewEntry(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
        }

    }
}
