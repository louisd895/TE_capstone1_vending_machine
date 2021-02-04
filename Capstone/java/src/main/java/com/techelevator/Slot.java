package com.techelevator;

public class Slot {
	private Product product;
	private String slotValue;
	private int productAmount;
	
	public Slot(Product product, int productAmount) {
		this.product = product;
		this.productAmount = productAmount;
		slotValue = product.getSlotValue();
	}

	public Product getProduct() {
		return product;
	}

	public String getSlotValue() {
		return slotValue;
	}

	public int getProductAmount() {
		return productAmount;
	}
	
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
}
