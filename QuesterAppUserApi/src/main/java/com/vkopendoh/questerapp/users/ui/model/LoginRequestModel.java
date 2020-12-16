package com.vkopendoh.questerapp.users.ui.model;

/**
 * @author Vladimir Kopendoh
 */
public class LoginRequestModel {
    public String email;
    public String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
