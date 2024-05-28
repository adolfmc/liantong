package com.bytedance.pangle.util;

import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.i */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3950i {
    /* renamed from: a */
    public static boolean m16623a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    /* renamed from: b */
    public static boolean m16622b() {
        return Build.VERSION.SDK_INT < 21;
    }

    /* renamed from: c */
    public static boolean m16621c() {
        return Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 23;
    }

    /* renamed from: d */
    public static boolean m16620d() {
        return Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 25;
    }

    /* renamed from: e */
    public static boolean m16619e() {
        return Build.VERSION.SDK_INT >= 26 && Build.VERSION.SDK_INT <= 28;
    }

    /* renamed from: f */
    public static boolean m16618f() {
        return Build.VERSION.SDK_INT >= 29 && Build.VERSION.SDK_INT <= 30;
    }

    /* renamed from: g */
    public static boolean m16617g() {
        return Build.VERSION.SDK_INT > 23;
    }

    /* renamed from: h */
    public static boolean m16616h() {
        return Build.VERSION.SDK_INT >= 26;
    }

    /* renamed from: i */
    public static boolean m16615i() {
        if (Build.VERSION.SDK_INT < 28) {
            return Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT > 0;
        }
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        r1 = (99 * 99) - ((18 * 18) * 34);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0000, code lost:
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String i1672829046107dc(java.lang.String r4) {
        /*
        L0:
            r0 = 74
            r1 = 55
        L4:
            r2 = 0
            switch(r0) {
                case 72: goto L0;
                case 73: goto L9;
                case 74: goto Lc;
                default: goto L8;
            }
        L8:
            goto L54
        L9:
            switch(r1) {
                case 94: goto L4f;
                case 95: goto L10;
                case 96: goto L39;
                default: goto Lc;
            }
        Lc:
            switch(r1) {
                case 55: goto L4f;
                case 56: goto L4f;
                case 57: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L4f
        L10:
            r0 = 18
            r1 = 1
            switch(r1) {
                case 60: goto L17;
                case 61: goto L23;
                case 62: goto L30;
                default: goto L16;
            }
        L16:
            goto L39
        L17:
            int r3 = 0 - r1
            int r3 = r3 * r2
            r2 = 0
            int r2 = r2 * 2
            int r2 = r2 - r1
            int r3 = r3 * r2
            int r3 = r3 % 6
            if (r3 == 0) goto L0
        L23:
            int r2 = 18 - r1
            int r2 = r2 * r0
            r3 = 18
            int r3 = r3 * 2
            int r3 = r3 - r1
            int r2 = r2 * r3
            int r2 = r2 % 6
            if (r2 == 0) goto L4f
        L30:
            r1 = 99
            int r1 = r1 * r1
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r1 = r1 - r0
            r0 = -1
            goto L0
        L39:
            char[] r4 = r4.toCharArray()
        L3d:
            int r0 = r4.length
            if (r2 >= r0) goto L49
            char r0 = r4[r2]
            r0 = r0 ^ r2
            char r0 = (char) r0
            r4[r2] = r0
            int r2 = r2 + 1
            goto L3d
        L49:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            return r0
        L4f:
            r0 = 73
            r1 = 96
            goto L4
        L54:
            r0 = 72
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.util.C3950i.i1672829046107dc(java.lang.String):java.lang.String");
    }

    /* renamed from: j */
    public static boolean m16614j() {
        if (Build.VERSION.SDK_INT < 31) {
            return Build.VERSION.SDK_INT == 30 && Build.VERSION.PREVIEW_SDK_INT > 0;
        }
        return true;
    }
}
