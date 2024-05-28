package org.apache.http.impl.p452io;

import java.io.IOException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequestFactory;
import org.apache.http.ParseException;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.HttpRequestParser */
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpRequestParser extends AbstractMessageParser {
    private final CharArrayBuffer lineBuf;
    private final HttpRequestFactory requestFactory;

    public HttpRequestParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpRequestFactory httpRequestFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        if (httpRequestFactory == null) {
            throw new IllegalArgumentException("Request factory may not be null");
        }
        this.requestFactory = httpRequestFactory;
        this.lineBuf = new CharArrayBuffer(128);
    }

    @Override // org.apache.http.impl.p452io.AbstractMessageParser
    protected HttpMessage parseHead(SessionInputBuffer sessionInputBuffer) throws IOException, HttpException, ParseException {
        this.lineBuf.clear();
        if (sessionInputBuffer.readLine(this.lineBuf) == -1) {
            throw new ConnectionClosedException("Client closed connection");
        }
        return this.requestFactory.newHttpRequest(this.lineParser.parseRequestLine(this.lineBuf, new ParserCursor(0, this.lineBuf.length())));
    }
}
