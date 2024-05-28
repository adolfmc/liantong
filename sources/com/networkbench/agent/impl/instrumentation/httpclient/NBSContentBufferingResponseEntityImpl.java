package com.networkbench.agent.impl.instrumentation.httpclient;

import com.networkbench.agent.impl.instrumentation.p263io.NBSCountingInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSContentBufferingResponseEntityImpl implements HttpEntity {

    /* renamed from: a */
    final HttpEntity f16383a;
    private NBSCountingInputStream contentStream;

    public NBSContentBufferingResponseEntityImpl(HttpEntity httpEntity) {
        if (httpEntity == null) {
            throw new IllegalArgumentException("Missing wrapped entity");
        }
        this.f16383a = httpEntity;
    }

    @Override // org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
        this.f16383a.consumeContent();
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException, IllegalStateException {
        NBSCountingInputStream nBSCountingInputStream = this.contentStream;
        if (nBSCountingInputStream != null) {
            return nBSCountingInputStream;
        }
        this.contentStream = new NBSCountingInputStream(this.f16383a.getContent(), true);
        return this.contentStream;
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return this.f16383a.getContentEncoding();
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        return this.f16383a.getContentLength();
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentType() {
        return this.f16383a.getContentType();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isChunked() {
        return this.f16383a.isChunked();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return this.f16383a.isRepeatable();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return this.f16383a.isStreaming();
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        this.f16383a.writeTo(outputStream);
    }
}
