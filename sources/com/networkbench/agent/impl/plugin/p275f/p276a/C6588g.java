package com.networkbench.agent.impl.plugin.p275f.p276a;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.f.a.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6588g {

    /* renamed from: a */
    private static final int f16884a = 65536;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m9315a(String str) throws IOException {
        byte[] m9313b = m9313b(str);
        if (m9313b == null) {
            return null;
        }
        return new String(m9313b);
    }

    /* renamed from: b */
    static byte[] m9313b(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(20000);
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        int contentLength = httpURLConnection.getContentLength();
        if (contentLength < 0) {
            contentLength = 65536;
        }
        if (contentLength > 65536) {
            return null;
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        byte[] bArr = new byte[65536];
        int read = inputStream.read(bArr);
        inputStream.close();
        if (read <= 0) {
            return null;
        }
        if (read < bArr.length) {
            byte[] bArr2 = new byte[read];
            System.arraycopy(bArr, 0, bArr2, 0, read);
            return bArr2;
        }
        return bArr;
    }

    /* renamed from: a */
    static void m9316a(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    /* renamed from: b */
    static void m9314b(Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 11) {
            AsyncTask.execute(runnable);
        } else {
            new Thread(runnable).start();
        }
    }
}
