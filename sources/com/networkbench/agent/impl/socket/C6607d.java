package com.networkbench.agent.impl.socket;

import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImplWrapper;
import org.apache.harmony.xnet.provider.jsse.SSLParametersImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6607d extends OpenSSLSocketImplWrapper implements InterfaceC6616m {

    /* renamed from: a */
    private int f16995a;

    /* renamed from: b */
    private String f16996b;

    /* renamed from: c */
    private C6613j f16997c;

    /* JADX INFO: Access modifiers changed from: protected */
    public C6607d(Socket socket, String str, int i, boolean z, SSLParametersImpl sSLParametersImpl) throws IOException {
        super(socket, str, i, z, sSLParametersImpl);
        this.f16997c = new C6613j();
        this.f16995a = 0;
        this.f16996b = str;
    }

    @Override // com.networkbench.agent.impl.socket.InterfaceC6616m
    /* renamed from: a */
    public void mo9233a(NBSTransactionState nBSTransactionState) {
        this.f16997c.m9254a(nBSTransactionState);
    }

    public final void startHandshake() throws IOException {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            super.startHandshake();
            this.f16995a += (int) (System.currentTimeMillis() - currentTimeMillis);
            C6621r.m9201a(this.f16996b, this.f16995a);
            this.f16997c.m9253a(this.f16996b);
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
            interfaceC6393e.mo10122a("CustomOpenSSLSocketImplOldWrapper getInputStream time:" + System.currentTimeMillis());
            if (inputStream instanceof C6612i) {
                return inputStream;
            }
            this.f16997c.m9252a(false);
            return new C6612i(this.f16997c, inputStream);
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
            interfaceC6393e.mo10122a("CustomOpenSSLSocketImplOldWrapper getOutputStream time:" + System.currentTimeMillis());
            return outputStream instanceof C6611h ? outputStream : new C6611h(this.f16997c, outputStream);
        } catch (IOException e) {
            InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
            interfaceC6393e2.mo10122a("getOutputStream error:" + e.getMessage());
            return super.getOutputStream();
        }
    }
}
