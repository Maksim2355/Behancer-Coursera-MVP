package com.elegion.test.behancer.di.modules;


import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.api.BehanceApi;
import com.elegion.test.behancer.di.customs_scopes.FragmentScope;
import com.elegion.test.behancer.presenters.ProfilePresenter;
import com.elegion.test.behancer.presenters.ProjectsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @FragmentScope
    @Provides
    public ProfilePresenter provideProfilePresenter(Storage storage, BehanceApi api){
        return new ProfilePresenter(storage, api);
    }

    @FragmentScope
    @Provides
    public ProjectsPresenter provideProjectsPresenter(Storage storage, BehanceApi api){
        return new ProjectsPresenter(storage, api);
    }




}
