package com.hackacode1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hackacode1.model.Personal;
import com.hackacode1.repository.IPersonalRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private IPersonalRepository perRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Personal personal = perRepo.findByUsername(username);
		if(personal==null) {
			throw new UsernameNotFoundException("Usuario no existente.");
		}
		return new UserPrincipal(personal);
		
	}
	
}
