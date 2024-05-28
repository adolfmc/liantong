package com.huawei.hms.opendevice;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactory;
import com.huawei.secure.android.common.util.IOUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.opendevice.h */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class HttpClient {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: HttpClient.java */
    /* renamed from: com.huawei.hms.opendevice.h$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    enum EnumC5042a {
        GET("GET"),
        POST("POST");
        

        /* renamed from: a */
        private String f11549a;

        EnumC5042a(String str) {
            this.f11549a = str;
        }

        /* renamed from: a */
        public String m14374a() {
            return this.f11549a;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r6v10, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r6v17, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15, types: [java.io.OutputStream, java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v22, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.InputStream] */
    /* renamed from: a */
    public static String m14376a(Context context, String str, String str2, Map<String, String> map) {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        InputStream inputStream;
        OutputStream outputStream;
        HttpsURLConnection httpsURLConnection;
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        if (str2 == 0 || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        int i = -1;
        try {
            try {
                context = m14375a((Context) context, (String) str, map, EnumC5042a.POST.m14374a());
                if (context == 0) {
                    IOUtil.closeSecure((OutputStream) null);
                    IOUtil.closeSecure((InputStream) null);
                    IOUtil.closeSecure((InputStream) null);
                    StreamUtil.m14331a((HttpsURLConnection) context);
                    HMSLog.m14110i("PushHttpsClient", "close connection");
                    return null;
                }
                try {
                    str = new BufferedOutputStream(context.getOutputStream());
                    try {
                        str.write(str2.getBytes("UTF-8"));
                        str.flush();
                        i = context.getResponseCode();
                        HMSLog.m14115d("PushHttpsClient", "https post response code: " + i);
                        if (i >= 400) {
                            str2 = context.getErrorStream();
                        } else {
                            str2 = context.getInputStream();
                        }
                        try {
                            bufferedInputStream = new BufferedInputStream(str2);
                        } catch (IOException unused) {
                            bufferedInputStream = null;
                            HMSLog.m14109w("PushHttpsClient", "https execute encounter IOException - http code:" + i);
                            httpsURLConnection = context;
                            outputStream = str;
                            inputStream = str2;
                            IOUtil.closeSecure(outputStream);
                            IOUtil.closeSecure(inputStream);
                            IOUtil.closeSecure((InputStream) bufferedInputStream);
                            StreamUtil.m14331a(httpsURLConnection);
                            HMSLog.m14110i("PushHttpsClient", "close connection");
                            return null;
                        } catch (RuntimeException unused2) {
                            bufferedInputStream = null;
                            HMSLog.m14109w("PushHttpsClient", "https execute encounter RuntimeException - http code:" + i);
                            httpsURLConnection = context;
                            outputStream = str;
                            inputStream = str2;
                            IOUtil.closeSecure(outputStream);
                            IOUtil.closeSecure(inputStream);
                            IOUtil.closeSecure((InputStream) bufferedInputStream);
                            StreamUtil.m14331a(httpsURLConnection);
                            HMSLog.m14110i("PushHttpsClient", "close connection");
                            return null;
                        } catch (Exception unused3) {
                            bufferedInputStream = null;
                            HMSLog.m14109w("PushHttpsClient", "https execute encounter unknown exception - http code:" + i);
                            httpsURLConnection = context;
                            outputStream = str;
                            inputStream = str2;
                            IOUtil.closeSecure(outputStream);
                            IOUtil.closeSecure(inputStream);
                            IOUtil.closeSecure((InputStream) bufferedInputStream);
                            StreamUtil.m14331a(httpsURLConnection);
                            HMSLog.m14110i("PushHttpsClient", "close connection");
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            IOUtil.closeSecure((OutputStream) str);
                            IOUtil.closeSecure((InputStream) str2);
                            IOUtil.closeSecure((InputStream) r0);
                            StreamUtil.m14331a((HttpsURLConnection) context);
                            HMSLog.m14110i("PushHttpsClient", "close connection");
                            throw th;
                        }
                    } catch (IOException unused4) {
                        str2 = null;
                    } catch (RuntimeException unused5) {
                        str2 = null;
                    } catch (Exception unused6) {
                        str2 = null;
                    } catch (Throwable th3) {
                        th = th3;
                        str2 = 0;
                    }
                } catch (IOException unused7) {
                    str = null;
                    str2 = null;
                    bufferedInputStream = null;
                    HMSLog.m14109w("PushHttpsClient", "https execute encounter IOException - http code:" + i);
                    httpsURLConnection = context;
                    outputStream = str;
                    inputStream = str2;
                    IOUtil.closeSecure(outputStream);
                    IOUtil.closeSecure(inputStream);
                    IOUtil.closeSecure((InputStream) bufferedInputStream);
                    StreamUtil.m14331a(httpsURLConnection);
                    HMSLog.m14110i("PushHttpsClient", "close connection");
                    return null;
                } catch (RuntimeException unused8) {
                    str = null;
                    str2 = null;
                    bufferedInputStream = null;
                    HMSLog.m14109w("PushHttpsClient", "https execute encounter RuntimeException - http code:" + i);
                    httpsURLConnection = context;
                    outputStream = str;
                    inputStream = str2;
                    IOUtil.closeSecure(outputStream);
                    IOUtil.closeSecure(inputStream);
                    IOUtil.closeSecure((InputStream) bufferedInputStream);
                    StreamUtil.m14331a(httpsURLConnection);
                    HMSLog.m14110i("PushHttpsClient", "close connection");
                    return null;
                } catch (Exception unused9) {
                    str = null;
                    str2 = null;
                    bufferedInputStream = null;
                    HMSLog.m14109w("PushHttpsClient", "https execute encounter unknown exception - http code:" + i);
                    httpsURLConnection = context;
                    outputStream = str;
                    inputStream = str2;
                    IOUtil.closeSecure(outputStream);
                    IOUtil.closeSecure(inputStream);
                    IOUtil.closeSecure((InputStream) bufferedInputStream);
                    StreamUtil.m14331a(httpsURLConnection);
                    HMSLog.m14110i("PushHttpsClient", "close connection");
                    return null;
                } catch (Throwable th4) {
                    th = th4;
                    str = 0;
                    context = context;
                    str2 = str;
                    IOUtil.closeSecure((OutputStream) str);
                    IOUtil.closeSecure((InputStream) str2);
                    IOUtil.closeSecure((InputStream) r0);
                    StreamUtil.m14331a((HttpsURLConnection) context);
                    HMSLog.m14110i("PushHttpsClient", "close connection");
                    throw th;
                }
                try {
                    String m14333a = StreamUtil.m14333a((InputStream) bufferedInputStream);
                    IOUtil.closeSecure((OutputStream) str);
                    IOUtil.closeSecure((InputStream) str2);
                    IOUtil.closeSecure((InputStream) bufferedInputStream);
                    StreamUtil.m14331a((HttpsURLConnection) context);
                    HMSLog.m14110i("PushHttpsClient", "close connection");
                    return m14333a;
                } catch (IOException unused10) {
                    HMSLog.m14109w("PushHttpsClient", "https execute encounter IOException - http code:" + i);
                    httpsURLConnection = context;
                    outputStream = str;
                    inputStream = str2;
                    IOUtil.closeSecure(outputStream);
                    IOUtil.closeSecure(inputStream);
                    IOUtil.closeSecure((InputStream) bufferedInputStream);
                    StreamUtil.m14331a(httpsURLConnection);
                    HMSLog.m14110i("PushHttpsClient", "close connection");
                    return null;
                } catch (RuntimeException unused11) {
                    HMSLog.m14109w("PushHttpsClient", "https execute encounter RuntimeException - http code:" + i);
                    httpsURLConnection = context;
                    outputStream = str;
                    inputStream = str2;
                    IOUtil.closeSecure(outputStream);
                    IOUtil.closeSecure(inputStream);
                    IOUtil.closeSecure((InputStream) bufferedInputStream);
                    StreamUtil.m14331a(httpsURLConnection);
                    HMSLog.m14110i("PushHttpsClient", "close connection");
                    return null;
                } catch (Exception unused12) {
                    HMSLog.m14109w("PushHttpsClient", "https execute encounter unknown exception - http code:" + i);
                    httpsURLConnection = context;
                    outputStream = str;
                    inputStream = str2;
                    IOUtil.closeSecure(outputStream);
                    IOUtil.closeSecure(inputStream);
                    IOUtil.closeSecure((InputStream) bufferedInputStream);
                    StreamUtil.m14331a(httpsURLConnection);
                    HMSLog.m14110i("PushHttpsClient", "close connection");
                    return null;
                }
            } catch (IOException unused13) {
                context = null;
            } catch (RuntimeException unused14) {
                context = null;
            } catch (Exception unused15) {
                context = null;
            } catch (Throwable th5) {
                th = th5;
                context = 0;
                str = 0;
            }
        } catch (Throwable th6) {
            r0 = map;
            th = th6;
        }
    }

    /* renamed from: a */
    private static HttpsURLConnection m14375a(Context context, String str, Map<String, String> map, String str2) throws Exception {
        URLConnection openConnection = new URL(str).openConnection();
        if (openConnection == null) {
            HMSLog.m14112e("PushHttpsClient", "urlConnection is null");
            return null;
        } else if (openConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
            try {
                SecureSSLSocketFactory secureSSLSocketFactory = SecureSSLSocketFactory.getInstance(context);
                if (secureSSLSocketFactory != null) {
                    httpsURLConnection.setSSLSocketFactory(secureSSLSocketFactory);
                    httpsURLConnection.setHostnameVerifier(SecureSSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                    httpsURLConnection.setRequestMethod(str2);
                    httpsURLConnection.setConnectTimeout(15000);
                    httpsURLConnection.setReadTimeout(15000);
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
                    httpsURLConnection.setRequestProperty("Connection", "close");
                    if (map != null && map.size() >= 1) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            String key = entry.getKey();
                            if (!TextUtils.isEmpty(key)) {
                                httpsURLConnection.setRequestProperty(key, URLEncoder.encode(entry.getValue() == null ? "" : entry.getValue(), "UTF-8"));
                            }
                        }
                    }
                    return httpsURLConnection;
                }
                HMSLog.m14112e("PushHttpsClient", "No ssl socket factory set.");
                return null;
            } catch (IOException | IllegalAccessException | IllegalArgumentException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
                HMSLog.m14112e("PushHttpsClient", "Failed to new TLSSocketFactory instance." + e.getMessage());
                throw new IOException("Failed to create SSLSocketFactory.");
            }
        } else {
            HMSLog.m14112e("PushHttpsClient", "current request is http not allow connection");
            return null;
        }
    }
}
