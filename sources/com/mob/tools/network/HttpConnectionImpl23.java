package com.mob.tools.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HttpConnectionImpl23 implements HttpConnection {

    /* renamed from: a */
    private HttpURLConnection f15001a;

    public HttpConnectionImpl23(HttpURLConnection httpURLConnection) {
        this.f15001a = httpURLConnection;
    }

    @Override // com.mob.tools.network.HttpConnection
    public int getResponseCode() throws IOException {
        return this.f15001a.getResponseCode();
    }

    @Override // com.mob.tools.network.HttpConnection
    public InputStream getInputStream() throws IOException {
        return this.f15001a.getInputStream();
    }

    @Override // com.mob.tools.network.HttpConnection
    public InputStream getErrorStream() throws IOException {
        return this.f15001a.getErrorStream();
    }

    @Override // com.mob.tools.network.HttpConnection
    public Map<String, List<String>> getHeaderFields() throws IOException {
        return this.f15001a.getHeaderFields();
    }
}
