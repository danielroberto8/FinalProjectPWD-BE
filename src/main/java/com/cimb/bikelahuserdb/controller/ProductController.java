package com.cimb.bikelahuserdb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.bikelahuserdb.dao.ProductRepo;
import com.cimb.bikelahuserdb.entity.Products;
import com.cimb.bikelahuserdb.entity.ProductsModel;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	private ProductRepo productRepo;

	@GetMapping()
	public Iterable<Products> getAllProducts() {
		return productRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Products> getProductById(@PathVariable int id) {
		return productRepo.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id) {
		productRepo.deleteById(id);
	}

	@PostMapping()
	public Products addProduct(@RequestBody Products product) {
		return productRepo.save(product);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Products> updateProduct(@PathVariable int id, @RequestBody ProductsModel product) {
		Products findProduct = productRepo.findById(id).get();

		if (product.getProductName() != null) {
			findProduct.setProductName(product.getProductName());
		}

		if (product.getCategory() != null) {
			findProduct.setCategory(product.getCategory());
		}

		if (product.getDescription() != null) {
			findProduct.setDescription(product.getDescription());
		}

		if (product.getImage() != null) {
			findProduct.setImage(product.getImage());
		}

		if (product.getPrice() != 0) {
			findProduct.setPrice(product.getPrice());
		}

		if (product.getDiscount() > -1 && product.getDiscount() < 100) {
			findProduct.setDiscount(product.getDiscount());
		}

		if (product.getQuantity() != 0) {
			findProduct.setQuantity(product.getQuantity());
		}

		if (product.getTotalPurchased() > -1) {
			findProduct.setTotalPurchased(product.getTotalPurchased());
		}

		productRepo.save(findProduct);

		return ResponseEntity.noContent().build();
	}
}
