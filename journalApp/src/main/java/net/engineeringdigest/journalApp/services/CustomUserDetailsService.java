package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalAppRepo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUserName = userRepo.findByUserName(username);
        //this piece of code help spring load up all of the infomratuon of the user stored in the db
        // in the way that I can understand and then later use.
        if(byUserName != null){
            UserDetails userDetials = org.springframework.security.core.userdetails.User.builder().
                    username(byUserName.getUserName()).
                    password(byUserName.getPassword())
                    .roles(byUserName.getRoles().toArray(new String[0]))
                    .build();
            return userDetials;
        }
        else {
            throw new RuntimeException("User not found");
        }
    }
}
