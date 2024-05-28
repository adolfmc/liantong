package com.bytedance.pangle.p176d;

import android.os.Handler;
import android.os.Looper;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.MethodUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.d.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3789a {

    /* renamed from: a */
    private static Class f9075a;

    /* renamed from: b */
    private static Object f9076b;

    /* renamed from: a */
    public static final Object m16958a() {
        if (f9076b == null) {
            try {
                synchronized (C3789a.class) {
                    if (f9076b == null) {
                        if (f9075a == null) {
                            f9075a = Class.forName("android.app.ActivityThread");
                        }
                        f9076b = MethodUtils.invokeStaticMethod(f9075a, "currentActivityThread", new Object[0]);
                    }
                    if (f9076b == null && Looper.myLooper() != Looper.getMainLooper()) {
                        final Object obj = new Object();
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.pangle.d.a.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                try {
                                    try {
                                        Object unused = C3789a.f9076b = MethodUtils.invokeStaticMethod(C3789a.f9075a, "currentActivityThread", new Object[0]);
                                        synchronized (obj) {
                                            obj.notify();
                                        }
                                    } catch (Exception e) {
                                        ZeusLogger.errReport("Zeus_pangle", "ActivityThreadHelper main looper invoke currentActivityThread failed.", e);
                                        synchronized (obj) {
                                            obj.notify();
                                        }
                                    }
                                } catch (Throwable th) {
                                    synchronized (obj) {
                                        obj.notify();
                                        throw th;
                                    }
                                }
                            }
                        });
                        if (f9076b == null) {
                            synchronized (obj) {
                                try {
                                    obj.wait(5000L);
                                } catch (InterruptedException e) {
                                    ZeusLogger.errReport("Zeus_pangle", "ActivityThreadHelper currentActivityThread interruptedException failed.", e);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                ZeusLogger.errReport("Zeus_pangle", "ActivityThreadHelper currentActivityThread failed.", e2);
            }
        }
        return f9076b;
    }
}
