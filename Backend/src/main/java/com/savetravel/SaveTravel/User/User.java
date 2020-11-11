package com.savetravel.SaveTravel.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
	    private String name;
	    private String email;
		
		public User() {	}

		public User(String name, String email) {
//			this.id = id;
			this.name = name;
			this.email = email;
		}
		
		
	      
	    
}
