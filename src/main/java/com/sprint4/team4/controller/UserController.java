package com.sprint4.team4.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sprint4.team4.model.UserModel;
import com.sprint4.team4.service.UserService;
import com.sprint4.team4.utils.ApiResponse;
import com.sprint4.team4.utils.JwtUtil;
import com.sprint4.team4.utils.LoginResponse;


@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String greeting() {
		return "Server is running on port 8080";
	}
	
	 @GetMapping("/register/{id}")
	public ResponseEntity<ApiResponse<UserModel>> getRegisterUser(@PathVariable Long id){
		UserModel um = userService.getRegisterUser(id);
		ApiResponse<UserModel> response = new ApiResponse<>(
				HttpStatus.OK.value(),
				"Retrive User with id:"+id+" successfully",
				um
			);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserModel>> registerUser(@RequestBody UserModel user ) {
		UserModel um = userService.registerUser(user);
		ApiResponse<UserModel> response = new ApiResponse<>(
					HttpStatus.OK.value(),
					"User registered successfully",
					um
				);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/register/{id}")
	public ResponseEntity<ApiResponse<UserModel>> deleteRegisterUser(@PathVariable Long id){
		boolean deleted = userService.deleteRegisterUser(id);
		if (deleted) {
	        ApiResponse<UserModel> response = new ApiResponse<>(
	                HttpStatus.OK.value(),
	                "User deleted successfully with id: " + id
	        );
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        ApiResponse<UserModel> response = new ApiResponse<>(
	                HttpStatus.NOT_FOUND.value(),
	                "User not found with id: " + id
	        );
	        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> loginUser(@RequestBody UserModel user){
		String userName = userService.loginUser(user);
		String token = JwtUtil.generateToken(user.getUserId(), false);
		LoginResponse loginRes = new LoginResponse(user.getUserId(),userName,token,false);
		ApiResponse<LoginResponse> res = new ApiResponse<>(
				HttpStatus.OK.value(),
				"Login successful",
				loginRes
		);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
}
