package com.hackacode1.controller;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackacode1.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@GetMapping("/user")
	public User getUser(@AuthenticationPrincipal UserDetails userDetails) {
		if(userDetails !=null) {
			String user = userDetails.getPassword();
			String rol = userDetails.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.findFirst()
					.orElse("Sin rol");
			return new User(user, rol);
		}
		return new User("Desconocido", "Sin rol");
	}
	
	
}
