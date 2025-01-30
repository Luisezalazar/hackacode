package com.hackacode1.security;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hackacode1.model.Personal;

public class UserPrincipal implements UserDetails{

    @Serial
    private static final long serialVersionUID = 1L;
	private Personal personal;
	
	public UserPrincipal(Personal personal)
	{
		this.personal= personal;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String rol = personal.getRol();
		return Collections.singleton(new SimpleGrantedAuthority(rol));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return personal.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return personal.getUsername();
	}

}
