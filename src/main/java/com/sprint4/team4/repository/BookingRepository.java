package com.sprint4.team4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint4.team4.model.BookingModel;

public interface BookingRepository extends JpaRepository<BookingModel, Long>{
	// for admin
	List<BookingModel> findByStatusIn(List<String> statuses);
	// for user
	List<BookingModel> findByUserId(String userId);
}
