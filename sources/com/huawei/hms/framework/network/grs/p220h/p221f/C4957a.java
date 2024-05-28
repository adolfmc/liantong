package com.huawei.hms.framework.network.grs.p220h.p221f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.p220h.C4951a;
import com.huawei.hms.framework.network.grs.p220h.p222g.C4958a;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.h.f.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4957a {
    /* renamed from: a */
    public static HttpsURLConnection m14851a(String str, Context context, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        URLConnection openConnection = new URL(str).openConnection();
        if (!(openConnection instanceof HttpsURLConnection)) {
            Logger.m15045w("URLConnectionHelper", "urlConnection is not an instance of HttpsURLConnection");
            return null;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
        try {
            httpsURLConnection.setSSLSocketFactory(C4958a.m14848a(context));
            httpsURLConnection.setHostnameVerifier(C4958a.m14849a());
        } catch (IllegalArgumentException unused) {
            Logger.m15045w("URLConnectionHelper", "init https ssl socket failed.");
        }
        httpsURLConnection.setConnectTimeout(10000);
        httpsURLConnection.setReadTimeout(10000);
        httpsURLConnection.setDoOutput(true);
        httpsURLConnection.setUseCaches(false);
        String m14862b = C4951a.m14862b(context, "NetworkKit-grs", str2);
        Logger.m15054d("URLConnectionHelper", "request to grs server with a User-Agent header is: " + m14862b);
        httpsURLConnection.setRequestProperty("User-Agent", m14862b);
        return httpsURLConnection;
    }

    /* renamed from: a */
    public static void m14850a(HttpsURLConnection httpsURLConnection, String str) {
        String str2;
        String str3;
        if (str == null) {
            str2 = "URLConnectionHelper";
            str3 = "sendHttpBody: The Body Data is Null";
        } else if (httpsURLConnection != null) {
            OutputStream outputStream = null;
            try {
                outputStream = httpsURLConnection.getOutputStream();
                outputStream.write(str.getBytes("UTF-8"));
                outputStream.flush();
                return;
            } finally {
                IoUtils.closeSecure(outputStream);
            }
        } else {
            str2 = "URLConnectionHelper";
            str3 = "sendHttpBody: HttpsURLConnection is Null";
        }
        Logger.m15049i(str2, str3);
    }
}
