package com.tydic.softphone.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class NetUtil {
    public static final int NETWORK_MOBILE = 0;
    public static final int NETWORK_NONE = -1;
    public static final int NETWORK_WIFI = 1;
    public static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static int getNetWorkState() {
        if (mContext == null) {
            throw new UnsupportedOperationException("please use NetUtils before init it");
        }
        if (Build.VERSION.SDK_INT < 21) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return -1;
            }
            if (activeNetworkInfo.getType() == 1) {
                return 1;
            }
            return activeNetworkInfo.getType() == 0 ? 0 : -1;
        }
        NetworkInfo activeNetworkInfo2 = ((ConnectivityManager) mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo2 != null && activeNetworkInfo2.isConnected() && activeNetworkInfo2.isAvailable()) {
            return activeNetworkInfo2.getType() == 0 ? 0 : 1;
        }
        return -1;
    }
}
