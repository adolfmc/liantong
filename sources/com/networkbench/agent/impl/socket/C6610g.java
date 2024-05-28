package com.networkbench.agent.impl.socket;

import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketImplFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6610g implements SocketImplFactory {

    /* renamed from: a */
    private static boolean f17005a;

    /* renamed from: a */
    public static boolean m9258a() {
        boolean z = f17005a;
        if (z) {
            return z;
        }
        C6610g c6610g = new C6610g();
        try {
            c6610g.createSocketImpl();
            Socket.setSocketImplFactory(c6610g);
            f17005a = true;
            return true;
        } catch (Throwable unused) {
            return f17005a;
        }
    }

    @Override // java.net.SocketImplFactory
    public final SocketImpl createSocketImpl() {
        return new C6609f();
    }
}
