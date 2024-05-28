package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11175av;
import com.xiaomi.push.C11363fg;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11402gd;
import com.xiaomi.push.C11408gj;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11470k;
import com.xiaomi.push.EnumC11409gk;
import com.xiaomi.push.EnumC11473n;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@NBSInstrumented
/* renamed from: com.xiaomi.push.service.m */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11603m {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: a */
    private static volatile C11603m f23686a;

    /* renamed from: a */
    private long f23688a;

    /* renamed from: a */
    private final Context f23689a;

    /* renamed from: a */
    private final SharedPreferences f23690a;

    /* renamed from: b */
    private final boolean f23697b;

    /* renamed from: c */
    private final boolean f23699c;

    /* renamed from: a */
    private final AtomicInteger f23692a = new AtomicInteger(0);

    /* renamed from: a */
    private String f23691a = null;

    /* renamed from: a */
    private volatile boolean f23693a = false;

    /* renamed from: b */
    private String f23695b = null;

    /* renamed from: b */
    private final AtomicInteger f23696b = new AtomicInteger(0);

    /* renamed from: c */
    private final AtomicInteger f23698c = new AtomicInteger(0);

    /* renamed from: a */
    private int f23687a = -1;

    /* renamed from: b */
    private long f23694b = -1;

    /* renamed from: d */
    private final boolean f23700d = m2488g();

    /* renamed from: a */
    public void m2514a() {
    }

    private C11603m(Context context) {
        this.f23689a = context;
        this.f23699c = C11469j.m2972a(context);
        this.f23697b = C11537ah.m2715a(this.f23689a).m2716a(EnumC11409gk.IntelligentHeartbeatSwitchBoolean.m3637a(), true);
        this.f23690a = this.f23689a.getSharedPreferences("hb_record", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f23690a.getLong(C11604a.m2483c(), -1L) == -1) {
            this.f23690a.edit().putLong(C11604a.m2483c(), currentTimeMillis).apply();
        }
        this.f23688a = this.f23690a.getLong(C11604a.m2477i(), -1L);
        if (this.f23688a == -1) {
            this.f23688a = currentTimeMillis;
            this.f23690a.edit().putLong(C11604a.m2477i(), currentTimeMillis).apply();
        }
    }

    /* renamed from: a */
    public static C11603m m2511a(Context context) {
        if (f23686a == null) {
            synchronized (C11603m.class) {
                if (f23686a == null) {
                    f23686a = new C11603m(context);
                }
            }
        }
        return f23686a;
    }

    /* renamed from: a */
    public synchronized void m2510a(C11175av c11175av) {
        if (m2495d()) {
            String str = null;
            if (c11175av != null) {
                if (c11175av.m4826a() == 0) {
                    String m4819b = c11175av.m4819b();
                    if (!TextUtils.isEmpty(m4819b) && !"UNKNOWN".equalsIgnoreCase(m4819b)) {
                        str = "M-" + m4819b;
                    }
                    m2502b(str);
                    this.f23687a = 0;
                } else {
                    if (c11175av.m4826a() != 1 && c11175av.m4826a() != 6) {
                        m2502b(null);
                        this.f23687a = -1;
                    }
                    m2502b("WIFI-ID-UNKNOWN");
                    this.f23687a = 1;
                }
            } else {
                m2502b(null);
                this.f23687a = -1;
            }
        }
    }

    /* renamed from: a */
    public synchronized void m2509a(String str) {
        if (!TextUtils.isEmpty(str)) {
            m2493e();
        }
        if (m2495d() && !TextUtils.isEmpty(str)) {
            m2502b("W-" + str);
        }
    }

    /* renamed from: b */
    private void m2502b(String str) {
        if ("WIFI-ID-UNKNOWN".equals(str)) {
            String str2 = this.f23691a;
            if (str2 == null || !str2.startsWith("W-")) {
                if (this.f23700d) {
                    this.f23691a = "W-NETWORK_ID_WIFI_DEFAULT";
                } else {
                    this.f23691a = null;
                }
            }
        } else {
            this.f23691a = str;
        }
        int i = this.f23690a.getInt(C11604a.m2486a(this.f23691a), -1);
        long j = this.f23690a.getLong(C11604a.m2484b(this.f23691a), -1L);
        long currentTimeMillis = System.currentTimeMillis();
        if (i != -1) {
            if (j == -1) {
                this.f23690a.edit().putLong(C11604a.m2484b(this.f23691a), currentTimeMillis + m2497d()).apply();
            } else if (currentTimeMillis > j) {
                this.f23690a.edit().remove(C11604a.m2486a(this.f23691a)).remove(C11604a.m2484b(this.f23691a)).apply();
            }
        }
        this.f23692a.getAndSet(0);
        if (TextUtils.isEmpty(this.f23691a) || m2516a() != -1) {
            this.f23693a = false;
        } else {
            this.f23693a = true;
        }
        AbstractC11049b.m5282a(String.format("[HB] network changed, netid:%s, %s", this.f23691a, Boolean.valueOf(this.f23693a)));
    }

    /* renamed from: b */
    public void m2504b() {
        if (m2495d()) {
            m2491f();
            if (this.f23693a && !TextUtils.isEmpty(this.f23691a) && this.f23691a.equals(this.f23695b)) {
                this.f23692a.getAndIncrement();
                AbstractC11049b.m5282a("[HB] ping timeout count:" + this.f23692a);
                if (m2513a()) {
                    AbstractC11049b.m5282a("[HB] change hb interval for net:" + this.f23691a);
                    m2498c(this.f23691a);
                    this.f23693a = false;
                    this.f23692a.getAndSet(0);
                    m2494d(this.f23691a);
                }
            }
        }
    }

    /* renamed from: c */
    public void m2500c() {
        if (m2495d()) {
            this.f23695b = this.f23691a;
        }
    }

    /* renamed from: d */
    public void m2496d() {
        if (m2495d()) {
            m2489g();
            if (this.f23693a) {
                this.f23692a.getAndSet(0);
            }
        }
    }

    /* renamed from: a */
    public void m2512a(int i) {
        this.f23690a.edit().putLong(C11604a.m2476j(), System.currentTimeMillis() + (i * 1000)).apply();
    }

    /* renamed from: c */
    private long m2501c() {
        return this.f23690a.getLong(C11604a.m2476j(), -1L);
    }

    /* renamed from: a */
    private boolean m2513a() {
        return this.f23692a.get() >= Math.max(C11537ah.m2715a(this.f23689a).m2719a(EnumC11409gk.IntelligentHeartbeatNATCountInt.m3637a(), 3), 3);
    }

    /* renamed from: a */
    public long m2515a() {
        int m2516a;
        long m3855b = C11363fg.m3855b();
        if (this.f23699c && !m2503b() && ((C11537ah.m2715a(this.f23689a).m2716a(EnumC11409gk.IntelligentHeartbeatSwitchBoolean.m3637a(), true) || m2501c() >= System.currentTimeMillis()) && (m2516a = m2516a()) != -1)) {
            m3855b = m2516a;
        }
        if (!TextUtils.isEmpty(this.f23691a) && !"WIFI-ID-UNKNOWN".equals(this.f23691a) && this.f23687a == 1) {
            m2506a(m3855b < 300000);
        }
        this.f23694b = m3855b;
        AbstractC11049b.m5282a("[HB] ping interval:" + m3855b);
        return m3855b;
    }

    /* renamed from: b */
    public long m2505b() {
        return this.f23694b;
    }

    /* renamed from: b */
    private boolean m2503b() {
        if (!TextUtils.isEmpty(this.f23691a)) {
            if (this.f23691a.startsWith("M-")) {
                if (!C11537ah.m2715a(this.f23689a).m2716a(EnumC11409gk.IntelligentHeartbeatUseInMobileNetworkBoolean.m3637a(), false)) {
                    return true;
                }
            } else if (this.f23691a.equals("W-NETWORK_ID_WIFI_DEFAULT") && !m2488g()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private int m2516a() {
        if (TextUtils.isEmpty(this.f23691a)) {
            return -1;
        }
        try {
            return this.f23690a.getInt(C11604a.m2486a(this.f23691a), -1);
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: c */
    private void m2498c(String str) {
        if (m2508a(str)) {
            this.f23690a.edit().putInt(C11604a.m2486a(str), 235000).apply();
            this.f23690a.edit().putLong(C11604a.m2484b(this.f23691a), System.currentTimeMillis() + m2497d()).apply();
        }
    }

    /* renamed from: d */
    private long m2497d() {
        return C11537ah.m2715a(this.f23689a).m2718a(EnumC11409gk.ShortHeartbeatEffectivePeriodMsLong.m3637a(), 7776000000L);
    }

    /* renamed from: a */
    private boolean m2508a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("W-") || str.startsWith("M-");
    }

    /* renamed from: d */
    private void m2494d(String str) {
        String str2;
        String str3;
        if (m2499c() && !TextUtils.isEmpty(str)) {
            if (str.startsWith("W-")) {
                str2 = "W";
            } else if (!str.startsWith("M-")) {
                return;
            } else {
                str2 = "M";
            }
            String valueOf = String.valueOf(235000);
            String valueOf2 = String.valueOf(System.currentTimeMillis() / 1000);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(":::");
            sb.append(str2);
            sb.append(":::");
            sb.append(valueOf);
            sb.append(":::");
            sb.append(valueOf2);
            String string = this.f23690a.getString(C11604a.m2480f(), null);
            if (TextUtils.isEmpty(string)) {
                str3 = sb.toString();
            } else {
                str3 = string + "###" + sb.toString();
            }
            this.f23690a.edit().putString(C11604a.m2480f(), str3).apply();
        }
    }

    /* renamed from: a */
    private void m2506a(boolean z) {
        String m2481e;
        if (m2499c()) {
            int incrementAndGet = (z ? this.f23696b : this.f23698c).incrementAndGet();
            Object[] objArr = new Object[2];
            objArr[0] = z ? "short" : "long";
            objArr[1] = Integer.valueOf(incrementAndGet);
            AbstractC11049b.m5274b(String.format("[HB] %s ping interval count: %s", objArr));
            if (incrementAndGet >= 5) {
                if (z) {
                    m2481e = C11604a.m2482d();
                } else {
                    m2481e = C11604a.m2481e();
                }
                int i = this.f23690a.getInt(m2481e, 0) + incrementAndGet;
                this.f23690a.edit().putInt(m2481e, i).apply();
                Object[] objArr2 = new Object[2];
                objArr2[0] = z ? "short" : "long";
                objArr2[1] = Integer.valueOf(i);
                AbstractC11049b.m5282a(String.format("[HB] accumulate %s hb count(%s) and write to file. ", objArr2));
                if (z) {
                    this.f23696b.set(0);
                } else {
                    this.f23698c.set(0);
                }
            }
        }
    }

    /* renamed from: e */
    private void m2493e() {
        if (this.f23690a.getBoolean(C11604a.m2487a(), false)) {
            return;
        }
        this.f23690a.edit().putBoolean(C11604a.m2487a(), true).apply();
    }

    /* renamed from: f */
    private void m2491f() {
        String m2479g;
        switch (this.f23687a) {
            case 0:
                m2479g = C11604a.m2479g();
                break;
            case 1:
                m2479g = C11604a.m2478h();
                break;
            default:
                m2479g = null;
                break;
        }
        if (TextUtils.isEmpty(m2479g)) {
            return;
        }
        if (this.f23690a.getLong(C11604a.m2477i(), -1L) == -1) {
            this.f23688a = System.currentTimeMillis();
            this.f23690a.edit().putLong(C11604a.m2477i(), this.f23688a).apply();
        }
        this.f23690a.edit().putInt(m2479g, this.f23690a.getInt(m2479g, 0) + 1).apply();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: g */
    private void m2489g() {
        /*
            Method dump skipped, instructions count: 706
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11603m.m2489g():void");
    }

    /* renamed from: a */
    private void m2507a(String str, String str2, Map<String, String> map) {
        C11408gj c11408gj = new C11408gj();
        c11408gj.m3649d(str);
        c11408gj.m3653c("hb_name");
        c11408gj.m3665a("hb_channel");
        c11408gj.m3668a(1L);
        c11408gj.m3657b(str2);
        c11408gj.m3662a(false);
        c11408gj.m3658b(System.currentTimeMillis());
        c11408gj.m3642g(this.f23689a.getPackageName());
        c11408gj.m3646e("com.xiaomi.xmsf");
        if (map == null) {
            map = new HashMap<>();
        }
        String str3 = null;
        C11614p m2430a = C11615q.m2430a(this.f23689a);
        if (m2430a != null && !TextUtils.isEmpty(m2430a.f23730a)) {
            String[] split = m2430a.f23730a.split("@");
            if (split.length > 0) {
                str3 = split[0];
            }
        }
        map.put("uuid", str3);
        map.put("model", C11470k.m2955a());
        Context context = this.f23689a;
        map.put("avc", String.valueOf(C11395g.m3720a(context, context.getPackageName())));
        map.put("pvc", String.valueOf(50909));
        map.put("cvc", String.valueOf(48));
        c11408gj.m3663a(map);
        C11402gd m3687a = C11402gd.m3687a(this.f23689a);
        if (m3687a != null) {
            m3687a.m3685a(c11408gj, this.f23689a.getPackageName());
        }
    }

    /* renamed from: c */
    private boolean m2499c() {
        return m2495d() && C11537ah.m2715a(this.f23689a).m2716a(EnumC11409gk.IntelligentHeartbeatDataCollectSwitchBoolean.m3637a(), true) && EnumC11473n.China.name().equals(C11578b.m2591a(this.f23689a).m2592a());
    }

    /* renamed from: d */
    private boolean m2495d() {
        return this.f23699c && (this.f23697b || this.f23700d || ((m2501c() > System.currentTimeMillis() ? 1 : (m2501c() == System.currentTimeMillis() ? 0 : -1)) >= 0));
    }

    /* renamed from: e */
    private boolean m2492e() {
        long j = this.f23690a.getLong(C11604a.m2483c(), -1L);
        if (j == -1) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        return j > currentTimeMillis || currentTimeMillis - j >= 259200000;
    }

    /* renamed from: f */
    private boolean m2490f() {
        if (this.f23688a == -1) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.f23688a;
        return j > currentTimeMillis || currentTimeMillis - j >= 259200000;
    }

    /* renamed from: g */
    private boolean m2488g() {
        return C11537ah.m2715a(this.f23689a).m2716a(EnumC11409gk.IntelligentHeartbeatForUnsupportWifiDigestBoolean.m3637a(), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.m$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11604a {
        /* renamed from: a */
        public static String m2487a() {
            return "support_wifi_digest";
        }

        /* renamed from: b */
        public static String m2485b() {
            return "record_support_wifi_digest_reported_time";
        }

        /* renamed from: c */
        public static String m2483c() {
            return "record_hb_count_start";
        }

        /* renamed from: d */
        public static String m2482d() {
            return "record_short_hb_count";
        }

        /* renamed from: e */
        public static String m2481e() {
            return "record_long_hb_count";
        }

        /* renamed from: f */
        public static String m2480f() {
            return "record_hb_change";
        }

        /* renamed from: g */
        public static String m2479g() {
            return "record_mobile_ptc";
        }

        /* renamed from: h */
        public static String m2478h() {
            return "record_wifi_ptc";
        }

        /* renamed from: i */
        public static String m2477i() {
            return "record_ptc_start";
        }

        /* renamed from: j */
        public static String m2476j() {
            return "keep_short_hb_effective_time";
        }

        /* renamed from: a */
        public static String m2486a(String str) {
            return String.format("HB_%s", str);
        }

        /* renamed from: b */
        public static String m2484b(String str) {
            return String.format("HB_dead_time_%s", str);
        }
    }
}
