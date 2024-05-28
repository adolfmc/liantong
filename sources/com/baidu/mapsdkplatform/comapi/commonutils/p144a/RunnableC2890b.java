package com.baidu.mapsdkplatform.comapi.commonutils.p144a;

import com.baidu.mapsdkplatform.comapi.commonutils.p144a.C2892c;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.File;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2890b implements Runnable {

    /* renamed from: a */
    private String f7143a;

    /* renamed from: b */
    private File f7144b;

    /* renamed from: c */
    private C2889a f7145c;

    /* renamed from: d */
    private C2892c.InterfaceC2894b f7146d;

    /* renamed from: e */
    private volatile boolean f7147e = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.a.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2891a implements HostnameVerifier {
        C2891a() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
        }
    }

    public RunnableC2890b(String str, File file, C2889a c2889a, C2892c.InterfaceC2894b interfaceC2894b) {
        this.f7143a = str;
        this.f7144b = file;
        this.f7145c = c2889a;
        this.f7146d = interfaceC2894b;
    }

    /* renamed from: a */
    public boolean m18468a() {
        return this.f7147e;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00d6 A[Catch: IOException -> 0x00d2, TRY_LEAVE, TryCatch #3 {IOException -> 0x00d2, blocks: (B:37:0x00ce, B:41:0x00d6), top: B:49:0x00ce }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ce A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.lang.String r3 = r6.f7143a     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r2.<init>(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            boolean r3 = com.baidu.mapapi.http.HttpClient.isHttpsEnable     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            if (r3 == 0) goto L23
            java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.net.URLConnection r2 = com.networkbench.agent.impl.instrumentation.NBSInstrumentation.openConnection(r2)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r3 = r2
            javax.net.ssl.HttpsURLConnection r3 = (javax.net.ssl.HttpsURLConnection) r3     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            com.baidu.mapsdkplatform.comapi.commonutils.a.b$a r4 = new com.baidu.mapsdkplatform.comapi.commonutils.a.b$a     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r4.<init>()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r3.setHostnameVerifier(r4)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            goto L2d
        L23:
            java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.net.URLConnection r2 = com.networkbench.agent.impl.instrumentation.NBSInstrumentation.openConnection(r2)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
        L2d:
            java.lang.String r3 = "GET"
            r2.setRequestMethod(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r3 = 10000(0x2710, float:1.4013E-41)
            r2.setConnectTimeout(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r2.setReadTimeout(r3)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.lang.String r3 = "Range"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r4.<init>()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.lang.String r5 = "bytes="
            r4.append(r5)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            com.baidu.mapsdkplatform.comapi.commonutils.a.a r5 = r6.f7145c     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            int r5 = r5.m18471a()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r4.append(r5)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.lang.String r5 = "-"
            r4.append(r5)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            com.baidu.mapsdkplatform.comapi.commonutils.a.a r5 = r6.f7145c     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            int r5 = r5.m18469b()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r4.append(r5)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            r2.setRequestProperty(r3, r4)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.lang.String r3 = "Connection"
            java.lang.String r4 = "Keep-Alive"
            r2.setRequestProperty(r3, r4)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.io.File r4 = r6.f7144b     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            java.lang.String r5 = "rw"
            r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lac
            com.baidu.mapsdkplatform.comapi.commonutils.a.a r4 = r6.f7145c     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
            int r4 = r4.m18471a()     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
            long r4 = (long) r4     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
            r3.seek(r4)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
            int r4 = r2.getResponseCode()     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 == r5) goto L8a
            r5 = 206(0xce, float:2.89E-43)
            if (r4 != r5) goto L9d
        L8a:
            r4 = 1048576(0x100000, float:1.469368E-39)
            byte[] r4 = new byte[r4]     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
            java.io.InputStream r1 = r2.getInputStream()     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
        L92:
            int r2 = r1.read(r4)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
            r5 = -1
            if (r2 == r5) goto L9d
            r3.write(r4, r0, r2)     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
            goto L92
        L9d:
            r2 = 1
            r6.f7147e = r2     // Catch: java.lang.Exception -> Lad java.lang.Throwable -> Lcb
            if (r1 == 0) goto La5
            r1.close()     // Catch: java.io.IOException -> Lba
        La5:
            r3.close()     // Catch: java.io.IOException -> Lba
            goto Lc5
        La9:
            r0 = move-exception
            r3 = r1
            goto Lcc
        Lac:
            r3 = r1
        Lad:
            r6.f7147e = r0     // Catch: java.lang.Throwable -> Lcb
            com.baidu.mapsdkplatform.comapi.commonutils.a.c$b r0 = r6.f7146d     // Catch: java.lang.Throwable -> Lcb
            r0.mo18460b(r6)     // Catch: java.lang.Throwable -> Lcb
            if (r1 == 0) goto Lbc
            r1.close()     // Catch: java.io.IOException -> Lba
            goto Lbc
        Lba:
            r0 = move-exception
            goto Lc2
        Lbc:
            if (r3 == 0) goto Lc5
            r3.close()     // Catch: java.io.IOException -> Lba
            goto Lc5
        Lc2:
            r0.printStackTrace()
        Lc5:
            com.baidu.mapsdkplatform.comapi.commonutils.a.c$b r0 = r6.f7146d
            r0.mo18461a(r6)
            return
        Lcb:
            r0 = move-exception
        Lcc:
            if (r1 == 0) goto Ld4
            r1.close()     // Catch: java.io.IOException -> Ld2
            goto Ld4
        Ld2:
            r1 = move-exception
            goto Lda
        Ld4:
            if (r3 == 0) goto Ldd
            r3.close()     // Catch: java.io.IOException -> Ld2
            goto Ldd
        Lda:
            r1.printStackTrace()
        Ldd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.commonutils.p144a.RunnableC2890b.run():void");
    }
}
