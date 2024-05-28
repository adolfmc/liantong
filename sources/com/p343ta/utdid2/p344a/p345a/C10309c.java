package com.p343ta.utdid2.p344a.p345a;

import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ta.utdid2.a.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10309c {
    /* renamed from: a */
    public static boolean m6446a() {
        if (Build.VERSION.SDK_INT < 29) {
            return Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 'Q' && Build.VERSION.CODENAME.charAt(0) <= 'Z';
        }
        return true;
    }
}
