package com.spring.test.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.test.dao.UserRepository;
import com.spring.test.domain.User;
import com.spring.test.dto.AuthenticateDto;

@RestController
@RequestMapping(path = "/api")
public class AuthController {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;

	public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}

	@GetMapping("/authenticate/{username}/{password}")
	public AuthenticateDto hello(@PathVariable String username, @PathVariable String password) throws Exception {

		System.out.println("Authenticating user:" + username);
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authRequest);
		if (!authentication.isAuthenticated()) {
			throw new Exception("Invalid credentials");
		}
		System.out.println("User authenticated: " + authentication.getName());
		Optional<User> userOptional = userRepository.findById(authentication.getName());
		User user = userOptional.get();
		
		String authToken = UUID.randomUUID().toString();
		user.setAuthToken(authToken);
		userRepository.save(user);
		
		AuthenticateDto response = new AuthenticateDto();
		response.setUsername(user.getUsername());
		response.setAuthToken(authToken);
		return response;
	}
	
	
}
