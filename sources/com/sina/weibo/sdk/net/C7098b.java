package com.sina.weibo.sdk.net;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.sina.weibo.sdk.net.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7098b implements InterfaceC7097a {
    @Override // com.sina.weibo.sdk.net.InterfaceC7097a
    /* renamed from: a */
    public final InterfaceC7103f mo8058a(InterfaceC7100d interfaceC7100d) {
        InputStream errorStream;
        String url = interfaceC7100d.getUrl();
        Bundle params = interfaceC7100d.getParams();
        if (params != null && params.size() != 0 && !TextUtils.isEmpty(url)) {
            Uri parse = Uri.parse(url);
            if (params != null && !params.isEmpty()) {
                Uri.Builder buildUpon = parse.buildUpon();
                for (String str : params.keySet()) {
                    buildUpon.appendQueryParameter(str, String.valueOf(params.get(str)));
                }
                parse = buildUpon.build();
            }
            if (parse != null) {
                url = parse.toString();
            }
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(url).openConnection());
        try {
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                Bundle bundle = new Bundle();
                bundle.putString("Content-Type", "application/x-www-form-urlencoded");
                m8056a(httpURLConnection, bundle);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setReadTimeout(interfaceC7100d.getReadTimeout());
                httpURLConnection.setConnectTimeout(interfaceC7100d.getConnectTimeout());
                httpURLConnection.connect();
                m8057a(httpURLConnection.getOutputStream(), interfaceC7100d);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    errorStream = httpURLConnection.getInputStream();
                } else {
                    errorStream = httpURLConnection.getErrorStream();
                }
                return new C7104g(responseCode, errorStream);
            } catch (Exception e) {
                throw new Throwable(e.getMessage());
            }
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    /* renamed from: a */
    private static void m8056a(HttpURLConnection httpURLConnection, Bundle bundle) {
        for (String str : bundle.keySet()) {
            httpURLConnection.addRequestProperty(str, String.valueOf(bundle.get(str)));
        }
    }

    /* renamed from: a */
    private static void m8057a(OutputStream outputStream, InterfaceC7100d interfaceC7100d) {
        Bundle mo8055d = interfaceC7100d.mo8055d();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : mo8055d.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            String valueOf = String.valueOf(mo8055d.get(str));
            try {
                sb.append(URLEncoder.encode(str, "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(valueOf, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.write(sb.toString().getBytes("UTF-8"));
            dataOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
