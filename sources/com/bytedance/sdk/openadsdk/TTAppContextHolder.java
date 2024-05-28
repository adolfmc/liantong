package com.bytedance.sdk.openadsdk;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import com.bytedance.sdk.openadsdk.api.C3972mb;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TTAppContextHolder {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: mb */
    private static volatile Context f9484mb;

    public static Context getContext() {
        if (f9484mb == null) {
            setContext(null);
        }
        return f9484mb;
    }

    public static synchronized void setContext(Context context) {
        synchronized (TTAppContextHolder.class) {
            if (f9484mb == null) {
                if (context != null) {
                    f9484mb = context.getApplicationContext();
                } else if (C3968mb.m16572mb() != null) {
                    try {
                        f9484mb = C3968mb.m16572mb();
                        if (f9484mb != null) {
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.TTAppContextHolder$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3968mb {
        @SuppressLint({"StaticFieldLeak"})

        /* renamed from: mb */
        private static volatile Application f9485mb;

        /* renamed from: mb */
        public static Application m16572mb() {
            return f9485mb;
        }

        static {
            try {
                Object m16571ox = m16571ox();
                f9485mb = (Application) m16571ox.getClass().getMethod("getApplication", new Class[0]).invoke(m16571ox, new Object[0]);
                C3972mb.m16552hj("MyApplication", "application get success");
            } catch (Throwable th) {
                C3972mb.m16543ox("MyApplication", "application get failed", th);
            }
        }

        /* renamed from: ox */
        private static Object m16571ox() {
            try {
                Method method = Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]);
                method.setAccessible(true);
                return method.invoke(null, new Object[0]);
            } catch (Throwable th) {
                C3972mb.m16543ox("MyApplication", "ActivityThread get error, maybe api level <= 4.2.2", th);
                return null;
            }
        }
    }
}
