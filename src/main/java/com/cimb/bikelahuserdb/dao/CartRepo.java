package com.cimb.bikelahuserdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.bikelahuserdb.entity.Carts;

public interface CartRepo extends JpaRepository<Carts, Integer>{

}
