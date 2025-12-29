package net.engineeringdigest.journalApp.cashe;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repos.ConfigJournalAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCahse {
    @Autowired
    ConfigJournalAppRepo configJournalAppRepo;
    public Map<String,String> app_cahse=  new HashMap<>();

    @PostConstruct
    public void init(){
        List<ConfigJournalAppEntity> configJournalAppRepoAll = configJournalAppRepo.findAll();
        for(ConfigJournalAppEntity ConfigJournalAppEntity : configJournalAppRepoAll){
            app_cahse.put(ConfigJournalAppEntity.getKey(), ConfigJournalAppEntity.getValue());
        }
    }
}
