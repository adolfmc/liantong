package org.apache.http.message;

import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicLineFormatter implements LineFormatter {
    public static final BasicLineFormatter DEFAULT = new BasicLineFormatter();

    protected CharArrayBuffer initBuffer(CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer != null) {
            charArrayBuffer.clear();
            return charArrayBuffer;
        }
        return new CharArrayBuffer(64);
    }

    public static final String formatProtocolVersion(ProtocolVersion protocolVersion, LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = DEFAULT;
        }
        return lineFormatter.appendProtocolVersion(null, protocolVersion).toString();
    }

    @Override // org.apache.http.message.LineFormatter
    public CharArrayBuffer appendProtocolVersion(CharArrayBuffer charArrayBuffer, ProtocolVersion protocolVersion) {
        if (protocolVersion == null) {
            throw new IllegalArgumentException("Protocol version may not be null");
        }
        int estimateProtocolVersionLen = estimateProtocolVersionLen(protocolVersion);
        if (charArrayBuffer == null) {
            charArrayBuffer = new CharArrayBuffer(estimateProtocolVersionLen);
        } else {
            charArrayBuffer.ensureCapacity(estimateProtocolVersionLen);
        }
        charArrayBuffer.append(protocolVersion.getProtocol());
        charArrayBuffer.append('/');
        charArrayBuffer.append(Integer.toString(protocolVersion.getMajor()));
        charArrayBuffer.append('.');
        charArrayBuffer.append(Integer.toString(protocolVersion.getMinor()));
        return charArrayBuffer;
    }

    protected int estimateProtocolVersionLen(ProtocolVersion protocolVersion) {
        return protocolVersion.getProtocol().length() + 4;
    }

    public static final String formatRequestLine(RequestLine requestLine, LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = DEFAULT;
        }
        return lineFormatter.formatRequestLine(null, requestLine).toString();
    }

    @Override // org.apache.http.message.LineFormatter
    public CharArrayBuffer formatRequestLine(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        if (requestLine == null) {
            throw new IllegalArgumentException("Request line may not be null");
        }
        CharArrayBuffer initBuffer = initBuffer(charArrayBuffer);
        doFormatRequestLine(initBuffer, requestLine);
        return initBuffer;
    }

    protected void doFormatRequestLine(CharArrayBuffer charArrayBuffer, RequestLine requestLine) {
        String method = requestLine.getMethod();
        String uri = requestLine.getUri();
        charArrayBuffer.ensureCapacity(method.length() + 1 + uri.length() + 1 + estimateProtocolVersionLen(requestLine.getProtocolVersion()));
        charArrayBuffer.append(method);
        charArrayBuffer.append(' ');
        charArrayBuffer.append(uri);
        charArrayBuffer.append(' ');
        appendProtocolVersion(charArrayBuffer, requestLine.getProtocolVersion());
    }

    public static final String formatStatusLine(StatusLine statusLine, LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = DEFAULT;
        }
        return lineFormatter.formatStatusLine(null, statusLine).toString();
    }

    @Override // org.apache.http.message.LineFormatter
    public CharArrayBuffer formatStatusLine(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        if (statusLine == null) {
            throw new IllegalArgumentException("Status line may not be null");
        }
        CharArrayBuffer initBuffer = initBuffer(charArrayBuffer);
        doFormatStatusLine(initBuffer, statusLine);
        return initBuffer;
    }

    protected void doFormatStatusLine(CharArrayBuffer charArrayBuffer, StatusLine statusLine) {
        int estimateProtocolVersionLen = estimateProtocolVersionLen(statusLine.getProtocolVersion()) + 1 + 3 + 1;
        String reasonPhrase = statusLine.getReasonPhrase();
        if (reasonPhrase != null) {
            estimateProtocolVersionLen += reasonPhrase.length();
        }
        charArrayBuffer.ensureCapacity(estimateProtocolVersionLen);
        appendProtocolVersion(charArrayBuffer, statusLine.getProtocolVersion());
        charArrayBuffer.append(' ');
        charArrayBuffer.append(Integer.toString(statusLine.getStatusCode()));
        charArrayBuffer.append(' ');
        if (reasonPhrase != null) {
            charArrayBuffer.append(reasonPhrase);
        }
    }

    public static final String formatHeader(Header header, LineFormatter lineFormatter) {
        if (lineFormatter == null) {
            lineFormatter = DEFAULT;
        }
        return lineFormatter.formatHeader(null, header).toString();
    }

    @Override // org.apache.http.message.LineFormatter
    public CharArrayBuffer formatHeader(CharArrayBuffer charArrayBuffer, Header header) {
        if (header == null) {
            throw new IllegalArgumentException("Header may not be null");
        }
        if (header instanceof FormattedHeader) {
            return ((FormattedHeader) header).getBuffer();
        }
        CharArrayBuffer initBuffer = initBuffer(charArrayBuffer);
        doFormatHeader(initBuffer, header);
        return initBuffer;
    }

    protected void doFormatHeader(CharArrayBuffer charArrayBuffer, Header header) {
        String name = header.getName();
        String value = header.getValue();
        int length = name.length() + 2;
        if (value != null) {
            length += value.length();
        }
        charArrayBuffer.ensureCapacity(length);
        charArrayBuffer.append(name);
        charArrayBuffer.append(": ");
        if (value != null) {
            charArrayBuffer.append(value);
        }
    }
}
