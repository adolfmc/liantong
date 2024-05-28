package com.bytedance.sdk.openadsdk.api.plugin.p184mb;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

@NBSInstrumented
/* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4012b {
    private C4012b() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.mb.b$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class C4014mb {

        /* renamed from: mb */
        private static final C4012b f9591mb = new C4012b();
    }

    /* renamed from: mb */
    public static C4012b m16501mb() {
        return C4014mb.f9591mb;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x007f, code lost:
        if (r5 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0081, code lost:
        r5.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008f, code lost:
        if (r5 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0092, code lost:
        return null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3, types: [javax.net.ssl.HttpsURLConnection] */
    /* renamed from: mb */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m16496mb(boolean r4, java.lang.String r5, byte[] r6) {
        /*
            r3 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L8a
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L8a
            javax.net.ssl.X509TrustManager r5 = r3.m16495ox()     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L8a
            javax.net.ssl.SSLSocketFactory r5 = r3.m16497mb(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L8a
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L8a
            java.net.URLConnection r5 = r1.openConnection()     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L8a
            java.net.URLConnection r5 = com.networkbench.agent.impl.instrumentation.NBSInstrumentation.openConnection(r5)     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L8a
            javax.net.ssl.HttpsURLConnection r5 = (javax.net.ssl.HttpsURLConnection) r5     // Catch: java.lang.Throwable -> L87 java.lang.Exception -> L8a
            r1 = 5000(0x1388, float:7.006E-42)
            r5.setConnectTimeout(r1)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            com.bytedance.sdk.openadsdk.api.plugin.mb.mb r1 = com.bytedance.sdk.openadsdk.api.plugin.p184mb.C4015mb.f9592mb     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            r5.setHostnameVerifier(r1)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            if (r4 == 0) goto L4c
            if (r6 == 0) goto L4c
            int r4 = r6.length     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            if (r4 == 0) goto L4c
            r4 = 1
            r5.setDoOutput(r4)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            int r4 = r6.length     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            r5.setFixedLengthStreamingMode(r4)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            java.io.OutputStream r1 = r5.getOutputStream()     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            r4.<init>(r1)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            r4.write(r6)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            r4.flush()     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            r4.close()     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            java.lang.String r4 = "POST"
            r5.setRequestMethod(r4)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            goto L51
        L4c:
            java.lang.String r4 = "GET"
            r5.setRequestMethod(r4)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
        L51:
            int r4 = r5.getResponseCode()     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            r6 = 200(0xc8, float:2.8E-43)
            if (r4 < r6) goto L7f
            r6 = 300(0x12c, float:4.2E-43)
            if (r4 >= r6) goto L7f
            java.lang.String r4 = "Content-Type"
            java.lang.String r4 = r5.getHeaderField(r4)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            java.lang.String r6 = "utf-8"
            java.lang.String r4 = m16498mb(r4, r6)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            java.io.InputStream r1 = r5.getInputStream()     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r1 = m16499mb(r1, r2)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            r6.<init>(r1, r4)     // Catch: java.lang.Exception -> L85 java.lang.Throwable -> L93
            if (r5 == 0) goto L7e
            r5.disconnect()     // Catch: java.lang.Exception -> L7e
        L7e:
            return r6
        L7f:
            if (r5 == 0) goto L92
        L81:
            r5.disconnect()     // Catch: java.lang.Exception -> L92
            goto L92
        L85:
            r4 = move-exception
            goto L8c
        L87:
            r4 = move-exception
            r5 = r0
            goto L94
        L8a:
            r4 = move-exception
            r5 = r0
        L8c:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L93
            if (r5 == 0) goto L92
            goto L81
        L92:
            return r0
        L93:
            r4 = move-exception
        L94:
            if (r5 == 0) goto L99
            r5.disconnect()     // Catch: java.lang.Exception -> L99
        L99:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.openadsdk.api.plugin.p184mb.C4012b.m16496mb(boolean, java.lang.String, byte[]):java.lang.String");
    }

    /* renamed from: mb */
    private static byte[] m16499mb(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            return null;
        }
        if (i < 1) {
            i = 1;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[i];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    /* renamed from: mb */
    private static String m16498mb(String str, String str2) {
        if (str != null) {
            String[] split = str.split(";", 0);
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=", 0);
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str2;
    }

    /* renamed from: mb */
    private SSLSocketFactory m16497mb(X509TrustManager x509TrustManager) throws IOException {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new IOException("No System TLS", e);
        }
    }

    /* renamed from: ox */
    private X509TrustManager m16495ox() throws IOException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (GeneralSecurityException e) {
            throw new IOException("No System TLS", e);
        }
    }
}
