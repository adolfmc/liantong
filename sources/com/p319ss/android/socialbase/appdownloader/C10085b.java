package com.p319ss.android.socialbase.appdownloader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.p319ss.android.socialbase.appdownloader.p335b.C10095mb;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10088b;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10094lz;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10101x;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import com.p319ss.android.socialbase.appdownloader.p340u.p341mb.C10155h;
import com.p319ss.android.socialbase.downloader.constants.DownloadStatus;
import com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import com.p319ss.android.socialbase.downloader.utils.SystemUtils;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.b */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10085b {

    /* renamed from: mb */
    private static int f19455mb;

    /* renamed from: ox */
    private static NotificationChannel f19456ox;

    /* renamed from: mb */
    private static String m6922mb(long j, long j2, String str, boolean z) {
        double d = j2 > 1 ? j / j2 : j;
        if (z || "GB".equals(str) || "TB".equals(str)) {
            return new DecimalFormat("#.##").format(d) + " " + str;
        }
        return new DecimalFormat("#").format(d) + " " + str;
    }

    /* renamed from: mb */
    public static String m6924mb(long j) {
        return m6921mb(j, true);
    }

    /* renamed from: mb */
    public static String m6921mb(long j, boolean z) {
        long[] jArr = {1099511627776L, 1073741824, 1048576, 1024, 1};
        String[] strArr = {"TB", "GB", "MB", "KB", "B"};
        if (j < 1) {
            return "0 " + strArr[strArr.length - 1];
        }
        for (int i = 0; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j >= j2) {
                return m6922mb(j, j2, strArr[i], z);
            }
        }
        return null;
    }

    /* renamed from: mb */
    private static String m6923mb(long j, long j2, String str) {
        double d = j2 > 1 ? j / j2 : j;
        if ("MB".equals(str)) {
            return new DecimalFormat("#").format(d) + str;
        }
        return new DecimalFormat("#.##").format(d) + str;
    }

    /* renamed from: ox */
    public static String m6897ox(long j) {
        long[] jArr = {1099511627776L, 1073741824, 1048576, 1024, 1};
        String[] strArr = {"TB", "GB", "MB", "KB", "B"};
        if (j < 1) {
            return "0 " + strArr[strArr.length - 1];
        }
        for (int i = 0; i < jArr.length; i++) {
            long j2 = jArr[i];
            if (j >= j2) {
                return m6923mb(j, j2, strArr[i]);
            }
        }
        return null;
    }

    /* renamed from: mb */
    public static int m6916mb(final Context context, final int i, final boolean z) {
        InterfaceC10101x m6818ko = C10112hj.m6786x().m6818ko();
        if (m6818ko == null) {
            return m6929hj(context, i, z);
        }
        DownloadInfo downloadInfo = Downloader.getInstance(context).getDownloadInfo(i);
        f19455mb = 1;
        m6818ko.mo6868mb(downloadInfo, new InterfaceC10094lz() { // from class: com.ss.android.socialbase.appdownloader.b.1
            @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10094lz
            /* renamed from: mb */
            public void mo6876mb() {
                int unused = C10085b.f19455mb = C10085b.m6929hj(context, i, z);
            }
        });
        return f19455mb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hj */
    public static int m6929hj(Context context, int i, boolean z) {
        if (DownloadSetting.obtain(i).optInt("notification_opt_2") == 1) {
            DownloadNotificationManager.getInstance().cancelNotification(i);
        }
        m6920mb((Activity) C10174ww.m6487mb().m6480ox());
        if (DownloadSetting.obtain(i).optInt("install_queue_enable", 0) == 1) {
            return C10174ww.m6487mb().m6486mb(context, i, z);
        }
        return m6895ox(context, i, z);
    }

    /* renamed from: ox */
    public static int m6895ox(final Context context, final int i, final boolean z) {
        final DownloadInfo downloadInfo = Downloader.getInstance(context).getDownloadInfo(i);
        if (downloadInfo != null && "application/vnd.android.package-archive".equals(downloadInfo.getMimeType()) && !TextUtils.isEmpty(downloadInfo.getSavePath()) && !TextUtils.isEmpty(downloadInfo.getName())) {
            final File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
            if (file.exists()) {
                DownloadComponentManager.submitIOTask(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.b.2
                    @Override // java.lang.Runnable
                    public void run() {
                        int m6915mb = C10085b.m6915mb(context, i, z, downloadInfo, file);
                        if (m6915mb == 1 && C10112hj.m6786x().m6816lc() != null) {
                            C10112hj.m6786x().m6816lc().onOpenInstaller(downloadInfo, null);
                        }
                        C10085b.m6892ox(downloadInfo, z, m6915mb);
                    }
                });
                return 1;
            }
        }
        m6892ox(downloadInfo, z, 2);
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static void m6892ox(DownloadInfo downloadInfo, boolean z, int i) {
        if (downloadInfo == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("by_user", z ? 1 : 2);
            jSONObject.put("view_result", i);
            jSONObject.put("real_package_name", downloadInfo.getFilePackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onEvent(downloadInfo.getId(), "install_view_result", jSONObject);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003f, code lost:
        if (r0 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
        r0.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0033, code lost:
        if (r0 != null) goto L9;
     */
    /* renamed from: mb */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m6915mb(android.content.Context r5, int r6, boolean r7, com.p319ss.android.socialbase.downloader.model.DownloadInfo r8, java.io.File r9) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.C10085b.m6915mb(android.content.Context, int, boolean, com.ss.android.socialbase.downloader.model.DownloadInfo, java.io.File):int");
    }

    /* renamed from: mb */
    public static int m6914mb(Context context, Intent intent) {
        try {
            if (C10112hj.m6786x().m6793o() != null) {
                if (C10112hj.m6786x().m6793o().installApp(intent)) {
                    return 1;
                }
            }
        } catch (Throwable unused) {
        }
        try {
            context.startActivity(intent);
            return 1;
        } catch (Throwable unused2) {
            return 0;
        }
    }

    /* renamed from: mb */
    public static boolean m6912mb(Context context, DownloadInfo downloadInfo, PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.packageName.equals(downloadInfo.getPackageName())) {
            return false;
        }
        InterfaceC10090hj m6792ox = C10112hj.m6786x().m6792ox();
        if (m6792ox != null) {
            m6792ox.mo6882mb(downloadInfo.getId(), 8, downloadInfo.getPackageName(), packageInfo.packageName, "");
            if (m6792ox.mo6884mb()) {
                return true;
            }
        }
        IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(context).getDownloadNotificationEventListener(downloadInfo.getId());
        if (downloadNotificationEventListener != null) {
            downloadNotificationEventListener.onNotificationEvent(8, downloadInfo, packageInfo.packageName, "");
            InterfaceC10088b m6814mb = C10112hj.m6786x().m6814mb();
            return (m6814mb instanceof C10095mb) && ((C10095mb) m6814mb).m6875b();
        }
        return false;
    }

    /* renamed from: mb */
    public static boolean m6917mb(Context context, int i, File file) {
        if (DownloadSetting.obtain(i).optInt("back_miui_silent_install", 1) == 1) {
            return false;
        }
        if ((C10152hj.m6573je() || C10152hj.m6567nk()) && SystemUtils.checkServiceExists(context, "com.miui.securitycore", "com.miui.enterprise.service.EntInstallService")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycore", "com.miui.enterprise.service.EntInstallService"));
            Bundle bundle = new Bundle();
            bundle.putInt("userId", 0);
            bundle.putInt("flag", 256);
            bundle.putString("apkPath", file.getPath());
            bundle.putString("installerPkg", "com.miui.securitycore");
            intent.putExtras(bundle);
            try {
                context.startService(intent);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /* renamed from: mb */
    public static int m6927mb() {
        return C10112hj.m6786x().m6788u() ? 16384 : 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0021 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: mb */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.net.Uri m6925mb(int r1, com.p319ss.android.socialbase.downloader.depend.IDownloadFileUriProvider r2, android.content.Context r3, java.lang.String r4, java.io.File r5) {
        /*
            if (r2 == 0) goto Lb
            java.lang.String r1 = r5.getAbsolutePath()     // Catch: java.lang.Throwable -> L1e
            android.net.Uri r1 = r2.getUriForFile(r4, r1)     // Catch: java.lang.Throwable -> L1e
            goto L1f
        Lb:
            com.ss.android.socialbase.appdownloader.hj r2 = com.p319ss.android.socialbase.appdownloader.C10112hj.m6786x()
            com.ss.android.socialbase.appdownloader.b.u r2 = r2.m6823h()
            if (r2 == 0) goto L1e
            java.lang.String r0 = r5.getAbsolutePath()     // Catch: java.lang.Throwable -> L1e
            android.net.Uri r1 = r2.m6870mb(r1, r4, r0)     // Catch: java.lang.Throwable -> L1e
            goto L1f
        L1e:
            r1 = 0
        L1f:
            if (r1 != 0) goto L3b
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L37
            r0 = 24
            if (r2 < r0) goto L32
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L37
            if (r2 != 0) goto L32
            android.net.Uri r1 = android.support.p083v4.content.FileProvider.getUriForFile(r3, r4, r5)     // Catch: java.lang.Throwable -> L37
            goto L3b
        L32:
            android.net.Uri r1 = android.net.Uri.fromFile(r5)     // Catch: java.lang.Throwable -> L37
            goto L3b
        L37:
            r2 = move-exception
            r2.printStackTrace()
        L3b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.C10085b.m6925mb(int, com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider, android.content.Context, java.lang.String, java.io.File):android.net.Uri");
    }

    /* renamed from: mb */
    private static Intent m6910mb(Context context, DownloadInfo downloadInfo, @NonNull File file, boolean z, int[] iArr) {
        Uri m6925mb = m6925mb(downloadInfo.getId(), Downloader.getInstance(context).getDownloadFileUriProvider(downloadInfo.getId()), context, C10112hj.m6786x().m6822hj(), file);
        if (m6925mb == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.addFlags(1);
        }
        intent.setDataAndType(m6925mb, "application/vnd.android.package-archive");
        InterfaceC10090hj m6792ox = C10112hj.m6786x().m6792ox();
        boolean mo6881mb = m6792ox != null ? m6792ox.mo6881mb(downloadInfo.getId(), z) : false;
        IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(context).getDownloadNotificationEventListener(downloadInfo.getId());
        if (downloadNotificationEventListener != null) {
            mo6881mb = downloadNotificationEventListener.interceptAfterNotificationSuccess(z);
        }
        iArr[0] = mo6881mb ? 1 : 0;
        if (mo6881mb) {
            return null;
        }
        return intent;
    }

    /* renamed from: mb */
    public static boolean m6905mb(DownloadInfo downloadInfo, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(downloadInfo.getPackageName()) || !str.equals(downloadInfo.getPackageName())) {
            return !TextUtils.isEmpty(downloadInfo.getName()) && m6909mb(DownloadComponentManager.getAppContext(), downloadInfo, str);
        }
        return true;
    }

    /* renamed from: mb */
    public static boolean m6909mb(Context context, DownloadInfo downloadInfo, String str) {
        if (context == null) {
            return false;
        }
        try {
            File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
            PackageInfo packageInfo = null;
            if (file.exists()) {
                Log.e("AppDownloadUtils", "isPackageNameEqualsWithApk fileName:" + downloadInfo.getName() + " apkFileSize：" + file.length() + " fileUrl：" + downloadInfo.getUrl());
                PackageInfo m6906mb = m6906mb(downloadInfo, file);
                if (m6906mb != null && m6906mb.packageName.equals(str)) {
                    int i = m6906mb.versionCode;
                    try {
                        packageInfo = context.getPackageManager().getPackageInfo(str, m6927mb());
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                    return packageInfo != null && i == packageInfo.versionCode;
                }
                return false;
            } else if (DownloadSetting.obtain(downloadInfo.getId()).optBugFix("install_callback_error")) {
                String string = DownloadUtils.getString(downloadInfo.getTempCacheData().get("extra_apk_package_name"), null);
                int i2 = DownloadUtils.getInt(downloadInfo.getTempCacheData().get("extra_apk_version_code"), 0);
                if (string == null || TextUtils.isEmpty(string) || !string.equals(str)) {
                    return false;
                }
                try {
                    packageInfo = context.getPackageManager().getPackageInfo(str, m6927mb());
                } catch (PackageManager.NameNotFoundException unused2) {
                }
                return packageInfo != null && i2 == packageInfo.versionCode;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: ox */
    public static boolean m6893ox(Context context, DownloadInfo downloadInfo, PackageInfo packageInfo) {
        return m6911mb(context, downloadInfo, packageInfo, false);
    }

    /* renamed from: mb */
    public static boolean m6911mb(Context context, DownloadInfo downloadInfo, PackageInfo packageInfo, boolean z) {
        PackageInfo packageInfo2;
        if (packageInfo == null) {
            return false;
        }
        String str = packageInfo.packageName;
        int i = packageInfo.versionCode;
        if (downloadInfo != null) {
            downloadInfo.setAppVersionCode(i);
        }
        try {
            packageInfo2 = context.getPackageManager().getPackageInfo(str, m6927mb());
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo2 = null;
        }
        if (packageInfo2 == null) {
            return false;
        }
        int i2 = packageInfo2.versionCode;
        return z ? i < i2 : (downloadInfo == null || DownloadSetting.obtain(downloadInfo.getId()).optInt("install_with_same_version_code", 0) != 1) ? i <= i2 : i < i2;
    }

    /* renamed from: mb */
    public static boolean m6913mb(Context context, DownloadInfo downloadInfo) {
        return m6907mb(context, downloadInfo, true);
    }

    /* renamed from: mb */
    public static boolean m6907mb(Context context, DownloadInfo downloadInfo, boolean z) {
        if (downloadInfo == null) {
            return false;
        }
        String packageName = downloadInfo.getPackageName();
        int appVersionCode = downloadInfo.getAppVersionCode();
        if (appVersionCode > 0 || !z) {
            PackageInfo packageInfo = null;
            try {
                packageInfo = context.getPackageManager().getPackageInfo(packageName, m6927mb());
            } catch (PackageManager.NameNotFoundException unused) {
            }
            if (packageInfo == null) {
                return false;
            }
            return DownloadSetting.obtain(downloadInfo.getId()).optInt("install_with_same_version_code", 0) == 1 ? appVersionCode < packageInfo.versionCode : appVersionCode <= packageInfo.versionCode;
        }
        return m6931b(context, downloadInfo);
    }

    /* renamed from: ox */
    public static int m6894ox(Context context, DownloadInfo downloadInfo) {
        if (context == null || downloadInfo == null || TextUtils.isEmpty(downloadInfo.getSavePath()) || TextUtils.isEmpty(downloadInfo.getName())) {
            return 0;
        }
        int appVersionCode = downloadInfo.getAppVersionCode();
        if (appVersionCode > 0) {
            return appVersionCode;
        }
        try {
            PackageInfo m6908mb = m6908mb(context, downloadInfo, downloadInfo.getSavePath(), downloadInfo.getName());
            if (m6908mb != null) {
                int i = m6908mb.versionCode;
                downloadInfo.setAppVersionCode(i);
                return i;
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    /* renamed from: b */
    public static boolean m6931b(Context context, DownloadInfo downloadInfo) {
        if (context == null || downloadInfo == null || TextUtils.isEmpty(downloadInfo.getSavePath()) || TextUtils.isEmpty(downloadInfo.getName())) {
            return false;
        }
        return m6893ox(context, downloadInfo, m6908mb(context, downloadInfo, downloadInfo.getSavePath(), downloadInfo.getName()));
    }

    /* renamed from: mb */
    public static PackageInfo m6908mb(Context context, DownloadInfo downloadInfo, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str, str2);
        if (file.exists()) {
            Log.e("AppDownloadUtils", "isApkInstalled apkFileSize：fileName:" + file.getPath() + " apkFileSize" + file.length());
            return m6906mb(downloadInfo, file);
        }
        return null;
    }

    /* renamed from: mb */
    public static String m6900mb(String str, String str2, String str3, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Uri parse = Uri.parse(str);
        if (z) {
            if (TextUtils.isEmpty(str2)) {
                str2 = !TextUtils.isEmpty(parse.getLastPathSegment()) ? parse.getLastPathSegment() : "default.apk";
            }
        } else {
            String lastPathSegment = parse.getLastPathSegment();
            if (!TextUtils.isEmpty(lastPathSegment)) {
                str2 = lastPathSegment;
            } else if (TextUtils.isEmpty(str2)) {
                str2 = "default.apk";
            }
        }
        if (!m6930b(str3) || str2.endsWith(".apk")) {
            return str2;
        }
        return str2 + ".apk";
    }

    /* renamed from: ox */
    public static String m6899ox() {
        return DownloadUtils.getDownloadPath();
    }

    /* renamed from: mb */
    public static String m6901mb(String str, DownloadSetting downloadSetting) {
        JSONObject optJSONObject;
        if (downloadSetting == null || (optJSONObject = downloadSetting.optJSONObject("download_dir")) == null) {
            return "";
        }
        String optString = optJSONObject.optString("dir_name");
        String substring = (TextUtils.isEmpty(optString) || !optString.startsWith("/")) ? optString : optString.substring(1);
        if (TextUtils.isEmpty(substring)) {
            return substring;
        }
        if (substring.contains("%s")) {
            try {
                substring = String.format(substring, str);
            } catch (Throwable unused) {
            }
        } else {
            substring = substring + str;
        }
        return substring.length() > 255 ? substring.substring(substring.length() - 255) : substring;
    }

    /* renamed from: mb */
    public static boolean m6902mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject(str).optBoolean("bind_app", false);
    }

    /* renamed from: ox */
    public static boolean m6891ox(String str) {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!jSONObject.optBoolean("bind_app", false)) {
            if (jSONObject.optBoolean("auto_install_with_notification", true)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: mb */
    public static int m6926mb(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == -2) {
            return 2;
        }
        if (i == 1) {
            return 4;
        }
        if (DownloadStatus.isDownloading(i) || i == 11) {
            return 1;
        }
        return DownloadStatus.isDownloadOver(i) ? 3 : 0;
    }

    /* renamed from: b */
    public static boolean m6930b(String str) {
        return !TextUtils.isEmpty(str) && str.equals("application/vnd.android.package-archive");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003b, code lost:
        if (r0 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003d, code lost:
        r0.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0048, code lost:
        if (r0 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
        return false;
     */
    /* renamed from: mb */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m6919mb(android.content.Context r6) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 20
            if (r0 <= r2) goto L4c
            if (r6 != 0) goto La
            goto L4c
        La:
            r0 = 0
            int r2 = com.p319ss.android.socialbase.appdownloader.C10102h.m6849ox()     // Catch: java.lang.Throwable -> L48
            android.content.res.Resources r3 = r6.getResources()     // Catch: java.lang.Throwable -> L48
            int r2 = r3.getColor(r2)     // Catch: java.lang.Throwable -> L48
            int r3 = com.p319ss.android.socialbase.appdownloader.C10102h.m6867b()     // Catch: java.lang.Throwable -> L48
            int r4 = com.p319ss.android.socialbase.appdownloader.C10102h.m6861hj()     // Catch: java.lang.Throwable -> L48
            r5 = 2
            int[] r5 = new int[r5]     // Catch: java.lang.Throwable -> L48
            r5[r1] = r3     // Catch: java.lang.Throwable -> L48
            r3 = 1
            r5[r3] = r4     // Catch: java.lang.Throwable -> L48
            int r4 = com.p319ss.android.socialbase.appdownloader.C10102h.m6862h()     // Catch: java.lang.Throwable -> L48
            android.content.res.TypedArray r0 = r6.obtainStyledAttributes(r4, r5)     // Catch: java.lang.Throwable -> L48
            int r6 = r0.getColor(r1, r1)     // Catch: java.lang.Throwable -> L48
            if (r2 != r6) goto L3b
            if (r0 == 0) goto L3a
            r0.recycle()     // Catch: java.lang.Throwable -> L3a
        L3a:
            return r3
        L3b:
            if (r0 == 0) goto L4b
        L3d:
            r0.recycle()     // Catch: java.lang.Throwable -> L4b
            goto L4b
        L41:
            r6 = move-exception
            if (r0 == 0) goto L47
            r0.recycle()     // Catch: java.lang.Throwable -> L47
        L47:
            throw r6
        L48:
            if (r0 == 0) goto L4b
            goto L3d
        L4b:
            return r1
        L4c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.C10085b.m6919mb(android.content.Context):boolean");
    }

    @TargetApi(26)
    /* renamed from: ox */
    public static String m6896ox(@NonNull Context context) {
        try {
            if (f19456ox == null) {
                f19456ox = new NotificationChannel("111111", "channel_appdownloader", 3);
                f19456ox.setSound(null, null);
                f19456ox.setShowBadge(false);
                ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(f19456ox);
                return "111111";
            }
            return "111111";
        } catch (Throwable th) {
            th.printStackTrace();
            return "111111";
        }
    }

    /* renamed from: b */
    public static List<String> m6933b() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add("application/vnd.android.package-archive");
        arrayList.add("application/ttpatch");
        return arrayList;
    }

    /* renamed from: mb */
    public static void m6903mb(DownloadInfo downloadInfo, boolean z, boolean z2) {
        C10112hj.m6786x().m6802mb(new C10149u(DownloadComponentManager.getAppContext(), downloadInfo.getUrl()).m6640mb(downloadInfo.getTitle()).m6624ox(downloadInfo.getName()).m6688b(downloadInfo.getSavePath()).m6637mb(downloadInfo.isShowNotification()).m6622ox(downloadInfo.isAutoInstallWithoutNotification()).m6687b(downloadInfo.isOnlyWifi() || z2).m6670hj(downloadInfo.getExtra()).m6674h(downloadInfo.getMimeType()).m6639mb(downloadInfo.getExtraHeaders()).m6673h(true).m6625ox(downloadInfo.getRetryCount()).m6689b(downloadInfo.getBackUpUrlRetryCount()).m6623ox(downloadInfo.getBackUpUrls()).m6671hj(downloadInfo.getMinProgressTimeMsInterval()).m6675h(downloadInfo.getMaxProgressCount()).m6609u(z).m6669hj(downloadInfo.isNeedHttpsToHttpRetry()).m6610u(downloadInfo.getPackageName()).m6657ko(downloadInfo.getMd5()).m6646mb(downloadInfo.getExpectFileLength()).m6650lz(downloadInfo.isNeedDefaultHttpServiceBackUp()).m6599x(downloadInfo.isNeedReuseFirstConnection()).m6662je(downloadInfo.isNeedIndependentProcess()).m6645mb(downloadInfo.getEnqueueType()).m6629o(downloadInfo.isForce()).m6632nk(downloadInfo.isHeadConnectionAvailable()).m6656ko(downloadInfo.isNeedRetryDelay()).m6603ww(downloadInfo.getRetryDelayTimeArray()).m6638mb(m6928hj(downloadInfo.getDownloadSettingString())).m6600x(downloadInfo.getIconUrl()).m6611u(downloadInfo.getExecutorGroup()).m6666io(downloadInfo.isAutoInstall()));
    }

    /* renamed from: hj */
    private static JSONObject m6928hj(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: mb */
    public static void m6920mb(Activity activity) {
        if (activity != null) {
            try {
                if (activity.isFinishing()) {
                    return;
                }
                activity.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: mb */
    public static PackageInfo m6906mb(DownloadInfo downloadInfo, File file) {
        if (downloadInfo == null) {
            return C10155h.m6548mb(DownloadComponentManager.getAppContext(), file, m6927mb());
        }
        PackageInfo packageInfo = downloadInfo.getPackageInfo();
        if (packageInfo == null) {
            PackageInfo m6548mb = C10155h.m6548mb(DownloadComponentManager.getAppContext(), file, m6927mb());
            downloadInfo.setPackageInfo(m6548mb);
            return m6548mb;
        }
        return packageInfo;
    }

    /* renamed from: mb */
    public static int m6918mb(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
