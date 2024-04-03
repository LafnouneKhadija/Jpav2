package ma.emsi.jpav2.web;

import ma.emsi.jpav2.Service.UserService;
import ma.emsi.jpav2.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userservice;
    @GetMapping("/users/{username}")
    //pour consulter users
    public User user(@PathVariable String username){
       User user=userservice.findUserByUserName(username);
   return user;
    }
}
