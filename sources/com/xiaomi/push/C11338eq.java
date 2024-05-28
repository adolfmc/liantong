package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.service.C11545am;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.eq */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C11338eq {
    /* renamed from: a */
    public static void m3970a(C11545am.C11547b c11547b, String str, AbstractC11356fa abstractC11356fa) {
        String m4789a;
        C11289dp.C11292c c11292c = new C11289dp.C11292c();
        if (!TextUtils.isEmpty(c11547b.f23535c)) {
            c11292c.m4260a(c11547b.f23535c);
        }
        if (!TextUtils.isEmpty(c11547b.f23537e)) {
            c11292c.m4251d(c11547b.f23537e);
        }
        if (!TextUtils.isEmpty(c11547b.f23538f)) {
            c11292c.m4248e(c11547b.f23538f);
        }
        c11292c.m4257b(c11547b.f23531a ? "1" : "0");
        if (!TextUtils.isEmpty(c11547b.f23536d)) {
            c11292c.m4254c(c11547b.f23536d);
        } else {
            c11292c.m4254c("XIAOMI-SASL");
        }
        C11339er c11339er = new C11339er();
        c11339er.m3943c(c11547b.f23533b);
        c11339er.m3962a(Integer.parseInt(c11547b.f23539g));
        c11339er.m3947b(c11547b.f23529a);
        c11339er.m3956a("BIND", (String) null);
        c11339er.m3958a(c11339er.m3941e());
        AbstractC11049b.m5282a("[Slim]: bind id=" + c11339er.m3941e());
        HashMap hashMap = new HashMap();
        hashMap.put("challenge", str);
        hashMap.put("token", c11547b.f23535c);
        hashMap.put("chid", c11547b.f23539g);
        hashMap.put("from", c11547b.f23533b);
        hashMap.put("id", c11339er.m3941e());
        hashMap.put("to", "xiaomi.com");
        if (c11547b.f23531a) {
            hashMap.put("kick", "1");
        } else {
            hashMap.put("kick", "0");
        }
        if (!TextUtils.isEmpty(c11547b.f23537e)) {
            hashMap.put("client_attrs", c11547b.f23537e);
        } else {
            hashMap.put("client_attrs", "");
        }
        if (!TextUtils.isEmpty(c11547b.f23538f)) {
            hashMap.put("cloud_attrs", c11547b.f23538f);
        } else {
            hashMap.put("cloud_attrs", "");
        }
        if (c11547b.f23536d.equals("XIAOMI-PASS") || c11547b.f23536d.equals("XMPUSH-PASS")) {
            m4789a = C11181az.m4789a(c11547b.f23536d, null, hashMap, c11547b.f23540h);
        } else {
            c11547b.f23536d.equals("XIAOMI-SASL");
            m4789a = null;
        }
        c11292c.m4245f(m4789a);
        c11339er.m3953a(c11292c.mo4063a(), (String) null);
        abstractC11356fa.mo3880b(c11339er);
    }

    /* renamed from: a */
    public static void m3969a(String str, String str2, AbstractC11356fa abstractC11356fa) {
        C11339er c11339er = new C11339er();
        c11339er.m3943c(str2);
        c11339er.m3962a(Integer.parseInt(str));
        c11339er.m3956a("UBND", (String) null);
        abstractC11356fa.mo3880b(c11339er);
    }
}
