package com.p281qq.p282e.comm;

import android.content.Context;
import android.content.Intent;
import com.p281qq.p282e.comm.util.GDTLogger;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6871a {
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0093, code lost:
        com.p281qq.p282e.comm.util.GDTLogger.m8234e(java.lang.String.format("Service[%s]需要在AndroidManifest.xml中声明", r4.getName()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ad, code lost:
        r8 = false;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m8283a(android.content.Context r8) {
        /*
            r0 = 0
            java.lang.String r1 = "android.permission.INTERNET"
            java.lang.String r2 = "android.permission.ACCESS_NETWORK_STATE"
            java.lang.String r3 = "android.permission.ACCESS_WIFI_STATE"
            java.lang.String[] r1 = new java.lang.String[]{r1, r2, r3}     // Catch: java.lang.Throwable -> Lb4
            r2 = r0
        Lc:
            r3 = 1
            r4 = 3
            if (r2 >= r4) goto L33
            r4 = r1[r2]     // Catch: java.lang.Throwable -> L2a
            int r5 = r8.checkCallingOrSelfPermission(r4)     // Catch: java.lang.Throwable -> L2a
            r6 = -1
            if (r5 != r6) goto L27
            java.lang.String r1 = "Permission[%s]需要在AndroidManifest.xml中声明"
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L2a
            r2[r0] = r4     // Catch: java.lang.Throwable -> L2a
            java.lang.String r1 = java.lang.String.format(r1, r2)     // Catch: java.lang.Throwable -> L2a
            com.p281qq.p282e.comm.util.GDTLogger.m8234e(r1)     // Catch: java.lang.Throwable -> L2a
            goto L31
        L27:
            int r2 = r2 + 1
            goto Lc
        L2a:
            r1 = move-exception
            java.lang.String r2 = "检查权限时发生异常"
            com.p281qq.p282e.comm.util.GDTLogger.m8233e(r2, r1)     // Catch: java.lang.Throwable -> Lb4
        L31:
            r1 = r0
            goto L34
        L33:
            r1 = r3
        L34:
            if (r1 == 0) goto Lb3
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r2 = com.p281qq.p282e.comm.constants.CustomPkgConstants.getADActivityName()     // Catch: java.lang.Throwable -> Lb4
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.Throwable -> Lb4
            r1[r0] = r2     // Catch: java.lang.Throwable -> Lb4
            boolean r1 = m8282a(r8, r1)     // Catch: java.lang.Throwable -> Lb4
            if (r1 == 0) goto Lb3
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r2 = com.p281qq.p282e.comm.constants.CustomPkgConstants.getPortraitADActivityName()     // Catch: java.lang.Throwable -> Lb4
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.Throwable -> Lb4
            r1[r0] = r2     // Catch: java.lang.Throwable -> Lb4
            boolean r1 = m8282a(r8, r1)     // Catch: java.lang.Throwable -> Lb4
            if (r1 == 0) goto Lb3
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r2 = com.p281qq.p282e.comm.constants.CustomPkgConstants.getLandscapeADActivityName()     // Catch: java.lang.Throwable -> Lb4
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.Throwable -> Lb4
            r1[r0] = r2     // Catch: java.lang.Throwable -> Lb4
            boolean r1 = m8282a(r8, r1)     // Catch: java.lang.Throwable -> Lb4
            if (r1 == 0) goto Lb3
            java.lang.Class[] r1 = new java.lang.Class[r3]     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r2 = com.p281qq.p282e.comm.constants.CustomPkgConstants.getDownLoadServiceName()     // Catch: java.lang.Throwable -> Lb4
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.Throwable -> Lb4
            r1[r0] = r2     // Catch: java.lang.Throwable -> Lb4
            r2 = r0
        L79:
            if (r2 >= r3) goto Laf
            r4 = r1[r2]     // Catch: java.lang.Throwable -> La6
            android.content.Intent r5 = new android.content.Intent     // Catch: java.lang.Throwable -> La6
            r5.<init>()     // Catch: java.lang.Throwable -> La6
            r5.setClass(r8, r4)     // Catch: java.lang.Throwable -> La6
            android.content.pm.PackageManager r6 = r8.getPackageManager()     // Catch: java.lang.Throwable -> La6
            r7 = 65536(0x10000, float:9.18355E-41)
            android.content.pm.ResolveInfo r5 = r6.resolveService(r5, r7)     // Catch: java.lang.Throwable -> La6
            if (r5 != 0) goto La3
            java.lang.String r8 = "Service[%s]需要在AndroidManifest.xml中声明"
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La6
            java.lang.String r2 = r4.getName()     // Catch: java.lang.Throwable -> La6
            r1[r0] = r2     // Catch: java.lang.Throwable -> La6
            java.lang.String r8 = java.lang.String.format(r8, r1)     // Catch: java.lang.Throwable -> La6
            com.p281qq.p282e.comm.util.GDTLogger.m8234e(r8)     // Catch: java.lang.Throwable -> La6
            goto Lad
        La3:
            int r2 = r2 + 1
            goto L79
        La6:
            r8 = move-exception
            java.lang.String r1 = "检查Service时发生异常"
            com.p281qq.p282e.comm.util.GDTLogger.m8233e(r1, r8)     // Catch: java.lang.Throwable -> Lb4
        Lad:
            r8 = r0
            goto Lb0
        Laf:
            r8 = r3
        Lb0:
            if (r8 == 0) goto Lb3
            r0 = r3
        Lb3:
            return r0
        Lb4:
            r8 = move-exception
            java.lang.String r1 = "检查AndroidManifest.xml时发生异常"
            com.p281qq.p282e.comm.util.GDTLogger.m8233e(r1, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p281qq.p282e.comm.C6871a.m8283a(android.content.Context):boolean");
    }

    /* renamed from: a */
    private static boolean m8282a(Context context, Class<?>... clsArr) {
        for (int i = 0; i < clsArr.length; i++) {
            try {
                Intent intent = new Intent();
                intent.setClass(context, clsArr[i]);
                if (context.getPackageManager().resolveActivity(intent, 65536) == null) {
                    GDTLogger.m8234e(String.format("Activity[%s]需要在AndroidManifest.xml中声明", clsArr[i].getName()));
                    return false;
                }
            } catch (Throwable th) {
                GDTLogger.m8233e("检查Activity时发生异常", th);
                return false;
            }
        }
        return true;
    }
}
