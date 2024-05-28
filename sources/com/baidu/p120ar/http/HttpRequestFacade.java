package com.baidu.p120ar.http;

import android.text.TextUtils;
import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IHttpRequest;
import com.baidu.p120ar.ihttp.IHttpResponse;
import com.baidu.p120ar.ihttp.IProgressCallback;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.http.HttpRequestFacade */
/* loaded from: E:\10201592_dexfile_execute.dex */
class HttpRequestFacade implements IHttpRequest {
    private IHttpCall mCall;
    private HttpRequestFactory mFactory;
    private IProgressCallback mProgressCallback;
    private UrlBuilder mUrlBuilder = new UrlBuilder();
    private IBodyBuilder mBodyBuilder = null;
    private HttpRequestInfo mRequest = new HttpRequestInfo();

    public HttpRequestFacade(HttpRequestFactory httpRequestFactory) {
        this.mFactory = httpRequestFactory;
        HttpRequestInfo httpRequestInfo = this.mRequest;
        httpRequestInfo.method = "GET";
        httpRequestInfo.headers = new HashMap();
        this.mRequest.options = new HttpRequestOptions();
        this.mCall = null;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setCharset(Charset charset) {
        this.mRequest.charset = charset;
        this.mUrlBuilder.setCharset(charset);
        IBodyBuilder iBodyBuilder = this.mBodyBuilder;
        if (iBodyBuilder != null) {
            iBodyBuilder.setCharset(charset);
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setUrl(String str) {
        this.mUrlBuilder.setUrlPre(str);
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addQueryMap(Map<String, Object> map) {
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    this.mUrlBuilder.addQueryField(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addQueryField(String str, Object obj) {
        if (obj != null) {
            this.mUrlBuilder.addQueryField(str, obj.toString());
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setMethod(String str) {
        if (str != null) {
            this.mRequest.method = str.toUpperCase();
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setConnectionTimeout(int i) {
        this.mRequest.options.connectionTimeout = i;
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setReadTimeout(int i) {
        this.mRequest.options.readTimeout = i;
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setUseCache(boolean z) {
        this.mRequest.options.useCache = z;
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addHeaders(String[] strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                addHeader(str);
            }
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addHeader(String str) {
        String[] parseHttpHeader = HttpUtil.parseHttpHeader(str);
        if (parseHttpHeader == null) {
            this.mRequest.addException(String.format("Header 格式必须是： \\\"Name: Value\\\". Found: \\\"%s\\\"", str));
            return this;
        }
        addHeader(parseHttpHeader[0], parseHttpHeader[1]);
        return this;
    }

    private void addHeader(String str, String str2) {
        this.mRequest.headers.put(str, str2);
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setAsMultipart() {
        IBodyBuilder iBodyBuilder = this.mBodyBuilder;
        return (iBodyBuilder == null || !(iBodyBuilder instanceof MultipartBodyBuilder)) ? setAsMultipart(HttpDefaultConfig.MULTIPART_BOUNDARY) : this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setAsMultipart(String str) {
        if (prepareForMultipart(str)) {
            ((MultipartBodyBuilder) this.mBodyBuilder).setBoundary(str);
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addPart(String str, Object obj) {
        if (obj != null && prepareForMultipart(null)) {
            ((MultipartBodyBuilder) this.mBodyBuilder).appendPart(str, obj.toString());
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addPartMap(Map<String, Object> map) {
        if (map != null && prepareForMultipart(null)) {
            MultipartBodyBuilder multipartBodyBuilder = (MultipartBodyBuilder) this.mBodyBuilder;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    multipartBodyBuilder.appendPart(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addFile(String str, String str2) {
        if (prepareForMultipart(null)) {
            ((MultipartBodyBuilder) this.mBodyBuilder).appendFile(str, str2);
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addFile(String str, byte[] bArr) {
        if (prepareForMultipart(null)) {
            ((MultipartBodyBuilder) this.mBodyBuilder).appendFile(str, bArr);
        }
        return this;
    }

    private boolean prepareForMultipart(String str) {
        IBodyBuilder iBodyBuilder = this.mBodyBuilder;
        if (iBodyBuilder != null && !(iBodyBuilder instanceof MultipartBodyBuilder)) {
            this.mRequest.addException(newBodyIllegalException(iBodyBuilder));
            return false;
        } else if (this.mBodyBuilder == null) {
            this.mBodyBuilder = new MultipartBodyBuilder();
            this.mBodyBuilder.setCharset(this.mRequest.charset);
            MultipartBodyBuilder multipartBodyBuilder = (MultipartBodyBuilder) this.mBodyBuilder;
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            multipartBodyBuilder.setBoundary(str);
            return true;
        } else if (TextUtils.isEmpty(str)) {
            return true;
        } else {
            MultipartBodyBuilder multipartBodyBuilder2 = (MultipartBodyBuilder) this.mBodyBuilder;
            if (multipartBodyBuilder2.isEmpty() || str.equals(multipartBodyBuilder2.getBoundary())) {
                return true;
            }
            this.mRequest.addException("已经添加请求体内容，不能再更改boundary");
            return false;
        }
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setBody(String str) {
        if (!TextUtils.isEmpty(str)) {
            setBody(str.getBytes(this.mRequest.charset), "application/x-www-form-urlencoded");
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setBody(JSONObject jSONObject) {
        if (jSONObject != null) {
            setBody((!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).getBytes(this.mRequest.charset), "application/json");
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest setBody(byte[] bArr) {
        return setBody(bArr, null);
    }

    private IHttpRequest setBody(byte[] bArr, String str) {
        if (prepareForRawBody(str)) {
            ((RawBodyBuilder) this.mBodyBuilder).setData(bArr);
        }
        return this;
    }

    private boolean prepareForRawBody(String str) {
        IBodyBuilder iBodyBuilder = this.mBodyBuilder;
        if (iBodyBuilder != null && !(iBodyBuilder instanceof RawBodyBuilder)) {
            this.mRequest.addException(newBodyIllegalException(iBodyBuilder));
            return false;
        }
        if (this.mBodyBuilder == null) {
            this.mBodyBuilder = new RawBodyBuilder();
            this.mBodyBuilder.setCharset(this.mRequest.charset);
        }
        if (TextUtils.isEmpty(str) || this.mRequest.headers.containsKey("Content-Type")) {
            return true;
        }
        addHeader("Content-Type", str);
        return true;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addFormData(Map<String, Object> map) {
        if (map != null && prepareForFormData()) {
            FormDataBodyBuilder formDataBodyBuilder = (FormDataBodyBuilder) this.mBodyBuilder;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    formDataBodyBuilder.appendField(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpRequest addFormField(String str, Object obj) {
        if (!TextUtils.isEmpty(str) && obj != null && prepareForFormData()) {
            ((FormDataBodyBuilder) this.mBodyBuilder).appendField(str, obj.toString());
        }
        return this;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public void setProgressCallback(IProgressCallback iProgressCallback) {
        this.mProgressCallback = iProgressCallback;
    }

    private boolean prepareForFormData() {
        IBodyBuilder iBodyBuilder = this.mBodyBuilder;
        if (iBodyBuilder != null && !(iBodyBuilder instanceof FormDataBodyBuilder)) {
            this.mRequest.addException(newBodyIllegalException(iBodyBuilder));
            return false;
        } else if (this.mBodyBuilder == null) {
            this.mBodyBuilder = new FormDataBodyBuilder();
            this.mBodyBuilder.setCharset(this.mRequest.charset);
            return true;
        } else {
            return true;
        }
    }

    private String newBodyIllegalException(IBodyBuilder iBodyBuilder) {
        return iBodyBuilder instanceof RawBodyBuilder ? "该请求已通过setBody设置请求体数据，不能再使用其他方式添加数据" : iBodyBuilder instanceof FormDataBodyBuilder ? "该请求是FormData方式传输数据，请使用addFormData或addFormField添加请求体数据" : iBodyBuilder instanceof MultipartBodyBuilder ? "该请求是Multipart方式传输数据，请使用addPart、addPartMap或addFile添加请求体数据" : "";
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public IHttpResponse execute() throws HttpException {
        IHttpCall iHttpCall = this.mCall;
        if (iHttpCall != null) {
            iHttpCall.cancel();
        }
        this.mCall = this.mFactory.getExecutor().execute(buildRequest(), this.mProgressCallback);
        return this.mCall.getResponse();
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public void enqueue(IHttpCallback iHttpCallback) {
        IHttpCall iHttpCall = this.mCall;
        if (iHttpCall != null) {
            iHttpCall.cancel();
        }
        HttpRequestExecutor executor = this.mFactory.getExecutor();
        try {
            HttpRequestInfo buildRequest = buildRequest();
            if (buildRequest != null) {
                this.mCall = executor.enqueue(buildRequest, iHttpCallback, this.mProgressCallback);
            }
        } catch (HttpException e) {
            this.mCall = executor.callOnFail(e, iHttpCallback);
        }
    }

    @Override // com.baidu.p120ar.ihttp.IHttpRequest
    public void cancel() {
        this.mProgressCallback = null;
        IHttpCall iHttpCall = this.mCall;
        if (iHttpCall != null) {
            iHttpCall.cancel();
        }
    }

    private HttpRequestInfo buildRequest() throws HttpException {
        if (this.mRequest.hasError()) {
            throw new HttpException(3, this.mRequest.getErrorMsg());
        }
        if (this.mRequest.charset != null) {
            addHeader("charset", this.mRequest.charset.name());
        }
        try {
            this.mRequest.url = this.mUrlBuilder.build();
            if (this.mBodyBuilder != null) {
                if (!this.mRequest.headers.containsKey("Content-Type")) {
                    String contentType = this.mBodyBuilder.getContentType();
                    if (!TextUtils.isEmpty(contentType)) {
                        this.mRequest.headers.put("Content-Type", contentType);
                    }
                }
                this.mRequest.bodyBuilder = this.mBodyBuilder;
            }
            return this.mRequest;
        } catch (MalformedURLException e) {
            throw new HttpException(3, e);
        }
    }
}
