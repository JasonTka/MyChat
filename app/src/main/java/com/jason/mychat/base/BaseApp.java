package com.jason.mychat.base;

import android.app.Application;

import com.jason.framework.Framework;

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Framework.getFramework().initFramework(this);
    }
}
