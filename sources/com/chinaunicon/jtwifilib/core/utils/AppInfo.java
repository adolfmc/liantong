package com.chinaunicon.jtwifilib.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AppInfo {
    public static String getPackageName(Context context) {
        return context.getApplicationContext().getPackageName();
    }

    public static boolean isWifi(Context context) {
        return getAPNType(context) == 1;
    }

    public static int getAPNType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            return 1;
        }
        if (type == 0) {
            int subtype = activeNetworkInfo.getSubtype();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (subtype != 13 || telephonyManager.isNetworkRoaming()) {
                if (subtype == 3 || subtype == 8 || (subtype == 5 && !telephonyManager.isNetworkRoaming())) {
                    return 3;
                }
                if (subtype == 1 || subtype == 2 || subtype != 4) {
                    return 2;
                }
                telephonyManager.isNetworkRoaming();
                return 2;
            }
            return 4;
        }
        return 0;
    }
}
