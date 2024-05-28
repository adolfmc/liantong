package com.bytedance.applog;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.bytedance.applog.network.RangersHttpException;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.bytedance.applog.j1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3607j1 {

    /* renamed from: b */
    public static JSONObject f8522b = null;

    /* renamed from: f */
    public static String f8526f = "https://databyterangers.com.cn";

    /* renamed from: a */
    public static final String[] f8521a = {"GET", "POST"};

    /* renamed from: c */
    public static final String[] f8523c = {"aid", "app_version", "tt_data", "device_id"};

    /* renamed from: d */
    public static final String[] f8524d = {"aid", "version_code", "ab_version", "iid", "device_platform"};

    /* renamed from: e */
    public static final String[] f8525e = {"tt_data", "device_platform"};

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.j1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3608a {

        /* renamed from: a */
        public String f8527a;

        /* renamed from: b */
        public byte[] f8528b;

        public C3608a(int i) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:93:0x020e A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x021b A[Catch: all -> 0x021e, TRY_ENTER, TryCatch #5 {all -> 0x021e, blocks: (B:91:0x0205, B:95:0x021b, B:96:0x021d), top: B:109:0x0205 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.bytedance.applog.C3607j1.C3608a m17267a(int r6, java.lang.String r7, java.util.HashMap<java.lang.String, java.lang.String> r8, byte[] r9, int r10) {
        /*
            Method dump skipped, instructions count: 556
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3607j1.m17267a(int, java.lang.String, java.util.HashMap, byte[], int):com.bytedance.applog.j1$a");
    }

    /* renamed from: a */
    public static String m17268a(int i, String str, HashMap<String, String> hashMap, byte[] bArr) {
        return m17267a(i, str, hashMap, bArr, 0).f8527a;
    }

    /* renamed from: a */
    public static String m17266a(String str) {
        String[] strArr;
        if (!TextUtils.isEmpty(str) && AppLog.getEncryptAndCompress()) {
            Uri parse = Uri.parse(str);
            String query = parse.getQuery();
            ArrayList<Pair> arrayList = new ArrayList();
            for (String str2 : f8523c) {
                String queryParameter = parse.getQueryParameter(str2);
                if (!TextUtils.isEmpty(queryParameter)) {
                    arrayList.add(new Pair(str2, queryParameter));
                }
            }
            Uri.Builder buildUpon = parse.buildUpon();
            buildUpon.clearQuery();
            for (Pair pair : arrayList) {
                buildUpon.appendQueryParameter((String) pair.first, (String) pair.second);
            }
            buildUpon.appendQueryParameter("tt_info", new String(Base64.encode(C3710v0.m17082d(query), 8)));
            return buildUpon.build().toString();
        }
        return str;
    }

    /* renamed from: a */
    public static String m17262a(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Uri parse = Uri.parse(str);
        HashMap hashMap = new HashMap(strArr.length);
        for (String str2 : strArr) {
            String queryParameter = parse.getQueryParameter(str2);
            if (!TextUtils.isEmpty(queryParameter)) {
                hashMap.put(str2, queryParameter);
            }
        }
        Uri.Builder buildUpon = parse.buildUpon();
        buildUpon.clearQuery();
        for (String str3 : hashMap.keySet()) {
            buildUpon.appendQueryParameter(str3, (String) hashMap.get(str3));
        }
        return buildUpon.build().toString();
    }

    /* renamed from: a */
    public static HashMap<String, String> m17270a() {
        String str;
        String str2;
        HashMap<String, String> hashMap = new HashMap<>(2);
        if (AppLog.getEncryptAndCompress()) {
            str = "Content-Type";
            str2 = "application/octet-stream;tt-data=a";
        } else {
            str = "Content-Type";
            str2 = "application/json; charset=utf-8";
        }
        hashMap.put(str, str2);
        return hashMap;
    }

    /* renamed from: a */
    public static JSONObject m17265a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("aid", str);
        jSONObject.put("os", "Android");
        jSONObject.put("os_version", String.valueOf(Build.VERSION.SDK_INT));
        jSONObject.put("sdk_version", "5.3.0");
        jSONObject.put("app_version", str2);
        return jSONObject;
    }

    /* renamed from: a */
    public static void m17261a(StringBuilder sb, String str, String str2) {
        if (sb == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        sb.append(sb.toString().indexOf(63) < 0 ? "?" : "&");
        sb.append(str);
        sb.append("=");
        sb.append(Uri.encode(str2));
    }

    /* renamed from: a */
    public static void m17260a(JSONObject jSONObject) {
        try {
            long optLong = jSONObject.optLong("server_time");
            if (optLong > 0) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("server_time", optLong);
                jSONObject2.put("local_time", System.currentTimeMillis() / 1000);
                f8522b = jSONObject2;
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static void m17259a(JSONObject jSONObject, String str) {
        String str2;
        JSONObject jSONObject2;
        JSONObject optJSONObject;
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("header", AppLog.getHeader());
            JSONArray jSONArray = new JSONArray();
            if (jSONObject != null) {
                jSONArray.put(jSONObject);
            }
            jSONObject3.put("event_v3", jSONArray);
        } catch (JSONException unused) {
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        hashMap.put("Cookie", str);
        boolean z = true;
        try {
            str2 = m17268a(1, f8526f + "/simulator/mobile/log", hashMap, C3710v0.m17082d(!(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3)));
        } catch (RangersHttpException unused2) {
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            z = false;
        } else {
            try {
                jSONObject2 = new JSONObject(str2);
            } catch (JSONException unused3) {
                jSONObject2 = null;
            }
            if (jSONObject2 != null && (optJSONObject = jSONObject2.optJSONObject("data")) != null) {
                z = optJSONObject.optBoolean("keep", true);
            }
        }
        if (z) {
            return;
        }
        AppLog.setRangersEventVerifyEnable(false, str);
    }

    /* renamed from: a */
    public static boolean m17269a(int i) {
        return i >= 500 && i < 600;
    }

    /* renamed from: a */
    public static boolean m17263a(String str, JSONObject jSONObject) {
        String str2;
        JSONObject jSONObject2;
        StringBuilder sb = new StringBuilder(str);
        float rawOffset = (TimeZone.getDefault().getRawOffset() * 1.0f) / 3600000.0f;
        if (rawOffset < -12.0f) {
            rawOffset = -12.0f;
        }
        if (rawOffset > 12.0f) {
            rawOffset = 12.0f;
        }
        m17261a(sb, "timezone", rawOffset + "");
        JSONObject jSONObject3 = null;
        String m17311a = C3572d3.m17311a((JSONObject) C3614k1.m17251a(jSONObject, "oaid", (Object) null));
        if (!TextUtils.isEmpty(m17311a)) {
            m17261a(sb, "oaid", m17311a);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("sim_serial_number");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            try {
                StringBuilder sb2 = new StringBuilder(((JSONObject) optJSONArray.get(0)).optString("sim_serial_number"));
                for (int i = 1; i < optJSONArray.length(); i++) {
                    String optString = ((JSONObject) optJSONArray.get(i)).optString("sim_serial_number");
                    sb2.append(",");
                    sb2.append(optString);
                }
                m17261a(sb, "sim_serial_number", sb2.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            str2 = AppLog.getNetClient().get(m17266a(sb.toString()), null);
        } catch (Exception e2) {
            e2.printStackTrace();
            str2 = null;
        }
        if (str2 != null) {
            try {
                jSONObject2 = new JSONObject(str2);
            } catch (JSONException e3) {
                e = e3;
            }
            try {
                m17260a(jSONObject2);
                jSONObject3 = jSONObject2;
            } catch (JSONException e4) {
                e = e4;
                jSONObject3 = jSONObject2;
                e.printStackTrace();
                return jSONObject3 == null ? false : false;
            }
        }
        if (jSONObject3 == null && "success".equals(jSONObject3.optString("message", ""))) {
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00be  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m17258a(java.lang.String[] r10, byte[] r11, com.bytedance.applog.C3726x r12) {
        /*
            java.util.HashMap r0 = m17270a()
            int r1 = r10.length
            r2 = 102(0x66, float:1.43E-43)
            r3 = 0
            r4 = 0
            r7 = r2
            r6 = r3
            r5 = r4
        Lc:
            if (r5 >= r1) goto L76
            r8 = r10[r5]
            com.bytedance.applog.network.INetworkClient r9 = com.bytedance.applog.AppLog.getNetClient()     // Catch: java.lang.Exception -> L50
            java.lang.String r8 = r9.post(r8, r11, r0)     // Catch: java.lang.Exception -> L50
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Exception -> L50
            if (r9 != 0) goto L73
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Exception -> L50
            r9.<init>(r8)     // Catch: java.lang.Exception -> L50
            m17260a(r9)     // Catch: java.lang.Exception -> L4e
            java.lang.String r6 = "ss_app_log"
            java.lang.String r8 = "magic_tag"
            java.lang.String r8 = r9.optString(r8)     // Catch: java.lang.Exception -> L4e
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L4e
            if (r6 == 0) goto L4b
            java.lang.String r6 = "success"
            java.lang.String r8 = "message"
            java.lang.String r8 = r9.optString(r8)     // Catch: java.lang.Exception -> L4e
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L4e
            if (r6 == 0) goto L47
            r7 = 200(0xc8, float:2.8E-43)
            goto L77
        L47:
            r6 = 101(0x65, float:1.42E-43)
            r7 = r6
            goto L4c
        L4b:
            r7 = r2
        L4c:
            r6 = r9
            goto L73
        L4e:
            r6 = move-exception
            goto L53
        L50:
            r8 = move-exception
            r9 = r6
            r6 = r8
        L53:
            boolean r8 = r6 instanceof com.bytedance.applog.network.RangersHttpException
            if (r8 == 0) goto L6d
            com.bytedance.applog.network.RangersHttpException r6 = (com.bytedance.applog.network.RangersHttpException) r6
            int r7 = r6.getResponseCode()
            com.bytedance.applog.InitConfig r6 = r12.f8897b
            boolean r6 = r6.isCongestionControlEnable()
            if (r6 == 0) goto L72
            boolean r6 = m17269a(r7)
            if (r6 == 0) goto L72
            goto L77
        L6d:
            java.lang.String r8 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r8, r6)
        L72:
            r6 = r9
        L73:
            int r5 = r5 + 1
            goto Lc
        L76:
            r9 = r6
        L77:
            if (r9 == 0) goto Ld1
            java.lang.String r10 = "blocklist"
            org.json.JSONObject r10 = r9.optJSONObject(r10)
            if (r10 == 0) goto Ld1
            java.lang.String r11 = "v1"
            org.json.JSONArray r11 = r10.optJSONArray(r11)
            if (r11 == 0) goto L8f
            int r0 = r11.length()
            goto L90
        L8f:
            r0 = r4
        L90:
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>(r0)
            r2 = r4
        L96:
            if (r2 >= r0) goto La8
            java.lang.String r5 = r11.optString(r2, r3)
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto La5
            r1.add(r5)
        La5:
            int r2 = r2 + 1
            goto L96
        La8:
            java.lang.String r11 = "v3"
            org.json.JSONArray r10 = r10.optJSONArray(r11)
            if (r10 == 0) goto Lb6
            int r11 = r10.length()
            goto Lb7
        Lb6:
            r11 = r4
        Lb7:
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>(r11)
        Lbc:
            if (r4 >= r11) goto Lce
            java.lang.String r2 = r10.optString(r4, r3)
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 != 0) goto Lcb
            r0.add(r2)
        Lcb:
            int r4 = r4 + 1
            goto Lbc
        Lce:
            r12.m17052a(r1, r0)
        Ld1:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3607j1.m17258a(java.lang.String[], byte[], com.bytedance.applog.x):int");
    }

    /* renamed from: a */
    public static JSONObject m17264a(String str, String str2, int i, int i2, String str3, String str4) {
        String str5;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject m17265a = m17265a(str, str2);
            m17265a.put("width", i);
            m17265a.put("height", i2);
            m17265a.put("device_id", str3);
            jSONObject.put("header", m17265a);
            jSONObject.put("qr_param", str4);
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/json; charset=utf-8");
            try {
                str5 = m17268a(1, f8526f + "/simulator/mobile/login", hashMap, C3710v0.m17082d(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)));
            } catch (RangersHttpException unused) {
                str5 = null;
            }
            if (TextUtils.isEmpty(str5)) {
                return null;
            }
            try {
                return new JSONObject(str5);
            } catch (JSONException e) {
                C3704u2.m17108a("U SHALL NOT PASS!", e);
                return null;
            }
        } catch (JSONException e2) {
            C3704u2.m17108a("U SHALL NOT PASS!", e2);
            return null;
        }
    }
}
