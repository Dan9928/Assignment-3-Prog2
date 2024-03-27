package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SampleController {

    @FXML
    private Button buyButton;

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
    private ListView<?> listToys;

    @FXML
    private Button searchButton;

    @FXML
    private RadioButton searchByName;

    @FXML
    private RadioButton searchBySN;

    @FXML
    private RadioButton searchByType;

}
