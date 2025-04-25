package com.mpos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpos.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
}
