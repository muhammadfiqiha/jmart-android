package com.fiqihJmartPK.jmart_android.model;
import java.util.regex.*;

public class Account extends Serializable{
    // instance variables - replace the example below with your own
    public double balance;
    public String name;
    public String email;
    public String password;
    public Store store;
    public final String REGEX_EMAIL;
    public final String REGEX_PASSWORD;

    public Account(String name, String email, String password, double balance){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.REGEX_EMAIL = email;
        this.REGEX_PASSWORD = password;
    }

    public String toString(){
        return "name: " + this.name + "\nemail: " + this.email + "\npassword: " + this.password;
    }

    public boolean validate() {
        Pattern patternEmail = Pattern.compile("(?!.*[@]{2})(?!.*[.]{2})[A-Za-z0-9.&_*~]{5,}[@]{1}[A-Za-z0-9]{3,}[.]{1}[A-Za-z0-9-]{3,}$");
        Pattern patternPassword = Pattern.compile("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{8,}$");
        Matcher matcherEmail = patternEmail.matcher(REGEX_EMAIL);
        Matcher matcherPassword = patternPassword.matcher(REGEX_PASSWORD);

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
