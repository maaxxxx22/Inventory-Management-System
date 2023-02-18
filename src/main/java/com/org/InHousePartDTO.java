package com.org;

/**
 * @author Okunta Braide
 */

/** This Class is created for the InHouse object which extends Part, returns the machine id. */

public class InHousePartDTO extends PartDTO {

    private static int machineID;


    public InHousePartDTO(int id, String name, double price, int stock, int min, int max, int machineID){
        super(id,name,price,stock,min,max);
        this.machineID=machineID;
    }



    public void setMachineID(int machineID){
        this.machineID = machineID;
    }
    public static int getMachineID(){
        return machineID;
    }
}

