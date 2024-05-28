package com.bytedance.pangle.res.p181a;

import java.io.IOException;
import java.util.HashMap;

/* renamed from: com.bytedance.pangle.res.a.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3908b {

    /* renamed from: c */
    C3915g f9305c;

    /* renamed from: i */
    private final InterfaceC3916h f9311i;

    /* renamed from: j */
    private final byte[] f9312j;

    /* renamed from: l */
    private int[] f9314l;

    /* renamed from: n */
    private boolean f9316n;

    /* renamed from: o */
    private int f9317o;

    /* renamed from: p */
    private int[] f9318p;

    /* renamed from: q */
    private int f9319q;

    /* renamed from: a */
    HashMap<Integer, Integer> f9303a = new HashMap<>();

    /* renamed from: b */
    boolean f9304b = false;

    /* renamed from: k */
    private boolean f9313k = false;

    /* renamed from: m */
    private final C3909a f9315m = new C3909a();

    /* renamed from: d */
    int f9306d = 0;

    /* renamed from: e */
    int f9307e = 1;

    /* renamed from: f */
    int f9308f = 2;

    /* renamed from: g */
    int f9309g = 3;

    /* renamed from: h */
    int f9310h = 4;

    public C3908b(byte[] bArr, InterfaceC3916h interfaceC3916h) {
        this.f9311i = interfaceC3916h;
        this.f9312j = bArr;
        m16711c();
    }

    /* renamed from: a */
    public final void m16713a() {
        if (this.f9313k) {
            this.f9313k = false;
            this.f9305c = null;
            this.f9314l = null;
            C3909a c3909a = this.f9315m;
            c3909a.f9321b = 0;
            c3909a.f9322c = 0;
            m16711c();
        }
    }

    /* renamed from: b */
    public final int m16712b() {
        if (this.f9305c == null) {
            throw new RuntimeException("Parser is not opened.");
        }
        try {
            m16710d();
            return this.f9317o;
        } catch (IOException e) {
            m16713a();
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.pangle.res.a.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class C3909a {

        /* renamed from: a */
        int[] f9320a = new int[32];

        /* renamed from: b */
        int f9321b;

        /* renamed from: c */
        int f9322c;

        /* renamed from: a */
        public final void m16709a() {
            m16708b();
            int i = this.f9321b;
            int[] iArr = this.f9320a;
            iArr[i] = 0;
            iArr[i + 1] = 0;
            this.f9321b = i + 2;
            this.f9322c++;
        }

        /* renamed from: b */
        final void m16708b() {
            int[] iArr = this.f9320a;
            int length = iArr.length;
            int i = this.f9321b;
            int i2 = length - i;
            if (i2 > 2) {
                return;
            }
            int[] iArr2 = new int[(iArr.length + i2) * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.f9320a = iArr2;
        }
    }

    /* renamed from: c */
    private void m16711c() {
        this.f9317o = -1;
        this.f9318p = null;
        this.f9319q = -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x02ad, code lost:
        throw new java.io.IOException("Invalid chunk type (" + r6 + ").");
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x012e, code lost:
        throw new java.io.IOException("Invalid resource ids size (" + r6 + ").");
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m16710d() {
        /*
            Method dump skipped, instructions count: 686
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.res.p181a.C3908b.m16710d():void");
    }
}
