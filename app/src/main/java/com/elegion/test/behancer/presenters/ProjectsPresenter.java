package com.elegion.test.behancer.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.common.BasePresenter;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.views.ProjectsRefreshView;
import com.elegion.test.behancer.utils.ApiUtils;
import com.elegion.test.behancer.data.Storage;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vladislav Falzan.
 */

@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsRefreshView> {

    @Inject
    Storage mStorage;

    @Inject
    BehanceApi mApi;

    @Inject
    public ProjectsPresenter(Storage storage) {
        mStorage = storage;
    }

    public void getProjects() {
        mCompositeDisposable.add(
                mApi.getProjects(BuildConfig.API_QUERY)
                        .subscribeOn(Schedulers.io())
                        .doOnSuccess(mStorage::insertProjects)
                        .onErrorReturn(throwable ->
                                ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> getViewState().showRefresh())
                        .doFinally(getViewState()::hideRefresh)
                        .subscribe(
                                response -> getViewState().showProjects(response.getProjects()),
                                throwable -> getViewState().showError())
        );
    }

    public void getProjects(String user){
        mCompositeDisposable.add(
                mApi.getUserProjectsInfo(user)
                        .subscribeOn(Schedulers.io())
                        .doOnSuccess(mStorage::insertProjects)
                        .onErrorReturn(throwable ->
                                ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getProjects() : null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showRefresh())
                .doFinally(getViewState()::hideRefresh)
                .subscribe(
                        response -> getViewState().showProjects(response.getProjects()),
                        throwable -> getViewState().showError())
        );

    }

    public void openProfileFragment(String username) {
        getViewState().openProfileFragment(username);
    }
}
