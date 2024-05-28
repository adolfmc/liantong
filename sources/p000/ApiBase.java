package p000;

import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

@NBSInstrumented
/* renamed from: p */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ApiBase implements HttpLoggingInterceptor.Logger {

    /* renamed from: d */
    public static byte[] f27442d = "A301D46C29176D5F".getBytes();

    /* renamed from: e */
    public static BASE64Encoder f27443e = new BASE64Encoder();

    /* renamed from: a */
    public HttpLoggingInterceptor f27444a;

    /* renamed from: b */
    public HostnameVerifier f27445b = lambda.INSTANCE;

    /* renamed from: c */
    public X509TrustManager f27446c = new C13619a(this);

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: ApiBase.java */
    /* renamed from: p$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class C13619a implements X509TrustManager {
        public C13619a(ApiBase apiBase) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            C14231v.m74a("ApiBase", "checkClientTrusted");
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            C14231v.m74a("ApiBase", "checkServerTrusted");
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* compiled from: ApiBase.java */
    /* renamed from: p$b */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C13620b implements Interceptor {
        public C13620b(ApiBase apiBase) {
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) {
            Request request = chain.request();
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            return chain.proceed(request.newBuilder().method(request.method(), request.body()).headers(request.headers()).tag(request.tag()).cacheControl(request.cacheControl()).header("Accept", "application/json").header("sign", ApiBase.m191c(buffer.readString(Charset.defaultCharset()))).build());
        }

        public /* synthetic */ C13620b(ApiBase apiBase, C13619a c13619a) {
            this(apiBase);
        }
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m195a(String str, SSLSession sSLSession) {
        return true;
    }

    /* renamed from: c */
    public static String m191c(String str) {
        try {
            return m194a(m193a(f27442d, str.trim().getBytes("UTF-8")));
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0083  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public p000.IApiService m192b(java.lang.String r6) {
        /*
            r5 = this;
            okhttp3.logging.HttpLoggingInterceptor r0 = new okhttp3.logging.HttpLoggingInterceptor
            r0.<init>(r5)
            r5.f27444a = r0
            okhttp3.logging.HttpLoggingInterceptor$Level r1 = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            r0.setLevel(r1)
            r0 = 0
            java.lang.String r1 = "SSL"
            javax.net.ssl.SSLContext r1 = javax.net.ssl.SSLContext.getInstance(r1)     // Catch: java.security.KeyManagementException -> L28 java.security.NoSuchAlgorithmException -> L2b
            r2 = 1
            javax.net.ssl.TrustManager[] r2 = new javax.net.ssl.TrustManager[r2]     // Catch: java.security.KeyManagementException -> L24 java.security.NoSuchAlgorithmException -> L26
            r3 = 0
            javax.net.ssl.X509TrustManager r4 = r5.f27446c     // Catch: java.security.KeyManagementException -> L24 java.security.NoSuchAlgorithmException -> L26
            r2[r3] = r4     // Catch: java.security.KeyManagementException -> L24 java.security.NoSuchAlgorithmException -> L26
            java.security.SecureRandom r3 = new java.security.SecureRandom     // Catch: java.security.KeyManagementException -> L24 java.security.NoSuchAlgorithmException -> L26
            r3.<init>()     // Catch: java.security.KeyManagementException -> L24 java.security.NoSuchAlgorithmException -> L26
            r1.init(r0, r2, r3)     // Catch: java.security.KeyManagementException -> L24 java.security.NoSuchAlgorithmException -> L26
            goto L30
        L24:
            r2 = move-exception
            goto L2d
        L26:
            r2 = move-exception
            goto L2d
        L28:
            r2 = move-exception
            r1 = r0
            goto L2d
        L2b:
            r2 = move-exception
            r1 = r0
        L2d:
            r2.printStackTrace()
        L30:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "baseUrl:"
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "ApiBase"
            p000.C14231v.m72b(r3, r2)
            okhttp3.OkHttpClient$Builder r2 = new okhttp3.OkHttpClient$Builder
            r2.<init>()
            p$b r3 = new p$b
            r3.<init>(r5, r0)
            okhttp3.OkHttpClient$Builder r0 = r2.addInterceptor(r3)
            okhttp3.logging.HttpLoggingInterceptor r2 = r5.f27444a
            okhttp3.OkHttpClient$Builder r0 = r0.addInterceptor(r2)
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.SECONDS
            r3 = 30
            okhttp3.OkHttpClient$Builder r0 = r0.connectTimeout(r3, r2)
            okhttp3.OkHttpClient$Builder r0 = r0.writeTimeout(r3, r2)
            okhttp3.OkHttpClient$Builder r0 = r0.readTimeout(r3, r2)
            javax.net.ssl.SSLSocketFactory r1 = r1.getSocketFactory()
            javax.net.ssl.X509TrustManager r2 = r5.f27446c
            okhttp3.OkHttpClient$Builder r0 = r0.sslSocketFactory(r1, r2)
            javax.net.ssl.HostnameVerifier r1 = r5.f27445b
            okhttp3.OkHttpClient$Builder r0 = r0.hostnameVerifier(r1)
            boolean r1 = r0 instanceof okhttp3.OkHttpClient.Builder
            if (r1 != 0) goto L83
            okhttp3.OkHttpClient r0 = r0.build()
            goto L89
        L83:
            okhttp3.OkHttpClient$Builder r0 = (okhttp3.OkHttpClient.Builder) r0
            okhttp3.OkHttpClient r0 = com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation.builderInit(r0)
        L89:
            retrofit2.Retrofit$Builder r1 = new retrofit2.Retrofit$Builder
            r1.<init>()
            retrofit2.Retrofit$Builder r6 = r1.baseUrl(r6)
            retrofit2.Retrofit$Builder r6 = r6.client(r0)
            retrofit2.converter.gson.GsonConverterFactory r0 = retrofit2.converter.gson.GsonConverterFactory.create()
            retrofit2.Retrofit$Builder r6 = r6.addConverterFactory(r0)
            retrofit2.Retrofit r6 = r6.build()
            java.lang.Class<r> r0 = p000.IApiService.class
            java.lang.Object r6 = r6.create(r0)
            r r6 = (p000.IApiService) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ApiBase.m192b(java.lang.String):r");
    }

    @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
    public void log(String str) {
        if (str.length() > 4000) {
            Log.e("ApiBase", "sb.length = " + str.length());
            int length = str.length() / 4000;
            int i = 0;
            while (i <= length) {
                int i2 = i + 1;
                int i3 = i2 * 4000;
                if (i3 >= str.length()) {
                    Log.e("ApiBase", "chunk " + i + " of " + length + ":" + str.substring(i * 4000));
                } else {
                    Log.e("ApiBase", "chunk " + i + " of " + length + ":" + str.substring(i * 4000, i3));
                }
                i = i2;
            }
            return;
        }
        Log.e("ApiBase", str);
    }

    /* renamed from: a */
    public static String m194a(byte[] bArr) {
        try {
            return f27443e.m2112a(bArr).replaceAll("(\r|\n|\t)", "");
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m193a(byte[] bArr, byte[] bArr2) {
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(new SecretKeySpec(bArr, "HmacMD5"));
            return mac.doFinal(bArr2);
        } catch (Exception unused) {
            return null;
        }
    }
}
