package com.baidu.mapapi.http;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import com.baidu.mapapi.JNIInitializer;
import com.baidu.mapapi.common.Logger;
import com.baidu.mapsdkplatform.comapi.util.SysOSAPI;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HttpClient {
    public static boolean isHttpsEnable = true;

    /* renamed from: a */
    HttpURLConnection f5876a;

    /* renamed from: b */
    private String f5877b = null;

    /* renamed from: c */
    private String f5878c = null;

    /* renamed from: d */
    private int f5879d;

    /* renamed from: e */
    private int f5880e;

    /* renamed from: f */
    private String f5881f;

    /* renamed from: g */
    private ProtoResultCallback f5882g;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum HttpStateError {
        NO_ERROR,
        NETWORK_ERROR,
        INNER_ERROR,
        REQUEST_ERROR,
        SERVER_ERROR
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class ProtoResultCallback {
        public abstract void onFailed(HttpStateError httpStateError);

        public abstract void onSuccess(String str);
    }

    public void setReadTimeOut(int i) {
        this.f5880e = i;
    }

    public void setMaxTimeOut(int i) {
        this.f5879d = i;
    }

    public HttpClient(String str, ProtoResultCallback protoResultCallback) {
        this.f5881f = str;
        this.f5882g = protoResultCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0159 A[Catch: Exception -> 0x015f, TryCatch #2 {Exception -> 0x015f, blocks: (B:14:0x003a, B:26:0x0075, B:27:0x007b, B:29:0x007f, B:78:0x014f, B:79:0x0155, B:81:0x0159, B:82:0x015e, B:70:0x013a, B:71:0x0140, B:73:0x0144, B:55:0x0106, B:57:0x010a), top: B:90:0x003a }] */
    /* JADX WARN: Type inference failed for: r0v11, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void request(java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.http.HttpClient.request(java.lang.String):void");
    }

    protected boolean checkNetwork() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) JNIInitializer.getCachedContext().getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                return networkCapabilities != null && networkCapabilities.hasCapability(12);
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        } catch (Exception e) {
            if (Logger.debugEnable()) {
                e.printStackTrace();
            } else {
                Logger.logW("HttpClient", e.getMessage());
            }
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    private HttpURLConnection m19029a() {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(this.f5877b);
            if (isHttpsEnable) {
                httpURLConnection = (HttpsURLConnection) NBSInstrumentation.openConnection(url.openConnection());
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new C2742a());
            } else {
                httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(url.openConnection());
            }
            httpURLConnection.setRequestMethod(this.f5881f);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(this.f5879d);
            httpURLConnection.setReadTimeout(this.f5880e);
            return httpURLConnection;
        } catch (Exception e) {
            Log.e("HttpClient", "url connect failed");
            if (Logger.debugEnable()) {
                e.printStackTrace();
                return null;
            }
            Logger.logW("HttpClient", e.getMessage());
            return null;
        }
    }

    public static String getPhoneInfo() {
        return SysOSAPI.m18133e();
    }

    public static String getAuthToken() {
        return SysOSAPI.f7443c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapapi.http.HttpClient$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2742a implements HostnameVerifier {
        C2742a() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
        }
    }
}
