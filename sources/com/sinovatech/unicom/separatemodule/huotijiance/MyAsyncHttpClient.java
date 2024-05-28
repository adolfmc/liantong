package com.sinovatech.unicom.separatemodule.huotijiance;

import android.content.Context;
import android.text.TextUtils;
import com.loopj.android.http.RequestCookie;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.cookie.PersistentCookieJar;
import com.loopj.android.http.cookie.cache.SetCookieCache;
import com.loopj.android.http.cookie.persistence.SharedPrefsCookiePersistor;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionSpec;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyAsyncHttpClient {
    private OkHttpClient client;
    private final String TAG = "OKHttp3";
    private RequestCookie requestCookie = new RequestCookie();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum RequestBodyWay {
        Form,
        JSON
    }

    public MyAsyncHttpClient(Context context) {
        OkHttpClient.Builder connectionSpecs = new OkHttpClient.Builder().retryOnConnectionFailure(true).cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context), this.requestCookie)).connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT));
        this.client = !(connectionSpecs instanceof OkHttpClient.Builder) ? connectionSpecs.build() : NBSOkHttp3Instrumentation.builderInit(connectionSpecs);
        this.client.dispatcher().setMaxRequestsPerHost(10);
    }

    public OkHttpClient getOkHttpClient() {
        return this.client;
    }

    public void addCookieForAddToReqeust(Cookie cookie) {
        this.requestCookie.addCookieForAddToReqeust(cookie);
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

    public void setTimeout(int i, int i2, int i3) {
        OkHttpClient.Builder connectTimeout = this.client.newBuilder().readTimeout(i, TimeUnit.SECONDS).writeTimeout(i2, TimeUnit.SECONDS).connectTimeout(i3, TimeUnit.SECONDS);
        this.client = !(connectTimeout instanceof OkHttpClient.Builder) ? connectTimeout.build() : NBSOkHttp3Instrumentation.builderInit(connectTimeout);
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

    public void post(String str, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        post(str, null, null, myAsyncHttpResponseHandler);
    }

    public void post(String str, Map<String, String> map, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        post(str, map, null, myAsyncHttpResponseHandler);
    }

    public void post(String str, RequestParams requestParams, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        post(str, requestParams.getRealParams(), null, myAsyncHttpResponseHandler);
    }

    public void get(String str, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        get(str, null, null, myAsyncHttpResponseHandler);
    }

    public void get(String str, Map<String, String> map, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        get(str, map, null, myAsyncHttpResponseHandler);
    }

    public void get(String str, RequestParams requestParams, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        get(str, requestParams.getRealParams(), null, myAsyncHttpResponseHandler);
    }

    public void get(String str, Map<String, String> map, Map<String, String> map2, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        try {
            myAsyncHttpResponseHandler.sendStartMessage();
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
            call(builder.build(), myAsyncHttpResponseHandler);
        } catch (Exception e) {
            myAsyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
            myAsyncHttpResponseHandler.sendFinishMessage();
        }
    }

    public void post(String str, Map<String, String> map, Map<String, String> map2, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        try {
            myAsyncHttpResponseHandler.sendStartMessage();
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
            call(builder2.build(), myAsyncHttpResponseHandler);
        } catch (Exception e) {
            myAsyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
            myAsyncHttpResponseHandler.sendFinishMessage();
        }
    }

    public void postJson(String str, String str2, Map<String, String> map, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        try {
            myAsyncHttpResponseHandler.sendStartMessage();
            MediaType parse = MediaType.parse("application/json; charset=utf-8");
            if (str2 == null) {
                str2 = "";
            }
            RequestBody create = RequestBody.create(parse, str2);
            Request.Builder builder = new Request.Builder();
            builder.url(str);
            builder.post(create);
            handleHeader(builder, map);
            call(builder.build(), myAsyncHttpResponseHandler);
        } catch (Exception e) {
            myAsyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
            myAsyncHttpResponseHandler.sendFinishMessage();
        }
    }

    public void uploadFile(String str, Map<String, Object> map, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        uploadFile(str, map, null, myAsyncHttpResponseHandler);
    }

    public void uploadFile(String str, Map<String, Object> map, Map<String, String> map2, MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        try {
            myAsyncHttpResponseHandler.sendStartMessage();
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
                            File file = (File) value;
                            builder.addFormDataPart(key, file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
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
            call(builder2.build(), myAsyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
            myAsyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
            myAsyncHttpResponseHandler.sendFinishMessage();
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

    private void call(Request request, final MyAsyncHttpResponseHandler myAsyncHttpResponseHandler) {
        this.client.newCall(request).enqueue(new Callback() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.MyAsyncHttpClient.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                iOException.printStackTrace();
                if (myAsyncHttpResponseHandler.getDelay() > 0) {
                    try {
                        Thread.sleep(myAsyncHttpResponseHandler.getDelay() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                myAsyncHttpResponseHandler.sendFailureMessage(iOException, iOException.getMessage());
                myAsyncHttpResponseHandler.sendFinishMessage();
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    myAsyncHttpResponseHandler.sendSuccessMessage(response.code(), response.body().string());
                    myAsyncHttpResponseHandler.sendFinishMessage();
                } catch (Exception e) {
                    myAsyncHttpResponseHandler.sendFailureMessage(e, e.getMessage());
                    myAsyncHttpResponseHandler.sendFinishMessage();
                }
            }
        });
    }
}
