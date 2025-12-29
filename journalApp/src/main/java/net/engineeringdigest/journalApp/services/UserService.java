package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repos.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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
    public void updateUserAndThenSaving (User user){
        // Encode the password before saving
        String rawPassword = user.getPassword(); // get the plain password from user object
        String encodedPassword = p.encode(rawPassword); // encode it with BCrypt
        user.setPassword(encodedPassword); // set the encoded password back to the user

        // Set roles

        // Save to repository
        userRepo.save(user);
    }
    // this meathod works fine and woulld just be the expermeint thing for logger
    public void saveNewAdmin(User user){
        try{
        String rawPassword = user.getPassword(); // get the plain password from user object
        String encodedPassword = p.encode(rawPassword); // encode it with BCrypt
        user.setPassword(encodedPassword); // set the encoded password back to the user

        // Set roles
        user.setRoles(Arrays.asList("USER","ADMIN"));

        // Save to repository
        userRepo.save(user);} catch (Exception e) {
            logger.error("Failed to save new admin user '{}': {}",
                    user.getUserName(),
                    e.getMessage(),
                    e);
            throw e; // or throw a custom exception
        }
    }
    public List<User> returnALLEntries(){
        getUsersForSentimentAnalysis();
        return userRepo.findAll();
    }
    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getUsersForSentimentAnalysis(){


        Query query= new Query();
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        query.addCriteria(Criteria.where("email").exists(true).ne(null));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

    public void deleteEntryByID(@PathVariable ObjectId  id){
        userRepo.deleteById(id);
    }
    public void updateEntryByID(@PathVariable ObjectId myId, @RequestBody User user){

    }
    public User findByUserName(String userName){return userRepo.findByUserName(userName);}


    }

