package com.p343ta.utdid2.device;

import android.content.Context;
import com.p343ta.utdid2.p344a.p345a.C10315g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ta.utdid2.device.UTDevice */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class UTDevice {
    @Deprecated
    public static String getUtdid(Context context) {
        return m6386d(context);
    }

    @Deprecated
    public static String getUtdidForUpdate(Context context) {
        return m6385e(context);
    }

    /* renamed from: d */
    private static String m6386d(Context context) {
        C10326a m6373b = C10327b.m6373b(context);
        return (m6373b == null || C10315g.m6435a(m6373b.m6376f())) ? "ffffffffffffffffffffffff" : m6373b.m6376f();
    }

    /* renamed from: e */
    private static String m6385e(Context context) {
        String m6363h = C10328c.m6372a(context).m6363h();
        return (m6363h == null || C10315g.m6435a(m6363h)) ? "ffffffffffffffffffffffff" : m6363h;
    }
}
