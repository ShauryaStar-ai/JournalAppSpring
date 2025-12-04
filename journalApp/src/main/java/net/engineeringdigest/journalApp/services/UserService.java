package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repos.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo; // Journal Entry Repo
    public void saveEntry(User user){

        userRepo.save(user);
    }
    private static PasswordEncoder p  = new BCryptPasswordEncoder();

    public void saveNewEntry(User user){
        // Encode the password before saving
        String rawPassword = user.getPassword(); // get the plain password from user object
        String encodedPassword = p.encode(rawPassword); // encode it with BCrypt
        user.setPassword(encodedPassword); // set the encoded password back to the user

        // Set roles
        user.setRoles(Arrays.asList("USER"));

        // Save to repository
        userRepo.save(user);
    }
    public void saveNewAdmin(User user){
        String rawPassword = user.getPassword(); // get the plain password from user object
        String encodedPassword = p.encode(rawPassword); // encode it with BCrypt
        user.setPassword(encodedPassword); // set the encoded password back to the user

        // Set roles
        user.setRoles(Arrays.asList("USER","ADMIN"));

        // Save to repository
        userRepo.save(user);
    }
    public List<User> returnALLEntries(){
            return userRepo.findAll();
        }

    public void deleteEntryByID(@PathVariable ObjectId  id){
        userRepo.deleteById(id);
    }
    public void updateEntryByID(@PathVariable ObjectId myId, @RequestBody User user){

    }
    public User findByUserName(String userName){return userRepo.findByUserName(userName);}


    }

