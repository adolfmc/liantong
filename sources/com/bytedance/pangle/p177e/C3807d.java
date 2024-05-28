package com.bytedance.pangle.p177e;

import android.os.Build;
import android.support.annotation.NonNull;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3792c;
import com.bytedance.pangle.p177e.C3811f;
import java.io.File;

/* renamed from: com.bytedance.pangle.e.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3807d implements C3811f.InterfaceC3814a {
    /* renamed from: a */
    private static boolean m16898a(@NonNull String str, @NonNull String str2) {
        try {
            return C3803a.m16908a(C3805b.m16903a(str, str2, C3805b.f9104b));
        } catch (Exception unused) {
            return false;
        }
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
    public static java.lang.String d1672829046108dc(java.lang.String r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p177e.C3807d.d1672829046108dc(java.lang.String):java.lang.String");
    }

    @Override // com.bytedance.pangle.p177e.C3811f.InterfaceC3814a
    /* renamed from: a */
    public final boolean mo16892a(String str, int i) {
        String m16934b = C3792c.m16934b(str, i);
        ZeusLogger.m16794d("Zeus/install_pangle", "full DexOpt:".concat(String.valueOf(m16934b)));
        String m16932c = C3792c.m16932c(str, i);
        String str2 = m16932c + File.separator + "compFully" + C3805b.m16900b(m16934b);
        String str3 = m16932c + File.separator + C3805b.m16905a(m16934b);
        if (m16898a(m16934b, str2)) {
            File file = new File(str2);
            if (file.exists()) {
                file.renameTo(new File(str3));
            }
            String str4 = Build.VERSION.SDK_INT >= 26 ? ".odex" : ".dex";
            File file2 = new File(str2.replace(str4, ".vdex"));
            if (file2.exists()) {
                file2.renameTo(new File(str3.replace(str4, ".vdex")));
            }
            boolean m16901a = C3805b.m16901a(str3);
            ZeusLogger.m16792i("Zeus/install_pangle", "full DexOpt result:".concat(String.valueOf(m16901a)));
            return m16901a;
        }
        return false;
    }
}
