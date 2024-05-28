package com.baidu.p120ar.ihttp;

import java.nio.charset.Charset;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ihttp.IHttpRequest */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IHttpRequest {
    IHttpRequest addFile(String str, String str2);

    IHttpRequest addFile(String str, byte[] bArr);

    IHttpRequest addFormData(Map<String, Object> map);

    IHttpRequest addFormField(String str, Object obj);

    IHttpRequest addHeader(String str);

    IHttpRequest addHeaders(String[] strArr);

    IHttpRequest addPart(String str, Object obj);

    IHttpRequest addPartMap(Map<String, Object> map);

    IHttpRequest addQueryField(String str, Object obj);

    IHttpRequest addQueryMap(Map<String, Object> map);

    void cancel();

    void enqueue(IHttpCallback iHttpCallback);

    IHttpResponse execute() throws HttpException;

    IHttpRequest setAsMultipart();

    IHttpRequest setAsMultipart(String str);

    IHttpRequest setBody(String str);

    IHttpRequest setBody(JSONObject jSONObject);

    IHttpRequest setBody(byte[] bArr);

    IHttpRequest setCharset(Charset charset);

    IHttpRequest setConnectionTimeout(int i);

    IHttpRequest setMethod(String str);

    void setProgressCallback(IProgressCallback iProgressCallback);

    IHttpRequest setReadTimeout(int i);

    IHttpRequest setUrl(String str);

    IHttpRequest setUseCache(boolean z);
}
