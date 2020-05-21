package com.darien.androidloginregistration.UseCases;

public class Validator {
    public static boolean isEmail(String email){
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean isName(String name){
        String regex = "^[a-zA-Z_ ]{2,30}$";
        return name.matches(regex);
    }

    public static boolean isCardNumber(String number){
        String regex = "^[0-9]{16}";
        return number.matches(regex);
    }

    public static boolean isRegNumber(String number){
        String regex = "^[0-9]{6}";
        return number.matches(regex);
    }

    public static boolean isPhoneNumber(String text){
        String regex = "^[0-9]{10}";
        return text.matches(regex);
    }
    public static boolean isPassword(String password){
        String regex = "^(?=.*[A-Z])(?=.*[0-9]).*$";
        return password.matches(regex);
    }
}
