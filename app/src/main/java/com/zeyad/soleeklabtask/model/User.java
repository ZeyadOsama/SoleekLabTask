package com.zeyad.soleeklabtask.model;

import androidx.annotation.NonNull;

public class User {

    private String email;
    private String password;
    private String UID;

    public User(@NonNull String email, @NonNull String password, @NonNull String UID) {
        this.email = email;
        this.password = password;
        this.UID = UID;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public String getUID() {
        return UID;
    }
}
