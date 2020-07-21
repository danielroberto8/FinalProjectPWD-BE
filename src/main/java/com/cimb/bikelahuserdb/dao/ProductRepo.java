package com.cimb.bikelahuserdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.bikelahuserdb.entity.Products;

public interface ProductRepo extends JpaRepository<Products, Integer>{

}
