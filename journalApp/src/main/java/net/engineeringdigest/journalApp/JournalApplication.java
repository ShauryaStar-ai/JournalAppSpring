package net.engineeringdigest.journalApp;

import net.engineeringdigest.journalApp.entity.Entry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);

        @RestController
        @RequestMapping("/journal") // endpoint for the whole class
        class JavaEntryController {
            private Map<Integer, Entry> collectionOfJournalEntries = new HashMap<>();

            @GetMapping
            public List<Entry> getAll() {
                return new ArrayList<>(collectionOfJournalEntries.values());
            }

            @PostMapping
            public void createEntry(@RequestBody Entry entry) {
                collectionOfJournalEntries.put(entry.getId(), entry);
            }
        }

    }
}
