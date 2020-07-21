package com.umc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umc.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	public List<UserEntity> findByEmail(String email);

}
