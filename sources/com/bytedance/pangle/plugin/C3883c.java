package com.bytedance.pangle.plugin;

import android.content.ComponentCallbacks;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.bytedance.pangle.C3865h;
import com.bytedance.pangle.ComponentManager;
import com.bytedance.pangle.PluginClassLoader;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusApplication;
import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.dex.C3797a;
import com.bytedance.pangle.log.C3870a;
import com.bytedance.pangle.log.C3871b;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p171a.C3769a;
import com.bytedance.pangle.p175c.C3784b;
import com.bytedance.pangle.p176d.C3792c;
import com.bytedance.pangle.p176d.C3794e;
import com.bytedance.pangle.p177e.C3805b;
import com.bytedance.pangle.p177e.C3815g;
import com.bytedance.pangle.provider.ContentProviderManager;
import com.bytedance.pangle.res.PluginResources;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.util.C3950i;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import com.bytedance.pangle.wrapper.PluginApplicationWrapper;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.pangle.plugin.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3883c {

    /* renamed from: a */
    private static final C3865h f9254a = C3865h.m16801a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m16762a(String str) {
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        if (plugin == null) {
            ZeusLogger.m16788w("Zeus/load_pangle", "PluginLoader loadPlugin, plugin == null, pkg = ".concat(String.valueOf(str)));
            return false;
        }
        synchronized (plugin) {
            if (!plugin.isInstalled()) {
                ZeusLogger.m16788w("Zeus/load_pangle", "PluginLoader loadPlugin, UN_INSTALLED, ".concat(String.valueOf(str)));
                return false;
            } else if (plugin.isLoaded()) {
                return true;
            } else {
                f9254a.m16800a(2000, 0, plugin.mPkgName, plugin.getVersion(), null);
                C3870a m16784a = C3870a.m16784a("Zeus/load_pangle", "PluginLoader", "loadPlugin:".concat(String.valueOf(str)));
                m16761a(C3784b.f9041g, C3784b.C3785a.f9072z, plugin.mPkgName, plugin.getVersion(), -1L, (String) null);
                ZeusPluginStateListener.postStateChange(str, 8, new Object[0]);
                StringBuilder sb = new StringBuilder();
                boolean m16760a = m16760a(str, plugin, sb);
                m16784a.m16785a("loadPluginInternal:".concat(String.valueOf(m16760a)));
                if (m16760a) {
                    plugin.setLifeCycle(3);
                    m16761a(C3784b.f9042h, C3784b.C3785a.f9045A, plugin.mPkgName, plugin.getVersion(), m16784a.m16786a(), sb.toString());
                    ZeusPluginStateListener.postStateChange(str, 9, new Object[0]);
                    f9254a.m16800a(2100, 0, plugin.mPkgName, plugin.getVersion(), null);
                } else {
                    sb.append("plugin:");
                    sb.append(plugin.mPkgName);
                    sb.append(" versionCode:");
                    sb.append(plugin.getVersion());
                    sb.append("load failed;");
                    m16761a(C3784b.f9042h, C3784b.C3785a.f9046B, plugin.mPkgName, plugin.getVersion(), -1L, sb.toString());
                    ZeusPluginStateListener.postStateChange(str, 10, new Object[0]);
                    f9254a.m16800a(2100, -1, plugin.mPkgName, plugin.getVersion(), null);
                }
                ZeusLogger.m16792i("Zeus/load_pangle", "PluginLoader loadFinished, ".concat(String.valueOf(plugin)));
                if (plugin.isLoaded()) {
                    ZeusLogger.m16794d("Zeus/load_pangle", "PluginLoader postResult, LOADED " + plugin.mPkgName);
                    return true;
                }
                return false;
            }
        }
    }

    /* renamed from: a */
    private boolean m16760a(final String str, final Plugin plugin, final StringBuilder sb) {
        final File file;
        try {
            if (plugin == null) {
                sb.append("loadPluginInternal, plugin == null;");
                ZeusLogger.m16788w("Zeus/load_pangle", "PluginLoader loadPluginInternal, plugin[" + str + "] not exist !!!");
                return false;
            } else if (!plugin.isInstalled()) {
                sb.append("loadPluginInternal, !plugin.isInstalled();");
                ZeusLogger.m16788w("Zeus/load_pangle", "PluginLoader loadPluginInternal, plugin[" + str + "] not installed !!!");
                return false;
            } else {
                final String m16934b = C3792c.m16934b(plugin.mPkgName, plugin.getVersion());
                if (!new File(m16934b).exists()) {
                    sb.append("loadPluginInternal, sourceApk not exist;");
                    ZeusLogger.m16788w("Zeus/load_pangle", "PluginLoader loadPluginInternal, plugin[" + str + "] file not exist !!!");
                    return false;
                }
                final File file2 = new File(C3792c.m16930d(plugin.mPkgName, plugin.getVersion()));
                File file3 = new File(C3792c.m16932c(plugin.mPkgName, plugin.getVersion()));
                if (C3950i.m16620d()) {
                    if (!C3805b.m16901a(file3 + File.separator + C3805b.m16905a(m16934b))) {
                        file3 = null;
                    }
                    file = file3;
                } else {
                    file = file3;
                }
                if (file != null && !file.exists()) {
                    file.mkdirs();
                }
                final PackageInfo[] packageInfoArr = new PackageInfo[1];
                if (C3950i.m16614j()) {
                    C3769a.m16982a(new C3769a.InterfaceC3771a() { // from class: com.bytedance.pangle.plugin.c.1
                        @Override // com.bytedance.pangle.p171a.C3769a.InterfaceC3771a
                        /* renamed from: a */
                        public final void mo16758a() {
                            C3883c.this.m16767a(plugin, m16934b, file2, file, sb);
                        }
                    }, new C3769a.InterfaceC3771a() { // from class: com.bytedance.pangle.plugin.c.2
                        @Override // com.bytedance.pangle.p171a.C3769a.InterfaceC3771a
                        /* renamed from: a */
                        public final void mo16758a() {
                            packageInfoArr[0] = C3883c.this.m16759a(str, plugin, sb, m16934b, file2);
                        }
                    });
                } else {
                    m16767a(plugin, m16934b, file2, file, sb);
                    packageInfoArr[0] = m16759a(str, plugin, sb, m16934b, file2);
                }
                m16766a(plugin, sb, packageInfoArr[0]);
                return true;
            }
        } catch (Throwable th) {
            sb.append("loadPluginInternal ");
            sb.append(th.getMessage());
            sb.append(";");
            ZeusLogger.m16787w("Zeus/load_pangle", "PluginLoader loadPluginInternal, plugin[" + str + "] ", th);
            return false;
        }
    }

    /* renamed from: a */
    private static void m16766a(Plugin plugin, StringBuilder sb, PackageInfo packageInfo) {
        ActivityInfo[] activityInfoArr = packageInfo.activities;
        if (activityInfoArr != null) {
            for (ActivityInfo activityInfo : activityInfoArr) {
                if (TextUtils.isEmpty(activityInfo.processName) || !activityInfo.processName.contains(":")) {
                    activityInfo.processName = "main";
                } else {
                    activityInfo.processName = activityInfo.processName.split(":")[1];
                }
                plugin.pluginActivities.put(activityInfo.name, activityInfo);
            }
        }
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (TextUtils.isEmpty(serviceInfo.processName) || !serviceInfo.processName.contains(":")) {
                    serviceInfo.processName = "main";
                } else {
                    serviceInfo.processName = serviceInfo.processName.split(":")[1];
                }
                plugin.pluginServices.put(serviceInfo.name, serviceInfo);
            }
        }
        ActivityInfo[] activityInfoArr2 = packageInfo.receivers;
        if (activityInfoArr2 != null) {
            for (ActivityInfo activityInfo2 : activityInfoArr2) {
                if (TextUtils.isEmpty(activityInfo2.processName) || !activityInfo2.processName.contains(":")) {
                    activityInfo2.processName = "main";
                } else {
                    activityInfo2.processName = activityInfo2.processName.split(":")[1];
                }
                plugin.pluginReceiver.put(activityInfo2.name, activityInfo2);
            }
        }
        ProviderInfo[] providerInfoArr = packageInfo.providers;
        if (providerInfoArr != null) {
            for (ProviderInfo providerInfo : providerInfoArr) {
                if (TextUtils.isEmpty(providerInfo.processName) || !providerInfo.processName.contains(":")) {
                    providerInfo.processName = "main";
                } else {
                    providerInfo.processName = providerInfo.processName.split(":")[1];
                }
                plugin.pluginProvider.put(providerInfo.name, providerInfo);
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (plugin.pluginProvider != null && plugin.pluginProvider.size() > 0) {
            ContentProviderManager.getInstance().installContentProviders(plugin.pluginProvider.values(), plugin);
        }
        sb.append("installProvider cost:");
        sb.append(System.currentTimeMillis() - currentTimeMillis);
        sb.append(";");
        long currentTimeMillis2 = System.currentTimeMillis();
        if (!TextUtils.isEmpty(packageInfo.applicationInfo.className)) {
            plugin.mApplication = (ZeusApplication) plugin.mClassLoader.loadClass(packageInfo.applicationInfo.className).newInstance();
            plugin.mApplication.attach(plugin, Zeus.getAppApplication());
        }
        sb.append("makeApplication cost:");
        sb.append(System.currentTimeMillis() - currentTimeMillis2);
        sb.append(";");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public PackageInfo m16759a(String str, final Plugin plugin, StringBuilder sb, String str2, File file) {
        long currentTimeMillis = System.currentTimeMillis();
        PackageInfo packageArchiveInfo = Zeus.getAppApplication().getPackageManager().getPackageArchiveInfo(str2, 143);
        plugin.mHostApplication = (PluginApplicationWrapper) ZeusTransformUtils.wrapperContext2Application(Zeus.getAppApplication(), plugin.mPkgName);
        plugin.mHostApplicationInfoHookSomeField = new ApplicationInfo(Zeus.getAppApplication().getApplicationInfo());
        plugin.mHostApplicationInfoHookSomeField.nativeLibraryDir = file.getAbsolutePath();
        plugin.mHostApplicationInfoHookSomeField.dataDir = plugin.mHostApplication.getDataDir().getAbsolutePath();
        plugin.mHostApplicationInfoHookSomeField.sourceDir = str2;
        if (TextUtils.isEmpty(packageArchiveInfo.applicationInfo.sourceDir)) {
            packageArchiveInfo.applicationInfo.sourceDir = str2;
        }
        if (TextUtils.isEmpty(packageArchiveInfo.applicationInfo.publicSourceDir)) {
            packageArchiveInfo.applicationInfo.publicSourceDir = str2;
        }
        plugin.mResources = new PluginResources(Zeus.getAppApplication().getPackageManager().getResourcesForApplication(packageArchiveInfo.applicationInfo), str);
        Zeus.getAppApplication().registerComponentCallbacks(new ComponentCallbacks() { // from class: com.bytedance.pangle.plugin.c.3
            @Override // android.content.ComponentCallbacks
            public final void onLowMemory() {
            }

            @Override // android.content.ComponentCallbacks
            public final void onConfigurationChanged(Configuration configuration) {
                plugin.mResources.updateConfiguration(configuration, Zeus.getAppApplication().getResources().getDisplayMetrics());
            }
        });
        sb.append("makeResources cost:");
        sb.append(System.currentTimeMillis() - currentTimeMillis);
        sb.append(";");
        return packageArchiveInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m16767a(final Plugin plugin, String str, File file, File file2, StringBuilder sb) {
        long currentTimeMillis = System.currentTimeMillis();
        m16768a(plugin, str, file, file2);
        sb.append("classLoader cost:");
        sb.append(System.currentTimeMillis() - currentTimeMillis);
        sb.append(" ;");
        if (plugin.mOpenLoadClassOpt) {
            C3794e.m16918a(new Runnable() { // from class: com.bytedance.pangle.plugin.c.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        plugin.mClassLoader.setAllPluginClasses((HashSet) MethodUtils.invokeStaticMethod(plugin.mClassLoader.loadClass("com.volcengine.PluginClassHolder"), "getPluginClasses", new Object[0]));
                    } catch (Throwable unused) {
                    }
                }
            });
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        try {
            String str2 = (String) FieldUtils.readStaticField(plugin.mClassLoader.loadClass("com.volcengine.StubConfig"), "actStubV1");
            if (str2 != null) {
                m16769a(plugin, str2);
            }
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            sb.append("actStubV1 cost:");
            sb.append(System.currentTimeMillis() - currentTimeMillis2);
            sb.append(";");
            throw th;
        }
        sb.append("actStubV1 cost:");
        sb.append(System.currentTimeMillis() - currentTimeMillis2);
        sb.append(";");
    }

    /* renamed from: a */
    private static void m16769a(Plugin plugin, String str) {
        JSONObject optJSONObject;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject(str);
        JSONObject optJSONObject2 = jSONObject.optJSONObject("mapping");
        HashMap hashMap = new HashMap();
        if (optJSONObject2 != null) {
            Iterator<String> keys = optJSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, optJSONObject2.getString(next));
            }
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("forceMappings");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                int optInt = jSONObject2.optInt("minApi", 0);
                int optInt2 = jSONObject2.optInt("maxApi", Integer.MAX_VALUE);
                int apiVersionCode = plugin.getApiVersionCode();
                if (apiVersionCode <= optInt2 && apiVersionCode >= optInt && (optJSONObject = jSONObject2.optJSONObject("mapping")) != null) {
                    Iterator<String> keys2 = optJSONObject.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        hashMap.put(next2, optJSONObject.getString(next2));
                    }
                }
            }
        }
        for (String str2 : hashMap.keySet()) {
            String str3 = (String) hashMap.get(str2);
            String str4 = plugin.mPkgName;
            StringBuilder sb = new StringBuilder();
            sb.append((str3 == null || !str3.contains(".")) ? plugin.mPkgName + "." : "");
            sb.append((String) hashMap.get(str2));
            ComponentManager.registerActivity(str4, sb.toString(), str2);
        }
    }

    /* renamed from: a */
    private static void m16761a(String str, int i, @NonNull String str2, int i2, long j, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt("status_code", C3871b.m16783a(Integer.valueOf(i)));
            jSONObject.putOpt("plugin_package_name", C3871b.m16783a(str2));
            jSONObject.putOpt("version_code", C3871b.m16783a(Integer.valueOf(i2)));
            jSONObject3.putOpt("duration", Integer.valueOf(C3871b.m16782b(Long.valueOf(j))));
            jSONObject2.putOpt("message", C3871b.m16783a(str3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        C3784b.m16961a().m16960a(str, jSONObject, jSONObject3, jSONObject2);
    }

    /* renamed from: a */
    private static void m16763a(Object obj, String str) {
        try {
            MethodUtils.getAccessibleMethod(BaseDexClassLoader.class, "addDexPath", String.class).invoke(obj, str);
            ZeusLogger.m16792i("Zeus/load_pangle", "PluginLoader createPluginClassLoader#addDexPath success >>>".concat(String.valueOf(str)));
        } catch (Throwable th) {
            ZeusLogger.errReport("Zeus/load_pangle", "PluginLoader createPluginClassLoader#addDexPath fail >>>".concat(String.valueOf(str)), th);
        }
    }

    /* renamed from: a */
    private static void m16768a(Plugin plugin, String str, File file, File file2) {
        if (C3950i.m16614j()) {
            plugin.mClassLoader = new PluginClassLoader("", file2, file.getAbsolutePath(), null);
            m16763a(plugin.mClassLoader, str);
        } else if (C3950i.m16622b()) {
            String m16887a = C3815g.m16887a(plugin.mPkgName, plugin.getVersion());
            String[] split = m16887a.split(":");
            long currentTimeMillis = System.currentTimeMillis();
            boolean z = !C3805b.m16902a(file2.getAbsolutePath(), split);
            ZeusLogger.m16794d("Zeus/load_pangle", "useDirect:" + (System.currentTimeMillis() - currentTimeMillis) + " " + z);
            if (z) {
                m16887a = "";
            }
            plugin.mClassLoader = new PluginClassLoader(m16887a, file2, file.getAbsolutePath(), null);
            if (z) {
                C3797a.m16915a(plugin.mClassLoader, plugin.mPkgName, plugin.getVersion());
            }
        } else {
            plugin.mClassLoader = new PluginClassLoader(str, file2, file.getAbsolutePath(), null);
        }
    }
}
