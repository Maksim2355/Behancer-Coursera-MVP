package com.elegion.test.behancer.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.elegion.test.behancer.BuildConfig;
import com.elegion.test.behancer.adapters.OnItemClickListener;
import com.elegion.test.behancer.common.BaseRefreshViewModel;
import com.lumi.domain.model.project.Project;
import com.lumi.domain.service.project_service.ProjectService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProjectsListViewModel extends BaseRefreshViewModel {

    private MutableLiveData<List<Project>> mProjects = new MutableLiveData<>();
    private MutableLiveData<String> mUsername = new MutableLiveData<>();

    private OnItemClickListener mOnItemClickListener = username -> mUsername.postValue(username);

    private ProjectService mProjectService;

    @Inject
    public ProjectsListViewModel(ProjectService projectService){
        mProjectService = projectService;
        update();
    }


    @Override
    public void update() {
        mDisposable = mProjectService.getProject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mIsLoading.postValue(true))
                .doFinally(() -> mIsLoading.postValue(false))
                .subscribe(
                        response -> mProjects.setValue(response),
                        throwable -> mIsListVisible.postValue(false)
                );
    }

    public LiveData<String> getUserClick() {
        return mUsername;
    }


    public LiveData<List<Project>> getProjects() {
        return mProjects;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void dispatchUsername(){
        mUsername.postValue("");
    }

}
