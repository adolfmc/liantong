package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.harvest.p260a.C6450m;
import com.networkbench.agent.impl.p243c.p248e.C6287e;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6647p;
import com.networkbench.agent.impl.util.C6653u;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class InitUrlConnection {
    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String USER_AGENT = "User-Agent";
    public static final String X_AES_KEY = "X-Aes-Key";
    private static final String X_APP_NONCE = "X-App-Nonce";
    public static final String X_APP_PROCESS = "X-App-Process";
    private static final String X_APP_SIGN = "X-App-Sign";
    public static final String X_APP_START = "X-App-Start";
    private static final String X_APP_TIMESTAMP = "X-App-Timestamp";
    private static final String X_LICENSE_KEY = "X-License-Key";
    public static int port = 80;
    public static String proxy;
    private String apkSha1;
    private StringBuilder composeHeaderValue = new StringBuilder();
    private String licenseKey;
    private int messageLen;
    private String userAgent;

    public InitUrlConnection(String str, String str2, String str3) {
        this.apkSha1 = str;
        this.userAgent = str2;
        this.licenseKey = str3;
    }

    public HttpURLConnection configureUrlConnection(String str, int i) throws Exception {
        if (str == null || i < 0) {
            throw new C6632b("error");
        }
        this.composeHeaderValue = new StringBuilder();
        this.messageLen = i;
        C6396h.m10137e("configureUrlConnection  url:" + str);
        HttpURLConnection createUrlConnection = createUrlConnection(str);
        initConnection(createUrlConnection);
        addNewlensHeader(createUrlConnection);
        return createUrlConnection;
    }

    private void initConnection(HttpURLConnection httpURLConnection) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout((int) TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS));
        httpURLConnection.setChunkedStreamingMode(0);
    }

    private void addNewlensHeader(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Content-Encoding", getContentType());
        httpURLConnection.setRequestProperty("User-Agent", this.userAgent);
        httpURLConnection.setRequestProperty("X-App-Sign", this.apkSha1);
        httpURLConnection.setRequestProperty("X-License-Key", this.licenseKey);
        httpURLConnection.setRequestProperty("X-App-Process", isMainProcess());
        if (C6638h.f17114n == 0) {
            httpURLConnection.setRequestProperty("X-App-Start", C6287e.f15695d + "");
        } else {
            httpURLConnection.setRequestProperty("X-App-Start", "0");
        }
        String str = C6450m.m9963a().m9960b() + "";
        if (C6638h.m8963w().f17126B) {
            httpURLConnection.setRequestProperty("X-App-Timestamp", str);
            httpURLConnection.setRequestProperty("X-App-Nonce", composeAppNonce(str));
            StringBuilder sb = this.composeHeaderValue;
            sb.append(this.userAgent);
            sb.append(this.licenseKey);
            sb.append(getContentType());
        }
        try {
            if (C6638h.m8963w().f17182z) {
                httpURLConnection.setRequestProperty("X-Aes-Key", Harvest.getInstance().getConfiguration().encryptAesToHeader());
                this.composeHeaderValue.append(httpURLConnection.getRequestProperty("X-Aes-Key"));
            }
        } catch (C6632b e) {
            C6638h.f17124y.mo10115e("add X_AES_KEY CustomException: " + e.getMessage());
        } catch (Throwable th) {
            C6638h.f17124y.mo10121a("setRequestProperty X_AES_KEY error", th);
        }
        StringBuilder sb2 = this.composeHeaderValue;
        sb2.append(str);
        sb2.append(httpURLConnection.getRequestProperty("X-App-Nonce"));
    }

    private String composeAppNonce(String str) {
        return C6647p.m8786b(UUID.randomUUID().toString() + "#" + str);
    }

    private String getContentType() {
        return C6653u.m8721c(this.messageLen) ? "identity" : "deflate";
    }

    private HttpURLConnection createUrlConnection(String str) throws Exception {
        URL url = new URL(str);
        String str2 = proxy;
        if (str2 != null) {
            return (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str2, port)));
        }
        return (HttpURLConnection) url.openConnection();
    }

    public String composeHeadValue() {
        return this.composeHeaderValue.toString();
    }

    public static String isMainProcess() {
        return String.valueOf(C6638h.f17114n + 1);
    }

    public HttpURLConnection configureUrlConnectionOfWebView(String str) throws Exception {
        if (str == null) {
            return null;
        }
        HttpURLConnection createUrlConnection = createUrlConnection(str);
        initConnection(createUrlConnection);
        return createUrlConnection;
    }
}
