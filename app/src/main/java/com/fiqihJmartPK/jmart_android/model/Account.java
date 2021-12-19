package com.fiqihJmartPK.jmart_android.model;
import java.util.regex.*;

/**
 * Objek kelas account sebagai akun yang akan digunakan pada jmart
 *
 * @author Muhammad Fiqih Arahman
 * @version (a version number or a date)
 */
public class Account extends Serializable
{
    // instance variables
    public double balance;
    public String name;
    public String email;
    public String password;
    public Store store;
    public final String REGEX_EMAIL = "(?!.*[@]{2})(?!.*[.]{2})[A-Za-z0-9.&_*~]{5,}[@]{1}[A-Za-z0-9]{3,}[.]{1}[A-Za-z0-9-]{3,}$";
    public final String REGEX_PASSWORD = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{8,}$";

    /**
     * @description constructor account sebagai inisialisasi nilai awal saat objek dibentuk
     * @param name sebagai account name
     * @param email sebagai account email
     * @param password sebagai account password
     * @param balance sebagai account balance
     */
    public Account(String name, String email, String password, double balance){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    /**
     * @description method untuk mendisplay nilai2 variabel yang telah diinisialisasi dari objek
     * @return string untuk display nilai variabel
     */
    public String toString(){
        return "name: " + this.name + "\nemail: " + this.email + "\npassword: " + this.password;
    }

    /**
     * @description method untuk mengecek email dan password sesuai regex yang telah dibentuk
     * @return boolean true jika email & password sesuai regex, false jika tidak
     */
    public boolean validate() {
        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
        Pattern patternPassword = Pattern.compile(REGEX_PASSWORD);
        Matcher matcherEmail = patternEmail.matcher(email);
        Matcher matcherPassword = patternPassword.matcher(password);

        boolean matchEmail = matcherEmail.find();
        boolean matchPassword = matcherPassword.find();

        if (matchEmail == true && matchPassword == true) {
            return true;
        }
        else {
            return false;
        }
    }
}
