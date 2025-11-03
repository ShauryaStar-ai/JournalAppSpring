package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Entry;
import net.engineeringdigest.journalApp.services.JournaEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/journal") // put this end point on this whole class
public class JavaEntryController {

    @Autowired
    JournaEntryService  journaEntryService;
    @GetMapping
    public ResponseEntity<List<Entry>> getAll(){
        try {
            List<Entry> entries = journaEntryService.returnALLEntries();

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

    @PostMapping
    public String createEntry(@RequestBody Entry entry) {
        try{
            journaEntryService.saveEntry(entry);
            return ResponseEntity.status(HttpStatus.CREATED).toString();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).toString();
        }

    }


    @GetMapping("id/{myId}")
    public Object getEntryByID(@PathVariable (name = "myId")String myId){
        ObjectId id = new ObjectId(myId);
        Entry entry = journaEntryService.findByID(id);
        if (entry != null) {
            // Return 200 OK with the entry
            return ResponseEntity.ok(entry);
        } else {
            // Return 404 Not Found if entry doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<String> removeEntryByID(@PathVariable("myId") String myId) {
        try {
            ObjectId id = new ObjectId(myId);
            journaEntryService.deleteEntryByID(id);

            // Return a confirmation message with HTTP 200 OK
            return ResponseEntity.ok("Entry with ID " + myId + " deleted successfully!");
        } catch (Exception e) {
            // Return an error message with HTTP 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete entry: " + e.getMessage());
        }
    }


    @PutMapping("id/{myId}")
    public ResponseEntity<String> updateByID(@PathVariable(name = "myId") String myId , @RequestBody Entry entry ){
        try{
        ObjectId id = new ObjectId(myId);
            Entry old = journaEntryService.findByID(id);
            old.setTitle(entry.getTitle());
            old.setConent(entry.getConent());
            journaEntryService.saveEntry(old);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Update the Entry");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update entry: " + e.getMessage());
        }
        }


}
