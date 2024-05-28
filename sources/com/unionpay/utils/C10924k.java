package com.unionpay.utils;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.utils.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10924k {

    /* renamed from: f */
    private static C10924k f20832f;

    /* renamed from: a */
    public String f20833a = "";

    /* renamed from: b */
    public String f20834b = "";

    /* renamed from: c */
    public String f20835c = "";

    /* renamed from: d */
    public String f20836d = "";

    /* renamed from: e */
    public String f20837e = "";

    /* renamed from: a */
    public static C10924k m5827a() {
        if (f20832f == null) {
            f20832f = Locale.getDefault().toString().startsWith("zh") ? new C10925l() : new C10926m();
        }
        return f20832f;
    }
}
