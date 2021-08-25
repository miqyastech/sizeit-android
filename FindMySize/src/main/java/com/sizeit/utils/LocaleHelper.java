package com.sizeit.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

public class LocaleHelper {

    public static Context setLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();

        configuration.setLayoutDirection(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
        }else {
            configuration.locale = locale;
            context.getResources().updateConfiguration(configuration,
                    context.getResources().getDisplayMetrics());
        }
        configuration.setLocale(locale);

        return context.createConfigurationContext(configuration);
    }

    public static Context onAttach(Context context) {
        return setLocale(context, Preferences.getPreferences(context).getString(Constants.language, Constants.language_arabic));
    }
} 