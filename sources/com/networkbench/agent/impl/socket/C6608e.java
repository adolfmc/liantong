package com.networkbench.agent.impl.socket;

import com.android.org.conscrypt.OpenSSLSocketImplWrapper;
import com.android.org.conscrypt.SSLParametersImpl;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6608e extends OpenSSLSocketImplWrapper implements InterfaceC6616m {

    /* renamed from: a */
    private int f16998a;

    /* renamed from: b */
    private String f16999b;

    /* renamed from: c */
    private C6613j f17000c;

    /* JADX INFO: Access modifiers changed from: protected */
    public C6608e(Socket socket, String str, int i, boolean z, SSLParametersImpl sSLParametersImpl) throws IOException {
        super(socket, str, i, z, sSLParametersImpl);
        this.f17000c = new C6613j();
        this.f16998a = 0;
        this.f16999b = str;
    }

    @Override // com.networkbench.agent.impl.socket.InterfaceC6616m
    /* renamed from: a */
    public void mo9233a(NBSTransactionState nBSTransactionState) {
        this.f17000c.m9254a(nBSTransactionState);
    }

    public final void startHandshake() throws IOException {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            super.startHandshake();
            this.f16998a += (int) (System.currentTimeMillis() - currentTimeMillis);
            C6621r.m9201a(this.f16999b, this.f16998a);
            this.f17000c.m9253a(this.f16999b);
        } catch (IOException e) {
            throw e;
        }
    }

    public final void close() throws IOException {
        super.close();
    }

    public final InputStream getInputStream() throws IOException {
        try {
            InputStream inputStream = super.getInputStream();
            if (inputStream == null) {
                return null;
            }
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10122a("CustomOpenSSLSocketImplWrapper getInputStream time:" + System.currentTimeMillis());
            if (inputStream instanceof C6612i) {
                return inputStream;
            }
            this.f17000c.m9252a(false);
            return new C6612i(this.f17000c, inputStream);
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
            interfaceC6393e.mo10122a("CustomOpenSSLSocketImplWrapper getOutputStream time:" + System.currentTimeMillis());
            return outputStream instanceof C6611h ? outputStream : new C6611h(this.f17000c, outputStream);
        } catch (IOException e) {
            InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
            interfaceC6393e2.mo10122a("getOutputStream error:" + e.getMessage());
            return super.getOutputStream();
        }
    }
}
