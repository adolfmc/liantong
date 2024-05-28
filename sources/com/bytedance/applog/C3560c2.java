package com.bytedance.applog;

import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.c2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3560c2 {

    /* renamed from: a */
    public static Object f8396a;

    /* renamed from: b */
    public static Field f8397b;

    /* renamed from: c */
    public static Class f8398c;

    /* renamed from: d */
    public static Class f8399d;

    /* renamed from: e */
    public static Class<?> f8400e;

    /* renamed from: f */
    public static Method f8401f;

    /* renamed from: g */
    public static boolean f8402g;

    /* renamed from: h */
    public static boolean f8403h;

    /* renamed from: i */
    public static boolean f8404i;

    /* renamed from: a */
    public static boolean m17318a(View view) {
        if (!f8402g) {
            m17317b();
        }
        Class<?> cls = view.getClass();
        return cls == f8398c || cls == f8399d;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0050  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.view.View[] m17319a() {
        /*
            r0 = 0
            android.view.View[] r1 = new android.view.View[r0]
            java.lang.Object r2 = com.bytedance.applog.C3560c2.f8396a
            if (r2 != 0) goto L1c
            java.lang.Object r2 = com.bytedance.applog.C3557c.f8384g
            android.app.Activity r2 = (android.app.Activity) r2
            if (r2 == 0) goto L8a
            r1 = 1
            android.view.View[] r1 = new android.view.View[r1]
            android.view.Window r2 = r2.getWindow()
            android.view.View r2 = r2.getDecorView()
            r1[r0] = r2
            goto L8a
        L1c:
            r3 = 0
            boolean r4 = com.bytedance.applog.C3560c2.f8403h     // Catch: java.lang.Exception -> L40
            if (r4 == 0) goto L30
            java.lang.reflect.Field r3 = com.bytedance.applog.C3560c2.f8397b     // Catch: java.lang.Exception -> L40
            java.lang.Object r2 = r3.get(r2)     // Catch: java.lang.Exception -> L40
            java.util.ArrayList r2 = (java.util.ArrayList) r2     // Catch: java.lang.Exception -> L40
            java.lang.Object[] r2 = r2.toArray(r1)     // Catch: java.lang.Exception -> L40
        L2d:
            android.view.View[] r2 = (android.view.View[]) r2     // Catch: java.lang.Exception -> L40
            goto L3c
        L30:
            boolean r4 = com.bytedance.applog.C3560c2.f8404i     // Catch: java.lang.Exception -> L40
            if (r4 == 0) goto L3b
            java.lang.reflect.Field r3 = com.bytedance.applog.C3560c2.f8397b     // Catch: java.lang.Exception -> L40
            java.lang.Object r2 = r3.get(r2)     // Catch: java.lang.Exception -> L40
            goto L2d
        L3b:
            r2 = r3
        L3c:
            if (r2 == 0) goto L46
            r1 = r2
            goto L46
        L40:
            r2 = move-exception
            java.lang.String r3 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r3, r2)
        L46:
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = r1.length
            r2.<init>(r3)
            int r3 = r1.length
            r4 = r0
        L4e:
            if (r4 >= r3) goto L81
            int r5 = r3 + (-1)
            int r5 = r5 - r4
            r5 = r1[r5]
            if (r5 == 0) goto L7e
            int r6 = r5.getWindowVisibility()
            if (r6 != 0) goto L7e
            int r6 = r5.getVisibility()
            if (r6 != 0) goto L7e
            int r6 = r5.getWidth()
            if (r6 == 0) goto L7e
            int r6 = r5.getHeight()
            if (r6 == 0) goto L7e
            boolean r6 = r5 instanceof com.bytedance.applog.C3748z1
            if (r6 == 0) goto L74
            goto L7e
        L74:
            r2.add(r0, r5)
            boolean r5 = m17318a(r5)
            if (r5 == 0) goto L7e
            goto L81
        L7e:
            int r4 = r4 + 1
            goto L4e
        L81:
            int r0 = r2.size()
            android.view.View[] r1 = new android.view.View[r0]
            r2.toArray(r1)
        L8a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3560c2.m17319a():android.view.View[]");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(28:3|(1:5)(1:64)|6|7|8|(1:10)(3:57|58|(1:60)(1:61))|11|(1:13)(2:54|(1:56))|14|15|16|17|18|19|20|21|22|(2:23|24)|(10:26|27|28|30|31|(1:33)(1:40)|34|35|36|37)(1:47)|44|45|30|31|(0)(0)|34|35|36|37) */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ba, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00bb, code lost:
        com.bytedance.applog.C3704u2.m17108a("U SHALL NOT PASS!", r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b4  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m17317b() {
        /*
            boolean r0 = com.bytedance.applog.C3560c2.f8402g
            if (r0 != 0) goto Lc2
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 17
            if (r0 < r1) goto Ld
            java.lang.String r0 = "android.view.WindowManagerGlobal"
            goto Lf
        Ld:
            java.lang.String r0 = "android.view.WindowManagerImpl"
        Lf:
            r2 = 1
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            if (r3 < r1) goto L1b
            java.lang.String r1 = "sDefaultWindowManager"
            goto L26
        L1b:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            r3 = 13
            if (r1 < r3) goto L24
            java.lang.String r1 = "sWindowManager"
            goto L26
        L24:
            java.lang.String r1 = "mWindowManager"
        L26:
            java.lang.String r3 = "mViews"
            java.lang.reflect.Field r3 = r0.getDeclaredField(r3)     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            com.bytedance.applog.C3560c2.f8397b = r3     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            java.lang.reflect.Field r0 = r0.getDeclaredField(r1)     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            java.lang.reflect.Field r1 = com.bytedance.applog.C3560c2.f8397b     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            r1.setAccessible(r2)     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            java.lang.reflect.Field r1 = com.bytedance.applog.C3560c2.f8397b     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            java.lang.Class r1 = r1.getType()     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            java.lang.Class<java.util.ArrayList> r3 = java.util.ArrayList.class
            if (r1 != r3) goto L44
            com.bytedance.applog.C3560c2.f8403h = r2     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            goto L50
        L44:
            java.lang.reflect.Field r1 = com.bytedance.applog.C3560c2.f8397b     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            java.lang.Class r1 = r1.getType()     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            java.lang.Class<android.view.View[]> r3 = android.view.View[].class
            if (r1 != r3) goto L50
            com.bytedance.applog.C3560c2.f8404i = r2     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
        L50:
            r0.setAccessible(r2)     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            r1 = 0
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            com.bytedance.applog.C3560c2.f8396a = r0     // Catch: java.lang.ClassNotFoundException -> L5b java.lang.IllegalAccessException -> L5d java.lang.NoSuchFieldException -> L5f
            goto L65
        L5b:
            r0 = move-exception
            goto L60
        L5d:
            r0 = move-exception
            goto L60
        L5f:
            r0 = move-exception
        L60:
            java.lang.String r1 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r1, r0)
        L65:
            java.lang.String r0 = "com.android.internal.view.menu.ListMenuItemView"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.NoSuchMethodException -> L7f java.lang.ClassNotFoundException -> L81
            com.bytedance.applog.C3560c2.f8400e = r0     // Catch: java.lang.NoSuchMethodException -> L7f java.lang.ClassNotFoundException -> L81
            java.lang.String r0 = "com.android.internal.view.menu.MenuView$ItemView"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.NoSuchMethodException -> L7f java.lang.ClassNotFoundException -> L81
            java.lang.String r1 = "getItemData"
            r3 = 0
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch: java.lang.NoSuchMethodException -> L7f java.lang.ClassNotFoundException -> L81
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r1, r3)     // Catch: java.lang.NoSuchMethodException -> L7f java.lang.ClassNotFoundException -> L81
            com.bytedance.applog.C3560c2.f8401f = r0     // Catch: java.lang.NoSuchMethodException -> L7f java.lang.ClassNotFoundException -> L81
            goto L87
        L7f:
            r0 = move-exception
            goto L82
        L81:
            r0 = move-exception
        L82:
            java.lang.String r1 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r1, r0)
        L87:
            r0 = 23
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.ClassNotFoundException -> La3
            if (r1 < r0) goto L9d
            java.lang.String r1 = "com.android.internal.policy.PhoneWindow$DecorView"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.ClassNotFoundException -> L96
            com.bytedance.applog.C3560c2.f8398c = r1     // Catch: java.lang.ClassNotFoundException -> L96
            goto La9
        L96:
            java.lang.String r1 = "com.android.internal.policy.DecorView"
        L98:
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.ClassNotFoundException -> La3
            goto La0
        L9d:
            java.lang.String r1 = "com.android.internal.policy.impl.PhoneWindow$DecorView"
            goto L98
        La0:
            com.bytedance.applog.C3560c2.f8398c = r1     // Catch: java.lang.ClassNotFoundException -> La3
            goto La9
        La3:
            r1 = move-exception
            java.lang.String r3 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r3, r1)
        La9:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.ClassNotFoundException -> Lba
            if (r1 < r0) goto Lb4
            java.lang.String r0 = "android.widget.PopupWindow$PopupDecorView"
        Laf:
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.ClassNotFoundException -> Lba
            goto Lb7
        Lb4:
            java.lang.String r0 = "android.widget.PopupWindow$PopupViewContainer"
            goto Laf
        Lb7:
            com.bytedance.applog.C3560c2.f8399d = r0     // Catch: java.lang.ClassNotFoundException -> Lba
            goto Lc0
        Lba:
            r0 = move-exception
            java.lang.String r1 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r1, r0)
        Lc0:
            com.bytedance.applog.C3560c2.f8402g = r2
        Lc2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3560c2.m17317b():void");
    }
}
