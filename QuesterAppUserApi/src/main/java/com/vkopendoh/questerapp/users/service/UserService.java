package com.vkopendoh.questerapp.users.service;

import com.vkopendoh.questerapp.users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Vladimir Kopendoh
 */
public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);
    UserDto getUserByUserId(String userId);
    List<UserDto> getUsers();
}
