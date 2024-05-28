package com.baidu.p120ar.arplay.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.util.NetUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NetUtils {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.util.NetUtils$NetType */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum NetType {
        WIFI,
        CMNET,
        CMWAP,
        NONE
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo[] allNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (int i = 0; i < allNetworkInfo.length; i++) {
                    if (allNetworkInfo[i] != null && allNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo networkInfo;
        if (context == null || (networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1)) == null) {
            return false;
        }
        return networkInfo.isAvailable();
    }

    public static boolean isMobileConnected(Context context) {
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
        return activeNetworkInfo.getType();
    }

    public static NetType getAPNType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return NetType.NONE;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return NetType.NONE;
        }
        int type = activeNetworkInfo.getType();
        if (type != 0) {
            if (type == 1) {
                return NetType.WIFI;
            }
            return NetType.NONE;
        } else if (activeNetworkInfo.getExtraInfo() != null && activeNetworkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals("cmnet")) {
            return NetType.CMNET;
        } else {
            return NetType.CMWAP;
        }
    }
}
