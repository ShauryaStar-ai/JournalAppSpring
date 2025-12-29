package net.engineeringdigest.journalApp.services;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysis {
    public String getSentiment(String text){
        return "postive and upbeat very happy";
    }
}
