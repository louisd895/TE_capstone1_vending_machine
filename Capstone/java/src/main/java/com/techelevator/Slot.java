package com.techelevator;

public class Slot {
	private Product product;
	private String slotCode;
	private int productAmount;
	
	public Slot(Product product, int productAmount) {
		this.product = product;
		this.productAmount = productAmount;
		slotCode = product.getSlotCode();
	}

	public Product getProduct() {
		return product;
	}

	public String getSlotCode() {
		return slotCode;
	}

	public int getProductAmount() {
		return productAmount;
	}
	
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
}
