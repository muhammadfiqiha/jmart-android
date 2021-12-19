package com.fiqihJmartPK.jmart_android.model;

/**
 * Kelas coupon sebagai coupon untuk mengurangi harga dengan beberapa kriteria
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Coupon extends Serializable
{
    /**
     * enum Type untuk daftar tipe coupon
     */
    enum Type {
        DISCOUNT,
        REBATE
    }
    // instance variables
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;

    /**
     * constructor coupon
     * @param name sebagai nama coupon
     * @param code sebagai kode coupon
     * @param type sebagai tipe coupon
     * @param cut sebagai potongan harga coupon
     * @param minimum sebagai minimum harga untuk pemakaian coupon
     */
    public Coupon(String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }

    /**
     * method yang mengembalikan apakah coupon sudah dipakai atau tidak
     * @return boolean isUsed
     */
    public boolean isUsed()
    {
        return this.used;
    }

    /**
     * method untuk mengecek apakah coupon dapat digunakan
     * @param price sebagai parameter harga penjualan
     * @param discount sebagai parameter discount
     * @return boolean true jika dapat digunakan, false jika tidak dapat digunakan
     */
    public boolean canApply(double price, double discount)
    {
        return !used && !(Treasury.getAdjustedPrice(price, discount) < minimum);
    }

    /**
     * method untuk melihat harga yang didapat setelah penggunaan coupon
     * @param price sebagai parameter harga
     * @param discount sebagai parameter discount
     * @return double sebagai nilai harga setelah coupon diapply
     */
    public double apply(double price, double discount){
        used = true;
        double adjustedPrice = Treasury.getAdjustedPrice(price, discount);
        if(type == Type.DISCOUNT){
            if (cut >= 100) return 0.0;
            return adjustedPrice - adjustedPrice * (cut/100);
        }
        else{
            return adjustedPrice - cut;
        }
    }
}
