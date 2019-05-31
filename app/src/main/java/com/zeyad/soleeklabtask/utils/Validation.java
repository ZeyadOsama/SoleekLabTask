package com.zeyad.soleeklabtask.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Validation {

    public static boolean isPasswordValid(String password) {
        return (!TextUtils.isEmpty(password) && password.length() > 6);

    }

    public static boolean isEmailValid(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
