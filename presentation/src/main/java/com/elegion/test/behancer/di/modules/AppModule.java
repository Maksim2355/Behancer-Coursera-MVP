package com.elegion.test.behancer.di.modules;

import androidx.room.Room;
import com.elegion.test.behancer.AppDelegate;
import com.lumi.data.database.BehanceDao;
import com.lumi.data.database.BehanceDatabase;

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
    public AppDelegate providerApp(){
        return mApp;
    }

    @Provides
    @Singleton
    public BehanceDatabase provideDatabase(){
        return Room.databaseBuilder(mApp, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public BehanceDao provideBehanceDao(BehanceDatabase database){
        return database.getBehanceDao();
    }

}
