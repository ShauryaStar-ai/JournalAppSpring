package net.engineeringdigest.journalApp;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class Test {


    public class MongoExample {
        public static void main(String[] args) {
            // Get the URI from the environment variable
            String mongoUri = System.getenv("MONGO_URI");

            // Connect to MongoDB
            MongoClient mongoClient = MongoClients.create(mongoUri);

            System.out.println("Connected to MongoDB!");

            // Remember to close the client when done
            mongoClient.close();
        }
    }

}
