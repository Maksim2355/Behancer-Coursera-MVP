package com.lumi.domain.service.user_service;

import com.lumi.domain.model.user.User;

import io.reactivex.Single;

public interface UserService {

    Single<User> getUser(String username);

    void insertUser(User user);

}
