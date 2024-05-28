package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.p453io.HttpTransportMetrics;
import org.apache.http.p453io.SessionOutputBuffer;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class LoggingSessionOutputBuffer implements SessionOutputBuffer {
    private final SessionOutputBuffer out;
    private final Wire wire;

    public LoggingSessionOutputBuffer(SessionOutputBuffer sessionOutputBuffer, Wire wire) {
        this.out = sessionOutputBuffer;
        this.wire = wire;
    }

    @Override // org.apache.http.p453io.SessionOutputBuffer
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        if (this.wire.enabled()) {
            this.wire.output(bArr, i, i2);
        }
    }

    @Override // org.apache.http.p453io.SessionOutputBuffer
    public void write(int i) throws IOException {
        this.out.write(i);
        if (this.wire.enabled()) {
            this.wire.output(i);
        }
    }

    @Override // org.apache.http.p453io.SessionOutputBuffer
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        if (this.wire.enabled()) {
            this.wire.output(bArr);
        }
    }

    @Override // org.apache.http.p453io.SessionOutputBuffer
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // org.apache.http.p453io.SessionOutputBuffer
    public void writeLine(CharArrayBuffer charArrayBuffer) throws IOException {
        this.out.writeLine(charArrayBuffer);
        if (this.wire.enabled()) {
            this.wire.output(new String(charArrayBuffer.buffer(), 0, charArrayBuffer.length()) + "[EOL]");
        }
    }

    @Override // org.apache.http.p453io.SessionOutputBuffer
    public void writeLine(String str) throws IOException {
        this.out.writeLine(str);
        if (this.wire.enabled()) {
            this.wire.output(str + "[EOL]");
        }
    }

    @Override // org.apache.http.p453io.SessionOutputBuffer
    public HttpTransportMetrics getMetrics() {
        return this.out.getMetrics();
    }
}
