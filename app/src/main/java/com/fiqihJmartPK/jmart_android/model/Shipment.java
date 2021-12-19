package com.fiqihJmartPK.jmart_android.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Kelas shipment sebagai shipment untuk produk
 *
 * @author Muhammad Fiqih Arahman
 * @version (a version number or a date)
 */
public class Shipment {

    //instance variables
    SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    public static final Plan INSTANT  = new Plan((byte) (1<<0));
    public static final Plan SAME_DAY = new Plan((byte) (1<<1));
    public static final Plan NEXT_DAY = new Plan((byte) (1<<2));
    public static final Plan REGULER  = new Plan((byte) (1<<3));
    public static final Plan KARGO    = new Plan((byte) (1<<4));
    public String address;
    public int cost;
    public byte plan;
    public String receipt;

    /**
     * constructor shipment sebagai inisialisasi nilai awal
     * @param address sebagai alamat shipment
     * @param cost sebagai biaya shipment
     * @param plan sebagai jenis shipment
     * @param receipt sebagai resi shipment
     */
    public Shipment(String address, int cost, byte plan, String receipt){
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }

    /**
     * method untuk mengestimasi lama pengiriman
     * @param reference sebagai referensi tanggal awal dikirim
     * @return String tanggal waktu estimasi
     */
    public String getEstimatedArrival(Date reference){
        Calendar cal = Calendar.getInstance();
        if(this.plan == 1<<0|| this.plan == 1<<1){
            return ESTIMATION_FORMAT.format(reference.getTime());
        }
        else if(this.plan == 1<<2){
            cal.setTime(reference);
            cal.add(Calendar.DATE,1);
            return ESTIMATION_FORMAT.format(cal);
        }
        else if(this.plan == 1<<3){
            cal.setTime(reference);
            cal.add(Calendar.DATE,2);
            return ESTIMATION_FORMAT.format(cal);
        }
        else{
            cal.setTime(reference);
            cal.add(Calendar.DATE,5);
            return ESTIMATION_FORMAT.format(cal);
        }
    }

    /**
     * method untuk mengecek durasi dari jenis shipment
     * @param reference sebagai referensi untuk jenis shipment
     * @return boolean true jika bitwise & reference(kelas plan) dan kelas shipment tidak nol
     */
    public boolean isDuration(Plan reference)
    {
        if ((reference.bit & this.plan) != 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * method untuk mengecek durasi dari jenis shipment
     * @param reference sebagai referensi untuk jenis shipment
     * @param object sebagai parameter bit objek (bukan dari kelas shipment)
     * @return boolean true jika bitwise & reference(kelas plan) dan objek tidak nol
     */
    public boolean isDuration(byte object, Plan reference)
    {
        if ((reference.bit & object) != 0){
            return true;
        }
        else {
            return false;
        }
    }

    public static class Plan
    {
        public final byte bit;

        private Plan(byte bit)
        {
            this.bit = bit;
        }
    }

    public class MultiDuration{
        public byte bit;

        public MultiDuration(Plan... args)
        {
            byte save = 0;
            for (Plan x: args){
                save = (byte) (save | x.bit);
            }
            bit = save;
        }
    }

    public boolean read(String content){
        return false;
    }

    public Object write(){
        return null;
    }
}
