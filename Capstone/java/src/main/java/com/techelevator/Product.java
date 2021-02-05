package com.techelevator;

public class Product {
	private String productName;
	private Double productPrice;
	private String slotCode;
	private String itemType;
	
	public Product(String productName, Double productPrice, String slotCode, String itemType) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.slotCode = slotCode;
		this.itemType = itemType;
	}

	public String getProductName() {
		return productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public String getSlotCode() {
		return slotCode;
	}

	public String getItemType() {
		return itemType;
	}
}
