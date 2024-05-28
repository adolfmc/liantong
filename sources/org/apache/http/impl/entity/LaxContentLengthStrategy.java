package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.entity.ContentLengthStrategy;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class LaxContentLengthStrategy implements ContentLengthStrategy {
    @Override // org.apache.http.entity.ContentLengthStrategy
    public long determineLength(HttpMessage httpMessage) throws HttpException {
        long j;
        Header header;
        if (httpMessage == null) {
            throw new IllegalArgumentException("HTTP message may not be null");
        }
        boolean isParameterTrue = httpMessage.getParams().isParameterTrue("http.protocol.strict-transfer-encoding");
        Header firstHeader = httpMessage.getFirstHeader("Transfer-Encoding");
        Header firstHeader2 = httpMessage.getFirstHeader("Content-Length");
        if (firstHeader != null) {
            try {
                HeaderElement[] elements = firstHeader.getElements();
                if (isParameterTrue) {
                    for (HeaderElement headerElement : elements) {
                        String name = headerElement.getName();
                        if (name != null && name.length() > 0 && !name.equalsIgnoreCase("chunked") && !name.equalsIgnoreCase("identity")) {
                            throw new ProtocolException("Unsupported transfer encoding: " + name);
                        }
                    }
                }
                int length = elements.length;
                if ("identity".equalsIgnoreCase(firstHeader.getValue())) {
                    return -1L;
                }
                if (length > 0 && "chunked".equalsIgnoreCase(elements[length - 1].getName())) {
                    return -2L;
                }
                if (isParameterTrue) {
                    throw new ProtocolException("Chunk-encoding must be the last one applied");
                }
                return -1L;
            } catch (ParseException e) {
                throw new ProtocolException("Invalid Transfer-Encoding header value: " + firstHeader, e);
            }
        } else if (firstHeader2 != null) {
            Header[] headers = httpMessage.getHeaders("Content-Length");
            if (!isParameterTrue || headers.length <= 1) {
                int length2 = headers.length - 1;
                while (true) {
                    if (length2 < 0) {
                        j = -1;
                        break;
                    }
                    try {
                        j = Long.parseLong(headers[length2].getValue());
                        break;
                    } catch (NumberFormatException e2) {
                        if (isParameterTrue) {
                            throw new ProtocolException("Invalid content length: " + header.getValue());
                        }
                        length2--;
                    }
                }
                if (j >= 0) {
                    return j;
                }
                return -1L;
            }
            throw new ProtocolException("Multiple content length headers");
        } else {
            return -1L;
        }
    }
}
