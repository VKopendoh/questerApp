package com.vkopendoh.questerapp.quest.ui.model;

import javax.persistence.Column;

/**
 * @author Vladimir Kopendoh
 */
public class QuestResponseModel {
    private String questId;
    private String userId;
    private String name;
    private String description;

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
