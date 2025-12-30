package net.engineeringdigest.journalApp.Scheduler;

import net.engineeringdigest.journalApp.entity.Entry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.services.EmailService;
import net.engineeringdigest.journalApp.services.SentimentAnalysis;
import net.engineeringdigest.journalApp.repos.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class emailScheduler {
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
            List<String> listOfEntriesOfTheWeek = entriesByTheUser.stream().filter(x -> x.getDateTime().isAfter(LocalDateTime.now().minusDays(7))).map(x-> x.getConent()).toList();
            String combinedEntry = String.join("",listOfEntriesOfTheWeek);
            String sentiment = sentimentAnalysis.getSentiment(combinedEntry);
            emailService.sendEmail(user.getEmail(),"JournalEntriesReport",sentiment);
        }

    }
}
