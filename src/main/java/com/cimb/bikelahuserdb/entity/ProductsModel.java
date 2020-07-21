package com.cimb.bikelahuserdb.entity;

public class ProductsModel {

	private int id;
	private String productName;
	private int price;
	private int quantity;
	private int discount;
	private String category;
	private String image;
	private String desc;
	private int totalPurchased;

	protected ProductsModel() {
	}

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getTotalPurchased() {
		return totalPurchased;
	}

	public void setTotalPurchased(int totalPurchased) {
		this.totalPurchased = totalPurchased;
	}

}
