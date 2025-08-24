package com.sprint4.team4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint4.team4.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long>{
	 boolean existsByUserId(String userId);
	 UserModel getByUserId(String userId);
}
