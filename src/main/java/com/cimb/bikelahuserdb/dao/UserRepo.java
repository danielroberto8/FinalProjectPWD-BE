package com.cimb.bikelahuserdb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.bikelahuserdb.entity.users;

public interface UserRepo extends JpaRepository<users, Integer> {

	public Optional<users> findByUsername(String username);

	public Optional<users> findByEmail(String email);

}
