package com.vkopendoh.questerapp.users.service;

import com.vkopendoh.questerapp.users.shared.UserDto;

import java.util.List;

/**
 * @author Vladimir Kopendoh
 */
public interface UserService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);
    UserDto getUserByUserId(String userId);
    List<UserDto> getUsers();
}
