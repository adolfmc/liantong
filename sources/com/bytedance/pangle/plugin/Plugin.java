package com.bytedance.pangle.plugin;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.bytedance.pangle.C3837g;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.InterfaceC3780c;
import com.bytedance.pangle.PluginClassLoader;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusApplication;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3791b;
import com.bytedance.pangle.p176d.C3792c;
import com.bytedance.pangle.p176d.C3793d;
import com.bytedance.pangle.p176d.C3794e;
import com.bytedance.pangle.p178f.p179a.C3829e;
import com.bytedance.pangle.res.p181a.C3910c;
import com.bytedance.pangle.servermanager.C3924b;
import com.bytedance.pangle.util.C3942b;
import com.bytedance.pangle.util.C3943c;
import com.bytedance.pangle.util.C3947g;
import com.bytedance.pangle.util.C3952k;
import com.bytedance.pangle.util.C3953l;
import com.bytedance.pangle.wrapper.PluginApplicationWrapper;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Plugin {
    public static final int LIFE_INSTALLED = 2;
    public static final int LIFE_LOADED = 3;
    public static final int LIFE_PENDING = 1;
    private static final String TAG = "Plugin";
    private int mApiVersionCode;
    public final String mAppKey;
    public final String mAppSecretKey;
    public ZeusApplication mApplication;
    public PluginClassLoader mClassLoader;
    public PluginApplicationWrapper mHostApplication;
    public ApplicationInfo mHostApplicationInfoHookSomeField;
    private volatile boolean mInitialized;
    private String mInternalPath;
    private int mInternalVersionCode;
    public boolean mIsSupportLibIso;
    public int mMaxVersionCode;
    public int mMinVersionCode;
    public final boolean mOpenLoadClassOpt;
    private String mPackageDir;
    public String mPkgName;
    public final boolean mReInstallInternalPluginByMd5;
    public Resources mResources;
    public String mSignature;
    public final boolean mUnInstallPluginWhenHostChange;
    public final boolean mUseMemoryForActivityIntent;
    private int mVersionCode;
    public String response;
    public HashMap<String, ActivityInfo> pluginActivities = new HashMap<>();
    public HashMap<String, ServiceInfo> pluginServices = new HashMap<>();
    public HashMap<String, ActivityInfo> pluginReceiver = new HashMap<>();
    public HashMap<String, ProviderInfo> pluginProvider = new HashMap<>();
    private volatile int mLifeCycle = 1;
    public final List<String> mSharedHostSos = new ArrayList();
    final Object installLock = new Object();
    final Object initializeLock = new Object();

    public Plugin(JSONObject jSONObject) {
        this.mInternalVersionCode = -1;
        this.mMaxVersionCode = Integer.MAX_VALUE;
        this.mPkgName = jSONObject.getString("packageName");
        this.mMinVersionCode = jSONObject.optInt("minPluginVersion", 0);
        this.mMaxVersionCode = jSONObject.optInt("maxPluginVersion", Integer.MAX_VALUE);
        this.mApiVersionCode = jSONObject.getInt("apiVersionCode");
        this.mSignature = GlobalParam.getInstance().getSignature(this.mPkgName);
        if (TextUtils.isEmpty(this.mSignature)) {
            this.mSignature = jSONObject.optString("signature", "");
        }
        this.mIsSupportLibIso = jSONObject.optBoolean("isSupportLibIsolate", false);
        this.mInternalPath = jSONObject.optString("internalPath", "");
        this.mInternalVersionCode = jSONObject.optInt("internalVersionCode", -1);
        this.mAppKey = jSONObject.optString("appKey", "");
        this.mAppSecretKey = jSONObject.optString("appSecretKey", "");
        this.mOpenLoadClassOpt = jSONObject.optBoolean("loadClassOpt", false);
        this.mUnInstallPluginWhenHostChange = jSONObject.optBoolean("unInstallPluginWhenHostChange", false);
        this.mUseMemoryForActivityIntent = jSONObject.optBoolean("useMemoryForActivityIntent", false);
        this.mReInstallInternalPluginByMd5 = jSONObject.optBoolean("reInstallInternalPluginByMd5", false);
        JSONArray optJSONArray = jSONObject.optJSONArray("sharedHostSo");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.mSharedHostSos.add((String) optJSONArray.get(i));
            }
        }
        setupInternalPlugin();
    }

    private void setupInternalPlugin() {
        String[] list;
        int m16607a;
        if (C3953l.m16606a().f9379a.getInt(String.format(Locale.getDefault(), "OFFLINE_INTERNAL_%s", this.mPkgName), -1) == this.mApiVersionCode) {
            return;
        }
        if (TextUtils.isEmpty(this.mInternalPath) || this.mInternalVersionCode == -1) {
            try {
                for (String str : Zeus.getAppApplication().getAssets().list(C3837g.f9175d)) {
                    if (str.startsWith(this.mPkgName + "_") && (m16607a = C3952k.m16607a(str.split("_")[1])) != -1) {
                        this.mInternalPath = C3837g.f9175d + "/" + str;
                        this.mInternalVersionCode = m16607a;
                        return;
                    }
                }
            } catch (IOException e) {
                ZeusLogger.m16787w("Zeus/install_pangle", "setupInternalPlugin failed.", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init() {
        boolean z;
        C3953l m16606a;
        String str;
        if (this.mInitialized) {
            return;
        }
        synchronized (this.initializeLock) {
            if (this.mInitialized) {
                return;
            }
            if (!C3793d.m16923a(Zeus.getAppApplication())) {
                updateInstallStateFromMainProcess();
            } else {
                int i = 0;
                if (!TextUtils.isEmpty(C3953l.m16606a().f9379a.getString("HOST_ABI_".concat(String.valueOf(this.mPkgName)), ""))) {
                    z = !TextUtils.equals(C3953l.m16606a().f9379a.getString("HOST_ABI_".concat(String.valueOf(this.mPkgName)), ""), Zeus.getHostAbi());
                    ZeusLogger.m16792i("Zeus/init_pangle", "ZeusSpUtils isHostAbiUpdate HOST_ABI=" + m16606a.f9379a.getString("HOST_ABI_".concat(String.valueOf(str)), "") + ", " + Zeus.getHostAbi() + ", result=" + z);
                } else {
                    z = false;
                }
                deleteIfNeeded();
                int installedMaxVer = getInstalledMaxVer();
                if (checkVersionValid(installedMaxVer, this.mApiVersionCode, z)) {
                    i = modifyResIfNeed(installedMaxVer);
                    updateToInstalled(i);
                }
                deleteOtherExpiredVer(i);
                ZeusLogger.m16792i("Zeus/init_pangle", "Plugin initPlugins result=".concat(String.valueOf(this)));
                C3953l m16606a2 = C3953l.m16606a();
                String str2 = this.mPkgName;
                SharedPreferences.Editor edit = m16606a2.f9379a.edit();
                edit.putString("ROM_LAST_".concat(String.valueOf(str2)), Build.VERSION.INCREMENTAL);
                edit.apply();
                C3953l m16606a3 = C3953l.m16606a();
                String str3 = this.mPkgName;
                String string = m16606a3.f9379a.getString("HOST_ABI_".concat(String.valueOf(str3)), "");
                SharedPreferences.Editor edit2 = m16606a3.f9379a.edit();
                edit2.putString("HOST_ABI_".concat(String.valueOf(str3)), Zeus.getHostAbi());
                edit2.apply();
                ZeusLogger.m16792i("Zeus/init_pangle", "ZeusSpUtils setHostAbiUpdated HOST_ABI=" + string + " --> " + Zeus.getHostAbi());
                C3953l m16606a4 = C3953l.m16606a();
                String str4 = this.mPkgName;
                String m16645b = C3942b.m16645b(Zeus.getAppApplication());
                String m16602b = m16606a4.m16602b(str4);
                if (!TextUtils.equals(m16602b, m16645b)) {
                    SharedPreferences.Editor edit3 = m16606a4.f9379a.edit();
                    edit3.putString("HOST_IDENTITY_".concat(String.valueOf(str4)), m16645b);
                    edit3.apply();
                }
                ZeusLogger.m16792i("Zeus/init_pangle", "ZeusSpUtils setHostIdentity(" + str4 + ") " + m16602b + " --> " + m16645b);
                C3953l m16606a5 = C3953l.m16606a();
                String str5 = this.mPkgName;
                int i2 = this.mApiVersionCode;
                int m16605a = m16606a5.m16605a(str5);
                if (m16605a != i2) {
                    SharedPreferences.Editor edit4 = m16606a5.f9379a.edit();
                    edit4.putInt("PLUGIN_API_VERSION_".concat(String.valueOf(str5)), i2);
                    edit4.apply();
                }
                ZeusLogger.m16792i("Zeus/init_pangle", "ZeusSpUtils setPluginApiVersion " + m16605a + " --> " + i2);
            }
            this.mInitialized = true;
            installInternalPlugin();
        }
    }

    private void installInternalPlugin() {
        if (!C3793d.m16923a(Zeus.getAppApplication()) || this.mReInstallInternalPluginByMd5) {
            if (getVersion() > this.mInternalVersionCode) {
                return;
            }
        } else if (getVersion() >= this.mInternalVersionCode || TextUtils.isEmpty(this.mInternalPath)) {
            return;
        }
        C3794e.m16918a(new Runnable() { // from class: com.bytedance.pangle.plugin.Plugin.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    String m16935b = C3792c.m16935b();
                    File file = new File(m16935b, Plugin.this.mPkgName + ".apk");
                    ZeusLogger.m16792i("Zeus/init_pangle", "Plugin copyInternalPlugin " + Plugin.this.mInternalPath + " --> " + file.getAbsolutePath());
                    C3947g.m16633a(Zeus.getAppApplication().getAssets().open(Plugin.this.mInternalPath), new FileOutputStream(file));
                    if (file.exists()) {
                        PluginManager.getInstance().asyncInstall(Plugin.this.mPkgName, file);
                        return;
                    }
                    ZeusLogger.m16788w("Zeus/install_pangle", "installInternalPlugin failed. " + file.getAbsolutePath() + " is not exists.");
                } catch (Throwable th) {
                    ZeusLogger.m16788w("Zeus/install_pangle", "installInternalPlugin failed. " + th.getMessage());
                }
            }
        });
    }

    private int modifyResIfNeed(int i) {
        String m16645b = C3942b.m16645b(Zeus.getAppApplication());
        if (TextUtils.isEmpty(m16645b) || !TextUtils.equals(C3953l.m16606a().m16602b(this.mPkgName), m16645b)) {
            if (this.mUnInstallPluginWhenHostChange || GlobalParam.getInstance().unInstallPluginWhenHostChange(this.mPkgName)) {
                ZeusLogger.m16794d("Zeus/init_pangle", "uninstall plugin by host update. " + this.mPkgName + " " + i);
                return 0;
            }
            ZeusLogger.m16794d("Zeus/init_pangle", "modifyRes by init. " + this.mPkgName + " " + i);
            int m16705a = new C3910c().m16705a(new File(C3792c.m16934b(this.mPkgName, i)), true, new StringBuilder());
            if (m16705a == 100 || m16705a == 200) {
                return i;
            }
            return 0;
        }
        return i;
    }

    public void setLifeCycle(int i) {
        this.mLifeCycle = i;
    }

    public int getLifeCycle() {
        updateInstallStateFromMainProcess();
        return this.mLifeCycle;
    }

    public int getApiVersionCode() {
        return this.mApiVersionCode;
    }

    public int getVersion() {
        updateInstallStateFromMainProcess();
        return this.mVersionCode;
    }

    public int getInternalVersionCode() {
        return this.mInternalVersionCode;
    }

    private void updateInstallStateFromMainProcess() {
        InterfaceC3780c m16682a;
        try {
            if (C3793d.m16923a(Zeus.getAppApplication()) || this.mLifeCycle >= 2 || (m16682a = C3924b.m16682a()) == null || !m16682a.mo16878a(this.mPkgName)) {
                return;
            }
            updateToInstalled(m16682a.mo16874b(this.mPkgName));
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("updateInstallStateFromMainProcess error. process = ");
            Zeus.getAppApplication();
            sb.append(C3793d.m16924a());
            ZeusLogger.m16787w("Zeus/ppm_pangle", sb.toString(), th);
        }
    }

    public boolean isInstalled() {
        updateInstallStateFromMainProcess();
        return this.mLifeCycle >= 2;
    }

    private void updateToInstalled(int i) {
        this.mVersionCode = i;
        this.mLifeCycle = 2;
    }

    public boolean isLoaded() {
        return this.mLifeCycle == 3;
    }

    public int getInstalledMaxVer() {
        if (TextUtils.isEmpty(this.mPackageDir)) {
            this.mPackageDir = C3792c.m16938a(this.mPkgName);
        }
        File[] listFiles = new File(this.mPackageDir).listFiles(new FileFilter() { // from class: com.bytedance.pangle.plugin.Plugin.2
            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                return file != null && file.getName().matches("^version-(\\d+)$");
            }
        });
        int i = -1;
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                int parseInt = Integer.parseInt(file.getName().split("-")[1]);
                if (parseInt > i && C3953l.m16606a().m16604a(this.mPkgName, parseInt) && new File(C3792c.m16934b(this.mPkgName, parseInt)).exists()) {
                    i = parseInt;
                }
            }
        }
        ZeusLogger.m16792i("Zeus/init_pangle", "Plugin getInstalledMaxVersion, pkg=" + this.mPkgName + ", maxVer=" + i);
        return i;
    }

    private boolean checkVersionValid(int i, int i2, boolean z) {
        int m16605a = C3953l.m16606a().m16605a(this.mPkgName);
        if (m16605a > i2) {
            ZeusLogger.m16788w("Zeus/init_pangle", TAG.concat(String.valueOf(String.format(" checkVersionValid %s apiVersion downgrade , lastApiVersion=%s , currentApiVersion=%s", this.mPkgName, Integer.valueOf(m16605a), Integer.valueOf(i2)))));
            return false;
        }
        boolean z2 = i >= 0 && i >= this.mMinVersionCode && i <= this.mMaxVersionCode;
        if (z2 && i2 != -1) {
            C3953l m16606a = C3953l.m16606a();
            String str = this.mPkgName;
            int i3 = m16606a.f9379a.getInt("API_MIN_" + str + "_" + i, 0);
            C3953l m16606a2 = C3953l.m16606a();
            String str2 = this.mPkgName;
            int i4 = m16606a2.f9379a.getInt("API_MAX_" + str2 + "_" + i, Integer.MAX_VALUE);
            if (i4 == 0) {
                i4 = Integer.MAX_VALUE;
            }
            if (i2 < i3 || i2 > i4) {
                ZeusLogger.m16788w("Zeus/init_pangle", TAG.concat(String.valueOf(String.format(" checkVersionValid plugin[%s, ver=%s] is not compatible with api[ver_code=%s], apiCompatibleVer=[%s,%s]", this.mPkgName, Integer.valueOf(this.mVersionCode), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)))));
                z2 = false;
            }
        }
        if (z2 && z && C3791b.m16944b(new File(C3792c.m16934b(this.mPkgName, i)))) {
            ZeusLogger.m16788w("Zeus/init_pangle", TAG.concat(String.valueOf(String.format(" checkVersionValid plugin[%s, ver=%s] not match hostAbi", this.mPkgName, Integer.valueOf(i)))));
            z2 = false;
        }
        ZeusLogger.m16792i("Zeus/init_pangle", "Plugin checkVersionValid, pkg=" + this.mPkgName + ", ver=" + this.mVersionCode + ", valid=" + z2);
        return z2;
    }

    private void deleteIfNeeded() {
        if (C3793d.m16923a(Zeus.getAppApplication())) {
            if (C3953l.m16606a().f9379a.getBoolean("UNINSTALL__".concat(String.valueOf(this.mPkgName)), false)) {
                C3953l m16606a = C3953l.m16606a();
                String str = this.mPkgName;
                SharedPreferences.Editor edit = m16606a.f9379a.edit();
                edit.remove("UNINSTALL__".concat(String.valueOf(str)));
                edit.apply();
                deleteInstalledPlugin();
                ZeusLogger.m16788w("Zeus/init_pangle", "Plugin deleteIfNeeded " + this.mPkgName);
            }
        }
    }

    private void deleteInstalledPlugin() {
        if (TextUtils.isEmpty(this.mPackageDir)) {
            this.mPackageDir = C3792c.m16938a(this.mPkgName);
        }
        new File(this.mPackageDir).listFiles(new FileFilter() { // from class: com.bytedance.pangle.plugin.Plugin.3
            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                if (file.getName().matches("^version-(\\d+)$")) {
                    C3953l.m16606a().m16603a(Plugin.this.mPkgName, Integer.parseInt(file.getName().split("-")[1]), false);
                }
                return false;
            }
        });
        C3947g.m16632a(this.mPackageDir);
    }

    private void deleteOtherExpiredVer(int i) {
        if (C3793d.m16923a(Zeus.getAppApplication())) {
            if (TextUtils.isEmpty(this.mPackageDir)) {
                this.mPackageDir = C3792c.m16938a(this.mPkgName);
            }
            final String concat = "version-".concat(String.valueOf(i));
            new File(this.mPackageDir).listFiles(new FileFilter() { // from class: com.bytedance.pangle.plugin.Plugin.4
                @Override // java.io.FileFilter
                public final boolean accept(File file) {
                    if (file != null && !concat.equals(file.getName()) && !"data".equals(file.getName())) {
                        C3947g.m16632a(file.getAbsolutePath());
                        ZeusLogger.m16788w("Zeus/init_pangle", "Plugin deleteOtherExpired " + file.getAbsolutePath());
                        if (file.getName().matches("^version-(\\d+)$")) {
                            C3953l.m16606a().m16603a(Plugin.this.mPkgName, Integer.parseInt(file.getName().split("-")[1]), false);
                        }
                    }
                    return false;
                }
            });
        }
    }

    public void setApiCompatVersion(int i, int i2, int i3) {
        C3953l m16606a = C3953l.m16606a();
        String str = this.mPkgName;
        SharedPreferences.Editor edit = m16606a.f9379a.edit();
        edit.putInt("API_MIN_" + str + "_" + i, i2);
        edit.putInt("API_MAX_" + str + "_" + i, i3);
        edit.apply();
    }

    public boolean isVersionInstalled(int i) {
        return C3953l.m16606a().m16604a(this.mPkgName, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean install(File file, C3829e c3829e) {
        String str;
        int i;
        boolean z = false;
        try {
            ZeusLogger.m16792i("Zeus/install_pangle", "Plugin install from local file " + file + ", " + Thread.currentThread().getName());
            str = c3829e.f9166a;
            i = c3829e.f9167b;
        } catch (Throwable th) {
            ZeusLogger.errReport("Zeus/install_pangle", "Plugin IMPOSSIBLE!!!", th);
        }
        synchronized (this.installLock) {
            try {
                ZeusLogger.m16792i("Zeus/install_pangle", "Plugin synchronized begin, plugin=".concat(String.valueOf(this)));
                boolean checkValid = checkValid(file, str, i);
                if (checkValid) {
                    boolean m16780a = C3877b.m16780a(file, str, i);
                    if (m16780a) {
                        try {
                            String str2 = C3943c.m16644a(new File(C3792c.m16934b(this.mPkgName, i)))[0];
                            C3953l m16606a = C3953l.m16606a();
                            String str3 = this.mPkgName;
                            SharedPreferences.Editor edit = m16606a.f9379a.edit();
                            edit.putString("IDENTITY_".concat(String.valueOf(str3)), str2);
                            edit.apply();
                            C3953l.m16606a().m16603a(this.mPkgName, i, true);
                            ZeusLogger.m16792i("Zeus/install_pangle", "Plugin markPluginInstalled, " + this.mPkgName + ":" + i + " identity=" + str2);
                            C3947g.m16634a(file);
                        } catch (Throwable th2) {
                            th = th2;
                            z = m16780a;
                            throw th;
                        }
                    }
                    z = m16780a;
                }
                synchronized (this) {
                    if (checkValid) {
                        if (this.mLifeCycle == 3) {
                            ZeusLogger.m16788w("Zeus/install_pangle", "Plugin LIFE_LOADED, valid next restart " + str + ":" + i);
                        } else if (z) {
                            updateToInstalled(i);
                            ZeusLogger.m16792i("Zeus/install_pangle", "Plugin INSTALLED " + str + ":" + i);
                        } else {
                            ZeusLogger.m16792i("Zeus/install_pangle", "Plugin INSTALL_FAILED" + str + ":" + i);
                            C3947g.m16634a(file);
                            ZeusLogger.m16788w("Zeus/install_pangle", "Plugin delete file by failedCount > 0 " + str + ":" + i);
                        }
                    } else {
                        C3947g.m16634a(file);
                        ZeusLogger.m16788w("Zeus/install_pangle", "Plugin deleting invalid " + str + ":" + i);
                    }
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    private boolean checkValid(File file, String str, int i) {
        if (!TextUtils.equals(this.mPkgName, str)) {
            ZeusLogger.m16788w("Zeus/install_pangle", "Plugin checkValid " + str + " package name not match !!!");
            return false;
        } else if (i < this.mMinVersionCode || i > this.mMaxVersionCode) {
            String format = String.format(" pluginApk ver[%s] not match plugin VerRange[%s, %s].", Integer.valueOf(i), Integer.valueOf(this.mMinVersionCode), Integer.valueOf(this.mMaxVersionCode));
            ZeusLogger.m16788w("Zeus/install_pangle", "Plugin checkValid " + str + " " + format);
            return false;
        } else if (i < this.mVersionCode && isInstalled()) {
            String format2 = String.format(" pluginApk ver[%s] lower than installed plugin[%s].", Integer.valueOf(i), Integer.valueOf(this.mVersionCode));
            ZeusLogger.m16788w("Zeus/install_pangle", "Plugin checkValid " + str + format2);
            return false;
        } else if (file == null || !file.exists()) {
            ZeusLogger.m16788w("Zeus/install_pangle", "Plugin checkValid " + str + " pluginApk not exist.");
            return false;
        } else if (i == this.mVersionCode && C3953l.m16606a().f9379a.getString("IDENTITY_".concat(String.valueOf(str)), "").equals(C3943c.m16644a(file)[0])) {
            ZeusLogger.m16788w("Zeus/install_pangle", "Plugin checkValid " + str + " pluginApk with the same identity has already installed.");
            return false;
        } else {
            ZeusLogger.m16792i("Zeus/install_pangle", "Plugin checkValid " + str + ":" + i + " true");
            return true;
        }
    }

    public String getNativeLibraryDir() {
        int i = this.mVersionCode;
        if (i > 0) {
            return C3792c.m16930d(this.mPkgName, i);
        }
        return C3792c.m16938a(this.mPkgName);
    }

    public void injectResponse(String str) {
        this.response = str;
    }

    public String toString() {
        return "Plugin{pkg=" + this.mPkgName + ", ver=" + this.mVersionCode + ", life=" + this.mLifeCycle + '}';
    }
}
