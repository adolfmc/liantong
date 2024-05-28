package com.king.zxing.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public enum FrontLightMode {
    ON,
    AUTO,
    OFF;

    private static FrontLightMode parse(String str) {
        return str == null ? AUTO : valueOf(str);
    }

    public static FrontLightMode readPref(SharedPreferences sharedPreferences) {
        return parse(sharedPreferences.getString("preferences_front_light_mode", AUTO.toString()));
    }

    public static void put(Context context, FrontLightMode frontLightMode) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("preferences_front_light_mode", frontLightMode.toString()).commit();
    }
}
