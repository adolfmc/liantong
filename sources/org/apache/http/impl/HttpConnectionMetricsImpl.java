package org.apache.http.impl;

import java.util.HashMap;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.p453io.HttpTransportMetrics;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpConnectionMetricsImpl implements HttpConnectionMetrics {
    public static final String RECEIVED_BYTES_COUNT = "http.received-bytes-count";
    public static final String REQUEST_COUNT = "http.request-count";
    public static final String RESPONSE_COUNT = "http.response-count";
    public static final String SENT_BYTES_COUNT = "http.sent-bytes-count";
    private final HttpTransportMetrics inTransportMetric;
    private HashMap metricsCache;
    private final HttpTransportMetrics outTransportMetric;
    private long requestCount = 0;
    private long responseCount = 0;

    public HttpConnectionMetricsImpl(HttpTransportMetrics httpTransportMetrics, HttpTransportMetrics httpTransportMetrics2) {
        this.inTransportMetric = httpTransportMetrics;
        this.outTransportMetric = httpTransportMetrics2;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public long getReceivedBytesCount() {
        HttpTransportMetrics httpTransportMetrics = this.inTransportMetric;
        if (httpTransportMetrics != null) {
            return httpTransportMetrics.getBytesTransferred();
        }
        return -1L;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public long getSentBytesCount() {
        HttpTransportMetrics httpTransportMetrics = this.outTransportMetric;
        if (httpTransportMetrics != null) {
            return httpTransportMetrics.getBytesTransferred();
        }
        return -1L;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public long getRequestCount() {
        return this.requestCount;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public long getResponseCount() {
        return this.responseCount;
    }

    public void incrementResponseCount() {
        this.responseCount++;
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public Object getMetric(String str) {
        Object obj;
        HashMap hashMap = this.metricsCache;
        if (hashMap == null) {
            obj = null;
        } else {
            obj = hashMap.get(str);
        }
        if (obj == null) {
            if ("http.request-count".equals(str)) {
                return new Long(this.requestCount);
            }
            if ("http.response-count".equals(str)) {
                return new Long(this.responseCount);
            }
            if ("http.received-bytes-count".equals(str)) {
                HttpTransportMetrics httpTransportMetrics = this.inTransportMetric;
                if (httpTransportMetrics != null) {
                    return new Long(httpTransportMetrics.getBytesTransferred());
                }
                return null;
            } else if ("http.sent-bytes-count".equals(str)) {
                HttpTransportMetrics httpTransportMetrics2 = this.outTransportMetric;
                if (httpTransportMetrics2 != null) {
                    return new Long(httpTransportMetrics2.getBytesTransferred());
                }
                return null;
            } else {
                return obj;
            }
        }
        return obj;
    }

    public void setMetric(String str, Object obj) {
        if (this.metricsCache == null) {
            this.metricsCache = new HashMap();
        }
        this.metricsCache.put(str, obj);
    }

    @Override // org.apache.http.HttpConnectionMetrics
    public void reset() {
        HttpTransportMetrics httpTransportMetrics = this.outTransportMetric;
        if (httpTransportMetrics != null) {
            httpTransportMetrics.reset();
        }
        HttpTransportMetrics httpTransportMetrics2 = this.inTransportMetric;
        if (httpTransportMetrics2 != null) {
            httpTransportMetrics2.reset();
        }
        this.requestCount = 0L;
        this.responseCount = 0L;
        this.metricsCache = null;
    }
}
