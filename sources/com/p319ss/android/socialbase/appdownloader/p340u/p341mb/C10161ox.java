package com.p319ss.android.socialbase.appdownloader.p340u.p341mb;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.u.mb.ox */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10161ox {
    /* renamed from: mb */
    public static final void m6513mb(C10156hj c10156hj, int i) throws IOException {
        int m6537ox = c10156hj.m6537ox();
        if (m6537ox == i) {
            return;
        }
        throw new IOException("Expected chunk of type 0x" + Integer.toHexString(i) + ", read 0x" + Integer.toHexString(m6537ox) + ".");
    }
}
