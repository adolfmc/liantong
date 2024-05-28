package com.networkbench.agent.impl.p259h;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.instrumentation.NBSNetworkProcessHeader;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.instrumentation.NBSTransactionStateUtil;
import com.networkbench.agent.impl.p239a.p240a.C6226b;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6648q;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.agent.impl.util.NBSAndroidAgentImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.h.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6424b implements InterfaceC6429f {

    /* renamed from: a */
    private static final InterfaceC6393e f16252a = C6394f.m10150a();

    /* renamed from: b */
    private volatile boolean f16253b = true;

    @Override // com.networkbench.agent.impl.p259h.InterfaceC6429f
    /* renamed from: a */
    public boolean mo9990a() {
        return this.f16253b;
    }

    /* renamed from: a */
    public static void m10004a(NBSTransactionState nBSTransactionState, String str, int i, int i2) {
        if (str != null && !"".equals(str) && C6638h.m8963w().m9036aj()) {
            nBSTransactionState.setAppData(str);
        }
        nBSTransactionState.setStatusCode(i2);
    }

    @Override // com.networkbench.agent.impl.p259h.InterfaceC6429f
    /* renamed from: a */
    public void mo9989a(NBSTransactionState nBSTransactionState, IOException iOException) {
        C6226b end;
        if (mo9986b()) {
            f16252a.mo10122a("okhttp3.0 ->httpError");
            NBSTransactionStateUtil.setErrorCodeFromException(nBSTransactionState, iOException);
            if (nBSTransactionState.isComplete() || (end = nBSTransactionState.end()) == null) {
                return;
            }
            end.m10984a(HttpLibType.OkHttp);
            if (nBSTransactionState.isError()) {
                String exception = nBSTransactionState.getException() != null ? nBSTransactionState.getException() : "";
                InterfaceC6393e interfaceC6393e = f16252a;
                interfaceC6393e.mo10122a("okhttp3.0 ->error message:" + exception);
                nBSTransactionState.setErrorDataInfo(exception, new HashMap(), "");
            }
            C6648q.m8781a(new C6412c(nBSTransactionState));
        }
    }

    @Override // com.networkbench.agent.impl.p259h.InterfaceC6429f
    /* renamed from: a */
    public void mo9988a(Request request, NBSTransactionState nBSTransactionState) {
        if (mo9986b()) {
            String httpUrl = request.url().toString();
            String str = null;
            if (httpUrl != null && httpUrl.contains("?")) {
                int indexOf = httpUrl.indexOf("?");
                String substring = httpUrl.substring(0, indexOf);
                str = httpUrl.substring(indexOf + 1);
                httpUrl = substring;
            }
            nBSTransactionState.setUrl(httpUrl);
            nBSTransactionState.setUrlParams(str);
            nBSTransactionState.setAllGetRequestParams(str);
            NBSTransactionStateUtil.setRequestMethod(nBSTransactionState, request.method());
            nBSTransactionState.setCarrier(NBSAgent.getActiveNetworkCarrier());
            nBSTransactionState.setHttpLibType(HttpLibType.OkHttp);
            if (httpUrl != null) {
                m10003a(nBSTransactionState, request);
            }
        }
    }

    @Override // com.networkbench.agent.impl.p259h.InterfaceC6429f
    /* renamed from: a */
    public void mo9987a(Response response, NBSTransactionState nBSTransactionState) {
        NBSAndroidAgentImpl impl;
        if (mo9986b()) {
            if (response == null) {
                f16252a.mo10115e("okhttp3.0 ->CallBack.onResponse(response) response is null ");
                return;
            }
            if (Harvest.isCdn_enabled() && (impl = NBSAgent.getImpl()) != null) {
                String cdnHeaderName = impl.m9150m().getCdnHeaderName();
                InterfaceC6393e interfaceC6393e = f16252a;
                interfaceC6393e.mo10122a("cdnHeaderName  key : " + cdnHeaderName);
                if (cdnHeaderName != null && !cdnHeaderName.isEmpty()) {
                    String header = response.header(cdnHeaderName);
                    nBSTransactionState.setCdnVendorName(header == null ? "" : header);
                    InterfaceC6393e interfaceC6393e2 = f16252a;
                    interfaceC6393e2.mo10122a("cdnHeaderName  value : " + header);
                }
            }
            int code = response.code();
            ResponseBody body = response.body();
            m10004a(nBSTransactionState, C6638h.m8963w().m9036aj() ? response.header("X-Tingyun-Tx-Data") : "", (int) (body == null ? 0L : body.contentLength()), code);
            C6396h.m10126p("okhttp3  setAppDataNew  start ....");
            nBSTransactionState.setAppDataNew(response.header("X-Tingyun-Data"));
            m10002a(nBSTransactionState, response);
            m10000c(nBSTransactionState, response);
        }
    }

    /* renamed from: a */
    private static void m10002a(NBSTransactionState nBSTransactionState, Response response) {
        try {
            nBSTransactionState.setContentType(C6653u.m8694i(response.header("Content-Type")));
        } catch (Exception unused) {
            f16252a.mo10116d("NBSOkHttp2TransactionStateUtil content-type has an error ");
        }
    }

    /* renamed from: b */
    private static void m10001b(NBSTransactionState nBSTransactionState, Response response) {
        if (nBSTransactionState.getStatusCode() >= 400) {
            TreeMap treeMap = new TreeMap();
            Headers headers = response.headers();
            if (headers != null && headers.size() > 0) {
                for (String str : headers.names()) {
                    String str2 = headers.get(str);
                    if (str2 != null) {
                        treeMap.put(str, str2);
                    }
                }
            }
            nBSTransactionState.setErrorDataInfo(response.message(), treeMap, nBSTransactionState.getException() != null ? nBSTransactionState.getException() : "");
        }
    }

    /* renamed from: c */
    private static void m10000c(NBSTransactionState nBSTransactionState, Response response) {
        m10001b(nBSTransactionState, response);
        nBSTransactionState.setEndState();
        C6648q.f17235e.add(nBSTransactionState);
    }

    @Override // com.networkbench.agent.impl.p259h.InterfaceC6429f
    /* renamed from: b */
    public boolean mo9986b() {
        return Harvest.isHttp_network_enabled();
    }

    /* renamed from: a */
    private static void m10003a(NBSTransactionState nBSTransactionState, final Request request) {
        NBSTransactionStateUtil.processParamsFilter(nBSTransactionState, nBSTransactionState.getUrlParams());
        NBSTransactionStateUtil.processHeaderParam(nBSTransactionState.getUrl(), new NBSNetworkProcessHeader() { // from class: com.networkbench.agent.impl.h.b.1
            @Override // com.networkbench.agent.impl.instrumentation.NBSNetworkProcessHeader
            public String getFilterHeader(String str) {
                Request request2 = Request.this;
                return (request2 == null || str == null) ? "" : request2.header(str);
            }
        }, nBSTransactionState);
    }
}
