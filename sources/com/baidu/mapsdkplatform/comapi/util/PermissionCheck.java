package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import java.util.Hashtable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PermissionCheck {

    /* renamed from: a */
    public static int f7399a = 200;

    /* renamed from: b */
    public static int f7400b = 202;

    /* renamed from: c */
    public static int f7401c = 252;

    /* renamed from: d */
    private static final String f7402d = "PermissionCheck";

    /* renamed from: e */
    private static Context f7403e = null;

    /* renamed from: f */
    private static String f7404f = null;

    /* renamed from: g */
    private static Hashtable<String, String> f7405g = null;

    /* renamed from: h */
    private static LBSAuthManager f7406h = null;

    /* renamed from: i */
    private static LBSAuthManagerListener f7407i = null;

    /* renamed from: j */
    private static InterfaceC2954c f7408j = null;

    /* renamed from: k */
    private static int f7409k = 601;

    /* renamed from: l */
    private static boolean f7410l;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.util.PermissionCheck$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2954c {
        /* renamed from: a */
        void mo18177a(C2953b c2953b);
    }

    public static void setPermissionCheckResultListener(InterfaceC2954c interfaceC2954c) {
        f7408j = interfaceC2954c;
    }

    public static void setPrivacyMode(boolean z) {
        f7410l = z;
        if (z) {
            permissionCheck();
        } else {
            SysOSAPI.m18143a();
        }
    }

    public static void init(Context context) {
        ApplicationInfo applicationInfo;
        f7403e = context;
        try {
            applicationInfo = f7403e.getPackageManager().getApplicationInfo(f7403e.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        if (applicationInfo != null && TextUtils.isEmpty(f7404f)) {
            f7404f = applicationInfo.metaData.getString("com.baidu.lbsapi.API_KEY");
        }
        if (f7405g == null) {
            f7405g = new Hashtable<>();
        }
        if (f7406h == null) {
            f7406h = LBSAuthManager.getInstance(f7403e);
        }
        if (f7407i == null) {
            f7407i = new C2952a();
        }
        String str = "";
        try {
            str = context.getPackageManager().getPackageInfo(f7403e.getPackageName(), 0).applicationInfo.loadLabel(f7403e.getPackageManager()).toString();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            JSONObject jSONObject = new JSONObject(SysOSAPI.m18137c());
            f7405g.put("mb", jSONObject.optString("mb"));
            f7405g.put("os", jSONObject.optString("os"));
            f7405g.put("sv", jSONObject.optString("sv"));
            f7405g.put("imt", "1");
            f7405g.put("net", jSONObject.optString("net"));
            f7405g.put("cpu", jSONObject.optString("cpu"));
            f7405g.put("glr", jSONObject.optString("glr"));
            f7405g.put("glv", jSONObject.optString("glv"));
            f7405g.put("resid", jSONObject.optString("resid"));
            f7405g.put("appid", "-1");
            f7405g.put("ver", "1");
            f7405g.put("screen", String.format("(%d,%d)", Integer.valueOf(jSONObject.optInt("screen_x")), Integer.valueOf(jSONObject.optInt("screen_y"))));
            f7405g.put("dpi", String.format("(%d,%d)", Integer.valueOf(jSONObject.optInt("dpi_x")), Integer.valueOf(jSONObject.optInt("dpi_y"))));
            f7405g.put("pcn", jSONObject.optString("pcn"));
            f7405g.put("cuid", jSONObject.optString("cuid"));
            f7405g.put("name", str);
        } catch (Exception unused) {
        }
    }

    public static synchronized int permissionCheck() {
        synchronized (PermissionCheck.class) {
            if (f7406h != null && f7407i != null && f7403e != null) {
                f7406h.setKey(f7404f);
                int authenticate = f7406h.authenticate(false, "lbs_androidmapsdk", f7405g, f7407i);
                if (authenticate != 0) {
                    String str = f7402d;
                    Log.e(str, "permission check result is: " + authenticate);
                }
                return authenticate;
            }
            String str2 = f7402d;
            Log.e(str2, "The authManager is: " + f7406h + "; the authCallback is: " + f7407i + "; the mContext is: " + f7403e);
            return 0;
        }
    }

    public static void setApiKey(String str) {
        if (str == null || str.trim().length() <= 0) {
            return;
        }
        f7404f = str;
    }

    public static void destory() {
        f7408j = null;
        f7403e = null;
        f7407i = null;
    }

    public static int getPermissionResult() {
        return f7409k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.util.PermissionCheck$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2952a implements LBSAuthManagerListener {
        private C2952a() {
        }

        @Override // com.baidu.lbsapi.auth.LBSAuthManagerListener
        public void onAuthResult(int i, String str) {
            if (str == null) {
                Log.e(PermissionCheck.f7402d, "The result is null");
                int permissionCheck = PermissionCheck.permissionCheck();
                String str2 = PermissionCheck.f7402d;
                Log.d(str2, "onAuthResult try permissionCheck result is: " + permissionCheck);
                return;
            }
            C2953b c2953b = new C2953b();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("status")) {
                    c2953b.f7411a = jSONObject.optInt("status");
                }
                if (jSONObject.has("appid")) {
                    c2953b.f7413c = jSONObject.optString("appid");
                }
                if (jSONObject.has("uid")) {
                    c2953b.f7412b = jSONObject.optString("uid");
                }
                if (jSONObject.has("message")) {
                    c2953b.f7414d = jSONObject.optString("message");
                }
                if (jSONObject.has("token")) {
                    c2953b.f7415e = jSONObject.optString("token");
                }
                if (jSONObject.has("ak_permission")) {
                    c2953b.f7416f = jSONObject.optInt("ak_permission");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int unused = PermissionCheck.f7409k = c2953b.f7411a;
            if (PermissionCheck.f7408j == null || !PermissionCheck.f7410l) {
                return;
            }
            PermissionCheck.f7408j.mo18177a(c2953b);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.util.PermissionCheck$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2953b {

        /* renamed from: a */
        public int f7411a = 0;

        /* renamed from: b */
        public String f7412b = "-1";

        /* renamed from: c */
        public String f7413c = "-1";

        /* renamed from: d */
        public String f7414d = "";

        /* renamed from: e */
        public String f7415e;

        /* renamed from: f */
        public int f7416f;

        public String toString() {
            return String.format("=============================================\n----------------- 鉴权错误信息 ------------\nsha1;package:%s\nkey:%s\nerrorcode: %d uid: %s appid %s msg: %s\n请仔细核查 SHA1、package与key申请信息是否对应，key是否删除，平台是否匹配\nerrorcode为230时，请参考论坛链接：\nhttp://bbs.lbsyun.baidu.com/forum.php?mod=viewthread&tid=106461\n=============================================\n", Cert.m18176a(PermissionCheck.f7403e), PermissionCheck.f7404f, Integer.valueOf(this.f7411a), this.f7412b, this.f7413c, this.f7414d);
        }
    }
}
