package com.xiaomi.push;

import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.AbstractC11356fa;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.service.AbstractC11590e;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.C11561ar;
import com.xiaomi.push.service.C11570aw;
import com.xiaomi.push.service.C11571ax;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.ey */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11350ey extends AbstractC11364fh {

    /* renamed from: a */
    private C11341et f22217a;

    /* renamed from: a */
    private C11342eu f22218a;

    /* renamed from: a */
    private Thread f22219a;

    /* renamed from: a */
    private byte[] f22220a;

    @Override // com.xiaomi.push.AbstractC11356fa
    /* renamed from: a */
    public boolean mo3896a() {
        return true;
    }

    public C11350ey(XMPushService xMPushService, C11358fb c11358fb) {
        super(xMPushService, c11358fb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized byte[] m3913a() {
        if (this.f22220a == null && !TextUtils.isEmpty(this.f22244a)) {
            String m2624a = C11571ax.m2624a();
            this.f22220a = C11561ar.m2644a(this.f22244a.getBytes(), (this.f22244a.substring(this.f22244a.length() / 2) + m2624a.substring(m2624a.length() / 2)).getBytes());
        }
        return this.f22220a;
    }

    /* renamed from: a */
    private C11339er m3910a(boolean z) {
        C11349ex c11349ex = new C11349ex();
        if (z) {
            c11349ex.m3958a("1");
        }
        byte[] m3980a = C11336ep.m3980a();
        if (m3980a != null) {
            C11289dp.C11299j c11299j = new C11289dp.C11299j();
            c11299j.m4158a(C11129a.m4945a(m3980a));
            c11349ex.m3953a(c11299j.mo4063a(), (String) null);
        }
        return c11349ex;
    }

    @Override // com.xiaomi.push.AbstractC11364fh
    /* renamed from: a */
    protected void mo3844a(boolean z) {
        if (this.f22218a != null) {
            C11339er m3910a = m3910a(z);
            AbstractC11049b.m5282a("[Slim] SND ping id=" + m3910a.m3941e());
            mo3880b(m3910a);
            m3837f();
            return;
        }
        throw new C11368fi("The BlobWriter is null.");
    }

    @Override // com.xiaomi.push.AbstractC11356fa
    /* renamed from: a */
    public synchronized void mo3887a(C11545am.C11547b c11547b) {
        C11338eq.m3970a(c11547b, m3840c(), this);
    }

    @Override // com.xiaomi.push.AbstractC11356fa
    /* renamed from: a */
    public synchronized void mo3885a(String str, String str2) {
        C11338eq.m3969a(str, str2, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaomi.push.AbstractC11364fh
    /* renamed from: a */
    public synchronized void mo3850a(int i, Exception exc) {
        if (this.f22217a != null) {
            this.f22217a.m3933b();
            this.f22217a = null;
        }
        if (this.f22218a != null) {
            try {
                this.f22218a.m3929b();
            } catch (Exception e) {
                AbstractC11049b.m5268d("SlimConnection shutdown cause exception: " + e);
            }
            this.f22218a = null;
        }
        this.f22220a = null;
        super.mo3850a(i, exc);
    }

    @Override // com.xiaomi.push.AbstractC11364fh, com.xiaomi.push.AbstractC11356fa
    /* renamed from: a */
    public void mo3843a(C11339er[] c11339erArr) {
        for (C11339er c11339er : c11339erArr) {
            mo3880b(c11339er);
        }
    }

    @Override // com.xiaomi.push.AbstractC11356fa
    @Deprecated
    /* renamed from: a */
    public void mo3888a(AbstractC11375fo abstractC11375fo) {
        mo3880b(C11339er.m3959a(abstractC11375fo, (String) null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaomi.push.AbstractC11364fh
    /* renamed from: a */
    public synchronized void mo3851a() {
        m3908h();
        this.f22218a.m3931a();
    }

    /* renamed from: h */
    private void m3908h() {
        try {
            this.f22217a = new C11341et(this.f22273a.getInputStream(), this);
            this.f22218a = new C11342eu(this.f22273a.getOutputStream(), this);
            this.f22219a = new Thread("Blob Reader (" + this.f22248b + ")") { // from class: com.xiaomi.push.ey.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        C11350ey.this.f22217a.m3935a();
                    } catch (Exception e) {
                        C11350ey.this.m3839c(9, e);
                    }
                }
            };
            this.f22219a.start();
        } catch (Exception e) {
            throw new C11368fi("Error to init reader and writer", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m3909b(AbstractC11375fo abstractC11375fo) {
        if (abstractC11375fo == null) {
            return;
        }
        for (AbstractC11356fa.C11357a c11357a : this.f22247a.values()) {
            c11357a.m3872a(abstractC11375fo);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m3912a(C11339er c11339er) {
        if (c11339er == null) {
            return;
        }
        if (AbstractC11590e.m2559a(c11339er)) {
            C11339er c11339er2 = new C11339er();
            c11339er2.m3962a(c11339er.m3968a());
            c11339er2.m3956a("SYNC", "ACK_RTT");
            c11339er2.m3958a(c11339er.m3941e());
            c11339er2.m3948b(c11339er.m3951b());
            c11339er2.m3961a(c11339er.m3946c());
            this.f22243a.m2885a(new C11570aw(this.f22243a, c11339er2));
        }
        if (c11339er.m3964a()) {
            AbstractC11049b.m5282a("[Slim] RCV blob chid=" + c11339er.m3968a() + "; id=" + c11339er.m3941e() + "; errCode=" + c11339er.m3952b() + "; err=" + c11339er.m3945c());
        }
        if (c11339er.m3968a() == 0) {
            if ("PING".equals(c11339er.m3966a())) {
                AbstractC11049b.m5282a("[Slim] RCV ping id=" + c11339er.m3941e());
                m3836g();
            } else if ("CLOSE".equals(c11339er.m3966a())) {
                m3839c(13, null);
            }
        }
        for (AbstractC11356fa.C11357a c11357a : this.f22247a.values()) {
            c11357a.m3873a(c11339er);
        }
    }

    @Override // com.xiaomi.push.AbstractC11356fa
    /* renamed from: b */
    public void mo3880b(C11339er c11339er) {
        C11342eu c11342eu = this.f22218a;
        if (c11342eu != null) {
            try {
                int m3930a = c11342eu.m3930a(c11339er);
                this.f22254d = SystemClock.elapsedRealtime();
                String m3940f = c11339er.m3940f();
                if (!TextUtils.isEmpty(m3940f)) {
                    C11392fz.m3733a(this.f22243a, m3940f, m3930a, false, true, System.currentTimeMillis());
                }
                for (AbstractC11356fa.C11357a c11357a : this.f22251b.values()) {
                    c11357a.m3873a(c11339er);
                }
                return;
            } catch (Exception e) {
                throw new C11368fi(e);
            }
        }
        throw new C11368fi("the writer is null.");
    }
}
