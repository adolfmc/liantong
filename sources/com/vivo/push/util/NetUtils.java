package com.vivo.push.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.x */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class NetUtils {
    /* renamed from: a */
    public static NetworkInfo m5335a(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            LogUtil.m5352a("NetUtils", e);
            return null;
        }
    }
}
