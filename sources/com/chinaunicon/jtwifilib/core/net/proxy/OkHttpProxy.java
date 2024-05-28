package com.chinaunicon.jtwifilib.core.net.proxy;

import com.chinaunicon.jtwifilib.core.net.builder.GetRequestBuilder;
import com.chinaunicon.jtwifilib.core.net.builder.PostRequestBuilder;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex */
public class OkHttpProxy {
    private static OkHttpClient mHttpClient;

    public static void download(String str) {
    }

    private static OkHttpClient init() {
        synchronized (OkHttpProxy.class) {
            if (mHttpClient == null) {
                mHttpClient = NBSOkHttp3Instrumentation.init();
            }
        }
        return mHttpClient;
    }

    public static OkHttpClient getInstance() {
        OkHttpClient okHttpClient = mHttpClient;
        return okHttpClient == null ? init() : okHttpClient;
    }

    public static void setInstance(OkHttpClient okHttpClient) {
        mHttpClient = okHttpClient;
    }

    public static GetRequestBuilder get() {
        return new GetRequestBuilder();
    }

    public static PostRequestBuilder post() {
        return new PostRequestBuilder();
    }

    public static void cancel(Object obj) {
        Dispatcher dispatcher = getInstance().dispatcher();
        for (Call call : dispatcher.queuedCalls()) {
            if (obj.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call2 : dispatcher.runningCalls()) {
            if (obj.equals(call2.request().tag())) {
                call2.cancel();
            }
        }
    }
}
