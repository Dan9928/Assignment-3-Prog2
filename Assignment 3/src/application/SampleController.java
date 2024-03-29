package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import model.Animals;
import model.Boardgames;
import model.Figures;
import model.Puzzles;
import model.Toy;

public class SampleController implements Initializable{
	ArrayList<Toy> toys;
	private final String FILE_PATH = "res/toys.txt";
	
    public SampleController() throws Exception {
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
	

	
    @FXML
    private Button buyButton;
    
    @FXML
    private Label buyID;

    @FXML
    private Button clearButton;

    @FXML
    private TextField enterName;

    @FXML
    private TextField enterSN;

    @FXML
    private TextField enterType;

    @FXML
    private ToggleGroup group;

    @FXML
    private ListView<String> listToys;
    
    @FXML
    private Button searchButton;

    @FXML
    private RadioButton searchNameButton;

    @FXML
    private RadioButton searchSNButton;

    @FXML
    private RadioButton searchTypeButton;
    

    @FXML
    void buyToy(ActionEvent event) throws IOException {
        String userItem = listToys.getSelectionModel().getSelectedItem();
        boolean itemFound = false;
        
        for (Toy toy : toys) {
            if (toy.toString().equals(userItem)) {
                int count = toy.getCount();
                if (count > 0) {
                    toy.setCount(count - 1);
                    buyID.setText("The Transaction Successfully Terminated!");
                    itemFound = true;
                    int index = listToys.getSelectionModel().getSelectedIndex();
                    listToys.getItems().set(index, toy.toString());
                } else {
                    buyID.setText("Out of stock!");
                }
                break;
            }
        }
        
        if (!itemFound) {
            buyID.setText("Out of stock!");
        }
    }

    @FXML
    void clearToys(ActionEvent event) {
    	buyID.setText("");
    	group.getSelectedToggle().setSelected(false);
    	listToys.getItems().clear();
    	enterName.clear();
    	enterSN.clear();
    	enterType.clear();
        for (Toy toy : toys) {
            listToys.getItems().addAll(toy.toString());
        }
    }


    @FXML
    void searchToys(ActionEvent event) {
    	String name = enterName.getText();
    	String type = enterType.getText();
    	String SN = enterSN.getText();
    	listToys.getItems().clear();
        if (searchNameButton.isSelected()) {
			for(Toy toy : toys) {
				 if(toy.getName().toLowerCase().contains(name.toLowerCase())) {
			            listToys.getItems().add(toy.toString());
			        }
			}
    	}
        if(searchSNButton.isSelected()) {
			for(Toy toy : toys) {
				if(toy.getSn().equals(SN)) {
					listToys.getItems().add(toy.toString());
				}
			}
        }
        if(searchTypeButton.isSelected()) {
    		for (Toy toys : toys) {
    	        if (toys instanceof Animals && type.toLowerCase().equalsIgnoreCase("animal")) {
    	        	listToys.getItems().add(toys.toString());
    	        } 
    	        if (toys instanceof Figures && type.toLowerCase().equalsIgnoreCase("figure")) {
    	        	listToys.getItems().add(toys.toString());
    	        } 
    	        if (toys instanceof Puzzles  && type.toLowerCase().equalsIgnoreCase("puzzle")) {
    	        	listToys.getItems().add(toys.toString());
    	        } 
    	        if (toys instanceof Boardgames && type.toLowerCase().equalsIgnoreCase("boardgame")) {
    	        	listToys.getItems().add(toys.toString());
    	        }
    	    }	
        }

    }

    @FXML
    void showToys(ActionEvent event) {
    	
    }
    
    @FXML
    private void Save(MouseEvent event) throws IOException {
    	PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH), true);
            for (Toy toy : toys) {
            	pw.println(toy.format());
            }
            pw.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            for (Toy toy : toys) {
                listToys.getItems().addAll(toy.toString());
            }
         
	}
	

}
