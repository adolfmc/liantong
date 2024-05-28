package com.bytedance.pangle.p176d;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.pangle.util.MethodUtils;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.d.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3793d {

    /* renamed from: a */
    private static String f9083a;

    /* renamed from: b */
    private static List<String> f9084b = new CopyOnWriteArrayList();

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: b */
    private static java.lang.String m16921b() {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6a
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L6a
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a
            java.lang.String r5 = "/proc/"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L6a
            int r5 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L6a
            r4.append(r5)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r5 = "/cmdline"
            r4.append(r5)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L6a
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r4 = "iso-8859-1"
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L6a
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L6a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6b
            r2.<init>()     // Catch: java.lang.Throwable -> L6b
        L2e:
            int r3 = r1.read()     // Catch: java.lang.Throwable -> L6b
            if (r3 <= 0) goto L39
            char r3 = (char) r3     // Catch: java.lang.Throwable -> L6b
            r2.append(r3)     // Catch: java.lang.Throwable -> L6b
            goto L2e
        L39:
            boolean r3 = com.bytedance.pangle.log.ZeusLogger.isDebug()     // Catch: java.lang.Throwable -> L6b
            if (r3 == 0) goto L56
            java.lang.String r3 = "Process"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6b
            java.lang.String r5 = "get processName = "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L6b
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Throwable -> L6b
            r4.append(r5)     // Catch: java.lang.Throwable -> L6b
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L6b
            com.bytedance.pangle.log.ZeusLogger.m16794d(r3, r4)     // Catch: java.lang.Throwable -> L6b
        L56:
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L6b
            r1.close()     // Catch: java.lang.Exception -> L5d
        L5d:
            return r0
        L5e:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L64
        L63:
            r1 = move-exception
        L64:
            if (r0 == 0) goto L69
            r0.close()     // Catch: java.lang.Exception -> L69
        L69:
            throw r1
        L6a:
            r1 = r0
        L6b:
            if (r1 == 0) goto L70
            r1.close()     // Catch: java.lang.Exception -> L70
        L70:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p176d.C3793d.m16921b():java.lang.String");
    }

    /* renamed from: a */
    public static String m16924a() {
        if (!TextUtils.isEmpty(f9083a)) {
            return f9083a;
        }
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                String processName = Application.getProcessName();
                if (!TextUtils.isEmpty(processName)) {
                    f9083a = processName;
                }
                return f9083a;
            }
        } catch (Throwable unused) {
        }
        try {
            Object invokeStaticMethod = MethodUtils.invokeStaticMethod(Class.forName("android.app.ActivityThread"), "currentProcessName", new Object[0]);
            if (!TextUtils.isEmpty((String) invokeStaticMethod)) {
                f9083a = (String) invokeStaticMethod;
            }
            return f9083a;
        } catch (Exception e) {
            e.printStackTrace();
            String m16921b = m16921b();
            f9083a = m16921b;
            return m16921b;
        }
    }

    /* renamed from: a */
    public static boolean m16923a(Context context) {
        String m16924a = m16924a();
        return (m16924a == null || !m16924a.contains(":")) && m16924a != null && m16924a.equals(context.getPackageName());
    }

    /* renamed from: a */
    public static String m16922a(String str) {
        return (TextUtils.isEmpty(str) || !str.contains(":")) ? "main" : str.split(":")[1];
    }
}
