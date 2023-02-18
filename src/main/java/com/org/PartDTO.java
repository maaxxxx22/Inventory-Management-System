package com.org;

/**
 * @author Okunta Braide
 */

/** The PartDTO class is the supplied part abstract class. */

public abstract class PartDTO {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public PartDTO(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }



    // method that return id

    public int getId() {
        return id;
    }
     //id the id to set

    public void setId(int id) {
        this.id = id;
    }

    // method that get name
    public String getName() {
        return name;
    }

    // method that set name
    public void setName(String name) {
        this.name = name;
    }

    // method that get price
    public double getPrice() {
        return price;
    }

    // method that set price
    public void setPrice(double price) {
        this.price = price;
    }

    // method that get stock
    public int getStock() {
        return stock;
    }

    // method that set stock
    public void setStock(int stock) {
        this.stock = stock;
    }

    // method that get min
    public int getMin() {
        return min;
    }

    // method that set min
    public void setMin(int min) {
        this.min = min;
    }

    // method that get max
    public int getMax() {
        return max;
    }

    // method that set max
    public void setMax(int max) {
        this.max = max;
    }


}


