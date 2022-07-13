package com.example.c482pa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class represents the entire inventory of the company, including parts and products. */
public class Inventory {

    /**This is the list for all the parts */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**This is the list for all the products */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**This adds a part to the list
     * @param newPart the part being added */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**This adds a product to the list
     * @param newProduct product being added */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

//    public static Part lookupPart(int partId) {
//    I found another, more efficient way to utilize the search textfield.
//    The code is found in the mainFormController, addProductController, and modifyProductController.
//    }

//    public static ObservableList<Part> lookUpPart(String partName){
//    I found another, more efficient way to utilize the search textfield.
//    The code is found in the mainFormController, addProductController, and modifyProductController.
//    }

//     public static Product lookupProduct(int productID) {     }
//   I found another, more efficient way to utilize the search textfield.
//    The code is found in the mainFormController, addProductController, and modifyProductController.
//     }

//    public static ObservableList<Product> lookupProduct(int productID){
//    I found another, more efficient way to utilize the search textfield.
//    The code is found in the mainFormController, addProductController, and modifyProductController.
//    }

    /**This updated a part with the revised information by replacing that old part at its index
     * @param index index the old part is located
     * @param selectedPart this is the new part that is replacing the old*/
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**This updated a product with the revised information by replacing that old product at its index
     * @param index index the old product is located
     * @param newProduct this is the new product that is replacing the old*/
     public static void updateProduct(int index, Product newProduct) {
         allProducts.set(index, newProduct);
     }

   /**This deletes a part from the list
    * @param selectedPart the part to be deleted
    * @return true once the part is deleted*/
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    /**This deletes a product fromt he list
     * @param selectedProduct the product to be delted
     * @return true once deleted*/
    public static boolean deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
        return true;
    }

    /**Getter for the part list
     * @return returns the observable list*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**Getter for product list
     * @return returns observable list */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }



}
