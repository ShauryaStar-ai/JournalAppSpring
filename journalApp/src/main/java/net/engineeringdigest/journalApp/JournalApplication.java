package net.engineeringdigest.journalApp;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import net.engineeringdigest.journalApp.entity.Entry;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = "net.engineeringdigest.journalApp")
public class JournalApplication {

    public static void main(String[] args) {
       SpringApplication.run(JournalApplication.class, args);



    }
    @Bean
    public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);


    }
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
