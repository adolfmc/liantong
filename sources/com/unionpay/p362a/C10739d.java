package com.unionpay.p362a;

import java.net.URL;
import java.util.HashMap;

/* renamed from: com.unionpay.a.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10739d {

    /* renamed from: b */
    private String f20645b;

    /* renamed from: e */
    private String f20648e;

    /* renamed from: a */
    private int f20644a = 1;

    /* renamed from: c */
    private HashMap f20646c = null;

    /* renamed from: d */
    private byte[] f20647d = null;

    public C10739d(String str) {
        this.f20645b = str;
    }

    /* renamed from: a */
    public final URL m5962a() {
        try {
            return new URL(this.f20645b);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public final void m5961a(String str) {
        if (str != null) {
            this.f20647d = str.getBytes();
            this.f20648e = str;
        }
    }

    /* renamed from: b */
    public final String m5960b() {
        return this.f20644a == 1 ? "POST" : "GET";
    }

    /* renamed from: c */
    public final String m5959c() {
        return this.f20648e;
    }

    /* renamed from: d */
    public final HashMap m5958d() {
        return this.f20646c;
    }
}
