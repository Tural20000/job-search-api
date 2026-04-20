package com.turalabdullayev.job_search_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turalabdullayev.job_search_api.dto.AuthRequest;
import com.turalabdullayev.job_search_api.model.Role;
import com.turalabdullayev.job_search_api.model.User;
import com.turalabdullayev.job_search_api.repository.RoleRepository;
import com.turalabdullayev.job_search_api.repository.UserRepository;

@RestController
public class RegistrationController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/reg-user")
	public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
		if (userRepository.findByUsername(authRequest.getUsername()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu istifadəçi adı artıq mövcuddur");
		}

		User user = new User();
		user.setUsername(authRequest.getUsername());
		user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

		java.util.Set<Role> roles = new java.util.HashSet<>(roleRepository.findAll());
		user.setRoles(roles);

		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Qeydiyyat uğurla tamamlandı");
	}
}
