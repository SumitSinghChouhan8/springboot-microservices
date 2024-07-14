package com.training.springsecuritybasicauth.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.training.springsecuritybasicauth.dao.Roles;
import com.training.springsecuritybasicauth.enums.Role;

public interface RoleRepository extends CrudRepository<Roles, Integer>{
	
	Optional<Roles> findByRole(Role role);

}
