package com.elegion.test.behancer.presenters;

import com.elegion.test.behancer.common.BasePresenter;
import com.lumi.domain.service.user_service.UserService;
import com.lumi.domain.utils.ApiUtils;
import com.elegion.test.behancer.views.ProfileRefreshView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileRefreshView> {

    private UserService mUserService;

    @Inject
    public ProfilePresenter(UserService userService) {
        mUserService = userService;
    }

    public void loadProfile(String username){
        mCompositeDisposable.add(
                mUserService.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() -> getViewState().hideRefresh())
                .subscribe(
                        response -> getViewState().bind(response),
                        throwable -> getViewState().showError()
                ));
    }

    public void openUserProjects(String username){
        getViewState().openUserWorks(username);
    }




}
