package com.cimb.bikelahuserdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.bikelahuserdb.entity.Transactions;

public interface TransactionRepo extends JpaRepository<Transactions, Integer> {

}
