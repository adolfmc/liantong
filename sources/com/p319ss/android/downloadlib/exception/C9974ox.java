package com.p319ss.android.downloadlib.exception;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.exception.ox */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9974ox {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.exception.ox$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC9976mb<T> {
        /* renamed from: ox */
        T mo7137ox();
    }

    /* renamed from: mb */
    public static <T> T m7272mb(boolean z, String str, @NonNull InterfaceC9976mb<T> interfaceC9976mb) {
        try {
            return interfaceC9976mb.mo7137ox();
        } catch (Throwable th) {
            if (th instanceof C9973mb) {
                throw th;
            }
            C9971b.m7285mb().m7280mb(z, th, str);
            if (TextUtils.isEmpty(str)) {
                throw th;
            }
            return null;
        }
    }

    /* renamed from: mb */
    public static <T> T m7274mb(InterfaceC9976mb<T> interfaceC9976mb) {
        return (T) m7272mb(true, null, interfaceC9976mb);
    }

    /* renamed from: mb */
    public static void m7273mb(final Runnable runnable) {
        m7274mb(new InterfaceC9976mb<Void>() { // from class: com.ss.android.downloadlib.exception.ox.1
            @Override // com.p319ss.android.downloadlib.exception.C9974ox.InterfaceC9976mb
            /* renamed from: mb */
            public Void mo7137ox() {
                runnable.run();
                return null;
            }
        });
    }
}
