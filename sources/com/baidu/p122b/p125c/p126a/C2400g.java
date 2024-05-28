package com.baidu.p122b.p125c.p126a;

import com.baidu.p122b.p125c.p128c.C2407b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.a.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2400g {

    /* renamed from: a */
    private static volatile byte[] f4226a;

    /* renamed from: a */
    public static byte[] m20265a() {
        if (f4226a == null) {
            synchronized (C2400g.class) {
                if (f4226a == null) {
                    byte[] bArr = new byte[16];
                    System.arraycopy(C2407b.m20243b(), 0, bArr, 0, 16);
                    C2396c c2396c = new C2396c();
                    c2396c.m20281a(2, bArr, bArr);
                    f4226a = c2396c.m20280a(new byte[]{-71, -100, -115, 26, 39, -124, 14, 14, -31, -46, -56, 1, 25, -127, -99, -107, -54, 51, 46, 14, 68, -68, -19, 28, 66, 19, -113, 5, 25, -11, -123, 50});
                }
            }
        }
        return f4226a;
    }
}
