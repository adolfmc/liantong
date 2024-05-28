package com.bytedance.applog.collector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.applog.C3591h;
import com.bytedance.applog.C3684s;
import com.bytedance.applog.C3704u2;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Collector extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String[] stringArrayExtra = intent.getStringArrayExtra("K_DATA");
        if (stringArrayExtra != null && stringArrayExtra.length > 0) {
            C3591h c3591h = C3591h.f8461A;
            if (c3591h == null) {
                C3684s.m17125a(stringArrayExtra);
                return;
            }
            c3591h.f8477p.removeMessages(4);
            c3591h.f8477p.obtainMessage(4, stringArrayExtra).sendToTarget();
            return;
        }
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
    }
}
