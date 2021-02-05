package com.techelevator;
import java.io.FileNotFoundException;

/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone
import java.util.*;
public class VendingMachineCLI {

    // Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE      = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT          = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													    MAIN_MENU_OPTION_PURCHASE,
													    MAIN_MENU_OPTION_EXIT
													    };
	
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
															PURCHASE_MENU_OPTION_SELECT_PRODUCT,
															PURCHASE_MENU_OPTION_FINISH
													    	};
	
	private Menu vendingMenu;              // Menu object to be used by an instance of this class
	
	public VendingMachineCLI(Menu menu) {  // Constructor - user will pas a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	}
	/**************************************************************************************************************************
	*  VendingMachineCLI main processing loop
	*  
	*  Display the main menu and process option chosen
	*
	*  It is invoked from the VendingMachineApp program
	*
	*  THIS is where most, if not all, of your Vending Machine objects and interactions 
	*  should be coded
	*
	*  Methods should be defined following run() method and invoked from it
	 * @throws FileNotFoundException 
	*
	***************************************************************************************************************************/

	public void run() throws FileNotFoundException {
		VendingMachine mainMachine = new VendingMachine();
		boolean shouldProcess = true;         // Loop control variable
		
		while(shouldProcess) {                // Loop until user indicates they want to exit
			
			String choice = (String)vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice
			
			switch(choice) {                  // Process based on user menu choice
			
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems(mainMachine);           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseMenu(mainMachine);
					break;                    // Exit switch statement
			
				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                  // Exit switch statement
			}	
		}
		return;                               // End method and return to caller
	}
/********************************************************************************************************
 * Methods used to perform processing
 ********************************************************************************************************/
	public void displayItems(VendingMachine mainMachine) { 
	    List<Slot> slots = mainMachine.getSlots();
		for (int i = 0; i < slots.size(); i++) {
			Slot singleSlot = slots.get(i);
		    Product aProduct = singleSlot.getProduct();
		    double aPrice = aProduct.getProductPrice();
		    String aName = aProduct.getProductName();
		    int aAmount  = singleSlot.getProductAmount();
		    String aSlotCode = singleSlot.getSlotCode();
		    if (aAmount == 0) {
		    	System.out.println(aSlotCode + " " + aName + " SOLD OUT");
		    }
		    else {
		    	System.out.printf(aSlotCode + " " + aName + " $" + "%.2f Quantity: " + aAmount + "\n", aPrice);
		    }
		}
		// static attribute used as method is not associated with specific object instance
		// Code to display items in Vending Machine
	}
	
	public void purchaseMenu(VendingMachine mainMachine) {

		boolean shouldProcess = true;
		Scanner userInput = new Scanner(System.in);
		
		while(shouldProcess) {
			
			System.out.println("\nCurrent Balance: ");
			System.out.printf("%.2f",mainMachine.getBalance());
			
			String choice = (String)vendingMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			
			switch(choice) {
			
				case PURCHASE_MENU_OPTION_FEED_MONEY:
					System.out.println("Please input a bill to add to the balance: ");
					System.out.println("$(1, 2, 5, 10, 20, 50, 100)");
					String userWantedAmount = userInput.nextLine();
					int userWantedDollarAmt = Integer.parseInt(userWantedAmount);
					
					while (userWantedDollarAmt != 1 && userWantedDollarAmt != 2 &&
						userWantedDollarAmt != 5 && userWantedDollarAmt != 10 &&
						userWantedDollarAmt != 20 && userWantedDollarAmt != 50 &&
						userWantedDollarAmt != 100) {
						System.out.println("Please a select a dollar amount that matches a bill: ");
						userWantedDollarAmt = Integer.parseInt(userInput.nextLine());
					}
					double newBalance = mainMachine.getBalance() + userWantedDollarAmt;
					mainMachine.setBalance(newBalance);
					break;
			
				case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
					displayItems(mainMachine);
					boolean doesProductExist = false;
					
					System.out.print("Please select an item code above to purchase: ");
					String userWantedCode = userInput.nextLine();
					
					for (Slot singleSlot : mainMachine.getSlots()) {
						if (singleSlot.getSlotCode().equals(userWantedCode.toUpperCase())) {
							doesProductExist = true;
							Product theProduct = singleSlot.getProduct();
							if (mainMachine.getBalance() >= theProduct.getProductPrice() && 
								singleSlot.getProductAmount() > 0) {
								String productName = theProduct.getProductName();
								double productCost = theProduct.getProductPrice();
								double updatedBalance = mainMachine.getBalance() - productCost;
								mainMachine.setBalance(updatedBalance);
								
								System.out.printf("Purchased: " + productName +", Cost: " + productCost + 
										", New Balance: " + "%.2f\n", mainMachine.getBalance());
								
								String theProductType = theProduct.getItemType();
								
								if (theProductType.equals("Chip")) {
									System.out.println("Crunch Crunch, Yum!");
								} else if (theProductType.equals("Candy")) {
									System.out.println("Munch Munch, Yum!");
								} else if (theProductType.equals("Drink")) {
									System.out.println("Glug Glug, Yum!");
								} else if (theProductType.equals("Gum")) {
									System.out.println("Chew Chew, Yum!");
								}
								
								singleSlot.setProductAmount(singleSlot.getProductAmount() - 1);
								break;
							} else if (singleSlot.getProductAmount() == 0) {
								System.out.println("Sorry, item is out of stock!");
								break;
							} else if (mainMachine.getBalance() < theProduct.getProductPrice()) {
								System.out.println("Sorry, please add more to the balance to purchase the item!");
								break;
							}
						}
					}
					break;                    
			
				case PURCHASE_MENU_OPTION_FINISH:
					endMethodProcessing();
					shouldProcess = false;
					break;  
			}
		}
		userInput.close();
		return;
	}
	
	public void purchaseItems() {	 // static attribute used as method is not associated with specific object instance
		// Code to purchase items from Vending Machine
	}
	
	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}
}
