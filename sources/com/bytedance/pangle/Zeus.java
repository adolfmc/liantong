package com.bytedance.pangle;

import android.app.Application;
import android.content.pm.ProviderInfo;
import android.os.RemoteException;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.apm.ApmUtils;
import com.bytedance.pangle.download.C3798a;
import com.bytedance.pangle.download.C3800b;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3789a;
import com.bytedance.pangle.p176d.C3791b;
import com.bytedance.pangle.p176d.C3793d;
import com.bytedance.pangle.p177e.C3811f;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.res.C3905a;
import com.bytedance.pangle.servermanager.C3924b;
import com.bytedance.pangle.util.C3942b;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Zeus {
    private static Application sApplication;
    private static final HashMap<String, ProviderInfo> serverManagerHashMap = new HashMap<>();
    static final Object wait = new Object();
    private static volatile boolean onPrivacyAgreed = false;

    public static Application getAppApplication() {
        if (sApplication == null) {
            C3776b.m16974a();
            try {
                sApplication = (Application) MethodUtils.invokeMethod(C3789a.m16958a(), "getApplication", new Object[0]);
            } catch (Throwable unused) {
            }
        }
        return sApplication;
    }

    public static void setAppContext(Application application) {
        if (application != null && TextUtils.equals(application.getClass().getSimpleName(), "PluginApplicationWrapper")) {
            try {
                sApplication = (Application) FieldUtils.readField(application, "mOriginApplication");
                return;
            } catch (Throwable unused) {
            }
        }
        sApplication = application;
    }

    public static HashMap<String, ProviderInfo> getServerManagerHashMap() {
        return serverManagerHashMap;
    }

    public static void init(Application application, boolean z) {
        C3865h.m16801a().m16799a(application, z);
        synchronized (wait) {
            wait.notifyAll();
        }
    }

    public static boolean waitInit(int i) {
        if (C3865h.m16801a().f9216a) {
            return true;
        }
        synchronized (wait) {
            if (!C3865h.m16801a().f9216a) {
                try {
                    if (i == -1) {
                        wait.wait();
                    } else {
                        wait.wait(i);
                    }
                } catch (InterruptedException unused) {
                }
            }
        }
        return C3865h.m16801a().f9216a;
    }

    public static synchronized void onPrivacyAgreed() {
        synchronized (Zeus.class) {
            if (onPrivacyAgreed) {
                return;
            }
            ApmUtils.getApmInstance().init();
            onPrivacyAgreed = true;
        }
    }

    public static void installFromDownloadDir() {
        if (C3793d.m16923a(getAppApplication())) {
            PluginManager.getInstance().installFromDownloadDir();
        }
    }

    public static void triggerBgDexOpt() {
        C3811f.m16894a();
    }

    public static boolean hasInit() {
        return C3865h.m16801a().f9216a;
    }

    public static void fetchPlugin(final String str) {
        C3798a m16913a = C3798a.m16913a();
        if (C3793d.m16923a(getAppApplication())) {
            if (GlobalParam.getInstance().autoFetch()) {
                final C3800b m16911a = C3800b.m16911a();
                Runnable runnable = m16911a.f9098c.get(str);
                if (runnable != null) {
                    m16911a.f9097b.removeCallbacks(runnable);
                }
                Runnable runnable2 = new Runnable() { // from class: com.bytedance.pangle.download.b.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (C3942b.m16646a(Zeus.getAppApplication())) {
                            m16911a.f9097b.postDelayed(this, 1800000L);
                        }
                    }
                };
                m16911a.f9098c.put(str, runnable2);
                m16911a.f9097b.postDelayed(runnable2, 1800000L);
                C3800b.m16911a();
                if (m16913a.f9093a.contains(str)) {
                    return;
                }
                m16913a.f9093a.add(str);
                return;
            }
            C3800b.m16911a();
        }
    }

    public static void registerPluginStateListener(ZeusPluginStateListener zeusPluginStateListener) {
        C3865h.m16801a().f9217b.add(zeusPluginStateListener);
    }

    public static void unregisterPluginStateListener(ZeusPluginStateListener zeusPluginStateListener) {
        C3865h m16801a = C3865h.m16801a();
        if (m16801a.f9217b != null) {
            m16801a.f9217b.remove(zeusPluginStateListener);
        }
    }

    public static void addPluginEventCallback(ZeusPluginEventCallback zeusPluginEventCallback) {
        C3865h m16801a = C3865h.m16801a();
        if (zeusPluginEventCallback != null) {
            synchronized (m16801a.f9218c) {
                m16801a.f9218c.add(zeusPluginEventCallback);
            }
        }
    }

    public static void removePluginEventCallback(ZeusPluginEventCallback zeusPluginEventCallback) {
        C3865h m16801a = C3865h.m16801a();
        if (zeusPluginEventCallback != null) {
            synchronized (m16801a.f9218c) {
                m16801a.f9218c.remove(zeusPluginEventCallback);
            }
        }
    }

    public static Plugin getPlugin(String str) {
        return getPlugin(str, true);
    }

    public static Plugin getPlugin(String str, boolean z) {
        return PluginManager.getInstance().getPlugin(str, z);
    }

    public static boolean isPluginInstalled(String str) {
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        return plugin != null && plugin.isInstalled();
    }

    public static boolean isPluginLoaded(String str) {
        return PluginManager.getInstance().isLoaded(str);
    }

    public static boolean loadPlugin(String str) {
        return PluginManager.getInstance().loadPlugin(str);
    }

    public static boolean syncInstallPlugin(String str, String str2) {
        InterfaceC3780c m16682a = C3924b.m16682a();
        if (m16682a != null) {
            try {
                return m16682a.mo16877a(str, str2);
            } catch (RemoteException e) {
                ZeusLogger.m16787w("Zeus/install_pangle", "syncInstallPlugin error.", e);
                return false;
            }
        }
        return false;
    }

    public static void unInstallPlugin(String str) {
        PluginManager.getInstance().unInstallPackage(str);
    }

    public static void setAllowDownloadPlugin(String str, int i, boolean z) {
        PluginManager.getInstance().setAllowDownloadPlugin(str, i, z);
    }

    public static String getHostAbi() {
        return C3791b.m16954a();
    }

    public static int getMaxInstallVer(String str) {
        if (C3793d.m16923a(getAppApplication())) {
            return getPlugin(str).getInstalledMaxVer();
        }
        return -1;
    }

    public static int getHostAbiBit() {
        return C3791b.m16945b();
    }

    public static int getInstalledPluginVersion(String str) {
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        if (plugin == null) {
            return -1;
        }
        int version = plugin.getVersion();
        ZeusLogger.m16794d("Zeus/download_pangle", " getInstalledPluginVersion, " + str + " = " + version);
        return version;
    }

    public static void addExternalAssetsForPlugin(String str, String str2) {
        Plugin plugin;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (plugin = getPlugin(str)) == null || plugin.mResources == null) {
            return;
        }
        new C3905a().m16729a(plugin.mResources.getAssets(), str2, false);
    }

    public static String getZeusDid() {
        String did = GlobalParam.getInstance().getDid();
        return !TextUtils.isEmpty(did) ? did : ApmUtils.getApmInstance().getDid();
    }
}
