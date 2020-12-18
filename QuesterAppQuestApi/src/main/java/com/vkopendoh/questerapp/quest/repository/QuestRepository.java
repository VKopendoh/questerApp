package com.vkopendoh.questerapp.quest.repository;

import com.vkopendoh.questerapp.quest.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vladimir Kopendoh
 */
@Repository
public interface QuestRepository extends JpaRepository<Quest,Long> {
    List<Quest> findAllByUserId(String userId);
}
