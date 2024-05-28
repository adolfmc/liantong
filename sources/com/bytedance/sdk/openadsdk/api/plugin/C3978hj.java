package com.bytedance.sdk.openadsdk.api.plugin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.sdk.openadsdk.AdConfig;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTCustomController;
import com.bytedance.sdk.openadsdk.api.plugin.C3982ko;
import com.bytedance.sdk.openadsdk.api.plugin.p184mb.C4012b;
import com.bytedance.sdk.openadsdk.api.plugin.p185ox.C4020ox;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.bytedance.sdk.openadsdk.api.plugin.hj */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3978hj {

    /* renamed from: b */
    private static SharedPreferences f9507b;

    /* renamed from: ox */
    private static ScheduledExecutorService f9511ox = Executors.newSingleThreadScheduledExecutor(new C3982ko.ThreadFactoryC3985ox("tt_pangle_thread_pl_report"));

    /* renamed from: hj */
    private static final List<Pair<String, JSONObject>> f9509hj = new ArrayList();

    /* renamed from: mb */
    static final Map<String, String> f9510mb = new HashMap();

    /* renamed from: h */
    private static volatile boolean f9508h = false;

    /* renamed from: mb */
    public static void m16526mb(Context context) {
        f9507b = context.getSharedPreferences("tt_sdk_settings_other", 0);
    }

    /* renamed from: mb */
    public static final void m16527mb(int i, String str, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("duration", Long.valueOf(j));
            jSONObject.putOpt("code", Integer.valueOf(i));
            jSONObject.putOpt("message", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m16530h("plugin_load_failed", jSONObject);
    }

    /* renamed from: mb */
    public static void m16523mb(String str, JSONObject jSONObject) {
        m16530h("zeus_" + str, jSONObject);
    }

    /* renamed from: mb */
    public static void m16528mb() {
        if (f9508h) {
            return;
        }
        try {
            f9508h = true;
            f9511ox.shutdown();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: h */
    private static void m16530h(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        TTAdManager adManager = TTAdSdk.getAdManager();
        if (adManager != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("action", 1);
            bundle.putString("event_name", str);
            bundle.putString("event_extra", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            adManager.getExtra(Bundle.class, bundle);
            return;
        }
        m16520ox(str, jSONObject);
    }

    /* renamed from: ox */
    public static final void m16521ox(int i, String str, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("duration", Long.valueOf(j));
            jSONObject.putOpt("code", Integer.valueOf(i));
            jSONObject.putOpt("message", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m16520ox("plugin_load_failed", jSONObject);
    }

    /* renamed from: ox */
    public static void m16520ox(final String str, final JSONObject jSONObject) {
        if (f9508h) {
            return;
        }
        f9511ox.execute(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.hj.1
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(C3978hj.m16518u(str, jSONObject));
                C3978hj.m16531b(arrayList);
            }
        });
    }

    /* renamed from: mb */
    public static void m16522mb(final List<JSONObject> list) {
        if (f9508h) {
            return;
        }
        if (list != null && list.isEmpty() && f9509hj.isEmpty()) {
            return;
        }
        f9511ox.execute(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.hj.2
            @Override // java.lang.Runnable
            public void run() {
                C3978hj.m16531b(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public static JSONObject m16518u(String str, JSONObject jSONObject) {
        String str2;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("os_api", Build.VERSION.SDK_INT);
            jSONObject.put("support_abi", Arrays.toString(Build.VERSION.SDK_INT >= 21 ? Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI, Build.CPU_ABI2}));
            jSONObject2.put("ad_sdk_version", "5.1.0.2");
            String m16438mb = C4021u.m16438mb("com.byted.pangle");
            if (TextUtils.isEmpty(m16438mb)) {
                m16438mb = "5.1.0.2";
            }
            jSONObject2.put("plugin_version", m16438mb);
            jSONObject2.put("timestamp", System.currentTimeMillis() / 1000);
            jSONObject2.put("is_plugin", true);
            if (jSONObject != null) {
                str2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
            } else {
                str2 = "";
            }
            jSONObject2.put("event_extra", str2);
            jSONObject2.put("type", str);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("model", Build.MODEL);
            jSONObject3.put("vendor", Build.MANUFACTURER);
            jSONObject3.put("imei", f9510mb.get("imei"));
            jSONObject3.put("oaid", f9510mb.get("oaid"));
            jSONObject2.put("device_info", jSONObject3);
        } catch (JSONException unused) {
        }
        return jSONObject2;
    }

    /* renamed from: b */
    public static void m16532b(String str, JSONObject jSONObject) {
        f9509hj.add(new Pair<>(str, jSONObject));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m16531b(List<JSONObject> list) {
        if (list == null) {
            return;
        }
        SharedPreferences sharedPreferences = f9507b;
        String format = String.format("https://%s%s", sharedPreferences != null ? sharedPreferences.getString("url_alog", "api-access.pangolin-sdk-toutiao.com") : "api-access.pangolin-sdk-toutiao.com", "/api/ad/union/sdk/stats/batch/");
        JSONObject jSONObject = new JSONObject();
        try {
            if (f9509hj.size() > 0) {
                Iterator<Pair<String, JSONObject>> it = f9509hj.iterator();
                while (it.hasNext()) {
                    Pair<String, JSONObject> next = it.next();
                    list.add(m16518u((String) next.first, (JSONObject) next.second));
                    it.remove();
                }
            }
            jSONObject.put("stats_list", new JSONArray((Collection) list));
        } catch (JSONException unused) {
        }
        JSONObject m16455mb = C4020ox.m16455mb(jSONObject);
        C4012b.m16501mb().m16496mb(true, format, (!(m16455mb instanceof JSONObject) ? m16455mb.toString() : NBSJSONObjectInstrumentation.toString(m16455mb)).getBytes());
    }

    /* renamed from: mb */
    public static void m16525mb(final Bundle bundle) {
        if (f9508h) {
            return;
        }
        f9511ox.execute(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.hj.3
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle2 = bundle;
                if (bundle2 == null) {
                    return;
                }
                try {
                    String string = bundle2.getString("event_name");
                    String string2 = bundle.getString("event_extra");
                    JSONObject jSONObject = TextUtils.isEmpty(string2) ? new JSONObject() : new JSONObject(string2);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(C3978hj.m16518u(string, jSONObject));
                    C3978hj.m16531b(arrayList);
                } catch (Exception unused) {
                }
            }
        });
    }

    /* renamed from: mb */
    public static void m16524mb(AdConfig adConfig) {
        if (adConfig == null) {
            return;
        }
        f9510mb.put("appid", adConfig.getAppId());
        Object extra = adConfig.getExtra("plugin_update_conf");
        if (extra instanceof Integer) {
            String num = ((Integer) extra).toString();
            Map<String, String> map = f9510mb;
            if (num == null) {
                num = "2";
            }
            map.put("plugin_update_conf", num);
        }
        TTCustomController customController = adConfig.getCustomController();
        if (customController != null) {
            try {
                f9510mb.put("oaid", customController.getDevOaid());
                f9510mb.put("imei", customController.getDevImei());
            } catch (Exception unused) {
            }
        }
    }
}
