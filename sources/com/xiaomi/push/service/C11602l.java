package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11180ay;
import com.xiaomi.push.C11425h;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11432hg;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11448hu;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.l */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11602l {
    /* renamed from: a */
    static void m2521a(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("ext_fcm_container_buffer");
        String stringExtra2 = intent.getStringExtra("mipush_app_package");
        if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
            return;
        }
        try {
            byte[] m2517b = m2517b(Base64.decode(stringExtra, 2), context.getSharedPreferences("mipush_apps_scrt", 0).getString(stringExtra2, null));
            if (m2517b != null) {
                C11635x.m2335a(context, C11621u.m2383a(m2517b), m2517b);
            } else {
                AbstractC11049b.m5282a("notify fcm notification error ：dencrypt failed");
            }
        } catch (Throwable th) {
            AbstractC11049b.m5279a("notify fcm notification error ", th);
        }
    }

    /* renamed from: a */
    public static byte[] m2518a(byte[] bArr, String str) {
        if (TextUtils.isEmpty(str)) {
            AbstractC11049b.m5282a("secret is empty, return null");
            return null;
        }
        try {
            return C11425h.m3409b(C11180ay.m4796a(str), bArr);
        } catch (Exception e) {
            AbstractC11049b.m5279a("encryption error. ", e);
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m2517b(byte[] bArr, String str) {
        if (TextUtils.isEmpty(str)) {
            AbstractC11049b.m5282a("secret is empty, return null");
            return null;
        }
        try {
            return C11425h.m3410a(C11180ay.m4796a(str), bArr);
        } catch (Exception e) {
            AbstractC11049b.m5279a("dencryption error. ", e);
            return null;
        }
    }

    /* renamed from: a */
    public static C11432hg m2519a(C11427hb c11427hb) {
        byte[] m3384a = c11427hb.m3384a();
        C11432hg c11432hg = new C11432hg();
        try {
            C11441hp.m3084a(c11432hg, m3384a);
            return c11432hg;
        } catch (C11448hu unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static void m2520a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        context.getSharedPreferences("mipush_apps_scrt", 0).edit().putString(str, str2).apply();
    }
}
