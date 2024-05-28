package com.networkbench.agent.impl.instrumentation;

import android.util.Log;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSOkHttpInstrumentation {
    @Deprecated
    /* renamed from: a */
    void m9877a() {
    }

    @NBSWrapReturn(className = "com/squareup/okhttp/OkHttpClient", methodDesc = "(Ljava/net/URL;)Ljava/net/HttpURLConnection;", methodName = "open")
    public static HttpURLConnection open(HttpURLConnection httpURLConnection) {
        Log.d("TingYun", "OkHttpInstrumentation - wrapping return of call to open");
        HttpURLConnection processControllerDispatch = processControllerDispatch(httpURLConnection);
        if (processControllerDispatch == null) {
            return processControllerDispatch;
        }
        if (processControllerDispatch instanceof HttpsURLConnection) {
            return new NBSHttpsURLConnectionExtension((HttpsURLConnection) processControllerDispatch);
        }
        return processControllerDispatch != null ? new NBSHttpURLConnectionExtension(processControllerDispatch) : processControllerDispatch;
    }

    @NBSWrapReturn(className = "com/squareup/okhttp/OkHttpClient", methodDesc = "(Ljava/net/URL;Ljava/net/Proxy)Ljava/net/HttpURLConnection;", methodName = "open")
    public static HttpURLConnection openWithProxy(HttpURLConnection httpURLConnection) {
        Log.d("TingYun", "OkHttpInstrumentation -wrapping return of call to openWithProxy");
        HttpURLConnection processControllerDispatch = processControllerDispatch(httpURLConnection);
        if (processControllerDispatch instanceof HttpsURLConnection) {
            return new NBSHttpsURLConnectionExtension((HttpsURLConnection) processControllerDispatch);
        }
        return processControllerDispatch != null ? new NBSHttpURLConnectionExtension(processControllerDispatch) : processControllerDispatch;
    }

    @NBSWrapReturn(className = "com/squareup/okhttp/OkUrlFactory", methodDesc = "(Ljava/net/URL;)Ljava/net/HttpURLConnection;", methodName = "open")
    public static HttpURLConnection urlFactoryOpen(HttpURLConnection httpURLConnection) {
        Log.d("TingYun", "OkHttpInstrumentation - wrapping return of call to OkUrlFactory.open...");
        HttpURLConnection processControllerDispatch = processControllerDispatch(httpURLConnection);
        if (processControllerDispatch instanceof HttpsURLConnection) {
            return new NBSHttpsURLConnectionExtension((HttpsURLConnection) processControllerDispatch);
        }
        return processControllerDispatch != null ? new NBSHttpURLConnectionExtension(processControllerDispatch) : processControllerDispatch;
    }

    private static HttpURLConnection processControllerDispatch(HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null) {
            return httpURLConnection;
        }
        httpURLConnection.getURL().getHost();
        return httpURLConnection;
    }
}
