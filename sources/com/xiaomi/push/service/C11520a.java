package com.xiaomi.push.service;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.service.C11534ag;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11520a {

    /* renamed from: a */
    private static final SparseArray<C11534ag.C11536a<String, String, String>> f23470a = new SparseArray<C11534ag.C11536a<String, String, String>>(6) { // from class: com.xiaomi.push.service.a.1
        {
            put(1, C11534ag.f23502g);
            put(2, C11534ag.f23501f);
            put(4, C11534ag.f23500e);
            put(8, C11534ag.f23497b);
            put(16, C11534ag.f23498c);
            put(32, C11534ag.f23503h);
        }
    };

    /* renamed from: a */
    private static int m2822a(String str, int i) {
        return C11534ag.m2731a(C11479r.m2934a(), str, null, f23470a.get(i));
    }

    /* renamed from: a */
    private static Bundle m2823a(String str) {
        return C11534ag.m2732a(C11479r.m2934a(), str, (String) null);
    }

    /* renamed from: a */
    public static int m2824a(Context context, String str) {
        int i = 0;
        if (context != null && !TextUtils.isEmpty(str)) {
            C11395g.EnumC11397b m3714a = C11395g.m3714a(context, str, true);
            if (m3714a == C11395g.EnumC11397b.ALLOWED) {
                i = 1;
            } else if (m3714a == C11395g.EnumC11397b.NOT_ALLOWED) {
                i = 2;
            }
            if (C11534ag.m2745a()) {
                Bundle m2823a = m2823a(str);
                if (m2823a.containsKey(C11534ag.f23502g.f23506c)) {
                    i |= m2823a.getBoolean(C11534ag.f23502g.f23506c) ? 4 : 8;
                }
                if (m2823a.containsKey(C11534ag.f23500e.f23506c)) {
                    i |= m2823a.getBoolean(C11534ag.f23500e.f23506c) ? 16 : 32;
                }
                if (m2823a.containsKey(C11534ag.f23501f.f23506c)) {
                    i |= m2823a.getBoolean(C11534ag.f23501f.f23506c) ? 64 : 128;
                }
                if (m2823a.containsKey(C11534ag.f23497b.f23506c)) {
                    i |= m2823a.getBoolean(C11534ag.f23497b.f23506c) ? 256 : 512;
                }
                if (m2823a.containsKey(C11534ag.f23498c.f23506c)) {
                    i |= m2823a.getBoolean(C11534ag.f23498c.f23506c) ? 1024 : 2048;
                }
                if (m2823a.containsKey(C11534ag.f23503h.f23506c)) {
                    return i | (m2823a.getBoolean(C11534ag.f23503h.f23506c) ? 4096 : 8192);
                }
                return i;
            }
            int m2822a = m2822a(str, 1);
            if (m2822a == 1) {
                i |= 4;
            } else if (m2822a == 0) {
                i |= 8;
            }
            int m2822a2 = m2822a(str, 4);
            if (m2822a2 == 1) {
                i |= 16;
            } else if (m2822a2 == 0) {
                i |= 32;
            }
            int m2822a3 = m2822a(str, 2);
            if (m2822a3 == 1) {
                i |= 64;
            } else if (m2822a3 == 0) {
                i |= 128;
            }
            int m2822a4 = m2822a(str, 8);
            if (m2822a4 == 1) {
                i |= 256;
            } else if (m2822a4 == 0) {
                i |= 512;
            }
            int m2822a5 = m2822a(str, 16);
            if (m2822a5 == 1) {
                i |= 1024;
            } else if (m2822a5 == 0) {
                i |= 2048;
            }
            int m2822a6 = m2822a(str, 32);
            return m2822a6 == 1 ? i | 4096 : m2822a6 == 0 ? i | 8192 : i;
        }
        AbstractC11049b.m5282a("context | packageName must not be null");
        return 0;
    }
}
