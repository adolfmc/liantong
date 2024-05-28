package com.networkbench.agent.impl.socket;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p243c.C6309j;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6640i;
import com.networkbench.agent.impl.util.C6642k;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PlainSocketImpl;
import java.net.SocketAddress;
import java.net.SocketException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6609f extends PlainSocketImpl implements InterfaceC6616m {

    /* renamed from: c */
    private static final InterfaceC6393e f17001c = C6394f.m10150a();

    /* renamed from: a */
    private int f17002a;

    /* renamed from: b */
    private boolean f17003b;

    /* renamed from: d */
    private C6613j f17004d = new C6613j();

    @Override // com.networkbench.agent.impl.socket.InterfaceC6616m
    /* renamed from: a */
    public void mo9233a(NBSTransactionState nBSTransactionState) {
        this.f17004d.m9254a(nBSTransactionState);
    }

    public final void close() throws IOException {
        super.close();
    }

    public final void connect(String str, int i) throws IOException {
        if (C6640i.m8959a(i)) {
            InterfaceC6393e interfaceC6393e = f17001c;
            interfaceC6393e.mo10122a("connect(String host, int port) port:" + i + ", is not http/https");
            super.connect(str, i);
            return;
        }
        C6309j c6309j = new C6309j();
        try {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                c6309j.m10494b(str);
                c6309j.m10495b(i);
                super.connect(str, i);
                this.f17002a = (int) (System.currentTimeMillis() - currentTimeMillis);
                c6309j.m10492c(this.f17002a);
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
    public final void connect(InetAddress inetAddress, int i) throws IOException {
        if (!Harvest.isHttp_network_enabled()) {
            super.connect(inetAddress, i);
        } else if (C6640i.m8959a(i)) {
            InterfaceC6393e interfaceC6393e = f17001c;
            interfaceC6393e.mo10122a("connect(InetAddress ipAddr, int port) port:" + i + ", is not http/https");
            super.connect(inetAddress, i);
        } else {
            C6309j c6309j = new C6309j();
            try {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    c6309j.m10497a(C6640i.m8958a(inetAddress));
                    c6309j.m10494b(C6640i.m8954b(inetAddress.getAddress()));
                    c6309j.m10495b(i);
                    super.connect(inetAddress, i);
                    this.f17002a = (int) (System.currentTimeMillis() - currentTimeMillis);
                    c6309j.m10492c(this.f17002a);
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
    }

    public final void connect(SocketAddress socketAddress, int i) throws IOException {
        if (!Harvest.isHttp_network_enabled()) {
            super.connect(socketAddress, i);
            return;
        }
        String str = "";
        String str2 = "";
        try {
            if (socketAddress instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
                str = C6640i.m8958a(inetSocketAddress);
                str2 = C6640i.m8954b(inetSocketAddress.getAddress());
            }
            long currentTimeMillis = System.currentTimeMillis();
            super.connect(socketAddress, i);
            InterfaceC6393e interfaceC6393e = f17001c;
            interfaceC6393e.mo10122a("connect end time: " + System.currentTimeMillis());
            this.f17002a = (int) (System.currentTimeMillis() - currentTimeMillis);
            this.f17004d.m9256a(this.f17002a);
            C6642k.m8916a(str2, str, this.f17002a, this.port);
            this.f17004d.m9253a(str);
        } catch (IOException e) {
            C6642k.m8915a(str2, this.f17003b, str, e);
        }
    }

    public final InputStream getInputStream() throws IOException {
        try {
            InputStream inputStream = super.getInputStream();
            if (inputStream == null) {
                return null;
            }
            InterfaceC6393e interfaceC6393e = f17001c;
            interfaceC6393e.mo10122a("CustomPlainSocketImpl getInputStream time:" + System.currentTimeMillis());
            if (inputStream instanceof C6612i) {
                return inputStream;
            }
            this.f17004d.m9252a(false);
            return new C6612i(this.f17004d, inputStream);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e2 = f17001c;
            interfaceC6393e2.mo10122a("getInputStream error:" + e.getMessage());
            return super.getInputStream();
        }
    }

    public final OutputStream getOutputStream() throws IOException {
        try {
            OutputStream outputStream = super.getOutputStream();
            if (outputStream == null) {
                return null;
            }
            InterfaceC6393e interfaceC6393e = f17001c;
            interfaceC6393e.mo10122a("customplainSocketImpl getOutputStream time:" + System.currentTimeMillis());
            return outputStream instanceof C6611h ? outputStream : new C6611h(this.f17004d, outputStream);
        } catch (IOException e) {
            InterfaceC6393e interfaceC6393e2 = f17001c;
            interfaceC6393e2.mo10122a("getOutputStream error:" + e.getMessage());
            return super.getOutputStream();
        }
    }

    public final Object getOption(int i) throws SocketException {
        return super.getOption(i);
    }

    public final void setOption(int i, Object obj) throws SocketException {
        super.setOption(i, obj);
    }
}
