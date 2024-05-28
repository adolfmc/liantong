package org.apache.http.impl.conn;

import org.apache.http.HttpResponseFactory;
import org.apache.http.impl.p452io.AbstractMessageParser;
import org.apache.http.message.LineParser;
import org.apache.http.p453io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class DefaultResponseParser extends AbstractMessageParser {
    private final CharArrayBuffer lineBuf;
    private final int maxGarbageLines;
    private final HttpResponseFactory responseFactory;

    public DefaultResponseParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpResponseFactory httpResponseFactory, HttpParams httpParams) {
        super(sessionInputBuffer, lineParser, httpParams);
        if (httpResponseFactory == null) {
            throw new IllegalArgumentException("Response factory may not be null");
        }
        this.responseFactory = httpResponseFactory;
        this.lineBuf = new CharArrayBuffer(128);
        this.maxGarbageLines = httpParams.getIntParameter("http.connection.max-status-line-garbage", Integer.MAX_VALUE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0052, code lost:
        throw new org.apache.http.ProtocolException("The server failed to respond with a valid HTTP response");
     */
    @Override // org.apache.http.impl.p452io.AbstractMessageParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected org.apache.http.HttpMessage parseHead(org.apache.http.p453io.SessionInputBuffer r8) throws java.io.IOException, org.apache.http.HttpException {
        /*
            r7 = this;
            org.apache.http.util.CharArrayBuffer r0 = r7.lineBuf
            r0.clear()
            r0 = 0
            r1 = r0
        L8:
            org.apache.http.util.CharArrayBuffer r2 = r7.lineBuf
            int r2 = r8.readLine(r2)
            r3 = -1
            if (r2 != r3) goto L1c
            if (r1 == 0) goto L14
            goto L1c
        L14:
            org.apache.http.NoHttpResponseException r8 = new org.apache.http.NoHttpResponseException
            java.lang.String r0 = "The target server failed to respond"
            r8.<init>(r0)
            throw r8
        L1c:
            org.apache.http.message.ParserCursor r4 = new org.apache.http.message.ParserCursor
            org.apache.http.util.CharArrayBuffer r5 = r7.lineBuf
            int r5 = r5.length()
            r4.<init>(r0, r5)
            org.apache.http.message.LineParser r5 = r7.lineParser
            org.apache.http.util.CharArrayBuffer r6 = r7.lineBuf
            boolean r5 = r5.hasProtocolVersion(r6, r4)
            if (r5 == 0) goto L42
        L32:
            org.apache.http.message.LineParser r8 = r7.lineParser
            org.apache.http.util.CharArrayBuffer r0 = r7.lineBuf
            org.apache.http.StatusLine r8 = r8.parseStatusLine(r0, r4)
            org.apache.http.HttpResponseFactory r0 = r7.responseFactory
            r1 = 0
            org.apache.http.HttpResponse r8 = r0.newHttpResponse(r8, r1)
            return r8
        L42:
            if (r2 == r3) goto L4b
            int r2 = r7.maxGarbageLines
            if (r1 >= r2) goto L4b
            int r1 = r1 + 1
            goto L8
        L4b:
            org.apache.http.ProtocolException r8 = new org.apache.http.ProtocolException
            java.lang.String r0 = "The server failed to respond with a valid HTTP response"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.conn.DefaultResponseParser.parseHead(org.apache.http.io.SessionInputBuffer):org.apache.http.HttpMessage");
    }
}
