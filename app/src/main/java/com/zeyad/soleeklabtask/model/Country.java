package com.zeyad.soleeklabtask.model;

import androidx.annotation.NonNull;

public class Country {

    private String name;
    private String alpha3Code;
    private String flag;

    public Country(String name, String alpha3Code, String flag) {
        this.name = name;
        this.alpha3Code = alpha3Code;
        this.flag = flag;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getAlphaCode() {
        return alpha3Code;
    }

    @NonNull
    public String getFlag() {
        return flag;
    }
}
