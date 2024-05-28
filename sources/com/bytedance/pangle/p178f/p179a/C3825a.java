package com.bytedance.pangle.p178f.p179a;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.bytedance.pangle.f.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class C3825a {

    /* renamed from: a */
    int f9149a;

    /* renamed from: b */
    C3826b f9150b;

    /* renamed from: c */
    int[] f9151c;

    /* renamed from: i */
    private boolean f9157i;

    /* renamed from: k */
    private C3830f f9159k;

    /* renamed from: j */
    private boolean f9158j = false;

    /* renamed from: l */
    private final C3827c f9160l = new C3827c();

    /* renamed from: d */
    int f9152d = 0;

    /* renamed from: e */
    int f9153e = 1;

    /* renamed from: f */
    int f9154f = 2;

    /* renamed from: g */
    int f9155g = 3;

    /* renamed from: h */
    int f9156h = 4;

    public C3825a() {
        m16869c();
    }

    /* renamed from: a */
    public final void m16873a() {
        if (this.f9158j) {
            this.f9158j = false;
            C3826b c3826b = this.f9150b;
            if (c3826b.f9161a != null) {
                try {
                    c3826b.f9161a.close();
                } catch (IOException unused) {
                }
                c3826b.m16863a((InputStream) null);
            }
            this.f9159k = null;
            this.f9150b = null;
            C3827c c3827c = this.f9160l;
            c3827c.f9164b = 0;
            c3827c.f9165c = 0;
            m16869c();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:92:0x023e, code lost:
        throw new java.io.IOException("Invalid chunk type (" + r2 + ").");
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int m16871b() {
        /*
            Method dump skipped, instructions count: 591
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p178f.p179a.C3825a.m16871b():int");
    }

    /* renamed from: a */
    public final String m16872a(int i) {
        int i2 = this.f9151c[m16866e(i) + 1];
        return i2 == -1 ? "" : this.f9159k.m16855a(i2);
    }

    /* renamed from: b */
    public final int m16870b(int i) {
        return this.f9151c[m16866e(i) + 3];
    }

    /* renamed from: c */
    public final int m16868c(int i) {
        return this.f9151c[m16866e(i) + 4];
    }

    /* renamed from: d */
    public final String m16867d(int i) {
        int m16866e = m16866e(i);
        int[] iArr = this.f9151c;
        if (iArr[m16866e + 3] == 3) {
            return this.f9159k.m16855a(iArr[m16866e + 2]);
        }
        return "";
    }

    /* renamed from: e */
    private int m16866e(int i) {
        if (this.f9149a != 2) {
            throw new IndexOutOfBoundsException("Current event is not START_TAG.");
        }
        int i2 = i * 5;
        if (i2 < this.f9151c.length) {
            return i2;
        }
        throw new IndexOutOfBoundsException("Invalid attribute index (" + i + ").");
    }

    /* renamed from: c */
    private void m16869c() {
        this.f9151c = null;
        this.f9149a = -1;
    }
}
