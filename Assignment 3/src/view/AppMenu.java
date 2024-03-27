package view;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Store;
import model.Animals;
import model.Boardgames;
import model.Figures;
import model.Puzzles;
import model.Toy;

public class AppMenu {
	//Creating Scanner to be used in rest of the file
	Scanner input;
	/**
 	Constructor for AppMenu class
     */
	public AppMenu() throws Exception {
		input = new Scanner(System.in);
	}
	/**
 	Method for displaying main menu options of store and returns char input of user.
 	@return the option chosen by the user
     */
	public char showMainMenu() {
		System.out.println("*****************************************************");
		System.out.println("*        WELCOME TO TOY STORE COMPANY!              *");
		System.out.println("*****************************************************");
		System.out.println("How May We Help You?:\n");
		System.out.println("\t(1) Search Inventory and Purchase Toy");
		System.out.println("\t(2) Add New Toy");
		System.out.println("\t(3) Remove Toy");
		System.out.println("\t(4) Save and Exit\n");
		System.out.print("Enter Option: ");
        String userInput = input.nextLine();
        //Code for validating user input
        if (userInput.length() != 1) {
            System.out.println("The input length must be one character! Try again.");
            return showMainMenu();
        }
        char option = userInput.charAt(0);
        if (!Character.isDigit(option)) {
            System.out.println("This is not an integer number! Try again.");
            return showMainMenu();
        }
        int numOption = Character.getNumericValue(option);
        if (numOption < 0 || numOption > 5) {
            System.out.println("This is not a valid option! Try again.");
            return showMainMenu();
        }
        return option;
    }
	/**
 	Method for displaying search options of store and returns char input of user.
 	@return the option chosen by the user
     */
	public char showPurchaseMethod() {
		System.out.println("Find Toys With:\n");
		System.out.println("\t(1) Serial Number(SN)");
		System.out.println("\t(2) Toy Name");
		System.out.println("\t(3) Type");
		System.out.println("\t(4) Back to Main Menu\n");
		System.out.print("Enter a choice:");
        String userInput = input.nextLine();
       //Code for input validation
        if (userInput.length() != 1) {
            System.out.println("The input length must be one character! Try again.");
            return showMainMenu();
        }
        char option = userInput.charAt(0);
        if (!Character.isDigit(option)) {
            System.out.println("This is not an integer number! Try again.");
            return showMainMenu();
        }
        int numOption = Character.getNumericValue(option);
        if (numOption < 0 || numOption > 5) {
            System.out.println("This is not a valid option! Try again.");
            return showMainMenu();
        }
        return option;
    }
	/**
 	Method for searching by serial number. If object exists runs purchaseOption method, if not then it re prompts user.
 	@param product Stores inventory in ArrayList
     */
	public void searchbySN(ArrayList<Toy> product) throws Exception {
	    boolean found = false;
	    while (!found) {
	        System.out.print("Enter a serial number: ");
	        String sn = input.nextLine();
	        int option = 1;
	        boolean flag = false;
	        for (Toy toy : product) {
	            if (toy.getSn().equalsIgnoreCase(sn)) {
	                System.out.println("(" + option + ") " + toy.toString());
	                flag = true;
	                System.out.println("(2) Back To Search Menu");
	                ArrayList<Toy> purchaseOptions = new ArrayList<>();
	                purchaseOptions.add(toy);
	                purchaseOption(product, purchaseOptions, 1);
	                found = true;
	            }
	        }
	        if (!flag) {
	            System.out.println(sn + " is an invalid serial number");
	            System.out.println("Please try again.\n");
	        }
	    }
	}
	/**
 	Method for searching by name of the toy, after prompting user for a name it runs the displaySearchResults method.
 	@param product Stores inventory in ArrayList
     */
	public void searchByName(ArrayList<Toy> product) throws Exception {
		System.out.print("Enter Toy Name:");
		String toyname = input.nextLine();
		displaySearchResults(product, toyname);
	}
	/**
 	Takes users input from searchByName and compares it to objects in stores inventory to find 
 	matches and display them. Lets user choose option then runs purchaseOption method if there were more than 0 options.
 	@param product the stores inventory in a ArrayList
 	@param user search term entered by user
     */
	public void displaySearchResults(ArrayList<Toy> product, String user) throws Exception {
		System.out.println("Here are the search results:\n");
		int option = 1;
		boolean flag = false;
		ArrayList<Toy> purchaseOptions = new ArrayList<>();
		for(Toy toys : product) {
			if(toys.getName().toLowerCase().contains((user.toLowerCase()))) {
				
				purchaseOptions.add(toys);	
				System.out.println("("+option+") "+toys.toString());
				
				flag = true;
				option++;
			}
		}  
		if (!flag || option == 1) {
			System.out.println("No results for "+user);
		} 
		else {
			System.out.println("("+option+") Back to Main Menu");
			purchaseOption(product, purchaseOptions, option - 1);
		}
	}
	/**
 	Method for searching by type the toy, after prompting user for a type it runs the displayTypeResults method.
 	@param product Stores inventory in ArrayList
     */
	public void searchByType(ArrayList<Toy> product) throws Exception {
		System.out.print("Enter Toy Type(animal, figure, puzzle, boardgame): ");
		String toytype = input.nextLine().toLowerCase();
		displayTypeResults(product, toytype);
	}
	/**
 	Takes users input from searchByType and finds all objects in stores inventory with that type.
 	@param product the stores inventory in a ArrayList
 	@param user search term entered by user
     */
	public void displayTypeResults(ArrayList<Toy> product, String user) throws Exception {
		System.out.println("Here are the search results:\n");
		boolean flag = false;
		int option = 1;
		ArrayList<Toy> purchaseOptions = new ArrayList<>();
		
		for (Toy toys : product) {
	        if (toys instanceof Animals && user.toLowerCase().equalsIgnoreCase("animal")) {
	            System.out.println("("+option+") "+toys.toString());
	            option++;
	            purchaseOptions.add(toys);
	            flag = true;
	        } 
	        if (toys instanceof Figures && user.toLowerCase().equalsIgnoreCase("figure")) {
	            System.out.println("("+option+") "+toys.toString());
	            option++;
	            purchaseOptions.add(toys);
	            flag = true;
	        } 
	        if (toys instanceof Puzzles  && user.toLowerCase().equalsIgnoreCase("puzzle")) {
	            System.out.println("("+option+") "+toys.toString());
	            option++;
	            purchaseOptions.add(toys);
	            flag = true;
	        } 
	        if (toys instanceof Boardgames && user.toLowerCase().equalsIgnoreCase("boardgame")) {
	            System.out.println("("+option+") "+toys.toString());
	            option++;
	            purchaseOptions.add(toys);
	            flag = true;
	        }
	    }	
	    	if (!flag) {
	        System.out.println("No toys of type '" + user + "' found");
	}  else {
	 System.out.println("("+option+") Back to Main Menu");
	 purchaseOption(product, purchaseOptions, option - 1);
	 
 }
	}
	/**
 	Displays a list of toys matching the search parameters from other methods. Users are either prompted to make a purchase which runs the purchase method
 	or users can return to menu running the continueToMenu method.
 	@param toys the stores inventory in a ArrayList
 	@param purchaseOptions options being displayed to user contained in an ArrayList
 	@param maxOption represents total option of toys in purchaseOptions
     */
	public void purchaseOption(ArrayList<Toy> toys, ArrayList<Toy> purchaseOptions, int maxOption) throws Exception {
	    boolean validnum = false;
	    while (!validnum) {
	    	System.out.print("Enter option number to purchase: ");
	    	int option = input.nextInt();
	    	input.nextLine(); 
	    	
	    	if (option >= 1 && option < maxOption + 1) {
	    		purchase(purchaseOptions.get(option - 1), toys);
	    		validnum = true;
	    	} 
	    	else if (option == maxOption + 1) {
	    		continueToMenu(toys);
	    		validnum = true;
	    	} 
	    	else {
	    		System.out.println("Invalid option. Please enter a number between 1 and " + maxOption + ".\n");
	    	}
	   }
	}
	/**
 	Makes purchase, decreasing available toy count by one and continues by running continueToMenu.
 	@param product toy being bough
 	@param toys the stores inventory in a ArrayList
     */
	public void purchase(Toy product, ArrayList<Toy> toys ) throws Exception {
		if (product.getCount() > 0) {
					product.setCount(product.getCount() - 1);
					System.out.println(product.getCount());
					System.out.println("The Transaction Successfully Terminated!");
					continueToMenu(toys);
				}
		else {
			System.out.println("Out of stock");
			continueToMenu(toys);
		}
	}
	/**
 	Displays menu for removing a toy and returns the toys prompted serial number.
 	@return toys serial number
     */
	public int showRemoveToy() {
		System.out.println("Enter Serial Number to remove Toy:");
		int serialnumber = input.nextInt();
		return serialnumber;		
	}
	/**
 	Goes to menu by creating a new Store object and running the toys array through to reinitialize it.
 	@param toys ArrayList containing the stores inventory
     */
	public void continueToMenu(ArrayList<Toy> toys) throws Exception {
        System.out.println("Press \"Enter\" to continue...");
        input.nextLine();
        Store storeControler = new Store(toys);
        storeControler.launchApplication();
	}
	/**
 	Method prompting user confirmation to remove toy from inventory.
 	@param toy the toy being removed
 	@param toys ArrayList of the stores inventory
     */
	public void wouldYouLikeToRemove(Toy toy, ArrayList<Toy> toys) throws Exception {
	    System.out.println("Do you want to remove it? (y/n)");
	    String removeToy = input.nextLine().toLowerCase();
	    
	    if (removeToy.equals("y")) {
	    	toys.remove(toy);
	        System.out.println("Item removed!");
	        continueToMenu(toys);
	    } 
	    else {
	    	continueToMenu(toys);
	    }
	}
}
	