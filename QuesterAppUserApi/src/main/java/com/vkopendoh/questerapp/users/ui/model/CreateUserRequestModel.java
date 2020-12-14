package com.vkopendoh.questerapp.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Vladimir Kopendoh
 */
public class CreateUserRequestModel {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "Minimum two letters for first name.")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Minimum two letters for last name.")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8,max = 16,message = "Password must be from 8 to 16 length.")
    private String password;

    @NotNull(message = "Email cannot be null.")
    @Email(message = "Email must be correct.")
    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
