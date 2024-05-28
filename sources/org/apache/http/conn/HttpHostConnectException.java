package org.apache.http.conn;

import java.net.ConnectException;
import org.apache.http.HttpHost;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpHostConnectException extends ConnectException {
    private static final long serialVersionUID = -3194482710275220224L;
    private final HttpHost host;

    public HttpHostConnectException(HttpHost httpHost, ConnectException connectException) {
        super("Connection to " + httpHost + " refused");
        this.host = httpHost;
        initCause(connectException);
    }

    public HttpHost getHost() {
        return this.host;
    }
}
