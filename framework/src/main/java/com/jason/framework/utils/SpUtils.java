package com.jason.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jason.framework.BuildConfig;

public class SpUtils {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private volatile static SpUtils mInstance = null;

    private SpUtils() {

    }

    public static SpUtils getInstance() {
        if (mInstance == null) {
            synchronized (SpUtils.class) {
                if (mInstance == null) {
                    mInstance = new SpUtils();
                }
            }
        }
        return mInstance;
    }

    public void initSp(Context context) {
        sp = context.getSharedPreferences(BuildConfig.SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void putInt(String key, int values) {
        editor.putInt(key, values);
        editor.commit();
    }

    public int getInt(String key, int defValues) {
        return sp.getInt(key, defValues);
    }

    public void putString(String key, String values) {
        editor.putString(key, values);
        editor.commit();
    }

    public String getString(String key, String defValues) {
        return sp.getString(key, defValues);
    }

    public void putBoolean(String key, Boolean values) {
        editor.putBoolean(key, values);
        editor.commit();
    }

    public boolean getBoolean(String key, Boolean defValues) {
        return sp.getBoolean(key, defValues);
    }

    public void deleteKey(String key) {
        editor.remove(key);
        editor.commit();
    }
}
