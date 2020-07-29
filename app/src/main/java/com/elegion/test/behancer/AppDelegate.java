package com.elegion.test.behancer;

import android.app.Application;
import androidx.room.Room;

import com.elegion.test.behancer.data.database.BehanceDatabase;
import com.elegion.test.behancer.data.Storage;
import com.elegion.test.behancer.di.components.AppComponent;
import com.elegion.test.behancer.di.components.DaggerAppComponent;
import com.elegion.test.behancer.di.modules.AppModule;
import com.elegion.test.behancer.di.modules.NetworkModule;

public class AppDelegate extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(){
        return sAppComponent;
    }
}
