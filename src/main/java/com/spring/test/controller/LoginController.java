package com.spring.test.controller;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.test.dao.UserRepository;
import com.spring.test.domain.User;

@Controller
public class LoginController {
	
	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;

	public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/token-login/{authToken}")
	public String hello(@PathVariable String authToken, HttpServletRequest req) throws Exception {

		System.out.println("Performing token login: " + authToken);
		Optional<User> optionalUser = userRepository.findByAuthToken(authToken);
		
		if(!optionalUser.isPresent()) {
			System.out.println("Auth token not found");
			return "redirect:/";
		}	
		System.out.println("Auth token has been found");

		User user = optionalUser.get();
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		Authentication authentication = authenticationManager.authenticate(authRequest);
		
		SecurityContext sc = SecurityContextHolder.getContext();
	    sc.setAuthentication(authentication);
	    
	    HttpSession session = req.getSession(true);
	    session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
		
	    return "redirect:/customers";

	}
}
