package com.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.category.entity.User;
import com.example.user.dto.RegisterUserDto;

@Service
public interface UserServices extends UserDetailsService {

	public User addUser(User user);

	@Transactional
	public boolean removeUserByMobile(String mobile);

	@Transactional
	public boolean removeUserByEmail(String email);

	public List<User> showAll();

	public User createAdministrator(RegisterUserDto input);

	public List<User> getUserM(String mobile);

	public Optional<User> getUserE(String email);

}
