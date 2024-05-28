package p000;

import android.se.omapi.Channel;
import android.se.omapi.Reader;
import android.se.omapi.SEService;
import android.se.omapi.Session;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import com.crb.jscard.entity.CardResult;
import java.util.concurrent.Executor;

@RequiresApi(api = 28)
/* renamed from: m  reason: case insensitive filesystem */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class SmartCardByGoogle extends AbstractC12227j implements SEService.OnConnectedListener {

    /* renamed from: c */
    public SEService f24919c;

    /* renamed from: e */
    public Channel f24921e;

    /* renamed from: f */
    public Session f24922f;

    /* renamed from: b */
    public Object f24918b = new Object();

    /* renamed from: d */
    public boolean f24920d = false;

    /* renamed from: g */
    public final String f24923g = SmartCardByGoogle.class.getSimpleName();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: SmartCardByGoogle.java */
    /* renamed from: m$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class ExecutorC12303a implements Executor {
        public ExecutorC12303a(SmartCardByGoogle smartCardByGoogle) {
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    }

    @Override // p000.AbstractC12227j
    /* renamed from: a */
    public void mo1833a() {
        SEService sEService = this.f24919c;
        if (sEService == null || !sEService.isConnected()) {
            this.f24919c = new SEService(Utils.m22196a().getApplicationContext(), new ExecutorC12303a(this), this);
            C14231v.m72b(this.f24923g, "start bind SEService");
            if (this.f24920d) {
                return;
            }
            synchronized (this.f24918b) {
                if (!this.f24920d) {
                    C14231v.m74a(this.f24923g, "thread is waiting");
                    try {
                        this.f24918b.wait();
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
        m1836e();
    }

    @Override // p000.AbstractC12227j
    /* renamed from: c */
    public void mo1830c() {
        try {
            mo1831b();
            SEService sEService = this.f24919c;
            if (sEService == null || !sEService.isConnected()) {
                return;
            }
            this.f24919c.shutdown();
            this.f24919c = null;
            this.f24920d = false;
            C14231v.m71c(this.f24923g, "SEService shutdown success");
        } catch (Exception e) {
            e.printStackTrace();
            String str = this.f24923g;
            C14231v.m72b(str, "SEService shutdown error:" + e.getMessage());
        }
    }

    /* renamed from: d */
    public final Object[] m1837d(String str) {
        Reader m1835f = m1835f();
        if (m1835f == null) {
            return new Object[]{-1, "selected reader not exist"};
        }
        if (m1835f.isSecureElementPresent()) {
            this.f24922f = m1835f.openSession();
            byte[] m78a = Hex.m78a(str);
            String str2 = this.f24923g;
            C14231v.m71c(str2, "open channel applet：" + str);
            Session session = this.f24922f;
            if (session != null) {
                this.f24921e = session.openLogicalChannel(m78a);
            }
            return this.f24921e == null ? new Object[]{-1, "channel is null"} : new Object[]{0, "open channel success"};
        }
        return new Object[]{-1, "selected reader can not use"};
    }

    /* renamed from: e */
    public void m1836e() {
        try {
            Channel channel = this.f24921e;
            if (channel != null && channel.isOpen()) {
                this.f24921e.close();
                this.f24921e = null;
                C14231v.m71c(this.f24923g, "channel close success");
            }
        } catch (Exception e) {
            String str = this.f24923g;
            C14231v.m71c(str, "channel close error:" + e.getMessage());
        }
        try {
            Session session = this.f24922f;
            if (session == null || session.isClosed()) {
                return;
            }
            this.f24922f.close();
            this.f24922f = null;
            C14231v.m74a(this.f24923g, "session close success");
        } catch (Exception e2) {
            String str2 = this.f24923g;
            C14231v.m72b(str2, "session close error:" + e2.getMessage());
        }
    }

    /* renamed from: f */
    public final Reader m1835f() {
        C14231v.m74a(this.f24923g, "select reader name:" + m1927d().m1916a());
        Reader[] readers = this.f24919c.getReaders();
        if (readers.length < 1) {
            C14231v.m72b(this.f24923g, "There is no avaliable reader");
            return null;
        }
        for (Reader reader : readers) {
            if (reader.getName().startsWith(m1927d().m1916a())) {
                return reader;
            }
        }
        return null;
    }

    @Override // android.se.omapi.SEService.OnConnectedListener
    public void onConnected() {
        C14231v.m71c(this.f24923g, "service is connected");
        synchronized (this.f24918b) {
            this.f24920d = true;
            this.f24918b.notifyAll();
        }
    }

    /* renamed from: c */
    public final synchronized CardResult m1838c(String str) {
        String str2 = this.f24923g;
        C14231v.m74a(str2, "Command APDU:" + str);
        if (str.startsWith("00A404")) {
            m1836e();
            Object[] m1837d = m1837d(str.substring(10, (Integer.parseInt(str.substring(8, 10), 16) * 2) + 10));
            int intValue = ((Integer) m1837d[0]).intValue();
            String str3 = (String) m1837d[1];
            if (intValue == 0) {
                String m77a = Hex.m77a(this.f24921e.getSelectResponse());
                String str4 = this.f24923g;
                C14231v.m74a(str4, "Response APDU：" + m77a);
                return new CardResult(0, str3, m77a);
            }
            String str5 = this.f24923g;
            C14231v.m72b(str5, "OpenChannel Error Desc:" + str3);
            return new CardResult(intValue, str3);
        }
        byte[] m78a = Hex.m78a(str);
        if (this.f24921e != null) {
            String str6 = this.f24923g;
            C14231v.m74a(str6, "mChannel------" + this.f24921e.toString());
            String m77a2 = Hex.m77a(this.f24921e.transmit(m78a));
            String str7 = this.f24923g;
            C14231v.m74a(str7, "Response APDU：" + m77a2);
            return new CardResult(0, "transmit apdu success", m77a2);
        }
        return new CardResult(-1, "Channal is not open");
    }

    @Override // p000.AbstractC12227j
    /* renamed from: a */
    public CardResult mo1832a(String str) {
        try {
            return m1838c(str);
        } catch (InterruptedException e) {
            return m1928b("thread error:" + e.getMessage());
        } catch (Exception e2) {
            return m1928b("error:" + e2.getMessage());
        }
    }
}
