package com.networkbench.agent.impl.plugin.p275f.p276a;

import com.networkbench.agent.impl.p254f.C6396h;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.f.a.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6584e implements InterfaceC6583d {

    /* renamed from: a */
    public static final int f16864a = -3;

    /* renamed from: b */
    public static final int f16865b = -2;

    /* renamed from: c */
    public static final int f16866c = -4;

    /* renamed from: d */
    public static final int f16867d = -1;

    /* renamed from: e */
    public String f16868e = "";

    /* renamed from: f */
    private final String f16869f;

    /* renamed from: g */
    private final int f16870g;

    /* renamed from: h */
    private final int f16871h;

    /* renamed from: i */
    private final InterfaceC6585a f16872i;

    /* renamed from: j */
    private boolean f16873j;

    /* renamed from: k */
    private InterfaceC6582c f16874k;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.f.a.e$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC6585a {
        /* renamed from: a */
        void mo9318a(C6586b c6586b);
    }

    private C6584e(String str, int i, int i2, InterfaceC6582c interfaceC6582c, InterfaceC6585a interfaceC6585a) {
        this.f16869f = str;
        this.f16870g = i;
        this.f16871h = i2;
        this.f16872i = interfaceC6585a;
        this.f16874k = interfaceC6582c;
    }

    /* renamed from: a */
    public static InterfaceC6583d m9322a(String str, InterfaceC6582c interfaceC6582c, InterfaceC6585a interfaceC6585a) {
        return m9323a(str, 80, 3, interfaceC6582c, interfaceC6585a);
    }

    /* renamed from: a */
    public static InterfaceC6583d m9323a(String str, int i, int i2, InterfaceC6582c interfaceC6582c, InterfaceC6585a interfaceC6585a) {
        C6584e c6584e = new C6584e(str, i, i2, interfaceC6582c, interfaceC6585a);
        c6584e.m9319b();
        return c6584e;
    }

    /* renamed from: b */
    private void m9319b() {
        try {
            String hostAddress = InetAddress.getAllByName(this.f16869f)[0].getHostAddress();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(hostAddress, this.f16870g);
            InterfaceC6582c interfaceC6582c = this.f16874k;
            interfaceC6582c.mo9317a("connect to " + hostAddress + ":" + this.f16870g);
            int[] iArr = new int[this.f16871h];
            int i = 0;
            int i2 = 0;
            char c = (char) 0;
            int i3 = -1;
            for (int i4 = 0; i4 < this.f16871h && !this.f16873j; i4++) {
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    m9321a(inetSocketAddress, 5000);
                } catch (IOException e) {
                    this.f16868e = e.getMessage();
                    this.f16874k.mo9317a(e.getMessage());
                    c = e instanceof SocketTimeoutException ? (char) 65533 : (char) 65534;
                    i++;
                }
                int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
                if (c >= 0) {
                    i2 += currentTimeMillis2;
                }
                iArr[i4] = currentTimeMillis2;
                try {
                    if (!this.f16873j && 100 > currentTimeMillis2 && currentTimeMillis2 > 0) {
                        Thread.sleep(100 - currentTimeMillis2);
                    }
                } catch (Exception e2) {
                    C6396h.m10137e(e2.toString());
                    if (e2 instanceof InterruptedException) {
                        this.f16872i.mo9318a(null);
                    }
                }
                i3 = i4;
            }
            if (i3 == -1) {
                this.f16872i.mo9318a(new C6586b(this.f16868e, -1, hostAddress, 0, 0, 0, 0, 0, 0));
            } else {
                this.f16872i.mo9318a(m9320a(iArr, i3, hostAddress, i, i2));
            }
        } catch (UnknownHostException e3) {
            this.f16868e = e3.getMessage();
            InterfaceC6582c interfaceC6582c2 = this.f16874k;
            interfaceC6582c2.mo9317a("Unknown host: " + this.f16869f);
            InterfaceC6585a interfaceC6585a = this.f16872i;
            String str = this.f16868e;
            int i5 = this.f16871h;
            interfaceC6585a.mo9318a(new C6586b(str, -4, "", 0, 0, 0, 0, i5, i5));
        }
    }

    /* renamed from: a */
    private C6586b m9320a(int[] iArr, int i, String str, int i2, int i3) {
        int i4 = 0;
        int i5 = 1000000;
        for (int i6 = 0; i6 <= i; i6++) {
            int i7 = iArr[i6];
            if (i7 > i4) {
                i4 = i7;
            }
            if (i7 < i5) {
                i5 = i7;
            }
        }
        return new C6586b(this.f16868e, 0, str, i4, i5, i3, 0, i + 1, i2);
    }

    /* renamed from: a */
    private void m9321a(InetSocketAddress inetSocketAddress, int i) throws IOException {
        Socket socket = null;
        try {
            try {
                Socket socket2 = new Socket();
                try {
                    socket2.connect(inetSocketAddress, i);
                    try {
                        socket2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    e = e2;
                    socket = socket2;
                    e.printStackTrace();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    socket = socket2;
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e = e4;
        }
    }

    @Override // com.networkbench.agent.impl.plugin.p275f.p276a.InterfaceC6583d
    /* renamed from: a */
    public void mo9324a() {
        this.f16873j = true;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.f.a.e$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6586b {

        /* renamed from: a */
        public final int f16875a;

        /* renamed from: b */
        public final String f16876b;

        /* renamed from: c */
        public final int f16877c;

        /* renamed from: d */
        public final int f16878d;

        /* renamed from: e */
        public final int f16879e;

        /* renamed from: f */
        public final int f16880f;

        /* renamed from: g */
        public final int f16881g;

        /* renamed from: h */
        public final int f16882h;

        /* renamed from: i */
        public final String f16883i;

        public C6586b(String str, int i, String str2, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.f16875a = i;
            this.f16876b = str2;
            this.f16877c = i2;
            this.f16878d = i3;
            this.f16879e = i4;
            this.f16880f = i5;
            this.f16881g = i6;
            this.f16882h = i7;
            this.f16883i = str;
        }

        public String toString() {
            return String.format("code:%d, ip:%s, maxtime:%d, mintime:%d, avgtime:%d, stddevtime:%d, count:%d, dropped:%d", Integer.valueOf(this.f16875a), this.f16876b, Integer.valueOf(this.f16877c), Integer.valueOf(this.f16878d), Integer.valueOf(this.f16879e), Integer.valueOf(this.f16880f), Integer.valueOf(this.f16881g), Integer.valueOf(this.f16882h));
        }
    }
}
