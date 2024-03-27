package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.InvalidNumberOfPlayers;
import exceptions.NegativePriceException;
import model.Animals;
import model.Boardgames;
import model.Figures;
import model.Puzzles;
import model.Toy;
import view.AppMenu;

public class Store {

	ArrayList<Toy> toys;
	AppMenu appMenu;
	private final String FILE_PATH = "res/toys.txt";
	/**
 	Empty Store constructor used for initializing the store. Runs launchApplication.
     */
    public Store() throws Exception {
    	toys = new ArrayList<>();
    	appMenu = new AppMenu();
		loadToys();
		launchApplication();
    }
    /**
 	Store constructor creating new instance of store while feeding it the inventory of old instance of Store.
     */
    public Store(ArrayList<Toy> product) throws Exception {
    	toys =  product;
    	appMenu = new AppMenu();	
    }
    /**
 	Method for showing the main menu and running methods based on user input from showMainMenu.
     */
	public void launchApplication() throws Exception{
		boolean flag = true;
		int option;
		
		while (flag) {
			option = appMenu.showMainMenu();
			
			switch (option) {
			case '1':
				searchinvAndpurchaseToy();
				flag = false;
				break;
			case '2':
				Add();
				flag = false;
				break;
			case '3':
				Remove();
				flag = false;
				break;
			case '4':
				Save();
				flag = false;
				break;
				}	
			}	
		}
	/**
 	Method for showing options for searching store inventory and running methods 
 	based on user input from showPurchaseMethod.
     */
	public void searchinvAndpurchaseToy() throws Exception {
		
		boolean flag = true;
		while (flag) {
		
		int option = appMenu.showPurchaseMethod();
		switch (option) {
		case '1':
			appMenu.searchbySN(toys);
			flag = false;
			break;
		case '2':
			appMenu.searchByName(toys);
			flag = false;
			break;
		case '3':
			appMenu.searchByType(toys);
			flag = false;
			break;
		case '4':
			launchApplication();
			flag = false;
			break;
			}
		break;
		}
	}
	/**
 	Method for adding toys to the store inventory. Prompts user for toy information 
 	and after validating everything it adds toy to the toys ArrayList.
     */
	public void Add() throws Exception {
		Scanner user = new Scanner(System.in);
		System.out.print("Enter Serial Number: " );
		String sn = user.nextLine();
		
		boolean validsn = verifysn(sn);
		if (!validsn) {
			System.out.println("The Serial Number should only contain digits! Try again.");
			Add();
		}
		if (snExists(sn)) {
			System.out.println("A Toy With This Serial Number Already Exists! Try again.");
			Add();
		}
		if(snlength(sn)) {
			System.out.println("The Serial Number's length MUST be 10 digits! Try again.");
			Add();
		}
	    System.out.print("Enter Toy Name: " );
	    String name = user.nextLine();
	    System.out.print("Enter Toy Brand: " );
	    String brand = user.nextLine();
	    
	    double price;
	    boolean validPrice = false;
	    do {
	        System.out.print("Enter Toy Price: ");
	         price = user.nextDouble();
	        if (price < 0) {
	            try {
	                throw new NegativePriceException();
	            } catch (NegativePriceException e) {
	                System.out.println(e.getMessage());
	            }
	        } else {
	            validPrice = true;
	        }
	    } while (!validPrice);
	    
	    System.out.print("Enter Available Count: " );
	    int count = user.nextInt();
	    System.out.print("Enter Appropriate Age: " );
	    int age = user.nextInt();
	 
	    Toy toy = null;
	    
	    switch (sn.charAt(0)) {
		case '0':
		case '1':
			System.out.print("Enter Figure Class(Action, Doll, or Historic): ");
			String figclass = user.nextLine();
			do {
	            System.out.print("Enter Figure Class(Action, Doll, or Historic): ");
	            figclass = user.nextLine().toLowerCase();
	            if (!figclass.equals("action") && !figclass.equals("doll") && !figclass.equals("historic")) {
	                System.out.println("Invalid input! Please enter 'Action', 'Doll', or 'Historic'.");
	            }
	        } while (!figclass.equals("action") && !figclass.equals("doll") && !figclass.equals("historic"));
	        	
			
			toy = new Figures(sn, name, brand, price, count, age, figclass);
		    toys.add(toy);
		    System.out.print("New Toy Added!\n");
		    appMenu.continueToMenu(toys);
			break;
		case '2':
		case '3':
			System.out.print("Enter Material: ");
			String material = user.nextLine();
			System.out.print("Enter Size: ");
			String size = user.nextLine();

			toy = new Animals(sn, name, brand, price, count, age, material, size);
		    toys.add(toy);
		    System.out.print("New Toy Added!\n");
		    appMenu.continueToMenu(toys);
			break;
		case '4':
		case '5':
		case '6':
			System.out.print("Enter Puzzle Type: ");
			String type = user.nextLine();

			toy = new Puzzles(sn, name, brand, price, count, age, type);
		    toys.add(toy);
		    System.out.print("New Toy Added!\n");
		    appMenu.continueToMenu(toys);
			break;
		case '7':
		case '8':
		case '9':
		    boolean validNumberOfPlayers = false;
		    do {
		        try {
		            System.out.print("Enter Minimum Number of Players: ");
		            int min = user.nextInt();
		            System.out.print("Enter Maximum Number of Players: ");
		            int max = user.nextInt();
		            user.nextLine(); // Consume newline character
		            if (min > max) {
		                throw new InvalidNumberOfPlayers();
		            }
		            // If no exception thrown, mark as valid
		            validNumberOfPlayers = true;
		            String numPlayers = String.valueOf(min) + "-" + String.valueOf(max);
		            System.out.print("Enter Designer Names(Use ',' to separate the names if there is more than one name): ");
		            String designer = user.nextLine();
		            toy = new Boardgames(sn, name, brand, price, count, age, numPlayers, designer);
		            toys.add(toy);
		            System.out.println("New Toy Added!\n");
		            appMenu.continueToMenu(toys);
		        } catch (InvalidNumberOfPlayers e) {
		            System.out.println(e.getMessage()); // Print the error message
		        }
		    } while (!validNumberOfPlayers);
		    break;
		
		default:
			System.out.println("Error! Try Again");
			Add();
			break;
		}
	    user.close();
	}
	/**
 	Method for verifying serial number and returns a boolean, true for valid and false for invalid.
 	@param sn serial number being checked
 	@return true if valid serial number, false if invalid.
     */
	public boolean verifysn(String sn) {
		   for (char c : sn.toCharArray()) {
			      if (Character.isDigit(c)) {
			         return true;
			      }
			   }
			   return false;
			}
	/**
 	Method used to check if a toy with the given serial number exists in the stores inventory.
 	@param sn serial number being checked
 	@return true if serial number found, false if not found.
     */
	public boolean snExists(String sn) {
		for (Toy toy : toys) {
			if (toy.getSn().equals(sn)) {
				return true;
			}
		}
		return false;
	}
	/**
 	Method for verifying serial number is correct length and returns a boolean, true for valid and false for invalid.
 	@param sn serial number being checked
 	@return true if valid serial number, false if invalid.
     */
	public boolean snlength(String sn) {
		if(sn.length() != 10) {
			return true;
		}
		return false;
	}
	/**
 	Method for removing toys from the store inventory. Prompts user for toy information 
 	and after validating everything removes toy from toys ArrayList.
     */
	public void Remove() throws Exception {
		Scanner user = new Scanner(System.in);
		System.out.print("Enter Serial Number: ");
		String sn = user.nextLine();
		if(snlength(sn)) {
			System.out.println("The Serial Number's length MUST be 10 digits! Try again.");
			Remove();
		}
		
		boolean listCheck = true;
		boolean toyExists = false;
		Toy theToy = null;
		
		while(listCheck) {
			for(Toy toy : toys) {
				if (toy.getSn().equals(sn)) {
					System.out.println("This Item Found: \n"+toy);
					theToy = toy;
					listCheck = false;
					toyExists = true;
					}
				}
			if(toyExists) {
				appMenu.wouldYouLikeToRemove(theToy, toys);
			}
			else if(!toyExists) {
				System.out.println("Toy does not exist. Try again.");
				Remove();
				}
			}
		user.close();
	}
	/**
 	Reads data from text file and loads information as Toy objects into toys ArrayList
     */
	private void loadToys() throws Exception {
	    File db = new File(FILE_PATH);
	    
	    if (db.exists()) {
	        Scanner fileReader = new Scanner(db);
	        while (fileReader.hasNextLine()) {
	            String currentLine = fileReader.nextLine();
	            String[] splittedLine = currentLine.split(";");
	            String sn = splittedLine[0];
	            String name = splittedLine[1];
	            String brand = splittedLine[2];
	            double price = Double.parseDouble(splittedLine[3]);
	            int count = Integer.parseInt(splittedLine[4]);
	            int age = Integer.parseInt(splittedLine[5]);
	          
	            Toy product;
	            if (sn.startsWith("0") || sn.startsWith("1")) { // Figures
                    String figClass = splittedLine[6];
                    product = new Figures(sn, name, brand, price, count, age, figClass);
                    toys.add(product);
                } 
	            if (sn.startsWith("2") || sn.startsWith("3")) { // Animals
                    String material = splittedLine[6];
                    String size = splittedLine[7];
                    product = new Animals(sn, name, brand, price, count, age, material, size);
                    toys.add(product);
                }
	            if (sn.startsWith("4") || sn.startsWith("5") || sn.startsWith("6")) { // Puzzles
                    String puzzleType = splittedLine[6];
                    product = new Puzzles(sn, name, brand, price, count, age, puzzleType);
                    toys.add(product);
                }
	            if (sn.startsWith("7") || sn.startsWith("8") || sn.startsWith("9")) { // Board Games
                    String numOfPlayers = splittedLine[6];
                    String designer = splittedLine[7];
                    product = new Boardgames(sn, name, brand, price, count, age, numOfPlayers, designer);
                    toys.add(product);
                }
	        }
	        fileReader.close();
	    }
	}
	/**
 	Saves the stores inventory data to a text file by printing the formated  Toy objects to it.
     */
	public void Save() throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH), true);
		
        for (Toy toy : toys) {
            pw.println(toy.format());
        }
		System.out.println("Saving Data Into Database...\n");
		System.out.println("*********** THANKS FOR VISITING US! ***********");
		pw.close();
	}
}

