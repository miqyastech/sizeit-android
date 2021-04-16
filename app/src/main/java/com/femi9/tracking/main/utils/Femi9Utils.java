package com.femi9.tracking.main.utils;

import android.app.Activity;

import java.util.HashMap;

public class Femi9Utils {

    private static HashMap<String, Activity> screenStack;

    // Add activity
    public static void addActivities(String actName, Activity _activity) {
        if (screenStack == null)
            screenStack = new HashMap<>();
        if (_activity != null)
            screenStack.put(actName, _activity);
    }

    // Remove Activity
    public static void removeActivity(String key) {
        if (screenStack != null && screenStack.size() > 0) {
            Activity _activity = screenStack.get(key);
            if (_activity != null) {
                screenStack.remove(key);
                _activity.finish();
            }
        }
    }

    // Remove all Activity
    public static void removeAllActivity() {
        if (screenStack != null && screenStack.size() > 0) {
            for (String key : screenStack.keySet()) {
                Activity _activity = screenStack.get(key);
                if (_activity != null) {
                    screenStack.remove(key);
                    _activity.finish();
                }
            }
        }
    }
}
