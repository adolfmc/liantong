package com.bytedance.applog;

import android.util.Log;

/* renamed from: com.bytedance.applog.g3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC3590g3 implements Runnable {

    /* renamed from: a */
    public final Runnable f8458a;

    /* renamed from: b */
    public final String f8459b;

    /* renamed from: c */
    public final String f8460c = Log.getStackTraceString(new RuntimeException("origin stacktrace"));

    public RunnableC3590g3(Runnable runnable, String str) {
        this.f8458a = runnable;
        this.f8459b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f8458a.run();
        } catch (Exception e) {
            e.printStackTrace();
            C3578e3.m17305a("TrackerDr", "Thread:" + this.f8459b + " exception\n" + this.f8460c, e);
        }
    }
}
