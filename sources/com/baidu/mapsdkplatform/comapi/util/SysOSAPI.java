package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mapsdkplatform.comapi.Initializer;
import com.baidu.mapsdkplatform.comjni.util.APPSysOSAPI;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import com.baidu.platform.comapi.util.JsonBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.util.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SysOSAPI {

    /* renamed from: a */
    public static Context f7441a = null;

    /* renamed from: c */
    public static String f7443c = null;

    /* renamed from: d */
    private static final String f7444d = "h";

    /* renamed from: g */
    private static String f7447g;

    /* renamed from: h */
    private static String f7448h;

    /* renamed from: i */
    private static String f7449i;

    /* renamed from: j */
    private static String f7450j;

    /* renamed from: k */
    private static int f7451k;

    /* renamed from: l */
    private static int f7452l;

    /* renamed from: m */
    private static int f7453m;

    /* renamed from: n */
    private static int f7454n;

    /* renamed from: o */
    private static int f7455o;

    /* renamed from: p */
    private static int f7456p;

    /* renamed from: q */
    private static String f7457q;

    /* renamed from: v */
    private static String f7462v;

    /* renamed from: w */
    private static String f7463w;

    /* renamed from: e */
    private static APPSysOSAPI f7445e = new APPSysOSAPI();

    /* renamed from: f */
    private static String f7446f = "02";

    /* renamed from: r */
    private static String f7458r = "baidu";

    /* renamed from: s */
    private static String f7459s = "";

    /* renamed from: t */
    private static String f7460t = "";

    /* renamed from: u */
    private static String f7461u = "";

    /* renamed from: x */
    private static String f7464x = "-1";

    /* renamed from: y */
    private static String f7465y = "-1";

    /* renamed from: b */
    public static float f7442b = 1.0f;

    /* renamed from: z */
    private static String f7466z = "";

    /* renamed from: A */
    private static Map<String, String> f7439A = new HashMap();

    /* renamed from: B */
    private static String f7440B = "";

    /* renamed from: d */
    private static void m18134d(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            f7450j = VersionInfo.getApiVersion();
            if (f7450j != null && !f7450j.equals("")) {
                f7450j = f7450j.replace('_', '.');
            }
            f7451k = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            f7450j = "1.0.0";
            f7451k = 1;
        }
    }

    /* renamed from: a */
    public static void m18143a() {
        f7443c = null;
    }

    /* renamed from: e */
    private static void m18132e(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        if (defaultDisplay != null) {
            f7452l = defaultDisplay.getWidth();
            f7453m = defaultDisplay.getHeight();
            defaultDisplay.getMetrics(displayMetrics);
        }
        f7442b = displayMetrics.density;
        f7454n = (int) displayMetrics.xdpi;
        f7455o = (int) displayMetrics.ydpi;
        f7456p = displayMetrics.densityDpi;
        if (f7456p == 0) {
            f7456p = C0567f.f1819h;
        }
    }

    /* renamed from: a */
    public static byte[] m18142a(Context context) {
        Signature[] signatureArr;
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                SigningInfo signingInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 134217728).signingInfo;
                if (signingInfo == null) {
                    return null;
                }
                if (signingInfo.hasMultipleSigners()) {
                    signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 134217728).signingInfo.getApkContentsSigners();
                } else {
                    signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 134217728).signingInfo.getSigningCertificateHistory();
                }
            } else {
                signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            }
            if (signatureArr != null && signatureArr.length > 0) {
                return signatureArr[0].toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: s */
    private static void m18118s() {
        f7457q = "0";
    }

    /* renamed from: b */
    public static void m18138b(Context context) {
        f7441a = context;
    }

    /* renamed from: c */
    public static void m18136c(Context context) {
        f7441a = context;
        if (context.getFilesDir() != null) {
            f7462v = context.getFilesDir().getAbsolutePath();
        }
        if (context.getCacheDir() != null) {
            f7463w = context.getCacheDir().getAbsolutePath();
        }
        if (Initializer.m18475b()) {
            f7449i = "Android" + Build.VERSION.SDK;
            f7448h = Build.MODEL;
        } else {
            f7449i = "Android";
            f7448h = "";
        }
        f7447g = context.getPackageName();
        m18134d(context);
        m18132e(context);
        m18118s();
        f7466z = m18119r();
        f7439A.put("resid", AppMD5.encodeUrlParamsValue(f7446f));
        f7439A.put("channel", AppMD5.encodeUrlParamsValue(m18122o()));
        f7439A.put("mb", AppMD5.encodeUrlParamsValue(m18128i()));
        f7439A.put("sv", AppMD5.encodeUrlParamsValue(m18126k()));
        f7439A.put("os", AppMD5.encodeUrlParamsValue(m18124m()));
        f7439A.put("dpi", AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(m18123n()), Integer.valueOf(m18123n()))));
        f7439A.put("cuid", AppMD5.encodeUrlParamsValue(f7466z));
        f7439A.put("pcn", AppMD5.encodeUrlParamsValue(f7441a.getPackageName()));
        f7439A.put("screen", AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(m18127j()), Integer.valueOf(m18125l()))));
        APPSysOSAPI aPPSysOSAPI = f7445e;
        if (aPPSysOSAPI != null) {
            aPPSysOSAPI.m18112a();
        }
    }

    /* renamed from: b */
    public static void m18139b() {
        m18131f();
    }

    /* renamed from: c */
    public static String m18137c() {
        JsonBuilder jsonBuilder = new JsonBuilder();
        jsonBuilder.object();
        jsonBuilder.putStringValue("cpu", f7459s);
        jsonBuilder.putStringValue("resid", f7446f);
        jsonBuilder.putStringValue("channel", f7458r);
        jsonBuilder.putStringValue("glr", f7460t);
        jsonBuilder.putStringValue("glv", f7461u);
        jsonBuilder.putStringValue("mb", m18128i());
        jsonBuilder.putStringValue("sv", m18126k());
        jsonBuilder.putStringValue("os", m18124m());
        jsonBuilder.key("dpi_x").value(m18123n());
        jsonBuilder.key("dpi_y").value(m18123n());
        jsonBuilder.putStringValue("net", f7457q);
        jsonBuilder.putStringValue("cuid", f7466z);
        jsonBuilder.key("signature").arrayValue();
        byte[] m18142a = m18142a(f7441a);
        if (m18142a != null) {
            for (byte b : m18142a) {
                jsonBuilder.value((int) b);
            }
        }
        jsonBuilder.endArrayValue();
        jsonBuilder.putStringValue("pcn", f7441a.getPackageName());
        jsonBuilder.key("screen_x").value(m18127j());
        jsonBuilder.key("screen_y").value(m18125l());
        jsonBuilder.endObject();
        f7440B = jsonBuilder.getJson();
        return f7440B;
    }

    /* renamed from: d */
    public static String m18135d() {
        return f7440B;
    }

    /* renamed from: e */
    public static String m18133e() {
        if (f7439A == null) {
            return null;
        }
        Date date = new Date();
        long time = date.getTime() + (date.getSeconds() * 1000);
        f7439A.put("ctm", AppMD5.encodeUrlParamsValue(String.format("%f", Double.valueOf((time / 1000) + ((time % 1000) / 1000.0d)))));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : f7439A.entrySet()) {
            sb.append("&");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    /* renamed from: f */
    public static void m18131f() {
        APPSysOSAPI aPPSysOSAPI = f7445e;
        if (aPPSysOSAPI != null) {
            aPPSysOSAPI.m18111b();
        }
    }

    /* renamed from: a */
    public static void m18141a(String str) {
        f7457q = str;
        m18129h();
    }

    /* renamed from: g */
    public static String m18130g() {
        return f7457q;
    }

    /* renamed from: h */
    public static void m18129h() {
        f7439A.put("net", AppMD5.encodeUrlParamsValue(m18130g()));
        f7439A.put("appid", AppMD5.encodeUrlParamsValue(f7464x));
        f7439A.put("bduid", "");
        JsonBuilder jsonBuilder = new JsonBuilder();
        jsonBuilder.object();
        jsonBuilder.putStringValue("cpu", f7459s);
        jsonBuilder.putStringValue("resid", f7446f);
        jsonBuilder.putStringValue("channel", f7458r);
        jsonBuilder.putStringValue("glr", f7460t);
        jsonBuilder.putStringValue("glv", f7461u);
        jsonBuilder.putStringValue("mb", m18128i());
        jsonBuilder.putStringValue("sv", m18126k());
        jsonBuilder.putStringValue("os", m18124m());
        jsonBuilder.key("dpi_x").value(m18123n());
        jsonBuilder.key("dpi_y").value(m18123n());
        jsonBuilder.putStringValue("net", f7457q);
        jsonBuilder.putStringValue("cuid", f7466z);
        jsonBuilder.putStringValue("pcn", f7441a.getPackageName());
        jsonBuilder.key("screen_x").value(m18127j());
        jsonBuilder.key("screen_y").value(m18125l());
        jsonBuilder.putStringValue("appid", f7464x);
        jsonBuilder.putStringValue("duid", f7465y);
        if (!TextUtils.isEmpty(f7443c)) {
            jsonBuilder.putStringValue("token", f7443c);
        }
        jsonBuilder.endObject();
        SysUpdateObservable.getInstance().updatePhoneInfo(jsonBuilder.getJson());
    }

    /* renamed from: a */
    public static void m18140a(String str, String str2) {
        f7464x = str2;
        f7465y = str;
        m18129h();
    }

    /* renamed from: i */
    public static String m18128i() {
        return f7448h;
    }

    /* renamed from: j */
    public static int m18127j() {
        return f7452l;
    }

    /* renamed from: k */
    public static String m18126k() {
        return f7450j;
    }

    /* renamed from: l */
    public static int m18125l() {
        return f7453m;
    }

    /* renamed from: m */
    public static String m18124m() {
        return f7449i;
    }

    /* renamed from: n */
    public static int m18123n() {
        return f7456p;
    }

    /* renamed from: o */
    public static String m18122o() {
        return f7458r;
    }

    /* renamed from: p */
    public static String m18121p() {
        return f7447g;
    }

    /* renamed from: q */
    public static String m18120q() {
        return f7462v;
    }

    /* renamed from: r */
    public static String m18119r() {
        String str;
        try {
            str = LBSAuthManager.getInstance(f7441a).getCUID();
        } catch (Exception unused) {
            str = "";
        }
        return str == null ? "" : str;
    }
}
