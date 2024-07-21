package net.engineeringdigest.journalApp.Controllers;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<String, JournalEntity> journalEntityMap=new HashMap<>();


    @GetMapping("/get")
    public List<JournalEntity> getall(){
        return new ArrayList<>(journalEntityMap.values());
    }

    @PostMapping
    public String addEntry(@RequestBody JournalEntity journalEntity){
        journalEntityMap.put(journalEntity.getId(), journalEntity);
        return "Entry added successfulyy";
    }

    @GetMapping("/id/{id_no}")
    public JournalEntity getbyid(@PathVariable Long id_no){
        return journalEntityMap.get(id_no);

    }

    @PutMapping("/id/{id}")
    public JournalEntity update(@PathVariable String id,@RequestBody JournalEntity journalEntity){
        return journalEntityMap.put(id,journalEntity);

    }
    @DeleteMapping("/id/{myid}")
    public JournalEntity delete(@PathVariable String myid){
        return journalEntityMap.remove(myid);

    }
}
