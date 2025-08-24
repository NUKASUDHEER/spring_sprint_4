package com.sprint4.team4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.sprint4.team4.model.BookingModel;
import com.sprint4.team4.service.BookingService;
import com.sprint4.team4.utils.ApiResponse;

@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/booking/all")
	public ResponseEntity<ApiResponse<List<BookingModel>>> getAllBookings(){
		 List<BookingModel> bookings = bookingService.getAllBookings();
		 ApiResponse<List<BookingModel>> res = new ApiResponse<>(
					HttpStatus.OK.value(),
					"Bookings retrived successfully",
					bookings
				);
		 return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PostMapping("/booking")
	public ResponseEntity<ApiResponse<BookingModel>> createBooking(@RequestBody BookingModel booking){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = (String) auth.getPrincipal(); 
		BookingModel bm = bookingService.CreateBooking(booking,userId);
		ApiResponse<BookingModel> res = new ApiResponse<>(HttpStatus.OK.value(),"Booking details have been saved successfully.",bm);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@GetMapping("/history")
	public ResponseEntity<ApiResponse<List<BookingModel>>> getBookings(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = (String) auth.getPrincipal(); 
		boolean isAdmin = auth.getAuthorities()
                 .stream()
                 .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
		List<BookingModel> bookings = bookingService.getAllBookingWithGiveUserRole(userId,isAdmin);
		ApiResponse<List<BookingModel>> res = new ApiResponse<>(
				HttpStatus.OK.value(),
				"Bookings retrived successfully",
				bookings
			);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
//	@PutMapping("/admin/delivery-update/{id}")
//	public ResponseEntity<ApiResponse<BookingModel>> updateDeliveryStatus(@PathVariable Long id){
//		
//	}
	
	
}
