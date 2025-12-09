package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PublicController {

    @Autowired
    private UserService userService;
    @PostMapping("/public_user")

    private ResponseEntity<String> addUser(@RequestBody User user) {
        if(userService.findByUserName(user.getUserName()) != null){
            log.debug("Entry already exists.Killing all bugs");
            log.trace("this is trace over here ");
            log.error("Entry already exists for {}",user.getUserName());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        else {
            userService.saveNewEntry(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
        }

    }
}
