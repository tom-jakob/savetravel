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





@SpringBootApplication
public class SaveTravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaveTravelApplication.class, args);
	}

	
	
}
