package net.engineeringdigest.journalApp.Scheduler;

import net.engineeringdigest.journalApp.cashe.AppCahse;
import net.engineeringdigest.journalApp.entity.Entry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiment;
import net.engineeringdigest.journalApp.services.EmailService;
import net.engineeringdigest.journalApp.services.SentimentAnalysis;
import net.engineeringdigest.journalApp.repos.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.image.ImageProducer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class journalAppScheduler {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepoImpl userRepoImpl;
    @Autowired
    private SentimentAnalysis sentimentAnalysis;
    @Scheduled(cron = "0 29 8 ? * TUE")
    public void fetchUsersAndSendSA(){
        List<User> usersForSentimentAnalysis = userRepoImpl.getUsersForSentimentAnalysis();
        for (User user : usersForSentimentAnalysis) {
            List<Entry> entriesByTheUser = user.getEntriesByTheUser();
            List<Sentiment> sentiments = entriesByTheUser.stream().filter(x -> x.getDateTime().isAfter(LocalDateTime.now().minusDays(7))).map(x-> x.getSentiment()).toList();
        Map<Sentiment,Integer> sentimentCounts = new HashMap<>(); // stores the count of each sentiment
            Sentiment sentimentMostFrequent = null; // not too sure what this piece of code does

            for (Sentiment sentiment: sentiments){ // gets each individual sentiment from each user
            if(sentiment != null){
                sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+1);
            }
            // we now have all of the sentiments of the user and how many times each of them come in the list sentimentCounts
            // we would now have the max sentiemnt finding

            int maxCount = 0;
            // no idea from this point on
            for(Map.Entry<Sentiment,Integer> entry : sentimentCounts.entrySet()){
                if(entry.getValue() > maxCount){
                    sentimentMostFrequent = entry.getKey();
                    maxCount = entry.getValue();
                    sentimentMostFrequent = entry.getKey();
                }

            }


        }
            if (sentimentMostFrequent != null){
                emailService.sendEmail(user.getEmail(),"JournalEntriesReport","Your most common sentiment of the week was "+sentimentMostFrequent.toString());
            }

        }

    }
    @Autowired
    private AppCahse appCahse;
    @Scheduled(cron = "0 */5 * * * ?")
    public void refreshAppCahse(){
        appCahse.init();
    }
}
