package com.fiqihJmartPK.jmart_android.model;

public class Product extends Serializable{

    public int accountId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public ProductCategory category;
    public double discount;
    public double price;
    public byte shipmentPlans;

    public Product(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans)
    {
        this.accountId = accountId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipmentPlans = shipmentPlans;
    }

    public boolean read(String content){
        return false;
    }

    public Object write(){
        return null;
    }

    @Override
    public String toString(){
        return "Nama: " + this.name + "\nWeight: " + this.weight + "\nconditionUsed: " + this.conditionUsed + "\nprice: " + this.price + "\ncategory: " + this.category + "\nshipmentPlans: " + this.shipmentPlans + "\naccountId: " + this.accountId;
    }
}
