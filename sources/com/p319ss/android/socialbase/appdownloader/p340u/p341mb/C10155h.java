package com.p319ss.android.socialbase.appdownloader.p340u.p341mb;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.monitor.IDownloadMonitorListener;
import com.p319ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.u.mb.h */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10155h {
    /* renamed from: mb */
    private static String m6550mb(int i) {
        return (i >>> 24) == 1 ? "android:" : "";
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: mb */
    public static android.content.pm.PackageInfo m6546mb(@android.support.annotation.NonNull java.io.File r11) {
        /*
            Method dump skipped, instructions count: 404
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.p340u.p341mb.C10155h.m6546mb(java.io.File):android.content.pm.PackageInfo");
    }

    /* renamed from: mb */
    public static PackageInfo m6548mb(@NonNull Context context, @NonNull File file, int i) {
        if (DownloadExpSwitchCode.isSwitchEnable(268435456) && Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 26) {
            try {
                return m6546mb(file);
            } catch (Throwable th) {
                m6545mb("getPackageInfo::unzip_getpackagearchiveinfo", th.getMessage());
                return m6544ox(context, file, i);
            }
        }
        return m6544ox(context, file, i);
    }

    /* renamed from: ox */
    private static PackageInfo m6544ox(@NonNull Context context, @NonNull File file, int i) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            m6545mb("unzip_getpackagearchiveinfo", "packageManager == null");
            return null;
        }
        try {
            return packageManager.getPackageArchiveInfo(file.getPath(), i);
        } catch (Throwable th) {
            m6545mb("unzip_getpackagearchiveinfo", "pm.getPackageArchiveInfo failed: " + th.getMessage());
            return null;
        }
    }

    /* renamed from: mb */
    private static void m6545mb(@NonNull String str, @NonNull String str2) {
        IDownloadMonitorListener downloadMonitorListener = DownloadComponentManager.getDownloadMonitorListener();
        if (downloadMonitorListener == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", str2);
        } catch (JSONException unused) {
        }
        downloadMonitorListener.monitorEvent(str, jSONObject, null, null);
    }

    /* renamed from: mb */
    private static String m6547mb(C10159mb c10159mb, int i) {
        int m6524ox = c10159mb.m6524ox(i);
        int m6534b = c10159mb.m6534b(i);
        if (m6524ox == 3) {
            return c10159mb.m6530hj(i);
        }
        return m6524ox == 2 ? String.format("?%s%08X", m6550mb(m6534b), Integer.valueOf(m6534b)) : (m6524ox < 16 || m6524ox > 31) ? String.format("<0x%X, type 0x%02X>", Integer.valueOf(m6534b), Integer.valueOf(m6524ox)) : String.valueOf(m6534b);
    }

    /* renamed from: mb */
    public static String m6549mb(Context context, PackageInfo packageInfo, String str) {
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        applicationInfo.sourceDir = str;
        applicationInfo.publicSourceDir = str;
        try {
            return applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (OutOfMemoryError e) {
            m6545mb("getPackageInfo::fail_load_label", e.getMessage());
            return null;
        }
    }
}
