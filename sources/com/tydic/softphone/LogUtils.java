package com.tydic.softphone;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class LogUtils {
    public static final String NETSTATE = "ConnectivityManager.CONNECTIVITY_ACTION";
    public static final boolean debug = true;

    public static void log(String str) {
        Log.i("janus", str);
    }
}
