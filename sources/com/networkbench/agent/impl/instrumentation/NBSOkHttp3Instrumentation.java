package com.networkbench.agent.impl.instrumentation;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p259h.C6427d;
import com.networkbench.agent.impl.util.C6638h;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkUrlFactory;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSOkHttp3Instrumentation {
    @Deprecated
    /* renamed from: a */
    void m9878a() {
    }

    @NBSReplaceCallSite
    public static Response.Builder body(Response.Builder builder, ResponseBody responseBody) {
        return builder.body(responseBody);
    }

    @NBSReplaceCallSite
    public static Response.Builder newBuilder(Response response) {
        return response.newBuilder();
    }

    public static OkHttpClient init() {
        synchronized (NBSOkHttp3Instrumentation.class) {
            try {
                if (C6638h.m8963w().m9063X()) {
                    C6427d c6427d = new C6427d();
                    OkHttpClient build = new OkHttpClient.Builder().addInterceptor(c6427d).build();
                    c6427d.m9996a(build);
                    return build;
                }
                return new OkHttpClient();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static OkHttpClient builderInit(OkHttpClient.Builder builder) {
        OkHttpClient build;
        synchronized (NBSOkHttp3Instrumentation.class) {
            try {
                if (C6638h.m8963w().m9063X()) {
                    if (!checkNBSInterceptors(builder)) {
                        C6427d c6427d = new C6427d();
                        build = builder.addInterceptor(c6427d).build();
                        c6427d.m9996a(build);
                    } else {
                        build = builder.build();
                    }
                    return build;
                }
                return builder.build();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static boolean checkNBSInterceptors(OkHttpClient.Builder builder) {
        return filterNBSInterceptor(builder.interceptors());
    }

    private static boolean filterNBSInterceptor(List<Interceptor> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        for (Interceptor interceptor : list) {
            if (interceptor instanceof C6427d) {
                return true;
            }
        }
        return false;
    }

    @NBSReplaceCallSite
    public static Call newCall(OkHttpClient okHttpClient, Request request) {
        return okHttpClient.newCall(request);
    }

    @NBSReplaceCallSite
    public static HttpURLConnection open(OkUrlFactory okUrlFactory, URL url) {
        try {
            url.getHost();
        } catch (Exception e) {
            C6396h.m10134h("NBSOkHttp3Instrumentation open has an error :" + e);
        }
        HttpURLConnection open = okUrlFactory.open(url);
        if (open == null) {
            return null;
        }
        if (Harvest.isHttp_network_enabled()) {
            C6396h.m10130l("okhttp3  open gather  begin !!");
            if (open instanceof HttpsURLConnection) {
                return new NBSHttpsURLConnectionExtension((HttpsURLConnection) open);
            }
            return open instanceof HttpURLConnection ? new NBSHttpURLConnectionExtension(open) : open;
        }
        return open;
    }
}
