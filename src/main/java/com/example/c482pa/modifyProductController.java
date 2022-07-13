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
import java.net.URL;
import java.util.ResourceBundle;

/** This controller controls the screen where the user can modify a product. */
public class modifyProductController implements Initializable {

    /**This is the table column that represents the part ID */
    @FXML
    private TableColumn<Part, Integer> PartID;

    /**This is the table column that represents the part inventory */
    @FXML
    private TableColumn<Part, Integer> PartInventory;

    /**This is the table column that represents the part name */
    @FXML
    private TableColumn<Part, String> PartName;

    /**This is the table column that represents the part price */
    @FXML
    private TableColumn<Part, Double> PartPrice;

    /**This is the text field where the product ID is entered */
    @FXML
    private TextField ProductID;

    /**This is the text field where the inventory is entered */
    @FXML
    private TextField ProductInvField;

    /**This is the text field where the max is entered */
    @FXML
    private TextField ProductMaxField;

    /**This is the text field where the min is entered*/
    @FXML
    private TextField ProductMinField;

    /**This is the text field where the name is entered */
    @FXML
    private TextField ProductNameField;

    /**This is the text field where the price is entered */
    @FXML
    private TextField ProductPriceField;

    /**This is the table for the parts */
    @FXML
    private TableView<Part> partTableView;

    /**This is the save button*/
    @FXML
    private Button save;

    /**This is the column that represents the associated part's ID */
    @FXML
    private TableColumn<Part, Integer> selectedID;

    /**This is the column that represents the associated part's inventory */
    @FXML
    private TableColumn<Part, Integer> selectedInventory;

    /**This is the column that represents the associated part's name */
    @FXML
    private TableColumn<Part, String> selectedName;

    /**This the associated part table */
    @FXML
    private TableView<Part> selectedPartTable;

    /**This is the column that represents the associated part's price*/
    @FXML
    private TableColumn<Part, Double> selectedPrice;

    /**This search bar for the part table */
    @FXML
    private TextField filterFieldPart;

    /**This is the stage for the modify part screen */
    private Stage stage;

    /**This is the scene for the modify part screen */
    private Scene scene;

    /**This is the parten for the modify part screen */
    private Parent root;
    /**This is the index of the product being modified*/
    private int OldIndex;

    /**This is the ID of the product being modified */
    private int OldId;

    /**The default product before all of its fields are set */
    private Product product = new Product("placeholder", 0.00, 0, 0, 0);

    /**This sets the associated parts upon initializing the modify product screen
     * @param product product being modified */
    public void setProductParts(Product product){
        this.product.getAllAssociatedParts().setAll(product.getAllAssociatedParts());
    }

    /**This gets the index of the product being modified
     * @param product product being modified*/
    public void getOldIndex(Product product){
        this.OldIndex = Inventory.getAllProducts().indexOf(product);
    }

    /**This gets the id of the product being modified
     * @param product product being modified*/
    public void getOldId(Product product){
        this.OldId = product.getId();
    }

    /**This method switches back to the main form.
     * @throws IOException throws IOException
     * @param event on hittin cancel OR saving*/
    public void switchToMainForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This method sets all the text fields with the product's old information
     * @param product selected product to be modified*/
    public void setAllTextFields(Product product) {
        setFieldID(product.getId());
        setFieldInv(product.getStock());
        setFieldName(product.getName());
        setFieldPrice(product.getPrice());
        setFieldMax(product.getMax());
        setFieldMin(product.getMin());
    }

    /**This sets the field text with the field ID
     * @param ID id to be set */
    public void setFieldID(int ID) {
       ProductID.setPromptText(String.valueOf(ID));
    }

    /**This sets the field text with the inventory
     * @param inv inv to be set */
    public void setFieldInv(int inv) {
        ProductInvField.setText(String.valueOf(inv));
    }

    /**This sets the field text with the product name
     * @param name name to be set */
    public void setFieldName(String name) {
        ProductNameField.setText(name);
    }

    /**This sets the field text with the price
     * @param price price to be set */
    public void setFieldPrice(Double price) {
        ProductPriceField.setText(String.valueOf(price));
    }

    /**This sets the field text with the max
     * @param max product max to be set */
    public void setFieldMax(int max){
        ProductMaxField.setText(String.valueOf(max));
    }

