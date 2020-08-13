package com.lumi.domain.repository;

import com.lumi.domain.model.user.User;

import io.reactivex.Single;

public interface UserRepository {

    String DB = "db";
    String SERVER = "server";

    Single<User> getUser(String username);

    void insertUser(User user);

}
