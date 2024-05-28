package com.bytedance.sdk.openadsdk.live;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.android.live.base.api.ILiveHostContextParam;
import com.bytedance.android.live.base.api.ILiveInitCallback;
import com.bytedance.android.live.base.api.IOuterLiveService;
import com.bytedance.android.openliveplugin.LivePluginHelper;
import com.bytedance.pangle.Zeus;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTPluginListener;
import com.bytedance.sdk.openadsdk.api.plugin.C4021u;
import com.bytedance.sdk.openadsdk.p187ox.C4074mb;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.sdk.openadsdk.live.mb */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4059mb {

    /* renamed from: mb */
    private static SharedPreferences f9663mb;

    /* renamed from: mb */
    public static Bundle m16373mb(C4021u c4021u, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("app_id", str);
        String m16438mb = C4021u.m16438mb("com.byted.live.lite");
        if (TextUtils.isEmpty(m16438mb)) {
            m16438mb = "0.0.0.0";
        }
        bundle.putString("plugin_version", m16438mb);
        bundle.putString("sdk_version", m16377mb(TTAppContextHolder.getContext()));
        return bundle;
    }

    /* renamed from: mb */
    public static void m16374mb(C4021u c4021u, final Bundle bundle, final TTPluginListener tTPluginListener) {
        c4021u.m16429ox(new TTPluginListener() { // from class: com.bytedance.sdk.openadsdk.live.mb.1
            @Override // com.bytedance.sdk.openadsdk.TTPluginListener
            public String packageName() {
                return "com.byted.live.lite";
            }

            @Override // com.bytedance.sdk.openadsdk.TTPluginListener
            public void onPluginListener(int i, ClassLoader classLoader, Resources resources, Bundle bundle2) {
                TTPluginListener tTPluginListener2 = TTPluginListener.this;
                if (tTPluginListener2 != null) {
                    tTPluginListener2.onPluginListener(i, classLoader, resources, bundle2);
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTPluginListener
            public Bundle config() {
                return bundle;
            }
        });
    }

    /* renamed from: mb */
    public static String m16377mb(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return null;
            }
            String string = applicationInfo.metaData.getString("ZEUS_PLUGIN_LIVE");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return C4021u.m16447mb(new JSONObject(string).optInt("apiVersionCode"));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: mb */
    public static boolean m16375mb(final Context context, final String str, final ILiveHostContextParam.Builder builder, final ILiveInitCallback iLiveInitCallback) {
        try {
            C4074mb.m16346mb().m16345mb(new Runnable() { // from class: com.bytedance.sdk.openadsdk.live.mb.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        LivePluginHelper.init((Application) context, str, builder, iLiveInitCallback);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: mb */
    public static boolean m16379mb() {
        try {
            C4074mb.m16346mb().m16345mb(new Runnable() { // from class: com.bytedance.sdk.openadsdk.live.mb.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        LivePluginHelper.initLiveCommerce();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: mb */
    public static boolean m16376mb(Context context, Bundle bundle) {
        if (context == null || bundle == null || !bundle.containsKey("room_id")) {
            return false;
        }
        long j = bundle.getLong("room_id");
        try {
            IOuterLiveService iOuterLiveService = (IOuterLiveService) LivePluginHelper.getLiveRoomService();
            if (iOuterLiveService != null) {
                iOuterLiveService.enterLiveRoom(context, j, bundle);
                return true;
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: mb */
    public static void m16378mb(long j) {
        if (f9663mb == null) {
            f9663mb = TTAppContextHolder.getContext().getSharedPreferences("csj_live", 0);
        }
        try {
            if (f9663mb != null) {
                SharedPreferences sharedPreferences = f9663mb;
                int i = sharedPreferences.getInt("live_init_" + j, 0);
                SharedPreferences.Editor edit = f9663mb.edit();
                edit.putInt("live_init_" + j, i + 1);
                edit.commit();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: ox */
    public static void m16372ox(long j) {
        if (f9663mb == null) {
            f9663mb = TTAppContextHolder.getContext().getSharedPreferences("csj_live", 0);
        }
        try {
            if (f9663mb != null) {
                SharedPreferences.Editor edit = f9663mb.edit();
                edit.putInt("live_init_" + j, 0);
                edit.commit();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public static boolean m16381b(long j) {
        int i;
        if (f9663mb == null) {
            f9663mb = TTAppContextHolder.getContext().getSharedPreferences("csj_live", 0);
        }
        SharedPreferences sharedPreferences = f9663mb;
        if (sharedPreferences != null) {
            i = sharedPreferences.getInt("live_init_" + j, 0);
        } else {
            i = 0;
        }
        return i < 5;
    }

    /* renamed from: hj */
    public static void m16380hj(long j) {
        try {
            Zeus.unInstallPlugin("com.byted.live.lite");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        m16372ox(j);
    }
}
