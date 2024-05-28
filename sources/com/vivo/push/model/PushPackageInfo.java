package com.vivo.push.model;

import android.text.TextUtils;

/* renamed from: com.vivo.push.model.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushPackageInfo {

    /* renamed from: a */
    private String f21056a;

    /* renamed from: d */
    private String f21059d;

    /* renamed from: b */
    private long f21057b = -1;

    /* renamed from: c */
    private int f21058c = -1;

    /* renamed from: e */
    private boolean f21060e = false;

    /* renamed from: f */
    private boolean f21061f = false;

    public PushPackageInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalAccessError("PushPackageInfo need a non-null pkgName.");
        }
        this.f21056a = str;
    }

    /* renamed from: a */
    public final String m5602a() {
        return this.f21056a;
    }

    /* renamed from: b */
    public final long m5597b() {
        return this.f21057b;
    }

    /* renamed from: a */
    public final void m5600a(long j) {
        this.f21057b = j;
    }

    /* renamed from: c */
    public final boolean m5595c() {
        return this.f21060e;
    }

    /* renamed from: a */
    public final void m5598a(boolean z) {
        this.f21060e = z;
    }

    /* renamed from: d */
    public final boolean m5594d() {
        return this.f21061f;
    }

    /* renamed from: b */
    public final void m5596b(boolean z) {
        this.f21061f = z;
    }

    /* renamed from: a */
    public final void m5601a(int i) {
        this.f21058c = i;
    }

    /* renamed from: a */
    public final void m5599a(String str) {
        this.f21059d = str;
    }

    public final String toString() {
        return "PushPackageInfo{mPackageName=" + this.f21056a + ", mPushVersion=" + this.f21057b + ", mPackageVersion=" + this.f21058c + ", mInBlackList=" + this.f21060e + ", mPushEnable=" + this.f21061f + "}";
    }
}
