package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine {
	private double balance;
	private List<Slot> slots;
	
	public VendingMachine() throws FileNotFoundException {
		balance = 0.00;
		slots = new ArrayList<Slot>();
		File vendingMachineProductList = new File("vendingmachine.csv");
		loadFile(vendingMachineProductList);
	}
	
	private void loadFile(File vendingMachineProductList) throws FileNotFoundException {
		Scanner fileInput = new Scanner(vendingMachineProductList);
		
		while (fileInput.hasNextLine()) {
			String productLine = fileInput.nextLine();
			
			String[] productLineSeperated = productLine.split("\\|");
			
			String slotValue = productLineSeperated[0];
			String productName = productLineSeperated[1];
			Double productPrice = Double.parseDouble(productLineSeperated[2]);
			String productType = productLineSeperated[3];
			
			Product aProduct = new Product(productName, productPrice, slotValue, productType);
			
			int maxStock = 5;
			Slot aSlot = new Slot(aProduct, maxStock);
			
			slots.add(aSlot);
		}
		
		fileInput.close();
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Slot> getSlots() {
		return slots;
	}
	
	
}
