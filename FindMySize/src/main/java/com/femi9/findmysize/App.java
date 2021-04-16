package com.femi9.findmysize;

import android.app.Application;

import com.femi9.utils.Preferences;

public class App extends Application {

    public static Preferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = new Preferences(this);
    }
}