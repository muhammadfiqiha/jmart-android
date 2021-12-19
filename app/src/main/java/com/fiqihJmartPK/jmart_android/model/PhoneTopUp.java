package com.fiqihJmartPK.jmart_android.model;

/**
 * kelas phonetopup sebagai kelas untuk top up pulsa hp
 * @author Muhammad Fiqih Arahman
 */
public class PhoneTopUp extends Invoice{
    public String phoneNumber;
    public Status status;

    /**
     * constructor phone top up
     * @param buyerId sebagai id pembeli
     * @param productId sebagai id produk yang dibeli
     * @param phoneNumber sebagai phone number yang akan di top up
     */
    public PhoneTopUp(int buyerId, int productId, String phoneNumber){
        super(buyerId, productId);
        this.phoneNumber = phoneNumber;
    }

    /**
     * method untuk total harga
     * @param product sebagai parameter objek produk yang akan dibeli
     * @return double total harga
     */
    public double getTotalPay(Product product){
        return product.price * product.discount;
    }
}
