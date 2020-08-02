package com.elegion.test.behancer.di.components;


import com.elegion.test.behancer.di.customs_scopes.FragmentScope;
import com.elegion.test.behancer.di.modules.PresenterModule;
import com.elegion.test.behancer.ui.ProfileFragment;
import com.elegion.test.behancer.ui.ProjectsFragment;
import com.elegion.test.behancer.ui.UserProjectsFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {PresenterModule.class})
public interface FragmentComponent {

    void inject(ProjectsFragment projectsFragment);

    void inject(ProfileFragment profileFragment);

    void inject(UserProjectsFragment userProjectsFragment);
}
