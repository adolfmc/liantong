package com.networkbench.agent.impl.socket;

import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6611h extends OutputStream {

    /* renamed from: b */
    private static final InterfaceC6393e f17006b = C6394f.m10150a();

    /* renamed from: a */
    private OutputStream f17007a;

    /* renamed from: c */
    private C6613j f17008c;

    public C6611h(C6613j c6613j, OutputStream outputStream) {
        if (outputStream == null || c6613j == null) {
            throw new NullPointerException("outputStream delegate or monitorStreamReadWrite was null");
        }
        InterfaceC6393e interfaceC6393e = f17006b;
        interfaceC6393e.mo10122a("HttpRequestParsingOutputStream init time:" + System.currentTimeMillis());
        this.f17007a = outputStream;
        this.f17008c = c6613j;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.f17007a.flush();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f17007a.close();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.f17007a.write(i);
        this.f17008c.m9255a(System.currentTimeMillis());
        InterfaceC6393e interfaceC6393e = f17006b;
        interfaceC6393e.mo10122a("HttpRequestParsingOutputStream write(int oneByte) time:" + System.currentTimeMillis());
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.f17007a.write(bArr);
        this.f17008c.m9255a(System.currentTimeMillis());
        InterfaceC6393e interfaceC6393e = f17006b;
        interfaceC6393e.mo10122a("HttpRequestParsingOutputStream write(byte[] buffer) time:" + System.currentTimeMillis());
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.f17007a.write(bArr, i, i2);
        this.f17008c.m9255a(System.currentTimeMillis());
        InterfaceC6393e interfaceC6393e = f17006b;
        interfaceC6393e.mo10122a("HttpRequestParsingOutputStream write(byte[] buffer, int offset, int count) time:" + System.currentTimeMillis());
    }
}
