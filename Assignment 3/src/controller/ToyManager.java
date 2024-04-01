package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.ListView;
import model.Animals;
import model.Boardgames;
import model.Figures;
import model.Puzzles;
import model.Toy;
/**
 * Toy Manager class, keeps track of the story inventory as an object for reference in the code.
 * Contains all appropriate accessors for Toys ArrayList.
 */
public class ToyManager {
    //Initialize ArrayList and File_path
	private ArrayList<Toy> inventoryToys;
    private final String FILE_PATH = "res/toys.txt";
    /**
     * Constructor method that loads inventory.
     */
    public ToyManager() throws Exception {
    	inventoryToys = new ArrayList<>();
        loadToys();
    }
    /**
     * Load Toys method.
     * Loads database into ArrayList for use in store.
     */
    private void loadToys() throws Exception {
	    //Initialize file
    	File db = new File(FILE_PATH);
	    //Add toys to ArrayList
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
	            //Sorting toy types
	            Toy product;
	            if (sn.startsWith("0") || sn.startsWith("1")) { // Figures
                    String figClass = splittedLine[6];
                    product = new Figures(sn, name, brand, price, count, age, figClass);
                    inventoryToys.add(product);
                } 
	            if (sn.startsWith("2") || sn.startsWith("3")) { // Animals
                    String material = splittedLine[6];
                    String size = splittedLine[7];
                    product = new Animals(sn, name, brand, price, count, age, material, size);
                    inventoryToys.add(product);
                }
	            if (sn.startsWith("4") || sn.startsWith("5") || sn.startsWith("6")) { // Puzzles
                    String puzzleType = splittedLine[6];
                    product = new Puzzles(sn, name, brand, price, count, age, puzzleType);
                    inventoryToys.add(product);
                }
	            if (sn.startsWith("7") || sn.startsWith("8") || sn.startsWith("9")) { // Board Games
                    String numOfPlayers = splittedLine[6];
                    String designer = splittedLine[7];
                    product = new Boardgames(sn, name, brand, price, count, age, numOfPlayers, designer);
                    inventoryToys.add(product);
                }
	        }
	        fileReader.close();
	    }
	}
    /**
     * Method for retrieving array list.
     *
     * @return inventoryToys The stores up to date inventory
     */
    public ArrayList<Toy> getToysList(){
    	return inventoryToys;
    }
    /**
     * Saves inventory to file
     */
    public void saveToFile() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH), true);
        for (Toy toy : inventoryToys) {
            pw.println(toy.format());
        }
        pw.close();
    }
    /**
     * Removes toy method, removes toy from the store inventory
     * @param input  toy to be removed.
     */
    public void RemoveToy(Toy input) {
    	inventoryToys.remove(input);
    }
    /**
     * Buys the selected toy, reducing count by one
     * 
     * @param input toy being bought
     */
    public void buyToy(Toy input, int count) {
        input.setCount(count - 1);
    }
    /**
     * Adds a new toy to the inventory
     *
     * @param input toy being added
     */
    public void addToy(Toy input) {
    	inventoryToys.add(input);
    }
}
