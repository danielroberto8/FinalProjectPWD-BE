package com.cimb.bikelahuserdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.bikelahuserdb.dao.CartRepo;
import com.cimb.bikelahuserdb.entity.Carts;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
	
	@Autowired
	private CartRepo cartRepo;
	
	@GetMapping()
	public Iterable<Carts> getAllCart(){
		return cartRepo.findAll();
	}
}
