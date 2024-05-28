package com.p281qq.p282e.comm.managers.setting;

import android.text.TextUtils;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.managers.setting.GlobalSetting */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class GlobalSetting {
    public static final String ADMOB_SDK_WRAPPER = "ADMOB";
    public static final String AGREE_PRIVACY_KEY = "agree_privacy";
    public static final String AGREE_READ_AAID = "allow_read_aaid";
    public static final String APPLOVIN_SDK_WRAPPER = "APPLOVIN";
    public static final String BD_SDK_WRAPPER = "BD";
    public static final String CCPA = "ccpa";
    public static final String COPPA = "coppa";
    public static final String FACEBOOK_SDK_WRAPPER = "FACEBOOK";
    public static final String GDPR = "gdpr";
    public static final String KS_SDK_WRAPPER = "KS";
    public static final String OVERSEA_PRIVACY_INFO = "oversea_privacy_info";
    public static final String PAG_SDK_WRAPPER = "PAG";
    public static final String TT_SDK_WRAPPER = "TT";

    /* renamed from: a */
    private static volatile Integer f17958a = null;

    /* renamed from: b */
    private static volatile boolean f17959b = false;

    /* renamed from: c */
    private static volatile boolean f17960c = true;

    /* renamed from: d */
    private static volatile Integer f17961d;

    /* renamed from: e */
    private static volatile Boolean f17962e;

    /* renamed from: f */
    private static volatile Boolean f17963f;

    /* renamed from: g */
    private static volatile Boolean f17964g;

    /* renamed from: h */
    private static volatile Map<String, String> f17965h = new HashMap();

    /* renamed from: i */
    private static volatile Map<String, String> f17966i = new HashMap();

    /* renamed from: j */
    private static final Map<String, String> f17967j = new HashMap();

    /* renamed from: k */
    private static final JSONObject f17968k = new JSONObject();

    /* renamed from: l */
    private static volatile String f17969l = null;

    /* renamed from: m */
    private static volatile String f17970m = null;

    /* renamed from: n */
    private static volatile String f17971n = null;

    /* renamed from: o */
    private static volatile String f17972o = null;

    /* renamed from: p */
    private static volatile String f17973p = null;

    public static Boolean getAgreeReadAndroidId() {
        return f17964g;
    }

    public static Boolean getAgreeReadDeviceId() {
        return f17963f;
    }

    public static Integer getChannel() {
        return f17958a;
    }

    public static String getCustomADActivityClassName() {
        return f17969l;
    }

    public static String getCustomLandscapeActivityClassName() {
        return f17972o;
    }

    public static String getCustomPortraitActivityClassName() {
        return f17970m;
    }

    public static String getCustomRewardvideoLandscapeActivityClassName() {
        return f17973p;
    }

    public static String getCustomRewardvideoPortraitActivityClassName() {
        return f17971n;
    }

    public static Map<String, String> getExtraUserData() {
        return Collections.unmodifiableMap(f17965h);
    }

    public static Integer getPersonalizedState() {
        return f17961d;
    }

    public static Map<String, String> getPreloadAdapterMaps() {
        return f17967j;
    }

    public static JSONObject getSettings() {
        return f17968k;
    }

    public static boolean isAgreePrivacyStrategy() {
        return f17962e == null || f17962e.booleanValue();
    }

    public static boolean isAgreeReadAndroidId() {
        if (f17964g == null) {
            return true;
        }
        return f17964g.booleanValue();
    }

    public static boolean isAgreeReadDeviceId() {
        if (f17963f == null) {
            return true;
        }
        return f17963f.booleanValue();
    }

    public static boolean isEnableMediationTool() {
        return f17959b;
    }

    public static boolean isEnableVideoDownloadingCache() {
        return f17960c;
    }

    public static void setAgreePrivacyStrategy(boolean z) {
        if (f17962e == null) {
            f17962e = Boolean.valueOf(z);
        }
    }

    @Deprecated
    public static void setAgreeReadAndroidId(boolean z) {
        f17964g = Boolean.valueOf(z);
    }

    @Deprecated
    public static void setAgreeReadDeviceId(boolean z) {
        f17963f = Boolean.valueOf(z);
    }

    public static void setAgreeReadPrivacyInfo(Map<String, Boolean> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        try {
            f17968k.putOpt("agree_privacy", new JSONObject(map));
        } catch (Exception e) {
            GDTLogger.m8234e("setAgreeReadPrivacyInfo错误：" + e.toString());
        }
    }

    public static void setChannel(int i) {
        if (f17958a == null) {
            f17958a = Integer.valueOf(i);
        }
    }

    public static void setCustomADActivityClassName(String str) {
        f17969l = str;
    }

    public static void setCustomLandscapeActivityClassName(String str) {
        f17972o = str;
    }

    public static void setCustomPortraitActivityClassName(String str) {
        f17970m = str;
    }

    public static void setCustomRewardvideoLandscapeActivityClassName(String str) {
        f17973p = str;
    }

    public static void setCustomRewardvideoPortraitActivityClassName(String str) {
        f17971n = str;
    }

    public static void setEnableCollectAppInstallStatus(boolean z) {
        try {
            f17968k.putOpt("ecais", Boolean.valueOf(z));
        } catch (JSONException unused) {
        }
    }

    public static void setEnableMediationTool(boolean z) {
        f17959b = z;
    }

    public static void setEnableVideoDownloadingCache(boolean z) {
        f17960c = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void setExtraUserData(java.util.Map<java.lang.String, java.lang.String> r3) {
        /*
            if (r3 != 0) goto L3
            return
        L3:
            java.util.Set r0 = r3.entrySet()
            java.util.Iterator r0 = r0.iterator()
        Lb:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L36
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L2f
            java.lang.Object r1 = r1.getValue()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto Lb
        L2f:
            java.lang.String r3 = "参数key和value不能为空！"
            com.p281qq.p282e.comm.util.GDTLogger.m8234e(r3)
            return
        L36:
            com.p281qq.p282e.comm.managers.setting.GlobalSetting.f17965h = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p281qq.p282e.comm.managers.setting.GlobalSetting.setExtraUserData(java.util.Map):void");
    }

    public static void setMediaExtData(Map<String, String> map, boolean z) {
        if (map == null) {
            return;
        }
        if (z) {
            f17966i = new HashMap();
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                f17966i.put(entry.getKey(), entry.getValue());
            }
        }
        try {
            f17968k.putOpt("media_ext", new JSONObject(f17966i));
        } catch (JSONException unused) {
            GDTLogger.m8234e("setMediaExtData失败，请检查");
        }
    }

    public static void setPersonalizedState(int i) {
        f17961d = Integer.valueOf(i);
    }

    public static void setPreloadAdapters(Map<String, String> map) {
        if (map == null) {
            return;
        }
        f17967j.putAll(map);
    }
}
