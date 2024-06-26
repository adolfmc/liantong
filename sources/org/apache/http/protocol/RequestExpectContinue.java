package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.HttpProtocolParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class RequestExpectContinue implements HttpRequestInterceptor {
    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        HttpEntity entity;
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if ((httpRequest instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity()) != null && entity.getContentLength() != 0) {
            ProtocolVersion protocolVersion = httpRequest.getRequestLine().getProtocolVersion();
            if (HttpProtocolParams.useExpectContinue(httpRequest.getParams()) && !protocolVersion.lessEquals(HttpVersion.HTTP_1_0)) {
                httpRequest.addHeader("Expect", "100-continue");
            }
        }
    }
}
