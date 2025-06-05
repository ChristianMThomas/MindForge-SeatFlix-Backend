package com.Mind_Forge_SeatFlix.SeatFlix.Users;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


            // Contoller used for Handling Request 

    @RestController
    @RequestMapping (path = "api/v1/users")

            // path to api ^

public class Users_Controller {


		private final Users_Service userService;
        
                // constructor 

        public Users_Controller(Users_Service userService){
        this.userService = userService;
        }

        @GetMapping
    	public List <Users> getUsers(){
        return Users_Service.getUsers();

	}
    

   

   

    


}
