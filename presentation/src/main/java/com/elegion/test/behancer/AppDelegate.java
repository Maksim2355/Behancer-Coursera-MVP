package com.elegion.test.behancer;

import android.app.Application;

import com.elegion.test.behancer.Navigation.RoutingFragment;
import com.elegion.test.behancer.di.components.ActivityComponent;
import com.elegion.test.behancer.di.components.AppComponent;
import com.elegion.test.behancer.di.components.DaggerAppComponent;
import com.elegion.test.behancer.di.components.FragmentComponent;
import com.elegion.test.behancer.di.modules.AppModule;
import com.elegion.test.behancer.di.modules.NetworkModule;
import com.elegion.test.behancer.di.modules.PresenterModule;
import com.elegion.test.behancer.di.modules.RepositoryModule;
import com.elegion.test.behancer.di.modules.RoutingModule;
import com.elegion.test.behancer.di.modules.ServiceModule;

public class AppDelegate extends Application {

    private AppComponent sAppComponent;
    private ActivityComponent sActivityComponent;
    private FragmentComponent sFragmentComponent;

    private static AppDelegate instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sAppComponent = DaggerAppComponent.builder()
                .repositoryModule(new RepositoryModule())
                .serviceModule(new ServiceModule())
                .networkModule(new NetworkModule())
                .appModule(new AppModule(this)).build();
    }

    public ActivityComponent startActivityComponent(RoutingFragment routing){
        if (sActivityComponent == null) {
            sActivityComponent = sAppComponent.addActivityComponent(new RoutingModule(routing));
        }
        return sActivityComponent;
    }

    public void stopActivityComponent(){
        sActivityComponent = null;
    }

    public FragmentComponent startFragmentComponent(){
        if (sFragmentComponent == null){
            sFragmentComponent = sActivityComponent.addFragmentComponent(new PresenterModule());
        }
        return sFragmentComponent;
    }

    public void stopFragmentComponent(){
        sFragmentComponent = null;
    }



    public static AppDelegate getInstance(){
        return instance;
    }
}
