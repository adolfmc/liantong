package org.apache.http.impl.entity;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.p452io.ChunkedOutputStream;
import org.apache.http.impl.p452io.ContentLengthOutputStream;
import org.apache.http.impl.p452io.IdentityOutputStream;
import org.apache.http.p453io.SessionOutputBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class EntitySerializer {
    private final ContentLengthStrategy lenStrategy;

    public EntitySerializer(ContentLengthStrategy contentLengthStrategy) {
        if (contentLengthStrategy == null) {
            throw new IllegalArgumentException("Content length strategy may not be null");
        }
        this.lenStrategy = contentLengthStrategy;
    }

    protected OutputStream doSerialize(SessionOutputBuffer sessionOutputBuffer, HttpMessage httpMessage) throws HttpException, IOException {
        long determineLength = this.lenStrategy.determineLength(httpMessage);
        if (determineLength == -2) {
            return new ChunkedOutputStream(sessionOutputBuffer);
        }
        if (determineLength == -1) {
            return new IdentityOutputStream(sessionOutputBuffer);
        }
        return new ContentLengthOutputStream(sessionOutputBuffer, determineLength);
    }

    public void serialize(SessionOutputBuffer sessionOutputBuffer, HttpMessage httpMessage, HttpEntity httpEntity) throws HttpException, IOException {
        if (sessionOutputBuffer == null) {
            throw new IllegalArgumentException("Session output buffer may not be null");
        }
        if (httpMessage == null) {
            throw new IllegalArgumentException("HTTP message may not be null");
        }
        if (httpEntity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        OutputStream doSerialize = doSerialize(sessionOutputBuffer, httpMessage);
        httpEntity.writeTo(doSerialize);
        doSerialize.close();
    }
}
