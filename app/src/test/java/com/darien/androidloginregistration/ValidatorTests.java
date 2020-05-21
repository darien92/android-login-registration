package com.darien.androidloginregistration;

import com.darien.androidloginregistration.UseCases.Validator;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTests {
    @Test
    public void testCorrectEmail(){
        Assert.assertTrue(Validator.isEmail("darienlazarog@gmail.com"));
    }

    @Test
    public void testIsName(){
        Assert.assertTrue(Validator.isName("Darien Gonzalez Perez"));
    }

    @Test
    public void testIsCardNumber(){
        Assert.assertTrue(Validator.isCardNumber("1234567890123453"));
    }

    @Test
    public void testIsRegNumber(){
        Assert.assertTrue(Validator.isRegNumber("123456"));
    }

    @Test
    public void testIsPhone(){
        Assert.assertTrue(Validator.isPhoneNumber("1234567890"));
    }


    @Test
    public void testIsPassword(){
        Assert.assertTrue(Validator.isPassword("A1asads2112Aasd@asda"));
    }
}
