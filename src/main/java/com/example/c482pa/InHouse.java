package com.example.c482pa;

/** This class represents all of the parts that were manufactured in house. */
public class InHouse extends Part{
    private int machineId;

    /**This is the constructor for when the ID is provided on the modify part form
     * @param id application remembers the randomized id when part was made
     * @param machineId enter the machine ID
     * @param max enter the max
     * @param min enter the min
     * @param name enter the name
     * @param price enter the price
     * @param stock enter amount in stock*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**This is the constructor for when the ID is not provided on the add part form
     * id application remembers the randomized id when part was made
     *       @param machineId enter the machine ID
     *       @param max enter the max
     *       @param min enter the min
     *       @param name enter the name
     *       @param price enter the price
     *       @param stock enter amount in stock*/
    public InHouse(String name, double price, int stock, int min, int max, int machineId){
        super(name, price, stock, min, max);
        this.machineId=machineId;
    }

    /**Getter for machine ID
     * @return returns machine ID */
    public int getMachineId() {
        return machineId;
    }

    /**Setter for machine ID
     * @param machineId machine ID to be set */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
