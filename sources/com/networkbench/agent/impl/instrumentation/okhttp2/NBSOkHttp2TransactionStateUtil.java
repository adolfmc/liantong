package com.networkbench.agent.impl.instrumentation.okhttp2;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.instrumentation.NBSNetworkProcessHeader;
import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.instrumentation.NBSTransactionStateUtil;
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
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSOkHttp2TransactionStateUtil extends NBSTransactionStateUtil {
    private static final String NO_BODY_TEXT = "Response BODY not found.";
    private static final InterfaceC6393e log = C6394f.m10150a();

    @Override // com.networkbench.agent.impl.instrumentation.NBSTransactionStateUtil
    @Deprecated
    /* renamed from: a */
    public void mo9828a() {
    }

    public static void inspectAndInstrument(NBSTransactionState nBSTransactionState, Request request) {
        String str;
        String urlString = request.urlString();
        if (urlString == null || !urlString.contains("?")) {
            str = null;
        } else {
            int indexOf = urlString.indexOf("?");
            String substring = urlString.substring(0, indexOf);
            str = urlString.substring(indexOf + 1);
            urlString = substring;
        }
        nBSTransactionState.setUrl(urlString);
        nBSTransactionState.setUrlParams(str);
        nBSTransactionState.setAllGetRequestParams(str);
        NBSTransactionStateUtil.setRequestMethod(nBSTransactionState, request.method());
        nBSTransactionState.setCarrier(NBSAgent.getActiveNetworkCarrier());
        nBSTransactionState.setHttpLibType(HttpLibType.OkHttp);
        if (urlString != null) {
            m9827a(nBSTransactionState, request);
        }
    }

    /* renamed from: a */
    private static void m9827a(NBSTransactionState nBSTransactionState, final Request request) {
        NBSTransactionStateUtil.processParamsFilter(nBSTransactionState, nBSTransactionState.getUrlParams());
        NBSTransactionStateUtil.processHeaderParam(nBSTransactionState.getUrl(), new NBSNetworkProcessHeader() { // from class: com.networkbench.agent.impl.instrumentation.okhttp2.NBSOkHttp2TransactionStateUtil.1
            @Override // com.networkbench.agent.impl.instrumentation.NBSNetworkProcessHeader
            public String getFilterHeader(String str) {
                Request request2 = request;
                return (request2 == null || str == null) ? "" : request2.header(str);
            }
        }, nBSTransactionState);
    }

    public static void inspectAndInstrumentResponse(NBSTransactionState nBSTransactionState, Response response) {
        NBSAndroidAgentImpl impl;
        if (response == null) {
            log.mo10115e("okhttp2.0 ->CallBack.onResponse(response) response is null ");
            return;
        }
        if (Harvest.isCdn_enabled() && (impl = NBSAgent.getImpl()) != null) {
            String cdnHeaderName = impl.m9150m().getCdnHeaderName();
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("cdnHeaderName  key : " + cdnHeaderName);
            if (cdnHeaderName != null && !cdnHeaderName.isEmpty()) {
                String header = response.header(cdnHeaderName);
                nBSTransactionState.setCdnVendorName(header == null ? "" : header);
                InterfaceC6393e interfaceC6393e2 = log;
                interfaceC6393e2.mo10122a("cdnHeaderName  value : " + header);
            }
        }
        try {
            inspectAndInstrumentResponse(nBSTransactionState, C6638h.m8963w().m9036aj() ? response.header("X-Tingyun-Tx-Data") : "", (int) response.body().contentLength(), response.code());
            nBSTransactionState.setAppDataNew(response.header("X-Tingyun-Data"));
            C6396h.m10126p("okhttp2  setAppDataNew  start ....");
            m9826a(nBSTransactionState, response);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    private static void m9826a(NBSTransactionState nBSTransactionState, Response response) {
        try {
            nBSTransactionState.setContentType(C6653u.m8694i(response.header("Content-Type")));
            nBSTransactionState.responseHeaderParam = C6653u.m8705e(NBSOkHttp2Instrumentation.getHeaderForOkhttp2(response.headers()));
        } catch (Exception unused) {
            log.mo10116d("NBSOkHttp2TransactionStateUtil content-type has an error ");
        }
        if (nBSTransactionState.end() == null) {
            return;
        }
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
        C6648q.m8781a(new C6412c(nBSTransactionState));
    }

    public static void inspectAndInstrument(NBSTransactionState nBSTransactionState, String str, String str2) {
        nBSTransactionState.setUrl(str);
        nBSTransactionState.setCarrier(NBSAgent.getActiveNetworkCarrier());
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
}
