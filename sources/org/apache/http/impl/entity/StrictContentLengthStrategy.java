package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.entity.ContentLengthStrategy;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class StrictContentLengthStrategy implements ContentLengthStrategy {
    @Override // org.apache.http.entity.ContentLengthStrategy
    public long determineLength(HttpMessage httpMessage) throws HttpException {
        if (httpMessage == null) {
            throw new IllegalArgumentException("HTTP message may not be null");
        }
        Header firstHeader = httpMessage.getFirstHeader("Transfer-Encoding");
        Header firstHeader2 = httpMessage.getFirstHeader("Content-Length");
        if (firstHeader != null) {
            String value = firstHeader.getValue();
            if ("chunked".equalsIgnoreCase(value)) {
                if (httpMessage.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0)) {
                    throw new ProtocolException("Chunked transfer encoding not allowed for " + httpMessage.getProtocolVersion());
                }
                return -2L;
            } else if ("identity".equalsIgnoreCase(value)) {
                return -1L;
            } else {
                throw new ProtocolException("Unsupported transfer encoding: " + value);
            }
        } else if (firstHeader2 == null) {
            return -1L;
        } else {
            String value2 = firstHeader2.getValue();
            try {
                return Long.parseLong(value2);
            } catch (NumberFormatException e) {
                throw new ProtocolException("Invalid content length: " + value2);
            }
        }
    }
}
