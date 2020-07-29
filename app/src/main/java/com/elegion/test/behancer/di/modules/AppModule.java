package com.elegion.test.behancer.di.modules;


import androidx.room.Room;

import com.elegion.test.behancer.AppDelegate;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.data.database.BehanceDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private AppDelegate mApp;

    public AppModule(AppDelegate mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    AppDelegate providerApp(){
        return mApp;
    }

    @Provides
    @Singleton
    Storage providerStorage(){
        final BehanceDatabase database =
                Room.databaseBuilder(mApp, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();

        return new Storage(database.getBehanceDao());
    }

}
