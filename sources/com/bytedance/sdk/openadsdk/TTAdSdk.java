package com.bytedance.sdk.openadsdk;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.TTCodeGroupRit;
import com.bytedance.sdk.openadsdk.api.C3972mb;
import com.bytedance.sdk.openadsdk.api.plugin.C3982ko;
import com.bytedance.sdk.openadsdk.live.C4063ox;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class TTAdSdk {
    public static final String INITIALIZER_CLASS_NAME = "com.bytedance.sdk.openadsdk.core.AdSdkInitializerHolder";

    /* renamed from: mb */
    private static final TTInitializer f9481mb = new C3982ko();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InitCallback {
        void fail(int i, String str);

        void success();
    }

    public static boolean isInitSuccess() {
        TTInitializer tTInitializer = f9481mb;
        if (tTInitializer != null) {
            return tTInitializer.isInitSuccess();
        }
        return false;
    }

    public static void init(Context context, TTAdConfig tTAdConfig, InitCallback initCallback) {
        m16574mb(context, tTAdConfig);
        Context applicationContext = context.getApplicationContext();
        TTInitializer tTInitializer = f9481mb;
        if (tTInitializer == null) {
            initCallback.fail(4100, "Load initializer failed");
        } else {
            tTInitializer.init(applicationContext, tTAdConfig, initCallback);
        }
    }

    /* renamed from: mb */
    private static void m16574mb(Context context, TTAdConfig tTAdConfig) {
        if (tTAdConfig != null && tTAdConfig.isDebug()) {
            C3972mb.m16551mb();
        }
        if (Looper.getMainLooper() != Looper.myLooper()) {
            C3972mb.m16549mb("Wrong Thread ! Please exec TTAdSdk.init in main thread.");
        }
        m16573mb(context, "Context is null, please check.");
        m16573mb(tTAdConfig, "TTAdConfig is null, please check.");
        TTAppContextHolder.setContext(context);
        updateConfigAuth(tTAdConfig);
        tTAdConfig.setExtra("_pangle_init_start_time", Long.valueOf(SystemClock.elapsedRealtime()));
        tTAdConfig.setExtra("_s_c", "main");
        tTAdConfig.setExtra("_l_s", true);
        tTAdConfig.setExtra("_ext_api_code", 999);
    }

    public static TTAdManager getAdManager() {
        TTInitializer tTInitializer = f9481mb;
        if (tTInitializer != null) {
            return tTInitializer.getAdManager();
        }
        return null;
    }

    public static void updateAdConfig(TTAdConfig tTAdConfig) {
        TTAdManager adManager;
        if (tTAdConfig == null || (adManager = f9481mb.getAdManager()) == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(tTAdConfig.getData())) {
            bundle.putString("extra_data", tTAdConfig.getData());
        }
        if (!TextUtils.isEmpty(tTAdConfig.getKeywords())) {
            bundle.putString("keywords", tTAdConfig.getKeywords());
        }
        if (bundle.keySet().isEmpty()) {
            return;
        }
        adManager.getExtra(AdConfig.class, bundle);
    }

    public static void updateConfigAuth(TTAdConfig tTAdConfig) {
        C4063ox m16367mb;
        if (tTAdConfig == null || (m16367mb = C4063ox.m16367mb()) == null) {
            return;
        }
        m16367mb.m16365mb(tTAdConfig.getInjectionAuth());
    }

    public static void updatePaid(boolean z) {
        TTAdManager adManager = f9481mb.getAdManager();
        if (adManager == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_paid", z);
        if (bundle.keySet().isEmpty()) {
            return;
        }
        adManager.getExtra(AdConfig.class, bundle);
    }

    public static void getCodeGroupRit(final long j, final TTCodeGroupRit.TTCodeGroupRitListener tTCodeGroupRitListener) {
        TTInitializer tTInitializer = f9481mb;
        if (tTInitializer != null) {
            tTInitializer.getAdManager().register(new CodeGroupRitObject() { // from class: com.bytedance.sdk.openadsdk.TTAdSdk.1
                @Override // com.bytedance.sdk.openadsdk.CodeGroupRitObject
                public long getCodeGroupId() {
                    return j;
                }

                @Override // com.bytedance.sdk.openadsdk.CodeGroupRitObject
                public TTCodeGroupRit.TTCodeGroupRitListener getListener() {
                    return tTCodeGroupRitListener;
                }
            });
        } else if (tTCodeGroupRitListener != null) {
            tTCodeGroupRitListener.onFail(4100, "please init sdk first!");
        }
    }

    /* renamed from: mb */
    private static void m16573mb(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static boolean isOnePointFiveAdType(TTNativeExpressAd tTNativeExpressAd) {
        Map<String, Object> mediaExtraInfo;
        if (tTNativeExpressAd == null) {
            mediaExtraInfo = null;
        } else {
            try {
                mediaExtraInfo = tTNativeExpressAd.getMediaExtraInfo();
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        if (mediaExtraInfo == null || !mediaExtraInfo.containsKey("_tt_ad_type_onepointfive")) {
            return false;
        }
        return ((Boolean) mediaExtraInfo.get("_tt_ad_type_onepointfive")).booleanValue();
    }
}
