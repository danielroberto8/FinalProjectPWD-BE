package com.cimb.bikelahuserdb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.bikelahuserdb.entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {

	public Optional<Users> findByUsername(String username);

	public Optional<Users> findByEmail(String email);

}
