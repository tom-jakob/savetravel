package com.savetravel.SaveTravel;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.savetravel.SaveTravel.Models.ERole;
import com.savetravel.SaveTravel.Models.Role;
import com.savetravel.SaveTravel.Models.User;
import com.savetravel.SaveTravel.Repositories.RoleRepository;

@SpringBootTest
class SaveTravelApplicationTests {

	@Autowired
	RoleRepository roleRepository;
	
	
	@Test
	void contextLoads() {
	}

	@Test
	public void simpleConstructorTestUser() {
		
		User testUser = new User("testUser", "test@test.de", "testpassword");
		
		assertNotNull(testUser.getUsername());
		
	}
	
	@Test
	public void assertJPARepositoryNotNull() {
		
		Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
		
		assertNotNull(userRole.get().getName());
	}
}
