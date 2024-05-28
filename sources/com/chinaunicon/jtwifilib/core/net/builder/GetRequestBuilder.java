package com.chinaunicon.jtwifilib.core.net.builder;

import android.text.TextUtils;
import com.chinaunicon.jtwifilib.core.net.callback.OkCallback;
import com.chinaunicon.jtwifilib.core.net.proxy.OkHttpProxy;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class GetRequestBuilder<T> extends RequestBuilder<T> {
    public GetRequestBuilder url(String str) {
        this.url = str;
        return this;
    }

    public GetRequestBuilder setParams(Map<String, String> map) {
        this.params = map;
        return this;
    }

    public GetRequestBuilder addParams(String str, String str2) {
        if (this.params == null) {
            this.params = new HashMap();
        }
        this.params.put(str, str2);
        return this;
    }

    public GetRequestBuilder tag(Object obj) {
        this.tag = obj;
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
        if (this.params != null && this.params.size() > 0) {
            this.url = appendParams(this.url, this.params);
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
        if (this.params != null && this.params.size() > 0) {
            this.url = appendParams(this.url, this.params);
        }
        return OkHttpProxy.getInstance().newCall(url.build()).execute();
    }
}
