package com.elegion.test.behancer.di.module;

import androidx.room.Room;

import com.elegion.test.behancer.AppDelegate;
import com.lumi.data.database.BehanceDao;
import com.lumi.data.database.BehanceDatabase;


import toothpick.config.Module;

public class AppModule extends Module {

    private final AppDelegate mApp;

    public AppModule(AppDelegate app){
        mApp = app;
        bind(AppDelegate.class).toInstance(app);
        bind(BehanceDao.class).toInstance(provideBehanceDao());
    }

    private BehanceDatabase provideDatabase() {
        return Room.databaseBuilder(mApp, BehanceDatabase.class, "behance_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    private BehanceDao provideBehanceDao(){
        return provideDatabase().getBehanceDao();
    }

}
