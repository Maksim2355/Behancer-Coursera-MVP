package com.elegion.test.behancer.presenters;

import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.utils.ApiUtils;
import com.elegion.test.behancer.views.ProfileRefreshView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileRefreshView> {

    private Storage mStorage;

    private BehanceApi mApi;

    @Inject
    public ProfilePresenter(Storage mStorage, BehanceApi mApi) {
        this.mStorage = mStorage;
        this.mApi = mApi;
    }

    public void loadProfile(String username){
        mCompositeDisposable.add(
                mApi.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(mStorage::insertUser)
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ?
                                mStorage.getUser(username) :
                                null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(() -> getViewState().hideRefresh())
                .subscribe(
                        response -> getViewState().bind(response.getUser()),
                        throwable -> getViewState().showError()
                ));
    }

    public void openUserProjects(String username){
        getViewState().openUserWorks(username);
    }




}
