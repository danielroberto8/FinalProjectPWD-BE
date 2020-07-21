package com.cimb.bikelahuserdb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int userId;
	private String status;
	private String purchaseDate;
	private String confirmationDate;
	private int totalPayment;
	private String transactionImage;
	private String cancelationMessage;
	private String delivery;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getTransactionImage() {
		return transactionImage;
	}

	public void setTransactionImage(String transactionImage) {
		this.transactionImage = transactionImage;
	}

	public String getCancelationMessage() {
		return cancelationMessage;
	}

	public void setCancelationMessage(String cancelationMessage) {
		this.cancelationMessage = cancelationMessage;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

}
