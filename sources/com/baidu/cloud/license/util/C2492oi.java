package com.baidu.cloud.license.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.baidu.cloud.license.LicenseManager;
import com.baidu.cloud.license.license.LicenseModel;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: AuthenticationManager.java */
@NBSInstrumented
/* renamed from: com.baidu.cloud.license.util.oi */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2492oi {

    /* renamed from: a */
    private static Context f4359a;

    /* renamed from: b */
    private static String f4360b;

    /* renamed from: c */
    private static String f4361c;

    /* renamed from: d */
    private static C2493oi f4362d;

    /* renamed from: e */
    private static C2493oi f4363e;

    /* renamed from: f */
    private static Long f4364f = 0L;

    /* renamed from: g */
    private static C2492oi f4365g = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: AuthenticationManager.java */
    /* renamed from: com.baidu.cloud.license.util.oi$oi */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2493oi {

        /* renamed from: a */
        public int f4366a;

        /* renamed from: b */
        public String f4367b;

        /* renamed from: c */
        public String f4368c;

        /* renamed from: d */
        public String f4369d;

        /* renamed from: e */
        public JSONArray f4370e;
    }

    private C2492oi(String str) {
        f4360b = str;
    }

    /* renamed from: e */
    private static String m20069e() {
        return SecretUtil.m20083a().concat("gihBYYM8rBQUNM9RAYjXdFPdeiwTmww+Mw2gSCXDqexH6B+kfIglVjnAHX9UIgPVtu10e4Ls=");
    }

    /* renamed from: a */
    public static synchronized C2492oi m20078a(Context context, String str) {
        C2492oi c2492oi;
        synchronized (C2492oi.class) {
            if (f4365g == null) {
                f4365g = new C2492oi(str);
                if (context != null) {
                    f4359a = context;
                }
                if (f4361c == null) {
                    f4361c = m20072c(f4359a.getPackageName() + m20072c(f4360b));
                }
                if (f4362d == null) {
                    try {
                        C2493oi m20070d = m20070d(RsaUtil.m20066a(m20067g(), m20069e()));
                        f4362d = m20070d;
                        if (m20070d != null) {
                            f4364f = Long.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(f4362d.f4369d).getTime());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            c2492oi = f4365g;
        }
        return c2492oi;
    }

    /* renamed from: a */
    public static void m20079a(Context context) {
        if (m20068f()) {
            return;
        }
        String string = context == null ? "" : context.getSharedPreferences("account_sp", 0).getString("license", "");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        m20074b(string);
    }

    /* renamed from: f */
    private static boolean m20068f() {
        if (f4362d != null) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = 0;
            try {
                j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(f4362d.f4369d).getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return j > currentTimeMillis;
        }
        return false;
    }

    /* renamed from: b */
    private static void m20074b(String str) {
        String str2;
        try {
            str2 = RsaUtil.m20066a(str, m20069e());
        } catch (Exception e) {
            e.printStackTrace();
            str2 = null;
        }
        C2493oi c2493oi = f4362d;
        String str3 = c2493oi != null ? c2493oi.f4367b : "";
        C2493oi m20070d = m20070d(str2);
        if (m20070d != null) {
            f4362d = m20070d;
            m20070d.f4367b = str3;
            f4363e = m20070d;
            try {
                f4364f = Long.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(m20070d.f4369d).getTime());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static boolean m20076a(String str) {
        if (System.currentTimeMillis() > f4364f.longValue() || !f4361c.equals(f4362d.f4367b)) {
            return false;
        }
        JSONArray jSONArray = f4362d.f4370e;
        return (!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray)).contains(str);
    }

    /* renamed from: a */
    public static boolean m20080a() {
        C2493oi c2493oi = f4362d;
        return (c2493oi == null || c2493oi.f4367b == null || !f4361c.equals(f4362d.f4367b)) ? false : true;
    }

    /* renamed from: b */
    public static boolean m20075b() {
        return (f4362d == null && f4363e == null) ? false : true;
    }

    /* renamed from: c */
    public static boolean m20073c() {
        long j = 0;
        if (f4363e != null) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(f4363e.f4369d).getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return j < currentTimeMillis;
        } else if (f4362d != null) {
            long currentTimeMillis2 = System.currentTimeMillis();
            try {
                j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(f4362d.f4369d).getTime();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return j < currentTimeMillis2;
        } else {
            return true;
        }
    }

    /* renamed from: d */
    public static byte[] m20071d() {
        C2493oi c2493oi = f4362d;
        if (c2493oi != null) {
            return C2491nx.m20081a(c2493oi.f4368c);
        }
        return new byte[0];
    }

    /* renamed from: c */
    private static String m20072c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b : MessageDigest.getInstance("MD5").digest(str.getBytes())) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0".concat(String.valueOf(hexString));
                }
                str2 = str2 + hexString;
            }
            return str2.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: g */
    private static String m20067g() {
        try {
            AssetManager assets = f4359a.getResources().getAssets();
            InputStream open = assets.open("player" + m20072c(f4360b) + ".license");
            InputStreamReader inputStreamReader = new InputStreamReader(open, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine + "\n");
                } else {
                    bufferedReader.close();
                    inputStreamReader.close();
                    open.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    private static C2493oi m20070d(String str) {
        if (TextUtils.isEmpty(str)) {
            return f4362d;
        }
        try {
            C2493oi c2493oi = new C2493oi();
            JSONObject jSONObject = new JSONObject(str);
            c2493oi.f4366a = jSONObject.optInt("code");
            c2493oi.f4367b = jSONObject.optString("vc");
            c2493oi.f4368c = jSONObject.optString("ar");
            c2493oi.f4369d = jSONObject.optString("expireTime");
            c2493oi.f4370e = jSONObject.optJSONArray("funcCodeList");
            return c2493oi;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m20077a(LicenseModel licenseModel) {
        String result = licenseModel.getResult();
        if (TextUtils.isEmpty(result)) {
            return;
        }
        Context context = LicenseManager.getContext();
        if (context != null) {
            context.getSharedPreferences("account_sp", 0).edit().putString("license", result).apply();
        }
        m20074b(result);
    }
}
