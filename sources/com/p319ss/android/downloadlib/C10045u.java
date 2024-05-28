package com.p319ss.android.downloadlib;

import com.p319ss.android.download.api.config.InterfaceC9810u;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.u */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10045u {

    /* renamed from: mb */
    private static volatile C10045u f19368mb;

    /* renamed from: ox */
    private InterfaceC9810u f19369ox = null;

    private C10045u() {
    }

    /* renamed from: mb */
    public static C10045u m7098mb() {
        if (f19368mb == null) {
            synchronized (C10045u.class) {
                try {
                    if (f19368mb == null) {
                        f19368mb = new C10045u();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f19368mb;
    }

    /* renamed from: ox */
    public InterfaceC9810u m7097ox() {
        return this.f19369ox;
    }
}
