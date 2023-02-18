package com.org;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Okunta Braide
 */

/** The ProductDT0 class is a class created for the product object. */

public class ProductDT0 {

    private ObservableList<PartDTO> associatedParts = FXCollections.observableArrayList();
    private ObservableList<ProductDT0> associatedProducts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int prodStock;
    private int prodMin;
    private int prodMax;
    public int allProducts;

    public ProductDT0(int prodId, String prodName, double Price, int Stock, int Min, int Max){
        this.id = prodId;
        this.name = prodName;
        this.price = Price;
        this.prodStock = Stock;
        this.prodMin = Min;
        this.prodMax = Max;
    }

    /** getters and setters for text fields. */

    public int getId(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public int getMin(){
        return prodMin;
    }
    public void setMin(int Min){
        this.prodMin = Min;
    }
    public int getMax(){
        return prodMax;
    }
    public void setMax(int Max){
        this.prodMax = Max;
    }
    public int getStock(){
        return prodStock;
    }
    public void setStock(int Stock){
        this.prodStock = Stock;
    }

    /** methods for the Products. */
    public void addAssociatedPart(PartDTO part){
        associatedParts.add(part);
    }
    public void addAssociatedProduct(ProductDT0 product){
        associatedProducts.add(product);
    }
    public boolean deleteAssociatedPart(PartDTO part){
        associatedParts.remove(part);
        return false;
    }
    public ObservableList<PartDTO> getAllAssociatedParts(){
        return associatedParts;
    }
    public ObservableList<ProductDT0> getAllAssociatedProducts(){
        return associatedProducts;
    }

    public void setAllAssociatedParts(ObservableList<PartDTO> associatedParts) {
        this.associatedParts = associatedParts;
    }
}

