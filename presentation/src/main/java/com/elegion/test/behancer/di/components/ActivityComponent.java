package com.elegion.test.behancer.di.components;

import com.elegion.test.behancer.di.customs_scopes.ActivityScope;
import com.elegion.test.behancer.di.modules.RoutingModule;
import com.elegion.test.behancer.di.modules.PresenterModule;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = RoutingModule.class)
public interface ActivityComponent {

    FragmentComponent addFragmentComponent(PresenterModule presenterModule);

}
