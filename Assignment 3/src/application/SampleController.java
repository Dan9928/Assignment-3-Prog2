package application;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.ToyManager;
import exceptions.NegativePriceException;
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
/**
 * The Sample controller class controls the view portion of the programming, housing the JavaFX portion of the project.
 * 
 */
public class SampleController implements Initializable{
	//Initializes ToyManager
	private ToyManager toysManager;
	/**
	* Sample controller initializer
	*/
    public SampleController() throws Exception {
    	toysManager = new ToyManager();
    }
    //Initializing FX labels
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
    private Label errorLabel;
    //Action event Listeners:
    
    /**
    * Buy Toy action. The selected Toy is checked, if its count is greater than 1 it will
    * subtract the count by one.
    *
    * @param event  Buy button click, 
    *
    */
    @FXML 
    void buyToy(ActionEvent event) throws IOException {
        String userItem = listToys.getSelectionModel().getSelectedItem();
        boolean itemFound = false;
        //Iterate through Array of toys
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
                	//If toy does not exist then out of stock
                    buyID.setText("Out of stock!");
                }
                break;
            }
        }
        //If for whatever reason item was not found.
        if (!itemFound) {
            buyID.setText("Out of stock!");
        }
    }
    /**
    * Clears search preferences.
    *
    * @param event Clear button being pressed
    */
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
    /**
    * Search toys. Updates list view with toys relevant to search.
    *
    * @param event  Search button being pressed
    */
    @FXML
    void searchToys(ActionEvent event) {
    	//Gather data from fields
    	String name = enterName.getText();
    	String type = enterType.getText();
    	String SN = enterSN.getText();
    	//Clear list
    	listToys.getItems().clear();
    	//Searching by name
        if (searchNameButton.isSelected()) {
			for(Toy toy : toysManager.getToysList()) {
				 if(toy.getName().toLowerCase().contains(name.toLowerCase())) {
			            listToys.getItems().add(toy.toString());
			        }
			}
    	}
        //Searching by SN
        if(searchSNButton.isSelected()) {
			for(Toy toy : toysManager.getToysList()) {
				if(toy.getSn().equals(SN)) {
					listToys.getItems().add(toy.toString());
				}
			}
        }
        //Searching by toy type
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
    /**
    * Adds toy to store.
    *
    * @param event  Add toy button being clicked. Adds toy based on type selected and information entered.
    */
    @FXML
	void addToy(ActionEvent event) throws Exception {
    	//Gather info from fields
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
    	//Determine toy type selected
    	
    	if(Price < 0) {
            try {
                throw new NegativePriceException();
            } catch (NegativePriceException e) {
                errorLabel.setText(e.getMessage());
            }
		}

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
    /**
    * Shows toys list in list view
    */
    @FXML
    void showToys(ActionEvent event) {
    }
    /**
    * Saves store inventory
    *
    * @param event Whenever an action is taken the list is saved
    */
    @FXML
    private void Save(MouseEvent event) throws IOException {
    	toysManager.saveToFile();
    }
    /**
    * Initialize list view and combo boxes
    *
    * @param arg0  the string to display.  If the text is null, 
    *        
    * @param arg1 Local objects needed for rendering.
    */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            for (Toy toy : toysManager.getToysList()) {
                listToys.getItems().addAll(toy.toString());
            }       
            categoryComboBox.setEditable(false);
            categoryComboBox.getItems().addAll("Animal", "Puzzle", "Figure", "Boardgame");   
	}
	/**
	    * Removes toy from inventory
	    *
	    * @param event Remove toy button being pressed. When pressed the toy with the matching SN entered is removed.  
	    */
	@FXML
	void removeToy(ActionEvent event) {
		//Initialize collected text and toy list.
		String deleteSN = deleteBySN.getText();
		ArrayList<Toy> toysList = toysManager.getToysList();
		//Find matching toy and delete
		for(Toy toy : toysList) {
			if(toy.getSn().equals(deleteSN)) {
				deleteToysList.getItems().add(toy.toString());
				toysManager.RemoveToy(toy);
				break;
			}
		}
	}	
}
