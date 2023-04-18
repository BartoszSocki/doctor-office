package com.example.springauthapi.model.mappers;

import com.example.springauthapi.entities.User;
import com.example.springauthapi.model.dto.UserRegisterDataDto;

public class UserRegisterDataDtoUserMapper implements UserMapper<UserRegisterDataDto> {

    public User mapToUser(UserRegisterDataDto userData) {
        var user = new User();
        user.setUsername(userData.getUsername());

        return user;
    }
}
