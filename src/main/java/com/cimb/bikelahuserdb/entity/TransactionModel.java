package com.cimb.bikelahuserdb.entity;

import java.util.List;

public class TransactionModel {
	/*Model untuk mengambil data transaksi yang dikirim*/
	private int id;
	private int user;
	private String status;
	private String purchaseDate;
	private String confirmationDate;
	private int totalPayment;

	private String delivery;
	private List<ItemListModel> itemList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getConfirmationDate() {
		return confirmationDate;
	}
	public void setConfirmationDate(String confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	public int getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public List<ItemListModel> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemListModel> itemList) {
		this.itemList = itemList;
	}
	
	
}
