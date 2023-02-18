package com.org;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Okunta Braide
 */

/** This Class is created for the inventory of all items. */

public class Inventory {
    public Inventory() {
    }

    public static void setAllParts(ObservableList<PartDTO> allParts) {
        Inventory.allParts = allParts;
    }

    public static void setAllProducts(ObservableList<ProductDT0> allProducts) {
        Inventory.allProducts = allProducts;
    }

    public static ObservableList<PartDTO> getAllParts() {
        return allParts;
    }

    public static ObservableList<ProductDT0> getAllProducts() {

        return allProducts;
    }


    private static ObservableList<PartDTO> allParts = FXCollections.observableArrayList();

    private static ObservableList<ProductDT0> allProducts = FXCollections.observableArrayList();

    /** This method is responsible for adding a part to the list of parts in Inventory. */
    public static void addPart(PartDTO newPart) {
        if(allParts.size() > 2 && newPart.getId() == 1) {
            int id = allParts.get(allParts.size() - 1).getId();
            newPart.setId(++id);
        }
        allParts.add(newPart);
    }

    /** This method is responsible for adding a product to the list of products in Inventory. */
    public static void addProduct(ProductDT0 newProduct) {
        if(allProducts.size() > 2 && newProduct.getId() == 1) {
            int id = allProducts.get(allProducts.size() - 1).getId();
            newProduct.setID(++id);
        }
        allProducts.add(newProduct);

    }

    /** This method will use the id to search for specific parts. */
    public static int lookupPartById(int id) {
        int index = -1;
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    /** This method will use the id to check the specific products. */
    public static int lookupProductById(int id) {
        int index = -1;
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    /** This method updates the parts list with a modified part. */
    public static void updatePart(PartDTO selectedPart) {
        int index = lookupPartById(selectedPart.getId());
        if(index > -1) {
            allParts.set(index,selectedPart);
        }
    }

    /** This method will update the products list. */
    public static void updateProduct(ProductDT0 newProduct) {
        int index = lookupProductById(newProduct.getId());
        if(index > -1) {
            allProducts.set(index,newProduct);
        }
    }
}



