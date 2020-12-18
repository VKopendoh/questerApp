package com.vkopendoh.questerapp.quest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Vladimir Kopendoh
 */
@Entity
public class Quest extends AbstractBaseEntity {

    @Column(nullable = false, unique = true)
    private String questId;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false)
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
