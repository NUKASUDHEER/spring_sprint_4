package com.sprint4.team4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sprint4.team4.exception.PasswordMismatchException;
import com.sprint4.team4.model.UserModel;
import com.sprint4.team4.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public UserModel registerUser(UserModel user) {
		if (userRepository.existsByUserId(user.getUserId())) {
		    throw new DataIntegrityViolationException("UserId already exists");
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
	        throw new PasswordMismatchException("Password and Confirm Password do not match");
	    }
		return userRepository.save(user);
	}
	
	public UserModel getRegisterUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public boolean deleteRegisterUser(Long id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public String loginUser(UserModel user) {
		UserModel userDetails = userRepository.getByUserId(user.getUserId());
		if(userDetails == null) {
			throw new RuntimeException("Please register before login.");
		}
		if(!userDetails.getPassword().equals(user.getPassword())) {
			throw new RuntimeException("Invalid password.");
		}
		return userDetails.getName();
	}
}
