package net.engineeringdigest.journalApp.Controllers;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    UserService userService;


    @GetMapping("/getall-users")
    public ResponseEntity<?> getAllusers(){

       List<User> users= userService.getall();

       return new ResponseEntity<>(users,HttpStatus.OK);

    }
    @PostMapping("/add-admin")
    public ResponseEntity<?> addNewAdmin(@RequestBody User user){
        userService.saveadmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
