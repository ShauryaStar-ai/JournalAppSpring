package net.engineeringdigest.journalAppRepo.repos;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import  org.springframework.data.mongodb.core.query.Query;
import java.util.List;
@Component
public class UserRepoImpl {
    @Autowired
    MongoTemplate mongoTemplate;


    public List<User> getUsersForSentimentAnalysis(){


        Query query= new Query();
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        //query.addCriteria(Criteria.where("email").exists(true).ne(null));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

}
