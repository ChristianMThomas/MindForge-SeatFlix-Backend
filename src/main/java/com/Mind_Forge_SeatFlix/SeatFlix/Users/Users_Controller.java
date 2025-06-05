package com.Mind_Forge_SeatFlix.SeatFlix.Users;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


// Contoller used for Handling Request 

    @RestController
    @RequestMapping (path = "api/v1/users")
public class Users_Controller {

   	@GetMapping
	public List <Users> hello(){
		return List.of(
			new Users(
				1L,
				"CThomas_876",
				"Password",
				"CThomas123@gmail.com"
			)
		);
	}
    

   

   

    


}
