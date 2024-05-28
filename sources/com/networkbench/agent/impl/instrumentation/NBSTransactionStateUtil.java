package com.networkbench.agent.impl.instrumentation;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.harvest.RequestMethodType;
import com.networkbench.agent.impl.p243c.C6305i;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.NBSAndroidAgentImpl;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.SSLException;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSTransactionStateUtil {
    private static final InterfaceC6393e log = C6394f.m10150a();

    @Deprecated
    /* renamed from: a */
    protected void mo9828a() {
    }

    public static void setUrlAndCarrier(NBSTransactionState nBSTransactionState, HttpURLConnection httpURLConnection) {
        try {
            String url = httpURLConnection.getURL().toString();
            String str = null;
            if (url.contains("?")) {
                int indexOf = url.indexOf("?");
                String substring = url.substring(0, indexOf);
                str = url.substring(indexOf + 1);
                url = substring;
            }
            nBSTransactionState.setUrl(url);
            nBSTransactionState.setUrlParams(str);
            nBSTransactionState.setAllGetRequestParams(str);
            nBSTransactionState.setAllGetRequestParams(str);
            nBSTransactionState.setCarrier(NBSAgent.getActiveNetworkCarrier());
        } catch (Throwable unused) {
        }
    }

    public static void setCrossProcessHeader(NBSTransactionState nBSTransactionState, HttpURLConnection httpURLConnection) {
        try {
            if (C6638h.m8963w().m9036aj()) {
                nBSTransactionState.setRequestHeaderIdValue(httpURLConnection.getRequestProperty(C6638h.m8963w().f17178d));
            }
            setApmsHeader(nBSTransactionState, httpURLConnection);
            String m9034al = C6638h.m8963w().m9034al();
            if (!TextUtils.isEmpty(m9034al) && C6638h.m8963w().m9036aj()) {
                httpURLConnection.setRequestProperty("X-Tingyun-Id", C6638h.m9050a(m9034al, C6638h.m9031ao()));
            }
            if (C6638h.m8963w().m9032an()) {
                httpURLConnection.setRequestProperty("X-Tingyun", C6638h.m8963w().m9033am());
            }
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("NBSTransactionStateUtil  setCrossProcessHeader() has an error :" + e);
        }
    }

    private static void setApmsHeader(NBSTransactionState nBSTransactionState, HttpURLConnection httpURLConnection) {
        try {
            if (C6638h.m8963w().m9027b()) {
                String replace = UUID.randomUUID().toString().replace("-", "");
                C6396h.m10126p("HttpURLConnection setCrossProcessHeader uuid :" + replace);
                JSONArray jSONArray = new JSONArray(C6638h.m8963w().m9060a().toString());
                for (int i = 0; i < chekSize(jSONArray.length()); i++) {
                    String string = jSONArray.getString(i);
                    C6396h.m10126p("HttpURLConnection setCrossProcessHeader apms  :" + string);
                    if (TextUtils.isEmpty(httpURLConnection.getRequestProperty(string))) {
                        httpURLConnection.addRequestProperty(string, replace);
                        nBSTransactionState.setUUid(replace);
                        C6396h.m10126p("HttpURLConnection setCrossProcessHeader apms  setUUid:" + replace);
                    } else {
                        nBSTransactionState.getApmsList().add(string);
                        C6396h.m10126p("HttpURLConnection setCrossProcessHeader  apmsList  :" + string);
                    }
                }
            }
        } catch (Throwable unused) {
            C6396h.m10126p(" HttpURLConnection apms数据格式解析错误!!!");
        }
    }

    private static int chekSize(int i) {
        return i < 10 ? i : C6638h.f17106c;
    }

    public static void setRequestMethod(NBSTransactionState nBSTransactionState, String str) {
        nBSTransactionState.setRequestMethod(setRequestMethod(str));
    }

    public static RequestMethodType setRequestMethod(String str) {
        if (TextUtils.isEmpty(str)) {
            return RequestMethodType.GET;
        }
        if (str.toUpperCase().equals("OPTIONS")) {
            return RequestMethodType.OPTIONS;
        }
        if (str.toUpperCase().equals("GET")) {
            return RequestMethodType.GET;
        }
        if (str.toUpperCase().equals("HEAD")) {
            return RequestMethodType.HEAD;
        }
        if (str.toUpperCase().equals("POST")) {
            return RequestMethodType.POST;
        }
        if (str.toUpperCase().equals("PUT")) {
            return RequestMethodType.PUT;
        }
        if (str.toUpperCase().equals("DELETE")) {
            return RequestMethodType.DELETE;
        }
        if (str.toUpperCase().equals("TRACE")) {
            return RequestMethodType.TRACE;
        }
        if (str.toUpperCase().equals("CONNECT")) {
            return RequestMethodType.CONNECT;
        }
        return RequestMethodType.GET;
    }

    public static void processUrlParams(NBSTransactionState nBSTransactionState, final HttpURLConnection httpURLConnection) {
        processParamsFilter(nBSTransactionState, nBSTransactionState.getUrlParams());
        processHeaderParam(nBSTransactionState.getUrl(), new NBSNetworkProcessHeader() { // from class: com.networkbench.agent.impl.instrumentation.NBSTransactionStateUtil.1
            @Override // com.networkbench.agent.impl.instrumentation.NBSNetworkProcessHeader
            public String getFilterHeader(String str) {
                return httpURLConnection.getRequestProperty(str);
            }
        }, nBSTransactionState);
    }

    public static void inspectAndInstrumentResponse(NBSTransactionState nBSTransactionState, HttpURLConnection httpURLConnection) {
        int responseCode;
        NBSAndroidAgentImpl impl;
        try {
            if (Harvest.isHttp_network_enabled()) {
                if (Harvest.isCdn_enabled() && (impl = NBSAgent.getImpl()) != null) {
                    String cdnHeaderName = impl.m9150m().getCdnHeaderName();
                    InterfaceC6393e interfaceC6393e = log;
                    interfaceC6393e.mo10122a("cdnHeaderName key : " + cdnHeaderName);
                    if (cdnHeaderName != null && !"".equals(cdnHeaderName)) {
                        nBSTransactionState.setCdnVendorName(httpURLConnection.getHeaderField(cdnHeaderName) == null ? "" : httpURLConnection.getHeaderField(cdnHeaderName));
                        InterfaceC6393e interfaceC6393e2 = log;
                        interfaceC6393e2.mo10122a("cdnHeaderName value :" + httpURLConnection.getHeaderField(cdnHeaderName));
                    }
                }
                String headerField = C6638h.m8963w().m9036aj() ? httpURLConnection.getHeaderField("X-Tingyun-Tx-Data") : "";
                if (headerField != null && !"".equals(headerField) && C6638h.m8963w().m9036aj()) {
                    InterfaceC6393e interfaceC6393e3 = log;
                    interfaceC6393e3.mo10117c("header:" + headerField);
                    nBSTransactionState.setAppData(headerField);
                }
                C6396h.m10126p("HttpURLConnection  setAppDataNew  start ....");
                nBSTransactionState.setAppDataNew(httpURLConnection.getHeaderField("X-Tingyun-Data"));
                processUrlParams(nBSTransactionState, httpURLConnection);
                nBSTransactionState.getStatusCode();
                try {
                    responseCode = httpURLConnection.getResponseCode();
                } catch (IOException | NullPointerException | Exception unused) {
                }
                nBSTransactionState.setStatusCode(responseCode);
            }
        } catch (Exception e) {
            log.mo10120a("Failed to retrieve response code due to underlying (Harmony?) NPE", e);
        }
    }

    public static C6305i.C6308c getAvalidUrlParam(ConcurrentHashMap<HarvestConfiguration.UrlFilter, C6305i.C6308c> concurrentHashMap, String str) throws NullPointerException {
        for (Map.Entry<HarvestConfiguration.UrlFilter, C6305i.C6308c> entry : concurrentHashMap.entrySet()) {
            if (entry.getKey().isAvalidUrl(str)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static String processParamsFilter(NBSTransactionState nBSTransactionState, String str) {
        String[] strArr;
        String[] split;
        String url = nBSTransactionState.getUrl();
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(str)) {
            nBSTransactionState.setUrlParams("");
            return "";
        }
        log.mo10122a("processParam filter url:" + url + ", urlParam:" + str);
        C6305i.C6308c avalidUrlParam = getAvalidUrlParam(HarvestConfiguration.getDefaultHarvestConfiguration().getUrlParamArray(), url);
        if (avalidUrlParam == null) {
            log.mo10122a("not find url param");
            nBSTransactionState.setUrlParams("");
            return "";
        }
        String[] split2 = str.split("&");
        HashMap hashMap = new HashMap();
        for (String str2 : split2) {
            if (str2 != null && (split = str2.split("=")) != null && split.length == 2) {
                hashMap.put(split[0], split[1]);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (avalidUrlParam.f15827a != null && avalidUrlParam.f15827a.length > 0) {
            for (String str3 : avalidUrlParam.f15827a) {
                String str4 = (String) hashMap.get(str3);
                if (!TextUtils.isEmpty(str4)) {
                    sb.append(str3);
                    sb.append("=");
                    sb.append(str4);
                    sb.append("&");
                }
            }
        }
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(sb2)) {
            sb2 = sb2.substring(0, sb2.length() - 1);
        }
        log.mo10122a(" find url param : " + sb2);
        nBSTransactionState.setUrlParams(sb2);
        return sb2;
    }

    public static void processHeaderParam(String str, NBSNetworkProcessHeader nBSNetworkProcessHeader, NBSTransactionState nBSTransactionState) {
        C6305i.C6308c avalidUrlParam;
        if (TextUtils.isEmpty(str) || nBSTransactionState == null || nBSNetworkProcessHeader == null || (avalidUrlParam = getAvalidUrlParam(HarvestConfiguration.getDefaultHarvestConfiguration().getUrlParamArray(), str)) == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = avalidUrlParam.f15829c;
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length; i++) {
                String filterHeader = nBSNetworkProcessHeader.getFilterHeader(strArr[i]);
                if (!TextUtils.isEmpty(filterHeader)) {
                    sb.append(strArr[i]);
                    sb.append('=');
                    sb.append(filterHeader);
                    sb.append('&');
                }
            }
        }
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(sb2)) {
            sb2 = sb2.substring(0, sb.length() - 1);
        }
        nBSTransactionState.setFormattedUrlParams(sb2);
    }

    public static void setErrorCodeFromException(NBSTransactionState nBSTransactionState, Exception exc) {
        if (exc instanceof IOException) {
            if (isSocketECONNRESET(exc)) {
                nBSTransactionState.setErrorCode(411, exc.toString());
                nBSTransactionState.setStatusCode(411);
                return;
            }
            String message = exc.getMessage();
            if (exc != null && message != null && message.contains("ftruncate failed: ENOENT (No such file or directory)")) {
                nBSTransactionState.setErrorCode(517, exc.toString());
                nBSTransactionState.setStatusCode(517);
                return;
            }
        }
        if (exc instanceof UnknownHostException) {
            nBSTransactionState.setErrorCode(901, exc.toString());
            nBSTransactionState.setStatusCode(901);
        } else if (exc instanceof SocketTimeoutException) {
            nBSTransactionState.setErrorCode(903, exc.toString());
            nBSTransactionState.setStatusCode(903);
        } else if (exc instanceof ConnectException) {
            nBSTransactionState.setErrorCode(902, exc.toString());
            nBSTransactionState.setStatusCode(902);
        } else if (exc instanceof MalformedURLException) {
            nBSTransactionState.setErrorCode(900, exc.toString());
            nBSTransactionState.setStatusCode(900);
        } else if (exc instanceof SSLException) {
            nBSTransactionState.setErrorCode(908, exc.toString());
            nBSTransactionState.setStatusCode(908);
        } else {
            nBSTransactionState.setErrorCode(-1, exc.toString());
            nBSTransactionState.setStatusCode(-1);
        }
    }

    public static boolean isSocketECONNRESET(Exception exc) {
        if (exc != null) {
            try {
                if (exc instanceof SocketException) {
                    return exc.getMessage().contains("recvfrom failed: ECONNRESET (Connection reset by peer)");
                }
                return false;
            } catch (Exception e) {
                log.mo10121a("isSocketECONNRESET error", e);
                return false;
            }
        }
        return false;
    }
}
