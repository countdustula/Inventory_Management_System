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


/** This controller controls the main screen of the application,
 * where the user adds, modifies, and deletes parts and products */
public class mainFormController implements Initializable {

    /**This is the stage for the main form */
    private Stage stage;

    /**This is the scene for the main form */
    private Scene scene;

    /**This is the parent for the main form */
    private Parent root;

    /**This is the exit button */
    @FXML
    private Button exit;

    /**This is the table column representing the part ID */
    @FXML
    private TableColumn<Part, Integer> PartID;

    /**This is the table column representing the amount in inventory */
    @FXML
    private TableColumn<Part, Integer> PartInventory;

    /**This is the table column representing the part name */
    @FXML
    private TableColumn<Part, String> PartName;

    /**This is the table column representing PartPrice */
    @FXML
    private TableColumn<Part, Double> PartPrice;

    /**This is the table representing the part table */
    @FXML
    private TableView<Part> MainPartTableView;

    /**This is the table column that represents a product's ID */
    @FXML
    private TableColumn<Product, Integer> ProductID;

    /**This is the table column that represents the amount in inventory */
    @FXML
    private TableColumn<Product, Integer> ProductInventory;

    /**This is the table column that represents the product name */
    @FXML
    private TableColumn<Product, String> ProductName;

    /**This is the table column that represents product price */
    @FXML
    private TableColumn<Product, Double> ProductPrice;

    /**This is the table that represents the products */
    @FXML
   private TableView<Product> ProductTableView;

    /**This is the table that represents the text field for the search part */
    @FXML
    private TextField filterFieldPart;

    /**This is the button that adds a part*/
    @FXML
    private Button addPartButton;

    /**This is the search bar for products */
    @FXML
    private TextField filterFieldProduct;

    /**This method closes the program
     * @param event on clicking "exit" */
    public void exit(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    /**This method switches to the add part screen
     * @throws IOException throws IO exception
     * @param event on "add" button*/
    public void switchToAddPart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-part.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This method switches to the add product screen
     * @param event on "add" button
     * @throws IOException throws IO excetion*/
    public void switchToAddProduct(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-product.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This method switches to the modify part screen
     * @param event on user clicking "modify"
     * @throws IOException throws IO exception*/
    public void switchToModifyPart(ActionEvent event) throws IOException {
        if (!MainPartTableView.getSelectionModel().isEmpty()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-part.fxml"));
            root = loader.load();

            Part selectedPart = MainPartTableView.getSelectionModel().getSelectedItem();

            modifyPartController modifyPartController = loader.getController();
            modifyPartController.setAllTextFields(selectedPart);
            modifyPartController.getOldIndex(selectedPart);
            modifyPartController.getOldId(selectedPart);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a part");
            alert.setHeaderText("No Part was selected");
            alert.setContentText("Please select a part and try again.");
            alert.showAndWait();
        }
    }

    /**This method switches to the modify product screen
     * @param event on clicking "modify"
     * @throws IOException throws IOException*/
    public void switchToModifyProduct(ActionEvent event) throws IOException {
        if (!ProductTableView.getSelectionModel().isEmpty()) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("modify-product.fxml"));
            root = loader.load();

            Product selectedProduct = ProductTableView.getSelectionModel().getSelectedItem();


            modifyProductController modifyProductController = loader.getController();
            modifyProductController.setProductParts(selectedProduct);
            modifyProductController.setAllTextFields(selectedProduct);
            modifyProductController.getOldIndex(selectedProduct);
            modifyProductController.getOldId(selectedProduct);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a product");
            alert.setHeaderText("No Product was selected");
            alert.setContentText("Please select a product and try again.");
            alert.showAndWait();
        }
    }



    /**This method deletes a part from the part list.
     * @param actionEvent on clicking "delete"
     * @throws IOException throws IOException*/
    public void deletePart(ActionEvent actionEvent) throws IOException {

        if (MainPartTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a part");
            alert.setHeaderText("No Part was selected");
            alert.setContentText("Please select a part and try again.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting Item", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Deleting Item");
            alert.setHeaderText("You are about to delete this selected part.");
            alert.setContentText("Are you sure you want to delete this part?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                for(int i = 0; i < Inventory.getAllProducts().size(); i++){
                    Inventory.getAllProducts().get(i).getAllAssociatedParts().removeAll(MainPartTableView.getSelectionModel().getSelectedItem());
                }
                Inventory.deletePart(MainPartTableView.getSelectionModel().getSelectedItem());

            }
        }

    }

    /**This method deletes product from product list.
     * RUNTIME ERROR: Initially, I had another line of code that deleted the product from the tableview,
     * and then from the product list in inventory.  This caused two products to be deleted from table view, since the
     * selected Part shifted when the first one was deleted.
     * @param actionEvent on clicking "delete"
     * @throws IOException throws IO exception*/
    public void deleteProduct(ActionEvent actionEvent) throws IOException {

        if (ProductTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a product");
            alert.setHeaderText("No product was selected");
            alert.setContentText("Please select a product and try again.");
            alert.showAndWait();
        }
        else if (ProductTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting Product", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Deleting Product");
            alert.setHeaderText("You are about to delete this selected product.");
            alert.setContentText("Are you sure you want to delete this product?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Inventory.deleteProduct(ProductTableView.getSelectionModel().getSelectedItem());
            }
        }

        else if (!ProductTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Associated parts detected");
            alert.setHeaderText("Cannot delete a product with associated parts.");
            alert.setContentText("Please delete associated products and try again.");
            alert.showAndWait();
        }

    }


    @Override
    /**This initializes both tables on the main form, including when they're being searched */
    public void initialize(URL location, ResourceBundle resourceBundle) {
        PartID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartInventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));


        MainPartTableView.setItems(Inventory.getAllParts());

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


        SortedList<Part> sortedPart = new SortedList<>(filteredPart);


        sortedPart.comparatorProperty().bind(MainPartTableView.comparatorProperty());

        MainPartTableView.setItems(sortedPart);


        ProductID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        ProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        ProductInventory.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        ProductPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        
        

        ProductTableView.setItems(Inventory.getAllProducts());

        FilteredList<Product> filteredProduct = new FilteredList<>(Inventory.getAllProducts(), p -> true);

       
        filterFieldProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProduct.setPredicate(myProduct -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }



                
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myProduct.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    

                } else if (String.valueOf(myProduct.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }

                return false; 
            });
        });

        
        SortedList<Product> sortedProduct = new SortedList<>(filteredProduct);

        
        sortedProduct.comparatorProperty().bind(ProductTableView.comparatorProperty());
        
        ProductTableView.setItems(sortedProduct);


    }
}
