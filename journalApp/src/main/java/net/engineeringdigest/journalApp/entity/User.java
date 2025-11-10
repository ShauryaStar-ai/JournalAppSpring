package net.engineeringdigest.journalApp.entity;

import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class User {
    @Id private ObjectId id;
    @Indexed(unique = true)
    @NonNull private String userName;
    @NonNull private String password;
    @DBRef private List<Entry> entriesByTheUser = new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public @NonNull String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public List<Entry> getEntriesByTheUser() {
        return entriesByTheUser;
    }

    public void setEntriesByTheUser(List<Entry> entriesByTheUser) {
        this.entriesByTheUser = entriesByTheUser;
    }
}
