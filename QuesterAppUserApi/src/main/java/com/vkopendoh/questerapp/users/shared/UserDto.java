package com.vkopendoh.questerapp.users.shared;

import com.vkopendoh.questerapp.users.ui.model.QuestResponseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vladimir Kopendoh
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = -8868783019135864968L;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String userId;
    private String encryptedPassword;
    private List<QuestResponseModel> quests;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<QuestResponseModel> getQuests() {
        return quests;
    }

    public void setQuests(List<QuestResponseModel> quests) {
        this.quests = quests;
    }
}
