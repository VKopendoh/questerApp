package com.vkopendoh.questerapp.frontend.ui.models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Vladimir Kopendoh
 */
public class UserCreateModel implements Serializable {
    private static final long serialVersionUID = 7412422102638825577L;

    @NotNull
    @Length(min = 1, max = 32)
    private String firstName;

    @NotNull
    @Length(min = 1, max = 32)
    private String lastName;

    @Email
    private String email;

    @NotNull
    @Length(min = 8, max = 64)
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
