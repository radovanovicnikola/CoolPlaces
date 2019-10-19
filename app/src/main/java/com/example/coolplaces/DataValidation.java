package com.example.coolplaces;

import android.util.Patterns;

public class DataValidation {
    static boolean isValidEmail(String email)
    {
            if (email == null)
                return false;
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    static boolean isValidPass(String password){
        return password.length()>=8;
    }
}
