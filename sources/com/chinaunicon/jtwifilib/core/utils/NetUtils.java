package com.chinaunicon.jtwifilib.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import java.util.UUID;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NetUtils {
    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    public static String getConnectWifiSsid(Context context) {
        return (context != null ? ((WifiManager) context.getSystemService("wifi")).getConnectionInfo() : null).getSSID();
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo networkInfo;
        if (context == null || (networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1)) == null) {
            return false;
        }
        return networkInfo.isAvailable();
    }

    public boolean isMobileConnected(Context context) {
        NetworkInfo networkInfo;
        if (context == null || (networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0)) == null) {
            return false;
        }
        return networkInfo.isAvailable();
    }

    public static int getConnectedType(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return -1;
        }
        return activeNetworkInfo.getType() == 1 ? 1 : 2;
    }

    public static String getFileNameFromUrl(String str) {
        String substring;
        int lastIndexOf = str.lastIndexOf(63);
        if (lastIndexOf > 1) {
            substring = str.substring(str.lastIndexOf(47) + 1, lastIndexOf);
        } else {
            substring = str.substring(str.lastIndexOf(47) + 1);
        }
        if (substring == null || "".equals(substring.trim())) {
            return UUID.randomUUID() + ".apk";
        }
        return substring;
    }

    public static boolean isCMWAPMobileNet(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo networkInfo;
        return (isWifi(context) || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (networkInfo = connectivityManager.getNetworkInfo(0)) == null || !"cmwap".equals(networkInfo.getExtraInfo())) ? false : true;
    }

    public static boolean isWifi(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static int getNetType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }
}
