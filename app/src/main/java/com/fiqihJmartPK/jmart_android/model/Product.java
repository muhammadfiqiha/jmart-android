package com.fiqihJmartPK.jmart_android.model;

/**
 * Kelas produk sebagai objek dari produk
 *
 * @author Muhammad Fiqih Arahman
 * @version (a version number or a date)
 */
public class Product extends Serializable
{
    // instance variables
    public int accountId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public ProductCategory category;
    public double discount;
    public double price;
    public byte shipmentPlans;

    /**
     * constructor product sebagai inisialisasi nilai awal
     * @param accountId sebagai parameter id dari akun pemilik
     * @param name sebagai nama produk
     * @param weight sebagai berat produk
     * @param conditionUsed sebagai kondisi produk
     * @param price sebagai harga produk
     * @param discount sebagai discount produk
     * @param category sebagai kategori produk
     * @param shipmentPlans sebagai jenis shipment produk
     */
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

    /**
     * @description method untuk mendisplay nilai2 variabel yang telah diinisialisasi dari objek
     * @return string untuk display nilai variabel
     */
    @Override
    public String toString(){
        return "Nama: " + this.name + "\nWeight: " + this.weight + "\nconditionUsed: " + this.conditionUsed + "\nprice: " + this.price + "\ncategory: " + this.category + "\nshipmentPlans: " + this.shipmentPlans + "\naccountId: " + this.accountId;
    }
}
