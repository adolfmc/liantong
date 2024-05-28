package org.apache.http.impl.p452io;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.SocketInputBuffer */
/* loaded from: E:\452516_dexfile_execute.dex */
public class SocketInputBuffer extends AbstractSessionInputBuffer {
    private final Socket socket;

    public SocketInputBuffer(Socket socket, int i, HttpParams httpParams) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("Socket may not be null");
        }
        this.socket = socket;
        init(socket.getInputStream(), 8192, httpParams);
    }

    @Override // org.apache.http.p453io.SessionInputBuffer
    public boolean isDataAvailable(int i) throws IOException {
        boolean hasBufferedData = hasBufferedData();
        if (!hasBufferedData) {
            int soTimeout = this.socket.getSoTimeout();
            try {
                try {
                    this.socket.setSoTimeout(i);
                    fillBuffer();
                    hasBufferedData = hasBufferedData();
                } catch (InterruptedIOException e) {
                    if (!(e instanceof SocketTimeoutException)) {
                        throw e;
                    }
                }
            } finally {
                this.socket.setSoTimeout(soTimeout);
            }
        }
        return hasBufferedData;
    }

    public boolean isStale() throws IOException {
        if (hasBufferedData()) {
            return false;
        }
        int soTimeout = this.socket.getSoTimeout();
        try {
            this.socket.setSoTimeout(1);
            return fillBuffer() == -1;
        } catch (SocketTimeoutException e) {
            return false;
        } catch (IOException e2) {
            return true;
        } finally {
            this.socket.setSoTimeout(soTimeout);
        }
    }
}
