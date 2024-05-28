package com.tydic.softphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.huawei.hms.api.FailedBinderCallBack;

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
        if (intent.getAction().equals("com.tydic.softphone.callid")) {
            Log.i("tydic_broadcast", "succ");
            String stringExtra = intent.getStringExtra(FailedBinderCallBack.CALLER_ID);
            int intExtra = intent.getIntExtra("code", -1);
            Log.i("tydic_broadcast", "code:" + String.valueOf(intExtra));
            int intExtra2 = intent.getIntExtra("durationTalk", 0);
            Log.i("tydic_broadcast", "durationTalk:" + intExtra2);
            Log.i("tydic_broadcast", "callid:" + String.valueOf(stringExtra));
            intent.getStringExtra("msg");
            return;
        }
        intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE");
    }

    public static void setEvevt(NetEvevt netEvevt) {
        evevt = netEvevt;
    }
}
