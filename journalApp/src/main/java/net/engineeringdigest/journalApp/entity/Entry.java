package net.engineeringdigest.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;
import net.engineeringdigest.journalApp.services.JournaEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection  = "jorunalEntires")
@Getter
@Setter

public class Entry {
    @Id private ObjectId id;
    private String title;
    private String conent;
    private LocalDateTime dateTime = LocalDateTime.now();


}
