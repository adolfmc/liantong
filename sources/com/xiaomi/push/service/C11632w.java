package com.xiaomi.push.service;

import android.content.Context;
import android.os.Messenger;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.AbstractC11356fa;
import com.xiaomi.push.AbstractC11375fo;
import com.xiaomi.push.C11157an;
import com.xiaomi.push.C11251cs;
import com.xiaomi.push.C11339er;
import com.xiaomi.push.C11368fi;
import com.xiaomi.push.C11419gu;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11448hu;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.InterfaceC11442hq;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.C11574ay;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.w */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C11632w {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2358a(XMPushService xMPushService) {
        C11614p m2430a = C11615q.m2430a(xMPushService.getApplicationContext());
        if (m2430a != null) {
            C11545am.C11547b m2435a = C11615q.m2430a(xMPushService.getApplicationContext()).m2435a(xMPushService);
            AbstractC11049b.m5282a("prepare account. " + m2435a.f23529a);
            m2356a(xMPushService, m2435a);
            C11545am.m2692a().m2686a(m2435a);
            m2355a(xMPushService, m2430a, 172800);
        }
    }

    /* renamed from: a */
    private static void m2355a(final XMPushService xMPushService, final C11614p c11614p, int i) {
        C11574ay.m2605a(xMPushService).m2604a(new C11574ay.AbstractRunnableC11576a("MSAID", i) { // from class: com.xiaomi.push.service.w.1
            @Override // com.xiaomi.push.service.C11574ay.AbstractRunnableC11576a
            /* renamed from: a */
            void mo2345a(C11574ay c11574ay) {
                C11157an m4882a = C11157an.m4882a(xMPushService);
                String m2600a = c11574ay.m2600a("MSAID", "msaid");
                String mo4863a = m4882a.mo4863a();
                if (TextUtils.isEmpty(mo4863a) || TextUtils.equals(m2600a, mo4863a)) {
                    return;
                }
                c11574ay.m2599a("MSAID", "msaid", mo4863a);
                C11430he c11430he = new C11430he();
                c11430he.m3335b(c11614p.f23733d);
                c11430he.m3331c(EnumC11414gp.ClientInfoUpdate.f22745a);
                c11430he.m3344a(C11541aj.m2703a());
                c11430he.m3341a(new HashMap());
                m4882a.m4880a(c11430he.m3350a());
                byte[] m3085a = C11441hp.m3085a(C11632w.m2349a(xMPushService.getPackageName(), c11614p.f23733d, c11430he, EnumC11404gf.Notification));
                XMPushService xMPushService2 = xMPushService;
                xMPushService2.m2866a(xMPushService2.getPackageName(), m3085a, true);
            }
        });
    }

    /* renamed from: a */
    private static String m2359a(C11427hb c11427hb) {
        if (c11427hb.f22975a != null && c11427hb.f22975a.f22822b != null) {
            String str = c11427hb.f22975a.f22822b.get("ext_traffic_source_pkg");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return c11427hb.f22981b;
    }

    /* renamed from: a */
    static C11339er m2352a(C11614p c11614p, Context context, C11427hb c11427hb) {
        try {
            C11339er c11339er = new C11339er();
            c11339er.m3962a(5);
            c11339er.m3943c(c11614p.f23730a);
            c11339er.m3947b(m2359a(c11427hb));
            c11339er.m3956a("SECMSG", "message");
            String str = c11614p.f23730a;
            c11427hb.f22976a.f22841a = str.substring(0, str.indexOf("@"));
            c11427hb.f22976a.f22845c = str.substring(str.indexOf("/") + 1);
            c11339er.m3953a(C11441hp.m3085a(c11427hb), c11614p.f23732c);
            c11339er.m3954a((short) 1);
            AbstractC11049b.m5282a("try send mi push message. packagename:" + c11427hb.f22981b + " action:" + c11427hb.f22974a);
            return c11339er;
        } catch (NullPointerException e) {
            AbstractC11049b.m5276a(e);
            return null;
        }
    }

    /* renamed from: a */
    static C11339er m2353a(XMPushService xMPushService, byte[] bArr) {
        C11427hb c11427hb = new C11427hb();
        try {
            C11441hp.m3084a(c11427hb, bArr);
            return m2352a(C11615q.m2430a((Context) xMPushService), xMPushService, c11427hb);
        } catch (C11448hu e) {
            AbstractC11049b.m5276a(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static <T extends InterfaceC11442hq<T, ?>> C11427hb m2349a(String str, String str2, T t, EnumC11404gf enumC11404gf) {
        return m2348a(str, str2, t, enumC11404gf, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static <T extends InterfaceC11442hq<T, ?>> C11427hb m2346b(String str, String str2, T t, EnumC11404gf enumC11404gf) {
        return m2348a(str, str2, t, enumC11404gf, false);
    }

    /* renamed from: a */
    private static <T extends InterfaceC11442hq<T, ?>> C11427hb m2348a(String str, String str2, T t, EnumC11404gf enumC11404gf, boolean z) {
        byte[] m3085a = C11441hp.m3085a(t);
        C11427hb c11427hb = new C11427hb();
        C11419gu c11419gu = new C11419gu();
        c11419gu.f22840a = 5L;
        c11419gu.f22841a = "fakeid";
        c11427hb.m3381a(c11419gu);
        c11427hb.m3377a(ByteBuffer.wrap(m3085a));
        c11427hb.m3383a(enumC11404gf);
        c11427hb.m3371b(z);
        c11427hb.m3372b(str);
        c11427hb.m3376a(false);
        c11427hb.m3378a(str2);
        return c11427hb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C11427hb m2350a(String str, String str2) {
        C11430he c11430he = new C11430he();
        c11430he.m3335b(str2);
        c11430he.m3331c("package uninstalled");
        c11430he.m3344a(AbstractC11375fo.m3791i());
        c11430he.m3340a(false);
        return m2349a(str, str2, c11430he, EnumC11404gf.Notification);
    }

    /* renamed from: b */
    static C11427hb m2347b(String str, String str2) {
        C11430he c11430he = new C11430he();
        c11430he.m3335b(str2);
        c11430he.m3331c(EnumC11414gp.AppDataCleared.f22745a);
        c11430he.m3344a(C11541aj.m2703a());
        c11430he.m3340a(false);
        return m2349a(str, str2, c11430he, EnumC11404gf.Notification);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2356a(final XMPushService xMPushService, C11545am.C11547b c11547b) {
        c11547b.m2672a((Messenger) null);
        c11547b.m2671a(new C11545am.C11547b.InterfaceC11549a() { // from class: com.xiaomi.push.service.w.2
            @Override // com.xiaomi.push.service.C11545am.C11547b.InterfaceC11549a
            /* renamed from: a */
            public void mo2344a(C11545am.EnumC11554c enumC11554c, C11545am.EnumC11554c enumC11554c2, int i) {
                if (enumC11554c2 == C11545am.EnumC11554c.binded) {
                    C11620t.m2404a(XMPushService.this, true);
                    C11620t.m2405a(XMPushService.this);
                } else if (enumC11554c2 == C11545am.EnumC11554c.unbind) {
                    AbstractC11049b.m5282a("onChange unbind");
                    C11620t.m2407a(XMPushService.this, 70000001, " the push is not connected.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2354a(XMPushService xMPushService, String str, byte[] bArr) {
        C11251cs.m4463a(str, xMPushService.getApplicationContext(), bArr);
        AbstractC11356fa m2902a = xMPushService.m2902a();
        if (m2902a != null) {
            if (m2902a.mo3896a()) {
                C11339er m2353a = m2353a(xMPushService, bArr);
                if (m2353a != null) {
                    m2902a.mo3880b(m2353a);
                    return;
                } else {
                    C11620t.m2406a(xMPushService, str, bArr, 70000003, "not a valid message");
                    return;
                }
            }
            throw new C11368fi("Don't support XMPP connection.");
        }
        throw new C11368fi("try send msg while connection is null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2357a(XMPushService xMPushService, C11427hb c11427hb) {
        C11251cs.m4465a(c11427hb.m3374b(), xMPushService.getApplicationContext(), c11427hb, -1);
        AbstractC11356fa m2902a = xMPushService.m2902a();
        if (m2902a != null) {
            if (m2902a.mo3896a()) {
                C11339er m2352a = m2352a(C11615q.m2430a((Context) xMPushService), xMPushService, c11427hb);
                if (m2352a != null) {
                    m2902a.mo3880b(m2352a);
                    return;
                }
                return;
            }
            throw new C11368fi("Don't support XMPP connection.");
        }
        throw new C11368fi("try send msg while connection is null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m2351a(String str) {
        return str + ".permission.MIPUSH_RECEIVE";
    }
}
