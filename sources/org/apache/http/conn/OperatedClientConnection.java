package org.apache.http.conn;

import java.io.IOException;
import java.net.Socket;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpInetConnection;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public interface OperatedClientConnection extends HttpClientConnection, HttpInetConnection {
    Socket getSocket();

    HttpHost getTargetHost();

    boolean isSecure();

    void openCompleted(boolean z, HttpParams httpParams) throws IOException;

    void opening(Socket socket, HttpHost httpHost) throws IOException;

    void update(Socket socket, HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException;
}
