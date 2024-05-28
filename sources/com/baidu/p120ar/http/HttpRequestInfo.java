package com.baidu.p120ar.http;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.HttpRequestInfo */
/* loaded from: E:\10201592_dexfile_execute.dex */
class HttpRequestInfo {
    public IBodyBuilder bodyBuilder;
    public Charset charset;
    public Map<String, String> headers;
    private StringBuilder mErrorMsg = new StringBuilder();
    public String method;
    public HttpRequestOptions options;
    public URL url;

    public void addException(String str) {
        StringBuilder sb = this.mErrorMsg;
        sb.append(str + "\r\n");
    }

    public String getErrorMsg() {
        return this.mErrorMsg.toString();
    }

    public boolean hasError() {
        return this.mErrorMsg.length() > 0;
    }
}
