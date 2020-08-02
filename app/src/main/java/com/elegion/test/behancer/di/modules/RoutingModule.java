package com.elegion.test.behancer.di.modules;

import com.elegion.test.behancer.Navigation.RoutingFragment;
import com.elegion.test.behancer.di.customs_scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class RoutingModule {

    private RoutingFragment routing;

    public RoutingModule(RoutingFragment routing) {
        this.routing = routing;
    }

    @ActivityScope
    @Provides
    public RoutingFragment provideRouting(){
        return routing;
    }
}
