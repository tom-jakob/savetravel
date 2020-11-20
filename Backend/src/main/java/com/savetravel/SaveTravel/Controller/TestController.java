package com.savetravel.SaveTravel.Controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savetravel.SaveTravel.Models.ERole;
import com.savetravel.SaveTravel.Models.Role;
import com.savetravel.SaveTravel.Models.User;
import com.savetravel.SaveTravel.Repositories.UserRepository;


@EnableAutoConfiguration
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	UserRepository userRepositoy;
	
	@PreAuthorize("permitAll()")
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess(@RequestBody User user) {
		
		String userName = user.getUsername();
		Optional<User> userFromDB = userRepositoy.findByUsername(userName);
		Set<Role> roles = userFromDB.get().getRoles();
		
		for (Role role : roles) {
			String roleType = role.getName().name();
			if (roleType == "ROLE_USER") {
				return "User Content.";
				
			} else return "Unauthorized";
			
		}
		
		return null;
	}
	
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	
}
