package com.baidu.lbsapi.auth;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.lbsapi.auth.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2592g {

    /* renamed from: a */
    private Context f4977a;

    /* renamed from: b */
    private String f4978b = null;

    /* renamed from: c */
    private HashMap<String, String> f4979c = null;

    /* renamed from: d */
    private String f4980d = null;

    public C2592g(Context context) {
        this.f4977a = context;
    }

    /* renamed from: a */
    private String m19652a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (networkCapabilities != null) {
                    return networkCapabilities.hasTransport(1) ? "WIFI" : networkCapabilities.hasTransport(0) ? "CELLULAR" : networkCapabilities.hasTransport(3) ? "ETHERNET" : networkCapabilities.hasTransport(6) ? "LoWPAN" : networkCapabilities.hasTransport(4) ? "VPN" : networkCapabilities.hasTransport(5) ? "WifiAware" : "wifi";
                }
                return "wifi";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return extraInfo != null ? (extraInfo.trim().toLowerCase().equals("cmwap") || extraInfo.trim().toLowerCase().equals("uniwap") || extraInfo.trim().toLowerCase().equals("3gwap") || extraInfo.trim().toLowerCase().equals("ctwap")) ? extraInfo.trim().toLowerCase().equals("ctwap") ? "ctwap" : "cmwap" : "wifi" : "wifi";
            }
            return null;
        } catch (Exception e) {
            if (C2583a.f4967a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x0190, code lost:
        if (com.baidu.lbsapi.auth.C2583a.f4967a == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x01c1, code lost:
        if (com.baidu.lbsapi.auth.C2583a.f4967a == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0102, code lost:
        if (com.baidu.lbsapi.auth.C2583a.f4967a == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0104, code lost:
        r13.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0160, code lost:
        if (com.baidu.lbsapi.auth.C2583a.f4967a == false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0169 A[Catch: all -> 0x0130, TryCatch #9 {all -> 0x0130, blocks: (B:7:0x002b, B:89:0x0135, B:91:0x0139, B:92:0x013c, B:102:0x0165, B:104:0x0169, B:105:0x016c, B:115:0x0196, B:117:0x019a, B:118:0x019d), top: B:149:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x019a A[Catch: all -> 0x0130, TryCatch #9 {all -> 0x0130, blocks: (B:7:0x002b, B:89:0x0135, B:91:0x0139, B:92:0x013c, B:102:0x0165, B:104:0x0169, B:105:0x016c, B:115:0x0196, B:117:0x019a, B:118:0x019d), top: B:149:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01c7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0189 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x00fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0159 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b3 A[Catch: all -> 0x0109, TryCatch #4 {all -> 0x0109, blocks: (B:45:0x00af, B:47:0x00b3, B:48:0x00ce), top: B:145:0x00af }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f5 A[Catch: Exception -> 0x011a, IOException -> 0x011c, MalformedURLException -> 0x011e, all -> 0x0120, TRY_LEAVE, TryCatch #0 {all -> 0x0120, blocks: (B:8:0x002f, B:64:0x010e, B:66:0x0116, B:67:0x0119, B:51:0x00ed, B:53:0x00f5, B:29:0x0090, B:31:0x0098), top: B:144:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x010c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0116 A[Catch: Exception -> 0x011a, IOException -> 0x011c, MalformedURLException -> 0x011e, all -> 0x0120, TryCatch #0 {all -> 0x0120, blocks: (B:8:0x002f, B:64:0x010e, B:66:0x0116, B:67:0x0119, B:51:0x00ed, B:53:0x00f5, B:29:0x0090, B:31:0x0098), top: B:144:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0139 A[Catch: all -> 0x0130, TryCatch #9 {all -> 0x0130, blocks: (B:7:0x002b, B:89:0x0135, B:91:0x0139, B:92:0x013c, B:102:0x0165, B:104:0x0169, B:105:0x016c, B:115:0x0196, B:117:0x019a, B:118:0x019d), top: B:149:0x002b }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m19650a(javax.net.ssl.HttpsURLConnection r13) {
        /*
            Method dump skipped, instructions count: 557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.C2592g.m19650a(javax.net.ssl.HttpsURLConnection):void");
    }

    /* renamed from: b */
    private static String m19648b(HashMap<String, String> hashMap) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return sb.toString();
    }

    /* renamed from: b */
    private HttpsURLConnection m19649b() {
        String str;
        try {
            URL url = new URL(this.f4978b);
            C2583a.m19676a("https URL: " + this.f4978b);
            String m19652a = m19652a(this.f4977a);
            if (m19652a != null && !m19652a.equals("")) {
                C2583a.m19676a("checkNetwork = " + m19652a);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) (m19652a.equals("cmwap") ? NBSInstrumentation.openConnectionWithProxy(url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80)))) : m19652a.equals("ctwap") ? NBSInstrumentation.openConnectionWithProxy(url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80)))) : NBSInstrumentation.openConnection(url.openConnection()));
                httpsURLConnection.setHostnameVerifier(new C2593h(this));
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setConnectTimeout(50000);
                httpsURLConnection.setReadTimeout(50000);
                return httpsURLConnection;
            }
            C2583a.m19674c("Current network is not available.");
            this.f4980d = ErrorMessage.m19699a(-10, "Current network is not available.");
            return null;
        } catch (MalformedURLException e) {
            if (C2583a.f4967a) {
                e.printStackTrace();
                C2583a.m19676a(e.getMessage());
            }
            str = "Auth server could not be parsed as a URL.";
            this.f4980d = ErrorMessage.m19699a(-11, str);
            return null;
        } catch (Exception e2) {
            if (C2583a.f4967a) {
                e2.printStackTrace();
                C2583a.m19676a(e2.getMessage());
            }
            str = "Init httpsurlconnection failed.";
            this.f4980d = ErrorMessage.m19699a(-11, str);
            return null;
        }
    }

    /* renamed from: c */
    private HashMap<String, String> m19647c(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (String str : hashMap.keySet()) {
            String str2 = str.toString();
            hashMap2.put(str2, hashMap.get(str2));
        }
        return hashMap2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public String m19651a(HashMap<String, String> hashMap) {
        this.f4979c = m19647c(hashMap);
        this.f4978b = this.f4979c.get("url");
        HttpsURLConnection m19649b = m19649b();
        if (m19649b == null) {
            C2583a.m19674c("syncConnect failed,httpsURLConnection is null");
        } else {
            m19650a(m19649b);
        }
        return this.f4980d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m19653a() {
        C2583a.m19676a("checkNetwork start");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f4977a.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                return networkCapabilities != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16);
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            C2583a.m19676a("checkNetwork end");
            return true;
        } catch (Exception e) {
            if (C2583a.f4967a) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
