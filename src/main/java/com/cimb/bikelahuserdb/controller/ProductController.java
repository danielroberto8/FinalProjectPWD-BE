package com.cimb.bikelahuserdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.bikelahuserdb.dao.ProductRepo;
import com.cimb.bikelahuserdb.entity.Products;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins="http://localhost:3000")
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	@GetMapping()
	public Iterable<Products> getAllProducts(){
		return productRepo.findAll();
	}
}
