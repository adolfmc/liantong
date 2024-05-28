package com.networkbench.agent.impl.instrumentation;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p251d.RunnableC6335b;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSInstrumentation {
    private static final InterfaceC6393e log = C6394f.m10150a();

    @NBSWrapReturn(className = "java/net/URL", methodDesc = "()Ljava/net/URLConnection;", methodName = "openConnection")
    public static URLConnection openConnection(URLConnection uRLConnection) {
        if (uRLConnection == null) {
            return uRLConnection;
        }
        try {
            if (Harvest.isHttp_network_enabled()) {
                C6396h.m10130l("URLConnection openConnection gather  begin !!");
                if (uRLConnection instanceof HttpsURLConnection) {
                    return new NBSHttpsURLConnectionExtension((HttpsURLConnection) uRLConnection);
                }
                return uRLConnection instanceof HttpURLConnection ? new NBSHttpURLConnectionExtension((HttpURLConnection) uRLConnection) : uRLConnection;
            }
            return uRLConnection;
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("NBSInstrumentation openConnection() has an error : " + e);
            return uRLConnection;
        }
    }

    @NBSWrapReturn(className = "java.net.URL", methodDesc = "(Ljava/net/Proxy;)Ljava/net/URLConnection;", methodName = "openConnection")
    public static URLConnection openConnectionWithProxy(URLConnection uRLConnection) {
        if (uRLConnection == null) {
            return uRLConnection;
        }
        try {
            if (Harvest.isHttp_network_enabled()) {
                C6396h.m10130l("URLConnection openConnectionWithProxy gather  begin !!");
                if (uRLConnection instanceof HttpsURLConnection) {
                    return new NBSHttpsURLConnectionExtension((HttpsURLConnection) uRLConnection);
                }
                return uRLConnection instanceof HttpURLConnection ? new NBSHttpURLConnectionExtension((HttpURLConnection) uRLConnection) : uRLConnection;
            }
            return uRLConnection;
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("NBSInstrumentation openConnectionWithProxy() has an error : " + e);
            return uRLConnection;
        }
    }

    private static URLConnection controllerDispatchConnection(String str, URL url, URLConnection uRLConnection) {
        String m10354a = RunnableC6335b.m10355a().m10354a(str);
        if (TextUtils.isEmpty(m10354a)) {
            return uRLConnection;
        }
        try {
            return new URL(url.getProtocol() + "://" + m10354a + url.getFile()).openConnection();
        } catch (IOException e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("NBSInstrumentation---urlConnection " + e.getMessage());
            return uRLConnection;
        }
    }
}
