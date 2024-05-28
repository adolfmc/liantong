package p000;

import android.support.annotation.NonNull;
import com.crb.jscard.entity.CardResult;
import org.simalliance.openmobileapi.Channel;
import org.simalliance.openmobileapi.Reader;
import org.simalliance.openmobileapi.SEService;
import org.simalliance.openmobileapi.Session;

/* renamed from: n  reason: case insensitive filesystem */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class SmartCardBySimalliance extends AbstractC12227j implements SEService.CallBack {

    /* renamed from: b */
    public SEService f24934b;

    /* renamed from: e */
    public Channel f24937e;

    /* renamed from: f */
    public Session f24938f;

    /* renamed from: c */
    public boolean f24935c = false;

    /* renamed from: d */
    public Object f24936d = new Object();

    /* renamed from: g */
    public final String f24939g = SmartCardBySimalliance.class.getSimpleName();

    @Override // p000.AbstractC12227j
    /* renamed from: a */
    public void mo1833a() {
        SEService sEService = this.f24934b;
        if (sEService == null || !sEService.isConnected()) {
            new SEService(Utils.m22196a().getApplicationContext(), this);
            C14231v.m74a(this.f24939g, "start bind SEService");
            if (this.f24935c) {
                return;
            }
            synchronized (this.f24936d) {
                if (!this.f24935c) {
                    C14231v.m75a("thread is waiting");
                    try {
                        this.f24936d.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override // p000.AbstractC12227j
    /* renamed from: b */
    public void mo1831b() {
        m1827e();
    }

    @Override // p000.AbstractC12227j
    /* renamed from: c */
    public void mo1830c() {
        mo1831b();
        try {
            SEService sEService = this.f24934b;
            if (sEService == null || !sEService.isConnected()) {
                return;
            }
            this.f24934b.shutdown();
            this.f24934b = null;
            this.f24935c = false;
            C14231v.m71c(this.f24939g, "SEService shutdown success");
        } catch (Exception e) {
            String str = this.f24939g;
            C14231v.m72b(str, "SEService shutdown error:" + e.getMessage());
        }
    }

    /* renamed from: d */
    public final Object[] m1828d(String str) {
        Reader m1826f = m1826f();
        if (m1826f == null) {
            return new Object[]{-1, "selected reader not exist"};
        }
        if (m1826f.isSecureElementPresent()) {
            this.f24938f = m1826f.openSession();
            byte[] m78a = Hex.m78a(str);
            String str2 = this.f24939g;
            C14231v.m74a(str2, "open channel applet：" + str);
            Session session = this.f24938f;
            if (session != null) {
                this.f24937e = session.openLogicalChannel(m78a);
            }
            return this.f24937e == null ? new Object[]{-1, "channel is null"} : new Object[]{0, "open channel success"};
        }
        return new Object[]{-1, "selected reader can not use"};
    }

    /* renamed from: e */
    public void m1827e() {
        try {
            Channel channel = this.f24937e;
            if (channel != null && !channel.isClosed()) {
                this.f24937e.close();
                this.f24937e = null;
                C14231v.m71c(this.f24939g, "channel close success");
            }
        } catch (Exception e) {
            String str = this.f24939g;
            C14231v.m71c(str, "channel close error:" + e.getMessage());
        }
        try {
            Session session = this.f24938f;
            if (session == null || session.isClosed()) {
                return;
            }
            this.f24938f.close();
            this.f24938f = null;
            C14231v.m74a(this.f24939g, "session close success");
        } catch (Exception e2) {
            String str2 = this.f24939g;
            C14231v.m72b(str2, "session close error:" + e2.getMessage());
        }
    }

    /* renamed from: f */
    public final Reader m1826f() {
        C14231v.m74a(this.f24939g, "select reader name:" + m1927d().m1916a());
        Reader[] readers = this.f24934b.getReaders();
        if (readers != null && readers.length >= 1) {
            C14231v.m74a(this.f24939g, String.valueOf(readers.length));
            for (Reader reader : readers) {
                C14231v.m72b(this.f24939g, "avaliable reader name:" + reader.getName());
                C14231v.m72b(this.f24939g, String.valueOf(reader.isSecureElementPresent()));
                if (reader.getName().startsWith(m1927d().m1916a())) {
                    return reader;
                }
            }
            return null;
        }
        C14231v.m72b(this.f24939g, "There is no avaliable reader");
        return null;
    }

    @Override // org.simalliance.openmobileapi.SEService.CallBack
    public void serviceConnected(SEService sEService) {
        C14231v.m74a(this.f24939g, "service connected");
        synchronized (this.f24936d) {
            if (sEService.isConnected()) {
                C14231v.m75a("bind SEService success");
                this.f24934b = sEService;
            }
            C14231v.m74a(this.f24939g, "Thread notifyAll");
            this.f24935c = true;
            this.f24936d.notifyAll();
        }
    }

    /* renamed from: c */
    public synchronized CardResult m1829c(String str) {
        String str2 = this.f24939g;
        C14231v.m74a(str2, "Command APDU:" + str);
        if (str.startsWith("00A404")) {
            m1827e();
            Object[] m1828d = m1828d(str.substring(10, (Integer.parseInt(str.substring(8, 10), 16) * 2) + 10));
            int intValue = ((Integer) m1828d[0]).intValue();
            String str3 = (String) m1828d[1];
            if (intValue == 0) {
                String m77a = Hex.m77a(this.f24937e.getSelectResponse());
                String str4 = this.f24939g;
                C14231v.m74a(str4, "Response APDU：" + m77a);
                return new CardResult(0, str3, m77a);
            }
            String str5 = this.f24939g;
            C14231v.m72b(str5, "OpenChannel Error Desc:" + str3);
            return new CardResult(intValue, str3);
        }
        byte[] m78a = Hex.m78a(str);
        Channel channel = this.f24937e;
        if (channel != null) {
            String m77a2 = Hex.m77a(channel.transmit(m78a));
            String str6 = this.f24939g;
            C14231v.m74a(str6, "Response APDU：" + m77a2);
            return new CardResult(0, "transmit apdu success", m77a2);
        }
        return new CardResult(-1, "Channal is not open");
    }

    @Override // p000.AbstractC12227j
    /* renamed from: a */
    public CardResult mo1832a(@NonNull String str) {
        try {
            return m1829c(str);
        } catch (InterruptedException e) {
            C14231v.m72b(this.f24939g, e.getMessage());
            return m1928b("thread error:" + e.getMessage());
        } catch (Exception e2) {
            C14231v.m72b(this.f24939g, e2.getMessage());
            return m1928b("error:" + e2.getMessage());
        }
    }
}
