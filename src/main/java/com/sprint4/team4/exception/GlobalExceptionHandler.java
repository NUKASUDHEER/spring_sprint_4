package com.sprint4.team4.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sprint4.team4.utils.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException e){
		ApiResponse<String> res = new ApiResponse<>(
				HttpStatus.BAD_REQUEST.value(),
				e.getMessage()
		);
		return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
	    String message = "UserId already exists. Please try with another UserId.";
		ApiResponse<Object> response = new ApiResponse<>(
		        HttpStatus.CONFLICT.value(),
		        message
		);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	 @ExceptionHandler(PasswordMismatchException.class)
	 public ResponseEntity<ApiResponse<Object>> handlePasswordMismatch(PasswordMismatchException ex) {
	     ApiResponse<Object> response = new ApiResponse<>(
	             HttpStatus.BAD_REQUEST.value(),
	             ex.getMessage()
	     );
	     return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	 }
	 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
	    ApiResponse<Object> response = new ApiResponse<>(
	            HttpStatus.INTERNAL_SERVER_ERROR.value(),
	            "Something went wrong: " + ex.getMessage()
	    );
	    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
