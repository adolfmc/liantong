package com.bytedance.pangle.plugin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.bytedance.pangle.C3837g;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3792c;
import com.bytedance.pangle.p176d.C3793d;
import com.bytedance.pangle.p176d.C3794e;
import com.bytedance.pangle.util.C3942b;
import com.bytedance.pangle.util.C3953l;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PluginManager {
    private static final String TAG = "PluginManager";
    private static volatile PluginManager sInstance;
    private volatile boolean hasInstallFromDownloadDir;
    private ExecutorService mInstallThreadPool;
    private volatile boolean mIsParsePluginConfig;
    private volatile Map<String, Plugin> mPlugins = new ConcurrentHashMap();
    private final C3883c pluginLoader = new C3883c();

    public static PluginManager getInstance() {
        if (sInstance == null) {
            synchronized (PluginManager.class) {
                if (sInstance == null) {
                    sInstance = new PluginManager();
                }
            }
        }
        return sInstance;
    }

    private PluginManager() {
    }

    public boolean loadPlugin(String str) {
        return this.pluginLoader.m16762a(str);
    }

    public ExecutorService getInstallThreadPool() {
        if (this.mInstallThreadPool == null) {
            this.mInstallThreadPool = C3794e.m16919a(GlobalParam.getInstance().getInstallThreads());
        }
        return this.mInstallThreadPool;
    }

    public Plugin getPlugin(String str, boolean z) {
        if (!Zeus.hasInit() && C3942b.m16647a()) {
            throw new RuntimeException("Please init Zeus first!");
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!this.mIsParsePluginConfig) {
            parsePluginConfig();
        }
        Plugin plugin = this.mPlugins.get(str);
        if (!z || plugin == null) {
            return plugin;
        }
        plugin.init();
        return plugin;
    }

    public Plugin getPlugin(String str) {
        return getPlugin(str, true);
    }

    private synchronized void parsePluginConfig() {
        if (this.mIsParsePluginConfig) {
            return;
        }
        ZeusLogger.m16790v("Zeus/init_pangle", "PluginManager parsePluginsJson");
        ArrayList<String> arrayList = new ArrayList();
        try {
            Bundle bundle = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 128).applicationInfo.metaData;
            for (String str : bundle.keySet()) {
                if (C3837g.f9176e.startsWith("PANGLE_")) {
                    if (str.startsWith(C3837g.f9176e) || str.startsWith("ZEUS_PLUGIN_")) {
                        arrayList.add(bundle.getString(str));
                    }
                } else if (str.startsWith(C3837g.f9176e)) {
                    arrayList.add(bundle.getString(str));
                }
            }
            try {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                for (String str2 : arrayList) {
                    try {
                        Plugin plugin = new Plugin(new JSONObject(str2));
                        concurrentHashMap.put(plugin.mPkgName, plugin);
                        ZeusLogger.m16792i("Zeus/init_pangle", "PluginManagerparsePluginsJson. find " + plugin.mPkgName);
                    } catch (JSONException e) {
                        ZeusLogger.errReport("Zeus/init_pangle", "PluginManager parsePluginsJson failed. " + str2.trim(), e);
                    }
                }
                this.mPlugins = concurrentHashMap;
                ZeusLogger.m16792i("Zeus/init_pangle", "PluginManager parsePluginsJson success");
            } catch (Exception e2) {
                ZeusLogger.errReport("Zeus/init_pangle", "PluginManager parsePluginsJson failed.", e2);
            }
            this.mIsParsePluginConfig = true;
        } catch (Exception e3) {
            ZeusLogger.errReport("Zeus/init_pangle", "PluginManager parsePluginsJson failed.", e3);
        }
    }

    public boolean checkPluginInstalled(String str) {
        Plugin plugin = getPlugin(str);
        ensurePluginFileExist(plugin);
        boolean z = plugin != null && plugin.isInstalled();
        ZeusLogger.m16794d("Zeus/ppm_pangle", "PluginManager checkPluginInstalled, " + str + " = " + z);
        return z;
    }

    private void ensurePluginFileExist(Plugin plugin) {
        if (plugin == null || !plugin.isInstalled() || new File(C3792c.m16934b(plugin.mPkgName, plugin.getVersion())).exists()) {
            return;
        }
        unInstallPackage(plugin.mPkgName);
    }

    public boolean isLoaded(String str) {
        Plugin plugin = getPlugin(str);
        return plugin != null && plugin.isLoaded();
    }

    public void unInstallPackage(String str) {
        ZeusLogger.m16794d("Zeus/ppm_pangle", "PluginManager unInstallPackage, ".concat(String.valueOf(str)));
        if (getPlugin(str) != null) {
            SharedPreferences.Editor edit = C3953l.m16606a().f9379a.edit();
            edit.putBoolean("UNINSTALL__".concat(String.valueOf(str)), true);
            edit.apply();
            ZeusLogger.m16792i("Zeus/init_pangle", "ZeusSpUtils markUnInstallFlag packageName=".concat(String.valueOf(str)));
        }
    }

    public void setAllowDownloadPlugin(String str, int i, boolean z) {
        ZeusLogger.m16794d("Zeus/ppm_pangle", "PluginManager setAllowDownloadPlugin, " + str + " " + i + " " + z);
        if (getPlugin(str) != null) {
            boolean z2 = !z;
            SharedPreferences.Editor edit = C3953l.m16606a().f9379a.edit();
            String str2 = "DISABLE_DOWNLOAD_" + str + "_" + i;
            if (z2) {
                edit.putInt(str2, 0);
            } else {
                edit.remove(str2);
            }
            edit.apply();
            ZeusLogger.m16792i("Zeus/init_pangle", "ZeusSpUtils markAllowDownloadFlag packageName=" + str + " version=" + i + " disable=" + z2);
        }
    }

    public void tryOfflineInternalPlugin(String str, int i) {
        Plugin plugin = getPlugin(str);
        if (plugin == null || plugin.getInternalVersionCode() != i) {
            return;
        }
        ZeusLogger.m16794d("Zeus/ppm_pangle", "PluginManager offlineInternalPlugin, pkgName:" + str + " pluginVer:" + i + " apiVer:" + plugin.getApiVersionCode());
        C3953l m16606a = C3953l.m16606a();
        int apiVersionCode = plugin.getApiVersionCode();
        SharedPreferences.Editor edit = m16606a.f9379a.edit();
        edit.putInt("OFFLINE_INTERNAL_".concat(String.valueOf(str)), apiVersionCode);
        edit.apply();
    }

    public boolean syncInstall(String str, File file) {
        ZeusLogger.m16792i("Zeus/install_pangle", "PluginManager syncInstall, file=".concat(String.valueOf(file)));
        return new RunnableC3876a(str, file).m16781a();
    }

    public void asyncInstall(String str, File file) {
        if (file != null) {
            getInstallThreadPool().execute(new RunnableC3876a(str, file));
            ZeusLogger.m16792i("Zeus/install_pangle", "PluginManager asyncInstall, file=".concat(String.valueOf(file)));
            return;
        }
        ZeusPluginStateListener.postStateChange(str, 7, "asyncInstall apk is null !");
        ZeusLogger.m16788w("Zeus/install_pangle", "PluginManager asyncInstall apk is null !");
    }

    public synchronized void installFromDownloadDir() {
        if (this.hasInstallFromDownloadDir) {
            ZeusLogger.m16788w("Zeus/init_pangle", "PluginManager zeus has been installFromDownloadDir!");
            return;
        }
        if (C3793d.m16923a(Zeus.getAppApplication())) {
            C3794e.m16918a(new RunnableC3888d());
        }
        this.hasInstallFromDownloadDir = true;
    }
}
