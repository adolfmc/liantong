package com.tencent.p348mm.sdk.p354b;

import android.util.Log;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.b.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class C10395c implements C10393b.InterfaceC10394a {
    @Override // com.tencent.p348mm.sdk.p354b.C10393b.InterfaceC10394a
    /* renamed from: b */
    public final int mo6190b() {
        int i;
        i = C10393b.level;
        return i;
    }

    @Override // com.tencent.p348mm.sdk.p354b.C10393b.InterfaceC10394a
    /* renamed from: d */
    public final void mo6189d(String str, String str2) {
        int i;
        i = C10393b.level;
        if (i <= 2) {
            Log.i(str, str2);
        }
    }

    @Override // com.tencent.p348mm.sdk.p354b.C10393b.InterfaceC10394a
    /* renamed from: e */
    public final void mo6188e(String str, String str2) {
        int i;
        i = C10393b.level;
        if (i <= 1) {
            Log.d(str, str2);
        }
    }

    @Override // com.tencent.p348mm.sdk.p354b.C10393b.InterfaceC10394a
    /* renamed from: f */
    public final void mo6187f(String str, String str2) {
        int i;
        i = C10393b.level;
        if (i <= 4) {
            Log.e(str, str2);
        }
    }
}
