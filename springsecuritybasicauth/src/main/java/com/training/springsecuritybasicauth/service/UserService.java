package com.training.springsecuritybasicauth.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.training.springsecuritybasicauth.dao.User;
import com.training.springsecuritybasicauth.repo.UserRepository;

@Configuration
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userfromdb = repo.findByUsername(username)
		.orElseThrow(()->new UsernameNotFoundException("user not found with username: "+ username));
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		userfromdb.getRoles().forEach(role->{
			roles.add((GrantedAuthority)()->role.getRole().name());
		});
		
		return new org.springframework.security.core.userdetails.User(userfromdb.getUsername(), userfromdb.getPassword(), roles);
	}

	
}
