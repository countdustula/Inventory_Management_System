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
/** This controller controls the scene where the user can modify a part. */
public class modifyPartController{

    /**This is the stage for the modify part screen */
    private Stage stage;

    /**This is the scene for the modify part scene */
    private Scene scene;

    /**This is the parent for the modify part screen */
    private Parent root;

    /**This is the index of the part that is going to be revised */
    private int OldIndex;

    /**This is the id of the part that is going to be revised */
    private int OldId;

    /**This is the text field for the amount in inventory */
    @FXML
    private TextField AddPartInv;

    /**This is the text field for the machine ID or company name */
    @FXML
    private TextField AddPartMachineID;

    /**This is the text field for the max */
    @FXML
    private TextField AddPartMax;

    /**This is the text field for the min*/
    @FXML
    private TextField AddPartMin;

    /**This is the text field for the name */
    @FXML
    private TextField AddPartName;

    /**This is the text field for the price */
    @FXML
    private TextField AddPartPrice;

    /**Radio button for the part being made in house */
    @FXML
    private RadioButton InHouse;

    /**This is  the label that changes to either machine ID or company name depending on the radio button selection */
    @FXML
    private Label MachineIDorCompanyName;

    /**This is the text field for the ID that's kept the same */
    @FXML
    private TextField ModifyID;

    /**This is the radio button for outsourced */
    @FXML
    private RadioButton OutSourced;

    /**This is the grouping for the radio buttons */
    @FXML
    private ToggleGroup origin;

    /**This is the save button, used to save the changes made to the part. */
    @FXML
    private Button save;

    /**This method gets the old index
     * @param part part being modified */
    public void getOldIndex(Part part){
        this.OldIndex = Inventory.getAllParts().indexOf(part);
    }

    /**This method gets the old ID
     * @param part part being modified*/
    public void getOldId(Part part){
        this.OldId = part.getId();
    }

    /**This method switches to the main form
     * @param event on cancel or save
     * @throws IOException throws IOException*/
    public void switchToMainForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-form.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This method sets all the text fields from the part's attributes
     * @param part product part being modified */
    public void setAllTextFields(Part part) {
        setFieldID(part.getId());
        setFieldInv(part.getStock());
        setFieldName(part.getName());
        setFieldPrice(part.getPrice());
        setFieldMax(part.getMax());
        setFieldMin(part.getMin());
        InHouse.setSelected(part instanceof InHouse);
        OutSourced.setSelected(part instanceof Outsourced);
        if (InHouse.isSelected()) {
            MachineIDorCompanyName.setText("Machine ID");
            setFieldMachineID((com.example.c482pa.InHouse) part);
        }
    else if(OutSourced.isSelected()) {
        MachineIDorCompanyName.setText("Company Name");
        setFieldCompanyName((Outsourced) part);
    }

}

    /**This sets the text field with the field ID
     * @param ID The ID being set */
    public void setFieldID(int ID) {
        ModifyID.setPromptText(String.valueOf(ID));
    }

    /**This sets the text field with the inventory
     * @param inv the inventory being set */
    public void setFieldInv(int inv) {
        AddPartInv.setText(String.valueOf(inv));
    }

    /**This sets text field with the part name
     * @param name the name being set */
    public void setFieldName(String name) {
        AddPartName.setText(name);
    }

    /**This sets the text field with the price
     * @param price the price being set */
    public void setFieldPrice(Double price) {
        AddPartPrice.setText(String.valueOf(price));
    }

    /**This sets the text field with the max
     * @param max the max being set */
    public void setFieldMax(int max){
        AddPartMax.setText(String.valueOf(max));
    }


    /**This sets the text field with the min
     * @param min the min being set */
    public void setFieldMin(int min){
        AddPartMin.setText(String.valueOf(min));
    }

    /**This sets the text field with the machine ID
     * @param inhouse the inhouse part being selected */
    public void setFieldMachineID(InHouse inhouse){
        AddPartMachineID.setText(String.valueOf(inhouse.getMachineId()));
    }

    /**This sets the text field with the company name
     * @param outsourced the outsourced part being selected */
    public void setFieldCompanyName(Outsourced outsourced){
        AddPartMachineID.setText(outsourced.getCompanyName());
    }

    /**This method changes the label to "Machine ID" or "Company name" depending on which radio button is toggled
     * @param event the event being the radio button being selected */
    public void changeToCompanyNameModifyForm(ActionEvent event) {
        if (InHouse.isSelected()) {
            MachineIDorCompanyName.setText("Machine ID");
        } else if (OutSourced.isSelected()) {
            MachineIDorCompanyName.setText("Company Name");
        }
    }

