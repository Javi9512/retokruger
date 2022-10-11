package com.jmichay.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jmichay.inventario.entity.User;
import com.jmichay.inventario.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = userRepository.findByUsername(username);
		String rol = usuario.getRole().getName().toUpperCase();
		return org.springframework.security.core.userdetails.User.withUsername(username)
				.password(usuario.getPassword()).roles(rol).build();
	}

}
