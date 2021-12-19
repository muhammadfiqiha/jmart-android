package com.fiqihJmartPK.jmart_android.model;

/**
 * Kelas treasury sebagai pengelola harga
 *
 * @author Muhammad Fiqih Arahman
 * @version (a version number or a date)
 */
public class Treasury
{
    // instance variables - replace the example below with your own
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;

    public double price;
    public double discount;
    /**
     * Constructor for objects of class PriceTag
     * @param price sebagai parameter harga
     */
    public Treasury(double price)
    {
        this.price = price;
        this.discount = 0.0;
    }

    /**
     * constructor pricetag sebagai inisialisasi nilai awal
     * @param price sebagai parameter harga
     * @param discount sebagai parameter discount
     */
    public Treasury(double price, double discount)
    {
        // initialise instance variables
        this.price = price;
        this.discount = discount;
    }


    /**
     * method untuk mendapatkan harga setelah penyesuaian (dengan adminfee dan harga diskon)
     * @param price sebagai parameter harga
     * @param discount sebagai parameter discount
     * @return double sebagai harga penyesuaian
     */
    public static double getAdjustedPrice(double price, double discount)
    {
        // put your code here
        return getDiscountedPrice(price, discount) + getAdminFee(price, discount);
    }

    /**
     * method untuk mendapatkan harga adminfee
     * @param price sebagai parameter harga
     * @param discount sebagai parameter discount
     * @return double sebagai total adminfee
     */
    public static double getAdminFee(double price, double discount)
    {
        double discountedPrice = getDiscountedPrice(price, discount);
        if (discountedPrice < BOTTOM_FEE)
            return BOTTOM_FEE;
        return discountedPrice - (price * COMMISSION_MULTIPLIER);
    }

    /**
     * method untuk mendapatkan harga diskon
     * @param price sebagai parameter harga
     * @param discount sebagai parameter diskon
     * @return double sebagai total harga setelah didiskon
     */
    private static double getDiscountedPrice(double price, double discount)
    {
        if (discount >= 100.0) {
            return 0.0;
        }
        else {
            double cut = price * discount / 100.0;
            return price - cut;
        }
    }
}
