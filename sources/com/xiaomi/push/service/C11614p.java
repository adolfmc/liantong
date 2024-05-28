package com.xiaomi.push.service;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.C11480s;
import com.xiaomi.push.service.C11545am;
import java.util.Locale;

/* renamed from: com.xiaomi.push.service.p */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11614p {

    /* renamed from: a */
    public final int f23729a;

    /* renamed from: a */
    public final String f23730a;

    /* renamed from: b */
    public final String f23731b;

    /* renamed from: c */
    public final String f23732c;

    /* renamed from: d */
    public final String f23733d;

    /* renamed from: e */
    public final String f23734e;

    /* renamed from: f */
    public final String f23735f;

    public C11614p(String str, String str2, String str3, String str4, String str5, String str6, int i) {
        this.f23730a = str;
        this.f23731b = str2;
        this.f23732c = str3;
        this.f23733d = str4;
        this.f23734e = str5;
        this.f23735f = str6;
        this.f23729a = i;
    }

    /* renamed from: b */
    private static boolean m2433b(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }

    /* renamed from: a */
    public C11545am.C11547b m2435a(XMPushService xMPushService) {
        C11545am.C11547b c11547b = new C11545am.C11547b(xMPushService);
        m2434a(c11547b, xMPushService, xMPushService.m2862b(), "c");
        return c11547b;
    }

    /* renamed from: a */
    public C11545am.C11547b m2434a(C11545am.C11547b c11547b, Context context, C11598h c11598h, String str) {
        c11547b.f23529a = context.getPackageName();
        c11547b.f23533b = this.f23730a;
        c11547b.f23540h = this.f23732c;
        c11547b.f23535c = this.f23731b;
        c11547b.f23539g = "5";
        c11547b.f23536d = "XMPUSH-PASS";
        c11547b.f23531a = false;
        C11480s.C11481a c11481a = new C11480s.C11481a();
        c11481a.m2919a("sdk_ver", 48).m2919a("cpvn", "5_9_9-C").m2919a("cpvc", 50909).m2919a("country_code", C11578b.m2591a(context).m2587b()).m2919a("region", C11578b.m2591a(context).m2592a()).m2919a("miui_vn", C11469j.m2961c()).m2919a("miui_vc", Integer.valueOf(C11469j.m2973a(context))).m2919a("xmsf_vc", Integer.valueOf(C11395g.m3720a(context, "com.xiaomi.xmsf"))).m2919a("android_ver", Integer.valueOf(Build.VERSION.SDK_INT)).m2919a("n_belong_to_app", Boolean.valueOf(C11533af.m2761a(context))).m2919a("systemui_vc", Integer.valueOf(C11395g.m3724a(context)));
        String m2437a = m2437a(context);
        if (!TextUtils.isEmpty(m2437a)) {
            c11481a.m2919a("latest_country_code", m2437a);
        }
        String m2959d = C11469j.m2959d();
        if (!TextUtils.isEmpty(m2959d)) {
            c11481a.m2919a("device_ch", m2959d);
        }
        String m2957e = C11469j.m2957e();
        if (!TextUtils.isEmpty(m2957e)) {
            c11481a.m2919a("device_mfr", m2957e);
        }
        c11547b.f23537e = c11481a.toString();
        String str2 = m2433b(context) ? "1000271" : this.f23733d;
        C11480s.C11481a c11481a2 = new C11480s.C11481a();
        c11481a2.m2919a("appid", str2).m2919a("locale", Locale.getDefault().toString()).m2919a("sync", 1);
        if (m2436a(context)) {
            c11481a2.m2919a("ab", str);
        }
        c11547b.f23538f = c11481a2.toString();
        c11547b.f23528a = c11598h;
        return c11547b;
    }

    /* renamed from: a */
    public static boolean m2436a(Context context) {
        return "com.xiaomi.xmsf".equals(context.getPackageName()) && m2438a();
    }

    /* renamed from: a */
    public static boolean m2438a() {
        try {
            return C11479r.m2929a(null, "miui.os.Build").getField("IS_ALPHA_BUILD").getBoolean(null);
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static String m2437a(Context context) {
        if ("com.xiaomi.xmsf".equals(context)) {
            if (TextUtils.isEmpty(null)) {
                String m2968a = C11469j.m2968a("ro.miui.region");
                return TextUtils.isEmpty(m2968a) ? C11469j.m2968a("ro.product.locale.region") : m2968a;
            }
            return null;
        }
        return C11469j.m2966b();
    }
}
