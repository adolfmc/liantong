package com.networkbench.agent.impl.instrumentation.okhttp2;

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
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSOKHttp2Reporter {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private volatile boolean isEnable = true;

    public boolean isEnabled() {
        return this.isEnable;
    }

    public static void inspectAndInstrumentResponse(NBSTransactionState nBSTransactionState, String str, int i, int i2) {
        if (str != null && !"".equals(str) && C6638h.m8963w().m9036aj()) {
            nBSTransactionState.setAppData(str);
        }
        nBSTransactionState.setStatusCode(i2);
        if (i >= 0) {
            nBSTransactionState.setBytesReceivedContent(i);
        }
    }

    public void httpError(NBSTransactionState nBSTransactionState, IOException iOException) {
        C6226b end;
        if (isNetworkEnter()) {
            log.mo10122a("okhttp2.0 ->httpError");
            NBSTransactionStateUtil.setErrorCodeFromException(nBSTransactionState, iOException);
            if (nBSTransactionState.isComplete() || (end = nBSTransactionState.end()) == null) {
                return;
            }
            end.m10984a(HttpLibType.OkHttp);
            if (nBSTransactionState.isError()) {
                String exception = nBSTransactionState.getException() != null ? nBSTransactionState.getException() : "";
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10122a("okhttp2.0 ->error message:" + exception);
                nBSTransactionState.setErrorDataInfo(exception, new HashMap(), "");
            }
            C6648q.m8781a(new C6412c(nBSTransactionState));
        }
    }

    public void requestFinished(Request request, NBSTransactionState nBSTransactionState) {
        if (isNetworkEnter()) {
            String url = request.url().toString();
            String str = null;
            if (url != null && url.contains("?")) {
                int indexOf = url.indexOf("?");
                String substring = url.substring(0, indexOf);
                str = url.substring(indexOf + 1);
                url = substring;
            }
            nBSTransactionState.setUrl(url);
            nBSTransactionState.setUrlParams(str);
            nBSTransactionState.setAllGetRequestParams(str);
            NBSTransactionStateUtil.setRequestMethod(nBSTransactionState, request.method());
            nBSTransactionState.setCarrier(NBSAgent.getActiveNetworkCarrier());
            nBSTransactionState.setHttpLibType(HttpLibType.OkHttp);
            if (url != null) {
                m9838a(nBSTransactionState, request);
            }
        }
    }

    public void responseFinished(Response response, NBSTransactionState nBSTransactionState) {
        NBSAndroidAgentImpl impl;
        if (isNetworkEnter()) {
            if (response == null) {
                log.mo10115e("okhttp2.0 ->CallBack.onResponse(response) response is null ");
                return;
            }
            String header = C6638h.m8963w().m9036aj() ? response.header("X-Tingyun-Tx-Data") : "";
            if (Harvest.isCdn_enabled() && (impl = NBSAgent.getImpl()) != null) {
                String cdnHeaderName = impl.m9150m().getCdnHeaderName();
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10122a("cdnHeaderName  key : " + cdnHeaderName);
                if (cdnHeaderName != null && !cdnHeaderName.isEmpty()) {
                    String header2 = response.header(cdnHeaderName);
                    nBSTransactionState.setCdnVendorName(header2 == null ? "" : header2);
                    InterfaceC6393e interfaceC6393e2 = log;
                    interfaceC6393e2.mo10122a("cdnHeaderName  value : " + header2);
                }
            }
            int code = response.code();
            ResponseBody body = response.body();
            long j = 0;
            if (body != null) {
                try {
                    j = body.contentLength();
                } catch (Throwable unused) {
                }
            }
            inspectAndInstrumentResponse(nBSTransactionState, header, (int) j, code);
            C6396h.m10126p("okhttp2  setAppDataNew  start ....");
            nBSTransactionState.setAppDataNew(response.header("X-Tingyun-Data"));
            m9837a(nBSTransactionState, response);
            m9835c(nBSTransactionState, response);
        }
    }

    /* renamed from: a */
    private static void m9837a(NBSTransactionState nBSTransactionState, Response response) {
        try {
            nBSTransactionState.setContentType(C6653u.m8694i(response.header("Content-Type")));
        } catch (Exception unused) {
            log.mo10116d("NBSOkHttp2TransactionStateUtil content-type has an error ");
        }
    }

    /* renamed from: b */
    private static void m9836b(NBSTransactionState nBSTransactionState, Response response) {
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
    private static void m9835c(NBSTransactionState nBSTransactionState, Response response) {
        NBSAndroidAgentImpl impl = NBSAgent.getImpl();
        if (impl == null || impl.m9150m() == null) {
            return;
        }
        m9836b(nBSTransactionState, response);
        nBSTransactionState.setEndState();
        C6648q.f17235e.add(nBSTransactionState);
    }

    public boolean isNetworkEnter() {
        return Harvest.isHttp_network_enabled();
    }

    /* renamed from: a */
    private static void m9838a(NBSTransactionState nBSTransactionState, final Request request) {
        NBSTransactionStateUtil.processParamsFilter(nBSTransactionState, nBSTransactionState.getUrlParams());
        NBSTransactionStateUtil.processHeaderParam(nBSTransactionState.getUrl(), new NBSNetworkProcessHeader() { // from class: com.networkbench.agent.impl.instrumentation.okhttp2.NBSOKHttp2Reporter.1
            @Override // com.networkbench.agent.impl.instrumentation.NBSNetworkProcessHeader
            public String getFilterHeader(String str) {
                Request request2 = request;
                return (request2 == null || str == null) ? "" : request2.header(str);
            }
        }, nBSTransactionState);
    }
}
