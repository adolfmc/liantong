package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.WebView;
import com.bytedance.applog.C3565d1;
import com.example.asus.detectionandalign.DetectionAuthentic;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.bytedance.applog.v0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3710v0 {

    /* renamed from: a */
    public static JSONObject f8858a;

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0034, code lost:
        r0 = r4[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0037, code lost:
        r2.close();
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003f, code lost:
        r1.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0078 A[Catch: IOException -> 0x0074, TRY_LEAVE, TryCatch #2 {IOException -> 0x0074, blocks: (B:44:0x0070, B:48:0x0078), top: B:54:0x0070 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m17105a() {
        /*
            java.lang.String r0 = ":"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L54
            java.lang.String r3 = "/proc/cpuinfo"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L54
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L4c java.io.IOException -> L4e
        Lf:
            java.lang.String r4 = r3.readLine()     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L6c
            if (r4 == 0) goto L43
            boolean r5 = r4.contains(r0)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L6c
            if (r5 == 0) goto Lf
            java.lang.String[] r4 = r4.split(r0)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L6c
            r5 = 0
            r6 = r4[r5]     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L6c
            java.lang.String r7 = "Hardware"
            boolean r6 = r6.contains(r7)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L6c
            if (r6 != 0) goto L34
            r5 = r4[r5]     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L6c
            java.lang.String r6 = "model name"
            boolean r5 = r5.contains(r6)     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L6c
            if (r5 == 0) goto Lf
        L34:
            r0 = 1
            r0 = r4[r0]     // Catch: java.io.IOException -> L4a java.lang.Throwable -> L6c
            r2.close()     // Catch: java.io.IOException -> L3e
            r3.close()     // Catch: java.io.IOException -> L3e
            goto L42
        L3e:
            r1 = move-exception
            r1.printStackTrace()
        L42:
            return r0
        L43:
            r2.close()     // Catch: java.io.IOException -> L60
            r3.close()     // Catch: java.io.IOException -> L60
            goto L6b
        L4a:
            r0 = move-exception
            goto L57
        L4c:
            r0 = move-exception
            goto L6e
        L4e:
            r0 = move-exception
            r3 = r1
            goto L57
        L51:
            r0 = move-exception
            r2 = r1
            goto L6e
        L54:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L57:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L6c
            if (r2 == 0) goto L62
            r2.close()     // Catch: java.io.IOException -> L60
            goto L62
        L60:
            r0 = move-exception
            goto L68
        L62:
            if (r3 == 0) goto L6b
            r3.close()     // Catch: java.io.IOException -> L60
            goto L6b
        L68:
            r0.printStackTrace()
        L6b:
            return r1
        L6c:
            r0 = move-exception
            r1 = r3
        L6e:
            if (r2 == 0) goto L76
            r2.close()     // Catch: java.io.IOException -> L74
            goto L76
        L74:
            r1 = move-exception
            goto L7c
        L76:
            if (r1 == 0) goto L7f
            r1.close()     // Catch: java.io.IOException -> L74
            goto L7f
        L7c:
            r1.printStackTrace()
        L7f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17105a():java.lang.String");
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0052: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:34:0x0052 */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m17101a(android.graphics.Bitmap r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L27
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L24
            r1.<init>()     // Catch: java.lang.Throwable -> L22 java.io.IOException -> L24
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.io.IOException -> L20 java.lang.Throwable -> L51
            r3 = 15
            r4.compress(r2, r3, r1)     // Catch: java.io.IOException -> L20 java.lang.Throwable -> L51
            r1.flush()     // Catch: java.io.IOException -> L20 java.lang.Throwable -> L51
            r1.close()     // Catch: java.io.IOException -> L20 java.lang.Throwable -> L51
            byte[] r4 = r1.toByteArray()     // Catch: java.io.IOException -> L20 java.lang.Throwable -> L51
            r2 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r2)     // Catch: java.io.IOException -> L20 java.lang.Throwable -> L51
            r0 = r1
            goto L2d
        L20:
            r4 = move-exception
            goto L3f
        L22:
            r4 = move-exception
            goto L53
        L24:
            r4 = move-exception
            r1 = r0
            goto L3f
        L27:
            java.lang.String r4 = "shot is null!"
            com.bytedance.applog.C3704u2.m17108a(r4, r0)     // Catch: java.lang.Throwable -> L3b java.io.IOException -> L3d
            r4 = r0
        L2d:
            if (r0 == 0) goto L50
            r0.flush()     // Catch: java.io.IOException -> L36
            r0.close()     // Catch: java.io.IOException -> L36
            goto L50
        L36:
            r0 = move-exception
            r0.printStackTrace()
            goto L50
        L3b:
            r4 = move-exception
            goto L53
        L3d:
            r4 = move-exception
            r1 = r0
        L3f:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L51
            if (r1 == 0) goto L4f
            r1.flush()     // Catch: java.io.IOException -> L4b
            r1.close()     // Catch: java.io.IOException -> L4b
            goto L4f
        L4b:
            r4 = move-exception
            r4.printStackTrace()
        L4f:
            r4 = r0
        L50:
            return r4
        L51:
            r4 = move-exception
            r0 = r1
        L53:
            if (r0 == 0) goto L60
            r0.flush()     // Catch: java.io.IOException -> L5c
            r0.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r0 = move-exception
            r0.printStackTrace()
        L60:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17101a(android.graphics.Bitmap):java.lang.String");
    }

    /* renamed from: a */
    public static String m17092a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; bArr != null && i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static JSONObject m17098a(C3672q0 c3672q0) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DetectionAuthentic.FRAME, c3672q0.m17140j());
            jSONObject.put("element_path", c3672q0.f8668o);
            ArrayList<String> arrayList = c3672q0.f8670q;
            if (arrayList != null && arrayList.size() > 0) {
                c3672q0.f8676w = new ArrayList<>();
                for (int i = 0; i < c3672q0.f8670q.size(); i++) {
                    c3672q0.f8676w.add("*");
                }
                jSONObject.put("positions", new JSONArray((Collection) c3672q0.f8670q));
                jSONObject.put("fuzzy_positions", new JSONArray((Collection) c3672q0.f8676w));
            }
            ArrayList<String> arrayList2 = c3672q0.f8669p;
            if (arrayList2 != null && arrayList2.size() > 0) {
                jSONObject.put("texts", new JSONArray((Collection) c3672q0.f8669p));
            }
            jSONObject.put("zIndex", c3672q0.f8766A);
            jSONObject.put("ignore", c3672q0.f8767B);
            jSONObject.put("is_html", c3672q0.f8675v);
            JSONArray jSONArray = new JSONArray();
            for (C3672q0 c3672q02 : c3672q0.f8771F) {
                jSONArray.put(m17098a(c3672q02));
            }
            jSONObject.put("children", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m17096a(String str, int i, String str2) {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put("id", str);
            jSONObject.put("slot_index", i);
            jSONObject.put("type", str2);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static void m17093a(JSONObject jSONObject, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        jSONObject.put(str, str2);
    }

    /* renamed from: a */
    public static boolean m17102a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m17094a(@Nullable JSONArray jSONArray) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && !TextUtils.isEmpty(optJSONObject.optString("id"))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003e, code lost:
        if (r1 == null) goto L9;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m17091b() {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.String r2 = "/system/bin/cat"
            java.lang.String r3 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.lang.ProcessBuilder r3 = new java.lang.ProcessBuilder     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r3.<init>(r2)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.lang.Process r2 = r3.start()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.io.InputStream r1 = r2.getInputStream()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r2 = 24
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
        L1c:
            int r3 = r1.read(r2)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r4 = -1
            if (r3 == r4) goto L40
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r3.<init>()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r3.append(r0)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.lang.String r0 = new java.lang.String     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r0.<init>(r2)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r3.append(r0)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.lang.String r0 = r3.toString()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            goto L1c
        L38:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L4d
            java.lang.String r0 = "N/A"
            if (r1 == 0) goto L48
        L40:
            r1.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r1 = move-exception
            r1.printStackTrace()
        L48:
            java.lang.String r0 = r0.trim()
            return r0
        L4d:
            r0 = move-exception
            if (r1 == 0) goto L58
            r1.close()     // Catch: java.io.IOException -> L54
            goto L58
        L54:
            r1 = move-exception
            r1.printStackTrace()
        L58:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17091b():java.lang.String");
    }

    @NonNull
    @SuppressLint({"MissingPermission"})
    /* renamed from: b */
    public static JSONArray m17090b(Context context) {
        JSONArray jSONArray = new JSONArray();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                jSONArray.put(m17096a(telephonyManager.getMeid(0), 0, "meid"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                jSONArray.put(m17096a(telephonyManager.getMeid(1), 1, "meid"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                jSONArray.put(m17096a(telephonyManager.getImei(0), 0, "imei"));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            try {
                jSONArray.put(m17096a(telephonyManager.getImei(1), 1, "imei"));
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        } else {
            jSONArray.put(m17096a(m17103a(context, 0), 0, "unknown"));
            jSONArray.put(m17096a(m17103a(context, 1), 1, "unknown"));
        }
        return jSONArray;
    }

    /* renamed from: b */
    public static boolean m17089b(String str) {
        return str == null || str.length() == 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0045, code lost:
        if (r2 == null) goto L20;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m17088b(byte[] r6) {
        /*
            r0 = 0
            if (r6 == 0) goto L4f
            int r1 = r6.length
            if (r1 > 0) goto L7
            goto L4f
        L7:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3f
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3f
            java.util.zip.GZIPInputStream r6 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L40
            r6.<init>(r2)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L40
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
        L1a:
            int r3 = r6.read(r0)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            if (r3 < 0) goto L25
            r4 = 0
            r1.write(r0, r4, r3)     // Catch: java.lang.Throwable -> L29 java.io.IOException -> L2e
            goto L1a
        L25:
            r6.close()     // Catch: java.io.IOException -> L47
            goto L47
        L29:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L34
        L2e:
            r0 = r6
            goto L40
        L30:
            r6 = move-exception
            goto L34
        L32:
            r6 = move-exception
            r2 = r0
        L34:
            if (r0 == 0) goto L39
            r0.close()     // Catch: java.io.IOException -> L39
        L39:
            if (r2 == 0) goto L3e
            r2.close()     // Catch: java.io.IOException -> L3e
        L3e:
            throw r6
        L3f:
            r2 = r0
        L40:
            if (r0 == 0) goto L45
            r0.close()     // Catch: java.io.IOException -> L45
        L45:
            if (r2 == 0) goto L4a
        L47:
            r2.close()     // Catch: java.io.IOException -> L4a
        L4a:
            byte[] r6 = r1.toByteArray()
            return r6
        L4f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17088b(byte[]):byte[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003c, code lost:
        r3 = java.lang.Long.parseLong(java.util.regex.Pattern.compile("[^0-9]").matcher(r0).replaceAll("").trim()) * 1024;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003f, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0043, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        r0.printStackTrace();
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0085: MOVE  (r7 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:51:0x0085 */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long m17087c() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L66
            java.lang.String r2 = "/proc/meminfo"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L62 java.lang.Exception -> L66
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r3 = 8192(0x2000, float:1.14794E-41)
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
        Lf:
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L84
            if (r0 == 0) goto L50
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L84
            if (r3 == 0) goto L1c
            goto Lf
        L1c:
            java.lang.String r3 = "MemTotal"
            boolean r3 = r0.contains(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L84
            if (r3 == 0) goto Lf
            java.lang.String r3 = "[^0-9]"
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L84
            java.util.regex.Matcher r0 = r3.matcher(r0)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L84
            java.lang.String r3 = ""
            java.lang.String r0 = r0.replaceAll(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L84
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L84
            long r3 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L84
            r5 = 1024(0x400, double:5.06E-321)
            long r3 = r3 * r5
            r1.close()     // Catch: java.io.IOException -> L43
            goto L47
        L43:
            r0 = move-exception
            r0.printStackTrace()
        L47:
            r2.close()     // Catch: java.io.IOException -> L4b
            goto L4f
        L4b:
            r0 = move-exception
            r0.printStackTrace()
        L4f:
            return r3
        L50:
            r1.close()     // Catch: java.io.IOException -> L54
            goto L79
        L54:
            r0 = move-exception
            r0.printStackTrace()
            goto L79
        L59:
            r0 = move-exception
            goto L6a
        L5b:
            r2 = move-exception
            goto L88
        L5d:
            r2 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
            goto L6a
        L62:
            r1 = move-exception
            r2 = r1
            r1 = r0
            goto L88
        L66:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L6a:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L84
            if (r1 == 0) goto L77
            r1.close()     // Catch: java.io.IOException -> L73
            goto L77
        L73:
            r0 = move-exception
            r0.printStackTrace()
        L77:
            if (r2 == 0) goto L81
        L79:
            r2.close()     // Catch: java.io.IOException -> L7d
            goto L81
        L7d:
            r0 = move-exception
            r0.printStackTrace()
        L81:
            r0 = -1
            return r0
        L84:
            r0 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
        L88:
            if (r1 == 0) goto L92
            r1.close()     // Catch: java.io.IOException -> L8e
            goto L92
        L8e:
            r1 = move-exception
            r1.printStackTrace()
        L92:
            if (r0 == 0) goto L9c
            r0.close()     // Catch: java.io.IOException -> L98
            goto L9c
        L98:
            r0 = move-exception
            r0.printStackTrace()
        L9c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17087c():long");
    }

    @SuppressLint({"HardwareIds"})
    /* renamed from: c */
    public static String m17086c(Context context) {
        String str = null;
        if (context == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26 && context.getApplicationInfo().targetSdkVersion >= 26) {
            try {
                str = Build.getSerial();
            } catch (SecurityException e) {
                C3704u2.m17108a("failed to get Build.SERIAL as no permission: READ_PRIVILEGED_PHONE_STATE or READ_PHONE_STATE", e);
            } catch (Throwable th) {
                C3704u2.m17108a("U SHALL NOT PASS!", th);
            }
        }
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, "unknown")) {
            str = Build.SERIAL;
        }
        return (TextUtils.isEmpty(str) || TextUtils.equals(str, "unknown")) ? "" : str;
    }

    /* renamed from: c */
    public static String m17085c(String str) {
        FileInputStream fileInputStream;
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        if (!file.exists()) {
            return "";
        }
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(fileInputStream));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                        } catch (IOException e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                    return "";
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    return "";
                                }
                            }
                            return "";
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    String sb2 = sb.toString();
                    try {
                        bufferedReader2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                    return sb2;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e8) {
                e = e8;
            }
        } catch (IOException e9) {
            e = e9;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003e, code lost:
        if (r1 == null) goto L9;
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m17084d() {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.String r2 = "/system/bin/cat"
            java.lang.String r3 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.lang.ProcessBuilder r3 = new java.lang.ProcessBuilder     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r3.<init>(r2)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.lang.Process r2 = r3.start()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.io.InputStream r1 = r2.getInputStream()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r2 = 24
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
        L1c:
            int r3 = r1.read(r2)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r4 = -1
            if (r3 == r4) goto L40
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r3.<init>()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r3.append(r0)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.lang.String r0 = new java.lang.String     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r0.<init>(r2)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            r3.append(r0)     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            java.lang.String r0 = r3.toString()     // Catch: java.io.IOException -> L38 java.lang.Throwable -> L4d
            goto L1c
        L38:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L4d
            java.lang.String r0 = "N/A"
            if (r1 == 0) goto L48
        L40:
            r1.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r1 = move-exception
            r1.printStackTrace()
        L48:
            java.lang.String r0 = r0.trim()
            return r0
        L4d:
            r0 = move-exception
            if (r1 == 0) goto L58
            r1.close()     // Catch: java.io.IOException -> L54
            goto L58
        L54:
            r1 = move-exception
            r1.printStackTrace()
        L58:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17084d():java.lang.String");
    }

    @SuppressLint({"HardwareIds"})
    /* renamed from: d */
    public static String[] m17083d(Context context) {
        int i;
        List<SubscriptionInfo> activeSubscriptionInfoList;
        String[] strArr = null;
        if (context == null) {
            return null;
        }
        try {
        } catch (Throwable th) {
            C3704u2.m17108a("", th);
        }
        if (Build.VERSION.SDK_INT < 22) {
            strArr = new String[1];
            try {
                strArr[0] = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            } catch (SecurityException e) {
                e = e;
                e.printStackTrace();
                return strArr;
            }
            return strArr;
        }
        try {
            activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
        } catch (SecurityException e2) {
            e = e2;
            e.printStackTrace();
            return strArr;
        }
        if (activeSubscriptionInfoList != null && !activeSubscriptionInfoList.isEmpty()) {
            strArr = new String[activeSubscriptionInfoList.size()];
            for (i = 0; i < activeSubscriptionInfoList.size(); i++) {
                strArr[i] = activeSubscriptionInfoList.get(i).getIccId();
            }
            return strArr;
        }
        return null;
    }

    /* renamed from: e */
    public static WifiInfo m17081e(Context context) {
        if (context == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            if (((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
                if (connectionInfo.getSSID() != null) {
                    return connectionInfo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m17082d(java.lang.String r3) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r1)
            r1 = 0
            boolean r2 = com.bytedance.applog.AppLog.getEncryptAndCompress()     // Catch: java.lang.Throwable -> L30
            if (r2 == 0) goto L21
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L30
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L30
            java.lang.String r1 = "UTF-8"
            byte[] r3 = r3.getBytes(r1)     // Catch: java.lang.Throwable -> L1e
            r2.write(r3)     // Catch: java.lang.Throwable -> L1e
            r1 = r2
            goto L2a
        L1e:
            r3 = move-exception
            r1 = r2
            goto L31
        L21:
            java.lang.String r2 = "UTF-8"
            byte[] r3 = r3.getBytes(r2)     // Catch: java.lang.Throwable -> L30
            r0.write(r3)     // Catch: java.lang.Throwable -> L30
        L2a:
            if (r1 == 0) goto L42
            r1.close()     // Catch: java.io.IOException -> L3c
            goto L42
        L30:
            r3 = move-exception
        L31:
            java.lang.String r2 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r2, r3)     // Catch: java.lang.Throwable -> L62
            if (r1 == 0) goto L42
            r1.close()     // Catch: java.io.IOException -> L3c
            goto L42
        L3c:
            r3 = move-exception
            java.lang.String r1 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r1, r3)
        L42:
            byte[] r3 = r0.toByteArray()
            boolean r0 = com.bytedance.applog.AppLog.getEncryptAndCompress()
            if (r0 == 0) goto L61
            com.bytedance.applog.InitConfig r0 = com.bytedance.applog.AppLog.getInitConfig()
            com.bytedance.mpaas.IEncryptor r0 = r0.getEncryptor()
            if (r0 == 0) goto L5c
            int r1 = r3.length
            byte[] r3 = r0.encrypt(r3, r1)
            goto L61
        L5c:
            int r0 = r3.length
            byte[] r3 = com.bytedance.applog.encryptor.EncryptorUtil.encrypt(r3, r0)
        L61:
            return r3
        L62:
            r3 = move-exception
            if (r1 == 0) goto L6f
            r1.close()     // Catch: java.io.IOException -> L69
            goto L6f
        L69:
            r0 = move-exception
            java.lang.String r1 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r1, r0)
        L6f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17082d(java.lang.String):byte[]");
    }

    /* renamed from: a */
    public static C3687s1 m17095a(String str, String str2, boolean z, JSONObject jSONObject) {
        String str3 = null;
        if (TextUtils.isEmpty(str)) {
            if (C3704u2.f8845b) {
                C3704u2.m17108a("WebViewJsUtil no event name, ignore " + str, (Throwable) null);
            }
            return null;
        }
        if (jSONObject != null) {
            str3 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        }
        C3687s1 c3687s1 = new C3687s1(str, z, str3);
        if (!TextUtils.isEmpty(str)) {
            try {
                c3687s1.m17233a(Long.parseLong(str2));
            } catch (NumberFormatException e) {
                C3704u2.m17108a("U SHALL NOT PASS!", e);
            }
        }
        return c3687s1;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    @android.support.annotation.Nullable
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m17103a(android.content.Context r4, int r5) {
        /*
            java.lang.String r0 = "getDeviceId"
            r1 = 0
            java.lang.String r2 = "phone"
            java.lang.Object r4 = r4.getSystemService(r2)     // Catch: java.lang.Exception -> L2e
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch: java.lang.Exception -> L2e
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L2e
            r3 = 21
            if (r2 < r3) goto L34
            java.lang.Class r2 = r4.getClass()     // Catch: java.lang.Exception -> L2e
            java.lang.Class[] r3 = m17097a(r0)     // Catch: java.lang.Exception -> L2e
            java.lang.reflect.Method r0 = r2.getMethod(r0, r3)     // Catch: java.lang.Exception -> L2e
            if (r5 < 0) goto L34
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L2e
            r3 = 0
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> L2e
            r2[r3] = r5     // Catch: java.lang.Exception -> L2e
            java.lang.Object r4 = r0.invoke(r4, r2)     // Catch: java.lang.Exception -> L2e
            goto L35
        L2e:
            r4 = move-exception
            java.lang.String r5 = ""
            com.bytedance.applog.C3704u2.m17108a(r5, r4)
        L34:
            r4 = r1
        L35:
            java.lang.String r4 = (java.lang.String) r4
            boolean r5 = com.bytedance.applog.C3704u2.f8845b
            if (r5 == 0) goto L4f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "getDeviceId  deviceId="
            r5.append(r0)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            com.bytedance.applog.C3704u2.m17108a(r5, r1)
        L4f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17103a(android.content.Context, int):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
        r1 = new java.lang.StringBuilder();
        r3 = r2.length;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0055, code lost:
        if (r5 >= r3) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
        r1.append(java.lang.String.format("%02X:", java.lang.Byte.valueOf(r2[r5])));
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0071, code lost:
        if (r1.length() <= 0) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0073, code lost:
        r1.deleteCharAt(r1.length() - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007b, code lost:
        r0 = r1.toString();
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m17104a(android.content.Context r9) {
        /*
            com.bytedance.applog.InitConfig r0 = com.bytedance.applog.AppLog.getInitConfig()
            if (r0 == 0) goto L1d
            com.bytedance.applog.InitConfig r0 = com.bytedance.applog.AppLog.getInitConfig()
            com.bytedance.applog.ISensitiveInfoProvider r0 = r0.getSensitiveInfoProvider()
            if (r0 == 0) goto L1d
            com.bytedance.applog.InitConfig r9 = com.bytedance.applog.AppLog.getInitConfig()
            com.bytedance.applog.ISensitiveInfoProvider r9 = r9.getSensitiveInfoProvider()
            java.lang.String r9 = r9.getMac()
            return r9
        L1d:
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            if (r1 < r2) goto L86
            java.util.Enumeration r1 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch: java.net.SocketException -> L80
        L28:
            if (r1 == 0) goto L86
            boolean r2 = r1.hasMoreElements()     // Catch: java.net.SocketException -> L80
            if (r2 == 0) goto L86
            java.lang.Object r2 = r1.nextElement()     // Catch: java.net.SocketException -> L80
            java.net.NetworkInterface r2 = (java.net.NetworkInterface) r2     // Catch: java.net.SocketException -> L80
            java.lang.String r3 = "wlan0"
            java.lang.String r4 = r2.getName()     // Catch: java.net.SocketException -> L80
            boolean r3 = r3.equals(r4)     // Catch: java.net.SocketException -> L80
            if (r3 == 0) goto L28
            byte[] r2 = r2.getHardwareAddress()     // Catch: java.net.SocketException -> L80
            if (r2 == 0) goto L28
            int r3 = r2.length     // Catch: java.net.SocketException -> L80
            if (r3 <= 0) goto L28
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.net.SocketException -> L80
            r1.<init>()     // Catch: java.net.SocketException -> L80
            int r3 = r2.length     // Catch: java.net.SocketException -> L80
            r4 = 0
            r5 = r4
        L54:
            r6 = 1
            if (r5 >= r3) goto L6d
            r7 = r2[r5]     // Catch: java.net.SocketException -> L80
            java.lang.String r8 = "%02X:"
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.net.SocketException -> L80
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)     // Catch: java.net.SocketException -> L80
            r6[r4] = r7     // Catch: java.net.SocketException -> L80
            java.lang.String r6 = java.lang.String.format(r8, r6)     // Catch: java.net.SocketException -> L80
            r1.append(r6)     // Catch: java.net.SocketException -> L80
            int r5 = r5 + 1
            goto L54
        L6d:
            int r2 = r1.length()     // Catch: java.net.SocketException -> L80
            if (r2 <= 0) goto L7b
            int r2 = r1.length()     // Catch: java.net.SocketException -> L80
            int r2 = r2 - r6
            r1.deleteCharAt(r2)     // Catch: java.net.SocketException -> L80
        L7b:
            java.lang.String r0 = r1.toString()     // Catch: java.net.SocketException -> L80
            goto L86
        L80:
            r1 = move-exception
            java.lang.String r2 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r2, r1)
        L86:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto La6
            java.lang.String r1 = "wifi"
            java.lang.Object r9 = r9.getSystemService(r1)     // Catch: java.lang.SecurityException -> La0
            android.net.wifi.WifiManager r9 = (android.net.wifi.WifiManager) r9     // Catch: java.lang.SecurityException -> La0
            android.net.wifi.WifiInfo r9 = r9.getConnectionInfo()     // Catch: java.lang.SecurityException -> La0
            if (r9 == 0) goto La6
            java.lang.String r0 = r9.getMacAddress()     // Catch: java.lang.SecurityException -> La0
            goto La6
        La0:
            r9 = move-exception
            java.lang.String r1 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r1, r9)
        La6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17104a(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
        com.bytedance.applog.C3704u2.m17108a("length:" + r4.length, (java.lang.Throwable) null);
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Class[] m17097a(java.lang.String r7) {
        /*
            r0 = 0
            java.lang.Class<android.telephony.TelephonyManager> r1 = android.telephony.TelephonyManager.class
            java.lang.reflect.Method[] r1 = r1.getDeclaredMethods()     // Catch: java.lang.Exception -> L3b
            int r2 = r1.length     // Catch: java.lang.Exception -> L3b
            r3 = 0
            r4 = r0
        La:
            if (r3 >= r2) goto L42
            r5 = r1[r3]     // Catch: java.lang.Exception -> L39
            java.lang.String r6 = r5.getName()     // Catch: java.lang.Exception -> L39
            boolean r6 = r7.equals(r6)     // Catch: java.lang.Exception -> L39
            if (r6 == 0) goto L36
            java.lang.Class[] r4 = r5.getParameterTypes()     // Catch: java.lang.Exception -> L39
            int r5 = r4.length     // Catch: java.lang.Exception -> L39
            r6 = 1
            if (r5 < r6) goto L36
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L39
            r7.<init>()     // Catch: java.lang.Exception -> L39
            java.lang.String r1 = "length:"
            r7.append(r1)     // Catch: java.lang.Exception -> L39
            int r1 = r4.length     // Catch: java.lang.Exception -> L39
            r7.append(r1)     // Catch: java.lang.Exception -> L39
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Exception -> L39
            com.bytedance.applog.C3704u2.m17108a(r7, r0)     // Catch: java.lang.Exception -> L39
            goto L42
        L36:
            int r3 = r3 + 1
            goto La
        L39:
            r7 = move-exception
            goto L3d
        L3b:
            r7 = move-exception
            r4 = r0
        L3d:
            java.lang.String r0 = ""
            com.bytedance.applog.C3704u2.m17108a(r0, r7)
        L42:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3710v0.m17097a(java.lang.String):java.lang.Class[]");
    }

    /* renamed from: a */
    public static JSONObject m17099a(C3565d1.C3567b c3567b) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DetectionAuthentic.FRAME, c3567b.f8415b.m17316a());
            jSONObject.put("element_path", c3567b.f8417d);
            List<String> list = c3567b.f8418e;
            if (list != null && list.size() > 0) {
                jSONObject.put("positions", new JSONArray((Collection) c3567b.f8418e));
            }
            List<String> list2 = c3567b.f8420g;
            if (list2 != null && list2.size() > 0) {
                jSONObject.put("texts", new JSONArray((Collection) c3567b.f8420g));
            }
            List<String> list3 = c3567b.f8424k;
            if (list3 != null && list3.size() > 0) {
                jSONObject.put("fuzzy_positions", c3567b.f8424k);
            }
            jSONObject.put("zIndex", c3567b.f8419f);
            JSONArray jSONArray = new JSONArray();
            for (C3565d1.C3567b c3567b2 : c3567b.f8421h) {
                jSONArray.put(m17099a(c3567b2));
            }
            jSONObject.put("children", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m17100a(WebView webView) {
        Object obj;
        try {
            Field declaredField = WebView.class.getDeclaredField("mProvider");
            declaredField.setAccessible(true);
            obj = declaredField.get(webView);
        } catch (Exception e) {
            C3704u2.m17108a("isDestroyed(): ", e);
        }
        if ("android.webkit.WebViewClassic".equals(obj)) {
            Field declaredField2 = obj.getClass().getDeclaredField("mWebViewCore");
            declaredField2.setAccessible(true);
            return declaredField2.get(obj) == null;
        }
        Field declaredField3 = obj.getClass().getDeclaredField("mAwContents");
        declaredField3.setAccessible(true);
        Object obj2 = declaredField3.get(obj);
        Method declaredMethod = obj2.getClass().getDeclaredMethod("isDestroyed", Integer.TYPE);
        declaredMethod.setAccessible(true);
        Object invoke = declaredMethod.invoke(obj2, 0);
        if (invoke instanceof Boolean) {
            return ((Boolean) invoke).booleanValue();
        }
        return false;
    }
}
