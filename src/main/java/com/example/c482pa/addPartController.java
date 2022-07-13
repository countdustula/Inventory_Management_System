package com.example.c482pa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

/** This controller controls the scene where the user adds a part to the inventory management system. */
public class addPartController {

    /** This is the stage for the addPartController form. */
    private Stage stage;

    /** This is the scene for the addPartController form.*/
    private Scene scene;

    /** This is the parent for the addPartController form. */
    private Parent root;

    /** This is the first column of the tableview on the main screen that represents PartID */
    @FXML
    private TableColumn<Part, Integer> PartID;

    /** This is the text field where the user enters either the machine ID,
     * or the company name*/
    @FXML
    private TextField AddPartMachineID;

    /** This is the text field where the user adds how many of that part are in stock */
    @FXML
    private TextField AddPartInv;

    /** This is the text field where the user adds the max amount of that part that can be in stock */
    @FXML
    private TextField AddPartMax;

    /**This is the text field where the user adds the minimum amount of that part that can be in stock*/
    @FXML
    private TextField AddPartMin;

    /**This is the text field where the user adds the part's name */
    @FXML
    private TextField AddPartName;

    /**This is the text field where the user adds the part's price */
    @FXML
    private TextField AddPartPrice;

    /**This is the button that the user clicks to save their part */
    @FXML
    private Button save;

    /**This is the label that will changed based on which radio button is seleced:
     * inhouse or outsourced. */
    @FXML
    private Label MachineIDorCompanyName;

    /**These are the radio buttons that can be selected based on if the part is produced in house or outsourced. */
    @FXML
    private RadioButton InHouse, OutSourced;

    /**This method shows an alert if there was an error with adding the part.
     * @param windowText The text for the window.
     * @param headerText The text for the header
     * @param contentText The text for the content of the warning.*/
    public static void showAlert(String windowText, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(windowText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**This method returns true or false depending on if the entered number is an integer.
     * @param number The number that will be tested
     * @return either true or false */
    public static boolean isInt(String number){
        try{
            int test = Integer.parseInt(number);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    /**This method returns true or false depending on if the entered number is an appropriate string for a name
     * or company name.
     * @param test This is the string that will be tested.
     * @return either true or false */
    public static boolean isString(String test) {
        return test.matches("[a-zA-Z' ']+");
    }


    /**This method returns true or false depending on if the entered number is an appropriate double
     * to represent a price.
     * @param number This is the number that will be tested.
     * @return either true or false*/
    public static boolean isDouble(String number){
        try{
            Double test = Double.parseDouble(number);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    /**This method returns true or false depending on if the entered decimal has two decimal places
     * @param test this is the number that will be tested
     * @return either true or false*/
    public static boolean twoDecimalPlaces(Double test){
        if (BigDecimal.valueOf(test).scale() <=2) {
            return true;
        }
        else {return false;}
    }

    /**This method switches back to the main form.
     * @param event user must provide an action for this to occur
     * @throws IOException throws IOException*/
    public void switchToMainForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This method changes the label to "Machine ID" or "Company Name" depending on which
     * radio button is selected
     * @param event on radio button selection*/
    public void changeToCompanyName(ActionEvent event) {
        if (InHouse.isSelected()) {
            MachineIDorCompanyName.setText("Machine ID");
        } else if (OutSourced.isSelected()) {
            MachineIDorCompanyName.setText("Company Name");
        }
    }

    public void exit(ActionEvent event) {
        stage.close();
    }

    /**This method saves the part, and give the part all of the attributes the user put in.
     * FUTURE ENHANCEMENT:  When there is an error when adding a part, the user should be redirected to the add part page,
     * and not the main page.
     * @param event the user must click save for this to occur */
    @FXML
    void save(ActionEvent event) throws IOException {
        if (AddPartInv.getText().trim().isEmpty()
        || AddPartName.getText().trim().isEmpty()
        || AddPartPrice.getText().trim().isEmpty()
        || AddPartMax.getText().trim().isEmpty()
        || AddPartMin.getText().trim().isEmpty()
        || AddPartMachineID.getText().trim().isEmpty()){
            showAlert("Empty Fields", "One or more fields is empty.", "Fill out all fields and try again.");
        }
        else if(!isInt(AddPartInv.getText())){
            showAlert("Invalid Input", "Invalid input for inventory.", "Please enter a whole number and try again.");
        }
        else if(!isInt(AddPartMin.getText())){
            showAlert("Invalid Input.", "Invalid input for Minimum or maximum", "Please enter a whole number and try again");
        }
        else if (!isInt(AddPartMax.getText())){
            showAlert("Invalid Input.", "Invalid input for Minimum or maximum", "Please enter a whole number and try again");
        }
        else if (Integer.parseInt(AddPartInv.getText()) > Integer.parseInt(AddPartMax.getText())){
            showAlert("Inventory exceeds max.", "The inventory entered exceeds the max amount.", "Please enter the correct amount in inventory.");
        }
        else if (Integer.parseInt(AddPartInv.getText()) < Integer.parseInt(AddPartMin.getText())){
            showAlert("Inventory below minimum.", "The inventory entered is less than the minimum amount",
                    "Please enter the correct amount.");
        }
        else if (!isString(AddPartName.getText())){
            showAlert("Invalid input for name.", "The entered name is invalid",
                    "Please enter a name without any numbers or special characters.");
        }
        else if (!isDouble(AddPartPrice.getText()) || !twoDecimalPlaces(Double.valueOf(AddPartPrice.getText())) || Double.valueOf(AddPartPrice.getText()) <= 0){
            showAlert("Invalid price", "The entered price is invalid.",
                    "Please enter a positive price with only two decimal places.");
        }
        else if (!isInt(AddPartMax.getText()) || Integer.valueOf(AddPartMax.getText()) < 0){
            showAlert("Invalid Max", "The entered max is invalid.",
                    "Please enter a whole positive number for the max");
        }
        else if (!isInt(AddPartMin.getText()) || Integer.valueOf(AddPartMin.getText()) < 0){
            showAlert("Invalid min", "The entered min is invalid.",
                    "Please enter a whole positive number for the min.");
        }
        else if(InHouse.isSelected() && !isInt(AddPartMachineID.getText())){
            showAlert("Invalid Machine ID", "The entered machine ID is not valid."
            ,"Please enter a whole number for the machine ID");
        }
        else if(OutSourced.isSelected() && !isString(AddPartMachineID.getText())){
            showAlert("Invalid company name.", "The entered company name is invalid",
                    "Please enter a valid company name made up of only alphabetical characters.");
        }
        else if (InHouse.isSelected()){
            InHouse inHouse = new InHouse(
                    (AddPartName.getText()),
                    Double.parseDouble(AddPartPrice.getText()),
                    Integer.parseInt(AddPartInv.getText()),
                    Integer.parseInt(AddPartMin.getText()),
                    Integer.parseInt(AddPartMax.getText()),
                    Integer.parseInt(AddPartMachineID.getText()));
            Inventory.addPart(inHouse);
            switchToMainForm(event);
        }

        else if (OutSourced.isSelected()){
            Outsourced outsourced = new Outsourced(
                    (AddPartName.getText()),
                    Double.parseDouble(AddPartPrice.getText()),
                    Integer.parseInt(AddPartInv.getText()),
                    Integer.parseInt(AddPartMin.getText()),
                    Integer.parseInt(AddPartMax.getText()),
                    AddPartMachineID.getText());
            Inventory.addPart(outsourced);
            switchToMainForm(event);
        }

        }
    }
