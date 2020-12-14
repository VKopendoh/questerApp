package com.vkopendoh.questerapp.users.repository;

import com.vkopendoh.questerapp.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Vladimir Kopendoh
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(String userId);

}
