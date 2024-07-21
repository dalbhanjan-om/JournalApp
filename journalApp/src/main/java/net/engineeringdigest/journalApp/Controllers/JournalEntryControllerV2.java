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

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;




    @GetMapping()
    public ResponseEntity<?> getAllEntriesOfUser(){
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            User user=userService.findbyusername(userName);

            List<JournalEntity> all= user.getJournalEntities();
            if (all!=null && ! all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping()
    public ResponseEntity<?> addEntry(@RequestBody JournalEntity journalEntity){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        try {
            journalEntryService.saveEntry(journalEntity,userName);
            return new ResponseEntity<>(journalEntity,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/id/{id_no}")
    public ResponseEntity<JournalEntity> getJournalbyid(@PathVariable String id_no){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user = userService.findbyusername(userName);
       List<JournalEntity> listOfJournalEntries= user.getJournalEntities().stream().filter(x-> x.getId().equals(id_no)).collect(Collectors.toList());
        if(!listOfJournalEntries.isEmpty()) {
            Optional<JournalEntity> JE = journalEntryService.findbyid(id_no);
            if (JE.isPresent()) {
                return new ResponseEntity<>(JE.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalByid(@PathVariable String id,@RequestBody JournalEntity newjournalEntity){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findbyusername(userName);
        List<JournalEntity> Journallist= user.getJournalEntities().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!Journallist.isEmpty()){

            JournalEntity old=journalEntryService.findbyid(id).orElse(null);
            if(old!=null){
                old.setTitle(newjournalEntity.getTitle()!=null && !newjournalEntity.getTitle().equals("")? newjournalEntity.getTitle(): old.getTitle());
                old.setContent(newjournalEntity.getContent()!=null && !newjournalEntity.equals("") ? newjournalEntity.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK) ;
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);



    }
    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteByid(@PathVariable String myid){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        boolean delete=journalEntryService.DeleteByid(myid,userName);
        if(delete){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

}
