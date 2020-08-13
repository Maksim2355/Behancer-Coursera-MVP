package com.elegion.test.behancer.di.components;

import com.elegion.test.behancer.di.modules.AppModule;
import com.elegion.test.behancer.di.modules.NetworkModule;
import com.elegion.test.behancer.di.modules.RepositoryModule;
import com.elegion.test.behancer.di.modules.RoutingModule;
import com.elegion.test.behancer.di.modules.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, RepositoryModule.class, ServiceModule.class})
public interface AppComponent {

    ActivityComponent addActivityComponent(RoutingModule routingModule);

}
