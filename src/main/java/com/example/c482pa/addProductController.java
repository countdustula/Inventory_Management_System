package com.example.c482pa;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
/** This controller controls and manages the scene where the user adds a product the the inventory management system */
public class addProductController implements Initializable {

    /**This is the stage for the add product screen. */
    private Stage stage;

    /**This is the scene for the add product screen */
    private Scene scene;

    /**This is the parent for the add product screen */
    private Parent root;

    /**This is the default state of the product before the user adds in the details. */
    public Product product = new Product("placeholder", 0.00, 0, 0, 0);

    /**This is the first column of the part table */
    @FXML
    private TableColumn<Part, Integer> PartID;

    /**This is the column of the part table that represents the inventory */
    @FXML
    private TableColumn<Part, Integer> PartInventory;

    /**This is the column of the part table that represents the name of the part*/
    @FXML
    private TableColumn<Part, String> PartName;

    /**This is the column of the part table that represents the price. */
    @FXML
    private TableColumn<Part, Double> PartPrice;

    /**This is the text field where the user enters the amount that's in inventory */
    @FXML
    private TextField ProductInvField;

    /**This it the text field where the user enters the max that can be in inventory */
    @FXML
    private TextField ProductMaxField;

    /**This is the text field where the user enters the minimum amount that can be in inventory */
    @FXML
    private TextField ProductMinField;

    /**This is the text field where the user enters the product's name */
    @FXML
    private TextField ProductNameField;

    /**This is the text field where the user enters the price */
    @FXML
    private TextField ProductPriceField;

    /**This is the button that the user presses to add than associated part*/
    @FXML
    private Button addAssociatedPart;

    /**This is the table that lists all of the parts */
    @FXML
    private TableView<Part> partTableView;

    /**This is the column that represents the part's ID */
    @FXML
    private TableColumn<Part, Integer> selectedID;

    /**This is the column that represents that part's amount in inventory */
    @FXML
    private TableColumn<Part, Integer> selectedInventory;

    /**This is the column that represents the part's name */
    @FXML
    private TableColumn<Part, String> selectedName;

    /**This is the table that represents the associated parts */
    @FXML
    private TableView<Part> selectedPartTable;

    /**This is the column that represents the selected Part's price */
    @FXML
    private TableColumn<Part, Double> selectedPrice;


    /**This is the text field that the user searches for parts. */
    @FXML
    private TextField filterFieldPart;


