package com.baidu.mapsdkplatform.comapi.p142b.p143a;

import android.content.Context;
import android.os.Build;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapsdkplatform.comapi.util.StorageSettings;
import com.baidu.mapsdkplatform.comapi.util.SyncSysInfo;
import com.baidu.mapsdkplatform.comapi.util.SysOSAPI;
import com.baidu.mapsdkplatform.comjni.util.JNIHandler;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.zip.GZIPOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.mapsdkplatform.comapi.b.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NativeCrashUtil {

    /* renamed from: a */
    private static String f7127a = "";

    /* renamed from: b */
    private static String f7128b = "";

    /* renamed from: c */
    private static String f7129c = "";

    /* renamed from: d */
    private Context f7130d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: NativeCrashUtil.java */
    /* renamed from: com.baidu.mapsdkplatform.comapi.b.a.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C2886a {

        /* renamed from: a */
        private static final NativeCrashUtil f7131a = new NativeCrashUtil();
    }

    /* renamed from: a */
    public static NativeCrashUtil m18496a() {
        return C2886a.f7131a;
    }

    /* renamed from: a */
    public void m18495a(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (Build.SUPPORTED_ABIS.length > 0) {
            f7129c = Build.SUPPORTED_ABIS[0];
        }
        this.f7130d = context;
        String m18121p = SysOSAPI.m18121p();
        if (m18121p.isEmpty()) {
            return;
        }
        if (m18121p.contains("_")) {
            m18121p = m18121p.replaceAll("_", "");
        }
        f7128b = m18121p + "_" + SysOSAPI.m18126k() + "_";
        m18484d();
        m18483e();
        m18482f();
    }

    /* renamed from: a */
    public void m18490a(String str, String str2) {
        JNIHandler.addLog(str, str2);
    }

    /* renamed from: d */
    private void m18484d() {
        if (StorageSettings.m18149a().m18145b() == null) {
            return;
        }
        String m18152b = StorageSettings.m18149a().m18145b().m18152b();
        if (m18152b.isEmpty()) {
            return;
        }
        String str = m18152b + File.separator + "crash";
        File file = new File(str);
        if (file.exists()) {
            f7127a = str;
        } else if (file.mkdir()) {
            f7127a = str;
        } else {
            f7127a = m18152b;
        }
    }

    /* renamed from: e */
    private void m18483e() {
        String str;
        String str2 = f7127a;
        if (str2 == null || str2.isEmpty() || (str = f7128b) == null || str.isEmpty()) {
            return;
        }
        String str3 = f7127a + File.separator + f7128b;
        MapSDKUncaughtExceptionHandler.m18500a().m18499a(str3);
        JNIHandler.registerNativeHandler(str3);
    }

    /* renamed from: f */
    private void m18482f() {
        if (NetworkUtil.isNetworkAvailable(this.f7130d)) {
            new Thread(new RunnableC2887d(this)).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18488a(File[] fileArr) {
        int length = fileArr.length;
        for (int i = 0; i < length - 10; i++) {
            int i2 = i + 10;
            if (fileArr[i2] != null && fileArr[i2].exists()) {
                fileArr[i2].delete();
            }
        }
    }

    /* renamed from: g */
    private HttpURLConnection m18481g() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL("https://api.map.baidu.com/lbs_sdkcc/report").openConnection());
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=bd_map_sdk_cc");
            httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
            httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
            httpURLConnection.setConnectTimeout(10000);
            return httpURLConnection;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0111 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0120 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x013b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0129 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean m18492a(java.io.File r10) {
        /*
            Method dump skipped, instructions count: 323
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.p142b.p143a.NativeCrashUtil.m18492a(java.io.File):boolean");
    }

    /* renamed from: b */
    private StringBuilder m18486b(File file) {
        String[] split = file.getName().substring(0, file.getName().length() - 4).split("_");
        StringBuilder sb = new StringBuilder();
        sb.append("--bd_map_sdk_cc");
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"phoneinfo\"\r\n");
        sb.append("\r\n");
        sb.append(URLDecoder.decode(SyncSysInfo.getPhoneInfo() + "&abi=" + f7129c));
        sb.append("\r\n");
        sb.append("--bd_map_sdk_cc");
        sb.append("\r\n");
        if (split[0] != null && !split[0].isEmpty()) {
            sb.append("Content-Disposition: form-data; name=\"packname\"\r\n");
            sb.append("\r\n");
            sb.append(split[0]);
            sb.append("\r\n");
            sb.append("--bd_map_sdk_cc");
            sb.append("\r\n");
        }
        if (split[1] != null && !split[1].isEmpty()) {
            sb.append("Content-Disposition: form-data; name=\"version\"\r\n");
            sb.append("\r\n");
            sb.append(split[1]);
            sb.append("\r\n");
            sb.append("--bd_map_sdk_cc");
            sb.append("\r\n");
        }
        if (split[2] != null && !split[2].isEmpty()) {
            sb.append("Content-Disposition: form-data; name=\"timestamp\"\r\n");
            sb.append("\r\n");
            sb.append(split[2]);
            sb.append("\r\n");
            sb.append("--bd_map_sdk_cc");
            sb.append("\r\n");
        }
        sb.append("Content-Disposition: form-data; name=\"os\"\r\n");
        sb.append("\r\n");
        sb.append("android");
        sb.append("\r\n");
        sb.append("--bd_map_sdk_cc");
        sb.append("\r\n");
        return sb;
    }

    /* renamed from: a */
    private byte[] m18489a(byte[] bArr) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        m18491a(byteArrayInputStream, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        byteArrayInputStream.close();
        return byteArray;
    }

    /* renamed from: a */
    private void m18491a(InputStream inputStream, OutputStream outputStream) throws Exception {
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, 1024);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                try {
                    outputStream.close();
                    inputStream.close();
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
        }
    }
}
