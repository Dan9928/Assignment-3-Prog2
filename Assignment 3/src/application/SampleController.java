package application;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.ToyManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import model.Animals;
import model.Boardgames;
import model.Figures;
import model.Puzzles;
import model.Toy;

public class SampleController implements Initializable{
	
	private ToyManager toysManager;
	
    public SampleController() throws Exception {
    	toysManager = new ToyManager();
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
    private ComboBox<String> categoryComboBox;
    
    @FXML
    private Button removeToyButton;
    
    @FXML
    private Button addToyButton;

    @FXML
    private TextField deleteBySN;
    
    @FXML
    private ListView<String> deleteToysList;
    
    @FXML
    private TextField addType;
    
    @FXML
    private TextField addAge;

    @FXML
    private TextField addBrand;

    @FXML
    private TextField addClass;

    @FXML
    private TextField addCount;

    @FXML
    private TextField addDesigner;

    @FXML
    private TextField addMaterial;

    @FXML
    private TextField addMaximum;

    @FXML
    private TextField addMinimum;

    @FXML
    private TextField addName;

    @FXML
    private TextField addPrice;

    @FXML
    private TextField addSN;

    @FXML
    private TextField addSize;


    
    @FXML 
    void buyToy(ActionEvent event) throws IOException {
        String userItem = listToys.getSelectionModel().getSelectedItem();
        boolean itemFound = false;
        
        for (Toy toy : toysManager.getToysList()) {
            if (toy.toString().equals(userItem)) {
                int count = toy.getCount();
                if (count > 0) {
                	toysManager.buyToy(toy, count);;
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
    	if (searchNameButton.isSelected()||searchSNButton.isSelected()||searchTypeButton.isSelected()) {
    	group.getSelectedToggle().setSelected(false);
    	}
    	listToys.getItems().clear();
    	enterName.clear();
    	enterSN.clear();
    	enterType.clear();
        for (Toy toy : toysManager.getToysList()) {
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
			for(Toy toy : toysManager.getToysList()) {
				 if(toy.getName().toLowerCase().contains(name.toLowerCase())) {
			            listToys.getItems().add(toy.toString());
			        }
			}
    	}
        if(searchSNButton.isSelected()) {
			for(Toy toy : toysManager.getToysList()) {
				if(toy.getSn().equals(SN)) {
					listToys.getItems().add(toy.toString());
				}
			}
        }
        if(searchTypeButton.isSelected()) {
    		for (Toy toys : toysManager.getToysList()) {
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
	void addToy(ActionEvent event) throws Exception {
    	
    	String SN = addSN.getText();
    	String Name = addName.getText();
    	String Brand = addBrand.getText();	
    	double Price = Double.parseDouble(addPrice.getText());	
    	int Count = Integer.parseInt(addCount.getText());	
    	int Age = Integer.parseInt(addAge.getText());
    	String Class = addClass.getText();
    	String Type = addType.getText();
    	String Material = addMaterial.getText();
    	String Size = addSize.getText();
    	String Minimum = addMinimum.getText();
    	String Maximum = addMaximum.getText();
    	String Designer = addDesigner.getText();
    	String Players = Minimum + "-" + Maximum;
    	
    	
    	String ToyType = categoryComboBox.getValue();
    		
    	if(ToyType.equals("Animal")) {
    		Animals toyAdded = new Animals(SN, Name, Brand, Price, Count, Age, Material, Size);
    		toysManager.addToy(toyAdded);
    	}
    	if(ToyType.equals("Puzzle")) {
    		Puzzles toyAdded = new Puzzles(SN, Name, Brand, Price, Count, Age, Class);
    		toysManager.addToy(toyAdded);
    	}
    	if(ToyType.equals("Figure")) {
    		Figures toyAdded = new Figures(SN, Name, Brand, Price, Count, Age, Type);
    		toysManager.addToy(toyAdded);
    	}
    	if(ToyType.equals("Boardgame")) {
    		Boardgames toyAdded = new Boardgames(SN, Name, Brand, Price, Count, Age, Players, Designer);
    		toysManager.addToy(toyAdded);
    	}
	
	}
    @FXML
    void showToys(ActionEvent event) {
    	
    }
    
    @FXML
    private void Save(MouseEvent event) throws IOException {
    	toysManager.saveToFile(toysManager.getToysList());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            for (Toy toy : toysManager.getToysList()) {
                listToys.getItems().addAll(toy.toString());
                
            }       
            categoryComboBox.setEditable(false);
            categoryComboBox.getItems().addAll("Animal", "Puzzle", "Figure", "Boardgame");   
	}
	
	@FXML
	void removeToy(ActionEvent event) {
		
		String deleteSN = deleteBySN.getText();
		ArrayList<Toy> toysList = toysManager.getToysList();
		
		for(Toy toy : toysList) {
			if(toy.getSn().equals(deleteSN)) {
				deleteToysList.getItems().add(toy.toString());
				toysManager.RemoveToy(toy);
				break;
			}
		}
	}

	
}
