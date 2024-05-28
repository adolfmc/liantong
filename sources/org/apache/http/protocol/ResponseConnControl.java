package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ResponseConnControl implements HttpResponseInterceptor {
    @Override // org.apache.http.HttpResponseInterceptor
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        Header firstHeader;
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 400 || statusCode == 408 || statusCode == 411 || statusCode == 413 || statusCode == 414 || statusCode == 503 || statusCode == 501) {
            httpResponse.setHeader("Connection", "Close");
            return;
        }
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            ProtocolVersion protocolVersion = httpResponse.getStatusLine().getProtocolVersion();
            if (entity.getContentLength() < 0 && (!entity.isChunked() || protocolVersion.lessEquals(HttpVersion.HTTP_1_0))) {
                httpResponse.setHeader("Connection", "Close");
                return;
            }
        }
        HttpRequest httpRequest = (HttpRequest) httpContext.getAttribute("http.request");
        if (httpRequest != null && (firstHeader = httpRequest.getFirstHeader("Connection")) != null) {
            httpResponse.setHeader("Connection", firstHeader.getValue());
        }
    }
}
