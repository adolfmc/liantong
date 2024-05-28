package com.xiaomi.push;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.xiaomi.push.au */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11169au {

    /* renamed from: a */
    private static final AtomicReference<C11172a<C11175av>> f21548a = new AtomicReference<>(m4856a());

    /* renamed from: a */
    public static final Pattern f21549a = Pattern.compile("([^\\s;]+)(.*)");

    /* renamed from: b */
    public static final Pattern f21550b = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);

    /* renamed from: c */
    public static final Pattern f21551c = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);

    /* renamed from: a */
    public static InputStream m4843a(Context context, URL url, boolean z, String str, String str2) {
        return m4841a(context, url, z, str, str2, null, null);
    }

    /* renamed from: a */
    public static void m4854a() {
        m4836b();
    }

    /* renamed from: b */
    public static void m4836b() {
        f21548a.set(m4856a());
    }

    /* renamed from: a */
    public static Object m4851a(Context context) {
        ConnectivityManager connectivityManager;
        NetworkRequest build;
        ConnectivityManager.NetworkCallback networkCallback;
        if (context == null) {
            context = C11479r.m2934a();
        }
        ConnectivityManager.NetworkCallback networkCallback2 = null;
        if (context == null || C11469j.m2972a(context) || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            build = new NetworkRequest.Builder().build();
            networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.xiaomi.push.au.1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    C11169au.m4836b();
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    super.onLost(network);
                    C11169au.m4836b();
                }
            };
        } catch (Throwable th) {
            th = th;
        }
        try {
            connectivityManager.registerNetworkCallback(build, networkCallback);
            return networkCallback;
        } catch (Throwable th2) {
            th = th2;
            networkCallback2 = networkCallback;
            AbstractC11049b.m5282a("exception occurred in adding network callback :" + th);
            return networkCallback2;
        }
    }

    /* renamed from: a */
    public static void m4848a(Context context, Object obj) {
        if (Build.VERSION.SDK_INT < 21) {
            AbstractC11049b.m5274b("less than LOLLIPOP(21) not support channel ");
        } else if (context == null || obj == null) {
        } else {
            try {
                if (obj instanceof ConnectivityManager.NetworkCallback) {
                    ((ConnectivityManager) context.getSystemService("connectivity")).unregisterNetworkCallback((ConnectivityManager.NetworkCallback) obj);
                }
            } catch (Throwable th) {
                AbstractC11049b.m5282a("exception occurred in removing network callback :" + th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.au$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11172a<T> extends FutureTask<T> {

        /* renamed from: a */
        private long f21552a;

        public C11172a(Callable<T> callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask, java.util.concurrent.RunnableFuture, java.lang.Runnable
        public void run() {
            this.f21552a = SystemClock.elapsedRealtime();
            super.run();
        }

        /* renamed from: a */
        public boolean m4827a() {
            return C11469j.m2972a(C11479r.m2934a()) || (isDone() && Math.abs(SystemClock.elapsedRealtime() - this.f21552a) > 1800000);
        }
    }

    /* renamed from: a */
    private static C11172a<C11175av> m4856a() {
        return new C11172a<>(new Callable<C11175av>() { // from class: com.xiaomi.push.au.2
            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public C11175av call() {
                NetworkInfo activeNetworkInfo;
                Context m2934a = C11479r.m2934a();
                if (m2934a != null) {
                    try {
                        ConnectivityManager connectivityManager = (ConnectivityManager) m2934a.getSystemService("connectivity");
                        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                            return null;
                        }
                        return new C11175av(activeNetworkInfo);
                    } catch (Exception unused) {
                        return null;
                    }
                }
                return null;
            }
        });
    }

    /* renamed from: a */
    public static C11175av m4855a() {
        C11172a<C11175av> c11172a = f21548a.get();
        if (c11172a != null) {
            try {
                if (c11172a.m4827a()) {
                    AtomicReference<C11172a<C11175av>> atomicReference = f21548a;
                    C11172a<C11175av> m4856a = m4856a();
                    atomicReference.set(m4856a);
                    c11172a = m4856a;
                }
                if (!c11172a.isDone()) {
                    c11172a.run();
                }
                return c11172a.get();
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* renamed from: a */
    public static InputStream m4841a(Context context, URL url, boolean z, String str, String str2, Map<String, String> map, C11174c c11174c) {
        if (context != null) {
            if (url == null) {
                throw new IllegalArgumentException("url");
            }
            URL url2 = !z ? new URL(m4840a(url.toString())) : url;
            try {
                HttpURLConnection.setFollowRedirects(true);
                HttpURLConnection m4844a = m4844a(context, url2);
                m4844a.setConnectTimeout(10000);
                m4844a.setReadTimeout(15000);
                if (!TextUtils.isEmpty(str)) {
                    m4844a.setRequestProperty("User-Agent", str);
                }
                if (str2 != null) {
                    m4844a.setRequestProperty("Cookie", str2);
                }
                if (map != null) {
                    for (String str3 : map.keySet()) {
                        m4844a.setRequestProperty(str3, map.get(str3));
                    }
                }
                if (c11174c != null && (url.getProtocol().equals("http") || url.getProtocol().equals("https"))) {
                    c11174c.f21554a = m4844a.getResponseCode();
                    if (c11174c.f21555a == null) {
                        c11174c.f21555a = new HashMap();
                    }
                    int i = 0;
                    while (true) {
                        String headerFieldKey = m4844a.getHeaderFieldKey(i);
                        String headerField = m4844a.getHeaderField(i);
                        if (headerFieldKey == null && headerField == null) {
                            break;
                        }
                        if (!TextUtils.isEmpty(headerFieldKey) && !TextUtils.isEmpty(headerField)) {
                            c11174c.f21555a.put(headerFieldKey, headerField);
                        }
                        i++;
                    }
                }
                return new C11173b(m4844a.getInputStream());
            } catch (IOException e) {
                throw new IOException("IOException:" + e.getClass().getSimpleName());
            } catch (Throwable th) {
                throw new IOException(th.getMessage());
            }
        }
        throw new IllegalArgumentException("context");
    }

    /* renamed from: a */
    public static String m4845a(Context context, URL url) {
        return m4842a(context, url, false, null, "UTF-8", null);
    }

    /* renamed from: a */
    public static String m4842a(Context context, URL url, boolean z, String str, String str2, String str3) {
        InputStream inputStream;
        try {
            inputStream = m4843a(context, url, z, str, str3);
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            StringBuilder sb = new StringBuilder(1024);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str2));
            char[] cArr = new char[4096];
            while (true) {
                int read = bufferedReader.read(cArr);
                if (-1 != read) {
                    sb.append(cArr, 0, read);
                } else {
                    C11647w.m2274a((Closeable) inputStream);
                    return sb.toString();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            C11647w.m2274a((Closeable) inputStream);
            throw th;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.au$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11174c {

        /* renamed from: a */
        public int f21554a;

        /* renamed from: a */
        public Map<String, String> f21555a;

        public String toString() {
            return String.format("resCode = %1$d, headers = %2$s", Integer.valueOf(this.f21554a), this.f21555a.toString());
        }
    }

    /* renamed from: a */
    public static String m4840a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        new String();
        return String.format("%s&key=%s", str, C11183ba.m4761a(String.format("%sbe988a6134bc8254465424e5a70ef037", str)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r7v16, types: [java.io.Closeable, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* renamed from: a */
    public static String m4838a(String str, Map<String, String> map, File file, String str2) {
        FileInputStream fileInputStream = null;
        if (!file.exists()) {
            return null;
        }
        String name = file.getName();
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(str).openConnection());
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=*****");
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                httpURLConnection.setFixedLengthStreamingMode(name.length() + 77 + ((int) file.length()) + str2.length());
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes("--*****\r\n");
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + str2 + "\";filename=\"" + file.getName() + "\"\r\n");
                dataOutputStream.writeBytes("\r\n");
                FileInputStream fileInputStream2 = new FileInputStream((File) file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        dataOutputStream.write(bArr, 0, read);
                        dataOutputStream.flush();
                    }
                    dataOutputStream.writeBytes("\r\n");
                    dataOutputStream.writeBytes("--");
                    dataOutputStream.writeBytes("*****");
                    dataOutputStream.writeBytes("--");
                    dataOutputStream.writeBytes("\r\n");
                    dataOutputStream.flush();
                    StringBuffer stringBuffer = new StringBuffer();
                    file = new BufferedReader(new InputStreamReader(new C11173b(httpURLConnection.getInputStream())));
                    while (true) {
                        try {
                            String readLine = file.readLine();
                            if (readLine != null) {
                                stringBuffer.append(readLine);
                            } else {
                                String stringBuffer2 = stringBuffer.toString();
                                C11647w.m2274a((Closeable) fileInputStream2);
                                C11647w.m2274a((Closeable) file);
                                return stringBuffer2;
                            }
                        } catch (IOException e) {
                            e = e;
                            throw new IOException("IOException:" + e.getClass().getSimpleName());
                        } catch (Throwable th) {
                            th = th;
                            throw new IOException(th.getMessage());
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e3) {
                e = e3;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* renamed from: a */
    public static int m4853a(Context context) {
        C11175av m4855a = m4855a();
        if (m4855a == null) {
            return -1;
        }
        return m4855a.m4826a();
    }

    /* renamed from: a */
    public static HttpURLConnection m4844a(Context context, URL url) {
        return (HttpURLConnection) NBSInstrumentation.openConnection(url.openConnection());
    }

    /* renamed from: com.xiaomi.push.au$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11173b extends FilterInputStream {

        /* renamed from: a */
        private boolean f21553a;

        public C11173b(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            int read;
            if (this.f21553a || (read = super.read(bArr, i, i2)) == -1) {
                this.f21553a = true;
                return -1;
            }
            return read;
        }
    }

    /* renamed from: a */
    public static boolean m4849a(Context context) {
        return m4853a(context) >= 0;
    }

    /* renamed from: b */
    public static boolean m4835b(Context context) {
        boolean z;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                    z = networkCapabilities != null ? networkCapabilities.hasCapability(16) : false;
                } catch (Exception unused) {
                }
            } else {
                z = m4849a(context);
            }
            return z && m4834c(context);
        }
        z = false;
        if (z) {
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m4834c(Context context) {
        C11175av m4855a = m4855a();
        return m4855a != null && m4855a.m4822a();
    }

    /* renamed from: d */
    public static boolean m4833d(Context context) {
        C11175av m4855a = m4855a();
        return m4855a != null && 1 == m4855a.m4826a();
    }

    /* renamed from: e */
    public static boolean m4832e(Context context) {
        C11175av m4852a = m4852a(context);
        return m4852a != null && m4852a.m4826a() == 0 && 20 == m4852a.m4820b();
    }

    /* renamed from: f */
    public static boolean m4831f(Context context) {
        C11175av m4852a = m4852a(context);
        return m4852a != null && m4852a.m4826a() == 0 && 13 == m4852a.m4820b();
    }

    /* renamed from: g */
    public static boolean m4830g(Context context) {
        C11175av m4852a = m4852a(context);
        if (m4852a != null && m4852a.m4826a() == 0) {
            String m4819b = m4852a.m4819b();
            if ("TD-SCDMA".equalsIgnoreCase(m4819b) || "CDMA2000".equalsIgnoreCase(m4819b) || "WCDMA".equalsIgnoreCase(m4819b)) {
                return true;
            }
            switch (m4852a.m4820b()) {
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return true;
                case 4:
                case 7:
                case 11:
                case 13:
                default:
                    return false;
            }
        }
        return false;
    }

    /* renamed from: h */
    public static boolean m4829h(Context context) {
        C11175av m4852a = m4852a(context);
        if (m4852a != null && m4852a.m4826a() == 0) {
            int m4820b = m4852a.m4820b();
            if (m4820b == 4 || m4820b == 7 || m4820b == 11) {
                return true;
            }
            switch (m4820b) {
                case 1:
                case 2:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static C11175av m4852a(Context context) {
        return m4855a();
    }

    /* renamed from: a */
    public static String m4850a(Context context) {
        if (m4833d(context)) {
            return "wifi";
        }
        C11175av m4855a = m4855a();
        if (m4855a == null) {
            return "";
        }
        return (m4855a.m4823a() + "-" + m4855a.m4819b()).toLowerCase();
    }

    /* renamed from: a */
    public static C11167as m4846a(Context context, String str, Map<String, String> map) {
        return m4847a(context, str, "POST", (Map<String, String>) null, m4837a(map));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.Closeable] */
    /* renamed from: a */
    public static C11167as m4847a(Context context, String str, String str2, Map<String, String> map, String str3) {
        boolean z;
        BufferedReader bufferedReader;
        OutputStream outputStream;
        C11167as c11167as = new C11167as();
        OutputStream outputStream2 = null;
        try {
            try {
                try {
                    HttpURLConnection m4844a = m4844a(context, m4839a(str));
                    m4844a.setConnectTimeout(10000);
                    m4844a.setReadTimeout(15000);
                    String str4 = str2;
                    if (str2 == 0) {
                        str4 = "GET";
                    }
                    m4844a.setRequestMethod(str4);
                    int i = 0;
                    if (map != null) {
                        z = "gzip".equalsIgnoreCase(map.get("Content-Encoding"));
                        for (String str5 : map.keySet()) {
                            m4844a.setRequestProperty(str5, map.get(str5));
                        }
                    } else {
                        z = false;
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        m4844a.setDoOutput(true);
                        byte[] bytes = str3.getBytes();
                        if (z) {
                            outputStream = new GZIPOutputStream(m4844a.getOutputStream());
                        } else {
                            outputStream = m4844a.getOutputStream();
                        }
                        try {
                            outputStream.write(bytes, 0, bytes.length);
                            outputStream.flush();
                            outputStream.close();
                        } catch (IOException e) {
                            e = e;
                            throw new IOException("err while request " + str + ":" + e.getClass().getSimpleName());
                        } catch (Throwable th) {
                            th = th;
                            throw new IOException(th.getMessage());
                        }
                    }
                    c11167as.f21545a = m4844a.getResponseCode();
                    AbstractC11049b.m5282a("Http POST Response Code: " + c11167as.f21545a);
                    while (true) {
                        String headerFieldKey = m4844a.getHeaderFieldKey(i);
                        String headerField = m4844a.getHeaderField(i);
                        if (headerFieldKey != null || headerField != null) {
                            c11167as.f21547a.put(headerFieldKey, headerField);
                            i = i + 1 + 1;
                        } else {
                            try {
                                break;
                            } catch (IOException unused) {
                                bufferedReader = new BufferedReader(new InputStreamReader(new C11173b(m4844a.getErrorStream())));
                            }
                        }
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(new C11173b(m4844a.getInputStream())));
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    String property = System.getProperty("line.separator");
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        stringBuffer.append(readLine);
                        stringBuffer.append(property);
                    }
                    c11167as.f21546a = stringBuffer.toString();
                    bufferedReader.close();
                    C11647w.m2274a((Closeable) null);
                    C11647w.m2274a((Closeable) null);
                    return c11167as;
                } catch (IOException e2) {
                    e = e2;
                    throw new IOException("err while request " + str + ":" + e.getClass().getSimpleName());
                } catch (Throwable th3) {
                    th = th3;
                    throw new IOException(th.getMessage());
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* renamed from: a */
    public static String m4837a(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                try {
                    stringBuffer.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    stringBuffer.append("=");
                    stringBuffer.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    stringBuffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    AbstractC11049b.m5282a("Failed to convert from params map to string: " + e);
                    AbstractC11049b.m5282a("map: " + map.toString());
                    return null;
                }
            }
        }
        if (stringBuffer.length() > 0) {
            stringBuffer = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private static URL m4839a(String str) {
        return new URL(str);
    }
}
