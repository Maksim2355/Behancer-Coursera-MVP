package com.elegion.test.behancer.di.components;

import com.elegion.test.behancer.di.modules.AppModule;
import com.elegion.test.behancer.di.modules.NetworkModule;
import com.elegion.test.behancer.ui.ProfileFragment;
import com.elegion.test.behancer.ui.ProjectsFragment;
import com.elegion.test.behancer.ui.UserProjectsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(ProjectsFragment projectsFragment);

    void inject(ProfileFragment profileFragment);

    void inject(UserProjectsFragment userProjectsFragment);
}
