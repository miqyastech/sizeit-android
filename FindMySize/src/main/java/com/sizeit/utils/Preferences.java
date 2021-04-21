package com.sizeit.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sizeit.findmysize.model.DataSizes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Preferences {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public Preferences(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public Integer getInt(String key) {
        return getInt(key, 0);
    }

    public Integer getInt(String key, int defaultVal) {
        return sharedPreferences.getInt(key, defaultVal);
    }

    public void putInt(String key, Integer value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    public boolean isArabic() {
        return getString(Constants.language).equals(Constants.language_arabic);
    }

    public void clear() {
        editor.clear().apply();
    }

    public void setSizesList(List<DataSizes> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(Constants.sizes, json);
        editor.commit();
    }

    public List<DataSizes> getSizesList() {
        List<DataSizes> arrayItems = new ArrayList<>();
        String serializedObject = sharedPreferences.getString(Constants.sizes, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<DataSizes>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }
        return arrayItems;
    }
}