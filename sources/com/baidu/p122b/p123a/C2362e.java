package com.baidu.p122b.p123a;

import com.baidu.p122b.p125c.p126a.C2396c;
import com.baidu.p122b.p125c.p126a.C2400g;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2362e {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.a.e$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2363a extends Exception {
        public C2363a(String str) {
            super(str);
        }

        public C2363a(Throwable th) {
            super(th);
        }
    }

    /* renamed from: a */
    public static String m20374a(byte[] bArr) {
        byte[] m20265a = C2400g.m20265a();
        return new String(C2396c.m20279a(m20265a, m20265a, bArr));
    }

    /* renamed from: a */
    public static Method m20375a(Class<?> cls, String str, Class<?>[] clsArr) {
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
