package net.engineeringdigest.journalApp.Controllers;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Services.JournalEntryService;
import net.engineeringdigest.journalApp.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;



    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userindb=userService.findbyusername(username);
            userindb.setUserName(user.getUserName());
            userindb.setPassword(user.getPassword());
            userService.saveEntry(userindb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

   @DeleteMapping()
    public ResponseEntity<?> deleteByUsername(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
         String username=authentication.getName();
         userService.deleteByUsername(username);
         return new ResponseEntity<>(HttpStatus.OK);

    }


}
