package com.bytedance.sdk.openadsdk.api.plugin;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.AdConfig;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTInitializer;
import com.bytedance.sdk.openadsdk.api.C3972mb;
import com.bytedance.sdk.openadsdk.api.plugin.C4021u;
import com.bytedance.sdk.openadsdk.live.C4059mb;
import com.bytedance.sdk.openadsdk.live.C4063ox;
import com.bytedance.sdk.openadsdk.p186mb.C4071ox;
import dalvik.system.BaseDexClassLoader;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.sdk.openadsdk.api.plugin.ko */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3982ko implements TTInitializer {

    /* renamed from: b */
    private static final Map<String, Bundle> f9516b = new ConcurrentHashMap();

    /* renamed from: mb */
    public static ScheduledExecutorService f9517mb = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryC3985ox());

    /* renamed from: ox */
    private volatile TTInitializer f9518ox;

    /* renamed from: mb */
    public static void m16514mb(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str) || bundle == null) {
            return;
        }
        f9516b.put(str, bundle);
    }

    @Override // com.bytedance.sdk.openadsdk.TTInitializer
    public void init(Context context, AdConfig adConfig, TTAdSdk.InitCallback initCallback) {
        if (Build.VERSION.SDK_INT < 21) {
            initCallback.fail(4201, "Only support >= 5.0");
            return;
        }
        C4021u.m16446mb(context).m16448mb();
        if (this.f9518ox != null) {
            this.f9518ox.init(context, adConfig, new C3984mb(initCallback));
        } else {
            m16517mb(context, adConfig, new C3984mb(initCallback), C3977h.m16535mb("duration"));
        }
    }

    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.ko$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C3984mb implements TTAdSdk.InitCallback {

        /* renamed from: mb */
        private TTAdSdk.InitCallback f9524mb;

        public C3984mb(TTAdSdk.InitCallback initCallback) {
            this.f9524mb = initCallback;
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdSdk.InitCallback
        public void success() {
            TTAdSdk.InitCallback initCallback = this.f9524mb;
            if (initCallback != null) {
                initCallback.success();
            }
            C3978hj.m16528mb();
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdSdk.InitCallback
        public void fail(int i, String str) {
            TTAdSdk.InitCallback initCallback = this.f9524mb;
            if (initCallback != null) {
                initCallback.fail(i, str);
                C3978hj.m16521ox(i, str, 0L);
            }
        }
    }

    @Override // com.bytedance.sdk.openadsdk.TTInitializer
    public boolean isInitSuccess() {
        if (this.f9518ox != null) {
            return this.f9518ox.isInitSuccess();
        }
        return false;
    }

    @Override // com.bytedance.sdk.openadsdk.TTInitializer
    public TTAdManager getAdManager() {
        return C3986mb.f9528mb;
    }

    /* renamed from: mb */
    private void m16517mb(final Context context, final AdConfig adConfig, final TTAdSdk.InitCallback initCallback, final C3977h c3977h) {
        f9517mb.execute(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.ko.1
            @Override // java.lang.Runnable
            public void run() {
                c3977h.m16533ox("wait_asyn_cost");
                TTInitializer m16516mb = C3982ko.this.m16516mb(adConfig, c3977h);
                if (m16516mb != null) {
                    C3986mb.f9528mb.m16511mb(m16516mb.getAdManager());
                    m16516mb.init(context, adConfig, initCallback);
                    m16516mb.getAdManager().register(C4071ox.m16352mb());
                    return;
                }
                initCallback.fail(4201, "No initializer");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public TTInitializer m16516mb(AdConfig adConfig, C3977h c3977h) {
        if (this.f9518ox == null) {
            synchronized (this) {
                if (this.f9518ox == null) {
                    C3978hj.m16524mb(adConfig);
                    C3972mb.m16544ox("TTPluginManager", "Create initializer");
                    this.f9518ox = m16512ox(adConfig, c3977h);
                    c3977h.m16536mb();
                    JSONObject jSONObject = new JSONObject();
                    c3977h.m16534mb(jSONObject, 20L);
                    try {
                        jSONObject.put("zeus", C4021u.m16446mb(TTAppContextHolder.getContext()).m16432ox());
                    } catch (JSONException unused) {
                    }
                    adConfig.setExtra("plugin", jSONObject);
                }
            }
        }
        return this.f9518ox;
    }

    /* renamed from: ox */
    private static TTInitializer m16512ox(AdConfig adConfig, C3977h c3977h) {
        C4021u m16446mb;
        BaseDexClassLoader m16442mb;
        try {
            c3977h.m16533ox("call_create_initializer");
            m16446mb = C4021u.m16446mb(TTAppContextHolder.getContext());
            m16442mb = m16446mb.m16442mb(c3977h);
        } catch (Throwable th) {
            if (th instanceof C3976b) {
                C3976b c3976b = (C3976b) th;
                C3978hj.m16527mb(c3976b.m16537mb(), c3976b.getMessage(), 0L);
            } else {
                C3978hj.m16527mb(6, th.getMessage(), 0L);
            }
            C3972mb.m16553h("TTPluginManager", "Create initializer failed: " + th);
        }
        if (m16442mb == null) {
            C3978hj.m16527mb(6, "Load plugin failed", 0L);
            C3972mb.m16553h("TTPluginManager", "Load plugin failed");
            return null;
        }
        Class<?> loadClass = m16442mb.loadClass("com.bytedance.sdk.openadsdk.core.AdSdkInitializerHolder");
        c3977h.m16533ox("get_init_class_cost");
        Bundle bundle = new Bundle();
        bundle.putSerializable("_pl_update_listener_", new C4021u.C4029ox());
        bundle.putSerializable("_pl_update_event_listener_", new C4021u.C4027b());
        Bundle m16513mb = m16513mb(f9516b);
        bundle.putBundle("_pl_config_info_", m16513mb);
        c3977h.m16533ox("create_bundle_cost");
        bundle.putSerializable("_live_sdk_", C4063ox.m16367mb());
        Bundle m16373mb = C4059mb.m16373mb(m16446mb, adConfig.getAppId());
        C4063ox.m16367mb().m16366mb(m16446mb, m16373mb);
        if (m16373mb != null) {
            m16513mb.putBundle("com.byted.live.lite", m16373mb);
        }
        c3977h.m16533ox("live_init_cost");
        Method declaredMethod = loadClass.getDeclaredMethod("getInstance", Bundle.class);
        c3977h.m16533ox("get_init_method_cost");
        TTInitializer tTInitializer = (TTInitializer) declaredMethod.invoke(null, bundle);
        c3977h.m16533ox("get_init_instance_cost");
        C3972mb.m16544ox("TTPluginManager", "Create initializer success");
        return tTInitializer;
    }

    /* renamed from: mb */
    private static final Bundle m16513mb(Map<String, Bundle> map) {
        if (map == null || map.size() == 0) {
            return new Bundle();
        }
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Bundle> entry : map.entrySet()) {
            String key = entry.getKey();
            Bundle value = entry.getValue();
            if (!TextUtils.isEmpty(key) && value != null) {
                bundle.putBundle(key, value);
            }
        }
        return bundle;
    }

    /* renamed from: com.bytedance.sdk.openadsdk.api.plugin.ko$ox */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class ThreadFactoryC3985ox implements ThreadFactory {

        /* renamed from: b */
        private final String f9525b;

        /* renamed from: mb */
        private final ThreadGroup f9526mb;

        /* renamed from: ox */
        private final AtomicInteger f9527ox;

        ThreadFactoryC3985ox() {
            this.f9527ox = new AtomicInteger(1);
            this.f9526mb = new ThreadGroup("csj_g_pl_init");
            this.f9525b = "csj_pl_init";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ThreadFactoryC3985ox(String str) {
            this.f9527ox = new AtomicInteger(1);
            this.f9526mb = new ThreadGroup("csj_g_pl_init");
            this.f9525b = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.f9526mb;
            Thread thread = new Thread(threadGroup, runnable, this.f9525b + this.f9527ox.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 10) {
                thread.setPriority(10);
            }
            return thread;
        }
    }
}
