package com.megvii.lv5;

import java.util.Timer;
import java.util.TimerTask;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.d1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5413d1 extends TimerTask {

    /* renamed from: a */
    public final /* synthetic */ C5435f1 f12529a;

    public C5413d1(C5435f1 c5435f1) {
        this.f12529a = c5435f1;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        C5435f1 c5435f1 = this.f12529a;
        Timer timer = c5435f1.f12590a;
        if (timer != null) {
            timer.cancel();
            c5435f1.f12590a = null;
        }
        this.f12529a.m13539a(EnumC5548r2.NETWORK_TIME_OUT);
    }
}
