package com.xiaomi.push;

import android.content.Context;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.C11603m;
import com.xiaomi.push.service.XMPushService;
import java.io.IOException;
import java.net.Socket;

/* renamed from: com.xiaomi.push.fh */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC11364fh extends AbstractC11356fa {

    /* renamed from: a */
    protected Exception f22272a;

    /* renamed from: a */
    protected Socket f22273a;

    /* renamed from: b */
    protected XMPushService f22274b;

    /* renamed from: c */
    private int f22275c;

    /* renamed from: c */
    String f22276c;

    /* renamed from: d */
    private String f22277d;

    /* renamed from: e */
    protected volatile long f22278e;

    /* renamed from: f */
    protected volatile long f22279f;

    /* renamed from: g */
    protected volatile long f22280g;

    /* renamed from: h */
    private long f22281h;

    /* renamed from: a */
    protected abstract void mo3844a(boolean z);

    public AbstractC11364fh(XMPushService xMPushService, C11358fb c11358fb) {
        super(xMPushService, c11358fb);
        this.f22272a = null;
        this.f22276c = null;
        this.f22278e = 0L;
        this.f22279f = 0L;
        this.f22280g = 0L;
        this.f22281h = 0L;
        this.f22274b = xMPushService;
    }

    /* renamed from: a */
    public Context m3854a() {
        return this.f22274b;
    }

    @Override // com.xiaomi.push.AbstractC11356fa
    /* renamed from: b */
    public void mo3841b(boolean z) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final long currentTimeMillis = System.currentTimeMillis();
        mo3844a(z);
        C11603m.m2511a(this.f22274b).m2500c();
        if (z) {
            return;
        }
        this.f22274b.m2884a(new XMPushService.AbstractC11509j(13) { // from class: com.xiaomi.push.fh.1
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                Thread.yield();
                if (!AbstractC11364fh.this.m3840c() || AbstractC11364fh.this.m3892a(elapsedRealtime)) {
                    return;
                }
                C11603m.m2511a(AbstractC11364fh.this.f22274b).m2504b();
                AbstractC11364fh.this.f22274b.m2894a(22, (Exception) null);
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "check the ping-pong." + currentTimeMillis;
            }
        }, 10000L);
    }

    /* renamed from: c */
    public String m3840c() {
        return this.f22244a;
    }

    @Override // com.xiaomi.push.AbstractC11356fa
    /* renamed from: a */
    public String mo3853a() {
        return this.f22277d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void mo3850a(int i, Exception exc) {
        if (m3884b() == 2) {
            return;
        }
        m3893a(2, i, exc);
        this.f22244a = "";
        try {
            this.f22273a.close();
        } catch (Throwable unused) {
        }
        this.f22278e = 0L;
        this.f22279f = 0L;
    }

    @Override // com.xiaomi.push.AbstractC11356fa
    /* renamed from: b */
    public void mo3842b(int i, Exception exc) {
        mo3850a(i, exc);
        if ((exc != null || i == 18) && this.f22280g != 0) {
            m3848a(exc);
        }
    }

    /* renamed from: a */
    protected void m3848a(Exception exc) {
        if (SystemClock.elapsedRealtime() - this.f22280g < 300000) {
            if (C11169au.m4849a((Context) this.f22274b)) {
                this.f22275c++;
                if (this.f22275c >= 2) {
                    String mo3853a = mo3853a();
                    AbstractC11049b.m5282a("max short conn time reached, sink down current host:" + mo3853a);
                    m3845a(mo3853a, 0L, exc);
                    this.f22275c = 0;
                    return;
                }
                return;
            }
            return;
        }
        this.f22275c = 0;
    }

    /* renamed from: a */
    protected void m3845a(String str, long j, Exception exc) {
        C11228cc m4561a = C11232cg.m4574a().m4561a(C11358fb.m3870a(), false);
        if (m4561a != null) {
            m4561a.m4588b(str, j, 0L, exc);
            C11232cg.m4574a().m4554c();
        }
    }

    @Override // com.xiaomi.push.AbstractC11356fa
    /* renamed from: a */
    public void mo3843a(C11339er[] c11339erArr) {
        throw new C11368fi("Don't support send Blob");
    }

    /* renamed from: a */
    private void m3849a(C11358fb c11358fb) {
        m3846a(c11358fb.m3863c(), c11358fb.m3871a());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0341  */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [boolean, int] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m3846a(java.lang.String r26, int r27) {
        /*
            Method dump skipped, instructions count: 843
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.AbstractC11364fh.m3846a(java.lang.String, int):void");
    }

    /* renamed from: a */
    protected synchronized void mo3851a() {
    }

    /* renamed from: e */
    public synchronized void m3838e() {
        try {
            if (!m3840c() && !m3884b()) {
                m3893a(0, 0, (Exception) null);
                m3849a(this.f22241a);
                return;
            }
            AbstractC11049b.m5282a("WARNING: current xmpp has connected");
        } catch (IOException e) {
            throw new C11368fi(e);
        }
    }

    /* renamed from: c */
    public void m3839c(final int i, final Exception exc) {
        C11169au.m4836b();
        this.f22274b.m2885a(new XMPushService.AbstractC11509j(2) { // from class: com.xiaomi.push.fh.2
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                AbstractC11364fh.this.f22274b.m2894a(i, exc);
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "shutdown the connection. " + i + ", " + exc;
            }
        });
    }

    /* renamed from: a */
    public Socket m3852a() {
        return new Socket();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C11228cc m3847a(final String str) {
        C11228cc m4561a = C11232cg.m4574a().m4561a(str, false);
        if (!m4561a.mo4541b()) {
            C11390fy.m3742a(new Runnable() { // from class: com.xiaomi.push.fh.3
                @Override // java.lang.Runnable
                public void run() {
                    C11232cg.m4574a().m4561a(str, true);
                }
            });
        }
        return m4561a;
    }

    /* renamed from: f */
    public void m3837f() {
        this.f22278e = SystemClock.elapsedRealtime();
    }

    /* renamed from: g */
    public void m3836g() {
        this.f22279f = SystemClock.elapsedRealtime();
    }
}
