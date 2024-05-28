package org.apache.http.client.methods;

import java.net.URI;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpPut extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "PUT";

    public HttpPut() {
    }

    public HttpPut(URI uri) {
        setURI(uri);
    }

    public HttpPut(String str) {
        setURI(URI.create(str));
    }

    @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
    public String getMethod() {
        return "PUT";
    }
}
