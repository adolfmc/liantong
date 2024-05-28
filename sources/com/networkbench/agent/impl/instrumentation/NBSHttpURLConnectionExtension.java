package com.networkbench.agent.impl.instrumentation;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.instrumentation.p263io.NBSCountingInputStream;
import com.networkbench.agent.impl.instrumentation.p263io.NBSCountingOutputStream;
import com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteEvent;
import com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6648q;
import com.networkbench.agent.impl.util.C6653u;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSHttpURLConnectionExtension extends HttpURLConnection {
    private static final InterfaceC6393e log = C6394f.m10150a();

    /* renamed from: a */
    NBSCountingInputStream f16363a;
    private HttpURLConnection impl;
    private NBSTransactionState transactionState;

    public NBSHttpURLConnectionExtension(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.impl = httpURLConnection;
        getTransactionState();
        if (httpURLConnection == null || !Harvest.isHttp_network_enabled()) {
            return;
        }
        this.transactionState.setDnsElapse(C6642k.m8917a(httpURLConnection.getURL().getHost()));
        this.transactionState.setAppPhase(C6638h.f17113m.intValue());
        NBSTransactionStateUtil.setCrossProcessHeader(this.transactionState, httpURLConnection);
        try {
            this.transactionState.requestHeaderParam.putAll(C6653u.m8705e(this.impl.getRequestProperties()));
        } catch (Throwable th) {
            C6396h.m10137e("Util.getHeader requestHeaderParam  has error .... " + th);
        }
    }

    @Override // java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        this.impl.addRequestProperty(str, str2);
        this.transactionState.requestHeaderParam.put(str.toLowerCase(), str2);
        getTransactionState().setNBSRequestHeader(str, this.impl.getRequestProperty(str));
    }

    @Override // java.net.HttpURLConnection
    public void disconnect() {
        try {
            if (this.transactionState != null && !this.transactionState.isComplete() && this.transactionState.isConnect) {
                addTransactionAndErrorData(this.transactionState);
            }
        } catch (Throwable unused) {
        }
        this.impl.disconnect();
    }

    @Override // java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.impl.usingProxy();
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        getTransactionState();
        try {
            this.impl.connect();
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.impl.getAllowUserInteraction();
    }

    @Override // java.net.URLConnection
    public int getConnectTimeout() {
        return this.impl.getConnectTimeout();
    }

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        getTransactionState();
        try {
            Object content = this.impl.getContent();
            if (this.impl.getContentLength() >= 0) {
                NBSTransactionState transactionState = getTransactionState();
                if (!transactionState.isComplete()) {
                    addTransactionAndErrorData(transactionState);
                }
            }
            return content;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public Object getContent(Class[] clsArr) throws IOException {
        getTransactionState();
        try {
            Object content = this.impl.getContent(clsArr);
            checkResponse();
            return content;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public String getContentEncoding() {
        getTransactionState();
        String contentEncoding = this.impl.getContentEncoding();
        checkResponse();
        return contentEncoding;
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        getTransactionState();
        int contentLength = this.impl.getContentLength();
        checkResponse();
        return contentLength;
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        getTransactionState();
        String contentType = this.impl.getContentType();
        checkResponse();
        return contentType;
    }

    @Override // java.net.URLConnection
    public long getDate() {
        getTransactionState();
        long date = this.impl.getDate();
        checkResponse();
        return date;
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public long getHeaderFieldDate(String str, long j) {
        getTransactionState();
        long headerFieldDate = this.impl.getHeaderFieldDate(str, j);
        checkResponse();
        return headerFieldDate;
    }

    @Override // java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.impl.getInstanceFollowRedirects();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() throws IOException {
        return this.impl.getPermission();
    }

    @Override // java.net.HttpURLConnection
    public String getRequestMethod() {
        NBSTransactionState transactionState = getTransactionState();
        String requestMethod = this.impl.getRequestMethod();
        NBSTransactionStateUtil.setRequestMethod(transactionState, requestMethod);
        this.transactionState.setHttpLibType(HttpLibType.URLConnection);
        return requestMethod;
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() throws IOException {
        getTransactionState();
        try {
            int responseCode = this.impl.getResponseCode();
            this.transactionState.setStatusCode(responseCode);
            checkResponse();
            return responseCode;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        getTransactionState();
        try {
            String responseMessage = this.impl.getResponseMessage();
            checkResponse();
            return responseMessage;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public void setChunkedStreamingMode(int i) {
        this.impl.setChunkedStreamingMode(i);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.impl.setFixedLengthStreamingMode(i);
    }

    @Override // java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.impl.setInstanceFollowRedirects(z);
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        getTransactionState();
        try {
            this.impl.setRequestMethod(str);
            this.transactionState.setHttpLibType(HttpLibType.URLConnection);
            NBSTransactionStateUtil.setRequestMethod(getTransactionState(), str);
        } catch (ProtocolException e) {
            error(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.impl.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection
    public boolean getDoInput() {
        return this.impl.getDoInput();
    }

    @Override // java.net.URLConnection
    public boolean getDoOutput() {
        return this.impl.getDoOutput();
    }

    @Override // java.net.URLConnection
    public long getExpiration() {
        getTransactionState();
        long expiration = this.impl.getExpiration();
        checkResponse();
        return expiration;
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        getTransactionState();
        String headerField = this.impl.getHeaderField(i);
        checkResponse();
        return headerField;
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        getTransactionState();
        String headerField = this.impl.getHeaderField(str);
        checkResponse();
        return headerField;
    }

    @Override // java.net.URLConnection
    public int getHeaderFieldInt(String str, int i) {
        getTransactionState();
        int headerFieldInt = this.impl.getHeaderFieldInt(str, i);
        checkResponse();
        return headerFieldInt;
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderFieldKey(int i) {
        getTransactionState();
        String headerFieldKey = this.impl.getHeaderFieldKey(i);
        checkResponse();
        return headerFieldKey;
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        getTransactionState();
        Map<String, List<String>> headerFields = this.impl.getHeaderFields();
        checkResponse();
        return headerFields;
    }

    @Override // java.net.URLConnection
    public long getIfModifiedSince() {
        getTransactionState();
        long ifModifiedSince = this.impl.getIfModifiedSince();
        checkResponse();
        return ifModifiedSince;
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        final NBSTransactionState transactionState = getTransactionState();
        try {
            NBSCountingInputStream nBSCountingInputStream = new NBSCountingInputStream(this.impl.getInputStream());
            NBSTransactionStateUtil.inspectAndInstrumentResponse(transactionState, this.impl);
            nBSCountingInputStream.addStreamCompleteListener(new NBSStreamCompleteListener() { // from class: com.networkbench.agent.impl.instrumentation.NBSHttpURLConnectionExtension.1
                @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
                public void streamError(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
                    InterfaceC6393e interfaceC6393e = NBSHttpURLConnectionExtension.log;
                    interfaceC6393e.mo10116d("streamError:" + nBSStreamCompleteEvent.toString());
                    if (!transactionState.isComplete()) {
                        transactionState.setBytesReceived(nBSStreamCompleteEvent.getBytes());
                    }
                    try {
                        transactionState.setStatusCode(NBSHttpURLConnectionExtension.this.impl.getResponseCode());
                    } catch (IOException unused) {
                    }
                    NBSHttpURLConnectionExtension.this.error(nBSStreamCompleteEvent.getException());
                }

                @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
                public void streamComplete(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
                    if (transactionState.isComplete()) {
                        return;
                    }
                    long contentLength = NBSHttpURLConnectionExtension.this.impl.getContentLength();
                    long bytes = nBSStreamCompleteEvent.getBytes();
                    if (contentLength < 0) {
                        contentLength = bytes;
                    }
                    transactionState.setBytesReceived(contentLength);
                    try {
                        transactionState.setStatusCode(NBSHttpURLConnectionExtension.this.impl.getResponseCode());
                    } catch (IOException unused) {
                    }
                    NBSHttpURLConnectionExtension.this.addTransactionAndErrorData(transactionState);
                }
            });
            return nBSCountingInputStream;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        getTransactionState();
        NBSCountingInputStream nBSCountingInputStream = this.f16363a;
        if (nBSCountingInputStream != null) {
            return nBSCountingInputStream;
        }
        try {
            this.f16363a = new NBSCountingInputStream(this.impl.getErrorStream(), true);
            this.f16363a.addStreamCompleteListener(new NBSStreamCompleteListener() { // from class: com.networkbench.agent.impl.instrumentation.NBSHttpURLConnectionExtension.2
                @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
                public void streamError(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
                    InterfaceC6393e interfaceC6393e = NBSHttpURLConnectionExtension.log;
                    interfaceC6393e.mo10116d("streamError:" + nBSStreamCompleteEvent.toString());
                    if (!NBSHttpURLConnectionExtension.this.transactionState.isComplete()) {
                        NBSHttpURLConnectionExtension.this.transactionState.setBytesReceived(nBSStreamCompleteEvent.getBytes());
                    }
                    NBSHttpURLConnectionExtension.this.error(nBSStreamCompleteEvent.getException());
                }

                @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
                public void streamComplete(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
                    if (NBSHttpURLConnectionExtension.this.transactionState.isComplete()) {
                        return;
                    }
                    int i = 0;
                    try {
                        i = NBSHttpURLConnectionExtension.this.impl.getResponseCode();
                        NBSHttpURLConnectionExtension.this.transactionState.setStatusCode(i);
                    } catch (IOException unused) {
                    }
                    long bytes = nBSStreamCompleteEvent.getBytes();
                    if (i != 206) {
                        long contentLength = NBSHttpURLConnectionExtension.this.impl.getContentLength();
                        if (contentLength >= 0) {
                            bytes = contentLength;
                        }
                    }
                    NBSHttpURLConnectionExtension.this.transactionState.setBytesReceived(bytes);
                    NBSHttpURLConnectionExtension nBSHttpURLConnectionExtension = NBSHttpURLConnectionExtension.this;
                    nBSHttpURLConnectionExtension.addTransactionAndErrorData(nBSHttpURLConnectionExtension.transactionState);
                }
            });
            return this.f16363a;
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("errorStream :" + e.toString());
            return this.impl.getErrorStream();
        }
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        getTransactionState();
        long lastModified = this.impl.getLastModified();
        checkResponse();
        return lastModified;
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        try {
            NBSCountingOutputStream nBSCountingOutputStream = new NBSCountingOutputStream(this.impl.getOutputStream());
            nBSCountingOutputStream.addStreamCompleteListener(new NBSStreamCompleteListener() { // from class: com.networkbench.agent.impl.instrumentation.NBSHttpURLConnectionExtension.3
                @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
                public void streamError(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
                    NBSTransactionState transactionState = NBSHttpURLConnectionExtension.this.getTransactionState();
                    if (!transactionState.isComplete()) {
                        transactionState.setBytesSent(nBSStreamCompleteEvent.getBytes());
                    }
                    try {
                        transactionState.setStatusCode(NBSHttpURLConnectionExtension.this.impl.getResponseCode());
                    } catch (IOException unused) {
                    }
                    NBSHttpURLConnectionExtension.this.error(nBSStreamCompleteEvent.getException());
                }

                @Override // com.networkbench.agent.impl.instrumentation.p263io.NBSStreamCompleteListener
                public void streamComplete(NBSStreamCompleteEvent nBSStreamCompleteEvent) {
                    NBSTransactionState transactionState = NBSHttpURLConnectionExtension.this.getTransactionState();
                    if (transactionState.isComplete()) {
                        return;
                    }
                    String requestProperty = NBSHttpURLConnectionExtension.this.impl.getRequestProperty("content-length");
                    long bytes = nBSStreamCompleteEvent.getBytes();
                    if (requestProperty != null) {
                        try {
                            bytes = Long.parseLong(requestProperty);
                        } catch (NumberFormatException unused) {
                        }
                    }
                    transactionState.setBytesSentAfterComplete(bytes);
                }
            });
            return nBSCountingOutputStream;
        } catch (IOException e) {
            error(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public int getReadTimeout() {
        return this.impl.getReadTimeout();
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.impl.getRequestProperties();
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String str) {
        return this.impl.getRequestProperty(str);
    }

    @Override // java.net.URLConnection
    public URL getURL() {
        return this.impl.getURL();
    }

    @Override // java.net.URLConnection
    public boolean getUseCaches() {
        return this.impl.getUseCaches();
    }

    @Override // java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.impl.setAllowUserInteraction(z);
    }

    @Override // java.net.URLConnection
    public void setConnectTimeout(int i) {
        this.impl.setConnectTimeout(i);
    }

    @Override // java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.impl.setDefaultUseCaches(z);
    }

    @Override // java.net.URLConnection
    public void setDoInput(boolean z) {
        this.impl.setDoInput(z);
    }

    @Override // java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.impl.setDoOutput(z);
    }

    @Override // java.net.URLConnection
    public void setIfModifiedSince(long j) {
        this.impl.setIfModifiedSince(j);
    }

    @Override // java.net.URLConnection
    public void setReadTimeout(int i) {
        this.impl.setReadTimeout(i);
    }

    @Override // java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        this.impl.setRequestProperty(str, str2);
        getTransactionState().setNBSRequestHeader(str, str2);
        this.transactionState.requestHeaderParam.put(str.toLowerCase(), str2);
    }

    @Override // java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.impl.setUseCaches(z);
    }

    @Override // java.net.URLConnection
    public String toString() {
        return this.impl.toString();
    }

    private void checkResponse() {
        if (getTransactionState().isComplete()) {
            return;
        }
        NBSTransactionStateUtil.inspectAndInstrumentResponse(getTransactionState(), this.impl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NBSTransactionState getTransactionState() {
        if (this.transactionState == null) {
            this.transactionState = new NBSTransactionState();
            NBSTransactionStateUtil.setUrlAndCarrier(this.transactionState, this.impl);
        }
        return this.transactionState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void error(Exception exc) {
        try {
            if (Harvest.isHttp_network_enabled()) {
                NBSTransactionState transactionState = getTransactionState();
                try {
                    transactionState.setContentType(C6653u.m8694i(this.impl.getContentType()));
                } catch (Exception e) {
                    log.mo10121a("NBSTransactionStateUtil. getcontenttype occur an error", e);
                }
                if (transactionState != null) {
                    try {
                        if (!transactionState.hasParseUrlParams) {
                            NBSTransactionStateUtil.processUrlParams(transactionState, this.impl);
                        }
                    } catch (Exception e2) {
                        log.mo10121a("NBSTransactionStateUtil.processUrlParams occur an error", e2);
                    }
                }
                if (!transactionState.isComplete()) {
                    String exception = transactionState.getException() != null ? transactionState.getException() : "";
                    NBSTransactionStateUtil.inspectAndInstrumentResponse(transactionState, this.impl);
                    NBSTransactionStateUtil.setErrorCodeFromException(transactionState, exc);
                    InterfaceC6393e interfaceC6393e = log;
                    interfaceC6393e.mo10122a("error message:" + exception);
                    transactionState.end();
                    InterfaceC6393e interfaceC6393e2 = log;
                    interfaceC6393e2.mo10122a("isError:" + transactionState.isError());
                    if (transactionState.isError()) {
                        transactionState.setErrorDataInfo(exception, new HashMap(), "");
                    }
                    C6648q.m8781a(new C6412c(transactionState));
                    return;
                }
                NBSTransactionStateUtil.setErrorCodeFromException(transactionState, exc);
            }
        } catch (Exception e3) {
            C6396h.m10134h("NBSHttpURLConnectionExtension error had an error :" + e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTransactionAndErrorData(NBSTransactionState nBSTransactionState) {
        try {
            if (Harvest.isHttp_network_enabled()) {
                try {
                    nBSTransactionState.setContentType(C6653u.m8694i(this.impl.getContentType()));
                } catch (Exception e) {
                    log.mo10121a("NBSTransactionStateUtil. getcontenttype occur an error", e);
                }
                try {
                    nBSTransactionState.responseHeaderParam = C6653u.m8705e(this.impl.getHeaderFields());
                } catch (Exception e2) {
                    C6396h.m10137e("addTransactionAndErrorData has error :  " + e2);
                }
                if (nBSTransactionState.end() == null) {
                    return;
                }
                if (nBSTransactionState.isError()) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        InputStream errorStream = getErrorStream();
                        if (errorStream instanceof NBSCountingInputStream) {
                            sb.append(((NBSCountingInputStream) errorStream).getBufferAsString());
                        }
                    } catch (Exception e3) {
                        log.mo10116d(e3.toString());
                    }
                    TreeMap treeMap = new TreeMap();
                    try {
                        if (this.impl.getHeaderFields() != null && this.impl.getHeaderFields().size() > 0) {
                            Map<String, List<String>> headerFields = this.impl.getHeaderFields();
                            for (String str : headerFields.keySet()) {
                                if (!TextUtils.isEmpty(str)) {
                                    treeMap.put(str, headerFields.get(str).get(0));
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                    String exception = nBSTransactionState.getException() != null ? nBSTransactionState.getException() : "";
                    InterfaceC6393e interfaceC6393e = log;
                    interfaceC6393e.mo10117c("error message:" + exception);
                    nBSTransactionState.setErrorDataInfo(sb.toString(), treeMap, exception);
                }
                C6648q.m8781a(new C6412c(nBSTransactionState));
            }
        } catch (Exception e4) {
            log.mo10121a("NBSHttpURLConnectionExtension addTransactionAndErrorData has an error : ", e4);
        }
    }
}
