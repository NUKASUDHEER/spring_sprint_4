package com.sprint4.team4.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint4.team4.model.BookingModel;
import com.sprint4.team4.repository.BookingRepository;
import com.sprint4.team4.repository.UserRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public BookingModel CreateBooking(BookingModel bookingModel,String userId) {
		if(!userRepository.existsByUserId(userId)) {
			throw new RuntimeException("User not exist, Please register");
		}
		bookingModel.setUserId(userId);
		return bookingRepository.save(bookingModel);
	}

	public List<BookingModel> getAllBookings() {
		return bookingRepository.findAll();
	}
	
	public List<BookingModel> getAllBookingWithGiveUserRole(String userId, boolean isAdmin) {
	    if (isAdmin) {
	        return bookingRepository.findByStatusIn(
	            Arrays.asList("IN_TRANSIT", "DELIVERED", "RETURNED")
	        );
	    } else {
	        // Normal user: Get all bookings by userId
	        return bookingRepository.findByUserId(userId);
	    }
	}

}
