package com.baidu.location.p140e;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.ServiceC2737f;
import com.baidu.location.p138c.C2688a;
import com.baidu.location.p138c.C2689b;
import com.baidu.location.p138c.C2697f;
import com.baidu.location.p138c.C2710k;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.e.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2735k {

    /* renamed from: A */
    public static float f5742A = 2.3f;

    /* renamed from: B */
    public static float f5743B = 3.8f;

    /* renamed from: C */
    public static int f5744C = 3;

    /* renamed from: D */
    public static int f5745D = 10;

    /* renamed from: E */
    public static int f5746E = 2;

    /* renamed from: F */
    public static int f5747F = 7;

    /* renamed from: G */
    public static int f5748G = 20;

    /* renamed from: H */
    public static int f5749H = 70;

    /* renamed from: I */
    public static int f5750I = 120;

    /* renamed from: J */
    public static float f5751J = 2.0f;

    /* renamed from: K */
    public static float f5752K = 10.0f;

    /* renamed from: L */
    public static float f5753L = 50.0f;

    /* renamed from: M */
    public static float f5754M = 200.0f;

    /* renamed from: N */
    public static int f5755N = 16;

    /* renamed from: O */
    public static int f5756O = 32;

    /* renamed from: P */
    public static float f5757P = 0.9f;

    /* renamed from: Q */
    public static int f5758Q = 10000;

    /* renamed from: R */
    public static float f5759R = 0.5f;

    /* renamed from: S */
    public static float f5760S = 0.0f;

    /* renamed from: T */
    public static float f5761T = 0.1f;

    /* renamed from: U */
    public static int f5762U = 30;

    /* renamed from: V */
    public static int f5763V = 100;

    /* renamed from: W */
    public static int f5764W = 0;

    /* renamed from: X */
    public static int f5765X = 0;

    /* renamed from: Y */
    public static int f5766Y = 0;

    /* renamed from: Z */
    public static int f5767Z = 420000;

    /* renamed from: a */
    public static boolean f5768a = false;

    /* renamed from: aA */
    public static double f5769aA = -0.10000000149011612d;

    /* renamed from: aB */
    public static int f5770aB = 0;

    /* renamed from: aC */
    public static int f5771aC = 0;

    /* renamed from: aD */
    public static int f5772aD = 1;

    /* renamed from: aE */
    public static boolean f5773aE = false;

    /* renamed from: aF */
    public static int f5774aF = -1;

    /* renamed from: aG */
    public static int f5775aG = 10;

    /* renamed from: aH */
    public static int f5776aH = 3;

    /* renamed from: aI */
    public static int f5777aI = 40;

    /* renamed from: aJ */
    public static double[] f5778aJ = null;

    /* renamed from: aK */
    public static int f5779aK = 1;

    /* renamed from: aL */
    public static int f5780aL = 1;

    /* renamed from: aM */
    public static int f5781aM = 1;

    /* renamed from: aa */
    public static boolean f5784aa = true;

    /* renamed from: ab */
    public static boolean f5785ab = true;

    /* renamed from: ac */
    public static int f5786ac = 20;

    /* renamed from: ad */
    public static int f5787ad = 300;

    /* renamed from: ae */
    public static int f5788ae = 1000;

    /* renamed from: af */
    public static int f5789af = Integer.MAX_VALUE;

    /* renamed from: ag */
    public static long f5790ag = 900000;

    /* renamed from: ah */
    public static long f5791ah = 420000;

    /* renamed from: ai */
    public static long f5792ai = 180000;

    /* renamed from: aj */
    public static long f5793aj = 0;

    /* renamed from: ak */
    public static long f5794ak = 15;

    /* renamed from: al */
    public static long f5795al = 300000;

    /* renamed from: am */
    public static int f5796am = 1000;

    /* renamed from: an */
    public static int f5797an = 0;

    /* renamed from: ao */
    public static int f5798ao = 30000;

    /* renamed from: ap */
    public static int f5799ap = 30000;

    /* renamed from: aq */
    public static float f5800aq = 10.0f;

    /* renamed from: ar */
    public static float f5801ar = 6.0f;

    /* renamed from: as */
    public static float f5802as = 10.0f;

    /* renamed from: at */
    public static int f5803at = 60;

    /* renamed from: au */
    public static int f5804au = 70;

    /* renamed from: av */
    public static int f5805av = 6;

    /* renamed from: aw */
    public static String f5806aw = null;

    /* renamed from: ax */
    public static boolean f5807ax = false;

    /* renamed from: ay */
    public static int f5808ay = 16;

    /* renamed from: az */
    public static float f5809az = 0.75f;

    /* renamed from: b */
    public static boolean f5810b = false;

    /* renamed from: c */
    public static boolean f5811c = false;

    /* renamed from: d */
    public static int f5812d = 0;

    /* renamed from: e */
    public static String f5813e = "no";

    /* renamed from: f */
    public static int f5814f = 4;

    /* renamed from: g */
    public static boolean f5815g = false;

    /* renamed from: h */
    public static boolean f5816h = false;

    /* renamed from: i */
    public static boolean f5817i = false;

    /* renamed from: j */
    public static boolean f5818j = false;

    /* renamed from: k */
    public static boolean f5819k = false;

    /* renamed from: l */
    public static boolean f5820l = false;

    /* renamed from: m */
    public static String f5821m = "gcj02";

    /* renamed from: n */
    public static String f5822n = "";

    /* renamed from: o */
    public static boolean f5823o = true;

    /* renamed from: p */
    public static int f5824p = 3;

    /* renamed from: q */
    public static double f5825q = 0.0d;

    /* renamed from: r */
    public static double f5826r = 0.0d;

    /* renamed from: s */
    public static double f5827s = 0.0d;

    /* renamed from: t */
    public static double f5828t = 0.0d;

    /* renamed from: u */
    public static int f5829u = 0;

    /* renamed from: v */
    public static byte[] f5830v = null;

    /* renamed from: w */
    public static boolean f5831w = false;

    /* renamed from: x */
    public static int f5832x = 0;

    /* renamed from: y */
    public static float f5833y = 1.1f;

    /* renamed from: z */
    public static float f5834z = 2.2f;

    /* renamed from: aO */
    private static String f5783aO = Build.MANUFACTURER;

    /* renamed from: aN */
    public static boolean f5782aN = false;

    /* renamed from: a */
    public static double m19070a(double d, double d2, double d3, double d4) {
        float[] fArr = new float[1];
        Location.distanceBetween(d, d2, d3, d4, fArr);
        return fArr[0];
    }

    /* renamed from: a */
    public static int m19068a(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Exception unused) {
            return 2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0017 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0016 A[RETURN] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m19067a(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = 0
            r1 = 1
            int r2 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L13
            int r3 = android.os.Process.myUid()     // Catch: java.lang.Exception -> L13
            int r4 = r4.checkPermission(r5, r2, r3)     // Catch: java.lang.Exception -> L13
            if (r4 != 0) goto L11
            goto L13
        L11:
            r4 = r0
            goto L14
        L13:
            r4 = r1
        L14:
            if (r4 != 0) goto L17
            return r0
        L17:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p140e.C2735k.m19067a(android.content.Context, java.lang.String):int");
    }

    /* renamed from: a */
    public static int m19062a(Object obj, String str) throws Exception {
        Method declaredMethod = obj.getClass().getDeclaredMethod(str, null);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return ((Integer) declaredMethod.invoke(obj, null)).intValue();
    }

    /* renamed from: a */
    public static int m19060a(String str, String str2, String str3) {
        int indexOf;
        int length;
        int indexOf2;
        String substring;
        if (str != null && !str.equals("") && (indexOf = str.indexOf(str2)) != -1 && (indexOf2 = str.indexOf(str3, (length = indexOf + str2.length()))) != -1 && (substring = str.substring(length, indexOf2)) != null && !substring.equals("")) {
            try {
                return Integer.parseInt(substring);
            } catch (NumberFormatException unused) {
            }
        }
        return Integer.MIN_VALUE;
    }

    /* renamed from: a */
    public static String m19072a() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(5);
        return String.format(Locale.CHINA, "%d-%02d-%02d %02d:%02d:%02d", Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(i), Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12)), Integer.valueOf(calendar.get(13)));
    }

    /* renamed from: a */
    public static String m19064a(C2688a c2688a, C2710k c2710k, Location location, String str, int i) {
        return m19063a(c2688a, c2710k, location, str, i, false);
    }

    /* renamed from: a */
    public static String m19063a(C2688a c2688a, C2710k c2710k, Location location, String str, int i, boolean z) {
        String m19246b;
        StringBuffer stringBuffer = new StringBuffer(2048);
        if (c2688a != null && (m19246b = C2689b.m19259a().m19246b(c2688a)) != null) {
            stringBuffer.append(m19246b);
        }
        if (c2710k != null) {
            String m19150b = i == 0 ? z ? c2710k.m19150b() : c2710k.m19146c() : c2710k.m19144d();
            if (m19150b != null) {
                stringBuffer.append(m19150b);
            }
        }
        if (location != null) {
            String m19203b = (f5812d == 0 || i == 0) ? C2697f.m19203b(location) : C2697f.m19192c(location);
            if (m19203b != null) {
                stringBuffer.append(m19203b);
            }
        }
        String m19093a = C2721b.m19096a().m19093a(i == 0);
        if (m19093a != null) {
            stringBuffer.append(m19093a);
        }
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(C2697f.m19228a().m19158m());
        String m19252a = C2689b.m19259a().m19252a(c2688a);
        if (m19252a != null && m19252a.length() + stringBuffer.length() < 2000) {
            stringBuffer.append(m19252a);
        }
        String stringBuffer2 = stringBuffer.toString();
        if (location != null && c2710k != null) {
            try {
                float speed = location.getSpeed();
                int i2 = f5812d;
                int m19140h = c2710k.m19140h();
                int m19156a = c2710k.m19156a();
                boolean m19139i = c2710k.m19139i();
                if (speed < f5801ar && ((i2 == 1 || i2 == 0) && (m19140h < f5803at || m19139i))) {
                    f5824p = 1;
                } else if (speed < f5802as && ((i2 == 1 || i2 == 0 || i2 == 3) && (m19140h < f5804au || m19156a > f5805av))) {
                    f5824p = 2;
                }
            } catch (Exception unused) {
                f5824p = 3;
            }
            return stringBuffer2;
        }
        f5824p = 3;
        return stringBuffer2;
    }

    /* renamed from: a */
    public static String m19061a(String str) {
        return Jni.en1(f5822n + ";" + str);
    }

    /* renamed from: a */
    public static String m19058a(byte[] bArr, String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (z) {
                hexString = hexString.toUpperCase();
            }
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
            sb.append(str);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static String m19057a(byte[] bArr, boolean z) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return m19058a(messageDigest.digest(), "", z);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public static boolean m19071a(double d, double d2) {
        return Math.abs(d - d2) <= 1.192092896E-7d;
    }

    /* renamed from: a */
    public static boolean m19069a(float f, float f2) {
        return Math.abs(f - f2) <= 1.1920929E-7f;
    }

    /* renamed from: a */
    public static boolean m19066a(Location location) {
        String str;
        if (location == null || (str = f5783aO) == null) {
            return false;
        }
        if ("huawei".equalsIgnoreCase(str)) {
            try {
                Bundle extras = location.getExtras();
                if (extras == null) {
                    return false;
                }
                if ((extras.getInt("SourceType") & 128) != 128) {
                    return false;
                }
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m19065a(BDLocation bDLocation) {
        int locType = bDLocation.getLocType();
        return (locType > 100 && locType < 200) || locType == 62;
    }

    /* renamed from: a */
    public static byte[] m19059a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    public static int m19055b(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return Settings.Secure.getInt(context.getContentResolver(), "location_mode", -1);
            } catch (Exception unused) {
                return -1;
            }
        }
        return -2;
    }

    /* renamed from: b */
    public static boolean m19056b() {
        return false;
    }

    /* renamed from: b */
    public static boolean m19054b(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m19053b(String str) {
        try {
            return Class.forName(str) != null;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static byte[] m19052b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
            byte[] bArr2 = new byte[2048];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: c */
    public static String m19051c() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet6Address) && nextElement.getHostAddress() != null && !nextElement.getHostAddress().startsWith("fe80:")) {
                        return nextElement.getHostAddress();
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m19049c(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
        } catch (Exception unused) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
            }
            if (TextUtils.isEmpty(readLine)) {
                return null;
            }
            return readLine;
        } catch (Exception unused3) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException unused4) {
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException unused5) {
                }
            }
            throw th;
        }
    }

    /* renamed from: c */
    public static boolean m19050c(Context context) {
        int i;
        if (context != null) {
            try {
                i = context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION");
            } catch (Exception e) {
                e.printStackTrace();
                i = 0;
            }
            boolean z = i == 0;
            if (z && Build.VERSION.SDK_INT >= 23) {
                try {
                    if (Settings.Secure.getInt(context.getContentResolver(), "location_mode", 1) == 0) {
                        return false;
                    }
                } catch (Exception unused) {
                }
            }
            return z;
        }
        return true;
    }

    /* renamed from: d */
    public static long m19046d(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime() / 1000;
        } catch (Exception unused) {
            return 0L;
        }
    }

    /* renamed from: d */
    public static String m19048d() {
        return "https://daup.map.baidu.com/cltr/rcvr";
    }

    /* renamed from: d */
    public static String m19047d(Context context) {
        int m19067a = m19067a(context, "android.permission.ACCESS_COARSE_LOCATION");
        int m19067a2 = m19067a(context, "android.permission.ACCESS_FINE_LOCATION");
        int m19067a3 = m19067a(context, "android.permission.READ_PHONE_STATE");
        if (Build.VERSION.SDK_INT < 29) {
            return "&per=" + m19067a + "|" + m19067a2 + "|" + m19067a3;
        }
        int m19067a4 = m19067a(context, "android.permission.ACCESS_BACKGROUND_LOCATION");
        return "&per=" + m19067a + "|" + m19067a2 + "|" + m19067a3 + "|" + m19067a4;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x003f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m19045e() {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            r2 = 0
            if (r0 > r1) goto L20
            java.lang.String r0 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.Exception -> L1c
            java.lang.String r3 = "mounted"
            boolean r0 = r0.equals(r3)     // Catch: java.lang.Exception -> L1c
            if (r0 == 0) goto L20
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Exception -> L1c
            java.lang.String r0 = r0.getPath()     // Catch: java.lang.Exception -> L1c
            goto L21
        L1c:
            r0 = move-exception
            r0.printStackTrace()
        L20:
            r0 = r2
        L21:
            if (r0 != 0) goto L3d
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 <= r1) goto L3d
            android.content.Context r1 = com.baidu.location.ServiceC2737f.getServiceContext()
            if (r1 == 0) goto L3d
            android.content.Context r0 = com.baidu.location.ServiceC2737f.getServiceContext()     // Catch: java.lang.Exception -> L3c
            java.lang.String r1 = android.os.Environment.DIRECTORY_MOVIES     // Catch: java.lang.Exception -> L3c
            java.io.File r0 = r0.getExternalFilesDir(r1)     // Catch: java.lang.Exception -> L3c
            java.lang.String r0 = r0.getAbsolutePath()     // Catch: java.lang.Exception -> L3c
            goto L3d
        L3c:
            r0 = r2
        L3d:
            if (r0 == 0) goto L64
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5f
            r1.<init>()     // Catch: java.lang.Exception -> L5f
            r1.append(r0)     // Catch: java.lang.Exception -> L5f
            java.lang.String r3 = "/baidu/tempdata"
            r1.append(r3)     // Catch: java.lang.Exception -> L5f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L5f
            java.io.File r3 = new java.io.File     // Catch: java.lang.Exception -> L5f
            r3.<init>(r1)     // Catch: java.lang.Exception -> L5f
            boolean r1 = r3.exists()     // Catch: java.lang.Exception -> L5f
            if (r1 != 0) goto L64
            r3.mkdirs()     // Catch: java.lang.Exception -> L5f
            goto L64
        L5f:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r2
        L64:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p140e.C2735k.m19045e():java.lang.String");
    }

    /* renamed from: e */
    public static String m19044e(Context context) {
        int i = -1;
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    i = activeNetworkInfo.getType();
                }
            } catch (Throwable unused) {
            }
        }
        return "&netc=" + i;
    }

    /* renamed from: f */
    public static int m19042f(Context context) {
        int m19067a = m19067a(context, "android.permission.ACCESS_FINE_LOCATION");
        if (m19055b(context) == 0 || m19067a != 1) {
            if (m19055b(context) == 0 || m19067a == 1) {
                return (m19055b(context) >= 1 || m19067a != 1) ? 0 : -1;
            }
            return -2;
        }
        return 1;
    }

    /* renamed from: f */
    public static String m19043f() {
        String m19045e = m19045e();
        if (m19045e == null) {
            return null;
        }
        return m19045e + "/baidu/tempdata";
    }

    /* renamed from: g */
    public static String m19041g() {
        try {
            File file = new File(ServiceC2737f.getServiceContext().getFilesDir() + File.separator + "lldt");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: h */
    public static String m19040h() {
        try {
            File file = new File(ServiceC2737f.getServiceContext().getFilesDir() + File.separator + "/baidu/tempdata");
            if (!file.exists()) {
                file.mkdirs();
            }
            return ServiceC2737f.getServiceContext().getFilesDir().getPath();
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: i */
    public static String m19039i() {
        try {
            File file = new File(ServiceC2737f.getServiceContext().getFilesDir() + File.separator + "/baidu/tempdata");
            if (!file.exists()) {
                file.mkdirs();
            }
            return ServiceC2737f.getServiceContext().getFilesDir().getPath() + File.separator + "/baidu/tempdata";
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: j */
    public static String m19038j() {
        return m19049c("ro.mediatek.platform");
    }

    /* renamed from: k */
    public static SSLSocketFactory m19037k() throws Exception {
        TrustManager[] trustManagerArr = {new C2736l()};
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, trustManagerArr, new SecureRandom());
        return sSLContext.getSocketFactory();
    }
}
