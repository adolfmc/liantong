package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.p453io.HttpTransportMetrics;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class LoggingSessionInputBuffer implements SessionInputBuffer {

    /* renamed from: in */
    private final SessionInputBuffer f26272in;
    private final Wire wire;

    public LoggingSessionInputBuffer(SessionInputBuffer sessionInputBuffer, Wire wire) {
        this.f26272in = sessionInputBuffer;
        this.wire = wire;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public boolean isDataAvailable(int i) throws IOException {
        return this.f26272in.isDataAvailable(i);
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f26272in.read(bArr, i, i2);
        if (this.wire.enabled() && read > 0) {
            this.wire.input(bArr, i, read);
        }
        return read;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public int read() throws IOException {
        int read = this.f26272in.read();
        if (this.wire.enabled() && read > 0) {
            this.wire.input(read);
        }
        return read;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public int read(byte[] bArr) throws IOException {
        int read = this.f26272in.read(bArr);
        if (this.wire.enabled() && read > 0) {
            this.wire.input(bArr, 0, read);
        }
        return read;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public String readLine() throws IOException {
        String readLine = this.f26272in.readLine();
        if (this.wire.enabled() && readLine != null) {
            this.wire.input(readLine + "[EOL]");
        }
        return readLine;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public int readLine(CharArrayBuffer charArrayBuffer) throws IOException {
        int readLine = this.f26272in.readLine(charArrayBuffer);
        if (this.wire.enabled() && readLine > 0) {
            this.wire.input(new String(charArrayBuffer.buffer(), charArrayBuffer.length() - readLine, readLine) + "[EOL]");
        }
        return readLine;
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public HttpTransportMetrics getMetrics() {
        return this.f26272in.getMetrics();
    }
}
