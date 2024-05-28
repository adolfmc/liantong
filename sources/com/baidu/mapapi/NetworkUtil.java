package com.baidu.mapapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.baidu.mapsdkplatform.comapi.util.SysUpdateObservable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NetworkUtil {
    public static boolean initConnectState() {
        return true;
    }

    public static void updateNetworkProxy(Context context) {
        SysUpdateObservable.getInstance().updateNetworkProxy(context);
    }

    public static boolean isWifiConnected(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            try {
                if (1 == networkInfo.getType()) {
                    return networkInfo.isConnected();
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static NetworkInfo getActiveNetworkInfo(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getCurrentNetMode(Context context) {
        NetworkCapabilities networkCapabilities;
        if (context == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        int i = 4;
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.getType() != 1) {
                if (Build.VERSION.SDK_INT > 29) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    if (connectivityManager == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork())) == null) {
                        return "mobile";
                    }
                    return networkCapabilities.hasTransport(1) ? "WIFI" : networkCapabilities.hasTransport(0) ? "CELLULAR" : networkCapabilities.hasTransport(3) ? "ETHERNET" : networkCapabilities.hasTransport(6) ? "LoWPAN" : networkCapabilities.hasTransport(4) ? "VPN" : networkCapabilities.hasTransport(5) ? "WifiAware" : "mobile";
                }
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    switch (telephonyManager.getNetworkType()) {
                        case 1:
                        case 2:
                            i = 6;
                            break;
                        case 3:
                        case 9:
                        case 10:
                        case 15:
                            i = 9;
                            break;
                        case 4:
                            i = 5;
                            break;
                        case 5:
                        case 6:
                        case 7:
                        case 12:
                            i = 7;
                            break;
                        case 8:
                            i = 8;
                            break;
                        case 11:
                            i = 2;
                            break;
                        case 14:
                            i = 10;
                            break;
                    }
                } else {
                    return Integer.toString(0);
                }
            } else {
                i = 1;
            }
            return Integer.toString(i);
        }
        i = 0;
        return Integer.toString(i);
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            if (isWifiConnected(context)) {
                return true;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || 1 != activeNetworkInfo.getType()) {
                return false;
            }
            return activeNetworkInfo.isConnected();
        } catch (Exception unused) {
            return false;
        }
    }
}
