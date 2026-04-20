package com.turalabdullayev.job_search_api.controller;

import java.util.Collections;
import java.util.HashSet;

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

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public RegistrationController(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/reg-user")
	public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
		if (userRepository.findByUsername(authRequest.getUsername()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bu istifadəçi adı artıq mövcuddur");
		}

		User user = new User();
		user.setUsername(authRequest.getUsername());
		user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

		// DÜZƏLİŞ: Yeni user-ə yalnız ROLE_USER veririk (Bazadan tapırıq)
		Role defaultRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
			Role role = new Role();
			role.setName("ROLE_USER");
			return roleRepository.save(role);
		});

		user.setRoles(new HashSet<>(Collections.singletonList(defaultRole)));

		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Qeydiyyat uğurla tamamlandı");
	}
}