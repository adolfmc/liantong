package org.apache.http.impl.p452io;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.p453io.HttpMessageParser;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.AbstractMessageParser */
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractMessageParser implements HttpMessageParser {
    protected final LineParser lineParser;
    private final int maxHeaderCount;
    private final int maxLineLen;
    private final SessionInputBuffer sessionBuffer;

    protected abstract HttpMessage parseHead(SessionInputBuffer sessionInputBuffer) throws IOException, HttpException, ParseException;

    public AbstractMessageParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpParams httpParams) {
        if (sessionInputBuffer == null) {
            throw new IllegalArgumentException("Session input buffer may not be null");
        }
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        this.sessionBuffer = sessionInputBuffer;
        this.maxHeaderCount = httpParams.getIntParameter("http.connection.max-header-count", -1);
        this.maxLineLen = httpParams.getIntParameter("http.connection.max-line-length", -1);
        this.lineParser = lineParser == null ? BasicLineParser.DEFAULT : lineParser;
    }

    public static Header[] parseHeaders(SessionInputBuffer sessionInputBuffer, int i, int i2, LineParser lineParser) throws HttpException, IOException {
        int i3;
        char charAt;
        if (sessionInputBuffer == null) {
            throw new IllegalArgumentException("Session input buffer may not be null");
        }
        if (lineParser == null) {
            lineParser = BasicLineParser.DEFAULT;
        }
        ArrayList arrayList = new ArrayList();
        CharArrayBuffer charArrayBuffer = null;
        CharArrayBuffer charArrayBuffer2 = null;
        while (true) {
            if (charArrayBuffer == null) {
                charArrayBuffer = new CharArrayBuffer(64);
            } else {
                charArrayBuffer.clear();
            }
            i3 = 0;
            if (sessionInputBuffer.readLine(charArrayBuffer) == -1 || charArrayBuffer.length() < 1) {
                break;
            }
            if ((charArrayBuffer.charAt(0) == ' ' || charArrayBuffer.charAt(0) == '\t') && charArrayBuffer2 != null) {
                while (i3 < charArrayBuffer.length() && ((charAt = charArrayBuffer.charAt(i3)) == ' ' || charAt == '\t')) {
                    i3++;
                }
                if (i2 > 0 && ((charArrayBuffer2.length() + 1) + charArrayBuffer.length()) - i3 > i2) {
                    throw new IOException("Maximum line length limit exceeded");
                }
                charArrayBuffer2.append(' ');
                charArrayBuffer2.append(charArrayBuffer, i3, charArrayBuffer.length() - i3);
            } else {
                arrayList.add(charArrayBuffer);
                charArrayBuffer2 = charArrayBuffer;
                charArrayBuffer = null;
            }
            if (i > 0 && arrayList.size() >= i) {
                throw new IOException("Maximum header count exceeded");
            }
        }
        Header[] headerArr = new Header[arrayList.size()];
        while (i3 < arrayList.size()) {
            try {
                headerArr[i3] = lineParser.parseHeader((CharArrayBuffer) arrayList.get(i3));
                i3++;
            } catch (ParseException e) {
                throw new ProtocolException(e.getMessage());
            }
        }
        return headerArr;
    }

    @Override // org.apache.http.p453io.HttpMessageParser
    public HttpMessage parse() throws IOException, HttpException {
        try {
            HttpMessage parseHead = parseHead(this.sessionBuffer);
            parseHead.setHeaders(parseHeaders(this.sessionBuffer, this.maxHeaderCount, this.maxLineLen, this.lineParser));
            return parseHead;
        } catch (ParseException e) {
            throw new ProtocolException(e.getMessage(), e);
        }
    }
}
