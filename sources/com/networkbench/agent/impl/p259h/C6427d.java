package com.networkbench.agent.impl.p259h;

import android.text.TextUtils;
import android.util.Log;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import java.io.IOException;
import java.util.UUID;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.h.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6427d implements Interceptor {

    /* renamed from: a */
    private static final InterfaceC6393e f16258a = C6394f.m10150a();

    /* renamed from: b */
    private InterfaceC6429f f16259b = new C6424b();

    /* renamed from: c */
    private OkHttpClient f16260c;

    public C6427d() {
        Log.d("TingYun", "OkHttpInstrumentation3 - wrapping Instructor");
    }

    /* renamed from: a */
    public void m9996a(OkHttpClient okHttpClient) {
        this.f16260c = okHttpClient;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (request == null || !Harvest.isHttp_network_enabled()) {
            return chain.proceed(request);
        }
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            nBSTransactionState.setHttpLibType(HttpLibType.OkHttp);
            if (this.f16259b == null) {
                this.f16259b = new C6424b();
            }
            if (this.f16259b.mo9990a() || request != null) {
                try {
                    request = m9994a(request, nBSTransactionState);
                    this.f16259b.mo9988a(request, nBSTransactionState);
                } catch (Exception e) {
                    f16258a.mo10121a("okhttp3.0 -> setCrossProcessHeader occur an error", e);
                }
                nBSTransactionState.requestHeaderParam = C6653u.m8705e(request.headers().toMultimap());
            }
        } catch (Exception e2) {
            f16258a.mo10121a("okhttp3 intercept error", e2);
        }
        OkHttpClient okHttpClient = this.f16260c;
        if (okHttpClient != null && okHttpClient.dns() != null) {
            C6426c.m9998a(this.f16260c, nBSTransactionState);
        }
        try {
            Response proceed = chain.proceed(request);
            try {
                nBSTransactionState.responseHeaderParam = C6653u.m8705e(proceed.headers().toMultimap());
                nBSTransactionState.setContentType(C6653u.m8694i(proceed.header("Content-Type")));
                nBSTransactionState.setBytesSent(m9995a(request));
            } catch (Exception e3) {
                f16258a.mo10121a("NBSOkHttp3Interceptor_. getContentType occur an error", e3);
            }
            if (this.f16259b.mo9990a() || proceed != null) {
                try {
                    this.f16259b.mo9987a(proceed, nBSTransactionState);
                } catch (Exception e4) {
                    InterfaceC6393e interfaceC6393e = f16258a;
                    interfaceC6393e.mo10116d("NBSOkHttp3Interceptor_  intercept()---> responseFinished  has an error : " + e4);
                }
            }
            return proceed.newBuilder().body(new C6428e(proceed.body(), nBSTransactionState, m9992a(proceed))).build();
        } catch (IOException e5) {
            if (this.f16259b.mo9990a()) {
                try {
                    this.f16259b.mo9989a(nBSTransactionState, e5);
                } catch (Exception e6) {
                    InterfaceC6393e interfaceC6393e2 = f16258a;
                    interfaceC6393e2.mo10116d("NBSOkHttp3Interceptor_  intercept() --->httpError has an error : " + e6);
                }
            }
            throw e5;
        }
    }

    /* renamed from: a */
    private long m9995a(Request request) {
        if (request == null || request.body() == null) {
            return 0L;
        }
        try {
            return request.body().contentLength();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = f16258a;
            interfaceC6393e.mo10116d("getRequestBodyLength error:" + th.getMessage());
            return 0L;
        }
    }

    /* renamed from: a */
    private boolean m9992a(Response response) {
        try {
            return !TextUtils.isEmpty(response.header("Content-Range", ""));
        } catch (Throwable th) {
            f16258a.mo10121a("isContentRangeHeaderExist happened error", th);
            return false;
        }
    }

    /* renamed from: a */
    private Request m9994a(Request request, NBSTransactionState nBSTransactionState) {
        try {
            Request.Builder newBuilder = request.newBuilder();
            if (nBSTransactionState == null) {
                nBSTransactionState = new NBSTransactionState();
            }
            for (String str : request.headers().names()) {
                InterfaceC6393e interfaceC6393e = f16258a;
                interfaceC6393e.mo10117c("request header：value" + request.header(str) + ", name:" + str);
            }
            if (C6638h.m8963w().m9036aj()) {
                nBSTransactionState.setRequestHeaderIdValue(request.header(C6638h.m8963w().f17178d));
            }
            m9993a(request, nBSTransactionState, newBuilder);
            String m9034al = C6638h.m8963w().m9034al();
            if (!TextUtils.isEmpty(m9034al) && C6638h.m8963w().m9036aj()) {
                newBuilder.addHeader("X-Tingyun-Id", C6638h.m9050a(m9034al, C6638h.m9031ao()));
            }
            if (C6638h.m8963w().m9032an()) {
                newBuilder.addHeader("X-Tingyun", C6638h.m8963w().m9033am());
            }
            if (request.tag() == null) {
                f16258a.mo10122a("set request tag");
                newBuilder.tag(nBSTransactionState);
            }
            return newBuilder.build();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e2 = f16258a;
            interfaceC6393e2.mo10116d("NBSOkHttp3Interceptor_  setCrossProcessHeader---> has an error : " + e);
            return request;
        }
    }

    /* renamed from: a */
    private void m9993a(Request request, NBSTransactionState nBSTransactionState, Request.Builder builder) {
        try {
            if (C6638h.m8963w().m9027b()) {
                String replace = UUID.randomUUID().toString().replace("-", "");
                C6396h.m10126p("OKhttp3 setCrossProcessHeader uuid :" + replace);
                JSONArray jSONArray = new JSONArray(C6638h.m8963w().m9060a().toString());
                C6396h.m10126p("okhttp3 setCrossProcessHeader apmsIssue  length :" + jSONArray.length());
                for (int i = 0; i < m9997a(jSONArray.length()); i++) {
                    String string = jSONArray.getString(i);
                    C6396h.m10126p("okhttp3 setCrossProcessHeader apms  :" + string);
                    if (TextUtils.isEmpty(request.header(string))) {
                        builder.addHeader(string, replace);
                        nBSTransactionState.setUUid(replace);
                    } else {
                        nBSTransactionState.getApmsList().add(string);
                        C6396h.m10126p("okhttp3 setCrossProcessHeader  apmsList  :" + string);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            C6396h.m10126p(" okhttp3 apms数据格式解析错误!!!" + th.getMessage());
        }
    }

    /* renamed from: a */
    private int m9997a(int i) {
        return i < 10 ? i : C6638h.f17106c;
    }
}
