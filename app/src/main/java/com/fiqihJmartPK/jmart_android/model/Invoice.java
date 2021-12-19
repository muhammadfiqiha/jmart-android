package com.fiqihJmartPK.jmart_android.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Kelas Invoice sebagai invoice dari pembelian
 *
 * @author Muhammad Fiqih Arahman
 * @version (a version number or a date)
 */
public class Invoice extends Serializable
{
    /**
     * enum rating sebagai daftar jenis rating untuk invoice
     */
    public enum Rating{
        BAD, GOOD, NEUTRAL, NONE
    }

    /**
     * enum status sebagai daftar jenis status pengiriman untuk invoice
     */
    public enum Status{
        CANCELLED,
        COMPLAINT,
        DELIVERED,
        FAILED,
        FINISHED,
        ON_PROGRESS,
        ON_DELIVERY,
        WAITING_CONFIRMATION
    }

    //instance variable
    public Date date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;

    /**
     * constructor invoice
     * @param buyerId sebagai id dari pembeli
     * @param productId sebagai id dari product yang dibeli
     */
    protected Invoice(int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.complaintId = -1;
    }

    /**
     * method getTotalPay sebagai harga total yang harus dibayar
     * @param product sebagai parameter objek produk yang akan dibeli
     * @return double sebagai harga total yang harus dibayar
     */
    public double getTotalPay(Product product){
        return product.price * product.discount;
    }
}