    /**This method shows an alert.
     * @param contentText alert's content text
     * @param headerText alert's header text
     * @param windowText alert's window text*/
    public static void showAlert(String windowText, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(windowText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**Checks whether the entered number is an integer
     * @param number the number being tested
     * @return either true or false*/
    public static boolean isInt(String number) {
        try {
            int test = Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**This method tested whether the input is a valid string for a name
     * @param test the string being tested
     * @return either true or false*/
    public static boolean isString(String test) {
        return test.matches("[a-zA-Z' ']+");
    }


    /**This method tests whether the input is a valid double
     * @param number the number being tested
     * @return either true or false*/
    public static boolean isDouble(String number) {
        try {
            Double test = Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**This moethod tests whether the inputted number is a double with two  decimal places, fit for a price
     * @param test this is the number being tested
     * @return either true or false*/
    public static boolean twoDecimalPlaces(Double test) {
        if (BigDecimal.valueOf(test).scale() <= 2) {
            return true;
        } else {
            return false;
        }
    }

    /**This adds an associated part to the product
     * @param actionEvent user muts click on the "add" button*/
    public void addAssociatedPart(ActionEvent actionEvent) {
        if (!partTableView.getSelectionModel().isEmpty()){
            product.addAssociatedPart(partTableView.getSelectionModel().getSelectedItem());
         }
        else {
            showAlert("No part selected.", "No part was selected", "Please selected a part and try again.");
        }
    }


    /**This method removes an associated part
     * @param actionEvent user must click the "remove associated part" button
     * @throws IOException throws IOException*/
    public void removeAssociatedPart(ActionEvent actionEvent) throws IOException {

        if (selectedPartTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a part");
            alert.setHeaderText("No Part was selected");
            alert.setContentText("Please select a part and try again.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting Item", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Deleting Item");
            alert.setHeaderText("You are about to delete this selected item.");
            alert.setContentText("Are you sure you want to delete this part?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                product.deleteAssociatedPart(selectedPartTable.getSelectionModel().getSelectedItem());
            }
        }

    }

    /**This takes the user back to the main form
     * @param event user must click cancel or save a product
     * @throws IOException throws IOException*/
    public void switchToMainForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This method saves a product to inventory
     * @param event user must click the save button
     * @throws IOException throws IOException*/
    public void save(ActionEvent event) throws IOException {
        if (ProductInvField.getText().trim().isEmpty()
                || ProductNameField.getText().trim().isEmpty()
                || ProductPriceField.getText().trim().isEmpty()
                || ProductMaxField.getText().trim().isEmpty()
                || ProductMinField.getText().trim().isEmpty()) {
            showAlert("Empty Fields", "One or more fields is empty.", "Fill out all fields and try again.");
        } else if (!isInt(ProductInvField.getText())) {
            showAlert("Invalid Input", "Invalid input for inventory.", "Please enter a whole number and try again.");
        }else if(!isInt(ProductMinField.getText())){
            showAlert("Invalid Input.", "Invalid input for Minimum or maximum", "Please enter a whole number and try again");
        }
        else if (!isInt(ProductMaxField.getText())){
            showAlert("Invalid Input.", "Invalid input for Minimum or maximum", "Please enter a whole number and try again");
        }
        else if (Integer.parseInt(ProductInvField.getText()) > Integer.parseInt(ProductMaxField.getText())) {
            showAlert("Inventory exceeds max.", "The inventory entered exceeds the max amount.", "Please enter the correct amount in inventory.");
        } else if (Integer.parseInt(ProductInvField.getText()) < Integer.parseInt(ProductMinField.getText())) {
            showAlert("Inventory below minimum.", "The inventory entered is less than the minimum amount",
                    "Please enter the correct amount.");
        } else if (!isString(ProductNameField.getText())) {
            showAlert("Invalid input for name.", "The entered name is invalid",
                    "Please enter a name without any numbers or special characters.");
        } else if (!isDouble(ProductPriceField.getText()) || !twoDecimalPlaces(Double.valueOf(ProductPriceField.getText())) || Double.valueOf(ProductPriceField.getText()) <= 0) {
            showAlert("Invalid price", "The entered price is invalid.",
                    "Please enter a positive price with only two decimal places.");
        } else if (!isInt(ProductMaxField.getText()) || Integer.valueOf(ProductMaxField.getText()) < 0) {
            showAlert("Invalid Max", "The entered max is invalid.",
                    "Please enter a whole positive number for the max");
        } else if (!isInt(ProductMinField.getText()) || Integer.valueOf(ProductMinField.getText()) < 0) {
            showAlert("Invalid min", "The entered min is invalid.",
                    "Please enter a whole positive number for the min.");
        } else {

            this.product.setName(ProductNameField.getText());
                    this.product.setPrice(Double.parseDouble(ProductPriceField.getText()));
                    this.product.setStock(Integer.parseInt(ProductInvField.getText()));
                    this.product.setMin(Integer.parseInt(ProductMinField.getText()));
                    this.product.setMax(Integer.parseInt(ProductMaxField.getText()));
            Inventory.addProduct(this.product);
            switchToMainForm(event);
        }

    }


    @Override
    /**This method initializes the tables on the add product page, along with the filtered list when the user searches for a part*/
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PartID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartInventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));


        partTableView.setItems(Inventory.getAllParts());

        selectedID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        selectedName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        selectedInventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        selectedPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        selectedPartTable.setItems(product.getAllAssociatedParts());

        FilteredList<Part> filteredPart = new FilteredList<>(Inventory.getAllParts(), p -> true);

        filterFieldPart.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPart.setPredicate(myPart -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }




                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myPart.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;


                } else if (String.valueOf(myPart.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });


        SortedList<Part> sortedData = new SortedList<>(filteredPart);


        sortedData.comparatorProperty().bind(partTableView.comparatorProperty());

        partTableView.setItems(sortedData);
    }
    }

