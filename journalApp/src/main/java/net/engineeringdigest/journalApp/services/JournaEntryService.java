package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.Entry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repos.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
public class JournaEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService; // Journal Entry Repo
    @Transactional
    public void saveEntry(Entry entry, String  userName ){
        User byUserName = userService.findByUserName(userName);

        journalEntryRepo.save(entry);
        byUserName.getEntriesByTheUser().add(entry);
        userService.saveEntry(byUserName);
        System.out.println("All done !");
    }
    public void saveEntry(Entry entry){
        journalEntryRepo.save(entry);
    }
        public List<Entry> returnALLEntries(){
            return journalEntryRepo.findAll();
        }


        public  Entry findByID(@PathVariable  ObjectId  myId){

        return journalEntryRepo.findById(myId).orElse(null);
    }
    /*@Transactional   // As of now this version is being blocked for testing puposes
    public void deleteEntryByID(@PathVariable ObjectId  id, String userName){
        User user = userService.findByUserName(userName);
        // the bug over here was that the the user we find in the usercollection is not the
        // same in memoery as find by id
        boolean removed = user.getEntriesByTheUser().remove(findByID(id));
        if(removed){
            journalEntryRepo.deleteById(id);
            userService.saveEntry(user);
        }
        if (!removed) {
            throw new IllegalStateException("Entry does not belong to the user");
        }
    }*/
    @Transactional
    public void deleteEntryByID(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        // finding the instance of the entry and the later removring it

        Entry entry = user.getEntriesByTheUser()// this code makes sure that the entry belong to the user
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Entry does not belong to the user"));
        // removing the instance that for sure belong to the user
        boolean removed = user.getEntriesByTheUser().remove(entry);
        if(removed){ // removing from the journlal if only the entry belongs to the user.
        // further removal from the list of entries
        journalEntryRepo.deleteById(id);
        //Saving the updated used
        userService.saveEntry(user);
        }
    }


}
