package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ResponseContent implements HttpResponseInterceptor {
    @Override // org.apache.http.HttpResponseInterceptor
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (httpResponse.containsHeader("Transfer-Encoding")) {
            throw new ProtocolException("Transfer-encoding header already present");
        }
        if (httpResponse.containsHeader("Content-Length")) {
            throw new ProtocolException("Content-Length header already present");
        }
        ProtocolVersion protocolVersion = httpResponse.getStatusLine().getProtocolVersion();
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            long contentLength = entity.getContentLength();
            if (entity.isChunked() && !protocolVersion.lessEquals(HttpVersion.HTTP_1_0)) {
                httpResponse.addHeader("Transfer-Encoding", "chunked");
            } else if (contentLength >= 0) {
                httpResponse.addHeader("Content-Length", Long.toString(entity.getContentLength()));
            }
            if (entity.getContentType() != null && !httpResponse.containsHeader("Content-Type")) {
                httpResponse.addHeader(entity.getContentType());
            }
            if (entity.getContentEncoding() != null && !httpResponse.containsHeader("Content-Encoding")) {
                httpResponse.addHeader(entity.getContentEncoding());
                return;
            }
            return;
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 204 && statusCode != 304 && statusCode != 205) {
            httpResponse.addHeader("Content-Length", "0");
        }
    }
}
