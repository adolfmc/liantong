package com.tydic.softphone.tydic_tracker.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class KqwNetworkUtil {
    public static final int NETWORN_2G = 2;
    public static final int NETWORN_3G = 3;
    public static final int NETWORN_4G = 4;
    public static final int NETWORN_MOBILE = 5;
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final String NET_2G = "2G";
    public static final String NET_3G = "3G";
    public static final String NET_4G = "4G";
    public static final String NET_MOBILE = "其他手机网络";
    public static final String NET_NONE = "没有联网";
    public static final String NET_WIFI = "WIFI";

    public static String getNetworkState(Context context) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return "没有联网";
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo == null || (state = networkInfo.getState()) == null || !(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo2 != null) {
                NetworkInfo.State state2 = networkInfo2.getState();
                String subtypeName = networkInfo2.getSubtypeName();
                if (state2 != null) {
                    if (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING) {
                        switch (activeNetworkInfo.getSubtype()) {
                            case 1:
                            case 2:
                            case 4:
                            case 7:
                            case 11:
                                return "2G";
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                            case 10:
                            case 12:
                            case 14:
                            case 15:
                                return "3G";
                            case 13:
                                return "4G";
                            default:
                                return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? "3G" : "其他手机网络";
                        }
                    }
                    return "没有联网";
                }
                return "没有联网";
            }
            return "没有联网";
        }
        return "WIFI";
    }
}
