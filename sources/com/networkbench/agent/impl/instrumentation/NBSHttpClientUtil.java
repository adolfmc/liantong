package com.networkbench.agent.impl.instrumentation;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.harvest.RequestMethodType;
import com.networkbench.agent.impl.instrumentation.httpclient.NBSContentBufferingResponseEntityImpl;
import com.networkbench.agent.impl.instrumentation.httpclient.NBSHttpRequestEntityImpl;
import com.networkbench.agent.impl.instrumentation.p263io.NBSCountingInputStream;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6648q;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import javax.net.ssl.SSLException;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSHttpClientUtil {
    private static final InterfaceC6393e log = C6394f.m10150a();

    public static HttpRequest setHttpClientCrossProcessHeader(HttpRequest httpRequest, NBSTransactionState nBSTransactionState) {
        try {
            if (Harvest.isHttp_network_enabled() && httpRequest != null) {
                Header firstHeader = httpRequest.getFirstHeader(C6638h.m8963w().f17178d);
                if (firstHeader != null && C6638h.m8963w().m9036aj()) {
                    nBSTransactionState.setRequestHeaderIdValue(firstHeader.getValue());
                }
                setApmsHeader(httpRequest, nBSTransactionState);
                String m9034al = C6638h.m8963w().m9034al();
                if (!TextUtils.isEmpty(m9034al) && C6638h.m8963w().m9036aj()) {
                    httpRequest.setHeader("X-Tingyun-Id", C6638h.m9050a(m9034al, C6638h.m9031ao()));
                }
                if (C6638h.m8963w().m9032an()) {
                    httpRequest.setHeader("X-Tingyun", C6638h.m8963w().m9033am());
                }
            }
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("NBSTransactionStateUtil setHttpClientCrossProcessHeader has an error " + e);
        }
        return httpRequest;
    }

    private static void setApmsHeader(HttpRequest httpRequest, NBSTransactionState nBSTransactionState) {
        try {
            if (C6638h.m8963w().m9027b()) {
                String replace = UUID.randomUUID().toString().replace("-", "");
                C6396h.m10126p("HttpRequest setCrossProcessHeader uuid :" + replace);
                JSONArray jSONArray = new JSONArray(C6638h.m8963w().m9060a().toString());
                for (int i = 0; i < chekSize(jSONArray.length()); i++) {
                    String string = jSONArray.getString(i);
                    C6396h.m10126p("HttpRequest setCrossProcessHeader apms  :" + string);
                    if (httpRequest.getFirstHeader(string) == null) {
                        httpRequest.setHeader(string, replace);
                        nBSTransactionState.setUUid(replace);
                    } else {
                        nBSTransactionState.getApmsList().add(string);
                        C6396h.m10126p("okhttp2 setCrossProcessHeader  apmsList  :" + string);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            C6396h.m10126p(" HttpRequest apms数据格式解析错误!!!");
        }
    }

    private static int chekSize(int i) {
        return i < 10 ? i : C6638h.f17106c;
    }

    public static void getHttpClientRequest(NBSTransactionState nBSTransactionState, HttpRequest httpRequest) {
        if (httpRequest instanceof HttpOptions) {
            nBSTransactionState.setRequestMethod(RequestMethodType.OPTIONS);
        } else if (httpRequest instanceof HttpGet) {
            nBSTransactionState.setRequestMethod(RequestMethodType.GET);
        } else if (httpRequest instanceof HttpHead) {
            nBSTransactionState.setRequestMethod(RequestMethodType.HEAD);
        } else if (httpRequest instanceof HttpPost) {
            nBSTransactionState.setRequestMethod(RequestMethodType.POST);
        } else if (httpRequest instanceof HttpPut) {
            nBSTransactionState.setRequestMethod(RequestMethodType.PUT);
        } else if (httpRequest instanceof HttpDelete) {
            nBSTransactionState.setRequestMethod(RequestMethodType.DELETE);
        } else if (httpRequest instanceof HttpTrace) {
            nBSTransactionState.setRequestMethod(RequestMethodType.TRACE);
        } else {
            nBSTransactionState.setRequestMethod(RequestMethodType.GET);
        }
    }

    private static void processParamsAndHeader(NBSTransactionState nBSTransactionState, final HttpRequest httpRequest, String str) {
        getHttpClientRequest(nBSTransactionState, httpRequest);
        NBSTransactionStateUtil.processParamsFilter(nBSTransactionState, nBSTransactionState.getUrlParams());
        NBSTransactionStateUtil.processHeaderParam(nBSTransactionState.getUrl(), new NBSNetworkProcessHeader() { // from class: com.networkbench.agent.impl.instrumentation.NBSHttpClientUtil.1
            @Override // com.networkbench.agent.impl.instrumentation.NBSNetworkProcessHeader
            public String getFilterHeader(String str2) {
                Header firstHeader = HttpRequest.this.getFirstHeader(str2);
                return firstHeader != null ? firstHeader.getValue() : "";
            }
        }, nBSTransactionState);
    }

    public static HttpRequest inspectAndInstrument(NBSTransactionState nBSTransactionState, HttpHost httpHost, HttpRequest httpRequest) {
        String str;
        if (httpRequest == null) {
            return httpRequest;
        }
        RequestLine requestLine = httpRequest.getRequestLine();
        String str2 = null;
        if (requestLine != null) {
            str = requestLine.getUri();
            if (str.contains("?")) {
                int indexOf = str.indexOf("?");
                String substring = str.substring(0, indexOf);
                str2 = str.substring(indexOf + 1);
                str = substring;
            }
        } else {
            str = null;
        }
        nBSTransactionState.setUrl(str);
        nBSTransactionState.setUrlParams(str2);
        nBSTransactionState.setAllGetRequestParams(str2);
        nBSTransactionState.setHttpLibType(HttpLibType.HttpClient);
        processParamsAndHeader(nBSTransactionState, httpRequest, str2);
        nBSTransactionState.setCarrier(NBSAgent.getActiveNetworkCarrier());
        nBSTransactionState.requestHeaderParam = getHeader(httpRequest.getAllHeaders());
        wrapRequestEntity(nBSTransactionState, httpRequest);
        return httpRequest;
    }

    private static HashMap<String, String> getHeader(Header[] headerArr) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (headerArr != null && headerArr.length > 0) {
            for (Header header : headerArr) {
                hashMap.put(header.getName().toLowerCase(), header.getValue());
            }
        }
        return hashMap;
    }

    private static void wrapRequestEntity(NBSTransactionState nBSTransactionState, HttpRequest httpRequest) {
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpRequest;
            if (httpEntityEnclosingRequest.getEntity() != null) {
                httpEntityEnclosingRequest.setEntity(new NBSHttpRequestEntityImpl(httpEntityEnclosingRequest.getEntity(), nBSTransactionState));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e5 A[Catch: Exception -> 0x017c, TryCatch #3 {Exception -> 0x017c, blocks: (B:2:0x0000, B:4:0x0020, B:6:0x002f, B:8:0x0032, B:10:0x0040, B:22:0x0089, B:24:0x008f, B:26:0x0095, B:28:0x00a3, B:30:0x00a6, B:34:0x00eb, B:33:0x00e5, B:35:0x00ee, B:37:0x00f6, B:39:0x00f9, B:40:0x0106, B:42:0x0110, B:44:0x0113, B:46:0x0119, B:47:0x0121, B:49:0x0125, B:50:0x012e, B:53:0x0138, B:54:0x0153, B:56:0x0159, B:58:0x0161, B:59:0x016a, B:60:0x0173, B:12:0x0066, B:14:0x0073, B:17:0x0077, B:18:0x0081), top: B:68:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0159 A[Catch: Exception -> 0x017c, TryCatch #3 {Exception -> 0x017c, blocks: (B:2:0x0000, B:4:0x0020, B:6:0x002f, B:8:0x0032, B:10:0x0040, B:22:0x0089, B:24:0x008f, B:26:0x0095, B:28:0x00a3, B:30:0x00a6, B:34:0x00eb, B:33:0x00e5, B:35:0x00ee, B:37:0x00f6, B:39:0x00f9, B:40:0x0106, B:42:0x0110, B:44:0x0113, B:46:0x0119, B:47:0x0121, B:49:0x0125, B:50:0x012e, B:53:0x0138, B:54:0x0153, B:56:0x0159, B:58:0x0161, B:59:0x016a, B:60:0x0173, B:12:0x0066, B:14:0x0073, B:17:0x0077, B:18:0x0081), top: B:68:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0173 A[Catch: Exception -> 0x017c, TRY_LEAVE, TryCatch #3 {Exception -> 0x017c, blocks: (B:2:0x0000, B:4:0x0020, B:6:0x002f, B:8:0x0032, B:10:0x0040, B:22:0x0089, B:24:0x008f, B:26:0x0095, B:28:0x00a3, B:30:0x00a6, B:34:0x00eb, B:33:0x00e5, B:35:0x00ee, B:37:0x00f6, B:39:0x00f9, B:40:0x0106, B:42:0x0110, B:44:0x0113, B:46:0x0119, B:47:0x0121, B:49:0x0125, B:50:0x012e, B:53:0x0138, B:54:0x0153, B:56:0x0159, B:58:0x0161, B:59:0x016a, B:60:0x0173, B:12:0x0066, B:14:0x0073, B:17:0x0077, B:18:0x0081), top: B:68:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.apache.http.HttpResponse inspectAndInstrument(com.networkbench.agent.impl.instrumentation.NBSTransactionState r6, org.apache.http.HttpResponse r7) {
        /*
            Method dump skipped, instructions count: 404
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.instrumentation.NBSHttpClientUtil.inspectAndInstrument(com.networkbench.agent.impl.instrumentation.NBSTransactionState, org.apache.http.HttpResponse):org.apache.http.HttpResponse");
    }

    private static void addTransactionAndErrorData(NBSTransactionState nBSTransactionState, HttpResponse httpResponse) {
        if (Harvest.isHttp_network_enabled()) {
            try {
                if (nBSTransactionState.end() == null) {
                    log.mo10115e("HttpResponseEntityWrapperImpl transactionData is null!");
                    return;
                }
                if (nBSTransactionState.isError()) {
                    StringBuilder sb = new StringBuilder();
                    if (httpResponse != null) {
                        try {
                            if (!(httpResponse.getEntity() instanceof NBSHttpRequestEntityImpl)) {
                                httpResponse.setEntity(new NBSContentBufferingResponseEntityImpl(httpResponse.getEntity()));
                            }
                            InputStream content = httpResponse.getEntity().getContent();
                            if (content instanceof NBSCountingInputStream) {
                                sb.append(((NBSCountingInputStream) content).getBufferAsString());
                            } else {
                                log.mo10116d("Unable to wrap content stream for entity");
                            }
                        } catch (IOException e) {
                            log.mo10116d(e.toString());
                        } catch (IllegalStateException e2) {
                            log.mo10116d(e2.toString());
                        }
                    }
                    Map<String, Object> resolvingResponseHeader = resolvingResponseHeader(httpResponse);
                    resolvingResponseHeader.put("Content-Length", Long.valueOf(nBSTransactionState.getBytesReceived()));
                    InterfaceC6393e interfaceC6393e = log;
                    interfaceC6393e.mo10117c("response body content:" + sb.toString());
                    String exception = nBSTransactionState.getException() != null ? nBSTransactionState.getException() : "";
                    InterfaceC6393e interfaceC6393e2 = log;
                    interfaceC6393e2.mo10117c("error message:" + exception);
                    nBSTransactionState.setErrorDataInfo(sb.toString(), resolvingResponseHeader, exception);
                }
                C6648q.m8781a(new C6412c(nBSTransactionState));
            } catch (Exception e3) {
                log.mo10121a("addTransactionAndErrorData", e3);
            }
        }
    }

    public static HttpUriRequest inspectAndInstrument(NBSTransactionState nBSTransactionState, HttpUriRequest httpUriRequest) {
        String uri;
        if (httpUriRequest == null) {
            return httpUriRequest;
        }
        RequestLine requestLine = httpUriRequest.getRequestLine();
        String str = null;
        if (requestLine != null) {
            uri = requestLine.getUri();
            if (uri.contains("?")) {
                int indexOf = uri.indexOf("?");
                String substring = uri.substring(0, indexOf);
                str = uri.substring(indexOf + 1);
                uri = substring;
            }
        } else {
            uri = httpUriRequest.getURI().toString();
        }
        nBSTransactionState.setUrl(uri);
        nBSTransactionState.setCarrier(NBSAgent.getActiveNetworkCarrier());
        nBSTransactionState.setUrlParams(str);
        nBSTransactionState.setAllGetRequestParams(str);
        nBSTransactionState.setHttpLibType(HttpLibType.HttpClient);
        nBSTransactionState.requestHeaderParam = getHeader(httpUriRequest.getAllHeaders());
        processParamsAndHeader(nBSTransactionState, httpUriRequest, str);
        wrapRequestEntity(nBSTransactionState, httpUriRequest);
        return httpUriRequest;
    }

    public static void setErrorCodeFromException(NBSTransactionState nBSTransactionState, Exception exc) {
        if (exc instanceof IOException) {
            if (NBSTransactionStateUtil.isSocketECONNRESET(exc)) {
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
        } else if ((exc instanceof SocketTimeoutException) || (exc instanceof ConnectTimeoutException)) {
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
        } else if (exc instanceof HttpResponseException) {
            nBSTransactionState.setStatusCode(((HttpResponseException) exc).getStatusCode());
        } else if (exc instanceof ClientProtocolException) {
            nBSTransactionState.setErrorCode(904, exc.toString());
            nBSTransactionState.setStatusCode(904);
        } else if (exc instanceof AuthenticationException) {
            nBSTransactionState.setErrorCode(907, exc.toString());
            nBSTransactionState.setStatusCode(907);
        } else {
            nBSTransactionState.setErrorCode(-1, exc.toString());
            nBSTransactionState.setStatusCode(-1);
        }
    }

    public static Map<String, Object> resolvingResponseHeader(HttpResponse httpResponse) {
        Header[] allHeaders;
        TreeMap treeMap = new TreeMap();
        if (httpResponse != null && (allHeaders = httpResponse.getAllHeaders()) != null) {
            for (Header header : allHeaders) {
                treeMap.put(header.getName(), header.getValue());
            }
        }
        return treeMap;
    }
}
