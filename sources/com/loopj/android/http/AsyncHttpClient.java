package com.loopj.android.http;

import android.content.Context;
import android.text.TextUtils;
import com.loopj.android.http.cookie.PersistentCookieJar;
import com.loopj.android.http.cookie.cache.SetCookieCache;
import com.loopj.android.http.cookie.persistence.SharedPrefsCookiePersistor;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import io.reactivex.Observable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.EventListener;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AsyncHttpClient {
    private OkHttpClient client;
    private final String TAG = "OKHttp3";
    private RequestCookie requestCookie = new RequestCookie();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum RequestBodyWay {
        Form,
        JSON,
        TextXJSON
    }

    public void setEventListenerFactory(EventListener.Factory factory) {
        OkHttpClient.Builder eventListenerFactory = this.client.newBuilder().eventListenerFactory(factory);
        this.client = !(eventListenerFactory instanceof OkHttpClient.Builder) ? eventListenerFactory.build() : NBSOkHttp3Instrumentation.builderInit(eventListenerFactory);
    }

    public AsyncHttpClient(Context context) {
        OkHttpClient.Builder cookieJar = new OkHttpClient.Builder().retryOnConnectionFailure(true).cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context), this.requestCookie));
        this.client = !(cookieJar instanceof OkHttpClient.Builder) ? cookieJar.build() : NBSOkHttp3Instrumentation.builderInit(cookieJar);
        this.client.dispatcher().setMaxRequestsPerHost(10);
    }

    public OkHttpClient getOkHttpClient() {
        return this.client;
    }

    public void addCookieForAddToReqeust(Cookie cookie) {
        this.requestCookie.addCookieForAddToReqeust(cookie);
    }

    public void clearCookieFromAddToRequest() {
        this.requestCookie.clear();
    }

    public void addInterceptor(Interceptor interceptor) {
        OkHttpClient.Builder addInterceptor = this.client.newBuilder().addInterceptor(interceptor);
        this.client = !(addInterceptor instanceof OkHttpClient.Builder) ? addInterceptor.build() : NBSOkHttp3Instrumentation.builderInit(addInterceptor);
    }

    public void addNetworkInterceptor(Interceptor interceptor) {
        OkHttpClient.Builder addNetworkInterceptor = this.client.newBuilder().addNetworkInterceptor(interceptor);
        this.client = !(addNetworkInterceptor instanceof OkHttpClient.Builder) ? addNetworkInterceptor.build() : NBSOkHttp3Instrumentation.builderInit(addNetworkInterceptor);
    }

    public void addAuthenticator(Authenticator authenticator) {
        OkHttpClient.Builder authenticator2 = this.client.newBuilder().authenticator(authenticator);
        this.client = !(authenticator2 instanceof OkHttpClient.Builder) ? authenticator2.build() : NBSOkHttp3Instrumentation.builderInit(authenticator2);
    }

    public void clearConnectPool() {
        this.client.connectionPool().evictAll();
    }

    public void setTimeout(int i, int i2, int i3) {
        long j = i;
        OkHttpClient.Builder callTimeout = this.client.newBuilder().readTimeout(j, TimeUnit.SECONDS).writeTimeout(i2, TimeUnit.SECONDS).connectTimeout(i3, TimeUnit.SECONDS).callTimeout(j, TimeUnit.SECONDS);
        this.client = !(callTimeout instanceof OkHttpClient.Builder) ? callTimeout.build() : NBSOkHttp3Instrumentation.builderInit(callTimeout);
    }

    public void setTimeout(int i, int i2, int i3, int i4) {
        OkHttpClient.Builder callTimeout = this.client.newBuilder().readTimeout(i, TimeUnit.SECONDS).writeTimeout(i2, TimeUnit.SECONDS).connectTimeout(i3, TimeUnit.SECONDS).callTimeout(i4, TimeUnit.SECONDS);
        this.client = !(callTimeout instanceof OkHttpClient.Builder) ? callTimeout.build() : NBSOkHttp3Instrumentation.builderInit(callTimeout);
    }

    public ArrayList<Cookie> getOKHttpCacheCookies() {
        return ((PersistentCookieJar) this.client.cookieJar()).getOKHttpCacheCookies();
    }

    public void clearOkHttpCacheCookies(boolean z) {
        if (z) {
            ((PersistentCookieJar) this.client.cookieJar()).clear();
        } else {
            ((PersistentCookieJar) this.client.cookieJar()).clearSession();
        }
    }

    public void post(String str, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        post(str, null, null, asyncHttpResponseHandler);
    }

    public void post(String str, Map<String, String> map, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        post(str, map, null, asyncHttpResponseHandler);
    }

    public void post(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        post(str, requestParams.getRealParams(), null, asyncHttpResponseHandler);
    }

    public void get(String str, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        get(str, null, null, asyncHttpResponseHandler);
    }

    public void get(String str, Map<String, String> map, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        get(str, map, null, asyncHttpResponseHandler);
    }

    public void get(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        get(str, requestParams.getRealParams(), null, asyncHttpResponseHandler);
    }

    public void get(String str, Map<String, String> map, Map<String, String> map2, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        try {
            asyncHttpResponseHandler.sendStartMessage();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (!TextUtils.isEmpty(key)) {
                        String trim = key.trim();
                        if (str.contains("?")) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            sb.append("&");
                            sb.append(trim);
                            sb.append("=");
                            sb.append(TextUtils.isEmpty(value) ? "" : value.trim());
                            str = sb.toString();
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str);
                            sb2.append("?");
                            sb2.append(trim);
                            sb2.append("=");
                            sb2.append(TextUtils.isEmpty(value) ? "" : value.trim());
                            str = sb2.toString();
                        }
                    }
                }
            }
            Request.Builder builder = new Request.Builder();
            builder.url(str);
            handleHeader(builder, map2);
            call(builder.build(), asyncHttpResponseHandler);
        } catch (Exception e) {
            asyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
            asyncHttpResponseHandler.sendFinishMessage();
        }
    }

    public void post(String str, Map<String, String> map, Map<String, String> map2, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        try {
            asyncHttpResponseHandler.sendStartMessage();
            FormBody.Builder builder = new FormBody.Builder();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (!TextUtils.isEmpty(key)) {
                        builder.add(key.trim(), TextUtils.isEmpty(value) ? "" : value.trim());
                    }
                }
            }
            FormBody build = builder.build();
            Request.Builder builder2 = new Request.Builder();
            builder2.url(str);
            builder2.post(build);
            handleHeader(builder2, map2);
            call(builder2.build(), asyncHttpResponseHandler);
        } catch (Exception e) {
            asyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
            asyncHttpResponseHandler.sendFinishMessage();
        }
    }

    public void postBytes(String str, String str2, byte[] bArr, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        try {
            asyncHttpResponseHandler.sendStartMessage();
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart(str2, str2, createProgressRequestBody(MediaType.parse("application/octet-stream"), bArr));
            call(new Request.Builder().url(str).post(builder.build()).build(), asyncHttpResponseHandler);
        } catch (Exception e) {
            asyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
            asyncHttpResponseHandler.sendFinishMessage();
        }
    }

    private <T> RequestBody createProgressRequestBody(final MediaType mediaType, final byte[] bArr) {
        return new RequestBody() { // from class: com.loopj.android.http.AsyncHttpClient.1
            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                return bArr.length;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                try {
                    Source source = Okio.source(new ByteArrayInputStream(bArr));
                    Buffer buffer = new Buffer();
                    contentLength();
                    while (true) {
                        long read = source.read(buffer, 2048L);
                        if (read == -1) {
                            return;
                        }
                        bufferedSink.write(buffer, read);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void uploadFile(String str, Map<String, Object> map, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        uploadFile(str, map, null, asyncHttpResponseHandler);
    }

    public void uploadFile(String str, Map<String, Object> map, Map<String, String> map2, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        try {
            asyncHttpResponseHandler.sendStartMessage();
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            if (map != null) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (!TextUtils.isEmpty(key)) {
                        if (value instanceof String) {
                            builder.addFormDataPart(key, (String) value);
                        } else if (value instanceof File) {
                            builder.addPart(Headers.m1782of("Content-Disposition", "form-data; name=\"" + key + "\";filename=\"\""), RequestBody.create(MediaType.parse("*/*"), (File) value));
                        } else if (value instanceof byte[]) {
                            builder.addPart(Headers.m1782of("Content-Disposition", "form-data; name=\"" + key + "\";filename=\"\""), RequestBody.create(MediaType.parse("*/*"), (byte[]) value));
                        }
                    }
                }
            }
            MultipartBody build = builder.build();
            Request.Builder builder2 = new Request.Builder();
            builder2.url(str);
            builder2.post(build);
            handleHeader(builder2, map2);
            call(builder2.build(), asyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
            asyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
            asyncHttpResponseHandler.sendFinishMessage();
        }
    }

    private void handleHeader(Request.Builder builder, Map<String, String> map) {
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    builder.header(key.trim(), value.trim());
                }
            }
        }
    }

    private void call(Request request, final AsyncHttpResponseHandler asyncHttpResponseHandler) {
        this.client.newCall(request).enqueue(new Callback() { // from class: com.loopj.android.http.AsyncHttpClient.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                iOException.printStackTrace();
                if (asyncHttpResponseHandler.getDelay() > 0) {
                    try {
                        Thread.sleep(asyncHttpResponseHandler.getDelay() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                asyncHttpResponseHandler.sendFailureMessage(iOException, iOException.getMessage());
                asyncHttpResponseHandler.sendFinishMessage();
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    asyncHttpResponseHandler.sendSuccessMessage(response.code(), response.body().string());
                    asyncHttpResponseHandler.sendFinishMessage();
                } catch (Exception e) {
                    asyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
                    asyncHttpResponseHandler.sendFinishMessage();
                }
            }
        });
    }

    public String syncGet(String str, Map<String, String> map) throws Exception {
        return syncGet(str, map, null);
    }

    public String syncGet(String str, Map<String, String> map, Map<String, String> map2) throws Exception {
        return SyncOkHttp.execute(this.client, "GET", RequestBodyWay.Form, str, map, null, map2);
    }

    public String syncPost(String str, Map<String, String> map) throws Exception {
        return syncPost(str, RequestBodyWay.Form, map, null, null);
    }

    public String syncPost(String str, String str2) throws Exception {
        return syncPost(str, RequestBodyWay.JSON, null, str2, null);
    }

    public String syncPost(String str, RequestBodyWay requestBodyWay, Map<String, String> map, String str2, Map<String, String> map2) throws Exception {
        return SyncOkHttp.execute(this.client, "POST", requestBodyWay, str, map, str2, map2);
    }

    public Observable<String> rxGet(String str, Map<String, String> map) {
        return rxGet(str, map, null, 0, 0);
    }

    public Observable<String> rxGet(String str, Map<String, String> map, int i, int i2) {
        return rxGet(str, map, null, i, i2);
    }

    public Observable<String> rxGet(String str, Map<String, String> map, Map<String, String> map2) {
        return rxGet(str, map, map2, 0, 0);
    }

    public Observable<String> rxGet(String str, Map<String, String> map, Map<String, String> map2, int i, int i2) {
        return RxOkHttp.execute(this.client, "GET", RequestBodyWay.Form, str, map, null, map2, i, i2);
    }

    public Observable<String> rxPost(String str, Map<String, String> map) {
        return rxPost(str, RequestBodyWay.Form, map, null, null, 0, 0);
    }

    public Observable<String> rxPost(String str, String str2) {
        return rxPost(str, RequestBodyWay.JSON, null, str2, null, 0, 0);
    }

    public Observable<String> rxPost(String str, Map<String, String> map, int i, int i2) {
        return rxPost(str, RequestBodyWay.Form, map, null, null, i, i2);
    }

    public Observable<String> rxPost(String str, String str2, int i, int i2) {
        return rxPost(str, RequestBodyWay.JSON, null, str2, null, i, i2);
    }

    public Observable<String> rxPost(String str, Map<String, String> map, Map<String, String> map2) {
        return rxPost(str, RequestBodyWay.Form, map, null, map2, 0, 0);
    }

    public Observable<String> rxPost(String str, Map<String, String> map, Map<String, String> map2, int i, int i2) {
        return rxPost(str, RequestBodyWay.Form, map, null, map2, i, i2);
    }

    public Observable<String> rxPost(String str, String str2, Map<String, String> map) {
        return rxPost(str, RequestBodyWay.JSON, null, str2, map, 0, 0);
    }

    public Observable<String> rxPost(String str, RequestBodyWay requestBodyWay, Map<String, String> map, String str2, Map<String, String> map2, int i, int i2) {
        return RxOkHttp.execute(this.client, "POST", requestBodyWay, str, map, str2, map2, i, i2);
    }
}
