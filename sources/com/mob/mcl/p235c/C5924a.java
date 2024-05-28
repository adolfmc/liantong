package com.mob.mcl.p235c;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.mob.mcl.c.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5924a {

    /* renamed from: a */
    final Socket f14580a;

    /* renamed from: b */
    public final InterfaceC5937d f14581b;

    /* renamed from: c */
    public SocketAddress f14582c;

    /* renamed from: d */
    public final AtomicBoolean f14583d = new AtomicBoolean(false);

    /* renamed from: e */
    final Map<FutureC5936c, Long> f14584e = new WeakHashMap();

    public C5924a(Socket socket, InterfaceC5937d interfaceC5937d) {
        this.f14580a = socket;
        this.f14581b = interfaceC5937d;
        this.f14583d.getAndSet(true);
        interfaceC5937d.mo12004a(this);
        new C5925a("mlp-worker").start();
    }

    /* renamed from: a */
    public FutureC5936c m12048a(C5938e c5938e) {
        FutureC5936c futureC5936c = new FutureC5936c();
        synchronized (this.f14584e) {
            this.f14584e.put(futureC5936c, Long.valueOf(c5938e.f14607c));
        }
        try {
            OutputStream outputStream = this.f14580a.getOutputStream();
            outputStream.write(c5938e.m12031a());
            outputStream.flush();
            return futureC5936c;
        } catch (Throwable th) {
            this.f14581b.mo12002a(this, th);
            return null;
        }
    }

    /* renamed from: a */
    void m12049a() {
        try {
            InputStream inputStream = this.f14580a.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8096];
            while (true) {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    return;
                }
                byteArrayOutputStream.write(bArr, 0, read);
                if (read < bArr.length) {
                    byteArrayOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    ByteBuffer wrap = ByteBuffer.wrap(byteArray);
                    int i = 0;
                    while (wrap.hasRemaining() && wrap.get() != 1) {
                        i++;
                    }
                    List<C5938e> m12030a = C5938e.m12030a((ByteBuffer) ((Buffer) new Object[]{wrap}[0]).position(i));
                    for (C5938e c5938e : m12030a) {
                        i += c5938e.m12029b();
                    }
                    m12047a(m12030a);
                    byteArrayOutputStream.reset();
                    if (byteArray.length - i > 0) {
                        byteArrayOutputStream.write(byteArray, i, byteArray.length - i);
                    }
                }
            }
        } catch (Throwable th) {
            this.f14581b.mo12002a(this, th);
            m12046a(true);
        }
    }

    /* renamed from: a */
    void m12047a(List<C5938e> list) {
        for (C5938e c5938e : list) {
            if (this.f14581b != null && c5938e.f14606b >= 9001) {
                this.f14581b.mo12003a(this, c5938e);
            }
            if (c5938e.f14606b < 9001) {
                Iterator<Map.Entry<FutureC5936c, Long>> it = this.f14584e.entrySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        Map.Entry<FutureC5936c, Long> next = it.next();
                        if (next.getValue().equals(Long.valueOf(c5938e.f14607c))) {
                            next.getKey().m12032a(c5938e);
                            break;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public void m12046a(boolean z) {
        if (this.f14583d.getAndSet(false)) {
            try {
                this.f14580a.close();
                this.f14581b.mo12001a(this, z);
            } catch (Throwable unused) {
            }
            this.f14583d.getAndSet(false);
            this.f14584e.clear();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.mcl.c.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class C5925a extends Thread {
        public C5925a(String str) {
            super(str);
            setDaemon(true);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            C5924a.this.m12049a();
        }
    }
}
