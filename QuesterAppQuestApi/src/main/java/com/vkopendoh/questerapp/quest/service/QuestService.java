package com.vkopendoh.questerapp.quest.service;

import com.vkopendoh.questerapp.quest.entity.Quest;
import com.vkopendoh.questerapp.quest.shared.QuestDto;

import java.util.List;

/**
 * @author Vladimir Kopendoh
 */
public interface QuestService {
    QuestDto createQuest(QuestDto questDto);

    List<Quest> getAlbums(String id);
}
