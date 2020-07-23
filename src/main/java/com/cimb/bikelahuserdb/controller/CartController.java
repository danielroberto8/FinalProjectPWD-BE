package com.cimb.bikelahuserdb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.bikelahuserdb.dao.CartRepo;
import com.cimb.bikelahuserdb.dao.ProductRepo;
import com.cimb.bikelahuserdb.dao.UserRepo;
import com.cimb.bikelahuserdb.entity.CartModel;
import com.cimb.bikelahuserdb.entity.Carts;
import com.cimb.bikelahuserdb.entity.Products;
import com.cimb.bikelahuserdb.entity.Users;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ProductRepo productRepo;

	@GetMapping
	public List<Carts> getCartByParams(@RequestParam int user_id, @RequestParam int product_id) {
		List<Carts> findCartList = new ArrayList<Carts>();

		List<Carts> getAllCart = cartRepo.findAll();

		Users findUser = userRepo.findById(user_id).get();

		Products findProduct = productRepo.findById(product_id).get();

		getAllCart.forEach(val -> {
			if ((val.getProduct() == findProduct || product_id == 0) && val.getUser() == findUser) {
				findCartList.add(val);
			}
		});

		return findCartList;
	}

	@GetMapping("/{id}")
	public List<Carts> getCartByUserId(@PathVariable int id) {
		List<Carts> findCartList = new ArrayList<Carts>();

		Users findUser = userRepo.findById(id).get();

		List<Carts> getAllCart = cartRepo.findAll();

		getAllCart.forEach(val -> {
			if (val.getUser() == findUser) {
				findCartList.add(val);
			}
		});

		return findCartList;
	}

	@PostMapping()
	public Carts addCart(@RequestBody CartModel getCart) {

		Carts cart = new Carts();

		Products findProduct = productRepo.findById(getCart.getProduct_id()).get();
		cart.setProduct(findProduct);

		Users findUser = userRepo.findById(getCart.getUser_id()).get();
		cart.setUser(findUser);

		cart.setQuantity(getCart.getQuantity());

		return cartRepo.save(cart);
	}

	@PatchMapping("/{id}")
	public void updateCartQty(@PathVariable int id, @RequestBody CartModel cart) {
		Carts findCart = cartRepo.findById(id).get();
		findCart.setQuantity(cart.getQuantity());
		cartRepo.save(findCart);
	}

	@DeleteMapping("/deletebycartid/{cart_id}")
	public void deleteCartById(@PathVariable int cart_id) {
		cartRepo.deleteById(cart_id);
	}

	@DeleteMapping("/deletebyuserid/{user_id}")
	public void deleteCartByUserId(@PathVariable int user_id) {
		List<Carts> getAllCart = cartRepo.findAll();

		Users findUser = userRepo.findById(user_id).get();

		getAllCart.forEach(val -> {
			if (findUser == val.getUser()) {
				cartRepo.deleteById(val.getId());
			}
		});
	}

}
