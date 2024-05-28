package com.mob.tools;

import com.mob.commons.C5868q;
import java.lang.Thread;

/* renamed from: com.mob.tools.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C6119b implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private static Thread.UncaughtExceptionHandler f14979a;

    /* renamed from: b */
    private static volatile boolean f14980b;

    /* renamed from: c */
    private static volatile boolean f14981c;

    /* renamed from: a */
    public static synchronized void m11367a() {
        synchronized (C6119b.class) {
            if (!f14980b && C5868q.f14475h && !f14981c) {
                f14981c = true;
                f14979a = Thread.getDefaultUncaughtExceptionHandler();
                Thread.setDefaultUncaughtExceptionHandler(new C6119b());
            }
        }
    }

    private C6119b() {
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        try {
            MobLog.getInstance().m11342d("UE handled, processing...", new Object[0]);
            MobLog.getInstance().crash(th);
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = f14979a;
            if (uncaughtExceptionHandler2 == null || (uncaughtExceptionHandler2 instanceof C6119b)) {
            }
        } catch (Throwable th2) {
            try {
                MobLog.getInstance().m11341d(th2);
                if (uncaughtExceptionHandler == null) {
                    return;
                }
            } finally {
                uncaughtExceptionHandler = f14979a;
                if (uncaughtExceptionHandler != null && !(uncaughtExceptionHandler instanceof C6119b)) {
                    uncaughtExceptionHandler.uncaughtException(thread, th);
                }
            }
        }
    }
}
