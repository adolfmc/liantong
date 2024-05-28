package com.bytedance.pangle.plugin;

import android.text.TextUtils;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p176d.C3792c;
import com.bytedance.pangle.util.C3947g;
import java.io.File;
import java.io.FileFilter;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.bytedance.pangle.plugin.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class RunnableC3888d implements Runnable {
    @Override // java.lang.Runnable
    public final void run() {
        m16757a(new File(C3792c.m16940a()));
        String m16933c = C3792c.m16933c();
        if (TextUtils.isEmpty(m16933c)) {
            return;
        }
        m16757a(new File(m16933c));
    }

    /* renamed from: a */
    private void m16757a(File file) {
        ZeusLogger.m16792i("Zeus/init_pangle", "ZeusScanRunnable listPluginDownloadDir, dir = ".concat(String.valueOf(file)));
        file.listFiles(new FileFilter() { // from class: com.bytedance.pangle.plugin.d.1
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                if (file2 == null) {
                    return false;
                }
                if (file2.getName().endsWith(".apk") || file2.getName().endsWith(".jar")) {
                    PluginManager.getInstance().asyncInstall(null, file2);
                    return true;
                }
                if ((file2.getAbsolutePath().endsWith(".temp") || file2.getAbsolutePath().endsWith(".tp")) && System.currentTimeMillis() - file2.lastModified() < 259200000) {
                    ZeusLogger.m16788w("Zeus/init_pangle", "ZeusScanRunnable installPluginDir find : ".concat(String.valueOf(file2)));
                } else {
                    C3947g.m16634a(file2);
                    ZeusLogger.m16788w("Zeus/init_pangle", "ZeusScanRunnable installPluginDir deleted : ".concat(String.valueOf(file2)));
                }
                return false;
            }
        });
    }
}
