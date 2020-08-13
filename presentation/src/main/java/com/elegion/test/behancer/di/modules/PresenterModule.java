package com.elegion.test.behancer.di.modules;



import com.elegion.test.behancer.di.customs_scopes.FragmentScope;
import com.elegion.test.behancer.presenters.ProfilePresenter;
import com.elegion.test.behancer.presenters.ProjectsPresenter;
import com.lumi.domain.service.project_service.ProjectService;
import com.lumi.domain.service.userProject_service.UserProjectService;
import com.lumi.domain.service.user_service.UserService;


import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @FragmentScope
    @Provides
    public ProfilePresenter provideProfilePresenter(UserService userService){
        return new ProfilePresenter(userService);
    }

    @FragmentScope
    @Provides
    public ProjectsPresenter provideProjectsPresenter(ProjectService projectService,
                                                      UserProjectService userProjectService) {
        return new ProjectsPresenter(projectService, userProjectService);
    }
}
