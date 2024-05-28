package com.networkbench.agent.impl.socket;

import com.android.org.conscrypt.OpenSSLSocketImpl;
import com.android.org.conscrypt.SSLParametersImpl;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6640i;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6603b extends OpenSSLSocketImpl implements InterfaceC6616m {

    /* renamed from: a */
    private int f16953a;

    /* renamed from: b */
    private String f16954b;

    /* renamed from: c */
    private C6613j f16955c;

    /* JADX INFO: Access modifiers changed from: protected */
    public C6603b(SSLParametersImpl sSLParametersImpl) {
        super(sSLParametersImpl);
        this.f16955c = new C6613j();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C6603b(String str, int i, SSLParametersImpl sSLParametersImpl) {
        super(str, i, sSLParametersImpl);
        this.f16955c = new C6613j();
        this.f16954b = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C6603b(InetAddress inetAddress, int i, SSLParametersImpl sSLParametersImpl) {
        super(inetAddress, i, sSLParametersImpl);
        this.f16955c = new C6613j();
        this.f16954b = C6640i.m8958a(inetAddress);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C6603b(String str, int i, InetAddress inetAddress, int i2, SSLParametersImpl sSLParametersImpl) {
        super(str, i, inetAddress, i2, sSLParametersImpl);
        this.f16955c = new C6613j();
        this.f16954b = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C6603b(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2, SSLParametersImpl sSLParametersImpl) {
        super(inetAddress, i, inetAddress2, i2, sSLParametersImpl);
        this.f16955c = new C6613j();
        this.f16954b = C6640i.m8958a(inetAddress);
    }

    @Override // com.networkbench.agent.impl.socket.InterfaceC6616m
    /* renamed from: a */
    public void mo9233a(NBSTransactionState nBSTransactionState) {
        this.f16955c.m9254a(nBSTransactionState);
    }

    public final void startHandshake() throws IOException {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            super.startHandshake();
            this.f16953a = (int) (System.currentTimeMillis() - currentTimeMillis);
            C6621r.m9201a(this.f16954b, this.f16953a);
            this.f16955c.m9253a(this.f16954b);
        } catch (IOException e) {
            throw e;
        }
    }

    public final InputStream getInputStream() throws IOException {
        try {
            InputStream inputStream = super.getInputStream();
            if (inputStream == null) {
                return null;
            }
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10122a("CustomOpenSSLSocketImpl getInputStream time:" + System.currentTimeMillis());
            if (inputStream instanceof C6612i) {
                return inputStream;
            }
            this.f16955c.m9252a(false);
            return new C6612i(this.f16955c, inputStream);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
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
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10122a("CustomOpenSSLSocketImpl getOutputStream time:" + System.currentTimeMillis());
            return outputStream instanceof C6611h ? outputStream : new C6611h(this.f16955c, outputStream);
        } catch (IOException e) {
            InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
            interfaceC6393e2.mo10122a("getOutputStream error:" + e.getMessage());
            return super.getOutputStream();
        }
    }
}
