package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repos.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo; // Journal Entry Repo
    public void saveEntry(User user){

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

