package com.baidu.rtc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class Connectivity {
    public static final int NETWORK_2G = 3;
    public static final int NETWORK_3G = 4;
    public static final int NETWORK_4G = 5;
    public static final int NETWORK_5G = 6;
    public static final int NETWORK_DISCONNECTED = 0;
    public static final int NETWORK_LAN = 1;
    public static final int NETWORK_SUBTYPE_WIFI_2P4G = 100;
    public static final int NETWORK_SUBTYPE_WIFI_5G = 101;
    public static final int NETWORK_UNKNOWN = -1;
    public static final int NETWORK_WIFI = 2;

    public static boolean isConnectionFast(int i, int i2) {
        if (i == 1) {
            return true;
        }
        if (i == 0) {
            switch (i2) {
                case 1:
                    return false;
                case 2:
                    return false;
                case 3:
                    return true;
                case 4:
                    return false;
                case 5:
                    return true;
                case 6:
                    return true;
                case 7:
                    return false;
                case 8:
                    return true;
                case 9:
                    return true;
                case 10:
                    return true;
                case 11:
                    return false;
                case 12:
                    return true;
                case 13:
                    return true;
                case 14:
                    return true;
                case 15:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    Connectivity() {
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        if (context == null) {
            return null;
        }
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isConnectedWifi(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 1;
    }

    public static boolean isConnectedMobile(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 0;
    }

    public static boolean isConnectedFast(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected() && isConnectionFast(networkInfo.getType(), networkInfo.getSubtype());
    }

    public static int getNetworkType(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();
            if (type == 1) {
                return 2;
            }
            if (type != 0) {
                return -1;
            }
            if (Build.VERSION.SDK_INT < 29 || networkInfo.getSubtype() != 20) {
                switch (networkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 3;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 4;
                    case 13:
                        return 5;
                    default:
                        return -1;
                }
            }
            return 6;
        }
        return 0;
    }

    public static int getNetworkType(Context context) {
        return getNetworkType(getNetworkInfo(context));
    }
}
