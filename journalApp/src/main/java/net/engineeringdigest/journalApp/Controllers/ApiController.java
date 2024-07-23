package net.engineeringdigest.journalApp.Controllers;

import net.engineeringdigest.journalApp.ApiResponse.WheatherResponsePojo;
import net.engineeringdigest.journalApp.Services.WheatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Wheather-Api")
public class ApiController {

    @Autowired
    WheatherService wheatherService;
    @GetMapping
    public ResponseEntity<?> wheather(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        WheatherResponsePojo responsePojo = wheatherService.getWheather("Pune");
        return new ResponseEntity<>("hi "+authentication.getName()+" The wheather today feels like "+responsePojo.getCurrent().getFeelslike_c(), HttpStatus.OK);

    }
}
