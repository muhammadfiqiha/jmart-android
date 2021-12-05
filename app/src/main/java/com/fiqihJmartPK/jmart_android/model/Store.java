package com.fiqihJmartPK.jmart_android.model;
import java.util.regex.*;

public class Store {
    public final String REGEX_NAME = "(?!.*[ ]{2})[A-Z][A-Za-z0-9 ]{3,18}[A-Za-z0-9]$";
    public final String REGEX_PHONE = "[0-9]{9,12}";
    public double balance;
    public String name;
    public String address;
    public String phoneNumber;

    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public boolean read(String content){
        return false;
    }

    public boolean validate(){
        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
        Matcher matcherPhone = patternPhone.matcher(phoneNumber);
        boolean matchPhone = matcherPhone.find();

        Pattern patternName = Pattern.compile(REGEX_NAME);
        Matcher matcherName = patternName.matcher(name);
        boolean matchName = matcherName.find();

        if (matchPhone == true && matchName == true) {
            return true;
        }
        else {
            return false;
        }
    }

    public String toString(){
        return "name: " + this.name + "\naddress: " + this.address + "\nphoneNumber: " + this.phoneNumber;
    }
}
