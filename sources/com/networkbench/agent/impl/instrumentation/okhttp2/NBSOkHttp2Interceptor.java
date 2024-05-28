package com.networkbench.agent.impl.instrumentation.okhttp2;

import android.text.TextUtils;
import android.util.Log;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.socket.C6621r;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6643l;
import com.networkbench.agent.impl.util.C6653u;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.UUID;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSOkHttp2Interceptor implements Interceptor {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private OkHttpClient client;
    private final NBSOKHttp2Reporter mEventReporter = new NBSOKHttp2Reporter();

    public NBSOkHttp2Interceptor() {
        Log.d("TingYun", "OkHttpInstrumentation2 - wrapping Instructor");
    }

    public void setClient(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (request == null || !Harvest.isHttp_network_enabled()) {
            return chain.proceed(request);
        }
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            nBSTransactionState.setHttpLibType(HttpLibType.OkHttp);
            if (this.mEventReporter.isEnabled() || request != null) {
                try {
                    request = m9831a(request, nBSTransactionState);
                    this.mEventReporter.requestFinished(request, nBSTransactionState);
                } catch (Exception e) {
                    log.mo10121a("okhttp2.0 -> setCrossProcessHeader occur an error", e);
                }
                nBSTransactionState.requestHeaderParam = C6653u.m8705e(NBSOkHttp2Instrumentation.getHeaderForOkhttp2(request.headers()));
            }
        } catch (Throwable th) {
            log.mo10121a("okhttp2 intercept error", th);
        }
        if (this.client != null) {
            if (m9834a()) {
                NBSOk2Dns.replaceDefaultDns(this.client, nBSTransactionState);
            } else {
                C6621r.m9198b(request.url().getHost(), C6642k.m8917a(request.url().getHost()));
                nBSTransactionState.setDnsElapse(C6621r.m9199b(request.url().getHost()));
            }
        }
        try {
            Response proceed = chain.proceed(request);
            try {
                nBSTransactionState.responseHeaderParam = C6653u.m8705e(NBSOkHttp2Instrumentation.getHeaderForOkhttp2(proceed.headers()));
                nBSTransactionState.setContentType(C6653u.m8694i(proceed.header("Content-Type")));
                nBSTransactionState.setBytesSent(m9832a(request));
            } catch (Exception e2) {
                log.mo10121a("NBSOkHttp2Interceptor_. getContentType occur an error", e2);
            }
            if (this.mEventReporter.isEnabled() || proceed != null) {
                try {
                    this.mEventReporter.responseFinished(proceed, nBSTransactionState);
                } catch (Exception e3) {
                    InterfaceC6393e interfaceC6393e = log;
                    interfaceC6393e.mo10116d("NBSOkHttp2Interceptor_  intercept()---> responseFinished  has an error : " + e3);
                }
            }
            return proceed.newBuilder().body(new NBSPrebufferedResponseBody(proceed.body(), nBSTransactionState, m9829a(proceed))).build();
        } catch (IOException e4) {
            if (this.mEventReporter.isEnabled()) {
                try {
                    this.mEventReporter.httpError(nBSTransactionState, e4);
                } catch (Exception e5) {
                    InterfaceC6393e interfaceC6393e2 = log;
                    interfaceC6393e2.mo10116d("NBSOkHttp2Interceptor_  intercept() --->httpError has an error : " + e5);
                }
            }
            throw e4;
        }
    }

    /* renamed from: a */
    private long m9832a(Request request) {
        if (request == null || request.body() == null) {
            return 0L;
        }
        try {
            return request.body().contentLength();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("getRequestBodyLength error:" + th.getMessage());
            return 0L;
        }
    }

    /* renamed from: a */
    private boolean m9829a(Response response) {
        try {
            return !TextUtils.isEmpty(response.header("Content-Range", ""));
        } catch (Throwable th) {
            log.mo10121a("isContentRangeHeaderExist happened error", th);
            return false;
        }
    }

    /* renamed from: a */
    private Request m9831a(Request request, NBSTransactionState nBSTransactionState) {
        try {
            Request.Builder newBuilder = request.newBuilder();
            if (nBSTransactionState == null) {
                nBSTransactionState = new NBSTransactionState();
            }
            m9830a(request, nBSTransactionState, newBuilder);
            if (C6638h.m8963w().m9036aj()) {
                nBSTransactionState.setRequestHeaderIdValue(request.header(C6638h.m8963w().f17178d));
            }
            String m9034al = C6638h.m8963w().m9034al();
            if (!TextUtils.isEmpty(m9034al) && C6638h.m8963w().m9036aj()) {
                newBuilder.addHeader("X-Tingyun-Id", C6638h.m9050a(m9034al, C6638h.m9031ao()));
            }
            if (C6638h.m8963w().m9032an()) {
                newBuilder.addHeader("X-Tingyun", C6638h.m8963w().m9033am());
            }
            if (request.tag() == null) {
                log.mo10122a("set request tag");
                newBuilder.tag(nBSTransactionState);
            }
            return newBuilder.build();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("NBSOkHttp2Interceptor setCrossProcessHeader---> has an error : " + e);
            return request;
        }
    }

    /* renamed from: a */
    private void m9830a(Request request, NBSTransactionState nBSTransactionState, Request.Builder builder) {
        try {
            if (C6638h.m8963w().m9027b()) {
                String replace = UUID.randomUUID().toString().replace("-", "");
                C6396h.m10126p("HttpURLConnection setCrossProcessHeader uuid :" + replace);
                JSONArray jSONArray = new JSONArray(C6638h.m8963w().m9060a().toString());
                for (int i = 0; i < m9833a(jSONArray.length()); i++) {
                    String string = jSONArray.getString(i);
                    C6396h.m10126p("okhttp2 setCrossProcessHeader apms  :" + string);
                    if (TextUtils.isEmpty(request.header(string))) {
                        builder.addHeader(string, replace);
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
    private int m9833a(int i) {
        return i < 10 ? i : C6638h.f17106c;
    }

    /* renamed from: a */
    private boolean m9834a() {
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
}
