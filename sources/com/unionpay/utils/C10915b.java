package com.unionpay.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.utils.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10915b {

    /* renamed from: a */
    private static HashMap f20828a = new C10916c();

    /* renamed from: a */
    public static int m5856a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.parseInt(str);
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static String m5862a() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new DecimalFormat("0000000").format(new SecureRandom().nextInt(10000000)));
        stringBuffer.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis())));
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m5861a(Context context) {
        int i;
        String str = "";
        String m5870a = UPUtils.m5870a(context, "configs");
        String m5870a2 = UPUtils.m5870a(context, "mode");
        String m5870a3 = UPUtils.m5870a(context, "or");
        if (!TextUtils.isEmpty(m5870a) && !TextUtils.isEmpty(m5870a2) && !TextUtils.isEmpty(m5870a3)) {
            try {
                JSONObject jSONObject = new JSONObject(m5870a);
                String m5834a = C10922i.m5834a(jSONObject, "sign");
                try {
                    i = Integer.parseInt(m5870a2);
                } catch (Exception unused) {
                    i = 0;
                }
                String str2 = new String(Base64.decode(jSONObject.getString("configs"), 2));
                String str3 = jSONObject.has("sePayConf") ? new String(Base64.decode(jSONObject.getString("sePayConf"), 2)) : "";
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                String m5851b = m5851b(UPUtils.m5868a(str2 + str3 + m5870a3));
                String forConfig = UPUtils.forConfig(i, m5834a);
                if (!TextUtils.isEmpty(forConfig)) {
                    if (forConfig.equals(m5851b)) {
                        str = str2;
                    }
                }
            } catch (Exception unused2) {
            }
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                Object m5835a = C10922i.m5835a(jSONArray, i2);
                if (m5835a != null) {
                    JSONObject jSONObject2 = (JSONObject) m5835a;
                    if ("app".equals(C10922i.m5834a(jSONObject2, "type"))) {
                        return new String(Base64.decode(C10922i.m5834a(jSONObject2, "ca"), 2));
                    }
                }
            }
            return "";
        } catch (Exception unused3) {
            return "";
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:15|(2:16|17)|(6:36|37|20|21|(2:23|24)|(2:27|28)(1:31))|19|20|21|(0)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x005e, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x005f, code lost:
        r2.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0063, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0064, code lost:
        r2.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0050 A[Catch: CertificateEncodingException -> 0x005e, NoSuchAlgorithmException -> 0x0063, Exception -> 0x0072, TRY_LEAVE, TryCatch #6 {NoSuchAlgorithmException -> 0x0063, CertificateEncodingException -> 0x005e, blocks: (B:33:0x004a, B:35:0x0050), top: B:55:0x004a, outer: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0069 A[Catch: Exception -> 0x0072, TRY_LEAVE, TryCatch #5 {Exception -> 0x0072, blocks: (B:4:0x0003, B:9:0x000d, B:15:0x0019, B:17:0x001d, B:19:0x0020, B:21:0x0025, B:22:0x0030, B:28:0x003e, B:33:0x004a, B:35:0x0050, B:42:0x0069, B:38:0x005f, B:40:0x0064, B:31:0x0046, B:25:0x0038, B:12:0x0013), top: B:53:0x0003, inners: #0, #3, #4, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m5859a(android.content.Context r2, java.lang.String r3, java.lang.String r4) {
        /*
            r0 = 0
            if (r2 == 0) goto L8
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch: java.lang.Exception -> L72
            goto L9
        L8:
            r2 = r0
        L9:
            if (r2 == 0) goto L16
            r1 = 64
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L12 java.lang.Exception -> L72
            goto L17
        L12:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Exception -> L72
        L16:
            r2 = r0
        L17:
            if (r2 == 0) goto L72
            android.content.pm.Signature[] r2 = r2.signatures     // Catch: java.lang.Exception -> L72
            if (r2 == 0) goto L72
            int r3 = r2.length     // Catch: java.lang.Exception -> L72
            if (r3 <= 0) goto L72
            r3 = 0
            r1 = r2[r3]     // Catch: java.lang.Exception -> L72
            if (r1 == 0) goto L72
            r2 = r2[r3]     // Catch: java.lang.Exception -> L72
            byte[] r2 = r2.toByteArray()     // Catch: java.lang.Exception -> L72
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch: java.lang.Exception -> L72
            r3.<init>(r2)     // Catch: java.lang.Exception -> L72
            java.lang.String r2 = "X509"
            java.security.cert.CertificateFactory r2 = java.security.cert.CertificateFactory.getInstance(r2)     // Catch: java.security.cert.CertificateException -> L37 java.lang.Exception -> L72
            goto L3c
        L37:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Exception -> L72
            r2 = r0
        L3c:
            if (r2 == 0) goto L49
            java.security.cert.Certificate r2 = r2.generateCertificate(r3)     // Catch: java.security.cert.CertificateException -> L45 java.lang.Exception -> L72
            java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2     // Catch: java.security.cert.CertificateException -> L45 java.lang.Exception -> L72
            goto L4a
        L45:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Exception -> L72
        L49:
            r2 = r0
        L4a:
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r4)     // Catch: java.security.cert.CertificateEncodingException -> L5e java.security.NoSuchAlgorithmException -> L63 java.lang.Exception -> L72
            if (r2 == 0) goto L67
            byte[] r2 = r2.getEncoded()     // Catch: java.security.cert.CertificateEncodingException -> L5e java.security.NoSuchAlgorithmException -> L63 java.lang.Exception -> L72
            byte[] r2 = r3.digest(r2)     // Catch: java.security.cert.CertificateEncodingException -> L5e java.security.NoSuchAlgorithmException -> L63 java.lang.Exception -> L72
            java.lang.String r2 = m5855a(r2)     // Catch: java.security.cert.CertificateEncodingException -> L5e java.security.NoSuchAlgorithmException -> L63 java.lang.Exception -> L72
            r0 = r2
            goto L67
        L5e:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Exception -> L72
            goto L67
        L63:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Exception -> L72
        L67:
            if (r0 == 0) goto L72
            java.lang.String r2 = ":"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.replaceAll(r2, r3)     // Catch: java.lang.Exception -> L72
            return r2
        L72:
            java.lang.String r2 = ""
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.utils.C10915b.m5859a(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static String m5857a(InputStream inputStream, String str) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return byteArrayOutputStream.toString(str);
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* renamed from: a */
    private static String m5855a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static boolean m5860a(Context context, String str) {
        PackageInfo packageInfo = null;
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null && !TextUtils.isEmpty(str)) {
                    packageInfo = packageManager.getPackageInfo(str, 0);
                }
            } catch (Exception unused) {
            }
        }
        return packageInfo != null;
    }

    /* renamed from: a */
    public static boolean m5858a(Context context, String str, String str2, String str3) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    String m5850c = m5850c(context, str);
                    if (!TextUtils.isEmpty(m5850c) && m5860a(context, str) && m5850c.matches(str3)) {
                        if (str2.equalsIgnoreCase(m5859a(context, str, "SHA1"))) {
                            return true;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: b */
    public static String m5853b(Context context, String str) {
        return m5859a(context, str, "SHA1");
    }

    /* renamed from: b */
    public static String m5851b(String str) {
        byte[] bytes;
        if (str == null) {
            return "";
        }
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        for (byte b : str.getBytes()) {
            sb.append(charArray[(b & 240) >> 4]);
            sb.append(charArray[b & 15]);
        }
        return sb.toString().trim();
    }

    /* renamed from: b */
    public static boolean m5854b() {
        try {
            return "HUAWEI".equalsIgnoreCase(Build.MANUFACTURER);
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m5852b(Context context, String str, String str2, String str3) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                    int m5846e = m5846e(context, str);
                    int m5844f = m5844f(str3);
                    if (m5860a(context, str) && m5846e >= m5844f) {
                        if (str2.equalsIgnoreCase(m5859a(context, str, "SHA256"))) {
                            return true;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: c */
    public static String m5850c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            return packageInfo != null ? packageInfo.versionName : "";
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m5849c(String str) {
        return !TextUtils.isEmpty((CharSequence) f20828a.get(str)) ? (String) f20828a.get(str) : str;
    }

    /* renamed from: d */
    public static boolean m5848d(Context context, String str) {
        return m5860a(context, str);
    }

    /* renamed from: d */
    public static final boolean m5847d(String str) {
        return !Pattern.compile("[^0-9]+").matcher(str).find();
    }

    /* renamed from: e */
    private static int m5846e(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: e */
    public static String m5845e(String str) {
        if (str != null) {
            try {
                return Pattern.compile("[\":,\\[\\]{}]").matcher(str).replaceAll("").trim();
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    /* renamed from: f */
    private static int m5844f(String str) {
        try {
            return Integer.valueOf(str, 10).intValue();
        } catch (Exception unused) {
            return Integer.MAX_VALUE;
        }
    }
}
