package com.alipay.sdk.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;
import com.alipay.sdk.app.C1997i;
import com.alipay.sdk.app.C1998j;
import com.alipay.sdk.app.EnumC1999k;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.cons.C2003a;
import com.alipay.sdk.data.C2006a;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2052n {

    /* renamed from: a */
    static final String f3902a = "com.eg.android.AlipayGphone";

    /* renamed from: b */
    static final int f3903b = 125;

    /* renamed from: c */
    private static final String f3904c = "com.alipay.android.app";

    /* renamed from: d */
    private static final String f3905d = "com.eg.android.AlipayGphoneRC";

    /* renamed from: e */
    private static final int f3906e = 99;

    /* renamed from: f */
    private static final String[] f3907f = {"10.1.5.1013151", "10.1.5.1013148"};

    /* renamed from: g */
    public static String m20644g(Context context) {
        return "-1;-1";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m20674a() {
        if (EnvUtils.isSandBox()) {
            return "com.eg.android.AlipayGphoneRC";
        }
        try {
            return C1997i.f3583a.get(0).f3739a;
        } catch (Throwable unused) {
            return "com.eg.android.AlipayGphone";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m20665a(String str) {
        return (EnvUtils.isSandBox() && TextUtils.equals(str, "com.eg.android.AlipayGphoneRC")) ? "com.eg.android.AlipayGphoneRC.IAlixPay" : "com.eg.android.AlipayGphone.IAlixPay";
    }

    /* renamed from: b */
    public static Map<String, String> m20657b(String str) {
        String[] split;
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("&")) {
            int indexOf = str2.indexOf("=", 1);
            if (-1 != indexOf) {
                hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
            }
        }
        return hashMap;
    }

    /* renamed from: c */
    public static Map<String, String> m20652c(String str) {
        String[] split;
        HashMap hashMap = new HashMap(4);
        int indexOf = str.indexOf(63);
        if (indexOf != -1 && indexOf < str.length() - 1) {
            for (String str2 : str.substring(indexOf + 1).split("&")) {
                int indexOf2 = str2.indexOf(61, 1);
                if (indexOf2 != -1 && indexOf2 < str2.length() - 1) {
                    hashMap.put(str2.substring(0, indexOf2), m20647e(str2.substring(indexOf2 + 1)));
                }
            }
        }
        return hashMap;
    }

    /* renamed from: d */
    public static JSONObject m20649d(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }

    /* renamed from: e */
    public static String m20647e(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            C2000a.m20898a("biz", "H5PayDataAnalysisError", e);
            return "";
        }
    }

    /* renamed from: a */
    public static String m20664a(String str, String str2, String str3) {
        try {
            int indexOf = str3.indexOf(str) + str.length();
            if (indexOf <= str.length()) {
                return "";
            }
            int indexOf2 = TextUtils.isEmpty(str2) ? 0 : str3.indexOf(str2, indexOf);
            if (indexOf2 < 1) {
                return str3.substring(indexOf);
            }
            return str3.substring(indexOf, indexOf2);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m20656b(byte[] bArr) {
        BigInteger modulus;
        try {
            PublicKey publicKey = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey();
            if (!(publicKey instanceof RSAPublicKey) || (modulus = ((RSAPublicKey) publicKey).getModulus()) == null) {
                return null;
            }
            return modulus.toString(16);
        } catch (Exception e) {
            C2000a.m20898a("auth", "GetPublicKeyFromSignEx", e);
            return null;
        }
    }

    /* renamed from: a */
    public static C2053a m20669a(Context context, List<C2006a.C2007a> list) {
        C2053a m20670a;
        if (list == null) {
            return null;
        }
        for (C2006a.C2007a c2007a : list) {
            if (c2007a != null && (m20670a = m20670a(context, c2007a.f3739a, c2007a.f3740b, c2007a.f3741c)) != null && !m20670a.m20641a() && !m20670a.m20640b()) {
                return m20670a;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static C2053a m20670a(Context context, String str, int i, String str2) {
        PackageInfo packageInfo;
        if (EnvUtils.isSandBox() && "com.eg.android.AlipayGphone".equals(str)) {
            str = "com.eg.android.AlipayGphoneRC";
        }
        try {
            packageInfo = m20660b(context, str);
        } catch (Throwable th) {
            C2000a.m20898a("auth", "GetPackageInfoEx", th);
            packageInfo = null;
        }
        if (m20658b(packageInfo)) {
            return m20667a(packageInfo, i, str2);
        }
        return null;
    }

    /* renamed from: b */
    private static boolean m20658b(PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = "info == null";
        } else if (packageInfo.signatures == null) {
            str = "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            C2000a.m20899a("auth", "NotIncludeSignatures", str);
        }
        return z;
    }

    /* renamed from: b */
    private static PackageInfo m20660b(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    /* renamed from: a */
    private static C2053a m20667a(PackageInfo packageInfo, int i, String str) {
        if (packageInfo == null) {
            return null;
        }
        return new C2053a(packageInfo, i, str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.util.n$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C2053a {

        /* renamed from: a */
        public final PackageInfo f3908a;

        /* renamed from: b */
        public final int f3909b;

        /* renamed from: c */
        public final String f3910c;

        public C2053a(PackageInfo packageInfo, int i, String str) {
            this.f3908a = packageInfo;
            this.f3909b = i;
            this.f3910c = str;
        }

        /* renamed from: a */
        public boolean m20641a() {
            Signature[] signatureArr = this.f3908a.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return false;
            }
            for (Signature signature : signatureArr) {
                String m20656b = C2052n.m20656b(signature.toByteArray());
                if (m20656b != null && !TextUtils.equals(m20656b, this.f3910c)) {
                    C2000a.m20899a("biz", "PublicKeyUnmatch", String.format("Got %s, expected %s", m20656b, this.f3910c));
                    return true;
                }
            }
            return false;
        }

        /* renamed from: b */
        public boolean m20640b() {
            return this.f3908a.versionCode < this.f3909b;
        }
    }

    /* renamed from: a */
    public static boolean m20672a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.alipay.android.app", 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m20659b(Context context, List<C2006a.C2007a> list) {
        try {
            for (C2006a.C2007a c2007a : list) {
                if (c2007a != null) {
                    String str = c2007a.f3739a;
                    if (EnvUtils.isSandBox() && "com.eg.android.AlipayGphone".equals(str)) {
                        str = "com.eg.android.AlipayGphoneRC";
                    }
                    try {
                        if (context.getPackageManager().getPackageInfo(str, 128) != null) {
                            return true;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        continue;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            C2000a.m20898a("biz", "CheckLaunchAppExistEx", th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m20668a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        try {
            String str = packageInfo.versionName;
            if (!TextUtils.equals(str, f3907f[0])) {
                if (!TextUtils.equals(str, f3907f[1])) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m20661b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(m20674a(), 128);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode < 99;
        } catch (Throwable th) {
            C2040c.m20715a(th);
            return false;
        }
    }

    /* renamed from: c */
    public static String m20654c(Context context) {
        String m20662b = m20662b();
        String m20655c = m20655c();
        String m20650d = m20650d(context);
        String m20648e = m20648e(context);
        return " (" + m20662b + ";" + m20655c + ";" + m20650d + ";;" + m20648e + ")(sdk android)";
    }

    /* renamed from: b */
    public static String m20662b() {
        return "Android " + Build.VERSION.RELEASE;
    }

    /* renamed from: c */
    public static String m20655c() {
        String m20651d = m20651d();
        int indexOf = m20651d.indexOf("-");
        if (indexOf != -1) {
            m20651d = m20651d.substring(0, indexOf);
        }
        int indexOf2 = m20651d.indexOf("\n");
        if (indexOf2 != -1) {
            m20651d = m20651d.substring(0, indexOf2);
        }
        return "Linux " + m20651d;
    }

    /* renamed from: d */
    private static String m20651d() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
            if (matcher.matches() && matcher.groupCount() >= 4) {
                return matcher.group(1) + "\n" + matcher.group(2) + " " + matcher.group(3) + "\n" + matcher.group(4);
            }
            return "Unavailable";
        } catch (IOException unused) {
            return "Unavailable";
        }
    }

    /* renamed from: d */
    public static String m20650d(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    /* renamed from: e */
    public static String m20648e(Context context) {
        DisplayMetrics m20642i = m20642i(context);
        return m20642i.widthPixels + "*" + m20642i.heightPixels;
    }

    /* renamed from: i */
    private static DisplayMetrics m20642i(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* renamed from: f */
    public static String m20646f(Context context) {
        String m20676a = C2051m.m20676a(context);
        return m20676a.substring(0, m20676a.indexOf("://"));
    }

    /* renamed from: a */
    public static String m20673a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            switch (random.nextInt(3)) {
                case 0:
                    sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 65.0d)));
                    break;
                case 1:
                    sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 97.0d)));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    /* renamed from: f */
    public static boolean m20645f(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m20671a(Context context, String str) {
        String str2 = "";
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    str2 = str2 + "#M";
                } else {
                    if (runningAppProcessInfo.processName.startsWith(str + ":")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        sb.append("#");
                        sb.append(runningAppProcessInfo.processName.replace(str + ":", ""));
                        str2 = sb.toString();
                    }
                }
            }
        } catch (Throwable unused) {
            str2 = "";
        }
        if (str2.length() > 0) {
            str2 = str2.substring(1);
        }
        return str2.length() == 0 ? "N" : str2;
    }

    /* renamed from: a */
    public static boolean m20666a(WebView webView, String str, Activity activity) {
        C2053a m20669a;
        int parseInt;
        String substring;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (activity == null) {
            return false;
        }
        if (str.toLowerCase().startsWith("alipays://platformapi/startApp?".toLowerCase()) || str.toLowerCase().startsWith("intent://platformapi/startapp?".toLowerCase())) {
            try {
                m20669a = m20669a(activity, C1997i.f3583a);
            } catch (Throwable unused) {
            }
            if (m20669a != null && !m20669a.m20640b() && !m20669a.m20641a()) {
                if (str.startsWith("intent://platformapi/startapp")) {
                    str = str.replaceFirst("intent://platformapi/startapp\\?", "alipays://platformapi/startApp?");
                }
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
            return true;
        } else if (TextUtils.equals(str, "sdklite://h5quit") || TextUtils.equals(str, "http://m.alipay.com/?action=h5quit")) {
            C1998j.m20913a(C1998j.m20910c());
            activity.finish();
            return true;
        } else if (str.startsWith("sdklite://h5quit?result=")) {
            try {
                String substring2 = str.substring(str.indexOf("sdklite://h5quit?result=") + 24);
                parseInt = Integer.parseInt(substring2.substring(substring2.lastIndexOf("&end_code=") + 10));
            } catch (Exception unused2) {
                C1998j.m20913a(C1998j.m20908e());
            }
            if (parseInt != EnumC1999k.SUCCEEDED.m20907a() && parseInt != EnumC1999k.PAY_WAITTING.m20907a()) {
                EnumC1999k m20903b = EnumC1999k.m20903b(EnumC1999k.FAILED.m20907a());
                C1998j.m20913a(C1998j.m20914a(m20903b.m20907a(), m20903b.m20904b(), ""));
                activity.runOnUiThread(new RunnableC2054o(activity));
                return true;
            }
            if (C2003a.f3687s) {
                StringBuilder sb = new StringBuilder();
                String decode = URLDecoder.decode(str);
                String decode2 = URLDecoder.decode(decode);
                String str2 = decode2.substring(decode2.indexOf("sdklite://h5quit?result=") + 24, decode2.lastIndexOf("&end_code=")).split("&return_url=")[0];
                int indexOf = decode.indexOf("&return_url=") + 12;
                sb.append(str2);
                sb.append("&return_url=");
                sb.append(decode.substring(indexOf, decode.indexOf("&", indexOf)));
                sb.append(decode.substring(decode.indexOf("&", indexOf)));
                substring = sb.toString();
            } else {
                String decode3 = URLDecoder.decode(str);
                substring = decode3.substring(decode3.indexOf("sdklite://h5quit?result=") + 24, decode3.lastIndexOf("&end_code="));
            }
            EnumC1999k m20903b2 = EnumC1999k.m20903b(parseInt);
            C1998j.m20913a(C1998j.m20914a(m20903b2.m20907a(), m20903b2.m20904b(), substring));
            activity.runOnUiThread(new RunnableC2054o(activity));
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: h */
    public static String m20643h(Context context) {
        return m20653c(context, context.getPackageName());
    }

    /* renamed from: c */
    private static String m20653c(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128).versionName;
        } catch (Throwable th) {
            C2000a.m20898a("biz", "GetPackageInfoEx", th);
            return "";
        }
    }
}
