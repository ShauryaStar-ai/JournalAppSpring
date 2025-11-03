package net.engineeringdigest.journalApp.repos;

import net.engineeringdigest.journalApp.entity.Entry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepo extends MongoRepository<Entry, ObjectId>{

}
