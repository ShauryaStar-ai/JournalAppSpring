package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Entry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal") // put this end point on this whole class
public class JavaEntryController {
    private Map<Integer, Entry> collectionOfJournalEntries = new HashMap<>();
    @GetMapping
    public List<Entry> getAll(){
        return new ArrayList<>(collectionOfJournalEntries.values());
    }
    @PostMapping
    public String createEntry(@RequestBody Entry entry) {
        collectionOfJournalEntries.put(entry.getId(), entry);
        return "Entry Added Sucessfully";
    }
    // why is link not /id
    @GetMapping("id/{myId}")
    public Entry getEntryByID(@PathVariable int myId){ //can the spelling here be different because the correct spelling I was meant to have was id
        return collectionOfJournalEntries.get(myId);
    }
}