    /**This method saves all of the changes made to the part
     * @param event on clicking "save"
     * @throws IOException throws IOException*/
    @FXML
    void save(ActionEvent event) throws IOException {
        if (AddPartInv.getText().trim().isEmpty()
                || AddPartName.getText().trim().isEmpty()
                || AddPartPrice.getText().trim().isEmpty()
                || AddPartMax.getText().trim().isEmpty()
                || AddPartMin.getText().trim().isEmpty()
                || AddPartMachineID.getText().trim().isEmpty()){
            addPartController.showAlert("Empty Fields", "One or more fields is empty.", "Fill out all fields and try again.");
        }
        else if(!addPartController.isInt(AddPartInv.getText())){
            addPartController.showAlert("Invalid Input", "Invalid input for inventory.", "Please enter a whole number and try again.");
        }else if(!addPartController.isInt(AddPartMin.getText())){
            addPartController.showAlert("Invalid Input.", "Invalid input for Minimum or maximum", "Please enter a whole number and try again");
        }
        else if (!addPartController.isInt(AddPartMax.getText())){
            addPartController.showAlert("Invalid Input.", "Invalid input for Minimum or maximum", "Please enter a whole number and try again");
        }
        else if (Integer.parseInt(AddPartInv.getText()) > Integer.parseInt(AddPartMax.getText())){
            addPartController.showAlert("Inventory exceeds max.", "The inventory entered exceeds the max amount.", "Please enter the correct amount in inventory.");
        }
        else if (Integer.parseInt(AddPartInv.getText()) < Integer.parseInt(AddPartMin.getText())){
            addPartController.showAlert("Inventory below minimum.", "The inventory entered is less than the minimum amount",
                    "Please enter the correct amount.");
        }
        else if (!addPartController.isString(AddPartName.getText())){
            addPartController.showAlert("Invalid input for name.", "The entered name is invalid",
                    "Please enter a name without any numbers or special characters.");
        }
        else if (!addPartController.isDouble(AddPartPrice.getText()) || !addPartController.twoDecimalPlaces(Double.valueOf(AddPartPrice.getText())) || Double.valueOf(AddPartPrice.getText()) <= 0){
            addPartController.showAlert("Invalid price", "The entered price is invalid.",
                    "Please enter a positive price with only two decimal places.");
        }
        else if (!addPartController.isInt(AddPartMax.getText()) || Integer.valueOf(AddPartMax.getText()) < 0){
            addPartController.showAlert("Invalid Max", "The entered max is invalid.",
                    "Please enter a whole positive number for the max");
        }
        else if (!addPartController.isInt(AddPartMin.getText()) || Integer.valueOf(AddPartMin.getText()) < 0){
            addPartController.showAlert("Invalid min", "The entered min is invalid.",
                    "Please enter a whole positive number for the min.");
        }
        else if(InHouse.isSelected() && !addPartController.isInt(AddPartMachineID.getText())){
            addPartController.showAlert("Invalid Machine ID", "The entered machine ID is not valid."
                    ,"Please enter a whole number for the machine ID");
        }
        else if(OutSourced.isSelected() && !addPartController.isString(AddPartMachineID.getText())){
            addPartController.showAlert("Invalid company name.", "The entered company name is invalid",
                    "Please enter a valid company name made up of only alphabetical characters.");
        }
        else if (InHouse.isSelected()){
            InHouse inHouse = new InHouse(
                    (OldId),
                    (AddPartName.getText()),
                    Double.parseDouble(AddPartPrice.getText()),
                    Integer.parseInt(AddPartInv.getText()),
                    Integer.parseInt(AddPartMin.getText()),
                    Integer.parseInt(AddPartMax.getText()),
                    Integer.parseInt(AddPartMachineID.getText()));
            Inventory.updatePart(OldIndex, inHouse);
            switchToMainForm(event);
        }

        else if (OutSourced.isSelected()){
            Outsourced outsourced = new Outsourced(
                    (OldId),
                    (AddPartName.getText()),
                    Double.parseDouble(AddPartPrice.getText()),
                    Integer.parseInt(AddPartInv.getText()),
                    Integer.parseInt(AddPartMin.getText()),
                    Integer.parseInt(AddPartMax.getText()),
                    AddPartMachineID.getText());
            Inventory.updatePart(OldIndex, outsourced);
            switchToMainForm(event);
        }

    }


}
    

