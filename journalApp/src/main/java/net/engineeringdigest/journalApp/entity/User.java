package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Data
@NoArgsConstructor
public class User {
    @Id private ObjectId id;
    @Indexed(unique = true)
    @NonNull private String userName;
    @NonNull private String password;
    @DBRef private List<Entry> entriesByTheUser = new ArrayList<>();
    private List<String> roles = new ArrayList<>();

    public static User build(String userName, String password, List<String> roles) {
        User u = new User();
        u.userName = userName;
        u.password = password;
        u.roles = roles;
        return u;
    }

}
