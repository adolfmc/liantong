package com.tydic.softphone.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class NetBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "NetBroadcastReceiver";
    public static NetEvevt evevt;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface NetEvevt {
        void onNetChange(int i);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            NetUtil.init(context);
            int netWorkState = NetUtil.getNetWorkState();
            Log.e("tydic", "网络:" + netWorkState);
            NetEvevt netEvevt = evevt;
            if (netEvevt != null) {
                netEvevt.onNetChange(netWorkState);
            }
        }
    }

    public static void setEvevt(NetEvevt netEvevt) {
        evevt = netEvevt;
    }
}
