package com.networkbench.agent.impl.instrumentation;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.instrumentation.httpclient.NBSResponseHandlerImpl;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6640i;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6648q;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSInstrumentationHttpClient {
    private static final InterfaceC6393e log = C6394f.m10150a();

    @NBSReplaceCallSite(isStatic = true)
    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        if (!Harvest.isHttp_network_enabled()) {
            return httpClient.execute(httpHost, httpRequest, httpContext);
        }
        C6396h.m10130l("httpClient execute gather  begin !!");
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            httpRequest = dispatchHttpClientRequest(httpRequest, nBSTransactionState);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("error set transaction e:" + e.getMessage());
        }
        try {
            return m9885_(httpClient.execute(httpHost, m9886_(httpHost, NBSHttpClientUtil.setHttpClientCrossProcessHeader(httpRequest, nBSTransactionState), nBSTransactionState), httpContext), nBSTransactionState);
        } catch (IOException e2) {
            httpClientError(nBSTransactionState, e2);
            throw e2;
        }
    }

    @NBSReplaceCallSite
    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        if (!Harvest.isHttp_network_enabled()) {
            return (T) httpClient.execute(httpHost, httpRequest, responseHandler, httpContext);
        }
        C6396h.m10130l("httpClient execute gather  begin !!");
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            httpRequest = dispatchHttpClientRequest(httpRequest, nBSTransactionState);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("error set transaction e:" + e.getMessage());
        }
        try {
            return (T) httpClient.execute(httpHost, m9886_(httpHost, NBSHttpClientUtil.setHttpClientCrossProcessHeader(httpRequest, nBSTransactionState), nBSTransactionState), m9884_(responseHandler, nBSTransactionState), httpContext);
        } catch (ClientProtocolException e2) {
            httpClientError(nBSTransactionState, e2);
            throw e2;
        } catch (IOException e3) {
            httpClientError(nBSTransactionState, e3);
            throw e3;
        }
    }

    @NBSReplaceCallSite
    public static <T> T execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        if (!Harvest.isHttp_network_enabled()) {
            return (T) httpClient.execute(httpHost, httpRequest, responseHandler);
        }
        C6396h.m10130l("httpClient execute gather  begin !!");
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            httpRequest = dispatchHttpClientRequest(httpRequest, nBSTransactionState);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("error set transaction e:" + e.getMessage());
        }
        try {
            return (T) httpClient.execute(httpHost, m9886_(httpHost, NBSHttpClientUtil.setHttpClientCrossProcessHeader(httpRequest, nBSTransactionState), nBSTransactionState), m9884_(responseHandler, nBSTransactionState));
        } catch (ClientProtocolException e2) {
            httpClientError(nBSTransactionState, e2);
            throw e2;
        } catch (IOException e3) {
            httpClientError(nBSTransactionState, e3);
            throw e3;
        }
    }

    @NBSReplaceCallSite
    public static HttpResponse execute(HttpClient httpClient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        if (!Harvest.isHttp_network_enabled()) {
            return httpClient.execute(httpHost, httpRequest);
        }
        C6396h.m10130l("httpClient execute gather  begin !!");
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            httpRequest = dispatchHttpClientRequest(httpRequest, nBSTransactionState);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("error set transaction e:" + e.getMessage());
        }
        try {
            return m9885_(httpClient.execute(httpHost, m9886_(httpHost, NBSHttpClientUtil.setHttpClientCrossProcessHeader(httpRequest, nBSTransactionState), nBSTransactionState)), nBSTransactionState);
        } catch (IOException e2) {
            httpClientError(nBSTransactionState, e2);
            throw e2;
        }
    }

    @NBSReplaceCallSite
    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        if (!Harvest.isHttp_network_enabled()) {
            return httpClient.execute(httpUriRequest, httpContext);
        }
        C6396h.m10130l("httpClient execute gather  begin !!");
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            httpUriRequest = (HttpUriRequest) dispatchHttpClientRequest(httpUriRequest, nBSTransactionState);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("error set transaction e:" + e.getMessage());
        }
        try {
            return m9885_(httpClient.execute(m9883_((HttpUriRequest) NBSHttpClientUtil.setHttpClientCrossProcessHeader(httpUriRequest, nBSTransactionState), nBSTransactionState), httpContext), nBSTransactionState);
        } catch (IOException e2) {
            httpClientError(nBSTransactionState, e2);
            throw e2;
        }
    }

    @NBSReplaceCallSite
    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException, ClientProtocolException {
        if (!Harvest.isHttp_network_enabled()) {
            return (T) httpClient.execute(httpUriRequest, responseHandler, httpContext);
        }
        C6396h.m10130l("httpClient execute gather  begin !!");
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            httpUriRequest = (HttpUriRequest) dispatchHttpClientRequest(httpUriRequest, nBSTransactionState);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("error set transaction e:" + e.getMessage());
        }
        try {
            return (T) httpClient.execute(m9883_((HttpUriRequest) NBSHttpClientUtil.setHttpClientCrossProcessHeader(httpUriRequest, nBSTransactionState), nBSTransactionState), m9884_(responseHandler, nBSTransactionState), httpContext);
        } catch (ClientProtocolException e2) {
            httpClientError(nBSTransactionState, e2);
            throw e2;
        } catch (IOException e3) {
            httpClientError(nBSTransactionState, e3);
            throw e3;
        }
    }

    @NBSReplaceCallSite
    public static <T> T execute(HttpClient httpClient, HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
        if (!Harvest.isHttp_network_enabled()) {
            return (T) httpClient.execute(httpUriRequest, responseHandler);
        }
        C6396h.m10130l("httpClient execute gather  begin !!");
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            httpUriRequest = (HttpUriRequest) dispatchHttpClientRequest(httpUriRequest, nBSTransactionState);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("error set transaction e:" + e.getMessage());
        }
        try {
            return (T) httpClient.execute(m9883_((HttpUriRequest) NBSHttpClientUtil.setHttpClientCrossProcessHeader(httpUriRequest, nBSTransactionState), nBSTransactionState), m9884_(responseHandler, nBSTransactionState));
        } catch (ClientProtocolException e2) {
            httpClientError(nBSTransactionState, e2);
            throw e2;
        } catch (IOException e3) {
            httpClientError(nBSTransactionState, e3);
            throw e3;
        }
    }

    @NBSReplaceCallSite
    public static HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        if (!Harvest.isHttp_network_enabled()) {
            return httpClient.execute(httpUriRequest);
        }
        C6396h.m10130l("httpClient execute gather  begin !!");
        NBSTransactionState nBSTransactionState = new NBSTransactionState();
        try {
            nBSTransactionState.setAppPhase(C6638h.f17113m.intValue());
            httpUriRequest = (HttpUriRequest) dispatchHttpClientRequest(httpUriRequest, nBSTransactionState);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("error set transaction e:" + e.getMessage());
        }
        try {
            return m9885_(httpClient.execute(m9883_((HttpUriRequest) NBSHttpClientUtil.setHttpClientCrossProcessHeader(httpUriRequest, nBSTransactionState), nBSTransactionState)), nBSTransactionState);
        } catch (IOException e2) {
            httpClientError(nBSTransactionState, e2);
            throw e2;
        }
    }

    /* renamed from: _ */
    private static HttpUriRequest m9883_(HttpUriRequest httpUriRequest, NBSTransactionState nBSTransactionState) {
        return NBSHttpClientUtil.inspectAndInstrument(nBSTransactionState, httpUriRequest);
    }

    /* renamed from: _ */
    private static HttpRequest m9886_(HttpHost httpHost, HttpRequest httpRequest, NBSTransactionState nBSTransactionState) {
        return NBSHttpClientUtil.inspectAndInstrument(nBSTransactionState, httpHost, httpRequest);
    }

    @NBSReplaceCallSite
    public static DefaultHttpClient initDefaultHttpClient() {
        return new DefaultHttpClient();
    }

    /* renamed from: _ */
    private static HttpResponse m9885_(HttpResponse httpResponse, NBSTransactionState nBSTransactionState) {
        return NBSHttpClientUtil.inspectAndInstrument(nBSTransactionState, httpResponse);
    }

    /* renamed from: _ */
    private static <T> ResponseHandler<? extends T> m9884_(ResponseHandler<? extends T> responseHandler, NBSTransactionState nBSTransactionState) {
        return NBSResponseHandlerImpl.wrap(responseHandler, nBSTransactionState);
    }

    private static HttpRequest dispatchHttpClientRequest(HttpRequest httpRequest, NBSTransactionState nBSTransactionState) {
        String str;
        String uri = httpRequest.getRequestLine().getUri();
        try {
            str = new URL(uri).getHost();
        } catch (MalformedURLException e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("dispatchHttpClientRequest error!" + e.getMessage() + uri);
            str = null;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str)) {
            return httpRequest;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            InetAddress[] allByName = InetAddress.getAllByName(str);
            int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
            if (C6642k.m8922a(currentTimeMillis2)) {
                nBSTransactionState.setDnsElapse(currentTimeMillis2);
            }
            if (nBSTransactionState != null) {
                nBSTransactionState.setAddressAllStr(C6640i.m8955a(allByName));
            }
            return httpRequest;
        } catch (UnknownHostException unused) {
            InterfaceC6393e interfaceC6393e2 = log;
            interfaceC6393e2.mo10116d("dispatchHttpClientRequest error ! getByName the hostName is " + str);
            return httpRequest;
        }
    }

    private static void httpClientError(NBSTransactionState nBSTransactionState, Exception exc) {
        try {
            if (Harvest.isHttp_network_enabled() && !nBSTransactionState.isComplete()) {
                NBSHttpClientUtil.setErrorCodeFromException(nBSTransactionState, exc);
                if (nBSTransactionState.end() == null) {
                    log.mo10116d("transactionData is null");
                    return;
                }
                if (nBSTransactionState.isError()) {
                    String exception = nBSTransactionState.getException() != null ? nBSTransactionState.getException() : "";
                    InterfaceC6393e interfaceC6393e = log;
                    interfaceC6393e.mo10122a("error message:" + exception);
                    nBSTransactionState.setErrorDataInfo(exception, new HashMap(), "");
                }
                C6648q.m8781a(new C6412c(nBSTransactionState));
            }
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e2 = log;
            interfaceC6393e2.mo10116d("error httpClientError e:" + e.getMessage());
        }
    }
}
