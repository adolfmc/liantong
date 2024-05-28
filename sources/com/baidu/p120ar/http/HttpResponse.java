package com.baidu.p120ar.http;

import com.baidu.p120ar.ihttp.IHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.HttpResponse */
/* loaded from: E:\10201592_dexfile_execute.dex */
class HttpResponse implements IHttpResponse {
    private static final int BUFFER_SIZE = 1024;
    private Charset mCharset;
    private int mCode;
    private HttpURLConnection mConnection;
    private String mContent;
    private String mMessage;

    public HttpResponse(HttpURLConnection httpURLConnection, Charset charset) throws IOException {
        this.mConnection = httpURLConnection;
        this.mCharset = charset;
        this.mCode = httpURLConnection.getResponseCode();
        this.mMessage = httpURLConnection.getResponseMessage();
    }

    @Override // com.baidu.p120ar.ihttp.IHttpResponse
    public int getCode() {
        return this.mCode;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpResponse
    public String getMessage() {
        return this.mMessage;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpResponse
    public boolean isSuccess() {
        int code = getCode();
        return code >= 200 && code < 300;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpResponse
    public String getHeader(String str) {
        return this.mConnection.getHeaderField(str);
    }

    @Override // com.baidu.p120ar.ihttp.IHttpResponse
    public int getContentLength() {
        return this.mConnection.getContentLength();
    }

    @Override // com.baidu.p120ar.ihttp.IHttpResponse
    public String getContent() throws IOException {
        String str = this.mContent;
        if (str != null) {
            return str;
        }
        InputStream stream = getStream();
        if (stream == null) {
            throw new IOException("Http请求响应输入流已不可访问，请不要在关闭输入流后再调用该方法");
        }
        String name = this.mCharset.name();
        InputStreamReader inputStreamReader = new InputStreamReader(stream, name);
        StringWriter stringWriter = new StringWriter();
        char[] cArr = new char[4096];
        for (int read = inputStreamReader.read(cArr); read > 0; read = inputStreamReader.read(cArr)) {
            stringWriter.write(cArr, 0, read);
        }
        String stringWriter2 = stringWriter.toString();
        inputStreamReader.close();
        stringWriter.close();
        if ("utf-8".equalsIgnoreCase(name)) {
            stringWriter2 = HttpUtil.clearUtf8BOM(stringWriter2);
        }
        this.mContent = stringWriter2;
        return stringWriter2;
    }

    @Override // com.baidu.p120ar.ihttp.IHttpResponse
    public InputStream getStream() throws IOException {
        return this.mConnection.getInputStream();
    }
}
