package com.sdk.p285a;

import android.annotation.SuppressLint;
import android.content.Context;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.framework.utils.log.MobileLogManager;
import com.sdk.p290f.C6998d;
import com.sdk.p293i.C7007a;
import com.sdk.p296l.C7011a;
import com.sdk.p298n.C7014a;
import com.sdk.p302r.C7037a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;

@NBSInstrumented
/* renamed from: com.sdk.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6960d<T> {

    /* renamed from: d */
    public static final String f18019d = "d";

    /* renamed from: e */
    public static boolean f18020e = C6998d.f18135a;

    /* renamed from: f */
    public static final String f18021f = UUID.randomUUID().toString();

    /* renamed from: g */
    public static boolean f18022g = true;

    /* renamed from: a */
    public Context f18023a;

    /* renamed from: b */
    public C6962e<T> f18024b;

    /* renamed from: c */
    public long f18025c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sdk.a.d$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6961a {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        HEAD("HEAD"),
        MOVE("MOVE"),
        COPY("COPY"),
        DELETE("DELETE"),
        OPTIONS("OPTIONS"),
        TRACE("TRACE"),
        CONNECT("CONNECT");
        

        /* renamed from: a */
        public final String f18037a;

        EnumC6961a(String str) {
            this.f18037a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.f18037a;
        }
    }

    public C6960d(Context context, C6962e<T> c6962e) {
        this.f18023a = context;
        this.f18024b = c6962e;
    }

    /* renamed from: a */
    public C6962e<T> m8209a() {
        return this.f18024b;
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: a */
    public HttpURLConnection m8206a(String str, boolean z) {
        HttpURLConnection httpURLConnection;
        LogUtils.d_yl(f18019d, "HttpRequest client 开始", 0);
        try {
            HttpURLConnection httpURLConnection2 = null;
            if (C7037a.m8129b(str).booleanValue()) {
                URL url = new URL(str);
                int mo8144a = C7014a.m8145b(this.f18023a).mo8144a();
                if (!m8207a(str) || mo8144a == 1) {
                    httpURLConnection = null;
                } else {
                    int m8163a = C7007a.m8163a();
                    if (m8163a < 23) {
                        mo8144a = m8203c();
                        httpURLConnection = (m8163a <= 21 || mo8144a == 1) ? null : new C6954a(this.f18023a, url).m8221a();
                    } else {
                        long currentTimeMillis = System.currentTimeMillis();
                        httpURLConnection = new C6954a(this.f18023a, url).m8221a();
                        this.f18025c = System.currentTimeMillis() - currentTimeMillis;
                    }
                    if (httpURLConnection == null) {
                        return null;
                    }
                }
                if (httpURLConnection == null) {
                    httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(url.openConnection());
                } else if (mo8144a == 0) {
                    mo8144a = 2;
                }
                String protocol = url.getProtocol();
                if (C7037a.m8129b(protocol).booleanValue() && "https".equals(protocol.toLowerCase(Locale.getDefault()))) {
                    httpURLConnection = (HttpsURLConnection) httpURLConnection;
                }
                httpURLConnection2 = httpURLConnection;
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setConnectTimeout(10000);
                httpURLConnection2.setReadTimeout(10000);
                httpURLConnection2.setInstanceFollowRedirects(false);
                httpURLConnection2.setRequestProperty("user-agent", C7011a.m8151a(this.f18023a));
                httpURLConnection2.setRequestProperty("netType", mo8144a + "");
                httpURLConnection2.setRequestProperty("os", "android");
                httpURLConnection2.setRequestProperty("Accept", "*/*");
                httpURLConnection2.setUseCaches(true);
                if (f18022g) {
                    z = true;
                }
                httpURLConnection2.setInstanceFollowRedirects(z);
                HttpURLConnection.setFollowRedirects(z);
                HashMap<String, Object> hashMap = this.f18024b.f18044e;
                if (hashMap != null && hashMap.size() > 0) {
                    for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                        httpURLConnection2.setRequestProperty(entry.getKey(), entry.getValue() + "");
                    }
                }
            }
            return httpURLConnection2;
        } catch (Exception e) {
            MobileLogManager.status302002(e.toString());
            LogUtils.m8186e(f18019d + "HttpRequst 248", e.toString(), Boolean.valueOf(f18020e));
            throw e;
        }
    }

    /* renamed from: a */
    public final boolean m8207a(String str) {
        return (str.contains("/api/netm/v1.0/qhbt") || str.contains("/api/netm/v1.0/qhbv") || str.contains("/st/api/v1.0/ses")) ? false : true;
    }

    /* renamed from: b */
    public String m8204b() {
        StringBuilder sb;
        try {
            C6962e<T> c6962e = this.f18024b;
            if (c6962e != null) {
                String str = c6962e.f18040a;
                String str2 = c6962e.f18041b;
                if (str.equals("GET")) {
                    C6962e<T> c6962e2 = this.f18024b;
                    String m8201a = c6962e2.m8201a(c6962e2.f18042c);
                    if (!C7037a.m8129b(m8201a).booleanValue()) {
                        return str2;
                    }
                    sb = new StringBuilder();
                    sb.append(str2);
                    sb.append("?");
                    sb.append(m8201a);
                } else {
                    this.f18024b.getClass();
                    if (!C7037a.m8129b(null).booleanValue()) {
                        return str2;
                    }
                    sb = new StringBuilder();
                    sb.append(str2);
                    sb.append("?unikey=");
                    sb.append((String) null);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            LogUtils.m8186e(f18019d, e.getMessage(), Boolean.valueOf(f18020e));
        }
        return null;
    }

    /* renamed from: c */
    public int m8203c() {
        LogUtils.d_yl(f18019d, "openCellularKitKat 开始:", 0);
        String str = this.f18024b.f18041b;
        if (m8207a(str)) {
            ArrayList arrayList = new ArrayList();
            if (C7037a.m8129b(str).booleanValue()) {
                arrayList.add(str);
            }
            return C7014a.m8145b(this.f18023a).mo8144a();
        }
        return C7014a.m8145b(this.f18023a).mo8144a();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.net.HttpURLConnection m8205a(java.net.HttpURLConnection r7) {
        /*
            r6 = this;
            java.lang.String r0 = com.sdk.p285a.C6960d.f18019d
            java.lang.String r1 = "HttpRequest HttpURLConnection execute() 开始"
            r2 = 0
            com.sdk.base.framework.utils.log.LogUtils.d_yl(r0, r1, r2)
            com.sdk.a.e<T> r1 = r6.f18024b
            if (r1 == 0) goto L35
            java.util.ArrayList<java.io.File> r1 = r1.f18043d
            if (r1 == 0) goto L19
            int r1 = r1.size()
            if (r1 != 0) goto L17
            goto L19
        L17:
            r1 = 1
            goto L1a
        L19:
            r1 = r2
        L1a:
            if (r1 == 0) goto L35
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "multipart/form-data; boundary="
            r1.append(r3)
            java.lang.String r3 = com.sdk.p285a.C6960d.f18021f
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "Content-Type"
            r7.setRequestProperty(r3, r1)
            goto L3c
        L35:
            java.lang.String r1 = "Content-Type"
            java.lang.String r3 = "application/x-www-form-urlencoded"
            r7.setRequestProperty(r1, r3)
        L3c:
            java.lang.String r1 = "Charset"
            java.lang.String r3 = "UTF-8"
            r7.setRequestProperty(r1, r3)
            java.lang.String r1 = "connection"
            java.lang.String r3 = "keep-alive"
            r7.setRequestProperty(r1, r3)
            com.sdk.a.e<T> r1 = r6.f18024b
            if (r1 == 0) goto Lba
            java.lang.String r1 = r1.f18040a
            java.lang.String r3 = "POST"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto Lb5
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "execute 建立了一个与服务器的tcp请求 step1:"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.sdk.base.framework.utils.log.LogUtils.d_yl(r0, r3, r2)
            java.lang.String r0 = "POST"
            r7.setRequestMethod(r0)
            com.sdk.a.e<T> r0 = r6.f18024b     // Catch: java.lang.Exception -> L83
            java.io.ByteArrayOutputStream r0 = r6.m8208a(r0)     // Catch: java.lang.Exception -> L83
            java.io.OutputStream r3 = r7.getOutputStream()     // Catch: java.lang.Exception -> L83
            byte[] r0 = r0.toByteArray()     // Catch: java.lang.Exception -> L83
            r3.write(r0)     // Catch: java.lang.Exception -> L83
            goto L9e
        L83:
            r0 = move-exception
            java.lang.String r3 = com.sdk.p285a.C6960d.f18019d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "execute 建立了一个与服务器的tcp请求 OutputStream Exception:"
            r4.append(r5)
            java.lang.String r0 = r0.toString()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.sdk.base.framework.utils.log.LogUtils.d_yl(r3, r0, r2)
        L9e:
            java.lang.String r0 = com.sdk.p285a.C6960d.f18019d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "execute 建立了一个与服务器的tcp请求 step2:"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.sdk.base.framework.utils.log.LogUtils.d_yl(r0, r1, r2)
            goto Lba
        Lb5:
            java.lang.String r0 = "GET"
            r7.setRequestMethod(r0)
        Lba:
            java.lang.String r0 = com.sdk.p285a.C6960d.f18019d
            java.lang.String r1 = "HttpRequest HttpURLConnection execute() 结束:"
            com.sdk.base.framework.utils.log.LogUtils.d_yl(r0, r1, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sdk.p285a.C6960d.m8205a(java.net.HttpURLConnection):java.net.HttpURLConnection");
    }

    /* renamed from: a */
    public ByteArrayOutputStream m8208a(C6962e<T> c6962e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (c6962e != null) {
            try {
                String m8201a = c6962e.m8201a(this.f18024b.f18042c);
                if (c6962e.f18041b.contains("/dro/log/v1.0/log")) {
                    m8201a = c6962e.f18042c.toString();
                }
                ArrayList<File> arrayList = c6962e.f18043d;
                if (arrayList == null || arrayList.size() <= 0) {
                    byteArrayOutputStream.write(m8201a.getBytes("utf-8"));
                } else {
                    byteArrayOutputStream.write(("--" + f18021f + "\r\n").getBytes("utf-8"));
                    byteArrayOutputStream.write("Content-Disposition: form-data; name=\"params\"".getBytes("utf-8"));
                    byteArrayOutputStream.write("\r\n\r\n".getBytes("utf-8"));
                    byteArrayOutputStream.write(m8201a.getBytes("utf-8"));
                    byteArrayOutputStream.write("\r\n".getBytes("utf-8"));
                    for (int i = 0; i < arrayList.size(); i++) {
                        File file = arrayList.get(i);
                        if (file != null) {
                            FileInputStream fileInputStream = new FileInputStream(file);
                            String name = file.getName();
                            byteArrayOutputStream.write(("--" + f18021f + "\r\n").getBytes("utf-8"));
                            byteArrayOutputStream.write(("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + name + "\"\r\n").getBytes("utf-8"));
                            byteArrayOutputStream.write("Content-Type: application/octet-stream\r\n".getBytes("utf-8"));
                            byteArrayOutputStream.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes("utf-8"));
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            byteArrayOutputStream.write(("\r\n--" + f18021f + "--\r\n").getBytes("utf-8"));
                            byteArrayOutputStream.flush();
                            fileInputStream.close();
                        }
                    }
                }
            } catch (Exception e) {
                MobileLogManager.status302002(e.toString());
                LogUtils.m8186e(f18019d + "HttpRequst558", e.toString(), Boolean.valueOf(f18020e));
            }
        }
        return byteArrayOutputStream;
    }
}
