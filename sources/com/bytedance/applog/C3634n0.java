package com.bytedance.applog;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.bytedance.applog.network.RangersHttpException;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.n0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3634n0 extends C3607j1 {

    /* renamed from: l */
    public static final String[] f8601l = {"Set-Cookie", "set-cookie", "SET-COOKIE"};

    /* renamed from: g */
    public final String f8602g;

    /* renamed from: h */
    public HttpURLConnection f8603h;

    /* renamed from: i */
    public String f8604i = Charset.defaultCharset().displayName();

    /* renamed from: j */
    public OutputStream f8605j;

    /* renamed from: k */
    public PrintWriter f8606k;

    public C3634n0(String str, String str2) {
        StringBuilder m17349a = C3535a.m17349a("===");
        m17349a.append(System.currentTimeMillis());
        m17349a.append("===");
        this.f8602g = m17349a.toString();
        this.f8603h = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(str).openConnection());
        this.f8603h.setUseCaches(false);
        this.f8603h.setDoOutput(true);
        this.f8603h.setDoInput(true);
        HttpURLConnection httpURLConnection = this.f8603h;
        StringBuilder m17349a2 = C3535a.m17349a("multipart/form-data; boundary=");
        m17349a2.append(this.f8602g);
        httpURLConnection.setRequestProperty("Content-Type", m17349a2.toString());
        if (!TextUtils.isEmpty(str2)) {
            this.f8603h.setRequestProperty("Cookie", str2);
        }
        this.f8605j = this.f8603h.getOutputStream();
        this.f8606k = new PrintWriter((Writer) new OutputStreamWriter(this.f8605j, this.f8604i), true);
    }

    /* renamed from: a */
    public static JSONObject m17218a(String str, String str2, String str3, C3654o1 c3654o1, String str4, String str5, String str6, String str7) {
        String str8;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("header", C3607j1.m17265a(str, str2));
            JSONArray jSONArray = new JSONArray();
            if (!TextUtils.isEmpty(str4)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("pageKey", c3654o1.f8667n);
                jSONObject2.put("picUrl", str6);
                jSONObject2.put("desc", str4);
                jSONObject2.put("isHtml", c3654o1.f8675v);
                jSONArray.put(jSONObject2);
            }
            if (!TextUtils.isEmpty(str5)) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("pageKey", c3654o1.f8667n);
                if (c3654o1.f8675v) {
                    c3654o1.f8668o += "/*";
                }
                jSONObject3.put("elementPath", c3654o1.f8668o);
                jSONObject3.put("picUrl", str7);
                jSONObject3.put("desc", str5);
                jSONObject3.put("isHtml", c3654o1.f8675v);
                ArrayList<String> arrayList = c3654o1.f8670q;
                if (arrayList != null && arrayList.size() > 0) {
                    jSONObject3.put("positions", new JSONArray((Collection) c3654o1.f8670q));
                }
                if (c3654o1.f8669p.size() > 0) {
                    jSONObject3.put("texts", new JSONArray((Collection) c3654o1.f8669p));
                }
                jSONArray.put(jSONObject3);
            }
            jSONObject.put("binds", jSONArray);
            StringBuilder sb = new StringBuilder();
            sb.append("bind: ");
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            C3704u2.m17108a(sb.toString(), (Throwable) null);
            HashMap hashMap = new HashMap();
            hashMap.put("Cookie", str3);
            try {
                str8 = AppLog.getNetClient().post(C3607j1.f8526f + "/simulator/event/bind", (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).getBytes(), hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                str8 = null;
            }
            if (str8 != null) {
                try {
                    return new JSONObject(str8);
                } catch (JSONException e2) {
                    C3704u2.m17108a("U SHALL NOT PASS!", e2);
                }
            }
            return null;
        } catch (JSONException e3) {
            C3704u2.m17108a("U SHALL NOT PASS!", e3);
            return null;
        }
    }

    /* renamed from: b */
    public static String m17213b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ 5);
            }
            return C3609j2.m17255a(bytes, 0, bytes.length);
        } catch (Exception unused) {
            return str;
        }
    }

    /* renamed from: b */
    public final String m17214b() {
        StringBuilder sb = new StringBuilder(128);
        this.f8606k.append((CharSequence) "\r\n").flush();
        this.f8606k.append((CharSequence) "--").append((CharSequence) this.f8602g).append((CharSequence) "--").append((CharSequence) "\r\n");
        this.f8606k.close();
        int responseCode = this.f8603h.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Server returned non-OK status: " + responseCode);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f8603h.getInputStream()));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                bufferedReader.close();
                this.f8603h.disconnect();
                return sb.toString();
            }
            sb.append(readLine);
        }
    }

    /* renamed from: b */
    public final void m17212b(String str, String str2) {
        this.f8606k.append((CharSequence) "--").append((CharSequence) this.f8602g).append((CharSequence) "\r\n");
        this.f8606k.append((CharSequence) "Content-Disposition: form-data; name=\"").append((CharSequence) str).append((CharSequence) "\"").append((CharSequence) "\r\n");
        this.f8606k.append((CharSequence) "Content-Type: text/plain; charset=").append((CharSequence) this.f8604i).append((CharSequence) "\r\n");
        this.f8606k.append((CharSequence) "\r\n");
        this.f8606k.append((CharSequence) str2).append((CharSequence) "\r\n");
        this.f8606k.flush();
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x009e, code lost:
        r7.put("_I_MY_TOKEN_adjf_", r4.substring(0, r4.indexOf(";")));
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0081 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONObject m17215a(java.lang.String r6, java.lang.String r7, org.json.JSONObject r8) {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L73
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L73
            r2.<init>()     // Catch: java.lang.Throwable -> L73
            java.lang.String r3 = com.bytedance.applog.C3607j1.f8526f     // Catch: java.lang.Throwable -> L73
            r2.append(r3)     // Catch: java.lang.Throwable -> L73
            java.lang.String r3 = "https://data.bytedance.com/passport/user/login/"
            r2.append(r3)     // Catch: java.lang.Throwable -> L73
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L73
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L73
            java.lang.String r2 = "mix_mode"
            java.lang.String r3 = "1"
            com.bytedance.applog.C3607j1.m17261a(r1, r2, r3)     // Catch: java.lang.Throwable -> L73
            java.lang.String r2 = "aid"
            java.lang.String r3 = "1675"
            com.bytedance.applog.C3607j1.m17261a(r1, r2, r3)     // Catch: java.lang.Throwable -> L73
            java.util.Iterator r2 = r8.keys()     // Catch: java.lang.Throwable -> L73
        L2b:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L73
            if (r3 == 0) goto L47
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L73
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L73
            java.lang.String r4 = "aid"
            boolean r4 = r4.equals(r3)     // Catch: java.lang.Throwable -> L73
            if (r4 != 0) goto L2b
            java.lang.String r4 = r8.optString(r3)     // Catch: java.lang.Throwable -> L73
            com.bytedance.applog.C3607j1.m17261a(r1, r3, r4)     // Catch: java.lang.Throwable -> L73
            goto L2b
        L47:
            java.lang.String r6 = m17213b(r6)     // Catch: java.lang.Throwable -> L73
            java.lang.String r7 = m17213b(r7)     // Catch: java.lang.Throwable -> L73
            java.lang.String r8 = "account"
            com.bytedance.applog.C3607j1.m17261a(r1, r8, r6)     // Catch: java.lang.Throwable -> L73
            java.lang.String r8 = "password"
            com.bytedance.applog.C3607j1.m17261a(r1, r8, r7)     // Catch: java.lang.Throwable -> L73
            com.bytedance.applog.n0 r8 = new com.bytedance.applog.n0     // Catch: java.lang.Throwable -> L73
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L73
            r8.<init>(r1, r0)     // Catch: java.lang.Throwable -> L73
            java.lang.String r1 = "account"
            r8.m17212b(r1, r6)     // Catch: java.lang.Throwable -> L71
            java.lang.String r6 = "password"
            r8.m17212b(r6, r7)     // Catch: java.lang.Throwable -> L71
            java.lang.String r6 = r8.m17214b()     // Catch: java.lang.Throwable -> L71
            goto L7b
        L71:
            r6 = move-exception
            goto L75
        L73:
            r6 = move-exception
            r8 = r0
        L75:
            java.lang.String r7 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r7, r6)
            r6 = r0
        L7b:
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L82
            return r0
        L82:
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lb0
            r7.<init>(r6)     // Catch: org.json.JSONException -> Lb0
            java.lang.String[] r6 = com.bytedance.applog.C3634n0.f8601l     // Catch: org.json.JSONException -> Lb0
            int r1 = r6.length     // Catch: org.json.JSONException -> Lb0
            r2 = 0
            r3 = r2
        L8c:
            if (r3 >= r1) goto Laf
            r4 = r6[r3]     // Catch: org.json.JSONException -> Lb0
            java.net.HttpURLConnection r5 = r8.f8603h     // Catch: org.json.JSONException -> Lb0
            java.lang.String r4 = r5.getHeaderField(r4)     // Catch: org.json.JSONException -> Lb0
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: org.json.JSONException -> Lb0
            if (r5 != 0) goto Lac
            java.lang.String r6 = "_I_MY_TOKEN_adjf_"
            java.lang.String r8 = ";"
            int r8 = r4.indexOf(r8)     // Catch: org.json.JSONException -> Lb0
            java.lang.String r8 = r4.substring(r2, r8)     // Catch: org.json.JSONException -> Lb0
            r7.put(r6, r8)     // Catch: org.json.JSONException -> Lb0
            goto Laf
        Lac:
            int r3 = r3 + 1
            goto L8c
        Laf:
            return r7
        Lb0:
            r6 = move-exception
            java.lang.String r7 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r7, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3634n0.m17215a(java.lang.String, java.lang.String, org.json.JSONObject):org.json.JSONObject");
    }

    /* renamed from: a */
    public static JSONObject m17219a(String str, String str2, String str3, C3654o1 c3654o1) {
        String str4;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("header", C3607j1.m17265a(str, str2));
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("pageKey", c3654o1.f8667n);
            jSONObject2.put("isHtml", c3654o1.f8675v);
            jSONArray.put(jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("pageKey", c3654o1.f8667n);
            if (c3654o1.f8675v) {
                c3654o1.f8668o += "/*";
            }
            jSONObject3.put("elementPath", c3654o1.f8668o);
            jSONObject3.put("isHtml", c3654o1.f8675v);
            ArrayList<String> arrayList = c3654o1.f8670q;
            if (arrayList != null && arrayList.size() > 0) {
                jSONObject3.put("positions", new JSONArray((Collection) c3654o1.f8670q));
            }
            if (c3654o1.f8669p.size() > 0) {
                jSONObject3.put("texts", new JSONArray((Collection) c3654o1.f8669p));
            }
            jSONArray.put(jSONObject3);
            jSONObject.put("get", jSONArray);
            StringBuilder sb = new StringBuilder();
            sb.append("query: ");
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            C3704u2.m17108a(sb.toString(), (Throwable) null);
            HashMap hashMap = new HashMap();
            hashMap.put("Cookie", str3);
            try {
                str4 = AppLog.getNetClient().post(C3607j1.f8526f + "/simulator/event/query", (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).getBytes(), hashMap);
            } catch (Exception e) {
                e.printStackTrace();
                str4 = null;
            }
            if (TextUtils.isEmpty(str4)) {
                return null;
            }
            try {
                return new JSONObject(str4);
            } catch (JSONException e2) {
                C3704u2.m17108a("U SHALL NOT PASS!", e2);
                return null;
            }
        } catch (JSONException e3) {
            C3704u2.m17108a("U SHALL NOT PASS!", e3);
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m17216a(String str, String str2, String str3, boolean z, HashMap<String, List<C3736y0>> hashMap, int[] iArr) {
        String str4;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("header", C3607j1.m17265a(str, str2));
            jSONObject.put("withGrid", z);
            JSONArray jSONArray = new JSONArray();
            for (Object obj : hashMap.keySet()) {
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                List<C3736y0> list = hashMap.get(obj);
                jSONObject2.put("pageKey", obj);
                if (list != null && list.size() > 0) {
                    jSONObject2.put("isHtml", list.get(0).f8675v);
                    if (list.get(0).f8675v) {
                        jSONObject2.put("tag", obj);
                    }
                    for (C3736y0 c3736y0 : list) {
                        JSONObject jSONObject3 = new JSONObject();
                        if (c3736y0.f8675v) {
                            c3736y0.f8668o += "/*";
                        }
                        jSONObject3.put("elementPath", c3736y0.f8668o);
                        ArrayList<String> arrayList = c3736y0.f8670q;
                        if (arrayList != null && arrayList.size() > 0) {
                            jSONObject3.put("positions", new JSONArray((Collection) c3736y0.f8670q));
                        }
                        jSONArray2.put(jSONObject3);
                    }
                    jSONObject2.put("items", jSONArray2);
                }
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("heat", jSONArray);
            if (iArr != null) {
                JSONArray jSONArray3 = new JSONArray();
                jSONArray3.put(iArr[0]);
                jSONArray3.put(iArr[1]);
                jSONObject.put("range_ts", jSONArray3);
            }
            if (C3704u2.f8845b) {
                StringBuilder sb = new StringBuilder();
                sb.append("query: ");
                sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                C3704u2.m17108a(sb.toString(), (Throwable) null);
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("Cookie", str3);
            try {
                str4 = C3607j1.m17268a(1, C3607j1.f8526f + "/simulator/event/query_heat", hashMap2, (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).getBytes());
            } catch (RangersHttpException unused) {
                str4 = null;
            }
            if (TextUtils.isEmpty(str4)) {
                return null;
            }
            try {
                return new JSONObject(str4);
            } catch (JSONException e) {
                C3704u2.m17108a("U SHALL NOT PASS!", e);
                return null;
            }
        } catch (JSONException e2) {
            C3704u2.m17108a("U SHALL NOT PASS!", e2);
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m17217a(String str, String str2, String str3, String str4, Bitmap bitmap, String str5) {
        String str6;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("header", C3607j1.m17265a(str, str2));
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("pageKey", str3);
            jSONObject2.put("elementPath", str4);
            jSONObject2.put("img", C3710v0.m17101a(bitmap));
            jSONArray.put(jSONObject2);
            jSONObject.put("pic", jSONArray);
        } catch (JSONException unused) {
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        hashMap.put("Cookie", str5);
        try {
            str6 = C3607j1.m17268a(1, C3607j1.f8526f + "/simulator/event/pic_upload", hashMap, (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).getBytes());
        } catch (RangersHttpException unused2) {
            str6 = null;
        }
        if (TextUtils.isEmpty(str6)) {
            return null;
        }
        try {
            return new JSONObject(str6);
        } catch (JSONException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m17220a(String str, String str2, int i, int i2, String str3, String str4, JSONArray jSONArray) {
        String str5;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject m17265a = C3607j1.m17265a(str, str2);
            m17265a.put("width", i);
            m17265a.put("height", i2);
            jSONObject.put("header", m17265a);
            jSONObject.put("img", str4);
            jSONObject.put("pages", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        hashMap.put("Cookie", str3);
        try {
            str5 = C3607j1.m17268a(1, C3607j1.f8526f + "/simulator/mobile/layout", hashMap, C3710v0.m17082d(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)));
        } catch (RangersHttpException unused) {
            str5 = null;
        }
        if (TextUtils.isEmpty(str5)) {
            return null;
        }
        try {
            return new JSONObject(str5);
        } catch (JSONException e2) {
            C3704u2.m17108a("U SHALL NOT PASS!", e2);
            return null;
        }
    }
}
