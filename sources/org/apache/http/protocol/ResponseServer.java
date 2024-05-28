package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ResponseServer implements HttpResponseInterceptor {
    @Override // org.apache.http.HttpResponseInterceptor
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        String str;
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (!httpResponse.containsHeader("Server") && (str = (String) httpResponse.getParams().getParameter("http.origin-server")) != null) {
            httpResponse.addHeader("Server", str);
        }
    }
}
