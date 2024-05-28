package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.Collection;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class RequestDefaultHeaders implements HttpRequestInterceptor {
    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        Collection<Header> collection = (Collection) httpRequest.getParams().getParameter("http.default-headers");
        if (collection != null) {
            for (Header header : collection) {
                httpRequest.addHeader(header);
            }
        }
    }
}
