package org.apache.http.impl.p452io;

import org.apache.http.p453io.HttpTransportMetrics;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.HttpTransportMetricsImpl */
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpTransportMetricsImpl implements HttpTransportMetrics {
    private long bytesTransferred = 0;

    @Override // org.apache.http.p453io.HttpTransportMetrics
    public long getBytesTransferred() {
        return this.bytesTransferred;
    }

    public void setBytesTransferred(long j) {
        this.bytesTransferred = j;
    }

    public void incrementBytesTransferred(long j) {
        this.bytesTransferred += j;
    }

    @Override // org.apache.http.p453io.HttpTransportMetrics
    public void reset() {
        this.bytesTransferred = 0L;
    }
}
