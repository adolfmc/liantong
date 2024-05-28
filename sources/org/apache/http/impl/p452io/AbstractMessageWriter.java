package org.apache.http.impl.p452io;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.message.BasicLineFormatter;
import org.apache.http.message.LineFormatter;
import org.apache.http.p453io.HttpMessageWriter;
import org.apache.http.p453io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.AbstractMessageWriter */
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractMessageWriter implements HttpMessageWriter {
    protected final CharArrayBuffer lineBuf;
    protected final LineFormatter lineFormatter;
    protected final SessionOutputBuffer sessionBuffer;

    protected abstract void writeHeadLine(HttpMessage httpMessage) throws IOException;

    public AbstractMessageWriter(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter, HttpParams httpParams) {
        if (sessionOutputBuffer == null) {
            throw new IllegalArgumentException("Session input buffer may not be null");
        }
        this.sessionBuffer = sessionOutputBuffer;
        this.lineBuf = new CharArrayBuffer(128);
        this.lineFormatter = lineFormatter == null ? BasicLineFormatter.DEFAULT : lineFormatter;
    }

    @Override // org.apache.http.p453io.HttpMessageWriter
    public void write(HttpMessage httpMessage) throws IOException, HttpException {
        if (httpMessage == null) {
            throw new IllegalArgumentException("HTTP message may not be null");
        }
        writeHeadLine(httpMessage);
        HeaderIterator headerIterator = httpMessage.headerIterator();
        while (headerIterator.hasNext()) {
            this.sessionBuffer.writeLine(this.lineFormatter.formatHeader(this.lineBuf, (Header) headerIterator.next()));
        }
        this.lineBuf.clear();
        this.sessionBuffer.writeLine(this.lineBuf);
    }
}
