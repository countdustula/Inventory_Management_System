package com.example.c482pa;

import java.util.Random;
/** This class is the abstract form of both in house and outsourced parts.  It lays the foundational
 * attributes for both.*/
abstract class Part {

    /**This is the ID for the part */
    private int id;

    /**This is the name for the part */
    private String name;

    /**This is the price for the part */
    private double price;

    /**This is the stock for the part */
    private int stock;

    /**This is the min for the part */
    private int min;

    /**This is the max for the part */
    private int max;

    /**This is the uppper bound for the random number generator */
    int upperBound = 9999;

    /**New instance for the random number generator */
    Random rand = new Random();

    /**Constructor for when a new ID needs to be generated */
    public Part(String name, double price, int stock, int min, int max) {
        this.id = rand.nextInt(upperBound);
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Constructor for when ID is provided when modifying part */
    public Part(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Setter for ID
     * @param id id being set*/
    public void setId(int id) {
        this.id = id;
    }

    /**Setter for name
     * @param name name being set*/
    public void setName(String name) {
        this.name = name;
    }

    /**Setter for price
     * @param price price being set */
    public void setPrice(double price) {
        this.price = price;
    }

    /**Setter for stock
     * @param stock stock being set*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Setter for min
     * @param min min being set*/
    public void setMin(int min) {
        this.min = min;
    }

    /**Setter for max
     * @param max max to be set */
    public void setMax(int max) {
        this.max = max;
    }

    /**Getter for ID
     * @return returns id */
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
}
