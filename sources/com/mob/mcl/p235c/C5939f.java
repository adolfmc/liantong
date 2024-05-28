package com.mob.mcl.p235c;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.mob.mcl.c.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5939f {

    /* renamed from: a */
    C5924a f14609a;

    /* renamed from: b */
    final InterfaceC5937d f14610b;

    /* renamed from: c */
    final AtomicLong f14611c = new AtomicLong();

    public C5939f(InterfaceC5937d interfaceC5937d) {
        this.f14610b = interfaceC5937d;
    }

    /* renamed from: a */
    public void m12026a() {
        C5924a c5924a = this.f14609a;
        if (c5924a != null) {
            c5924a.m12046a(false);
        }
    }

    /* renamed from: a */
    public void m12024a(SocketAddress socketAddress, boolean z, boolean z2, int i) throws Throwable {
        C5924a c5924a = this.f14609a;
        if (c5924a != null) {
            if (!socketAddress.equals(c5924a.f14582c)) {
                this.f14609a.m12046a(false);
            } else if (m12023b()) {
                return;
            }
        }
        Socket socket = new Socket();
        socket.setKeepAlive(z);
        socket.setTcpNoDelay(z2);
        socket.connect(socketAddress, i);
        this.f14609a = new C5924a(socket, this.f14610b);
        this.f14609a.f14582c = socketAddress;
    }

    /* renamed from: b */
    public boolean m12023b() {
        C5924a c5924a = this.f14609a;
        return c5924a != null && c5924a.f14583d.get();
    }

    /* renamed from: a */
    public FutureC5936c m12025a(C5938e c5938e) {
        if (c5938e.f14607c == 0) {
            c5938e.f14607c = this.f14611c.incrementAndGet();
        }
        return this.f14609a.m12048a(c5938e);
    }
}
