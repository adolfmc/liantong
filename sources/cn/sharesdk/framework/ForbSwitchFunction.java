package cn.sharesdk.framework;

import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ForbSwitchFunction {

    /* renamed from: b */
    private static volatile ForbSwitchFunction f2875b;

    /* renamed from: a */
    private boolean f2876a = false;

    /* JADX WARN: Type inference failed for: r0v1, types: [cn.sharesdk.framework.c$1] */
    private ForbSwitchFunction() {
        new Thread() { // from class: cn.sharesdk.framework.c.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    ForbSwitchFunction.this.f2876a = MobSDK.isForb();
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21737b(th);
                }
            }
        }.start();
    }

    /* renamed from: a */
    public static ForbSwitchFunction m21859a() {
        synchronized (ForbSwitchFunction.class) {
            if (f2875b == null) {
                synchronized (ForbSwitchFunction.class) {
                    if (f2875b == null) {
                        f2875b = new ForbSwitchFunction();
                    }
                }
            }
        }
        return f2875b;
    }
}
