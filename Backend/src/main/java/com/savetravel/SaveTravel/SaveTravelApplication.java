package com.savetravel.SaveTravel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.savetravel.SaveTravel.Repositories.UserRepository;
import com.savetravel.SaveTravel.User.User;




@SpringBootApplication
public class SaveTravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaveTravelApplication.class, args);
	}

	
    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                User user = new User(name, name.toLowerCase() + "@domain.com");
                userRepository.save(user);
            });
           
            
            List<User> userList = new ArrayList<User>();
            
            userList = (List<User>) userRepository.findAll();
            
            for (User user : userList) {
            	System.out.println(user.getName() + " id: " + user.getId());
				
			}
            
        };
    }
	
	
}
