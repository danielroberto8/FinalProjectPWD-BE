package com.cimb.bikelahuserdb.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String productName;
	private int price;
	private int quantity;
	private int discount;
	private String category;
	private String image;
	private String description;
	private int totalPurchased;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy ="product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Carts> cart;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTotalPurchased() {
		return totalPurchased;
	}
	public void setTotalPurchased(int totalPurchased) {
		this.totalPurchased = totalPurchased;
	}
	public List<Carts> getCart() {
		return cart;
	}
	public void setCart(List<Carts> cart) {
		this.cart = cart;
	}
}
