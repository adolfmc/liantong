package com.baidu.p122b.p125c.p128c;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.c.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2407b {

    /* renamed from: a */
    private static byte[] f4236a;

    /* renamed from: b */
    private static byte[] f4237b;

    /* renamed from: a */
    public static byte[] m20244a() {
        byte[] bArr = f4236a;
        if (bArr != null) {
            return bArr;
        }
        f4236a = new BigInteger(C2406a.f4231a).modPow(new BigInteger(C2406a.f4232b), new BigInteger(C2406a.f4235e)).toByteArray();
        return f4236a;
    }

    /* renamed from: b */
    public static byte[] m20243b() {
        byte[] bArr = f4237b;
        if (bArr != null) {
            return bArr;
        }
        f4237b = new BigInteger(C2406a.f4233c).modPow(new BigInteger(C2406a.f4234d), new BigInteger(C2406a.f4235e)).toByteArray();
        return f4237b;
    }
}
