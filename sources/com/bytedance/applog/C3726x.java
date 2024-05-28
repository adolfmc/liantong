package com.bytedance.applog;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.x */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3726x {

    /* renamed from: a */
    public final Context f8896a;

    /* renamed from: b */
    public final InitConfig f8897b;

    /* renamed from: c */
    public final SharedPreferences f8898c;

    /* renamed from: d */
    public final SharedPreferences f8899d;

    /* renamed from: e */
    public final SharedPreferences f8900e;

    /* renamed from: f */
    public volatile JSONObject f8901f;

    /* renamed from: g */
    public volatile String f8902g;

    /* renamed from: h */
    public volatile JSONObject f8903h;

    /* renamed from: k */
    public volatile HashSet<String> f8906k;

    /* renamed from: l */
    public int f8907l = 1;

    /* renamed from: i */
    public final HashSet<String> f8904i = new HashSet<>();

    /* renamed from: j */
    public final HashSet<String> f8905j = new HashSet<>();

    public C3726x(Context context, InitConfig initConfig) {
        this.f8896a = context;
        this.f8897b = initConfig;
        this.f8900e = this.f8896a.getSharedPreferences(this.f8897b.getSpName(), 0);
        this.f8898c = this.f8896a.getSharedPreferences("header_custom", 0);
        this.f8899d = this.f8896a.getSharedPreferences("last_sp_session", 0);
    }

    /* renamed from: a */
    public JSONObject m17055a() {
        JSONObject jSONObject = this.f8901f;
        if (jSONObject == null) {
            synchronized (this) {
                try {
                    jSONObject = new JSONObject(this.f8898c.getString("ab_configure", ""));
                } catch (JSONException unused) {
                }
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                this.f8901f = jSONObject;
            }
        }
        return jSONObject;
    }

    /* renamed from: a */
    public void m17054a(String str) {
        C3704u2.m17108a("setExternalAbVersion, " + str, (Throwable) null);
        C3535a.m17350a(this.f8898c, "external_ab_version", str);
        this.f8902g = null;
    }

    /* renamed from: a */
    public void m17052a(HashSet<String> hashSet, HashSet<String> hashSet2) {
        if (hashSet != null) {
            this.f8904i.addAll(hashSet);
        }
        if (hashSet2 != null) {
            this.f8905j.addAll(hashSet2);
        }
    }

    /* renamed from: a */
    public void m17051a(JSONObject jSONObject) {
        String str;
        String jSONObject2 = jSONObject == null ? "" : !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        if (C3704u2.f8845b) {
            str = "setAbConfig, " + jSONObject2;
        } else {
            str = "setAbConfig";
        }
        C3704u2.m17108a(str, (Throwable) null);
        C3535a.m17350a(this.f8898c, "ab_configure", jSONObject2);
        this.f8901f = null;
    }

    /* renamed from: a */
    public boolean m17053a(ArrayList<AbstractC3628m1> arrayList) {
        if (arrayList == null || arrayList.size() == 0 || (this.f8904i.size() == 0 && this.f8905j.size() == 0)) {
            return true;
        }
        Iterator<AbstractC3628m1> it = arrayList.iterator();
        while (it.hasNext()) {
            AbstractC3628m1 next = it.next();
            if (next instanceof C3673q1) {
                C3673q1 c3673q1 = (C3673q1) next;
                StringBuilder sb = new StringBuilder();
                sb.append(c3673q1.f8776l);
                sb.append(!TextUtils.isEmpty(c3673q1.f8777m) ? c3673q1.f8777m : "");
                if (this.f8904i.contains(sb.toString())) {
                    it.remove();
                }
            } else if (this.f8905j.contains(((C3687s1) next).f8808m)) {
                it.remove();
            }
        }
        return true;
    }

    /* renamed from: b */
    public String m17050b() {
        return this.f8898c.getString("ab_sdk_version", "");
    }

    /* renamed from: b */
    public ArrayList<AbstractC3628m1> m17049b(ArrayList<AbstractC3628m1> arrayList) {
        Iterator<AbstractC3628m1> it = arrayList.iterator();
        ArrayList<AbstractC3628m1> arrayList2 = null;
        while (it.hasNext()) {
            AbstractC3628m1 next = it.next();
            String str = "!_NO_NAME_!";
            if (next instanceof C3673q1) {
                C3673q1 c3673q1 = (C3673q1) next;
                StringBuilder sb = new StringBuilder();
                sb.append(c3673q1.f8776l);
                sb.append(!TextUtils.isEmpty(c3673q1.f8777m) ? c3673q1.f8777m : "");
                str = sb.toString();
            } else if (next instanceof C3687s1) {
                str = ((C3687s1) next).f8808m;
            }
            HashSet<String> hashSet = this.f8906k;
            if (hashSet == null) {
                try {
                    JSONArray jSONArray = new JSONArray(this.f8900e.getString("real_time_events", "[]"));
                    int length = jSONArray.length();
                    HashSet<String> hashSet2 = new HashSet<>();
                    for (int i = 0; i < length; i++) {
                        String string = jSONArray.getString(i);
                        if (!TextUtils.isEmpty(string)) {
                            hashSet2.add(string);
                        }
                    }
                    hashSet = hashSet2;
                } catch (Throwable th) {
                    C3704u2.m17108a("U SHALL NOT PASS!", th);
                    hashSet = new HashSet<>();
                }
            }
            if (hashSet.contains(str)) {
                it.remove();
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                }
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    /* renamed from: c */
    public String m17048c() {
        String str = this.f8902g;
        if (TextUtils.isEmpty(str)) {
            synchronized (this) {
                str = this.f8898c.getString("external_ab_version", "");
                this.f8902g = str;
            }
        }
        return str;
    }

    /* renamed from: d */
    public long m17047d() {
        return this.f8900e.getLong("session_interval", 30000L);
    }

    /* renamed from: e */
    public boolean m17046e() {
        return this.f8897b.isAutoTrackEnabled();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003b, code lost:
        com.bytedance.applog.C3712v2.f8864a = r4.processName;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00bc A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean m17045f() {
        /*
            r8 = this;
            com.bytedance.applog.InitConfig r0 = r8.f8897b
            int r0 = r0.getProcess()
            r1 = 1
            if (r0 != 0) goto Lb3
            com.bytedance.applog.InitConfig r0 = r8.f8897b
            android.content.Context r2 = r8.f8896a
            java.lang.String r3 = com.bytedance.applog.C3712v2.f8864a
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L17
            goto La9
        L17:
            int r3 = android.os.Process.myPid()     // Catch: java.lang.Exception -> L40
            java.lang.String r4 = "activity"
            java.lang.Object r2 = r2.getSystemService(r4)     // Catch: java.lang.Exception -> L40
            android.app.ActivityManager r2 = (android.app.ActivityManager) r2     // Catch: java.lang.Exception -> L40
            java.util.List r2 = r2.getRunningAppProcesses()     // Catch: java.lang.Exception -> L40
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Exception -> L40
        L2b:
            boolean r4 = r2.hasNext()     // Catch: java.lang.Exception -> L40
            if (r4 == 0) goto L44
            java.lang.Object r4 = r2.next()     // Catch: java.lang.Exception -> L40
            android.app.ActivityManager$RunningAppProcessInfo r4 = (android.app.ActivityManager.RunningAppProcessInfo) r4     // Catch: java.lang.Exception -> L40
            int r5 = r4.pid     // Catch: java.lang.Exception -> L40
            if (r5 != r3) goto L2b
            java.lang.String r2 = r4.processName     // Catch: java.lang.Exception -> L40
            com.bytedance.applog.C3712v2.f8864a = r2     // Catch: java.lang.Exception -> L40
            goto L44
        L40:
            r2 = move-exception
            r2.printStackTrace()
        L44:
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L88
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L88
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L88
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L88
            r6.<init>()     // Catch: java.lang.Throwable -> L88
            java.lang.String r7 = "/proc/"
            r6.append(r7)     // Catch: java.lang.Throwable -> L88
            int r7 = android.os.Process.myPid()     // Catch: java.lang.Throwable -> L88
            r6.append(r7)     // Catch: java.lang.Throwable -> L88
            java.lang.String r7 = "/cmdline"
            r6.append(r7)     // Catch: java.lang.Throwable -> L88
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L88
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L88
            java.lang.String r6 = "iso-8859-1"
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L88
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L88
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L89
            r4.<init>()     // Catch: java.lang.Throwable -> L89
        L75:
            int r5 = r3.read()     // Catch: java.lang.Throwable -> L89
            if (r5 <= 0) goto L80
            char r5 = (char) r5     // Catch: java.lang.Throwable -> L89
            r4.append(r5)     // Catch: java.lang.Throwable -> L89
            goto L75
        L80:
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L89
            r3.close()     // Catch: java.lang.Exception -> L8f
            goto L8f
        L88:
            r3 = r2
        L89:
            if (r3 == 0) goto L8e
            r3.close()     // Catch: java.lang.Exception -> L8e
        L8e:
            r4 = r2
        L8f:
            com.bytedance.applog.C3712v2.f8864a = r4
            boolean r3 = com.bytedance.applog.C3704u2.f8845b
            if (r3 == 0) goto La7
            java.lang.String r3 = "getProcessName, "
            java.lang.StringBuilder r3 = com.bytedance.applog.C3535a.m17349a(r3)
            java.lang.String r4 = com.bytedance.applog.C3712v2.f8864a
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.bytedance.applog.C3704u2.m17108a(r3, r2)
        La7:
            java.lang.String r3 = com.bytedance.applog.C3712v2.f8864a
        La9:
            java.lang.String r2 = ":"
            boolean r2 = r3.contains(r2)
            r2 = r2 ^ r1
            r0.setProcess(r2)
        Lb3:
            com.bytedance.applog.InitConfig r0 = r8.f8897b
            int r0 = r0.getProcess()
            if (r0 != r1) goto Lbc
            goto Lbd
        Lbc:
            r1 = 0
        Lbd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3726x.m17045f():boolean");
    }
}
