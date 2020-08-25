package com.bsk.mybank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bsk.mybank.entity.Account;
import com.bsk.mybank.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	Optional<User> findByUserId(int userId);

}
