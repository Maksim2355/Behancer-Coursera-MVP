package com.elegion.test.behancer.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.elegion.test.behancer.common.BaseRefreshViewModel;
import com.lumi.domain.model.project.Project;
import com.lumi.domain.service.userProject_service.UserProjectService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserProjectsViewModel extends BaseRefreshViewModel {

    private MutableLiveData<List<Project>> mProjectsUser = new MutableLiveData<>();

    private String mUsername;

    private UserProjectService mUserProjectService;

    @Inject
    public UserProjectsViewModel(UserProjectService userProjectService, String username){
        mUserProjectService = userProjectService;
        mUsername = username;
        update();
    }

    @Override
    public void update() {
        mDisposable = mUserProjectService.getProject(mUsername)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .subscribe(
                        response -> mProjectsUser.postValue(response),
                        throwable -> mIsListVisible.postValue(false));
    }



    public LiveData<List<Project>> getProjectsUser() {
        return mProjectsUser;
    }

}
