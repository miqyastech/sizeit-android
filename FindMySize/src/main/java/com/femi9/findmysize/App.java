package com.femi9.findmysize;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.femi9.utils.Constants;
import com.femi9.utils.Preferences;
import com.franmontiel.localechanger.LocaleChanger;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class App extends Application {

    public static Preferences preferences;

    public static final List<Locale> SUPPORTED_LOCALES =
            Arrays.asList(
                    new Locale("en", "US"),
                    new Locale("ar", "SA")
            );

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = new Preferences(this);
        LocaleChanger.initialize(getApplicationContext(), SUPPORTED_LOCALES);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.setAutoLogAppEventsEnabled(false);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleChanger.onConfigurationChanged();
    }

    /**
     * set app language
     */
    public static void initializeLanguage(String language) {
        if (language != null && !language.trim().equals("")) {
            if (language.equals(Constants.language_arabic)) {
                LocaleChanger.setLocale(SUPPORTED_LOCALES.get(1));
                preferences.putString(Constants.language,Constants.language_arabic);
            } else {
                LocaleChanger.setLocale(SUPPORTED_LOCALES.get(0));
                preferences.putString(Constants.language,Constants.language_english);
            }
        } else {
            LocaleChanger.setLocale(SUPPORTED_LOCALES.get(0));
            preferences.putString(Constants.language,Constants.language_english);
        }
    }
}