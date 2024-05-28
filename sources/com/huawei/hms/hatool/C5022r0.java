package com.huawei.hms.hatool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.r0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5022r0 {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    private static String m14517a(int i, String str) {
        switch (i) {
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
                break;
            case 13:
                return "4G";
            default:
                if (!str.equalsIgnoreCase("TD-SCDMA") && !str.equalsIgnoreCase("WCDMA") && !str.equalsIgnoreCase("CDMA2000")) {
                    return str;
                }
                break;
        }
        return "3G";
    }

    /* renamed from: a */
    public static String m14516a(Context context) {
        NetworkInfo activeNetworkInfo;
        String str;
        String str2;
        if (context == null || context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
            C5029v.m14451f("hmsSdk", "not have network state phone permission!");
            return "";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return "";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "WIFI";
        }
        if (activeNetworkInfo.getType() == 0) {
            String subtypeName = activeNetworkInfo.getSubtypeName();
            C5029v.m14455c("hmsSdk", "Network getSubtypeName : " + subtypeName);
            return m14517a(activeNetworkInfo.getSubtype(), subtypeName);
        } else if (activeNetworkInfo.getType() == 16) {
            C5029v.m14451f("hmsSdk", "type name = COMPANION_PROXY");
            return "COMPANION_PROXY";
        } else {
            if (activeNetworkInfo.getType() == 9) {
                str = "ETHERNET";
                str2 = "type name = ETHERNET";
            } else {
                str = "OTHER_NETWORK_TYPE";
                str2 = "type name = " + activeNetworkInfo.getType();
            }
            C5029v.m14455c("hmsSdk", str2);
            return str;
        }
    }
}
