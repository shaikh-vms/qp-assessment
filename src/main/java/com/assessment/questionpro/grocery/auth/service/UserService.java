package com.assessment.questionpro.grocery.auth.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assessment.questionpro.grocery.auth.model.AppUser;
import com.assessment.questionpro.grocery.auth.model.UserRole;
import com.assessment.questionpro.grocery.auth.repository.RoleRepository;
import com.assessment.questionpro.grocery.auth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) {
		AppUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found"));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getRoles().stream()
						.map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(
								"ROLE_" + role.getName()))
						.collect(Collectors.toList()));
	}

	// Method to create a new user with roles
	public AppUser createUser(String username, String password, String roleName) {
		UserRole role = roleRepository.findByName(roleName);
		if (role == null) {
			throw new IllegalArgumentException("Role not found");
		}

		AppUser user = new AppUser();

		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password)); // Encode the password
		user.getRoles().add(role);

		return userRepository.save(user);
	}
}
