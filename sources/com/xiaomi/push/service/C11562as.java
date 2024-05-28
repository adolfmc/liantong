package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11333eo;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.as */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11562as {

    /* renamed from: d */
    private static int f23613d = 300000;

    /* renamed from: a */
    private XMPushService f23616a;

    /* renamed from: b */
    private int f23617b = 0;

    /* renamed from: c */
    private int f23618c = 0;

    /* renamed from: a */
    private int f23614a = 500;

    /* renamed from: a */
    private long f23615a = 0;

    public C11562as(XMPushService xMPushService) {
        this.f23616a = xMPushService;
    }

    /* renamed from: a */
    public void m2641a() {
        this.f23615a = System.currentTimeMillis();
        this.f23616a.m2896a(1);
        this.f23617b = 0;
    }

    /* renamed from: a */
    public void m2640a(boolean z) {
        if (!this.f23616a.m2898a()) {
            AbstractC11049b.m5270c("should not reconnect as no client or network.");
        } else if (z) {
            if (!this.f23616a.m2895a(1)) {
                this.f23617b++;
            }
            this.f23616a.m2896a(1);
            AbstractC11049b.m5280a("ReconnectionManager", "-->tryReconnect(): exec ConnectJob");
            XMPushService xMPushService = this.f23616a;
            xMPushService.getClass();
            xMPushService.m2885a(new XMPushService.C11504e());
        } else if (this.f23616a.m2895a(1)) {
        } else {
            int m2642a = m2642a();
            this.f23617b++;
            AbstractC11049b.m5282a("schedule reconnect in " + m2642a + "ms");
            XMPushService xMPushService2 = this.f23616a;
            xMPushService2.getClass();
            xMPushService2.m2884a(new XMPushService.C11504e(), (long) m2642a);
            if (this.f23617b == 2 && C11333eo.m3990a().m3988a()) {
                C11641z.m2282b();
            }
            if (this.f23617b == 3) {
                C11641z.m2286a();
            }
        }
    }

    /* renamed from: a */
    private int m2642a() {
        if (this.f23617b > 8) {
            return 300000;
        }
        double random = (Math.random() * 2.0d) + 1.0d;
        int i = this.f23617b;
        if (i > 4) {
            return (int) (random * 60000.0d);
        }
        if (i > 1) {
            return (int) (random * 10000.0d);
        }
        if (this.f23615a == 0) {
            return 0;
        }
        if (System.currentTimeMillis() - this.f23615a < 310000) {
            int i2 = this.f23614a;
            int i3 = f23613d;
            if (i2 >= i3) {
                return i2;
            }
            this.f23618c++;
            if (this.f23618c >= 4) {
                return i3;
            }
            this.f23614a = (int) (i2 * 1.5d);
            return i2;
        }
        this.f23614a = 1000;
        this.f23618c = 0;
        return 0;
    }
}
