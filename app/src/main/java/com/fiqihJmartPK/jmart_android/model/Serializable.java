package com.fiqihJmartPK.jmart_android.model;
import java.util.HashMap;

/**
 * Method serializable sebagai serial id untuk child classnya
 *
 * @author @netlabui
 * @version (a version number or a date)
 */
public class Serializable implements Comparable<Serializable>
{
    //instance variables
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    public final int id;

    /**
     * constructor serializable sebagai inisialisasi id
     */
    protected Serializable()
    {
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }

    /**
     * method untuk memberi id ke setiap objek pada kelas tertentu
     * @param clazz sebagai parameter kelas
     * @param id sebagai parameter id
     * @param <T> sebagai parameter tipe data generic
     * @return hashmap class sebagai mapping untuk tiap objek/kelas yang inherit ke serializable
     */
    public static <T extends Serializable> Integer setClosingId(Class<T> clazz, int id)
    {
        return mapCounter.put(clazz, id);
    }

    /**
     * method untuk mendapatkan id objek untuk kelas tertentu
     * @param clazz sebagai parameter kelas
     * @param <T> sebagai parameter tipe data generic
     * @return hashmap class sebagai mapping tiap objek/kelas
     */
    public static <T extends Serializable> Integer getClosingId(Class<T> clazz)
    {
        return mapCounter.get(clazz);
    }

    /**
     * method untuk mengecek kesesuaian id dari kelas selain serializable
     * @param other sebagai parameter objek
     * @return boolean true jika id sesuai
     */
    public boolean equals(Object other)
    {
        return other instanceof Serializable && ((Serializable) other).id == id;
    }

    /**
     * method untuk mengecek kesesuaian id dari kelas serializable
     * @param other sebagai parameter objek
     * @return boolean true jika sesuai
     */
    public boolean equals(Serializable other)
    {
        return other.id == id;
    }

    /**
     * method untuk mengecek kesesuaian id dari kelas serializable
     * @param other sebagai parameter objek
     * @return boolean true jika sesuai
     */
    @Override
    public int compareTo(Serializable other)
    {
        return Integer.compare(this.id, other.id);
    }
}
