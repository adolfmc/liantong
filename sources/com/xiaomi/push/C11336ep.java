package com.xiaomi.push;

import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11330em;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.XMPushService;
import java.util.Hashtable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ep */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11336ep {

    /* renamed from: a */
    private static final int f22180a = EnumC11324ei.PING_RTT.m4043a();

    /* renamed from: a */
    private static long f22181a = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.ep$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11337a {

        /* renamed from: a */
        static Hashtable<Integer, Long> f22182a = new Hashtable<>();
    }

    /* renamed from: a */
    public static void m3973a(String str, Exception exc) {
        try {
            C11330em.C11331a m4000b = C11330em.m4000b(exc);
            C11325ej m3993a = C11333eo.m3990a().m3993a();
            m3993a.m4038a(m4000b.f22159a.m4043a());
            m3993a.m4027c(m4000b.f22160a);
            m3993a.m4031b(str);
            if (C11333eo.m3991a() != null && C11333eo.m3991a().f22164a != null) {
                m3993a.m4028c(C11169au.m4835b(C11333eo.m3991a().f22164a) ? 1 : 0);
            }
            C11333eo.m3990a().m3984a(m3993a);
        } catch (NullPointerException unused) {
        }
    }

    /* renamed from: b */
    public static void m3971b(String str, Exception exc) {
        try {
            C11330em.C11331a m3998d = C11330em.m3998d(exc);
            C11325ej m3993a = C11333eo.m3990a().m3993a();
            m3993a.m4038a(m3998d.f22159a.m4043a());
            m3993a.m4027c(m3998d.f22160a);
            m3993a.m4031b(str);
            if (C11333eo.m3991a() != null && C11333eo.m3991a().f22164a != null) {
                m3993a.m4028c(C11169au.m4835b(C11333eo.m3991a().f22164a) ? 1 : 0);
            }
            C11333eo.m3990a().m3984a(m3993a);
        } catch (NullPointerException unused) {
        }
    }

    /* renamed from: a */
    public static void m3974a(String str, int i, Exception exc) {
        C11325ej m3993a = C11333eo.m3990a().m3993a();
        if (C11333eo.m3991a() != null && C11333eo.m3991a().f22164a != null) {
            m3993a.m4028c(C11169au.m4835b(C11333eo.m3991a().f22164a) ? 1 : 0);
        }
        if (i > 0) {
            m3993a.m4038a(EnumC11324ei.GSLB_REQUEST_SUCCESS.m4043a());
            m3993a.m4031b(str);
            m3993a.m4032b(i);
            C11333eo.m3990a().m3984a(m3993a);
            return;
        }
        try {
            C11330em.C11331a m4002a = C11330em.m4002a(exc);
            m3993a.m4038a(m4002a.f22159a.m4043a());
            m3993a.m4027c(m4002a.f22160a);
            m3993a.m4031b(str);
            C11333eo.m3990a().m3984a(m3993a);
        } catch (NullPointerException unused) {
        }
    }

    /* renamed from: a */
    public static void m3975a(XMPushService xMPushService, C11545am.C11547b c11547b) {
        new C11327el(xMPushService, c11547b).m4006a();
    }

    /* renamed from: a */
    public static synchronized void m3978a(int i, int i2) {
        synchronized (C11336ep.class) {
            if (i2 < 16777215) {
                C11337a.f22182a.put(Integer.valueOf((i << 24) | i2), Long.valueOf(System.currentTimeMillis()));
            } else {
                AbstractC11049b.m5268d("stats key should less than 16777215");
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m3976a(int i, int i2, String str, int i3) {
        synchronized (C11336ep.class) {
            long currentTimeMillis = System.currentTimeMillis();
            int i4 = (i << 24) | i2;
            if (C11337a.f22182a.containsKey(Integer.valueOf(i4))) {
                C11325ej m3993a = C11333eo.m3990a().m3993a();
                m3993a.m4038a(i2);
                m3993a.m4032b((int) (currentTimeMillis - C11337a.f22182a.get(Integer.valueOf(i4)).longValue()));
                m3993a.m4031b(str);
                if (i3 > -1) {
                    m3993a.m4028c(i3);
                }
                C11333eo.m3990a().m3984a(m3993a);
                C11337a.f22182a.remove(Integer.valueOf(i2));
            } else {
                AbstractC11049b.m5268d("stats key not found");
            }
        }
    }

    /* renamed from: a */
    public static void m3981a() {
        if (f22181a == 0 || SystemClock.elapsedRealtime() - f22181a > 7200000) {
            f22181a = SystemClock.elapsedRealtime();
            m3978a(0, f22180a);
        }
    }

    /* renamed from: b */
    public static void m3972b() {
        m3976a(0, f22180a, null, -1);
    }

    /* renamed from: a */
    public static void m3977a(int i, int i2, int i3, String str, int i4) {
        C11325ej m3993a = C11333eo.m3990a().m3993a();
        m3993a.m4039a((byte) i);
        m3993a.m4038a(i2);
        m3993a.m4032b(i3);
        m3993a.m4031b(str);
        m3993a.m4028c(i4);
        C11333eo.m3990a().m3984a(m3993a);
    }

    /* renamed from: a */
    public static void m3979a(int i) {
        C11325ej m3993a = C11333eo.m3990a().m3993a();
        m3993a.m4038a(EnumC11324ei.CHANNEL_STATS_COUNTER.m4043a());
        m3993a.m4028c(i);
        C11333eo.m3990a().m3984a(m3993a);
    }

    /* renamed from: a */
    public static byte[] m3980a() {
        C11326ek m3992a = C11333eo.m3990a().m3992a();
        if (m3992a != null) {
            return C11441hp.m3085a(m3992a);
        }
        return null;
    }
}
