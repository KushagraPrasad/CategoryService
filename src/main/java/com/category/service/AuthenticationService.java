package com.category.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.category.entity.Role;
import com.category.entity.RoleEnum;
import com.category.entity.User;
import com.category.repository.RoleRepository;
import com.category.repository.UserRepository;
import com.example.user.dto.LoginUserDto;
import com.example.user.dto.RegisterUserDto;

@Service
public class AuthenticationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	private final RoleRepository roleRepository;

	public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public User signup(RegisterUserDto input) {
		Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);

		return optionalRole.map((Role role) -> new User().setName(input.getName()).setEmail(input.getEmail())
				.setPassword(passwordEncoder.encode(input.getPassword())).setRole(role).setMobile(input.getMobile()))
				.map((User user) -> userRepository.save(user)).orElse(null);
	}

	public User authenticate(LoginUserDto input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
		return userRepository.findByEmail(input.getEmail())
				.orElseThrow(() -> new NoSuchElementException("User not found"));
	}
}