package com.huawei.hms.hatool;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.w */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC5031w {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.hatool.w$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5032a extends Exception {
        C5032a(String str) {
            super(str);
        }
    }

    /* renamed from: a */
    public static C5009n0 m14448a(String str, byte[] bArr, Map<String, String> map) {
        return m14447a(str, bArr, map, "POST");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0156  */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.Map<java.lang.String, java.lang.String>, java.util.Map] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v18, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v19, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v20, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v21, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v22, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v23, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v24, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v33, types: [java.io.OutputStream, java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v14, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v15, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v16, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18, types: [java.io.Closeable, java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.Closeable] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.huawei.hms.hatool.C5009n0 m14447a(java.lang.String r4, byte[] r5, java.util.Map<java.lang.String, java.lang.String> r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hatool.AbstractC5031w.m14447a(java.lang.String, byte[], java.util.Map, java.lang.String):com.huawei.hms.hatool.n0");
    }

    /* renamed from: a */
    private static HttpURLConnection m14449a(String str, int i, Map<String, String> map, String str2) {
        if (TextUtils.isEmpty(str)) {
            C5029v.m14458b("hmsSdk", "CreateConnection: invalid urlPath.");
            return null;
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        m14446a(httpURLConnection);
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(i));
        httpURLConnection.setRequestProperty("Connection", "close");
        if (map != null && map.size() >= 1) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key != null && !TextUtils.isEmpty(key)) {
                    httpURLConnection.setRequestProperty(key, entry.getValue());
                }
            }
        }
        return httpURLConnection;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m14446a(java.net.HttpURLConnection r3) {
        /*
            boolean r0 = r3 instanceof javax.net.ssl.HttpsURLConnection
            if (r0 == 0) goto L41
            javax.net.ssl.HttpsURLConnection r3 = (javax.net.ssl.HttpsURLConnection) r3
            r0 = 0
            android.content.Context r1 = com.huawei.hms.hatool.AbstractC5020q0.m14526i()     // Catch: java.lang.IllegalAccessException -> L10 java.io.IOException -> L15 java.security.GeneralSecurityException -> L1a java.security.KeyStoreException -> L1f java.security.NoSuchAlgorithmException -> L24
            com.huawei.secure.android.common.ssl.SecureSSLSocketFactory r0 = com.huawei.secure.android.common.ssl.SecureSSLSocketFactory.getInstance(r1)     // Catch: java.lang.IllegalAccessException -> L10 java.io.IOException -> L15 java.security.GeneralSecurityException -> L1a java.security.KeyStoreException -> L1f java.security.NoSuchAlgorithmException -> L24
            goto L2b
        L10:
            java.lang.String r1 = "hmsSdk"
            java.lang.String r2 = "getSocketFactory(): Illegal Access Exception "
            goto L28
        L15:
            java.lang.String r1 = "hmsSdk"
            java.lang.String r2 = "getSocketFactory(): IO Exception!"
            goto L28
        L1a:
            java.lang.String r1 = "hmsSdk"
            java.lang.String r2 = "getSocketFactory(): General Security Exception"
            goto L28
        L1f:
            java.lang.String r1 = "hmsSdk"
            java.lang.String r2 = "getSocketFactory(): Key Store exception"
            goto L28
        L24:
            java.lang.String r1 = "hmsSdk"
            java.lang.String r2 = "getSocketFactory(): Algorithm Exception!"
        L28:
            com.huawei.hms.hatool.C5029v.m14451f(r1, r2)
        L2b:
            if (r0 == 0) goto L39
            r3.setSSLSocketFactory(r0)
            com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier r0 = new com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier
            r0.<init>()
            r3.setHostnameVerifier(r0)
            goto L41
        L39:
            com.huawei.hms.hatool.w$a r3 = new com.huawei.hms.hatool.w$a
            java.lang.String r0 = "No ssl socket factory set"
            r3.<init>(r0)
            throw r3
        L41:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hatool.AbstractC5031w.m14446a(java.net.HttpURLConnection):void");
    }

    /* renamed from: b */
    private static String m14445b(HttpURLConnection httpURLConnection) {
        InputStream inputStream = null;
        try {
            try {
                inputStream = httpURLConnection.getInputStream();
                return C5000k1.m14634a(inputStream);
            } catch (IOException unused) {
                int responseCode = httpURLConnection.getResponseCode();
                C5029v.m14451f("hmsSdk", "When Response Content From Connection inputStream operation exception! " + responseCode);
                C5000k1.m14637a((Closeable) inputStream);
                return "";
            }
        } finally {
            C5000k1.m14637a((Closeable) inputStream);
        }
    }
}
