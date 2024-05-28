package com.networkbench.agent.impl.instrumentation.httpclient;

import com.networkbench.agent.impl.instrumentation.NBSHttpClientUtil;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.instrumentation.p263io.NBSCountingInputStream;
import com.networkbench.agent.impl.instrumentation.p263io.NBSCountingOutputStream;
import com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteEvent;
import com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener;
import com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListenerSource;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6648q;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.AbstractHttpMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSHttpResponseEntityImpl implements NBSStreamCompleteListener, HttpEntity {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private NBSCountingInputStream contentStream;
    private final HttpEntity impl;
    private HttpResponse response;
    private final NBSTransactionState transactionState;

    public NBSHttpResponseEntityImpl(HttpResponse httpResponse, NBSTransactionState nBSTransactionState, long j) {
        this.response = httpResponse;
        this.impl = httpResponse.getEntity();
        this.transactionState = nBSTransactionState;
    }

    @Override // org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
        try {
            this.impl.consumeContent();
        } catch (IOException e) {
            NBSHttpClientUtil.setErrorCodeFromException(this.transactionState, e);
            if (!this.transactionState.isComplete()) {
                this.transactionState.end();
                if (this.transactionState.isError()) {
                    this.transactionState.setErrorDataInfo("", new HashMap(), this.transactionState.getException() != null ? this.transactionState.getException() : "");
                }
                C6648q.m8781a(new C6412c(this.transactionState));
            }
            throw e;
        }
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException, IllegalStateException {
        NBSCountingInputStream nBSCountingInputStream = this.contentStream;
        if (nBSCountingInputStream != null) {
            return nBSCountingInputStream;
        }
        try {
            boolean z = true;
            if (this.impl instanceof AbstractHttpMessage) {
                Header lastHeader = ((AbstractHttpMessage) this.impl).getLastHeader("Transfer-Encoding");
                if (lastHeader != null && "chunked".equalsIgnoreCase(lastHeader.getValue())) {
                    z = false;
                }
            } else if (this.impl instanceof HttpEntityWrapper) {
                z = true ^ this.impl.isChunked();
            }
            this.contentStream = new NBSCountingInputStream(this.impl.getContent(), z);
            this.contentStream.addStreamCompleteListener(this);
            return this.contentStream;
        } catch (IOException e) {
            NBSHttpClientUtil.setErrorCodeFromException(this.transactionState, e);
            if (!this.transactionState.isComplete()) {
                C6642k.m8918a(new C6410a(this.transactionState.end()));
            }
            throw e;
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
        if (!this.transactionState.isComplete()) {
            NBSCountingOutputStream nBSCountingOutputStream = new NBSCountingOutputStream(outputStream);
            try {
                this.impl.writeTo(nBSCountingOutputStream);
                if (this.transactionState.isComplete()) {
                    return;
                }
                this.transactionState.setBytesReceived(nBSCountingOutputStream.getCount());
                m9869a(this.transactionState);
                return;
            } catch (IOException e) {
                NBSHttpClientUtil.setErrorCodeFromException(this.transactionState, e);
                if (!this.transactionState.isComplete()) {
                    this.transactionState.setBytesReceived(nBSCountingOutputStream.getCount());
                    C6642k.m8918a(new C6410a(this.transactionState.end()));
                }
                e.printStackTrace();
                throw e;
            }
        }
        this.impl.writeTo(outputStream);
    }

    @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
    public void streamComplete(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
        ((NBSStreamCompleteListenerSource) nBSStreamCompleteEvent.getSource()).removeStreamCompleteListener(this);
        log.mo10117c("streamComplete");
        if (this.transactionState.isComplete()) {
            return;
        }
        log.mo10117c("transaction not complete");
        this.transactionState.setBytesReceived(nBSStreamCompleteEvent.getBytes());
        m9869a(this.transactionState);
    }

    @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
    public void streamError(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
        ((NBSStreamCompleteListenerSource) nBSStreamCompleteEvent.getSource()).removeStreamCompleteListener(this);
        NBSHttpClientUtil.setErrorCodeFromException(this.transactionState, nBSStreamCompleteEvent.getException());
        if (this.transactionState.isComplete()) {
            return;
        }
        this.transactionState.setBytesReceived(nBSStreamCompleteEvent.getBytes());
    }

    /* renamed from: a */
    private void m9869a(NBSTransactionState nBSTransactionState) {
        try {
            if (nBSTransactionState.end() == null) {
                log.mo10115e("HttpResponseEntityWrapperImpl transactionData is null!");
                return;
            }
            if (nBSTransactionState.isError()) {
                StringBuilder sb = new StringBuilder();
                try {
                    InputStream content = getContent();
                    if (content instanceof NBSCountingInputStream) {
                        sb.append(((NBSCountingInputStream) content).getBufferAsString());
                    }
                } catch (Exception e) {
                    log.mo10116d(e.toString());
                }
                Map<String, Object> resolvingResponseHeader = NBSHttpClientUtil.resolvingResponseHeader(this.response);
                resolvingResponseHeader.put("Content-Length", Long.valueOf(nBSTransactionState.getBytesReceived()));
                String exception = nBSTransactionState.getException() != null ? nBSTransactionState.getException() : "";
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10116d("error message:" + exception);
                nBSTransactionState.setErrorDataInfo(sb.toString(), resolvingResponseHeader, exception);
            }
            C6648q.m8781a(new C6412c(nBSTransactionState));
        } catch (Exception e2) {
            log.mo10121a("addTransactionAndErrorData", e2);
        }
    }
}
