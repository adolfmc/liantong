package com.networkbench.agent.impl.socket.p279b;

import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6631a;
import com.networkbench.agent.impl.util.C6633c;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6645n;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketImplFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6605b implements SocketImplFactory {

    /* renamed from: a */
    private Class<? extends SocketImpl> f16990a;

    /* renamed from: b */
    private SocketImplFactory f16991b;

    public C6605b(Class<? extends SocketImpl> cls) {
        this.f16990a = cls;
    }

    public C6605b(SocketImplFactory socketImplFactory) {
        this.f16991b = socketImplFactory;
    }

    @Override // java.net.SocketImplFactory
    public SocketImpl createSocketImpl() {
        SocketImplFactory socketImplFactory = this.f16991b;
        SocketImpl createSocketImpl = socketImplFactory != null ? socketImplFactory.createSocketImpl() : null;
        if (createSocketImpl == null) {
            try {
                createSocketImpl = (SocketImpl) C6645n.m8877a(C6645n.m8883a(Socket.class, SocketImpl.class), m9260b());
            } catch (Throwable unused) {
            }
        }
        C6631a.m9136a(createSocketImpl);
        return (createSocketImpl == null || m9261a()) ? createSocketImpl : new C6604a(createSocketImpl);
    }

    /* renamed from: a */
    private static boolean m9261a() {
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length <= 0) {
                return false;
            }
            for (int i = 0; i < stackTrace.length; i++) {
                if (stackTrace[i] != null && stackTrace[i].toString().contains("java.net.ServerSocket")) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            C6633c.m9111d("isServerSocket error", new Object[0]);
            return false;
        }
    }

    /* renamed from: b */
    private static Socket m9260b() {
        try {
            Constructor declaredConstructor = Socket.class.getDeclaredConstructor(SocketImpl.class);
            declaredConstructor.setAccessible(true);
            return (Socket) declaredConstructor.newInstance(m9259c());
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10115e("createAvalidSocket error:" + th.getMessage());
            return null;
        }
    }

    /* renamed from: c */
    private static SocketImpl m9259c() {
        try {
            Constructor<?> declaredConstructor = Class.forName("java.net.SocksSocketImpl").getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            return (SocketImpl) declaredConstructor.newInstance(new Object[0]);
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10115e("createSocksSocketImpl e" + th);
            return null;
        }
    }
}
