package com.baidu.lbsapi.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.p116a.p117a.p118a.p119a.C2130a;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LBSAuthManager {
    public static final int CODE_AUTHENTICATE_SUCC = 0;
    public static final int CODE_AUTHENTICATING = 602;
    public static final int CODE_INNER_ERROR = -1;
    public static final int CODE_KEY_NOT_EXIST = 101;
    public static final int CODE_NETWORK_FAILED = -11;
    public static final int CODE_NETWORK_INVALID = -10;
    public static final int CODE_UNAUTHENTICATE = 601;
    public static final String VERSION = "1.0.25";

    /* renamed from: a */
    private static Context f4953a;

    /* renamed from: d */
    private static C2598m f4954d;

    /* renamed from: e */
    private static int f4955e;

    /* renamed from: g */
    private static LBSAuthManager f4957g;

    /* renamed from: b */
    private C2586c f4963b = null;

    /* renamed from: c */
    private C2589e f4964c = null;

    /* renamed from: h */
    private boolean f4965h = false;

    /* renamed from: n */
    private final Handler f4966n = new HandlerC2594i(this, Looper.getMainLooper());

    /* renamed from: f */
    private static Hashtable<String, LBSAuthManagerListener> f4956f = new Hashtable<>();

    /* renamed from: i */
    private static String f4958i = "";

    /* renamed from: j */
    private static String f4959j = "";

    /* renamed from: k */
    private static String f4960k = "";

    /* renamed from: l */
    private static String[] f4961l = null;

    /* renamed from: m */
    private static boolean f4962m = false;

    private LBSAuthManager(Context context) {
        f4953a = context;
        C2598m c2598m = f4954d;
        if (c2598m != null && !c2598m.isAlive()) {
            f4954d = null;
        }
        C2583a.m19675b("BaiduApiAuth SDK Version:1.0.25");
        m19680e();
    }

    /* renamed from: a */
    private int m19689a(String str) {
        int i = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
            i = jSONObject.getInt("status");
            if (jSONObject.has("current") && i == 0) {
                long j = jSONObject.getLong("current");
                long currentTimeMillis = System.currentTimeMillis();
                if ((currentTimeMillis - j) / 3600000.0d < 24.0d) {
                    if (this.f4965h) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        if (!simpleDateFormat.format(Long.valueOf(currentTimeMillis)).equals(simpleDateFormat.format(Long.valueOf(j)))) {
                        }
                    }
                }
                i = 601;
            }
            if (jSONObject.has("current") && i == 602) {
                if ((System.currentTimeMillis() - jSONObject.getLong("current")) / 1000 > 180.0d) {
                    return 601;
                }
                return i;
            }
            return i;
        } catch (JSONException e) {
            e.printStackTrace();
            return i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0072, code lost:
        if (r6 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0082, code lost:
        if (r6 == null) goto L13;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007f  */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.InputStreamReader] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m19696a(int r6) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            r2.<init>()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.lang.String r3 = "/proc/"
            r2.append(r3)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            r2.append(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.lang.String r6 = "/cmdline"
            r2.append(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e java.io.FileNotFoundException -> L50
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e java.io.FileNotFoundException -> L50
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L47
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L47
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L68 java.io.FileNotFoundException -> L78
            r2.close()
            r1.close()
        L35:
            r6.close()
            goto L85
        L3a:
            r0 = move-exception
            r4 = r2
            r2 = r6
            r6 = r0
            r0 = r4
            goto L55
        L40:
            r2 = move-exception
            r4 = r2
            r2 = r6
            r6 = r4
            goto L55
        L45:
            r2 = r0
            goto L68
        L47:
            r2 = r0
            goto L78
        L49:
            r1 = move-exception
            r2 = r6
            r6 = r1
            r1 = r0
            goto L55
        L4e:
            r1 = r0
            goto L67
        L50:
            r1 = r0
            goto L77
        L52:
            r6 = move-exception
            r1 = r0
            r2 = r1
        L55:
            if (r0 == 0) goto L5a
            r0.close()
        L5a:
            if (r1 == 0) goto L5f
            r1.close()
        L5f:
            if (r2 == 0) goto L64
            r2.close()
        L64:
            throw r6
        L65:
            r6 = r0
            r1 = r6
        L67:
            r2 = r1
        L68:
            if (r2 == 0) goto L6d
            r2.close()
        L6d:
            if (r1 == 0) goto L72
            r1.close()
        L72:
            if (r6 == 0) goto L85
            goto L35
        L75:
            r6 = r0
            r1 = r6
        L77:
            r2 = r1
        L78:
            if (r2 == 0) goto L7d
            r2.close()
        L7d:
            if (r1 == 0) goto L82
            r1.close()
        L82:
            if (r6 == 0) goto L85
            goto L35
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.m19696a(int):java.lang.String");
    }

    /* renamed from: a */
    private String m19695a(Context context) {
        String str;
        try {
            str = m19696a(Process.myPid());
        } catch (IOException unused) {
            str = null;
        }
        return str != null ? str : f4953a.getPackageName();
    }

    /* renamed from: a */
    private String m19694a(Context context, String str) {
        ApplicationInfo applicationInfo;
        LBSAuthManagerListener lBSAuthManagerListener;
        String str2;
        if (TextUtils.isEmpty(f4958i)) {
            String str3 = "";
            try {
                applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            } catch (PackageManager.NameNotFoundException unused) {
                LBSAuthManagerListener lBSAuthManagerListener2 = f4956f.get(str);
                if (lBSAuthManagerListener2 != null) {
                    lBSAuthManagerListener2.onAuthResult(101, ErrorMessage.m19699a(101, "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
                }
            }
            if (applicationInfo.metaData == null) {
                lBSAuthManagerListener = f4956f.get(str);
                str2 = lBSAuthManagerListener != null ? "AndroidManifest.xml的application中没有meta-data标签" : "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值";
                return str3;
            }
            str3 = applicationInfo.metaData.getString("com.baidu.lbsapi.API_KEY");
            if ((str3 == null || str3.equals("")) && (lBSAuthManagerListener = f4956f.get(str)) != null) {
            }
            return str3;
            lBSAuthManagerListener.onAuthResult(101, ErrorMessage.m19699a(101, str2));
            return str3;
        }
        return f4958i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void m19688a(String str, String str2) {
        if (str == null) {
            str = m19678g();
        }
        Message obtainMessage = this.f4966n.obtainMessage();
        int i = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
            if (!jSONObject.has("current")) {
                jSONObject.put("current", System.currentTimeMillis());
            }
            m19682c(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            if (jSONObject.has("current")) {
                jSONObject.remove("current");
            }
            i = jSONObject.getInt("status");
            obtainMessage.what = i;
            obtainMessage.obj = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            Bundle bundle = new Bundle();
            bundle.putString("listenerKey", str2);
            obtainMessage.setData(bundle);
            this.f4966n.sendMessage(obtainMessage);
        } catch (JSONException e) {
            e.printStackTrace();
            obtainMessage.what = i;
            obtainMessage.obj = new JSONObject();
            Bundle bundle2 = new Bundle();
            bundle2.putString("listenerKey", str2);
            obtainMessage.setData(bundle2);
            this.f4966n.sendMessage(obtainMessage);
        }
        if (f4954d != null) {
            f4954d.m19640c();
        }
        f4955e--;
        C2583a.m19676a("httpRequest called mAuthCounter-- = " + f4955e);
        if (f4955e == 0 && f4954d != null) {
            f4954d.m19642a();
            f4954d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19687a(boolean z, String str, Hashtable<String, String> hashtable, String str2) {
        String str3;
        String m19672a;
        String str4;
        StringBuilder sb;
        int i;
        String m19694a = m19694a(f4953a, str2);
        if (m19694a == null || m19694a.equals("")) {
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("url", "https://api.map.baidu.com/sdkcs/verify");
        C2583a.m19676a("url:https://api.map.baidu.com/sdkcs/verify");
        hashMap.put("output", "json");
        hashMap.put("ak", m19694a);
        C2583a.m19676a("ak:" + m19694a);
        if (TextUtils.isEmpty(f4960k)) {
            str3 = "mcode";
            m19672a = C2584b.m19672a(f4953a);
        } else {
            str3 = "mcode";
            m19672a = f4960k;
        }
        hashMap.put(str3, m19672a);
        hashMap.put("from", "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry<String, String> entry : hashtable.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    hashMap.put(key, value);
                }
            }
        }
        String cuid = getCUID();
        C2583a.m19676a("cuid:" + cuid);
        if (TextUtils.isEmpty(cuid)) {
            hashMap.put("cuid", "");
        } else {
            hashMap.put("cuid", cuid);
        }
        hashMap.put("pcn", f4953a.getPackageName());
        hashMap.put("version", "1.0.25");
        hashMap.put("macaddr", "");
        String str5 = "";
        try {
            str5 = C2584b.m19673a();
        } catch (Exception unused) {
        }
        if (TextUtils.isEmpty(str5)) {
            hashMap.put("language", "");
        } else {
            hashMap.put("language", str5);
        }
        if (z) {
            if (z) {
                sb = new StringBuilder();
                i = 1;
            } else {
                sb = new StringBuilder();
                i = 0;
            }
            sb.append(i);
            sb.append("");
            hashMap.put("force", sb.toString());
        }
        if (str == null) {
            str4 = "from_service";
            str = "";
        } else {
            str4 = "from_service";
        }
        hashMap.put(str4, str);
        String m19679f = m19679f();
        if (!TextUtils.isEmpty(m19679f)) {
            hashMap.put("extend", m19679f);
        }
        this.f4963b = new C2586c(f4953a);
        this.f4963b.m19661a(hashMap, new C2596k(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19686a(boolean z, String str, Hashtable<String, String> hashtable, String[] strArr, String str2) {
        String str3;
        StringBuilder sb;
        int i;
        String m19694a = m19694a(f4953a, str2);
        if (m19694a == null || m19694a.equals("")) {
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("url", "https://api.map.baidu.com/sdkcs/verify");
        hashMap.put("output", "json");
        hashMap.put("ak", m19694a);
        hashMap.put("from", "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry<String, String> entry : hashtable.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    hashMap.put(key, value);
                }
            }
        }
        String cuid = getCUID();
        C2583a.m19676a("sendAuthRequests : cuid: " + cuid);
        if (TextUtils.isEmpty(cuid)) {
            hashMap.put("cuid", "");
        } else {
            hashMap.put("cuid", cuid);
        }
        hashMap.put("pcn", f4953a.getPackageName());
        hashMap.put("version", "1.0.25");
        hashMap.put("macaddr", "");
        String str4 = "";
        try {
            str4 = C2584b.m19673a();
        } catch (Exception unused) {
        }
        if (TextUtils.isEmpty(str4)) {
            hashMap.put("language", "");
        } else {
            hashMap.put("language", str4);
        }
        if (z) {
            if (z) {
                sb = new StringBuilder();
                i = 1;
            } else {
                sb = new StringBuilder();
                i = 0;
            }
            sb.append(i);
            sb.append("");
            hashMap.put("force", sb.toString());
        }
        if (str == null) {
            str3 = "from_service";
            str = "";
        } else {
            str3 = "from_service";
        }
        hashMap.put(str3, str);
        String m19679f = m19679f();
        if (!TextUtils.isEmpty(m19679f)) {
            hashMap.put("extend", m19679f);
        }
        this.f4964c = new C2589e(f4953a);
        this.f4964c.m19655a(hashMap, strArr, new C2597l(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public boolean m19684b(String str) {
        JSONObject jSONObject;
        String m19694a = m19694a(f4953a, str);
        String str2 = "";
        try {
            jSONObject = new JSONObject(m19678g());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jSONObject.has("ak")) {
            str2 = jSONObject.getString("ak");
            return (m19694a == null || str2 == null || m19694a.equals(str2)) ? false : true;
        }
        return true;
    }

    /* renamed from: c */
    private void m19682c(String str) {
        Context context = f4953a;
        context.getSharedPreferences("authStatus_" + m19695a(f4953a), 0).edit().putString("status", str).commit();
    }

    /* renamed from: e */
    private void m19680e() {
        synchronized (LBSAuthManager.class) {
            if (f4954d == null) {
                f4954d = new C2598m("auth");
                f4954d.start();
                while (f4954d.f4993a == null) {
                    try {
                        C2583a.m19676a("wait for create auth thread.");
                        Thread.sleep(3L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: f */
    private String m19679f() {
        try {
            JSONObject jSONObject = new JSONObject(m19678g());
            return !jSONObject.has("extend") ? "" : jSONObject.getString("extend");
        } catch (JSONException unused) {
            return "";
        }
    }

    /* renamed from: g */
    private String m19678g() {
        Context context = f4953a;
        return context.getSharedPreferences("authStatus_" + m19695a(f4953a), 0).getString("status", "{\"status\":601}");
    }

    public static LBSAuthManager getInstance(Context context) {
        if (f4957g == null) {
            synchronized (LBSAuthManager.class) {
                if (f4957g == null) {
                    f4957g = new LBSAuthManager(context);
                }
            }
        } else if (context != null) {
            f4953a = context;
        } else if (C2583a.f4967a) {
            C2583a.m19674c("input context is null");
            new RuntimeException("here").printStackTrace();
        }
        return f4957g;
    }

    public int authenticate(boolean z, String str, Hashtable<String, String> hashtable, LBSAuthManagerListener lBSAuthManagerListener) {
        synchronized (LBSAuthManager.class) {
            boolean z2 = false;
            if (hashtable != null) {
                String str2 = hashtable.get("zero_auth");
                if (str2 != null && Integer.valueOf(str2).intValue() == 1) {
                    z2 = true;
                }
            }
            this.f4965h = z2;
            String str3 = System.currentTimeMillis() + "";
            if (lBSAuthManagerListener != null) {
                f4956f.put(str3, lBSAuthManagerListener);
            }
            String m19694a = m19694a(f4953a, str3);
            if (m19694a != null && !m19694a.equals("")) {
                f4955e++;
                C2583a.m19676a(" mAuthCounter  ++ = " + f4955e);
                String m19678g = m19678g();
                C2583a.m19676a("getAuthMessage from cache:" + m19678g);
                int m19689a = m19689a(m19678g);
                if (m19689a == 601) {
                    try {
                        JSONObject put = new JSONObject().put("status", 602);
                        m19682c(!(put instanceof JSONObject) ? put.toString() : NBSJSONObjectInstrumentation.toString(put));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                m19680e();
                if (f4954d != null && f4954d.f4993a != null) {
                    C2583a.m19676a("mThreadLooper.mHandler = " + f4954d.f4993a);
                    f4954d.f4993a.post(new RunnableC2595j(this, m19689a, z, str3, str, hashtable));
                    return m19689a;
                }
                return -1;
            }
            return 101;
        }
    }

    public String getCUID() {
        if (f4953a == null) {
            return "";
        }
        String str = "";
        try {
            C2583a.m19676a("mIsPrivacyMode " + f4962m);
            if (f4962m) {
                str = C2130a.m20444a(f4953a);
                C2583a.m19676a("getCUID: " + str);
            } else {
                Context context = f4953a;
                Context context2 = f4953a;
                SharedPreferences sharedPreferences = context.getSharedPreferences("Map_Privacy", 0);
                if (sharedPreferences.contains("cuid")) {
                    str = sharedPreferences.getString("cuid", "");
                } else {
                    str = C2599n.m19638a(UUID.randomUUID().toString().getBytes(), true) + "|MAPSDK001";
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("cuid", str);
                    edit.apply();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getKey() {
        Context context = f4953a;
        if (context == null) {
            return "";
        }
        try {
            return getPublicKey(context);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getMCode() {
        return f4953a == null ? "" : !TextUtils.isEmpty(f4960k) ? f4960k : C2584b.m19672a(f4953a);
    }

    public boolean getPrivacyMode() {
        return f4962m;
    }

    public String getPublicKey(Context context) throws PackageManager.NameNotFoundException {
        if (TextUtils.isEmpty(f4958i)) {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.baidu.lbsapi.API_KEY");
        }
        return f4958i;
    }

    public void setKey(String str) {
        if (f4953a == null || TextUtils.isEmpty(str)) {
            return;
        }
        f4958i = str;
    }

    public void setMCode(String str) {
        f4960k = str;
    }

    public void setMCodes(String[] strArr) {
        f4961l = strArr;
    }

    public void setPackageName(String str) {
        f4959j = str;
    }

    public void setPrivacyMode(boolean z) {
        Context context = f4953a;
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("Map_Privacy", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (z) {
            edit.putBoolean("privacyMode", z);
            edit.apply();
        } else {
            z = sharedPreferences.getBoolean("privacyMode", false);
        }
        f4962m = z;
    }
}
