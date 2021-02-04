package com.techelevator;

public class Product {
	private String productName;
	private Double productPrice;
	private String slotValue;
	private String itemType;
	
	public Product(String productName, Double productPrice, String slotValue, String itemType) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.slotValue = slotValue;
		this.itemType = itemType;
	}

	public String getProductName() {
		return productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public String getSlotValue() {
		return slotValue;
	}

	public String getItemType() {
		return itemType;
	}
}
