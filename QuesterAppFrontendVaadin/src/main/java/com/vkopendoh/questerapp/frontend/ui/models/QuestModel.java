package com.vkopendoh.questerapp.frontend.ui.models;

import java.io.Serializable;

/**
 * @author Vladimir Kopendoh
 */
public class QuestModel implements Serializable {

    private static final long serialVersionUID = 8409624673315431863L;

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
