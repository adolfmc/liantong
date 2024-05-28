package com.baidu.p122b.p125c.p126a;

import java.security.SecureRandom;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2396c {

    /* renamed from: a */
    public static final SecureRandom f4208a = new SecureRandom();

    /* renamed from: b */
    private C2398e f4209b;

    public C2396c() {
        this.f4209b = null;
        this.f4209b = new C2398e(new C2395b(), 16);
    }

    /* renamed from: a */
    public static byte[] m20279a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        C2396c c2396c = new C2396c();
        c2396c.m20281a(2, bArr, bArr2);
        return c2396c.m20280a(bArr3);
    }

    /* renamed from: a */
    public void m20281a(int i, byte[] bArr, byte[] bArr2) {
        this.f4209b.m20277a(i, bArr, bArr2, f4208a);
    }

    /* renamed from: a */
    public final byte[] m20280a(byte[] bArr) {
        if (bArr != null) {
            return this.f4209b.m20276a(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("Null input buffer");
    }
}
