package com.elegion.test.behancer.view_model;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.elegion.test.behancer.common.BaseRefreshViewModel;
import com.lumi.domain.model.user.User;
import com.lumi.domain.service.user_service.UserService;


import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends BaseRefreshViewModel {

    private UserService mUserService;

    private MutableLiveData<Boolean> mIsGoUserProjects = new MutableLiveData<>(false);
    private View.OnClickListener mOnBtnWorksListClickListener = v -> {mIsGoUserProjects.postValue(true);};

    private MutableLiveData<User> mUser = new MutableLiveData<>();

    private final String mUsername;

    @Inject
    public ProfileViewModel(UserService userService, String username) {
        mUserService = userService;
        mUsername = username;
        update();
    }

    @Override
    public void update() {
        mDisposable = mUserService.getUser(mUsername)
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .doOnSuccess(user -> mIsListVisible.postValue(true))
                .subscribeOn(Schedulers.io())
                .subscribe(
                        response -> mUser.postValue(response),
                        throwable -> mIsListVisible.postValue(false)
                );
    }

    public MutableLiveData<User> getUser() {
        return mUser;
    }

    public View.OnClickListener getOnBtnWorksListClickListener() {
        return mOnBtnWorksListClickListener;
    }

    public MutableLiveData<Boolean> getIsGoUserProjects() {
        return mIsGoUserProjects;
    }

    public void dispatchIsGoUserProjectsFragment(){
        mIsGoUserProjects.postValue(false);
    }

}
