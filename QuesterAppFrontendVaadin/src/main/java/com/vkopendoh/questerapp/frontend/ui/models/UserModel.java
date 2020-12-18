package com.vkopendoh.questerapp.frontend.ui.models;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vladimir Kopendoh
 */
public class UserModel implements Serializable {

    private static final long serialVersionUID = 3292342413220112764L;

    public UserModel(){}

    private String firstName;
    private String lastName;
    private String email;
    private String userId;

    private List<QuestModel> quests;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<QuestModel> getQuests() {
        return quests;
    }

    public void setQuests(List<QuestModel> quests) {
        this.quests = quests;
    }
}
