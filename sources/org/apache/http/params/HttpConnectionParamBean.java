package org.apache.http.params;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpConnectionParamBean extends HttpAbstractParamBean {
    public HttpConnectionParamBean(HttpParams httpParams) {
        super(httpParams);
    }

    public void setSoTimeout(int i) {
        HttpConnectionParams.setSoTimeout(this.params, i);
    }

    public void setTcpNoDelay(boolean z) {
        HttpConnectionParams.setTcpNoDelay(this.params, z);
    }

    public void setSocketBufferSize(int i) {
        HttpConnectionParams.setSocketBufferSize(this.params, i);
    }

    public void setLinger(int i) {
        HttpConnectionParams.setLinger(this.params, i);
    }

    public void setConnectionTimeout(int i) {
        HttpConnectionParams.setConnectionTimeout(this.params, i);
    }

    public void setStaleCheckingEnabled(boolean z) {
        HttpConnectionParams.setStaleCheckingEnabled(this.params, z);
    }
}
