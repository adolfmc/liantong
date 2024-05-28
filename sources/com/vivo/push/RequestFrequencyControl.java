package com.vivo.push;

import android.os.SystemClock;

/* renamed from: com.vivo.push.y */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RequestFrequencyControl {

    /* renamed from: a */
    private volatile long f21254a = -1;

    /* renamed from: a */
    public final synchronized boolean m5318a() {
        boolean z;
        long j = this.f21254a;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        z = j != -1 && elapsedRealtime > j && elapsedRealtime < j + 2000;
        this.f21254a = SystemClock.elapsedRealtime();
        return z;
    }
}
