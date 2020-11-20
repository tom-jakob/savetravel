package com.savetravel.SaveTravel.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savetravel.SaveTravel.Models.ERole;
import com.savetravel.SaveTravel.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
	
	public Optional<Role> findByName(ERole name);

	
	
}
