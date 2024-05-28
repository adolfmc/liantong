package com.networkbench.agent.impl.instrumentation.okhttp2;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.instrumentation.NBSTransactionStateUtil;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.socket.C6621r;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6643l;
import com.networkbench.agent.impl.util.C6648q;
import com.networkbench.agent.impl.util.C6653u;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSCallExtension extends Call {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private Call impl;
    private boolean isOkhttp2;
    private Request request;
    private NBSTransactionState transactionState;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NBSCallExtension(OkHttpClient okHttpClient, Request request) {
        super(okHttpClient, request);
        this.isOkhttp2 = true;
        m9848a(okHttpClient, request);
        this.request = m9845a(request, okHttpClient);
        this.transactionState.requestHeaderParam = C6653u.m8705e(NBSOkHttp2Instrumentation.getHeaderForOkhttp2(request.headers()));
        this.impl = okHttpClient.newCall(this.request);
    }

    /* renamed from: a */
    private void m9847a(final Request request) {
        C6369q.m10273a().m10272a(new Runnable() { // from class: com.networkbench.agent.impl.instrumentation.okhttp2.NBSCallExtension.1
            @Override // java.lang.Runnable
            public void run() {
                C6621r.m9198b(request.url().getHost(), C6642k.m8917a(request.url().getHost()));
                if (NBSCallExtension.this.transactionState != null) {
                    NBSCallExtension.this.transactionState.setOk2DnsFromThread(true);
                    NBSCallExtension.this.transactionState.setDnsElapse(C6621r.m9199b(request.url().getHost()));
                }
            }
        });
    }

    /* renamed from: a */
    private void m9848a(OkHttpClient okHttpClient, Request request) {
        if (Harvest.isHttp_network_enabled()) {
            if (m9851a()) {
                NBSOk2Dns.replaceDefaultDns(okHttpClient, m9842b());
            } else {
                m9847a(request);
            }
        }
    }

    /* renamed from: a */
    private boolean m9851a() {
        boolean z = false;
        try {
            C6643l c6643l = new C6643l(C6653u.m8695i());
            if (c6643l.m8897a() == 2) {
                if (c6643l.m8896b() >= 6) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10115e("get okhttp2 version error:" + th);
        }
        InterfaceC6393e interfaceC6393e2 = log;
        interfaceC6393e2.mo10122a("okhttp2 dns interface is " + z);
        return z;
    }

    /* renamed from: a */
    private Request m9845a(Request request, OkHttpClient okHttpClient) {
        if (request != null) {
            try {
                if (Harvest.isHttp_network_enabled()) {
                    if (this.transactionState == null) {
                        this.transactionState = new NBSTransactionState(this.isOkhttp2);
                    }
                    if (C6638h.m8963w().m9036aj()) {
                        this.transactionState.setRequestHeaderIdValue(request.header(C6638h.m8963w().f17178d));
                    }
                    m9846a(request, this.transactionState);
                    this.transactionState.setAppPhase(C6638h.f17113m.intValue());
                    Request.Builder newBuilder = request.newBuilder();
                    String m9034al = C6638h.m8963w().m9034al();
                    if (!TextUtils.isEmpty(m9034al) && C6638h.m8963w().m9036aj()) {
                        newBuilder.addHeader("X-Tingyun-Id", C6638h.m9050a(m9034al, C6638h.m9031ao()));
                    }
                    if (C6638h.m8963w().m9032an()) {
                        newBuilder.addHeader("X-Tingyun", C6638h.m8963w().m9033am());
                    }
                    Request build = newBuilder.build();
                    NBSOkHttp2TransactionStateUtil.inspectAndInstrument(this.transactionState, build);
                    return build;
                }
            } catch (Exception unused) {
                return request;
            }
        }
        return request;
    }

    public Response execute() throws IOException {
        try {
            m9842b().setTraces();
            Response execute = this.impl.execute();
            m9844a(execute);
            return execute;
        } catch (IOException e) {
            m9843a(e, (Response) null);
            throw e;
        }
    }

    public void enqueue(Callback callback) {
        this.impl.enqueue(new NBSCallbackExtension(callback, m9842b()));
    }

    public void cancel() {
        this.impl.cancel();
    }

    public boolean isCanceled() {
        return this.impl.isCanceled();
    }

    /* renamed from: a */
    private void m9844a(Response response) {
        try {
            if (Harvest.isHttp_network_enabled() && !m9842b().isComplete()) {
                NBSOkHttp2TransactionStateUtil.inspectAndInstrumentResponse(m9842b(), response);
            }
        } catch (Exception e) {
            C6396h.m10134h("NBSCallExtension checkResponse() : " + e);
        }
    }

    /* renamed from: b */
    private NBSTransactionState m9842b() {
        if (this.transactionState == null) {
            this.transactionState = new NBSTransactionState(this.isOkhttp2);
        }
        return this.transactionState;
    }

    /* renamed from: a */
    private void m9843a(Exception exc, Response response) {
        try {
            if (Harvest.isHttp_network_enabled()) {
                NBSTransactionState m9842b = m9842b();
                NBSTransactionStateUtil.setErrorCodeFromException(m9842b, exc);
                if (m9842b.isComplete() || m9842b.end() == null) {
                    return;
                }
                if (response != null) {
                    m9842b.setContentType(C6653u.m8694i(response.header("Content-Type")));
                }
                if (m9842b.isError()) {
                    String exception = m9842b.getException() != null ? m9842b.getException() : "";
                    InterfaceC6393e interfaceC6393e = log;
                    interfaceC6393e.mo10122a("error message:" + exception);
                    if (m9842b.isError()) {
                        m9842b.setErrorDataInfo(exception, new HashMap(), "");
                    }
                }
                C6648q.m8781a(new C6412c(m9842b));
            }
        } catch (Exception unused) {
            C6396h.m10134h("NBSCallExtension error() har an error :" + exc);
        }
    }

    /* renamed from: a */
    private void m9846a(Request request, NBSTransactionState nBSTransactionState) {
        try {
            if (C6638h.m8963w().m9027b()) {
                String replace = UUID.randomUUID().toString().replace("-", "");
                C6396h.m10126p("HttpURLConnection setCrossProcessHeader uuid :" + replace);
                JSONArray jSONArray = new JSONArray(C6638h.m8963w().m9060a().toString());
                for (int i = 0; i < m9850a(jSONArray.length()); i++) {
                    String string = jSONArray.getString(i);
                    C6396h.m10126p("okhttp2 setCrossProcessHeader apms  :" + string);
                    if (TextUtils.isEmpty(request.header(string))) {
                        request.newBuilder().addHeader(string, replace);
                        nBSTransactionState.setUUid(replace);
                        C6396h.m10126p("okhttp2 setCrossProcessHeader apms  :" + string + "------  uuid : " + replace);
                    } else {
                        nBSTransactionState.getApmsList().add(string);
                        C6396h.m10126p("okhttp2 setCrossProcessHeader  apmsList  :" + string);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            C6396h.m10126p(" okhttp2 apms数据格式解析错误!!!" + th.getMessage());
        }
    }

    /* renamed from: a */
    private int m9850a(int i) {
        return i < 10 ? i : C6638h.f17106c;
    }
}
