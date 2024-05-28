package com.xiaomi.push;

import android.content.Context;
import android.os.SystemClock;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.AbstractC11555an;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.XMPushService;
import io.socket.client.Socket;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.xiaomi.push.fa */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC11356fa {

    /* renamed from: a */
    private static final AtomicInteger f22237a = new AtomicInteger(0);

    /* renamed from: a */
    public static boolean f22238a;

    /* renamed from: a */
    protected C11358fb f22241a;

    /* renamed from: a */
    protected XMPushService f22243a;

    /* renamed from: a */
    protected int f22239a = 0;

    /* renamed from: a */
    protected long f22240a = -1;

    /* renamed from: b */
    protected volatile long f22249b = 0;

    /* renamed from: c */
    protected volatile long f22253c = 0;

    /* renamed from: a */
    private LinkedList<Pair<Integer, Long>> f22246a = new LinkedList<>();

    /* renamed from: a */
    private final Collection<InterfaceC11360fd> f22245a = new CopyOnWriteArrayList();

    /* renamed from: a */
    protected final Map<InterfaceC11362ff, C11357a> f22247a = new ConcurrentHashMap();

    /* renamed from: b */
    protected final Map<InterfaceC11362ff, C11357a> f22251b = new ConcurrentHashMap();

    /* renamed from: a */
    protected InterfaceC11369fj f22242a = null;

    /* renamed from: a */
    protected String f22244a = "";

    /* renamed from: b */
    protected String f22250b = "";

    /* renamed from: c */
    private int f22252c = 2;

    /* renamed from: b */
    protected final int f22248b = f22237a.getAndIncrement();

    /* renamed from: e */
    private long f22255e = 0;

    /* renamed from: d */
    protected long f22254d = 0;

    /* renamed from: a */
    private String m3895a(int i) {
        return i == 1 ? "connected" : i == 0 ? Socket.EVENT_CONNECTING : i == 2 ? "disconnected" : "unknown";
    }

    /* renamed from: a */
    public abstract void mo3888a(AbstractC11375fo abstractC11375fo);

    /* renamed from: a */
    public abstract void mo3887a(C11545am.C11547b c11547b);

    /* renamed from: a */
    public abstract void mo3885a(String str, String str2);

    /* renamed from: a */
    public abstract void mo3843a(C11339er[] c11339erArr);

    /* renamed from: a */
    public boolean mo3896a() {
        return false;
    }

    /* renamed from: b */
    public abstract void mo3842b(int i, Exception exc);

    /* renamed from: b */
    public abstract void mo3880b(C11339er c11339er);

    /* renamed from: b */
    public abstract void mo3841b(boolean z);

    static {
        f22238a = false;
        try {
            f22238a = Boolean.getBoolean("smack.debugEnabled");
        } catch (Exception unused) {
        }
        C11363fg.m3859a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC11356fa(XMPushService xMPushService, C11358fb c11358fb) {
        this.f22241a = c11358fb;
        this.f22243a = xMPushService;
        m3882b();
    }

    /* renamed from: a */
    public C11358fb m3898a() {
        return this.f22241a;
    }

    /* renamed from: a */
    public String mo3853a() {
        return this.f22241a.m3863c();
    }

    /* renamed from: b */
    public String m3883b() {
        return this.f22241a.m3865b();
    }

    /* renamed from: a */
    public long m3899a() {
        return this.f22253c;
    }

    /* renamed from: a */
    public void m3891a(InterfaceC11360fd interfaceC11360fd) {
        if (interfaceC11360fd == null || this.f22245a.contains(interfaceC11360fd)) {
            return;
        }
        this.f22245a.add(interfaceC11360fd);
    }

    /* renamed from: b */
    public void m3879b(InterfaceC11360fd interfaceC11360fd) {
        this.f22245a.remove(interfaceC11360fd);
    }

    /* renamed from: a */
    public void m3889a(InterfaceC11362ff interfaceC11362ff, InterfaceC11370fk interfaceC11370fk) {
        if (interfaceC11362ff == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f22247a.put(interfaceC11362ff, new C11357a(interfaceC11362ff, interfaceC11370fk));
    }

    /* renamed from: a */
    public void m3890a(InterfaceC11362ff interfaceC11362ff) {
        this.f22247a.remove(interfaceC11362ff);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Map<InterfaceC11362ff, C11357a> m3897a() {
        return this.f22247a;
    }

    /* renamed from: b */
    public void m3877b(InterfaceC11362ff interfaceC11362ff, InterfaceC11370fk interfaceC11370fk) {
        if (interfaceC11362ff == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f22251b.put(interfaceC11362ff, new C11357a(interfaceC11362ff, interfaceC11370fk));
    }

    /* renamed from: b */
    public void m3878b(InterfaceC11362ff interfaceC11362ff) {
        this.f22251b.remove(interfaceC11362ff);
    }

    /* renamed from: b */
    protected void m3882b() {
        String str;
        if (this.f22241a.m3869a() && this.f22242a == null) {
            Class<?> cls = null;
            try {
                str = System.getProperty("smack.debuggerClass");
            } catch (Throwable unused) {
                str = null;
            }
            if (str != null) {
                try {
                    cls = Class.forName(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cls == null) {
                this.f22242a = new C11352ez(this);
                return;
            }
            try {
                this.f22242a = (InterfaceC11369fj) cls.getConstructor(AbstractC11356fa.class, Writer.class, Reader.class).newInstance(this);
            } catch (Exception e2) {
                throw new IllegalArgumentException("Can't initialize the configured debugger!", e2);
            }
        }
    }

    /* renamed from: com.xiaomi.push.fa$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11357a {

        /* renamed from: a */
        private InterfaceC11362ff f22256a;

        /* renamed from: a */
        private InterfaceC11370fk f22257a;

        public C11357a(InterfaceC11362ff interfaceC11362ff, InterfaceC11370fk interfaceC11370fk) {
            this.f22256a = interfaceC11362ff;
            this.f22257a = interfaceC11370fk;
        }

        /* renamed from: a */
        public void m3872a(AbstractC11375fo abstractC11375fo) {
            InterfaceC11370fk interfaceC11370fk = this.f22257a;
            if (interfaceC11370fk == null || interfaceC11370fk.mo2829a(abstractC11375fo)) {
                this.f22256a.mo2830a(abstractC11375fo);
            }
        }

        /* renamed from: a */
        public void m3873a(C11339er c11339er) {
            this.f22256a.mo2831a(c11339er);
        }
    }

    /* renamed from: b */
    public boolean m3881b() {
        return this.f22252c == 0;
    }

    /* renamed from: c */
    public boolean m3875c() {
        return this.f22252c == 1;
    }

    /* renamed from: a */
    public int m3900a() {
        return this.f22239a;
    }

    /* renamed from: a */
    public void m3893a(int i, int i2, Exception exc) {
        int i3 = this.f22252c;
        if (i != i3) {
            AbstractC11049b.m5282a(String.format("update the connection status. %1$s -> %2$s : %3$s ", m3895a(i3), m3895a(i), AbstractC11555an.m2661a(i2)));
        }
        if (C11169au.m4849a((Context) this.f22243a)) {
            m3894a(i);
        }
        if (i == 1) {
            this.f22243a.m2896a(10);
            if (this.f22252c != 0) {
                AbstractC11049b.m5282a("try set connected while not connecting.");
            }
            this.f22252c = i;
            for (InterfaceC11360fd interfaceC11360fd : this.f22245a) {
                interfaceC11360fd.mo2857b(this);
            }
        } else if (i == 0) {
            if (this.f22252c != 2) {
                AbstractC11049b.m5282a("try set connecting while not disconnected.");
            }
            this.f22252c = i;
            for (InterfaceC11360fd interfaceC11360fd2 : this.f22245a) {
                interfaceC11360fd2.mo2889a(this);
            }
        } else if (i == 2) {
            this.f22243a.m2896a(10);
            int i4 = this.f22252c;
            if (i4 == 0) {
                for (InterfaceC11360fd interfaceC11360fd3 : this.f22245a) {
                    interfaceC11360fd3.mo2887a(this, exc == null ? new CancellationException("disconnect while connecting") : exc);
                }
            } else if (i4 == 1) {
                for (InterfaceC11360fd interfaceC11360fd4 : this.f22245a) {
                    interfaceC11360fd4.mo2888a(this, i2, exc);
                }
            }
            this.f22252c = i;
        }
    }

    /* renamed from: b */
    public int m3884b() {
        return this.f22252c;
    }

    /* renamed from: a */
    public synchronized void m3886a(String str) {
        if (this.f22252c == 0) {
            AbstractC11049b.m5282a("setChallenge hash = " + C11183ba.m4761a(str).substring(0, 8));
            this.f22244a = str;
            m3893a(1, 0, null);
        } else {
            AbstractC11049b.m5282a("ignore setChallenge because connection was disconnected");
        }
    }

    /* renamed from: c */
    public synchronized void m3876c() {
        this.f22255e = SystemClock.elapsedRealtime();
    }

    /* renamed from: a */
    public synchronized boolean m3892a(long j) {
        return this.f22255e >= j;
    }

    /* renamed from: a */
    private void m3894a(int i) {
        synchronized (this.f22246a) {
            if (i == 1) {
                this.f22246a.clear();
            } else {
                this.f22246a.add(new Pair<>(Integer.valueOf(i), Long.valueOf(System.currentTimeMillis())));
                if (this.f22246a.size() > 6) {
                    this.f22246a.remove(0);
                }
            }
        }
    }

    /* renamed from: d */
    public void m3874d() {
        synchronized (this.f22246a) {
            this.f22246a.clear();
        }
    }
}
