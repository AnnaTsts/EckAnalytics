package com.eck_analytics.Controller;

import com.eck_analytics.Model.Person;
import com.eck_analytics.Services.PersonService;
import com.eck_analytics.dto.request.EditProfile;
import com.eck_analytics.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:4200")
public class UserController{

    @Autowired
    private PersonService personService;

    @PostMapping("/edit")
    public ResponseEntity<?> changeUserDetails(@RequestBody EditProfile editProfile){
        Person person = new Person(editProfile.getAge(),editProfile.getWeight(),
                editProfile.getHeight(),editProfile.getSex());
        personService.savePerson(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserSummary(){
        Object potentialPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (potentialPrincipal instanceof UserPrincipal) {
            return ResponseEntity.ok(personService.getUserSummary((((UserPrincipal) potentialPrincipal).getId())));
        }

        return ResponseEntity.ok().build();
    }
}
