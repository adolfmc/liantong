package com.chinaunicon.jtwifilib.core.net.builder;

import android.text.TextUtils;
import com.chinaunicon.jtwifilib.core.net.callback.OkCallback;
import com.chinaunicon.jtwifilib.core.net.proxy.OkHttpProxy;
import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PostRequestBuilder<T> extends RequestBuilder<T> {
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json; charset=utf-8");
    private static final String TAG = "PostRequestBuilder";
    RequestBody body = null;
    private Map<String, String> headers;
    private String key;
    private String value;

    public PostRequestBuilder url(String str) {
        this.url = str;
        return this;
    }

    public PostRequestBuilder setParams(Map<String, String> map) {
        this.params = map;
        return this;
    }

    public PostRequestBuilder addParams(String str, String str2) {
        if (this.params == null) {
            this.params = new IdentityHashMap();
        }
        this.params.put(str, str2);
        return this;
    }

    public PostRequestBuilder header(String str, String str2) {
        this.key = str;
        this.value = str2;
        return this;
    }

    public PostRequestBuilder headers(Map<String, String> map) {
        this.headers = map;
        return this;
    }

    public PostRequestBuilder coo(Map<String, String> map) {
        this.headers = map;
        return this;
    }

    public PostRequestBuilder addHeader(String str, String str2) {
        if (this.headers == null) {
            this.headers = new IdentityHashMap();
        }
        this.headers.put(str, str2);
        return this;
    }

    public PostRequestBuilder tag(Object obj) {
        this.tag = obj;
        return this;
    }

    public PostRequestBuilder setCustomRequestBodyEntity(Object obj) {
        setRequestBody(MEDIA_TYPE_MARKDOWN, obj.toString());
        return this;
    }

    private void setRequestBody(MediaType mediaType, String str) {
        setCustomRequestBody(RequestBody.create(mediaType, str));
    }

    private PostRequestBuilder setCustomRequestBody(RequestBody requestBody) {
        this.body = requestBody;
        return this;
    }

    @Override // com.chinaunicon.jtwifilib.core.net.builder.RequestBuilder
    public Call enqueue(Callback callback) {
        if (TextUtils.isEmpty(this.url)) {
            throw new IllegalArgumentException("url can not be null !");
        }
        Request.Builder url = new Request.Builder().url(this.url);
        if (this.tag != null) {
            url.tag(this.tag);
        }
        FormBody.Builder builder = new FormBody.Builder();
        appendParams(builder, this.params);
        appendHeaders(url, this.headers);
        String str = this.value;
        if (str != null && !str.isEmpty()) {
            appendHeader(url, this.key, this.value);
        }
        RequestBody requestBody = this.body;
        if (requestBody != null) {
            url.post(requestBody);
        } else {
            url.post(builder.build());
        }
        Request build = url.build();
        if (callback instanceof OkCallback) {
            ((OkCallback) callback).onStart();
        }
        Call newCall = OkHttpProxy.getInstance().newCall(build);
        newCall.enqueue(callback);
        return newCall;
    }

    @Override // com.chinaunicon.jtwifilib.core.net.builder.RequestBuilder
    public Response execute() throws IOException {
        if (TextUtils.isEmpty(this.url)) {
            throw new IllegalArgumentException("url can not be null !");
        }
        Request.Builder url = new Request.Builder().url(this.url);
        if (this.tag != null) {
            url.tag(this.tag);
        }
        FormBody.Builder builder = new FormBody.Builder();
        appendParams(builder, this.params);
        appendHeaders(url, this.headers);
        url.post(builder.build());
        return OkHttpProxy.getInstance().newCall(url.build()).execute();
    }
}
