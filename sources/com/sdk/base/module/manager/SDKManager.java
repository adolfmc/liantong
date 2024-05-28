package com.sdk.base.module.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.Keep;
import com.sdk.base.api.CallBack;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.mobile.manager.login.cucc.UiOauthManager;
import com.sdk.mobile.manager.oauth.cucc.OauthManager;
import com.sdk.p285a.C6954a;
import com.sdk.p285a.C6960d;
import com.sdk.p290f.C6998d;
import com.sdk.p300p.C7028b;
import com.sdk.p300p.C7032f;
import com.sdk.p302r.C7037a;
import com.sdk.p303s.C7038a;
import com.sdk.p304t.C7039a;
import com.sdk.p308x.C7048a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class SDKManager {
    public static final String ALGO_A = "A";
    public static final String ALGO_B_AES_SHA256_RSA = "B";
    public static final String ALGO_C_RFU = "C";
    public static final String ALGO_D_RFU = "D";
    public static final String ALGO_E_SM4_SM3_SM2 = "E";
    public static final String HASH_FINGER_MD5 = "MD5";
    public static final String HASH_FINGER_SM3 = "SM3";
    public static Context mContext = null;
    private static String statisticalTestHost = "";
    public static String substring = null;
    private static boolean support_GM = true;
    private static String testHost = "";
    private static boolean useCache = true;
    private static String userAgent;

    public static Context getContext() {
        return mContext;
    }

    public static boolean getInitFlag() {
        return C7037a.m8130a(C7039a.f18201c).booleanValue();
    }

    public static String getStatisticalTestHost() {
        return statisticalTestHost;
    }

    public static String getTestHost() {
        return testHost;
    }

    public static String get_MobileHandlerTime() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n最大时长 = ");
        String str = C7048a.f18237h;
        sb.append(0L);
        sb.append("\n最小时长 = ");
        sb.append(0L);
        sb.append("\n时长总和 = ");
        sb.append(0L);
        sb.append("\n异步多线程，时长总和不是总时间\n\n时长列表 = ");
        sb.append(C7048a.f18241l);
        return sb.toString();
    }

    @Keep
    public static void init(Context context, String str, String str2) {
        init(context, str, str2, "B", "md5");
    }

    @Deprecated
    public static void init(Context context, String str, String str2, String str3) {
        init(context, str, str2, str3, "md5");
    }

    @Keep
    public static void init(Context context, String str, String str2, String str3, String str4) {
        mContext = context;
        C7039a.m8126a(context).getClass();
        C7039a.f18200b = str2;
        C7039a.f18201c = str;
        C7039a.f18204f = false;
        C7039a.f18202d = str3.toUpperCase();
        C7039a.f18203e = str4;
        setUseCache(true);
        setDebug(false);
        setDebugHost(false);
        setSupport3net(true);
        setSupport_GM(false);
        setSupport_reDirect(true);
        if (C7032f.f18192c != null) {
            C7032f.m8136a().f18193a = null;
            C7032f.f18192c = null;
        }
    }

    public static void init_MobileHandlerTime() {
        String str = C7048a.f18237h;
        C7048a.f18241l = "\n";
    }

    @Keep
    public static void initkey(Context context, String str, String str2) {
        mContext = context;
        C7039a.m8126a(context).getClass();
        C7039a.f18200b = str2;
        C7039a.f18201c = str;
        if (C7032f.f18192c != null) {
            C7032f.m8136a().f18193a = null;
            C7032f.f18192c = null;
        }
    }

    public static boolean isDebug() {
        return C6998d.f18135a;
    }

    public static boolean isDebugHost() {
        return C6998d.f18137c;
    }

    public static boolean isSupport_GM() {
        return support_GM;
    }

    public static boolean isSupport_reDirect() {
        return C6960d.f18022g;
    }

    public static boolean isbSupport3net() {
        return C7039a.f18204f;
    }

    public static void releaseConnect(Context context) {
        String str = C7038a.f18197a;
        C6954a c6954a = new C6954a();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        c6954a.f17984b = connectivityManager;
        if (connectivityManager != null && C6954a.f17982g != null) {
            c6954a.f17984b = null;
            C6954a.f17981f = true;
            C6954a.f17982g = null;
            C6954a.f17980e = null;
            c6954a.f17983a = null;
        }
        C7048a c7048a = UiOauthManager.getInstance(context).getmHandler();
        if (c7048a != null) {
            C7048a.f18239j = null;
            c7048a.f18247f = null;
            UiOauthManager.getInstance(context).setmHandler(null);
        }
        C7048a c7048a2 = OauthManager.getInstance(context).getmHandler();
        if (c7048a2 != null) {
            C7048a.f18239j = null;
            c7048a2.f18247f = null;
            OauthManager.getInstance(context).setmHandler(null);
        }
    }

    @Keep
    public static void setDebug(boolean z) {
        C6998d.f18135a = z;
        LogUtils.setDEBUG(z);
        C7028b.f18185a = false;
    }

    @Keep
    public static void setDebugHost(boolean z) {
        C6998d.f18137c = z;
        if (z) {
            setTestHost("");
        }
    }

    public static void setStatisticalTestHost(String str) {
        statisticalTestHost = str;
    }

    @Keep
    public static void setSupport3net(boolean z) {
        C7039a.f18204f = z;
    }

    @Keep
    public static void setSupport_GM(boolean z) {
        boolean z2;
        String str;
        String str2;
        support_GM = z;
        if (z) {
            z2 = true;
            str = "E";
            str2 = "SM3";
        } else {
            z2 = false;
            str = "B";
            str2 = "MD5";
        }
        setSupport_GM(z2, str, str2);
    }

    @Keep
    public static void setSupport_GM(boolean z, String str, String str2) {
        support_GM = z;
        C7039a.f18202d = str.toUpperCase();
        C7039a.f18203e = str2;
    }

    public static void setSupport_reDirect(boolean z) {
        C6960d.f18022g = z;
    }

    public static void setTestHost(String str) {
        testHost = str;
    }

    @Keep
    public static void setUseCache(boolean z) {
        useCache = z;
    }

    public static <T> void toFailed(CallBack<T> callBack, int i, String str) {
        if (callBack != null) {
            callBack.onFailed(1, i, str, null);
        }
    }

    public static boolean useCache() {
        return useCache;
    }

    public static void setDebug(boolean z, boolean z2) {
        C6998d.f18135a = z;
        LogUtils.setDEBUG(z);
        C7028b.f18185a = z2;
    }
}
