package com.vkopendoh.questerapp.users.service;

import com.vkopendoh.questerapp.users.shared.UserDto;

/**
 * @author Vladimir Kopendoh
 */
public interface UserService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);
    UserDto getUserByUserId(String userId);
}
