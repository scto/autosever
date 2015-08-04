package com.afollestad.autosever;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author Aidan Follestad (afollestad)
 */
public class Util {

    public static boolean isEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("sever_enabled", false);
    }

    public static void isEnabled(Context context, boolean enabled) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean("sever_enabled", enabled).commit();
    }

    @SuppressLint("CommitPrefEdits")
    public static void defaultState(Context context, boolean state) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!prefs.contains("wifi_state"))
            prefs.edit().putBoolean("wifi_state", state).commit();
    }

    public static boolean lastState(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("wifi_state", false);
    }

    public static void lastState(Context context, boolean state) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean("wifi_state", state).commit();
    }
}