package com.bytedance.applog;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.i2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3603i2 implements InterfaceC3662p2 {

    /* renamed from: f */
    public static String f8507f;

    /* renamed from: g */
    public static String f8508g;

    /* renamed from: h */
    public static String f8509h;

    /* renamed from: i */
    public static JSONArray f8510i;

    /* renamed from: j */
    public static volatile String f8511j;

    /* renamed from: k */
    public static String[] f8512k;

    /* renamed from: l */
    public static String f8513l;

    /* renamed from: a */
    public final Context f8514a;

    /* renamed from: b */
    public AbstractC3635n1 f8515b;

    /* renamed from: c */
    public final C3620l1 f8516c;

    /* renamed from: d */
    public final String f8517d;

    /* renamed from: e */
    public final C3726x f8518e;

    public C3603i2(Context context, C3726x c3726x, C3620l1 c3620l1) {
        this.f8518e = c3726x;
        this.f8517d = c3726x.f8897b.getLocalTest() ? "_local" : "";
        this.f8514a = context.getApplicationContext();
        C3596h2 c3596h2 = new C3596h2();
        this.f8516c = c3620l1;
        this.f8515b = new C3719w1(this.f8514a, c3726x.f8897b.getSpName());
        this.f8515b.f8607a = this.f8516c;
        if (!c3726x.f8897b.getAnonymous()) {
            new Thread(new RunnableC3589g2(c3596h2)).start();
        }
        Account account = c3726x.f8897b.getAccount();
        C3620l1 c3620l12 = this.f8516c;
        if (c3620l12 != null) {
            c3620l12.m17246a(account);
        }
    }

    /* renamed from: a */
    public String m17282a() {
        if (TextUtils.isEmpty(f8508g)) {
            try {
                SharedPreferences sharedPreferences = this.f8514a.getSharedPreferences("snssdk_openudid", 0);
                String string = sharedPreferences.getString("clientudid", null);
                if (C3712v2.m17078a(string)) {
                    this.f8516c.m17208b(string, null);
                } else {
                    string = UUID.randomUUID().toString();
                    String m17208b = this.f8516c.m17208b(m17278a("clientudid.dat", (String) null), string);
                    if (C3712v2.m17078a(m17208b)) {
                        string = m17208b;
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("clientudid", string);
                    edit.commit();
                }
                if (!TextUtils.isEmpty(string)) {
                    string = string + this.f8517d;
                }
                f8508g = string;
                return string;
            } catch (Exception e) {
                C3704u2.m17108a("", e);
                return "";
            }
        }
        return f8508g;
    }

    /* renamed from: a */
    public void m17281a(Account account) {
        C3620l1 c3620l1 = this.f8516c;
        if (c3620l1 != null) {
            c3620l1.m17246a(account);
        }
    }

    /* renamed from: a */
    public void m17280a(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f8511j = null;
        String str3 = "clear_key_prefix" + str;
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.f8518e.f8897b.getSpName(), 0);
        if (!sharedPreferences.getBoolean(str3, false)) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(str3, true);
            if (sharedPreferences.contains("device_id")) {
                edit.remove("device_id");
            }
            if (sharedPreferences.contains("install_id")) {
                edit.remove("install_id");
            }
            edit.apply();
            this.f8515b.mo17068a("device_id");
            if (!C3704u2.f8845b) {
                return;
            }
            str2 = "clearKey : " + str + " :clear installId and deviceId finish";
        } else if (!C3704u2.f8845b) {
            return;
        } else {
            str2 = "clearKey : " + str + " : is already cleared";
        }
        C3704u2.m17108a(str2, (Throwable) null);
    }

    /* renamed from: a */
    public void m17279a(String str) {
        if (!C3735y.m17007f(str) || C3712v2.m17073b(str, f8511j)) {
            return;
        }
        f8511j = this.f8515b.m17207c(str, f8511j);
    }

    /* renamed from: b */
    public String m17276b() {
        if (TextUtils.isEmpty(f8511j)) {
            f8511j = this.f8515b.m17207c("", "");
            return f8511j;
        }
        return f8511j;
    }

    /* renamed from: c */
    public String m17275c() {
        if (TextUtils.isEmpty(f8513l)) {
            try {
                String m17205e = this.f8515b.m17205e(null, C3710v0.m17086c(this.f8514a));
                if (!TextUtils.isEmpty(m17205e)) {
                    m17205e = m17205e + this.f8517d;
                }
                f8513l = m17205e;
                return m17205e;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return f8513l;
    }

    /* renamed from: d */
    public String[] m17274d() {
        String[] strArr = f8512k;
        if (strArr == null || strArr.length <= 0) {
            try {
                String[] m17209a = this.f8515b.m17209a((String[]) null, C3710v0.m17083d(this.f8514a));
                if (m17209a == null) {
                    m17209a = new String[0];
                }
                for (int i = 0; i < m17209a.length; i++) {
                    m17209a[i] = m17209a[i] + this.f8517d;
                }
                f8512k = m17209a;
                return m17209a;
            } catch (Exception e) {
                C3704u2.m17108a("", e);
                return null;
            }
        }
        return strArr;
    }

    /* renamed from: e */
    public String m17273e() {
        String deviceId;
        if (TextUtils.isEmpty(f8509h)) {
            try {
                if (!this.f8518e.f8897b.isImeiEnable()) {
                    deviceId = this.f8518e.f8897b.getAppImei();
                } else {
                    Context context = this.f8514a;
                    if (context != null) {
                        try {
                            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    deviceId = null;
                }
                String m17204f = this.f8515b.m17204f(null, deviceId);
                if (!TextUtils.isEmpty(m17204f)) {
                    m17204f = m17204f + this.f8517d;
                }
                f8509h = m17204f;
                return m17204f;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return f8509h;
    }

    /* renamed from: f */
    public JSONArray m17272f() {
        JSONArray jSONArray = f8510i;
        if (jSONArray != null) {
            return jSONArray;
        }
        try {
            JSONArray m17090b = C3710v0.m17090b(this.f8514a);
            JSONArray jSONArray2 = new JSONArray(this.f8515b.m17203g(null, !(m17090b instanceof JSONArray) ? m17090b.toString() : NBSJSONArrayInstrumentation.toString(m17090b)));
            if (!TextUtils.isEmpty(this.f8517d)) {
                String str = this.f8517d;
                if (jSONArray2.length() != 0) {
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        JSONObject optJSONObject = jSONArray2.optJSONObject(i);
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString("id");
                            if (!TextUtils.isEmpty(optString)) {
                                optJSONObject.remove("id");
                                optJSONObject.put("id", optString + str);
                            }
                        }
                    }
                }
            }
            f8510i = jSONArray2;
            return f8510i;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: g */
    public String m17271g() {
        throw new RuntimeException("请不要调用这个方法");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d2  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m17277a(boolean r8) {
        /*
            r7 = this;
            java.lang.String r0 = com.bytedance.applog.C3603i2.f8507f
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto Lb
            java.lang.String r8 = com.bytedance.applog.C3603i2.f8507f
            return r8
        Lb:
            android.content.Context r0 = r7.f8514a
            r1 = 0
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Exception -> L19
            java.lang.String r2 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r2)     // Catch: java.lang.Exception -> L19
            goto L1e
        L19:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r1
        L1e:
            boolean r2 = com.bytedance.applog.C3712v2.m17078a(r0)     // Catch: java.lang.Exception -> Lb3
            if (r2 == 0) goto L35
            java.lang.String r2 = "9774d56d682e549c"
            boolean r2 = r2.equals(r0)     // Catch: java.lang.Exception -> Lb3
            if (r2 == 0) goto L2d
            goto L35
        L2d:
            com.bytedance.applog.n1 r8 = r7.f8515b     // Catch: java.lang.Exception -> Lb3
            java.lang.String r0 = r8.m17206d(r1, r0)     // Catch: java.lang.Exception -> Lb3
            goto Lb9
        L35:
            android.content.Context r2 = r7.f8514a     // Catch: java.lang.Exception -> Lb3
            java.lang.String r3 = "snssdk_openudid"
            r4 = 0
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r3, r4)     // Catch: java.lang.Exception -> Lb3
            java.lang.String r3 = "openudid"
            java.lang.String r3 = r2.getString(r3, r1)     // Catch: java.lang.Exception -> Lb3
            boolean r5 = com.bytedance.applog.C3712v2.m17078a(r3)     // Catch: java.lang.Exception -> Lb3
            if (r5 != 0) goto Lac
            java.security.SecureRandom r3 = new java.security.SecureRandom     // Catch: java.lang.Exception -> Lb3
            r3.<init>()     // Catch: java.lang.Exception -> Lb3
            java.math.BigInteger r5 = new java.math.BigInteger     // Catch: java.lang.Exception -> Lb3
            r6 = 80
            r5.<init>(r6, r3)     // Catch: java.lang.Exception -> Lb3
            r3 = 16
            java.lang.String r3 = r5.toString(r3)     // Catch: java.lang.Exception -> Lb3
            char r4 = r3.charAt(r4)     // Catch: java.lang.Exception -> Lb3
            r5 = 45
            if (r4 != r5) goto L6a
            r4 = 1
            java.lang.String r3 = r3.substring(r4)     // Catch: java.lang.Exception -> Lb3
        L6a:
            int r4 = r3.length()     // Catch: java.lang.Exception -> Lb3
            int r4 = 13 - r4
            if (r4 <= 0) goto L88
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb3
            r5.<init>()     // Catch: java.lang.Exception -> Lb3
        L77:
            if (r4 <= 0) goto L81
            r6 = 70
            r5.append(r6)     // Catch: java.lang.Exception -> Lb3
            int r4 = r4 + (-1)
            goto L77
        L81:
            r5.append(r3)     // Catch: java.lang.Exception -> Lb3
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Exception -> Lb3
        L88:
            if (r8 == 0) goto L9d
            java.lang.String r8 = "openudid.dat"
            java.lang.String r8 = m17278a(r8, r1)     // Catch: java.lang.Exception -> Lb3
            com.bytedance.applog.l1 r1 = r7.f8516c     // Catch: java.lang.Exception -> Lb3
            java.lang.String r8 = r1.m17206d(r8, r3)     // Catch: java.lang.Exception -> Lb3
            boolean r1 = com.bytedance.applog.C3712v2.m17078a(r8)     // Catch: java.lang.Exception -> Lb3
            if (r1 == 0) goto L9d
            goto L9e
        L9d:
            r8 = r3
        L9e:
            android.content.SharedPreferences$Editor r1 = r2.edit()     // Catch: java.lang.Exception -> Lb3
            java.lang.String r2 = "openudid"
            r1.putString(r2, r8)     // Catch: java.lang.Exception -> Lb3
            r1.commit()     // Catch: java.lang.Exception -> Lb3
            r0 = r8
            goto Lb9
        Lac:
            com.bytedance.applog.l1 r8 = r7.f8516c     // Catch: java.lang.Exception -> Lb3
            r8.m17206d(r3, r1)     // Catch: java.lang.Exception -> Lb3
            r0 = r3
            goto Lb9
        Lb3:
            r8 = move-exception
            java.lang.String r1 = ""
            com.bytedance.applog.C3704u2.m17108a(r1, r8)
        Lb9:
            boolean r8 = android.text.TextUtils.isEmpty(r0)
            if (r8 != 0) goto Lcc
            java.lang.StringBuilder r8 = com.bytedance.applog.C3535a.m17349a(r0)
            java.lang.String r0 = r7.f8517d
            r8.append(r0)
            java.lang.String r0 = r8.toString()
        Lcc:
            boolean r8 = android.text.TextUtils.isEmpty(r0)
            if (r8 != 0) goto Ld4
            com.bytedance.applog.C3603i2.f8507f = r0
        Ld4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3603i2.m17277a(boolean):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x00db A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m17278a(java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r0 = android.os.Environment.getExternalStorageState()
            java.lang.String r1 = "mounted"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto Ld
            return r7
        Ld:
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            r1.<init>()     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.lang.String r2 = r2.getPath()     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.lang.String r2 = "L0FuZHJvaWQvZGF0YS9jb20uc25zc2RrLmFwaS9jYWNoZQ=="
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> Lc1
            java.lang.String r4 = "UTF-8"
            byte[] r2 = r2.getBytes(r4)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> Lc1
            r4 = 2
            byte[] r2 = android.util.Base64.decode(r2, r4)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> Lc1
            r3.<init>(r2)     // Catch: java.lang.Exception -> L31 java.lang.Throwable -> Lc1
            goto L33
        L31:
            java.lang.String r3 = ""
        L33:
            r1.append(r3)     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            r2.<init>()     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            r2.append(r1)     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.lang.String r3 = "/"
            r2.append(r3)     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            r2.append(r6)     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            r2.<init>(r1)     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            boolean r1 = r2.exists()     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            if (r1 != 0) goto L5a
            return r7
        L5a:
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            r1.<init>(r6)     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.lang.String r2 = "rwd"
            r6.<init>(r1, r2)     // Catch: java.lang.Throwable -> Lc1 java.lang.Exception -> Lc5
            java.nio.channels.FileChannel r2 = r6.getChannel()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.nio.channels.FileLock r0 = r2.lock()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            boolean r1 = r1.isFile()     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            if (r1 == 0) goto L97
            r1 = 129(0x81, float:1.81E-43)
            byte[] r2 = new byte[r1]     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r3 = 0
            int r4 = r6.read(r2, r3, r1)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            if (r4 <= 0) goto L97
            if (r4 >= r1) goto L97
            java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            java.lang.String r5 = "UTF-8"
            r1.<init>(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            boolean r2 = com.bytedance.applog.C3712v2.m17078a(r1)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            if (r2 == 0) goto L97
            if (r0 == 0) goto L93
            r0.release()     // Catch: java.lang.Exception -> L93
        L93:
            r6.close()     // Catch: java.lang.Exception -> L96
        L96:
            return r1
        L97:
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            if (r1 == 0) goto La6
            if (r0 == 0) goto La2
            r0.release()     // Catch: java.lang.Exception -> La2
        La2:
            r6.close()     // Catch: java.lang.Exception -> La5
        La5:
            return r7
        La6:
            java.lang.String r1 = "UTF-8"
            byte[] r1 = r7.getBytes(r1)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r2 = 0
            r6.setLength(r2)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            r6.write(r1)     // Catch: java.lang.Throwable -> Lbd java.lang.Exception -> Lbf
            if (r0 == 0) goto Lb9
            r0.release()     // Catch: java.lang.Exception -> Lb9
        Lb9:
            r6.close()     // Catch: java.lang.Exception -> Lbc
        Lbc:
            return r7
        Lbd:
            r7 = move-exception
            goto Ld9
        Lbf:
            r1 = move-exception
            goto Lc8
        Lc1:
            r6 = move-exception
            r7 = r6
            r6 = r0
            goto Ld9
        Lc5:
            r6 = move-exception
            r1 = r6
            r6 = r0
        Lc8:
            java.lang.String r2 = "U SHALL NOT PASS!"
            com.bytedance.applog.C3704u2.m17108a(r2, r1)     // Catch: java.lang.Throwable -> Ld8
            if (r0 == 0) goto Ld2
            r0.release()     // Catch: java.lang.Exception -> Ld2
        Ld2:
            if (r6 == 0) goto Ld7
            r6.close()     // Catch: java.lang.Exception -> Ld7
        Ld7:
            return r7
        Ld8:
            r7 = move-exception
        Ld9:
            if (r0 == 0) goto Lde
            r0.release()     // Catch: java.lang.Exception -> Lde
        Lde:
            if (r6 == 0) goto Le3
            r6.close()     // Catch: java.lang.Exception -> Le3
        Le3:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3603i2.m17278a(java.lang.String, java.lang.String):java.lang.String");
    }
}
