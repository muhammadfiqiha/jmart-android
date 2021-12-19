package com.fiqihJmartPK.jmart_android.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Kelas payment untuk aktivitas pembayaran yang extends ke kelas invoice
 *
 * @author Muhammad Fiqih Arahman
 * @version (version number or date here)
 */
public class Payment extends Invoice
{
    // instance variables
    public int productCount;
    public Shipment shipment;
    public ArrayList<Record> history = new ArrayList<Record>();

    /**
     * constructor payment sebagai inisialisasi nilai awal
     * @param buyerId sebagai parameter id dari pembeli
     * @param productId sebagai id dari produk yang dibeli
     * @param productCount sebagai jumlah produk yang dibeli
     * @param shipment sebagai jenis shipment dari pembelian
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    /**
     * method getTotalPay dari jumlah total harga pembelian
     * @param product sebagai parameter objek produk yang akan dibeli
     * @return double total harga yang harus dibayar
     */
    public double getTotalPay(Product product){
        return product.price * product.discount * productCount;
    }

    /**
     * kelas inner record sebagai record aktivitas payment
     */
    public static class Record{
        public Status status;
        public Date date;
        public String message;

        public Record(Status status, String message){
            this.status = status;
            this.message = message;
        }
    }
}
