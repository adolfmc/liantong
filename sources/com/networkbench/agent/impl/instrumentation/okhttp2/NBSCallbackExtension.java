package com.networkbench.agent.impl.instrumentation.okhttp2;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.instrumentation.NBSTransactionStateUtil;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.util.C6648q;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSCallbackExtension implements Callback {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private Callback impl;
    private NBSTransactionState transactionState;

    public NBSCallbackExtension(Callback callback, NBSTransactionState nBSTransactionState) {
        this.impl = callback;
        this.transactionState = nBSTransactionState;
    }

    public void onFailure(Request request, IOException iOException) {
        try {
            m9839a(iOException);
        } catch (Exception e) {
            C6396h.m10134h("NBSCallbackExtension onFailure : " + e);
        }
        this.impl.onFailure(request, iOException);
    }

    public void onResponse(Response response) throws IOException {
        try {
            m9840a(response);
        } catch (Exception e) {
            C6396h.m10134h("NBSCallbackExtension onResponse " + e);
        }
        this.impl.onResponse(response);
    }

    /* renamed from: a */
    private void m9840a(Response response) throws Exception {
        if (Harvest.isHttp_network_enabled() && !m9841a().isComplete()) {
            NBSOkHttp2TransactionStateUtil.inspectAndInstrumentResponse(m9841a(), response);
        }
    }

    /* renamed from: a */
    private NBSTransactionState m9841a() {
        return this.transactionState;
    }

    /* renamed from: a */
    private void m9839a(Exception exc) throws Exception {
        if (Harvest.isHttp_network_enabled()) {
            NBSTransactionState m9841a = m9841a();
            NBSTransactionStateUtil.setErrorCodeFromException(m9841a, exc);
            if (m9841a.isComplete() || m9841a.end() == null) {
                return;
            }
            if (m9841a.isError()) {
                String exception = m9841a.getException() != null ? m9841a.getException() : "";
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10122a("error message:" + exception);
                m9841a.setErrorDataInfo(exception, new HashMap(), "");
            }
            C6648q.m8781a(new C6412c(m9841a));
        }
    }
}
