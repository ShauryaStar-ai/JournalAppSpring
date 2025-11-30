package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/admin")
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
}
