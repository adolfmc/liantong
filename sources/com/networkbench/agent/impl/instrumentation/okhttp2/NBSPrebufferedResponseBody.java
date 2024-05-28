package com.networkbench.agent.impl.instrumentation.okhttp2;

import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p259h.C6423a;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSPrebufferedResponseBody extends ResponseBody {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private final ResponseBody impl;
    private boolean isContentRange;
    private BufferedSource source;
    private NBSTransactionState transactionState;

    public NBSPrebufferedResponseBody(ResponseBody responseBody, NBSTransactionState nBSTransactionState, boolean z) {
        this.impl = responseBody;
        this.transactionState = nBSTransactionState;
        this.isContentRange = z;
    }

    public MediaType contentType() {
        return this.impl.contentType();
    }

    public long contentLength() throws IOException {
        return this.impl.contentLength();
    }

    public BufferedSource source() throws IOException {
        if (this.source == null) {
            this.source = Okio.buffer(m9825a(this.impl.source()));
        }
        return this.source;
    }

    /* renamed from: a */
    private Source m9825a(BufferedSource bufferedSource) {
        long j;
        try {
            j = this.impl.contentLength();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10115e("ok2 getContentLength error:" + th);
            j = 0L;
        }
        return new C6423a(this.transactionState, bufferedSource, this.isContentRange, j);
    }

    public void close() throws IOException {
        this.impl.close();
    }
}
