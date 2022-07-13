package com.example.c482pa;
/** This class represents the outsourced parts in the company's inventory. */
public class Outsourced extends Part {

    /**This is the company name */
    private String companyName;

    /**This is the constructor for when you modify the part and the id stays the same
     * @param id part id
     * @param name part name
     * @param price part price
     * @param stock part stock
     * @param min part min
     * @param max part max
     * @param companyName part company name*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**This is the constructor for when making a brand new part and a new ID is randomly generated
     * @param name part name
     * @param price part price
     * @param stock part stock
     * @param min part min
     * @param max part max
     * @param companyName part company name*/
    public Outsourced(String name, double price, int stock, int min, int max, String companyName) {
        super(name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**This is the setter for the company name
     * @param companyName the new company name*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**This is the getter for the company name
     * @return returns company name */
    public String getCompanyName() {
        return companyName;
    }
}

