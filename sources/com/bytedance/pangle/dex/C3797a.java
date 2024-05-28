package com.bytedance.pangle.dex;

import com.bytedance.pangle.p176d.C3792c;
import com.bytedance.pangle.p177e.C3805b;
import com.bytedance.pangle.p177e.C3815g;
import com.bytedance.pangle.util.FieldUtils;
import dalvik.system.DexFile;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.dex.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3797a {

    /* renamed from: a */
    private static volatile int f9090a;

    /* renamed from: b */
    private static volatile Constructor<?> f9091b;

    /* renamed from: a */
    public static void m16915a(ClassLoader classLoader, String str, int i) {
        Object obj = FieldUtils.getField(classLoader.getClass(), "pathList").get(classLoader);
        ArrayList arrayList = new ArrayList();
        String[] split = C3815g.m16887a(str, i).split(":");
        String m16932c = C3792c.m16932c(str, i);
        for (String str2 : split) {
            Object native_load_direct_dex = DirectDex.native_load_direct_dex(str2);
            if (native_load_direct_dex == null) {
                native_load_direct_dex = DexFile.loadDex(str2, m16932c + File.separator + C3805b.m16905a(str2), 0);
            }
            arrayList.add(m16916a(new File(str2), native_load_direct_dex));
        }
        Object[] array = arrayList.toArray();
        Field field = FieldUtils.getField(obj.getClass(), "dexElements");
        Object[] objArr = (Object[]) field.get(obj);
        Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), objArr.length + array.length);
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        System.arraycopy(array, 0, objArr2, objArr.length, array.length);
        field.set(obj, objArr2);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0036 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0018 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Object m16916a(java.io.File r11, java.lang.Object r12) {
        /*
            int r0 = com.bytedance.pangle.dex.C3797a.f9090a
            java.lang.reflect.Constructor<?> r1 = com.bytedance.pangle.dex.C3797a.f9091b
            r2 = 0
            if (r1 != 0) goto Le
            java.lang.String r3 = "dalvik.system.DexPathList$Element"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: java.lang.Exception -> Le
            goto Lf
        Le:
            r3 = r2
        Lf:
            r4 = 8
            r5 = 4
            r6 = 0
            r7 = 3
            r8 = 2
            r9 = 1
            if (r1 != 0) goto L34
            java.lang.Class[] r1 = new java.lang.Class[r5]     // Catch: java.lang.Exception -> L33
            java.lang.Class<java.io.File> r10 = java.io.File.class
            r1[r6] = r10     // Catch: java.lang.Exception -> L33
            java.lang.Class r10 = java.lang.Boolean.TYPE     // Catch: java.lang.Exception -> L33
            r1[r9] = r10     // Catch: java.lang.Exception -> L33
            java.lang.Class<java.io.File> r10 = java.io.File.class
            r1[r8] = r10     // Catch: java.lang.Exception -> L33
            java.lang.Class<dalvik.system.DexFile> r10 = dalvik.system.DexFile.class
            r1[r7] = r10     // Catch: java.lang.Exception -> L33
            java.lang.reflect.Constructor r1 = r3.getConstructor(r1)     // Catch: java.lang.Exception -> L33
            r1.setAccessible(r9)     // Catch: java.lang.Exception -> L33
            r0 = r4
            goto L34
        L33:
            r1 = r2
        L34:
            if (r1 != 0) goto L4e
            java.lang.Class[] r1 = new java.lang.Class[r7]     // Catch: java.lang.Exception -> L4d
            java.lang.Class<java.io.File> r10 = java.io.File.class
            r1[r6] = r10     // Catch: java.lang.Exception -> L4d
            java.lang.Class<java.util.zip.ZipFile> r10 = java.util.zip.ZipFile.class
            r1[r9] = r10     // Catch: java.lang.Exception -> L4d
            java.lang.Class<dalvik.system.DexFile> r10 = dalvik.system.DexFile.class
            r1[r8] = r10     // Catch: java.lang.Exception -> L4d
            java.lang.reflect.Constructor r1 = r3.getConstructor(r1)     // Catch: java.lang.Exception -> L4d
            r1.setAccessible(r9)     // Catch: java.lang.Exception -> L4d
            r0 = r5
            goto L4e
        L4d:
            r1 = r2
        L4e:
            if (r1 != 0) goto L68
            java.lang.Class[] r1 = new java.lang.Class[r7]     // Catch: java.lang.Exception -> L67
            java.lang.Class<java.io.File> r10 = java.io.File.class
            r1[r6] = r10     // Catch: java.lang.Exception -> L67
            java.lang.Class<java.io.File> r10 = java.io.File.class
            r1[r9] = r10     // Catch: java.lang.Exception -> L67
            java.lang.Class<dalvik.system.DexFile> r10 = dalvik.system.DexFile.class
            r1[r8] = r10     // Catch: java.lang.Exception -> L67
            java.lang.reflect.Constructor r1 = r3.getConstructor(r1)     // Catch: java.lang.Exception -> L67
            r1.setAccessible(r9)     // Catch: java.lang.Exception -> L67
            r0 = r8
            goto L68
        L67:
            r1 = r2
        L68:
            if (r1 != 0) goto L86
            java.lang.Class[] r1 = new java.lang.Class[r5]     // Catch: java.lang.Exception -> L85
            java.lang.Class<java.io.File> r10 = java.io.File.class
            r1[r6] = r10     // Catch: java.lang.Exception -> L85
            java.lang.Class r10 = java.lang.Boolean.TYPE     // Catch: java.lang.Exception -> L85
            r1[r9] = r10     // Catch: java.lang.Exception -> L85
            java.lang.Class<java.io.File> r10 = java.io.File.class
            r1[r8] = r10     // Catch: java.lang.Exception -> L85
            java.lang.Class<dalvik.system.DexFile> r10 = dalvik.system.DexFile.class
            r1[r7] = r10     // Catch: java.lang.Exception -> L85
            java.lang.reflect.Constructor r1 = r3.getConstructor(r1)     // Catch: java.lang.Exception -> L85
            r1.setAccessible(r9)     // Catch: java.lang.Exception -> L85
            r0 = r9
            goto L86
        L85:
            r1 = r2
        L86:
            com.bytedance.pangle.dex.C3797a.f9091b = r1
            com.bytedance.pangle.dex.C3797a.f9090a = r0
            if (r0 == r5) goto La3
            if (r0 == r4) goto L92
            switch(r0) {
                case 1: goto L92;
                case 2: goto La3;
                default: goto L91;
            }
        L91:
            goto Laf
        L92:
            java.lang.Object[] r0 = new java.lang.Object[r5]
            r0[r6] = r11
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            r0[r9] = r11
            r0[r8] = r2
            r0[r7] = r12
            java.lang.Object r2 = m16914a(r1, r0)
            goto Laf
        La3:
            java.lang.Object[] r0 = new java.lang.Object[r7]
            r0[r6] = r11
            r0[r9] = r2
            r0[r8] = r12
            java.lang.Object r2 = m16914a(r1, r0)
        Laf:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.dex.C3797a.m16916a(java.io.File, java.lang.Object):java.lang.Object");
    }

    /* renamed from: a */
    private static Object m16914a(Constructor<?> constructor, Object... objArr) {
        try {
            return constructor.newInstance(objArr);
        } catch (Exception unused) {
            return null;
        }
    }
}
