package com.gmrz.authenticationso.utils;

import android.content.Context;
import android.os.Bundle;
import com.utils.AAID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Configurator {
    public static String KEY_OF_FLAG = AAID.FINGER_ECC.name;
    private static final String METADATA_NAME_MANIFEST = "com.gmrz.authentication.API_KEY";
    public static String SHARED_PREFERENCES_NAME = "deprecated";

    public static boolean isEnableNewAAID(Context context) {
        if (getSharedPrefFlag(context)) {
            return false;
        }
        return foundEnableNewAaidFlagInManifest(context);
    }

    private static boolean getSharedPrefFlag(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getBoolean(KEY_OF_FLAG, false);
    }

    public static void setEnableDeprecatedAAID(Context context) {
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit().putBoolean(KEY_OF_FLAG, true).commit();
    }

    public static void setDisableDeprecatedAAID(Context context) {
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit().clear().commit();
    }

    private static boolean foundEnableNewAaidFlagInManifest(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return false;
            }
            return bundle.containsKey("com.gmrz.authentication.API_KEY");
        } catch (Exception unused) {
            return false;
        }
    }
}
