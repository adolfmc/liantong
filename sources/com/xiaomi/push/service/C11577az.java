package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11184bb;
import com.xiaomi.push.C11407gi;
import com.xiaomi.push.C11408gj;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.C11647w;
import com.xiaomi.push.EnumC11414gp;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.az */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11577az {

    /* renamed from: a */
    private static AtomicLong f23647a = new AtomicLong(0);

    /* renamed from: a */
    private static SimpleDateFormat f23646a = new SimpleDateFormat("yyyy/MM/dd");

    /* renamed from: a */
    private static String f23645a = f23646a.format(Long.valueOf(System.currentTimeMillis()));

    /* renamed from: a */
    public static synchronized String m2598a() {
        String str;
        synchronized (C11577az.class) {
            String format = f23646a.format(Long.valueOf(System.currentTimeMillis()));
            if (!TextUtils.equals(f23645a, format)) {
                f23647a.set(0L);
                f23645a = format;
            }
            str = format + "-" + f23647a.incrementAndGet();
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006c  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.xiaomi.push.C11430he> m2593a(java.util.List<com.xiaomi.push.C11408gj> r10, java.lang.String r11, java.lang.String r12, int r13) {
        /*
            r0 = 0
            if (r10 != 0) goto L9
            java.lang.String r10 = "requests can not be null in TinyDataHelper.transToThriftObj()."
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d(r10)
            return r0
        L9:
            int r1 = r10.size()
            if (r1 != 0) goto L15
            java.lang.String r10 = "requests.length is 0 in TinyDataHelper.transToThriftObj()."
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d(r10)
            return r0
        L15:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.xiaomi.push.gi r2 = new com.xiaomi.push.gi
            r2.<init>()
            r3 = 0
            r4 = r2
            r2 = r3
            r5 = r2
        L23:
            int r6 = r10.size()
            if (r2 >= r6) goto Lb2
            java.lang.Object r6 = r10.get(r2)
            com.xiaomi.push.gj r6 = (com.xiaomi.push.C11408gj) r6
            if (r6 != 0) goto L33
            goto Lae
        L33:
            java.util.Map r7 = r6.m3671a()
            if (r7 == 0) goto L76
            java.util.Map r7 = r6.m3671a()
            java.lang.String r8 = "item_size"
            boolean r7 = r7.containsKey(r8)
            if (r7 == 0) goto L76
            java.util.Map r7 = r6.m3671a()
            java.lang.String r8 = "item_size"
            java.lang.Object r7 = r7.get(r8)
            java.lang.String r7 = (java.lang.String) r7
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L5c
            int r7 = java.lang.Integer.parseInt(r7)     // Catch: java.lang.Exception -> L5c
            goto L5d
        L5c:
            r7 = r3
        L5d:
            java.util.Map r8 = r6.m3671a()
            int r8 = r8.size()
            r9 = 1
            if (r8 != r9) goto L6c
            r6.m3663a(r0)
            goto L77
        L6c:
            java.util.Map r8 = r6.m3671a()
            java.lang.String r9 = "item_size"
            r8.remove(r9)
            goto L77
        L76:
            r7 = r3
        L77:
            if (r7 > 0) goto L7e
            byte[] r7 = com.xiaomi.push.C11441hp.m3085a(r6)
            int r7 = r7.length
        L7e:
            if (r7 <= r13) goto L99
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "TinyData is too big, ignore upload request item:"
            r7.append(r8)
            java.lang.String r6 = r6.m3651d()
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d(r6)
            goto Lae
        L99:
            int r8 = r5 + r7
            if (r8 <= r13) goto Laa
            com.xiaomi.push.he r4 = m2594a(r11, r12, r4)
            r1.add(r4)
            com.xiaomi.push.gi r4 = new com.xiaomi.push.gi
            r4.<init>()
            r5 = r3
        Laa:
            r4.m3674a(r6)
            int r5 = r5 + r7
        Lae:
            int r2 = r2 + 1
            goto L23
        Lb2:
            int r10 = r4.m3679a()
            if (r10 == 0) goto Lbf
            com.xiaomi.push.he r10 = m2594a(r11, r12, r4)
            r1.add(r10)
        Lbf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11577az.m2593a(java.util.List, java.lang.String, java.lang.String, int):java.util.ArrayList");
    }

    /* renamed from: a */
    private static C11430he m2594a(String str, String str2, C11407gi c11407gi) {
        return new C11430he("-1", false).m3327d(str).m3335b(str2).m3338a(C11647w.m2267a(C11441hp.m3085a(c11407gi))).m3331c(EnumC11414gp.UploadTinyData.f22745a);
    }

    /* renamed from: a */
    public static boolean m2596a(C11408gj c11408gj, boolean z) {
        if (c11408gj == null) {
            AbstractC11049b.m5282a("item is null, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (!z && TextUtils.isEmpty(c11408gj.f22514a)) {
            AbstractC11049b.m5282a("item.channel is null or empty, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (TextUtils.isEmpty(c11408gj.f22521d)) {
            AbstractC11049b.m5282a("item.category is null or empty, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (TextUtils.isEmpty(c11408gj.f22520c)) {
            AbstractC11049b.m5282a("item.name is null or empty, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (!C11184bb.m4756a(c11408gj.f22521d)) {
            AbstractC11049b.m5282a("item.category can only contain ascii char, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (!C11184bb.m4756a(c11408gj.f22520c)) {
            AbstractC11049b.m5282a("item.name can only contain ascii char, verfiy ClientUploadDataItem failed.");
            return true;
        } else if (c11408gj.f22519b == null || c11408gj.f22519b.length() <= 30720) {
            return false;
        } else {
            AbstractC11049b.m5282a("item.data is too large(" + c11408gj.f22519b.length() + "), max size for data is 30720 , verfiy ClientUploadDataItem failed.");
            return true;
        }
    }

    /* renamed from: a */
    public static void m2597a(Context context, String str, String str2, long j, String str3) {
        C11408gj c11408gj = new C11408gj();
        c11408gj.m3649d(str);
        c11408gj.m3653c(str2);
        c11408gj.m3668a(j);
        c11408gj.m3657b(str3);
        c11408gj.m3665a("push_sdk_channel");
        c11408gj.m3642g(context.getPackageName());
        c11408gj.m3646e(context.getPackageName());
        c11408gj.m3662a(true);
        c11408gj.m3658b(System.currentTimeMillis());
        c11408gj.m3644f(m2598a());
        C11579ba.m2584a(context, c11408gj);
    }

    /* renamed from: a */
    public static boolean m2595a(String str) {
        return !C11479r.m2927b() || "com.miui.hybrid".equals(str);
    }
}
