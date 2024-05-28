package com.baidu.p120ar.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.NetworkUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class NetworkUtils {
    public static final int NETWORKTYPE_2G = 2;
    public static final int NETWORKTYPE_3G = 3;
    public static final int NETWORKTYPE_4G = 4;
    public static final int NETWORKTYPE_WIFI = 5;

    private NetworkUtils() {
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static boolean isWifiNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 1;
    }

    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static boolean isMobileNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0;
    }

    public static int getActiveNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null) {
            return Integer.MIN_VALUE;
        }
        if (activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0) {
            String subtypeName = activeNetworkInfo.getSubtypeName();
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return 2;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return 3;
                case 13:
                    return 4;
                default:
                    if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                        return 3;
                    }
                    break;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static String getNetworkType(Context context) {
        if (!isNetworkConnected(context) || context == null) {
            return "not_Reachable";
        }
        if (isWifiNetworkConnected(context)) {
            return "wifi";
        }
        int activeNetworkType = getActiveNetworkType(context);
        return activeNetworkType == 2 ? "2G" : activeNetworkType == 3 ? "3G" : activeNetworkType == 4 ? "4G" : "other_network";
    }
}
