package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Entry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repos.UserRepo;
import net.engineeringdigest.journalApp.services.UserService;
import org.bson.types.ObjectId;
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
                userService.saveEntry(userInDB);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Update the Entry");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                    .body("Failed to update entry: " + e.getMessage());
        }
        return null;
    }

}





