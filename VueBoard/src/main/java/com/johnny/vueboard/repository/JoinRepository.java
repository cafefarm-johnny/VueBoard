package com.johnny.vueboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnny.vueboard.Entity.UserInfo;

public interface JoinRepository extends JpaRepository<UserInfo, Long> {
	
	Optional<UserInfo> findByUserId(String userId);
}
