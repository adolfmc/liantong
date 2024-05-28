package com.tencent.p348mm.sdk.p354b;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.b.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10392a {
    /* renamed from: a */
    public static int m6198a(Bundle bundle, String str) {
        if (bundle == null) {
            return -1;
        }
        try {
            return bundle.getInt(str, -1);
        } catch (Exception e) {
            C10393b.m6194a("MicroMsg.IntentUtil", "getIntExtra exception:%s", e.getMessage());
            return -1;
        }
    }

    /* renamed from: b */
    public static String m6197b(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        try {
            return bundle.getString(str);
        } catch (Exception e) {
            C10393b.m6194a("MicroMsg.IntentUtil", "getStringExtra exception:%s", e.getMessage());
            return null;
        }
    }
}
