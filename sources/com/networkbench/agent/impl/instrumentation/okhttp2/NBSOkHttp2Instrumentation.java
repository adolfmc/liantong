package com.networkbench.agent.impl.instrumentation.okhttp2;

import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSHttpURLConnectionExtension;
import com.networkbench.agent.impl.instrumentation.NBSHttpsURLConnectionExtension;
import com.networkbench.agent.impl.instrumentation.NBSReplaceCallSite;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6643l;
import com.networkbench.agent.impl.util.C6653u;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSOkHttp2Instrumentation {
    @NBSReplaceCallSite
    public static Call newCall(OkHttpClient okHttpClient, Request request) {
        try {
            Log.d("TingYun", "OkHttpInstrumentation2 - wrapping newCall");
            if (C6638h.m8963w().m9063X() && isSpecificOkhttp(C6653u.m8695i()) == 1) {
                return new NBSCallExtension(okHttpClient, request);
            }
            return okHttpClient.newCall(request);
        } catch (Throwable unused) {
            return okHttpClient.newCall(request);
        }
    }

    public static int isSpecificOkhttp(String str) {
        try {
            C6643l c6643l = new C6643l(str);
            if (c6643l.m8897a() == 0) {
                return 0;
            }
            if (c6643l.m8897a() < 2) {
                return 1;
            }
            if (c6643l.m8897a() == 2) {
                if (c6643l.m8896b() < 2) {
                    return 1;
                }
            }
            return 2;
        } catch (Throwable unused) {
            return 0;
        }
    }

    @NBSReplaceCallSite
    public static HttpURLConnection open(OkUrlFactory okUrlFactory, URL url) {
        HttpURLConnection open = okUrlFactory.open(url);
        if (open instanceof HttpsURLConnection) {
            return new NBSHttpsURLConnectionExtension((HttpsURLConnection) open);
        }
        return open instanceof HttpURLConnection ? new NBSHttpURLConnectionExtension(open) : open;
    }

    @NBSReplaceCallSite
    public static ResponseBody body(Response response) {
        return response.body();
    }

    public static OkHttpClient init() {
        try {
            if (C6638h.m8963w().m9063X() && isSpecificOkhttp(C6653u.m8695i()) == 2) {
                NBSOkHttp2Interceptor nBSOkHttp2Interceptor = new NBSOkHttp2Interceptor();
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.interceptors().add(nBSOkHttp2Interceptor);
                nBSOkHttp2Interceptor.setClient(okHttpClient);
                return okHttpClient;
            }
            return new OkHttpClient();
        } catch (Throwable unused) {
            return new OkHttpClient();
        }
    }

    public static Map<String, List<String>> getHeaderForOkhttp2(Headers headers) {
        if (headers == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            List list = (List) linkedHashMap.get(name);
            if (list == null) {
                list = new ArrayList(2);
                linkedHashMap.put(name, list);
            }
            list.add(headers.value(i));
        }
        return linkedHashMap;
    }
}
