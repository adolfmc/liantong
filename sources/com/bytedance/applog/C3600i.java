package com.bytedance.applog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.applog.C3730x2;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.bytedance.applog.i */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3600i extends BroadcastReceiver {

    /* renamed from: c */
    public static long f8495c;

    /* renamed from: a */
    public final C3591h f8497a;

    /* renamed from: b */
    public static C3730x2.EnumC3731a f8494b = C3730x2.EnumC3731a.UNKNOWN;

    /* renamed from: d */
    public static AtomicBoolean f8496d = new AtomicBoolean(false);

    public C3600i(C3591h c3591h) {
        this.f8497a = c3591h;
    }

    /* renamed from: a */
    public int m17283a() {
        if (Math.abs(f8495c - System.currentTimeMillis()) > 60000) {
            try {
                f8494b = C3730x2.m17031b(this.f8497a.f8464c);
            } catch (Throwable th) {
                C3704u2.m17108a("U SHALL NOT PASS!", th);
            }
            f8495c = System.currentTimeMillis();
            if (f8496d.compareAndSet(false, true)) {
                try {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    this.f8497a.f8464c.registerReceiver(this, intentFilter);
                } catch (Throwable th2) {
                    C3704u2.m17108a("U SHALL NOT PASS!", th2);
                }
            }
        }
        return f8494b.f8930a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            f8495c = 0L;
        }
    }
}
