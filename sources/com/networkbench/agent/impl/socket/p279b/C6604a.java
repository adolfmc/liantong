package com.networkbench.agent.impl.socket.p279b;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p243c.C6309j;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.socket.C6611h;
import com.networkbench.agent.impl.socket.C6612i;
import com.networkbench.agent.impl.socket.C6613j;
import com.networkbench.agent.impl.socket.InterfaceC6616m;
import com.networkbench.agent.impl.socket.p278a.C6601a;
import com.networkbench.agent.impl.util.C6640i;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6645n;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6604a extends SocketImpl implements InterfaceC6616m {

    /* renamed from: A */
    private static boolean f16956A = false;

    /* renamed from: a */
    private static Field f16959a = null;

    /* renamed from: b */
    private static Field f16960b = null;

    /* renamed from: c */
    private static Field f16961c = null;

    /* renamed from: d */
    private static Field f16962d = null;

    /* renamed from: e */
    private static final int f16963e = 0;

    /* renamed from: f */
    private static final int f16964f = 1;

    /* renamed from: g */
    private static final int f16965g = 2;

    /* renamed from: h */
    private static final int f16966h = 3;

    /* renamed from: i */
    private static final int f16967i = 4;

    /* renamed from: j */
    private static final int f16968j = 5;

    /* renamed from: k */
    private static final int f16969k = 6;

    /* renamed from: l */
    private static final int f16970l = 7;

    /* renamed from: m */
    private static final int f16971m = 8;

    /* renamed from: n */
    private static final int f16972n = 9;

    /* renamed from: o */
    private static final int f16973o = 10;

    /* renamed from: p */
    private static final int f16974p = 11;

    /* renamed from: q */
    private static final int f16975q = 12;

    /* renamed from: r */
    private static final int f16976r = 13;

    /* renamed from: s */
    private static final int f16977s = 14;

    /* renamed from: t */
    private static final int f16978t = 15;

    /* renamed from: u */
    private static final int f16979u = 16;

    /* renamed from: v */
    private static final int f16980v = 17;

    /* renamed from: w */
    private static final int f16981w = 18;

    /* renamed from: x */
    private static final int f16982x = 19;

    /* renamed from: y */
    private static final int f16983y = 20;

    /* renamed from: B */
    private int f16985B;

    /* renamed from: E */
    private SocketImpl f16987E;

    /* renamed from: G */
    private boolean f16988G;

    /* renamed from: z */
    private static Method[] f16984z = new Method[20];

    /* renamed from: D */
    private static Throwable f16957D = null;

    /* renamed from: F */
    private static final InterfaceC6393e f16958F = C6394f.m10150a();

    /* renamed from: C */
    private String f16986C = "";

    /* renamed from: H */
    private C6613j f16989H = new C6613j();

    static {
        int i = 20;
        int i2 = 0;
        f16956A = false;
        try {
            f16959a = SocketImpl.class.getDeclaredField("address");
            f16960b = SocketImpl.class.getDeclaredField("fd");
            f16961c = SocketImpl.class.getDeclaredField("localport");
            f16962d = SocketImpl.class.getDeclaredField("port");
            C6645n.m8878a((AccessibleObject) f16959a, new AccessibleObject[]{f16960b, f16961c, f16962d});
            f16984z[0] = SocketImpl.class.getDeclaredMethod("accept", SocketImpl.class);
            f16984z[1] = SocketImpl.class.getDeclaredMethod("available", new Class[0]);
            f16984z[2] = SocketImpl.class.getDeclaredMethod("bind", InetAddress.class, Integer.TYPE);
            f16984z[3] = SocketImpl.class.getDeclaredMethod("close", new Class[0]);
            f16984z[4] = SocketImpl.class.getDeclaredMethod("connect", InetAddress.class, Integer.TYPE);
            f16984z[5] = SocketImpl.class.getDeclaredMethod("connect", SocketAddress.class, Integer.TYPE);
            f16984z[6] = SocketImpl.class.getDeclaredMethod("connect", String.class, Integer.TYPE);
            f16984z[7] = SocketImpl.class.getDeclaredMethod("create", Boolean.TYPE);
            f16984z[8] = SocketImpl.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            f16984z[9] = SocketImpl.class.getDeclaredMethod("getInetAddress", new Class[0]);
            f16984z[10] = SocketImpl.class.getDeclaredMethod("getInputStream", new Class[0]);
            f16984z[11] = SocketImpl.class.getDeclaredMethod("getLocalPort", new Class[0]);
            f16984z[12] = SocketImpl.class.getDeclaredMethod("getOutputStream", new Class[0]);
            f16984z[13] = SocketImpl.class.getDeclaredMethod("getPort", new Class[0]);
            f16984z[14] = SocketImpl.class.getDeclaredMethod("listen", Integer.TYPE);
            f16984z[15] = SocketImpl.class.getDeclaredMethod("sendUrgentData", Integer.TYPE);
            f16984z[16] = SocketImpl.class.getDeclaredMethod("setPerformancePreferences", Integer.TYPE, Integer.TYPE, Integer.TYPE);
            f16984z[17] = SocketImpl.class.getDeclaredMethod("shutdownInput", new Class[0]);
            f16984z[18] = SocketImpl.class.getDeclaredMethod("shutdownOutput", new Class[0]);
            f16984z[19] = SocketImpl.class.getDeclaredMethod("supportsUrgentData", new Class[0]);
            C6645n.m8875a(f16984z);
            f16956A = true;
        } catch (NoSuchFieldException e) {
            f16956A = false;
            String str = "unknown";
            if (f16959a == null) {
                str = "address";
            } else if (f16960b == null) {
                str = "fd";
            } else if (f16961c == null) {
                str = "localport";
            } else if (f16962d == null) {
                str = "port";
            }
            m9268a(new C6601a("No such field: " + str, e));
        } catch (NoSuchMethodException e2) {
            f16956A = false;
            while (true) {
                if (i2 >= 20) {
                    break;
                } else if (f16984z[i2] == null) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            m9268a(new C6601a("Bad method: " + i, e2));
        } catch (SecurityException e3) {
            f16956A = false;
            m9268a(e3);
        } catch (Throwable th) {
            f16956A = false;
            m9268a(th);
        }
    }

    public C6604a(SocketImpl socketImpl) {
        if (socketImpl == null) {
            throw new NullPointerException("delegate was null");
        }
        this.f16987E = socketImpl;
        m9262e();
    }

    @Override // com.networkbench.agent.impl.socket.InterfaceC6616m
    /* renamed from: a */
    public void mo9233a(NBSTransactionState nBSTransactionState) {
        this.f16989H.m9254a(nBSTransactionState);
    }

    /* renamed from: a */
    public static boolean m9270a() {
        return f16956A;
    }

    /* renamed from: b */
    public static Throwable m9267b() {
        return f16957D;
    }

    /* renamed from: a */
    private static void m9268a(Throwable th) {
        f16957D = th;
    }

    /* renamed from: d */
    private void m9263d() {
        try {
            f16959a.set(this.f16987E, this.address);
            f16960b.set(this.f16987E, this.fd);
            f16961c.setInt(this.f16987E, this.localport);
            f16962d.setInt(this.f16987E, this.port);
        } catch (IllegalAccessException | IllegalArgumentException unused) {
        }
    }

    /* renamed from: e */
    private void m9262e() {
        try {
            this.address = (InetAddress) f16959a.get(this.f16987E);
            this.fd = (FileDescriptor) f16960b.get(this.f16987E);
            this.localport = f16961c.getInt(this.f16987E);
            this.port = f16962d.getInt(this.f16987E);
        } catch (IllegalAccessException e) {
            throw new C6601a(e);
        } catch (IllegalArgumentException e2) {
            throw new C6601a(e2);
        }
    }

    /* renamed from: a */
    private <T> T m9269a(int i, Object[] objArr) throws Exception {
        m9263d();
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                return (T) f16984z[i].invoke(this.f16987E, objArr);
                            } catch (IllegalArgumentException e) {
                                throw new C6601a(e);
                            }
                        } catch (Exception e2) {
                            throw new C6601a(e2);
                        }
                    } catch (ClassCastException e3) {
                        throw new C6601a(e3);
                    }
                } catch (InvocationTargetException e4) {
                    Throwable targetException = e4.getTargetException();
                    if (targetException == null) {
                        throw new C6601a(e4);
                    }
                    if (targetException instanceof Exception) {
                        throw ((Exception) targetException);
                    }
                    if (targetException instanceof Error) {
                        throw ((Error) targetException);
                    }
                    throw new C6601a(targetException);
                }
            } catch (IllegalAccessException e5) {
                throw new C6601a(e5);
            }
        } finally {
            m9262e();
        }
    }

    /* renamed from: b */
    private <T> T m9266b(int i, Object[] objArr) {
        try {
            return (T) m9269a(i, objArr);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new C6601a(e2);
        }
    }

    /* renamed from: c */
    private Object m9264c(int i, Object[] objArr) throws IOException {
        try {
            return m9269a(i, objArr);
        } catch (IOException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new C6601a(e3);
        }
    }

    @Override // java.net.SocketImpl
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = (InputStream) m9264c(10, new Object[0]);
        if (inputStream == null) {
            return null;
        }
        try {
            InterfaceC6393e interfaceC6393e = f16958F;
            interfaceC6393e.mo10122a("CustomPlainSocketImpl getInputStream time:" + System.currentTimeMillis());
            if (inputStream instanceof C6612i) {
                return inputStream;
            }
            this.f16989H.m9252a(false);
            return new C6612i(this.f16989H, inputStream);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e2 = f16958F;
            interfaceC6393e2.mo10122a("getInputStream error:" + e.getMessage());
            return inputStream;
        }
    }

    @Override // java.net.SocketImpl
    public OutputStream getOutputStream() throws IOException {
        OutputStream outputStream = (OutputStream) m9264c(12, new Object[0]);
        if (outputStream == null) {
            return null;
        }
        try {
            InterfaceC6393e interfaceC6393e = f16958F;
            interfaceC6393e.mo10122a("customplainSocketImpl getOutputStream time:" + System.currentTimeMillis());
            return outputStream instanceof C6611h ? outputStream : new C6611h(this.f16989H, outputStream);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e2 = f16958F;
            interfaceC6393e2.mo10122a("getOutputStream error:" + e.getMessage());
            return outputStream;
        }
    }

    @Override // java.net.SocketImpl
    public void create(boolean z) throws IOException {
        m9264c(7, new Object[]{Boolean.valueOf(z)});
    }

    @Override // java.net.SocketImpl
    public final void connect(String str, int i) throws IOException {
        C6309j c6309j = new C6309j();
        try {
            try {
                InterfaceC6393e interfaceC6393e = f16958F;
                interfaceC6393e.mo10122a("connect(String host, int port) host:" + str + ", port:" + i);
                long currentTimeMillis = System.currentTimeMillis();
                c6309j.m10494b(str);
                c6309j.m10495b(i);
                m9264c(6, new Object[]{str, Integer.valueOf(i)});
                this.f16985B = (int) (System.currentTimeMillis() - currentTimeMillis);
                c6309j.m10492c(this.f16985B);
            } catch (IOException e) {
                c6309j.m10498a(-1);
                c6309j.mo9219d(e.getMessage());
                throw e;
            }
        } finally {
            if (c6309j.m10493c() != null && !C6642k.m8905d(c6309j.m10493c())) {
                Harvest.addSocketDatasInfo(c6309j);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [byte[], java.io.Serializable] */
    @Override // java.net.SocketImpl
    public final void connect(InetAddress inetAddress, int i) throws IOException {
        C6309j c6309j = new C6309j();
        try {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                c6309j.m10497a(C6640i.m8958a(inetAddress));
                c6309j.m10494b(C6640i.m8954b(inetAddress.getAddress()));
                c6309j.m10495b(i);
                m9264c(4, new Object[]{inetAddress, Integer.valueOf(i)});
                this.f16985B = (int) (System.currentTimeMillis() - currentTimeMillis);
                c6309j.m10492c(this.f16985B);
            } catch (IOException e) {
                c6309j.m10498a(-1);
                c6309j.mo9219d(e.getMessage());
                throw e;
            }
        } finally {
            if (c6309j.m10493c() != null && !C6642k.m8905d(c6309j.m10493c())) {
                Harvest.addSocketDatasInfo(c6309j);
            }
        }
    }

    @Override // java.net.SocketImpl
    public final void connect(SocketAddress socketAddress, int i) throws IOException {
        String str = "";
        String str2 = "";
        try {
            if (socketAddress instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
                str = C6640i.m8958a(inetSocketAddress);
                str2 = C6640i.m8954b(inetSocketAddress.getAddress());
                this.f16986C = str2;
            }
            long currentTimeMillis = System.currentTimeMillis();
            m9264c(5, new Object[]{socketAddress, Integer.valueOf(i)});
            this.f16985B = (int) (System.currentTimeMillis() - currentTimeMillis);
            this.f16989H.m9256a(this.f16985B);
            C6642k.m8916a(str2, str, this.f16985B, this.port);
            this.f16989H.m9253a(str);
        } catch (IOException e) {
            C6642k.m8915a(str2, this.f16988G, str, e);
        }
    }

    @Override // java.net.SocketImpl
    public void bind(InetAddress inetAddress, int i) throws IOException {
        m9264c(2, new Object[]{inetAddress, Integer.valueOf(i)});
    }

    @Override // java.net.SocketImpl
    public void listen(int i) throws IOException {
        m9264c(14, new Object[]{Integer.valueOf(i)});
    }

    @Override // java.net.SocketImpl
    public void accept(SocketImpl socketImpl) throws IOException {
        m9264c(0, new Object[]{socketImpl});
    }

    @Override // java.net.SocketImpl
    public int available() throws IOException {
        Integer num = (Integer) m9264c(1, new Object[0]);
        if (num == null) {
            throw new C6601a("Received a null Integer");
        }
        return num.intValue();
    }

    @Override // java.net.SocketImpl
    public void close() throws IOException {
        m9264c(3, new Object[0]);
    }

    @Override // java.net.SocketImpl
    public void shutdownInput() throws IOException {
        m9264c(17, new Object[0]);
    }

    @Override // java.net.SocketImpl
    public void shutdownOutput() throws IOException {
        m9264c(18, new Object[0]);
    }

    @Override // java.net.SocketImpl
    public FileDescriptor getFileDescriptor() {
        return (FileDescriptor) m9266b(8, new Object[0]);
    }

    @Override // java.net.SocketImpl
    public InetAddress getInetAddress() {
        return (InetAddress) m9266b(9, new Object[0]);
    }

    @Override // java.net.SocketImpl
    public int getPort() {
        return ((Integer) m9266b(13, new Object[0])).intValue();
    }

    @Override // java.net.SocketImpl
    public boolean supportsUrgentData() {
        return ((Boolean) m9266b(19, new Object[0])).booleanValue();
    }

    @Override // java.net.SocketImpl
    public void sendUrgentData(int i) throws IOException {
        m9264c(15, new Object[]{Integer.valueOf(i)});
    }

    @Override // java.net.SocketImpl
    public int getLocalPort() {
        return ((Integer) m9266b(11, new Object[0])).intValue();
    }

    @Override // java.net.SocketImpl
    public String toString() {
        return this.f16987E.toString();
    }

    @Override // java.net.SocketImpl
    public void setPerformancePreferences(int i, int i2, int i3) {
        m9266b(16, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    @Override // java.net.SocketOptions
    public void setOption(int i, Object obj) throws SocketException {
        this.f16987E.setOption(i, obj);
    }

    @Override // java.net.SocketOptions
    public Object getOption(int i) throws SocketException {
        return this.f16987E.getOption(i);
    }

    /* renamed from: c */
    public SocketImpl m9265c() {
        return this.f16987E;
    }
}
