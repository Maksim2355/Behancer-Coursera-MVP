package com.lumi.data.repository.user_rep;

import com.lumi.data.api.BehanceApi;
import com.lumi.domain.model.user.Image;
import com.lumi.domain.model.user.User;
import com.lumi.domain.model.user.UserResponse;
import com.lumi.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class UserServerRepository implements UserRepository {

    @Inject BehanceApi mApi;

    @Inject
    public UserServerRepository(){ }

    @Override
    public Single<User> getUser(String username) {
        return mApi.getUserInfo(username).map(new Function<UserResponse, User>() {
            @Override
            public User apply(UserResponse userResponse) throws Exception {
                return userResponse.getUser();
            }
        });
    }

    @Override
    public void insertUser(User user) {
       //no impl
    }
}
