package com.vkopendoh.questerapp.quest.service;

import com.vkopendoh.questerapp.quest.entity.Quest;
import com.vkopendoh.questerapp.quest.repository.QuestRepository;
import com.vkopendoh.questerapp.quest.shared.QuestDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Vladimir Kopendoh
 */
@Service
public class QuestServiceImpl implements QuestService {
    private final QuestRepository questRepository;

    @Autowired
    public QuestServiceImpl(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public QuestDto createQuest(QuestDto questDto) {
        questDto.setQuestId(UUID.randomUUID().toString());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Quest quest = modelMapper.map(questDto, Quest.class);
        questRepository.save(quest);

        return modelMapper.map(quest, QuestDto.class);
    }

    @Override
    public List<Quest> getAlbums(String id) {
        return questRepository.findAllByUserId(id);
    }
}
