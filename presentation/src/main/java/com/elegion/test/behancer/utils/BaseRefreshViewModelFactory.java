package com.elegion.test.behancer.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.elegion.test.behancer.view_model.ProfileViewModel;
import com.elegion.test.behancer.view_model.ProjectsListViewModel;
import com.elegion.test.behancer.view_model.UserProjectsViewModel;
import com.lumi.domain.service.project_service.ProjectService;
import com.lumi.domain.service.userProject_service.UserProjectService;
import com.lumi.domain.service.user_service.UserService;

import javax.inject.Inject;

public class BaseRefreshViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    //TODO Сделать адекватное наименование констант view model, которые производит данная фабрика
    private static final class ChildBaseRefreshViewModels {
        public static final String PROJECTS_VIEW_MODEL_NAME = "ProjectsListViewModel";
        public static final String PROFILE_VIEW_MODEL_NAME = "ProfileViewModel";
        public static final String USER_PROJECTS_VIEW_MODEL_NAME = "UserProjectsViewModel";
    }

    private UserService mUserService = null;

    private ProjectService mProjectService = null;

    private UserProjectService mUserProjectService = null;

    private String mUsername;

    @Inject
    public BaseRefreshViewModelFactory(ProjectService projectService) {
        mProjectService = projectService;
    }

    @Inject
    public BaseRefreshViewModelFactory(UserService userService, String username) {
        mUserService = userService;
        mUsername = username;
    }

    @Inject
    public BaseRefreshViewModelFactory(UserProjectService userProjectService, String username) {
        mUserProjectService = userProjectService;
        mUsername = username;
    }

    //TODO() Сделать нормальный кейс и решить проблему с выходным значением default
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        String simpleName = modelClass.getSimpleName();
        switch (simpleName){
            case ChildBaseRefreshViewModels.PROFILE_VIEW_MODEL_NAME: {
                return (T) new ProfileViewModel(mUserService, mUsername);
            }case ChildBaseRefreshViewModels.PROJECTS_VIEW_MODEL_NAME: {
                return (T) new ProjectsListViewModel(mProjectService);
            }
            default: return (T) new UserProjectsViewModel(mUserProjectService, mUsername);

        }
    }
}
