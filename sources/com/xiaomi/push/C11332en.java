package com.xiaomi.push;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.en */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11332en implements InterfaceC11360fd {

    /* renamed from: a */
    private int f22161a;

    /* renamed from: a */
    AbstractC11356fa f22163a;

    /* renamed from: a */
    XMPushService f22164a;

    /* renamed from: a */
    private Exception f22165a;

    /* renamed from: e */
    private long f22170e;

    /* renamed from: f */
    private long f22171f;

    /* renamed from: a */
    private long f22162a = 0;

    /* renamed from: b */
    private long f22167b = 0;

    /* renamed from: c */
    private long f22168c = 0;

    /* renamed from: d */
    private long f22169d = 0;

    /* renamed from: a */
    private String f22166a = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    public C11332en(XMPushService xMPushService) {
        this.f22170e = 0L;
        this.f22171f = 0L;
        this.f22164a = xMPushService;
        m3995b();
        int myUid = Process.myUid();
        try {
            this.f22171f = TrafficStats.getUidRxBytes(myUid);
            this.f22170e = TrafficStats.getUidTxBytes(myUid);
        } catch (Exception e) {
            AbstractC11049b.m5282a("Failed to obtain traffic data during initialization: " + e);
            this.f22171f = -1L;
            this.f22170e = -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Exception m3997a() {
        return this.f22165a;
    }

    /* renamed from: a */
    public synchronized void m3996a() {
        if (this.f22164a == null) {
            return;
        }
        String m4850a = C11169au.m4850a((Context) this.f22164a);
        boolean m4835b = C11169au.m4835b(this.f22164a);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f22162a > 0) {
            this.f22167b += elapsedRealtime - this.f22162a;
            this.f22162a = 0L;
        }
        if (this.f22168c != 0) {
            this.f22169d += elapsedRealtime - this.f22168c;
            this.f22168c = 0L;
        }
        if (m4835b) {
            if ((!TextUtils.equals(this.f22166a, m4850a) && this.f22167b > 30000) || this.f22167b > 5400000) {
                m3994c();
            }
            this.f22166a = m4850a;
            if (this.f22162a == 0) {
                this.f22162a = elapsedRealtime;
            }
            if (this.f22164a.m2851c()) {
                this.f22168c = elapsedRealtime;
            }
        }
    }

    /* renamed from: b */
    private void m3995b() {
        this.f22167b = 0L;
        this.f22169d = 0L;
        this.f22162a = 0L;
        this.f22168c = 0L;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (C11169au.m4849a((Context) this.f22164a)) {
            this.f22162a = elapsedRealtime;
        }
        if (this.f22164a.m2851c()) {
            this.f22168c = elapsedRealtime;
        }
    }

    /* renamed from: c */
    private synchronized void m3994c() {
        AbstractC11049b.m5270c("stat connpt = " + this.f22166a + " netDuration = " + this.f22167b + " ChannelDuration = " + this.f22169d + " channelConnectedTime = " + this.f22168c);
        C11325ej c11325ej = new C11325ej();
        c11325ej.f22133a = (byte) 0;
        c11325ej.m4038a(EnumC11324ei.CHANNEL_ONLINE_RATE.m4043a());
        c11325ej.m4035a(this.f22166a);
        c11325ej.m4024d((int) (System.currentTimeMillis() / 1000));
        c11325ej.m4032b((int) (this.f22167b / 1000));
        c11325ej.m4028c((int) (this.f22169d / 1000));
        C11333eo.m3990a().m3984a(c11325ej);
        m3995b();
    }

    @Override // com.xiaomi.push.InterfaceC11360fd
    /* renamed from: a */
    public void mo2889a(AbstractC11356fa abstractC11356fa) {
        this.f22161a = 0;
        this.f22165a = null;
        this.f22163a = abstractC11356fa;
        this.f22166a = C11169au.m4850a((Context) this.f22164a);
        C11336ep.m3978a(0, EnumC11324ei.CONN_SUCCESS.m4043a());
    }

    @Override // com.xiaomi.push.InterfaceC11360fd
    /* renamed from: a */
    public void mo2888a(AbstractC11356fa abstractC11356fa, int i, Exception exc) {
        long j;
        if (this.f22161a == 0 && this.f22165a == null) {
            this.f22161a = i;
            this.f22165a = exc;
            C11336ep.m3971b(abstractC11356fa.mo3853a(), exc);
        }
        if (i == 22 && this.f22168c != 0) {
            long m3899a = abstractC11356fa.m3899a() - this.f22168c;
            if (m3899a < 0) {
                m3899a = 0;
            }
            this.f22169d += m3899a + (C11363fg.m3855b() / 2);
            this.f22168c = 0L;
        }
        m3996a();
        int myUid = Process.myUid();
        long j2 = -1;
        try {
            j2 = TrafficStats.getUidRxBytes(myUid);
            j = TrafficStats.getUidTxBytes(myUid);
        } catch (Exception e) {
            AbstractC11049b.m5282a("Failed to obtain traffic data: " + e);
            j = -1L;
        }
        AbstractC11049b.m5270c("Stats rx=" + (j2 - this.f22171f) + ", tx=" + (j - this.f22170e));
        this.f22171f = j2;
        this.f22170e = j;
    }

    @Override // com.xiaomi.push.InterfaceC11360fd
    /* renamed from: b */
    public void mo2857b(AbstractC11356fa abstractC11356fa) {
        m3996a();
        this.f22168c = SystemClock.elapsedRealtime();
        C11336ep.m3976a(0, EnumC11324ei.CONN_SUCCESS.m4043a(), abstractC11356fa.mo3853a(), abstractC11356fa.m3900a());
    }

    @Override // com.xiaomi.push.InterfaceC11360fd
    /* renamed from: a */
    public void mo2887a(AbstractC11356fa abstractC11356fa, Exception exc) {
        C11336ep.m3977a(0, EnumC11324ei.CHANNEL_CON_FAIL.m4043a(), 1, abstractC11356fa.mo3853a(), C11169au.m4835b(this.f22164a) ? 1 : 0);
        m3996a();
    }
}
