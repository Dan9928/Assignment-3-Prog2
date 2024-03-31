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

public class ToyManager {
    private ArrayList<Toy> toys;
    private final String FILE_PATH = "res/toys.txt";

    public ToyManager() throws Exception {
        toys = new ArrayList<>();
        loadToys();
    }
    
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
    
    public boolean buyToy(String userItem) {
        for (Toy toy : toys) {
            if (toy.toString().equals(userItem)) {
                int count = toy.getCount();
                if (count > 0) {
                    toy.setCount(count - 1);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void searchByName(String name, ListView<String> listView) {
        for (Toy toy : toys) {
            if (toy.getName().toLowerCase().contains(name.toLowerCase())) {
                listView.getItems().add(toy.toString());
            }
        }
    }

    public void searchBySN(String SN, ListView<String> listView) {
        for (Toy toy : toys) {
            if (toy.getSn().equals(SN)) {
                listView.getItems().add(toy.toString());
            }
        }
    }

    public void searchByType(String type, ListView<String> listView) {
        for (Toy toy : toys) {
            if (toy instanceof Animals && type.equalsIgnoreCase("animal")) {
                listView.getItems().add(toy.toString());
            } else if (toy instanceof Figures && type.equalsIgnoreCase("figure")) {
                listView.getItems().add(toy.toString());
            } else if (toy instanceof Puzzles && type.equalsIgnoreCase("puzzle")) {
                listView.getItems().add(toy.toString());
            } else if (toy instanceof Boardgames && type.equalsIgnoreCase("boardgame")) {
                listView.getItems().add(toy.toString());
            }
        }
    }

    public void saveToFile() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH), true);
        for (Toy toy : toys) {
            pw.println(toy.format());
        }
        pw.close();
    }
}
