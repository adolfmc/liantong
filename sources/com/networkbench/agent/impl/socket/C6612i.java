package com.networkbench.agent.impl.socket;

import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6612i extends InputStream {

    /* renamed from: b */
    private static final InterfaceC6393e f17009b = C6394f.m10150a();

    /* renamed from: a */
    private InputStream f17010a;

    /* renamed from: c */
    private C6613j f17011c;

    public C6612i(C6613j c6613j, InputStream inputStream) {
        if (inputStream == null || c6613j == null) {
            throw new NullPointerException("inputStream delegate or monitorStreamReadWrite was null");
        }
        this.f17010a = inputStream;
        this.f17011c = c6613j;
        InterfaceC6393e interfaceC6393e = f17009b;
        interfaceC6393e.mo10122a("HttpResponseParsingInputStream init time:" + System.currentTimeMillis());
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f17010a.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f17010a.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.f17010a.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f17010a.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.f17010a.read();
        this.f17011c.m9249c(System.currentTimeMillis());
        InterfaceC6393e interfaceC6393e = f17009b;
        interfaceC6393e.mo10122a("HttpResponseParsingInputStream read() time:" + System.currentTimeMillis());
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int read = this.f17010a.read(bArr);
        this.f17011c.m9249c(System.currentTimeMillis());
        InterfaceC6393e interfaceC6393e = f17009b;
        interfaceC6393e.mo10122a("HttpResponseParsingInputStream read(byte[] buffer) time:" + System.currentTimeMillis());
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f17010a.read(bArr, i, i2);
        this.f17011c.m9249c(System.currentTimeMillis());
        InterfaceC6393e interfaceC6393e = f17009b;
        interfaceC6393e.mo10122a("HttpResponseParsingInputStream read(byte[] buffer, int byteOffset, int byteCount) time:" + System.currentTimeMillis());
        return read;
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.f17010a.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return this.f17010a.skip(j);
    }
}
