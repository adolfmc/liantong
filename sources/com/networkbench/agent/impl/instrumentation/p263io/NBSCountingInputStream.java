package com.networkbench.agent.impl.instrumentation.p263io;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.instrumentation.io.NBSCountingInputStream */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSCountingInputStream extends InputStream implements NBSStreamCompleteListenerSource {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private final ByteBuffer buffer;
    private long count;
    private boolean enableBuffering;
    private final InputStream impl;
    private final C6481a listenerManager;

    public NBSCountingInputStream(InputStream inputStream) {
        this.count = 0L;
        this.listenerManager = new C6481a();
        this.enableBuffering = false;
        this.impl = inputStream;
        log.mo10122a("new NBSCountingInputStream");
        if (this.enableBuffering) {
            log.mo10122a("buffer enabled");
            this.buffer = ByteBuffer.allocate(NBSAgent.getResponseBodyLimit());
            fillBuffer();
            return;
        }
        this.buffer = null;
    }

    public NBSCountingInputStream(InputStream inputStream, boolean z) {
        this.count = 0L;
        this.listenerManager = new C6481a();
        this.enableBuffering = false;
        this.impl = inputStream;
        this.enableBuffering = z;
        if (z) {
            this.buffer = ByteBuffer.allocate(NBSAgent.getResponseBodyLimit());
            fillBuffer();
            return;
        }
        this.buffer = null;
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

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.enableBuffering) {
            synchronized (this.buffer) {
                if (m9866a(1L)) {
                    int m9867a = m9867a();
                    if (m9867a >= 0) {
                        this.count++;
                    }
                    return m9867a;
                }
            }
        }
        try {
            int read = this.impl.read();
            if (read >= 0) {
                this.count++;
            } else {
                m9861c();
            }
            return read;
        } catch (IOException e) {
            m9865a(e);
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        if (this.enableBuffering) {
            synchronized (this.buffer) {
                if (m9866a(length)) {
                    int m9864a = m9864a(bArr);
                    if (m9864a >= 0) {
                        this.count += m9864a;
                        return m9864a;
                    }
                    throw new IOException("readBufferBytes failed");
                }
                int remaining = this.buffer.remaining();
                if (remaining > 0) {
                    i = m9863a(bArr, 0, remaining);
                    if (i < 0) {
                        throw new IOException("partial read from buffer failed");
                    }
                    length -= i;
                    this.count += i;
                }
            }
        }
        try {
            int read = this.impl.read(bArr, i, length);
            if (read >= 0) {
                this.count += read;
                return read + i;
            }
            log.mo10117c("end flag:" + read);
            if (i <= 0) {
                m9861c();
                return read;
            }
            return i;
        } catch (IOException e) {
            log.mo10116d("NOTIFY STREAM ERROR: " + e.toString());
            e.printStackTrace();
            m9865a(e);
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (this.enableBuffering) {
            synchronized (this.buffer) {
                if (i2 != 0) {
                    if (m9866a(i2)) {
                        int m9863a = m9863a(bArr, i, i2);
                        if (m9863a >= 0) {
                            this.count += m9863a;
                            return m9863a;
                        }
                        throw new IOException("readBufferBytes failed");
                    }
                }
                int remaining = this.buffer.remaining();
                if (remaining > 0) {
                    i3 = m9863a(bArr, i, remaining);
                    if (i3 < 0) {
                        throw new IOException("partial read from buffer failed");
                    }
                    i2 -= i3;
                    this.count += i3;
                } else {
                    i3 = 0;
                }
            }
        } else {
            i3 = 0;
        }
        try {
            if (this.impl != null) {
                int read = this.impl.read(bArr, i + i3, i2);
                if (read >= 0) {
                    this.count += read;
                    return read + i3;
                } else if (i3 <= 0) {
                    m9861c();
                    return read;
                } else {
                    return i3;
                }
            }
            return 0;
        } catch (IOException e) {
            m9865a(e);
            throw e;
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (this.enableBuffering) {
            synchronized (this.buffer) {
                if (m9866a(j)) {
                    this.buffer.position((int) j);
                    this.count += j;
                    return j;
                }
                j -= this.buffer.remaining();
                if (j > 0) {
                    this.buffer.position(this.buffer.remaining());
                } else {
                    throw new IOException("partial read from buffer (skip) failed");
                }
            }
        }
        try {
            long skip = this.impl.skip(j);
            this.count += skip;
            return skip;
        } catch (IOException e) {
            m9865a(e);
            throw e;
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        try {
            return (this.enableBuffering ? this.buffer.remaining() : 0) + this.impl.available();
        } catch (IOException e) {
            m9865a(e);
            throw e;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (this.impl != null) {
                this.impl.close();
            }
            m9861c();
        } catch (IOException e) {
            m9865a(e);
            throw e;
        }
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        if (markSupported()) {
            this.impl.mark(i);
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.impl.markSupported();
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        if (markSupported()) {
            try {
                this.impl.reset();
            } catch (IOException e) {
                m9865a(e);
                throw e;
            }
        }
    }

    /* renamed from: a */
    private int m9867a() {
        if (m9862b()) {
            return -1;
        }
        return this.buffer.get() & 255;
    }

    /* renamed from: a */
    private int m9864a(byte[] bArr) {
        return m9863a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    private int m9863a(byte[] bArr, int i, int i2) {
        if (m9862b()) {
            return -1;
        }
        int remaining = this.buffer.remaining();
        this.buffer.get(bArr, i, i2);
        return remaining - this.buffer.remaining();
    }

    /* renamed from: a */
    private boolean m9866a(long j) {
        return ((long) this.buffer.remaining()) >= j;
    }

    /* renamed from: b */
    private boolean m9862b() {
        return !this.buffer.hasRemaining();
    }

    public void fillBuffer() {
        int i;
        ByteBuffer byteBuffer = this.buffer;
        if (byteBuffer == null || !byteBuffer.hasArray()) {
            return;
        }
        synchronized (this.buffer) {
            try {
                i = this.impl.read(this.buffer.array(), 0, this.buffer.capacity());
            } catch (IOException e) {
                log.mo10116d(e.toString());
                i = 0;
            }
            if (i <= 0) {
                this.buffer.limit(0);
            } else if (i < this.buffer.capacity()) {
                this.buffer.limit(i);
            }
        }
    }

    /* renamed from: c */
    private void m9861c() {
        if (this.listenerManager.m9858a()) {
            return;
        }
        this.listenerManager.m9857a(new NBSStreamCompleteEvent(this, this.count));
    }

    /* renamed from: a */
    private void m9865a(Exception exc) {
        if (this.listenerManager.m9858a()) {
            return;
        }
        this.listenerManager.m9854b(new NBSStreamCompleteEvent(this, this.count, exc));
    }

    public void setBufferingEnabled(boolean z) {
        this.enableBuffering = z;
    }

    public String getBufferAsString() {
        String str;
        ByteBuffer byteBuffer = this.buffer;
        if (byteBuffer != null) {
            synchronized (byteBuffer) {
                byte[] bArr = new byte[this.buffer.limit()];
                for (int i = 0; i < this.buffer.limit(); i++) {
                    bArr[i] = this.buffer.get(i);
                }
                str = null;
                try {
                    str = new String(bArr, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return str;
        }
        return "";
    }
}
