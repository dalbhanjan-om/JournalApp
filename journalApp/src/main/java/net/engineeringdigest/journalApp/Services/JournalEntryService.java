package net.engineeringdigest.journalApp.Services;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    JournalEntryRepository journalEntryRepository;

    @Autowired
    UserService userService;

    @Transactional
    public void saveEntry(JournalEntity journalEntity,String userName){
        User user=userService.findbyusername(userName);
        JournalEntity userJournalEntries = journalEntryRepository.save(journalEntity);
        user.getJournalEntities().add(userJournalEntries);
        userService.saveEntry(user);
    }
    public void saveEntry(JournalEntity journalEntity){
        journalEntryRepository.save(
                journalEntity
        );
    }

    public List<JournalEntity> getall(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findbyid(String id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean DeleteByid(String id,String userName){
        boolean remove=false;
        try {
            User user=userService.findbyusername(userName);
            boolean removed=user.getJournalEntities().removeIf(x->x.getId().equals(id));
            if(removed){
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
                return remove=true;
            }

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("id and username does not match"+e);
        }
        return remove;

    }


}
