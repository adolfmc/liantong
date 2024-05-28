package org.apache.http.impl.p452io;

import java.io.IOException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.message.LineFormatter;
import org.apache.http.p453io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.impl.io.HttpRequestWriter */
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpRequestWriter extends AbstractMessageWriter {
    public HttpRequestWriter(SessionOutputBuffer sessionOutputBuffer, LineFormatter lineFormatter, HttpParams httpParams) {
        super(sessionOutputBuffer, lineFormatter, httpParams);
    }

    @Override // org.apache.http.impl.p452io.AbstractMessageWriter
    protected void writeHeadLine(HttpMessage httpMessage) throws IOException {
        this.sessionBuffer.writeLine(this.lineFormatter.formatRequestLine(this.lineBuf, ((HttpRequest) httpMessage).getRequestLine()));
    }
}
