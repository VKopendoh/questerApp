package com.vkopendoh.questerapp.quest.ui.controllers;

import com.vkopendoh.questerapp.quest.entity.Quest;
import com.vkopendoh.questerapp.quest.service.QuestService;
import com.vkopendoh.questerapp.quest.shared.QuestDto;
import com.vkopendoh.questerapp.quest.ui.model.QuestRequestModel;
import com.vkopendoh.questerapp.quest.ui.model.QuestResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vladimir Kopendoh
 */
@RestController
public class QuestController {
    private final QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @PostMapping( value = "/user/{id}/quest",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<QuestResponseModel> createQuest(@RequestBody @Valid QuestRequestModel questDetails, @PathVariable String id) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        QuestDto questDto = modelMapper.map(questDetails,QuestDto.class);
        questDto.setUserId(id);
        QuestDto createdQuest = questService.createQuest(questDto);

        QuestResponseModel responseValue = modelMapper.map(createdQuest, QuestResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseValue);
    }

    @GetMapping( value = "http://quest-ws/user/{id}/quest",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
            })
    public ResponseEntity<List<QuestResponseModel>> userQuests(@PathVariable String id) {

        List<QuestResponseModel> returnValue = new ArrayList<>();

        List<Quest> quests = questService.getAlbums(id);

        if(quests == null || quests.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.OK).body(returnValue);
        }

        Type listType = new TypeToken<List<QuestResponseModel>>(){}.getType();

        returnValue = new ModelMapper().map(quests, listType);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
