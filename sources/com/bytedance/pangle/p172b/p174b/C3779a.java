package com.bytedance.pangle.p172b.p174b;

import com.bytedance.pangle.log.ZeusLogger;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.b.b.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3779a {

    /* renamed from: a */
    private static Method f9030a;

    /* renamed from: b */
    private static Method f9031b;

    /* renamed from: c */
    private static Method f9032c;

    /* renamed from: d */
    private static Method f9033d;

    static {
        try {
            f9030a = Class.class.getDeclaredMethod("getDeclaredField", String.class);
            f9031b = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            f9032c = Class.class.getDeclaredMethod("getDeclaredConstructor", Class[].class);
            f9033d = Class.class.getDeclaredMethod("forName", String.class);
        } catch (Throwable th) {
            ZeusLogger.errReport("Zeus/init_pangle", "DoubleReflectorinit failed", th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.reflect.Field m16966a(java.lang.Class<?> r10, java.lang.String r11) {
        /*
            java.lang.reflect.Method r0 = com.bytedance.pangle.p172b.p174b.C3779a.f9030a
            r1 = 0
            if (r0 == 0) goto L43
            r2 = 0
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L1e
            r4[r2] = r11     // Catch: java.lang.Throwable -> L1e
            java.lang.Object r0 = r0.invoke(r10, r4)     // Catch: java.lang.Throwable -> L1e
            java.lang.reflect.Field r0 = (java.lang.reflect.Field) r0     // Catch: java.lang.Throwable -> L1e
            if (r0 == 0) goto L1c
            r0.setAccessible(r3)     // Catch: java.lang.Throwable -> L17
            goto L1c
        L17:
            r1 = move-exception
            r9 = r1
            r1 = r0
            r0 = r9
            goto L1f
        L1c:
            r1 = r0
            goto L43
        L1e:
            r0 = move-exception
        L1f:
            java.lang.String r4 = "Zeus_pangle"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "DoubleReflector"
            r5.<init>(r6)
            java.lang.String r6 = "getField %s#%s failed !!!"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.String r8 = r10.getName()
            r7[r2] = r8
            r7[r3] = r11
            java.lang.String r2 = java.lang.String.format(r6, r7)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            com.bytedance.pangle.log.ZeusLogger.m16787w(r4, r2, r0)
        L43:
            if (r1 == 0) goto L46
            return r1
        L46:
            java.lang.reflect.Field r10 = com.bytedance.pangle.p172b.p173a.C3777a.m16973a(r10, r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p172b.p174b.C3779a.m16966a(java.lang.Class, java.lang.String):java.lang.reflect.Field");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0047 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.reflect.Method m16965a(java.lang.Class<?> r10, java.lang.String r11, java.lang.Class<?>... r12) {
        /*
            java.lang.reflect.Method r0 = com.bytedance.pangle.p172b.p174b.C3779a.f9031b
            r1 = 0
            if (r0 == 0) goto L45
            r2 = 0
            r3 = 2
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L21
            r5[r2] = r11     // Catch: java.lang.Throwable -> L21
            r5[r4] = r12     // Catch: java.lang.Throwable -> L21
            java.lang.Object r0 = r0.invoke(r10, r5)     // Catch: java.lang.Throwable -> L21
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0     // Catch: java.lang.Throwable -> L21
            if (r0 == 0) goto L1f
            r0.setAccessible(r4)     // Catch: java.lang.Throwable -> L1a
            goto L1f
        L1a:
            r1 = move-exception
            r9 = r1
            r1 = r0
            r0 = r9
            goto L22
        L1f:
            r1 = r0
            goto L45
        L21:
            r0 = move-exception
        L22:
            java.lang.String r5 = "Zeus_pangle"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "DoubleReflector"
            r6.<init>(r7)
            java.lang.String r7 = "getMethod %s#%s failed !!!"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r8 = r10.getName()
            r3[r2] = r8
            r3[r4] = r11
            java.lang.String r2 = java.lang.String.format(r7, r3)
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            com.bytedance.pangle.log.ZeusLogger.m16787w(r5, r2, r0)
        L45:
            if (r1 == 0) goto L48
            return r1
        L48:
            java.lang.reflect.Method r10 = com.bytedance.pangle.p172b.p173a.C3777a.m16972a(r10, r11, r12)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p172b.p174b.C3779a.m16965a(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.reflect.Constructor m16964a(java.lang.Class<?> r8, java.lang.Class<?>... r9) {
        /*
            java.lang.reflect.Method r0 = com.bytedance.pangle.p172b.p174b.C3779a.f9032c
            if (r0 == 0) goto L3a
            r1 = 0
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L11
            r3[r1] = r9     // Catch: java.lang.Throwable -> L11
            java.lang.Object r0 = r0.invoke(r8, r3)     // Catch: java.lang.Throwable -> L11
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0     // Catch: java.lang.Throwable -> L11
            goto L3b
        L11:
            r0 = move-exception
            java.lang.String r3 = "Zeus_pangle"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "DoubleReflector"
            r4.<init>(r5)
            java.lang.String r5 = "getConstructor %s<init>%s failed !!!"
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String r7 = r8.getName()
            r6[r1] = r7
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r6[r2] = r1
            java.lang.String r1 = java.lang.String.format(r5, r6)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.bytedance.pangle.log.ZeusLogger.m16787w(r3, r1, r0)
        L3a:
            r0 = 0
        L3b:
            if (r0 == 0) goto L3e
            return r0
        L3e:
            java.lang.reflect.Constructor r8 = com.bytedance.pangle.p172b.p173a.C3777a.m16971a(r8, r9)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p172b.p174b.C3779a.m16964a(java.lang.Class, java.lang.Class[]):java.lang.reflect.Constructor");
    }
}