    /**This sets the field text with the min
     * @param min product min to be set*/
    public void setFieldMin(int min){
        ProductMinField.setText(String.valueOf(min));
    }

    /**This method adds an associated part
     * @param actionEvent on clicking "add" */
    public void addAssociatedPart(ActionEvent actionEvent) {
        if (!partTableView.getSelectionModel().isEmpty()){
            product.addAssociatedPart(partTableView.getSelectionModel().getSelectedItem());
        }
        else {
            addProductController.showAlert("No part selected.", "No part was selected", "Please selected a part and try again.");
        }
    }

    /**This method removes an associated part
     * @param actionEvent on "remove associated part"
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
                this.product.deleteAssociatedPart(selectedPartTable.getSelectionModel().getSelectedItem());
            }
        }

    }

    /**This method saves the modified part
     * @param event on clicking "save"
     * @throws IOException throws IOException*/
    @FXML
    void save(ActionEvent event) throws IOException {
        if (ProductInvField.getText().trim().isEmpty()
                || ProductNameField.getText().trim().isEmpty()
                || ProductPriceField.getText().trim().isEmpty()
                || ProductMaxField.getText().trim().isEmpty()
                || ProductMinField.getText().trim().isEmpty()){
            addPartController.showAlert("Empty Fields", "One or more fields is empty.", "Fill out all fields and try again.");
        }
        else if(!addPartController.isInt(ProductInvField.getText())){
            addPartController.showAlert("Invalid Input", "Invalid input for inventory.", "Please enter a whole number and try again.");
        }
        else if(!addProductController.isInt(ProductMinField.getText())){
            addProductController.showAlert("Invalid Input.", "Invalid input for Minimum or maximum", "Please enter a whole number and try again");
        }
        else if (!addProductController.isInt(ProductMaxField.getText())){
            addProductController.showAlert("Invalid Input.", "Invalid input for Minimum or maximum", "Please enter a whole number and try again");
        }
        else if (Integer.parseInt(ProductInvField.getText()) > Integer.parseInt(ProductMaxField.getText())){
            addPartController.showAlert("Inventory exceeds max.", "The inventory entered exceeds the max amount.", "Please enter the correct amount in inventory.");
        }
        else if (Integer.parseInt(ProductInvField.getText()) < Integer.parseInt(ProductMinField.getText())){
            addPartController.showAlert("Inventory below minimum.", "The inventory entered is less than the minimum amount",
                    "Please enter the correct amount.");
        }
        else if (!addPartController.isString(ProductNameField.getText())){
            addPartController.showAlert("Invalid input for name.", "The entered name is invalid",
                    "Please enter a name without any numbers or special characters.");
        }
        else if (!addPartController.isDouble(ProductPriceField.getText()) || !addPartController.twoDecimalPlaces(Double.valueOf(ProductPriceField.getText())) || Double.valueOf(ProductPriceField.getText()) <= 0){
            addPartController.showAlert("Invalid price", "The entered price is invalid.",
                    "Please enter a positive price with only two decimal places.");
        }
        else if (!addPartController.isInt(ProductMaxField.getText()) || Integer.valueOf(ProductMaxField.getText()) < 0){
            addPartController.showAlert("Invalid Max", "The entered max is invalid.",
                    "Please enter a whole positive number for the max");
        }
        else if (!addPartController.isInt(ProductMinField.getText()) || Integer.valueOf(ProductMinField.getText()) < 0){
            addPartController.showAlert("Invalid min", "The entered min is invalid.",
                    "Please enter a whole positive number for the min.");
        }
        else{
            this.product.setName(ProductNameField.getText());
            this.product.setPrice(Double.parseDouble(ProductPriceField.getText()));
            this.product.setStock(Integer.parseInt(ProductInvField.getText()));
            this.product.setMin(Integer.parseInt(ProductMinField.getText()));
            this.product.setMax(Integer.parseInt(ProductMaxField.getText()));
            Inventory.updateProduct(OldIndex, this.product);
            switchToMainForm(event);
        }
    }

    @Override
    /**This method initializes the tables on the modify product screen*/
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

        selectedPartTable.setItems(this.product.getAllAssociatedParts());

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



