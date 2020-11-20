package com.savetravel.SaveTravel;

import static org.junit.Assert.assertNotNull;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.savetravel.SaveTravel.Models.ERole;
import com.savetravel.SaveTravel.Models.Role;
import com.savetravel.SaveTravel.Models.User;
import com.savetravel.SaveTravel.Repositories.RoleRepository;

public class ControllerTests {

		
	@Autowired
	RoleRepository roleRepository;
	
	
	public void simpleConstructorTest() {
		
		User testUser = new User("testUser", "test@test.de", "testpassword");
		
		assertNotNull(testUser);
		
		
	}
	
	@Test
	public void assertJPARepositoryNotNull() {
		
		Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
		
		assertNotNull(userRole.get().getName());
	}
	
	
	
	
}
