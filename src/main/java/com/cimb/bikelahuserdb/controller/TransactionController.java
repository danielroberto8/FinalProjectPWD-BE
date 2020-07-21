package com.cimb.bikelahuserdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.bikelahuserdb.dao.TransactionRepo;
import com.cimb.bikelahuserdb.entity.Transactions;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

	@Autowired
	private TransactionRepo transactionRepo;
	
	@GetMapping
	public Iterable<Transactions> getAllTransaction(){
		return transactionRepo.findAll();
	}
}
