package com.bytedance.pangle.download;

import android.os.Handler;
import android.os.Looper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.download.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3800b {

    /* renamed from: e */
    private static volatile C3800b f9095e;

    /* renamed from: a */
    final Map<String, Long> f9096a = new ConcurrentHashMap();

    /* renamed from: b */
    public final Handler f9097b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    public final Map<String, Runnable> f9098c = new ConcurrentHashMap();

    /* renamed from: d */
    final Map<String, Runnable> f9099d = new ConcurrentHashMap();

    private C3800b() {
    }

    /* renamed from: a */
    public static C3800b m16911a() {
        if (f9095e == null) {
            synchronized (C3800b.class) {
                if (f9095e == null) {
                    f9095e = new C3800b();
                }
            }
        }
        return f9095e;
    }
}
