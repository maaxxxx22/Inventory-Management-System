package com.org;

/**
 * @author Okunta Braide
 */

/** This class is for the OutSourcedPartDTO radio button. */

public class OutSourcedPartDTO extends PartDTO {

    private String companyName;

    public OutSourcedPartDTO(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id,name,price,stock,min,max);
        this.companyName=companyName;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public String getCompanyName(){
        return companyName;
    }
}

