package com.example.c482pa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;
/** This class represents the structure and attributes that products in the company's inventory have. */
public class Product {

    /**This is the associated parts list */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**This is the product id */
    private int id;

    /**This is the product name */
    private String name;

    /**This is the product price */
    private double price;

    /**This is the product stock */
    private int stock;

    /**This is the product min */
    private int min;

    /**This is the product max */
    private int max;

    /**This is the upper bound for the random number generator */
    int upperBound = 9999;

    /**Instance for random number generator */
    Random rand = new Random();


    /**Constructor for when making  a new product and new ID must be generated
     * @param name product name
     * @param price product price
     * @param stock product stock
     * @param min product min
     * @param max product max*/
    public Product(String name, double price, int stock, int min, int max) {
        this.id = rand.nextInt((upperBound));
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Constructor for when modifying product and ID stays the same*
     * @param id product id
     * @param name product name
     * @param price product price
     * @param stock product stock
     * @param min product min
     * @param max product max
     */

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Setter for ID
     * @param id id to be set */
    public void setId(int id) {
        this.id = id;
    }

    /**Setter for name
     * @param name name to be set */
    public void setName(String name) {
        this.name = name;
    }

    /**Setter for price
     * @param price price to be set */
    public void setPrice(double price) {
        this.price = price;
    }

    /**Setter for stock
     * @param stock stock to be set*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Setter for min
     * @param min min to be set */
    public void setMin(int min) {
        this.min = min;
    }

    /**Setter for max
     * @param max max to be set */
    public void setMax(int max) {
        this.max = max;
    }

    /**Getter for ID
     * @return returns ID */
    public int getId() {
        return id;
    }

    /**Getter for name
     * @return returns name */
    public String getName() {
        return name;
    }

    /**Getter for price
     * @return returns price */
    public double getPrice() {
        return price;
    }

    /**Getter for stock
     * @return returns stock */
    public int getStock() {
        return stock;
    }

    /**Getter for min
     * @return returns min */
    public int getMin() {
        return min;
    }

    /**Getter for max
     * @return returns max */
    public int getMax() {
        return max;
    }

    /**This adds the associated part
     * @param part part to be added */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**This deletes the associated part
     * @param selectedAssociatedPart part to be deleted
     * @return returns true when deleted */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }

    /**This is the getter for all the associated parts
     * @return returns associated parts */
     public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
     }


}
