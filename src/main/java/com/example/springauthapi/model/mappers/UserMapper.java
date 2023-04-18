package com.example.springauthapi.model.mappers;

import com.example.springauthapi.entities.User;

public interface UserMapper<T> {

    User mapToUser(T userData);

}
