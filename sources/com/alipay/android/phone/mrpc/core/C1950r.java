package com.alipay.android.phone.mrpc.core;

import java.io.Closeable;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.r */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1950r {
    /* renamed from: a */
    public static void m21071a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
