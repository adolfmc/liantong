package p001a.p002a.p003a.p057f;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.baidu.license.LicenseManager;
import com.baidu.license.license.LicenseModel;
import com.baidu.license.util.SecretUtil;
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
import p001a.p002a.p003a.p004a.RequestParameterUtil;

@NBSInstrumented
/* renamed from: a.a.a.f.oi */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class AuthenticationManager {

    /* renamed from: a */
    public static Context f2086a;

    /* renamed from: b */
    public static String f2087b;

    /* renamed from: c */
    public static String f2088c;

    /* renamed from: d */
    public static C0700oi f2089d;

    /* renamed from: e */
    public static C0700oi f2090e;

    /* renamed from: f */
    public static Long f2091f = 0L;

    /* renamed from: g */
    public static AuthenticationManager f2092g = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: AuthenticationManager.java */
    /* renamed from: a.a.a.f.oi$oi */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C0700oi {

        /* renamed from: a */
        public String f2093a;

        /* renamed from: b */
        public String f2094b;

        /* renamed from: c */
        public String f2095c;

        /* renamed from: d */
        public JSONArray f2096d;
    }

    public AuthenticationManager(String str) {
        f2087b = str;
    }

    /* renamed from: a */
    public static String m22352a() {
        return SecretUtil.m19637a().concat("gihBYYM8rBQUNM9RAYjXdFPdeiwTmww+Mw2gSCXDqexH6B+kfIglVjnAHX9UIgPVtu10e4Ls=");
    }

    /* renamed from: c */
    public static String m22347c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b : MessageDigest.getInstance("MD5").digest(str.getBytes())) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("0");
                    sb.append(hexString);
                    hexString = sb.toString();
                }
                str2 = str2 + hexString;
            }
            return str2.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    public static C0700oi m22346d(String str) {
        if (TextUtils.isEmpty(str)) {
            return f2089d;
        }
        try {
            C0700oi c0700oi = new C0700oi();
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.optInt("code");
            c0700oi.f2093a = jSONObject.optString("vc");
            c0700oi.f2094b = jSONObject.optString("ar");
            c0700oi.f2095c = jSONObject.optString("expireTime");
            c0700oi.f2096d = jSONObject.optJSONArray("funcCodeList");
            return c0700oi;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public boolean m22348b(String str) {
        if (System.currentTimeMillis() > f2091f.longValue() || !f2088c.equals(f2089d.f2093a)) {
            return false;
        }
        JSONArray jSONArray = f2089d.f2096d;
        return (!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray)).contains(str);
    }

    /* renamed from: a */
    public static synchronized AuthenticationManager m22351a(Context context, String str) {
        AuthenticationManager authenticationManager;
        String str2;
        synchronized (AuthenticationManager.class) {
            if (f2092g == null) {
                f2092g = new AuthenticationManager(str);
                if (context != null) {
                    AuthenticationManager authenticationManager2 = f2092g;
                    f2086a = context;
                }
                if (f2088c == null) {
                    f2088c = m22347c(f2086a.getPackageName() + m22347c(f2087b));
                }
                if (f2089d == null) {
                    try {
                        AssetManager assets = f2086a.getResources().getAssets();
                        StringBuilder sb = new StringBuilder();
                        sb.append(m22347c(f2087b));
                        sb.append(".license");
                        InputStream open = assets.open(sb.toString());
                        InputStreamReader inputStreamReader = new InputStreamReader(open, "UTF-8");
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        StringBuilder sb2 = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(readLine);
                            sb3.append("\n");
                            sb2.append(sb3.toString());
                        }
                        bufferedReader.close();
                        inputStreamReader.close();
                        open.close();
                        str2 = sb2.toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                        str2 = "";
                    }
                    try {
                        f2089d = m22346d(RequestParameterUtil.m22365a(str2, m22352a()));
                        if (f2089d != null) {
                            f2091f = Long.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(f2089d.f2095c).getTime());
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            authenticationManager = f2092g;
        }
        return authenticationManager;
    }

    /* renamed from: a */
    public static /* synthetic */ void m22350a(LicenseModel licenseModel) {
        String result = licenseModel.getResult();
        if (TextUtils.isEmpty(result)) {
            return;
        }
        Context context = LicenseManager.sContext;
        if (context != null) {
            context.getSharedPreferences("account_sp", 0).edit().putString("license", result).apply();
        }
        m22349a(result);
    }

    /* renamed from: a */
    public static void m22349a(String str) {
        String str2;
        try {
            str2 = RequestParameterUtil.m22365a(str, m22352a());
        } catch (Exception e) {
            e.printStackTrace();
            str2 = null;
        }
        C0700oi c0700oi = f2089d;
        String str3 = c0700oi != null ? c0700oi.f2093a : "";
        C0700oi m22346d = m22346d(str2);
        if (m22346d != null) {
            f2089d = m22346d;
            f2089d.f2093a = str3;
            f2090e = m22346d;
            try {
                f2091f = Long.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(m22346d.f2095c).getTime());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
