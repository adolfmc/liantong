package com.networkbench.agent.impl.instrumentation.p263io;

import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.instrumentation.io.NBSCountingOutputStream */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSCountingOutputStream extends OutputStream implements NBSStreamCompleteListenerSource {
    private final OutputStream impl;
    private long count = 0;
    private final C6481a listenerManager = new C6481a();

    public NBSCountingOutputStream(OutputStream outputStream) {
        this.impl = outputStream;
    }

    @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListenerSource
    public void addStreamCompleteListener(NBSStreamCompleteListener nBSStreamCompleteListener) {
        C6481a c6481a = this.listenerManager;
        if (c6481a != null) {
            c6481a.m9856a(nBSStreamCompleteListener);
        }
    }

    @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListenerSource
    public void removeStreamCompleteListener(NBSStreamCompleteListener nBSStreamCompleteListener) {
        this.listenerManager.m9853b(nBSStreamCompleteListener);
    }

    public long getCount() {
        return this.count;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        try {
            this.impl.write(i);
            this.count++;
        } catch (IOException e) {
            m9859a(e);
            throw e;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        try {
            this.impl.write(bArr);
            this.count += bArr.length;
        } catch (IOException e) {
            m9859a(e);
            throw e;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            this.impl.write(bArr, i, i2);
            this.count += i2;
        } catch (IOException e) {
            m9859a(e);
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        try {
            this.impl.flush();
        } catch (IOException e) {
            m9859a(e);
            throw e;
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.impl.close();
            m9860a();
        } catch (IOException e) {
            m9859a(e);
            throw e;
        }
    }

    /* renamed from: a */
    private void m9860a() {
        if (this.listenerManager.m9858a()) {
            return;
        }
        this.listenerManager.m9857a(new NBSStreamCompleteEvent(this, this.count));
    }

    /* renamed from: a */
    private void m9859a(Exception exc) {
        if (this.listenerManager.m9858a()) {
            return;
        }
        this.listenerManager.m9854b(new NBSStreamCompleteEvent(this, this.count, exc));
    }
}
