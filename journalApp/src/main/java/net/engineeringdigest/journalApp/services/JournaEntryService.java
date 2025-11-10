package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.Entry;
import net.engineeringdigest.journalApp.repos.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class JournaEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo; // Journal Entry Repo
    public void saveEntry(Entry entry){
        journalEntryRepo.save(entry);
    }
        public List<Entry> returnALLEntries(){
            return journalEntryRepo.findAll();
        }


        public  Entry findByID(@PathVariable  ObjectId  myId){

        return journalEntryRepo.findById(myId).orElse(null);
    }
    public void deleteEntryByID(@PathVariable ObjectId  id){
        journalEntryRepo.deleteById(id);
    }


}
