package com.savetravel.SaveTravel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.savetravel.SaveTravel.Repositories.UserRepository;
import com.savetravel.SaveTravel.Models.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	
	   	@Autowired 
	    public UserRepository userRepository;
	 
	    @GetMapping("/users")
	    public List<User> getUsers() {
	        return (List<User>) userRepository.findAll();
	    }
	 
	    @PostMapping("/users")
	    void addUser(@RequestBody User user) {
	        userRepository.save(user);
	    }
	}
	
	

