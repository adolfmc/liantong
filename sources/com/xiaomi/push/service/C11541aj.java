package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11184bb;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.aj */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11541aj {

    /* renamed from: a */
    private static long f23513a = 0;

    /* renamed from: a */
    private static String f23514a = "";

    /* renamed from: a */
    public static String m2703a() {
        if (TextUtils.isEmpty(f23514a)) {
            f23514a = C11184bb.m4758a(4);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f23514a);
        long j = f23513a;
        f23513a = 1 + j;
        sb.append(j);
        return sb.toString();
    }

    /* renamed from: b */
    public static String m2701b() {
        return C11184bb.m4758a(32);
    }

    /* renamed from: a */
    public static String m2702a(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 32) {
            return str;
        }
        try {
            return "BlockId_" + str.substring(8);
        } catch (Exception e) {
            AbstractC11049b.m5268d("Exception occurred when filtering registration packet id for log. " + e);
            return "UnexpectedId";
        }
    }
}
