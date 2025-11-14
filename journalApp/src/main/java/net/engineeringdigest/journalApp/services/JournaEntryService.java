package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.Entry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repos.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
public class JournaEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService; // Journal Entry Repo
    public void saveEntry(Entry entry, String  userName ){
        User byUserName = userService.findByUserName(userName);

        journalEntryRepo.save(entry);
        byUserName.getEntriesByTheUser().add(entry);
        userService.saveEntry(byUserName);
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
    public void deleteEntryByID(@PathVariable ObjectId  id, String userName){
        User user = userService.findByUserName(userName);
        user.getEntriesByTheUser().remove(findByID(id));
        journalEntryRepo.deleteById(id);
        userService.saveEntry(user);
    }


}
