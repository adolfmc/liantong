package com.p281qq.p282e.comm.managers.plugin;

import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.managers.plugin.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6878a {

    /* renamed from: a */
    private static Method f17943a;

    /* renamed from: b */
    private static boolean f17944b;

    /* renamed from: a */
    public static void m8263a(Throwable th, String str) {
        try {
            Exception exc = new Exception("插件错误：" + str, th);
            if (f17944b) {
                return;
            }
            if (f17943a == null) {
                Method declaredMethod = Class.forName("com.tencent.bugly.crashreport.CrashReport").getDeclaredMethod("postCatchedException", Throwable.class);
                f17943a = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            f17943a.invoke(null, exc);
        } catch (Throwable unused) {
            f17944b = true;
        }
    }
}
