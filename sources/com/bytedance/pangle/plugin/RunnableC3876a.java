package com.bytedance.pangle.plugin;

import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p178f.p179a.C3828d;
import com.bytedance.pangle.p178f.p179a.C3829e;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.bytedance.pangle.plugin.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class RunnableC3876a implements Runnable {

    /* renamed from: a */
    private final File f9240a;

    /* renamed from: b */
    private final String f9241b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3876a(String str, File file) {
        this.f9240a = file;
        this.f9241b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        m16781a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m16781a() {
        C3829e m16856a = C3828d.m16856a(this.f9240a);
        if (m16856a == null) {
            String str = this.f9241b;
            ZeusPluginStateListener.postStateChange(str, 7, " read local file package info failed !!! pluginPkg = " + this.f9241b + " mApkFile.exists = " + this.f9240a.exists());
            StringBuilder sb = new StringBuilder("PluginInstallRunnable read local file package info failed !!! pluginPkg = ");
            sb.append(this.f9241b);
            ZeusLogger.m16788w("Zeus/install_pangle", sb.toString());
            return false;
        }
        Plugin plugin = PluginManager.getInstance().getPlugin(m16856a.f9166a);
        if (plugin == null) {
            String str2 = this.f9241b;
            ZeusPluginStateListener.postStateChange(str2, 7, " plugin == null !!! pluginPkg = " + this.f9241b);
            ZeusLogger.m16788w("Zeus/install_pangle", "PluginInstallRunnable cannot query valid plugin !!! packageName = " + m16856a.f9166a);
            return false;
        }
        boolean install = plugin.install(this.f9240a, m16856a);
        if (install) {
            ZeusPluginStateListener.postStateChange(m16856a.f9166a, 6, new Object[0]);
        } else {
            ZeusPluginStateListener.postStateChange(m16856a.f9166a, 7, "Internal error.");
        }
        return install;
    }
}
