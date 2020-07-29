package com.elegion.test.behancer.di.modules;


import android.content.Context;

import com.elegion.test.behancer.Navigation.RoutingFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationModule {

    @Provides
    RoutingFragment provideRoutingFragment(Context context){
        return (RoutingFragment) context;
    }

}
