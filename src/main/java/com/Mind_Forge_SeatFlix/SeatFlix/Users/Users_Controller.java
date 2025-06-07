package com.Mind_Forge_SeatFlix.SeatFlix.Users;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


                                                                        //Rest Controller that handles User's Request
@RestController
@RequestMapping(path = "api/v1/users")                                  // path to api

public class Users_Controller {
        private final Users_Service user_Service;                        // created an instance of Users_Service
         
        public Users_Controller(Users_Service user_Service) {            // constructor
                this.user_Service = user_Service; 
        }

        @GetMapping                                                     // GET MAPPING that handles GET Request to the api path
        public List<Users> getUsers() {                                 // Method retrieves list of Users from the userService and returns it
                return user_Service.getUsers();

        }

       @PostMapping                                                     //POST MAPPING that handles POST Request to the api path
        public void Register(@RequestBody Users users) {                //Method takes userinput in the form of an object and uses UserService method to add new user
                 user_Service.addNewUsers(users);
       
    }
        @DeleteMapping(path = "{id}")
        public void deleteUser(@PathVariable("id") Long id) {
        user_Service.deleteUser(id);
    }

         @PutMapping(path = "{id}")
        public void updateUser(
        @PathVariable("id") Long id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email)
    {
        user_Service.updateService(id, name, email);
    }

   @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    Users user = ((Users_Repository) user_Service).findUserByUsername(loginRequest.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        return ResponseEntity.ok("Login successful!");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}



}
