package com.elegion.test.behancer.presenters;

import com.elegion.test.behancer.common.BasePresenter;
import com.lumi.domain.service.project_service.ProjectService;
import com.lumi.domain.service.userProject_service.UserProjectService;
import com.elegion.test.behancer.views.ProjectsRefreshView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;


@InjectViewState
public class ProjectsPresenter extends BasePresenter<ProjectsRefreshView> {

    private ProjectService mProjectService;
    private UserProjectService mUserProjectService;

    @Inject
    public ProjectsPresenter(ProjectService projectService, UserProjectService userProjectService) {
        mProjectService = projectService;
        mUserProjectService = userProjectService;
    }

    public void getProjects() {
        mCompositeDisposable.add(
                mProjectService.getProject()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> getViewState().showRefresh())
                        .doFinally(getViewState()::hideRefresh)
                        .subscribe(
                                response -> getViewState().showProjects(response),
                                throwable -> getViewState().showError())
        );
    }

    public void getProjects(String user){
        mCompositeDisposable.add(
                mUserProjectService.getProject(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> getViewState().showRefresh())
                        .doFinally(getViewState()::hideRefresh)
                        .subscribe(
                                response -> getViewState().showProjects(response),
                                throwable -> getViewState().showError())
                );

    }

    public void openProfileFragment(String username) {
        getViewState().openProfileFragment(username);
    }
}
