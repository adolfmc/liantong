package org.apache.http.impl;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.TokenIterator;
import org.apache.http.message.BasicTokenIterator;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class DefaultConnectionReuseStrategy implements ConnectionReuseStrategy {
    @Override // org.apache.http.ConnectionReuseStrategy
    public boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext) {
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP response may not be null.");
        }
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null.");
        }
        HttpConnection httpConnection = (HttpConnection) httpContext.getAttribute("http.connection");
        if (httpConnection != null && !httpConnection.isOpen()) {
            return false;
        }
        HttpEntity entity = httpResponse.getEntity();
        ProtocolVersion protocolVersion = httpResponse.getStatusLine().getProtocolVersion();
        if (entity != null && entity.getContentLength() < 0 && (!entity.isChunked() || protocolVersion.lessEquals(HttpVersion.HTTP_1_0))) {
            return false;
        }
        HeaderIterator headerIterator = httpResponse.headerIterator("Connection");
        if (!headerIterator.hasNext()) {
            headerIterator = httpResponse.headerIterator("Proxy-Connection");
        }
        if (headerIterator.hasNext()) {
            try {
                TokenIterator createTokenIterator = createTokenIterator(headerIterator);
                boolean z = false;
                while (createTokenIterator.hasNext()) {
                    String nextToken = createTokenIterator.nextToken();
                    if ("Close".equalsIgnoreCase(nextToken)) {
                        return false;
                    }
                    if ("Keep-Alive".equalsIgnoreCase(nextToken)) {
                        z = true;
                    }
                }
                if (z) {
                    return true;
                }
            } catch (ParseException e) {
                return false;
            }
        }
        return !protocolVersion.lessEquals(HttpVersion.HTTP_1_0);
    }

    protected TokenIterator createTokenIterator(HeaderIterator headerIterator) {
        return new BasicTokenIterator(headerIterator);
    }
}
