package com.jimmy.emulatorcheck;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jimmy on 2017/9/5.
 */
public class MyApplication extends Application {

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }
}
