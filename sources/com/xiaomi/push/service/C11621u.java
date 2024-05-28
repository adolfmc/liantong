package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.AbstractC11375fo;
import com.xiaomi.push.C11305dt;
import com.xiaomi.push.C11339er;
import com.xiaomi.push.C11368fi;
import com.xiaomi.push.C11371fl;
import com.xiaomi.push.C11374fn;
import com.xiaomi.push.C11392fz;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11420gv;
import com.xiaomi.push.C11421gw;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.C11635x;
import com.xiaomi.push.service.XMPushService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: com.xiaomi.push.service.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11621u {
    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: AGET  (r16 I:short A[IMMUTABLE_TYPE]) = (r176 I:short[] A[IMMUTABLE_TYPE]), (r0 I:??[int, short, byte, char]), expected to be less than 21
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    /* renamed from: a */
    public static void m2386a(com.xiaomi.push.service.XMPushService r17, java.lang.String r18, byte[] r19, android.content.Intent r20) {
        /*
            Method dump skipped, instructions count: 1266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11621u.m2386a(com.xiaomi.push.service.XMPushService, java.lang.String, byte[], android.content.Intent):void");
    }

    /* renamed from: a */
    public void m2397a(Context context, C11545am.C11547b c11547b, boolean z, int i, String str) {
        C11614p m2430a;
        if (z || (m2430a = C11615q.m2430a(context)) == null || !"token-expired".equals(str)) {
            return;
        }
        C11615q.m2424a(context, m2430a.f23735f, m2430a.f23733d, m2430a.f23734e);
    }

    /* renamed from: a */
    public void m2392a(XMPushService xMPushService, AbstractC11375fo abstractC11375fo, C11545am.C11547b c11547b) {
        if (abstractC11375fo instanceof C11374fn) {
            C11374fn c11374fn = (C11374fn) abstractC11375fo;
            C11371fl a = c11374fn.m3819a("s");
            if (a != null) {
                try {
                    m2385a(xMPushService, C11561ar.m2645a(C11561ar.m2648a(c11547b.f23540h, c11374fn.m3790j()), a.m3824c()), C11392fz.m3730a(abstractC11375fo.mo3775a()));
                    return;
                } catch (IllegalArgumentException e) {
                    AbstractC11049b.m5276a(e);
                    return;
                }
            }
            return;
        }
        AbstractC11049b.m5282a("not a mipush message");
    }

    /* renamed from: a */
    public void m2393a(XMPushService xMPushService, C11339er c11339er, C11545am.C11547b c11547b) {
        try {
            byte[] m3957a = c11339er.m3957a(c11547b.f23540h);
            HashMap hashMap = null;
            if (AbstractC11590e.m2558b(c11339er)) {
                hashMap = new HashMap();
                hashMap.put("t_im", String.valueOf(c11339er.m3951b()));
                hashMap.put("t_rt", String.valueOf(c11339er.m3967a()));
            }
            m2384a(xMPushService, m3957a, c11339er.mo3914c(), hashMap);
        } catch (IllegalArgumentException e) {
            AbstractC11049b.m5276a(e);
        }
    }

    /* renamed from: a */
    private static void m2385a(XMPushService xMPushService, byte[] bArr, long j) {
        m2384a(xMPushService, bArr, j, (Map<String, String>) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x026d  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m2384a(com.xiaomi.push.service.XMPushService r19, byte[] r20, long r21, java.util.Map<java.lang.String, java.lang.String> r23) {
        /*
            Method dump skipped, instructions count: 664
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11621u.m2384a(com.xiaomi.push.service.XMPushService, byte[], long, java.util.Map):void");
    }

    /* renamed from: a */
    public static Intent m2382a(byte[] bArr, long j) {
        C11427hb m2383a = m2383a(bArr);
        if (m2383a == null) {
            return null;
        }
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mrt", Long.toString(j));
        intent.setPackage(m2383a.f22981b);
        return intent;
    }

    /* renamed from: a */
    public static C11427hb m2383a(byte[] bArr) {
        C11427hb c11427hb = new C11427hb();
        try {
            C11441hp.m3084a(c11427hb, bArr);
            return c11427hb;
        } catch (Throwable th) {
            AbstractC11049b.m5276a(th);
            return null;
        }
    }

    /* renamed from: a */
    public static void m2398a(Context context, C11427hb c11427hb, byte[] bArr) {
        try {
            C11635x.C11639c m2335a = C11635x.m2335a(context, c11427hb, bArr);
            if (m2335a.f23789a > 0 && !TextUtils.isEmpty(m2335a.f23790a)) {
                C11392fz.m3733a(context, m2335a.f23790a, m2335a.f23789a, true, false, System.currentTimeMillis());
            }
            if (C11469j.m2972a(context) && C11629v.m2370a(context, c11427hb, m2335a.f23791a)) {
                C11629v.m2372a(context, c11427hb);
                AbstractC11049b.m5282a("consume this broadcast by tts");
                return;
            }
            m2381b(context, c11427hb, bArr);
        } catch (Exception e) {
            AbstractC11049b.m5282a("notify push msg error " + e);
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private static void m2381b(Context context, C11427hb c11427hb, byte[] bArr) {
        if (C11635x.m2311a(c11427hb)) {
            return;
        }
        String m2312a = C11635x.m2312a(c11427hb);
        if (TextUtils.isEmpty(m2312a) || m2395a(context, m2312a, bArr)) {
            return;
        }
        C11305dt.m4117a(context).m4109b(m2312a, C11635x.m2298b(c11427hb), c11427hb.m3388a().m3559a(), "1");
    }

    /* renamed from: a */
    public static boolean m2395a(Context context, String str, byte[] bArr) {
        if (C11395g.m3715a(context, str)) {
            Intent intent = new Intent("com.xiaomi.mipush.MESSAGE_ARRIVED");
            intent.putExtra("mipush_payload", bArr);
            intent.setPackage(str);
            try {
                if (context.getPackageManager().queryBroadcastReceivers(intent, 0).isEmpty()) {
                    return false;
                }
                AbstractC11049b.m5282a("broadcast message arrived.");
                context.sendBroadcast(intent, C11632w.m2351a(str));
                return true;
            } catch (Exception e) {
                AbstractC11049b.m5282a("meet error when broadcast message arrived. " + e);
                return false;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m2387a(XMPushService xMPushService, String str, C11427hb c11427hb, C11417gs c11417gs) {
        boolean z = true;
        if (c11417gs != null && c11417gs.m3558a() != null && c11417gs.m3558a().containsKey("__check_alive") && c11417gs.m3558a().containsKey("__awake")) {
            C11430he c11430he = new C11430he();
            c11430he.m3335b(c11427hb.m3387a());
            c11430he.m3327d(str);
            c11430he.m3331c(EnumC11414gp.AwakeSystemApp.f22745a);
            c11430he.m3344a(c11417gs.m3559a());
            c11430he.f23010a = new HashMap();
            boolean m3715a = C11395g.m3715a(xMPushService.getApplicationContext(), str);
            c11430he.f23010a.put("app_running", Boolean.toString(m3715a));
            if (!m3715a) {
                boolean parseBoolean = Boolean.parseBoolean(c11417gs.m3558a().get("__awake"));
                c11430he.f23010a.put("awaked", Boolean.toString(parseBoolean));
                if (!parseBoolean) {
                    z = false;
                }
            }
            try {
                C11632w.m2357a(xMPushService, C11632w.m2349a(c11427hb.m3374b(), c11427hb.m3387a(), c11430he, EnumC11404gf.Notification));
            } catch (C11368fi e) {
                AbstractC11049b.m5276a(e);
            }
        }
        return z;
    }

    /* renamed from: a */
    private static void m2391a(final XMPushService xMPushService, final C11427hb c11427hb) {
        xMPushService.m2885a(new XMPushService.AbstractC11509j(4) { // from class: com.xiaomi.push.service.u.1
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "send app absent message.";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                try {
                    C11632w.m2357a(xMPushService, C11632w.m2350a(c11427hb.m3374b(), c11427hb.m3387a()));
                } catch (C11368fi e) {
                    AbstractC11049b.m5276a(e);
                    xMPushService.m2894a(10, e);
                }
            }
        });
    }

    /* renamed from: a */
    private static boolean m2394a(C11427hb c11427hb) {
        return "com.xiaomi.xmsf".equals(c11427hb.f22981b) && c11427hb.m3388a() != null && c11427hb.m3388a().m3558a() != null && c11427hb.m3388a().m3558a().containsKey("miui_package_name");
    }

    /* renamed from: b */
    private static boolean m2380b(C11427hb c11427hb) {
        Map<String, String> m3558a = c11427hb.m3388a().m3558a();
        return m3558a != null && m3558a.containsKey("notify_effect");
    }

    /* renamed from: a */
    private static boolean m2396a(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
        intent.setPackage(str);
        Intent intent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
        intent2.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        try {
            List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 32);
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 32);
            if (queryBroadcastReceivers.isEmpty()) {
                if (queryIntentServices.isEmpty()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
            return false;
        }
    }

    /* renamed from: c */
    private static boolean m2378c(C11427hb c11427hb) {
        if (c11427hb.m3388a() == null || c11427hb.m3388a().m3558a() == null) {
            return false;
        }
        return "1".equals(c11427hb.m3388a().m3558a().get("obslete_ads_message"));
    }

    /* renamed from: b */
    private static void m2379b(final XMPushService xMPushService, final C11427hb c11427hb) {
        xMPushService.m2885a(new XMPushService.AbstractC11509j(4) { // from class: com.xiaomi.push.service.u.2
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "send ack message for message.";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                try {
                    C11632w.m2357a(xMPushService, C11621u.m2399a(xMPushService, c11427hb, C11469j.m2972a((Context) xMPushService) ? C11629v.m2373a((Context) xMPushService, c11427hb) : null));
                } catch (C11368fi e) {
                    AbstractC11049b.m5268d("error sending ack message :" + e);
                    xMPushService.m2894a(10, e);
                }
            }
        });
    }

    /* renamed from: c */
    private static void m2377c(final XMPushService xMPushService, final C11427hb c11427hb) {
        xMPushService.m2885a(new XMPushService.AbstractC11509j(4) { // from class: com.xiaomi.push.service.u.3
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "send ack message for obsleted message.";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                try {
                    C11427hb m2400a = C11621u.m2400a((Context) xMPushService, c11427hb);
                    m2400a.m3388a().m3551a("message_obsleted", "1");
                    C11632w.m2357a(xMPushService, m2400a);
                } catch (C11368fi e) {
                    AbstractC11049b.m5276a(e);
                    xMPushService.m2894a(10, e);
                }
            }
        });
    }

    /* renamed from: d */
    private static void m2376d(final XMPushService xMPushService, final C11427hb c11427hb) {
        xMPushService.m2885a(new XMPushService.AbstractC11509j(4) { // from class: com.xiaomi.push.service.u.4
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "send ack message for unrecognized new miui message.";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                try {
                    C11427hb m2400a = C11621u.m2400a((Context) xMPushService, c11427hb);
                    m2400a.m3388a().m3551a("miui_message_unrecognized", "1");
                    C11632w.m2357a(xMPushService, m2400a);
                } catch (C11368fi e) {
                    AbstractC11049b.m5276a(e);
                    xMPushService.m2894a(10, e);
                }
            }
        });
    }

    /* renamed from: a */
    private static void m2389a(final XMPushService xMPushService, final C11427hb c11427hb, final String str) {
        xMPushService.m2885a(new XMPushService.AbstractC11509j(4) { // from class: com.xiaomi.push.service.u.5
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "send app absent ack message for message.";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                try {
                    C11427hb m2400a = C11621u.m2400a((Context) xMPushService, c11427hb);
                    m2400a.m3388a().m3551a("absent_target_package", str);
                    C11632w.m2357a(xMPushService, m2400a);
                } catch (C11368fi e) {
                    AbstractC11049b.m5276a(e);
                    xMPushService.m2894a(10, e);
                }
            }
        });
    }

    /* renamed from: a */
    private static void m2388a(final XMPushService xMPushService, final C11427hb c11427hb, final String str, final String str2) {
        xMPushService.m2885a(new XMPushService.AbstractC11509j(4) { // from class: com.xiaomi.push.service.u.6
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "send wrong message ack for message.";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                try {
                    C11427hb m2400a = C11621u.m2400a((Context) xMPushService, c11427hb);
                    m2400a.f22975a.m3551a("error", str);
                    m2400a.f22975a.m3551a("reason", str2);
                    C11632w.m2357a(xMPushService, m2400a);
                } catch (C11368fi e) {
                    AbstractC11049b.m5276a(e);
                    xMPushService.m2894a(10, e);
                }
            }
        });
    }

    /* renamed from: a */
    private static void m2390a(final XMPushService xMPushService, final C11427hb c11427hb, final C11430he c11430he) {
        xMPushService.m2885a(new XMPushService.AbstractC11509j(4) { // from class: com.xiaomi.push.service.u.7
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "send ack message for clear push message.";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                try {
                    C11421gw c11421gw = new C11421gw();
                    c11421gw.m3458c(EnumC11414gp.CancelPushMessageACK.f22745a);
                    c11421gw.m3464a(c11430he.m3351a());
                    c11421gw.m3467a(c11430he.m3352a());
                    c11421gw.m3460b(c11430he.m3337b());
                    c11421gw.m3454e(c11430he.m3329d());
                    c11421gw.m3468a(0L);
                    c11421gw.m3456d("success clear push message.");
                    C11632w.m2357a(xMPushService, C11632w.m2346b(c11427hb.m3374b(), c11427hb.m3387a(), c11421gw, EnumC11404gf.Notification));
                } catch (C11368fi e) {
                    AbstractC11049b.m5268d("clear push message. " + e);
                    xMPushService.m2894a(10, e);
                }
            }
        });
    }

    /* renamed from: a */
    public static C11427hb m2400a(Context context, C11427hb c11427hb) {
        return m2399a(context, c11427hb, (Map<String, String>) null);
    }

    /* renamed from: a */
    public static C11427hb m2399a(Context context, C11427hb c11427hb, Map<String, String> map) {
        C11420gv c11420gv = new C11420gv();
        c11420gv.m3497b(c11427hb.m3387a());
        C11417gs m3388a = c11427hb.m3388a();
        if (m3388a != null) {
            c11420gv.m3501a(m3388a.m3559a());
            c11420gv.m3504a(m3388a.m3561a());
            if (!TextUtils.isEmpty(m3388a.m3547b())) {
                c11420gv.m3494c(m3388a.m3547b());
            }
        }
        c11420gv.m3500a(C11441hp.m3089a(context, c11427hb));
        C11427hb m2349a = C11632w.m2349a(c11427hb.m3374b(), c11427hb.m3387a(), c11420gv, EnumC11404gf.AckMessage);
        C11417gs m3388a2 = c11427hb.m3388a();
        if (m3388a2 != null) {
            m3388a2 = C11568au.m2630a(m3388a2.m3560a());
            Map<String, String> m3558a = m3388a2.m3558a();
            String str = m3558a != null ? m3558a.get("channel_id") : null;
            m3388a2.m3551a("mat", Long.toString(System.currentTimeMillis()));
            m3388a2.m3551a("cs", String.valueOf(C11591f.m2553a(context, c11427hb.f22981b, str)));
        }
        if (map != null) {
            try {
                if (map.size() > 0) {
                    for (String str2 : map.keySet()) {
                        m3388a2.m3551a(str2, map.get(str2));
                    }
                }
            } catch (Throwable th) {
                AbstractC11049b.m5268d("error adding params to ack message :" + th);
            }
        }
        m2349a.m3382a(m3388a2);
        return m2349a;
    }

    /* renamed from: a */
    private static boolean m2401a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (queryBroadcastReceivers != null) {
                if (!queryBroadcastReceivers.isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }
}
