package com.networkbench.agent.impl.instrumentation.httpclient;

import com.networkbench.agent.impl.instrumentation.NBSHttpClientUtil;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.instrumentation.p263io.NBSCountingInputStream;
import com.networkbench.agent.impl.instrumentation.p263io.NBSCountingOutputStream;
import com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteEvent;
import com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener;
import com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListenerSource;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6642k;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSHttpRequestEntityImpl implements NBSStreamCompleteListener, HttpEntity {
    private final HttpEntity impl;
    private final NBSTransactionState transactionState;

    public NBSHttpRequestEntityImpl(HttpEntity httpEntity, NBSTransactionState nBSTransactionState) {
        this.impl = httpEntity;
        this.transactionState = nBSTransactionState;
    }

    @Override // org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
        try {
            this.impl.consumeContent();
        } catch (IOException e) {
            NBSHttpClientUtil.setErrorCodeFromException(this.transactionState, e);
            if (!this.transactionState.isComplete()) {
                C6642k.m8918a(new C6410a(this.transactionState.end()));
            }
            throw e;
        }
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException, IllegalStateException {
        try {
            if (!this.transactionState.isSent()) {
                NBSCountingInputStream nBSCountingInputStream = new NBSCountingInputStream(this.impl.getContent());
                nBSCountingInputStream.addStreamCompleteListener(this);
                return nBSCountingInputStream;
            }
            return this.impl.getContent();
        } catch (IOException e) {
            NBSHttpClientUtil.setErrorCodeFromException(this.transactionState, e);
            if (!this.transactionState.isComplete()) {
                C6642k.m8918a(new C6410a(this.transactionState.end()));
            }
            throw e;
        } catch (IllegalStateException e2) {
            NBSHttpClientUtil.setErrorCodeFromException(this.transactionState, e2);
            if (!this.transactionState.isComplete()) {
                C6642k.m8918a(new C6410a(this.transactionState.end()));
            }
            throw e2;
        }
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return this.impl.getContentEncoding();
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        return this.impl.getContentLength();
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentType() {
        return this.impl.getContentType();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isChunked() {
        return this.impl.isChunked();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return this.impl.isRepeatable();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return this.impl.isStreaming();
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        try {
            if (!this.transactionState.isSent()) {
                NBSCountingOutputStream nBSCountingOutputStream = new NBSCountingOutputStream(outputStream);
                this.impl.writeTo(nBSCountingOutputStream);
                this.transactionState.setBytesSent(nBSCountingOutputStream.getCount());
                return;
            }
            this.impl.writeTo(outputStream);
        } catch (IOException e) {
            NBSHttpClientUtil.setErrorCodeFromException(this.transactionState, e);
            if (!this.transactionState.isComplete()) {
                C6642k.m8918a(new C6410a(this.transactionState.end()));
            }
            throw e;
        }
    }

    @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
    public void streamComplete(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
        ((NBSStreamCompleteListenerSource) nBSStreamCompleteEvent.getSource()).removeStreamCompleteListener(this);
        this.transactionState.setBytesSent(nBSStreamCompleteEvent.getBytes());
    }

    @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
    public void streamError(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
        ((NBSStreamCompleteListenerSource) nBSStreamCompleteEvent.getSource()).removeStreamCompleteListener(this);
        NBSHttpClientUtil.setErrorCodeFromException(this.transactionState, nBSStreamCompleteEvent.getException());
        if (this.transactionState.isComplete()) {
            return;
        }
        this.transactionState.setBytesSent(nBSStreamCompleteEvent.getBytes());
        C6642k.m8918a(new C6410a(this.transactionState.end()));
    }
}
