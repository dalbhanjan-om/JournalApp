package net.engineeringdigest.journalApp.Services;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public void saveEntry(User user){

        userRepository.save(user);
    }
    public void savewithBcryptpassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));

        userRepository.save(user);
    }

    public List<User> getall(){
        return userRepository.findAll();
    }

    public Optional<User> findbyid(String id){
        return userRepository.findById(id);
    }

    public void DeleteByid(String id){
        userRepository.deleteById(id);
    }

    public void deleteall(){
        userRepository.deleteAll();
    }

    public void deleteByUsername(String username){
        userRepository.deleteByUserName(username);
    }

    public User findbyusername(String username){
        return userRepository.findByUserName(username);

    }
}
