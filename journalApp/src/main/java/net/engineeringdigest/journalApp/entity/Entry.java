package net.engineeringdigest.journalApp.entity;

import lombok.Getter;
import lombok.Setter;
import net.engineeringdigest.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;

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
    private Sentiment sentiment;

}
