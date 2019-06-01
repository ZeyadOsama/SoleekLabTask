package com.zeyad.soleeklabtask.model;

import androidx.annotation.NonNull;

public class Country {

    private String name;

    public Country(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
