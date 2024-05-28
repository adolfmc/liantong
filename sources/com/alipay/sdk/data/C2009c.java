package com.alipay.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.sdk.app.C1997i;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.cons.C2003a;
import com.alipay.sdk.sys.C2033b;
import com.alipay.sdk.tid.C2035b;
import com.alipay.sdk.util.C2037a;
import com.alipay.sdk.util.C2040c;
import com.alipay.sdk.util.C2052n;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.data.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2009c {

    /* renamed from: a */
    private static final String f3744a = "virtualImeiAndImsi";

    /* renamed from: b */
    private static final String f3745b = "virtual_imei";

    /* renamed from: c */
    private static final String f3746c = "virtual_imsi";

    /* renamed from: d */
    private static C2009c f3747d;

    /* renamed from: e */
    private String f3748e;

    /* renamed from: f */
    private String f3749f = "sdk-and-lite";

    /* renamed from: g */
    private String f3750g;

    /* renamed from: e */
    private String m20848e() {
        return "1";
    }

    /* renamed from: f */
    private String m20847f() {
        return "-1;-1";
    }

    /* renamed from: a */
    public String m20861a() {
        return this.f3750g;
    }

    private C2009c() {
        String m20918a = C1997i.m20918a();
        if (C1997i.m20916b()) {
            return;
        }
        this.f3749f += '_' + m20918a;
    }

    /* renamed from: b */
    public static synchronized C2009c m20855b() {
        C2009c c2009c;
        synchronized (C2009c.class) {
            if (f3747d == null) {
                f3747d = new C2009c();
            }
            c2009c = f3747d;
        }
        return c2009c;
    }

    /* renamed from: a */
    public synchronized void m20856a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        PreferenceManager.getDefaultSharedPreferences(C2033b.m20772a().m20770b()).edit().putString("trideskey", str).commit();
        C2003a.f3671c = str;
    }

    /* renamed from: b */
    private String m20854b(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    /* renamed from: a */
    public String m20857a(C2035b c2035b) {
        Context m20770b = C2033b.m20772a().m20770b();
        C2037a m20728a = C2037a.m20728a(m20770b);
        if (TextUtils.isEmpty(this.f3748e)) {
            String m20662b = C2052n.m20662b();
            String m20655c = C2052n.m20655c();
            String m20650d = C2052n.m20650d(m20770b);
            String m20646f = C2052n.m20646f(m20770b);
            String m20648e = C2052n.m20648e(m20770b);
            String m20854b = m20854b(m20770b);
            this.f3748e = "Msp/15.6.8 (" + m20662b + ";" + m20655c + ";" + m20650d + ";" + m20646f + ";" + m20648e + ";" + m20854b;
        }
        String m20706b = C2037a.m20725b(m20770b).m20706b();
        String m20644g = C2052n.m20644g(m20770b);
        String m20848e = m20848e();
        String m20729a = m20728a.m20729a();
        String m20726b = m20728a.m20726b();
        String m20850d = m20850d();
        String m20852c = m20852c();
        if (c2035b != null) {
            this.f3750g = c2035b.m20754b();
        }
        String replace = Build.MANUFACTURER.replace(";", " ");
        String replace2 = Build.MODEL.replace(";", " ");
        boolean m20768d = C2033b.m20768d();
        String m20721d = m20728a.m20721d();
        String m20851c = m20851c(m20770b);
        String m20849d = m20849d(m20770b);
        StringBuilder sb = new StringBuilder();
        sb.append(this.f3748e);
        sb.append(";");
        sb.append(m20706b);
        sb.append(";");
        sb.append(m20644g);
        sb.append(";");
        sb.append(m20848e);
        sb.append(";");
        sb.append(m20729a);
        sb.append(";");
        sb.append(m20726b);
        sb.append(";");
        sb.append(this.f3750g);
        sb.append(";");
        sb.append(replace);
        sb.append(";");
        sb.append(replace2);
        sb.append(";");
        sb.append(m20768d);
        sb.append(";");
        sb.append(m20721d);
        sb.append(";");
        sb.append(m20847f());
        sb.append(";");
        sb.append(this.f3749f);
        sb.append(";");
        sb.append(m20850d);
        sb.append(";");
        sb.append(m20852c);
        sb.append(";");
        sb.append(m20851c);
        sb.append(";");
        sb.append(m20849d);
        if (c2035b != null) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("tid", C2035b.m20758a(m20770b).m20759a());
            hashMap.put("utdid", C2033b.m20772a().m20767e());
            String m20853b = m20853b(m20770b, hashMap);
            if (!TextUtils.isEmpty(m20853b)) {
                sb.append(";");
                sb.append(m20853b);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: c */
    public String m20852c() {
        Context m20770b = C2033b.m20772a().m20770b();
        SharedPreferences sharedPreferences = m20770b.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imei", null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C2035b.m20758a(m20770b).m20759a())) {
                string = m20846g();
            } else {
                string = C2037a.m20728a(m20770b).m20726b();
            }
            sharedPreferences.edit().putString("virtual_imei", string).commit();
        }
        return string;
    }

    /* renamed from: d */
    public String m20850d() {
        String substring;
        Context m20770b = C2033b.m20772a().m20770b();
        SharedPreferences sharedPreferences = m20770b.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imsi", null);
        if (TextUtils.isEmpty(string)) {
            if (TextUtils.isEmpty(C2035b.m20758a(m20770b).m20759a())) {
                String m20767e = C2033b.m20772a().m20767e();
                if (TextUtils.isEmpty(m20767e)) {
                    substring = m20846g();
                } else {
                    substring = m20767e.substring(3, 18);
                }
                string = substring;
            } else {
                string = C2037a.m20728a(m20770b).m20729a();
            }
            sharedPreferences.edit().putString("virtual_imsi", string).commit();
        }
        return string;
    }

    /* renamed from: g */
    private String m20846g() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        Random random = new Random();
        return hexString + (random.nextInt(9000) + 1000);
    }

    /* renamed from: c */
    private String m20851c(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getSSID() : "-1";
    }

    /* renamed from: d */
    private String m20849d(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getBSSID() : "00";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m20859a(Context context, HashMap<String, String> hashMap) {
        String str = "";
        try {
            str = SecurityClientMobile.GetApdid(context, hashMap);
        } catch (Throwable th) {
            C2040c.m20715a(th);
            C2000a.m20898a("third", "GetApdidEx", th);
        }
        if (TextUtils.isEmpty(str)) {
            C2000a.m20899a("third", "GetApdidNull", "apdid == null");
        }
        C2040c.m20711d("msp", "apdid:" + str);
        return str;
    }

    /* renamed from: b */
    private String m20853b(Context context, HashMap<String, String> hashMap) {
        try {
            return (String) Executors.newFixedThreadPool(2).submit(new CallableC2010d(this, context, hashMap)).get(3000L, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            C2000a.m20898a("third", "GetApdidTimeout", th);
            return "";
        }
    }

    /* renamed from: a */
    public String m20860a(Context context) {
        if (context != null) {
            try {
                StringBuilder sb = new StringBuilder();
                String packageName = context.getPackageName();
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                sb.append("(");
                sb.append(packageName);
                sb.append(";");
                sb.append(packageInfo.versionCode);
                sb.append(")");
                return sb.toString();
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }
}
