package com.networkbench.agent.impl.instrumentation.httpclient;

import com.networkbench.agent.impl.instrumentation.NBSHttpClientUtil;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSResponseHandlerImpl<T> implements ResponseHandler<T> {
    private final ResponseHandler<T> impl;
    private final NBSTransactionState transactionState;

    private NBSResponseHandlerImpl(ResponseHandler<T> responseHandler, NBSTransactionState nBSTransactionState) {
        this.impl = responseHandler;
        this.transactionState = nBSTransactionState;
    }

    @Override // org.apache.http.client.ResponseHandler
    public T handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        NBSHttpClientUtil.inspectAndInstrument(this.transactionState, httpResponse);
        return this.impl.handleResponse(httpResponse);
    }

    public static <T> ResponseHandler<? extends T> wrap(ResponseHandler<? extends T> responseHandler, NBSTransactionState nBSTransactionState) {
        return new NBSResponseHandlerImpl(responseHandler, nBSTransactionState);
    }
}
