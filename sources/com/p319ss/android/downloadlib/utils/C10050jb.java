package com.p319ss.android.downloadlib.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.p319ss.android.download.api.config.InterfaceC9803lc;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.p320b.C9779ox;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9915b;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.utils.jb */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10050jb {

    /* renamed from: ox */
    private static Object[] f19380ox = new Object[0];

    /* renamed from: b */
    private static Object[] f19377b = new Object[73];

    /* renamed from: mb */
    static final char[] f19379mb = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: hj */
    private static String f19378hj = null;

    /* renamed from: mb */
    public static boolean m7046mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    /* renamed from: ox */
    public static boolean m7030ox(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    /* renamed from: mb */
    public static long m7041mb(JSONObject jSONObject, String str) {
        return C9779ox.m7968mb(jSONObject, str);
    }

    /* renamed from: mb */
    public static JSONObject m7039mb(JSONObject jSONObject, JSONObject jSONObject2) {
        return C9779ox.m7967mb(jSONObject, jSONObject2);
    }

    @NonNull
    /* renamed from: mb */
    public static JSONObject m7042mb(JSONObject jSONObject) {
        return C9779ox.m7969mb(jSONObject);
    }

    @NonNull
    /* renamed from: mb */
    public static JSONObject m7035mb(JSONObject... jSONObjectArr) {
        return C9779ox.m7965mb(jSONObjectArr);
    }

    /* renamed from: mb */
    public static boolean m7053mb(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
            if (queryIntentActivities != null) {
                return !queryIntentActivities.isEmpty();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: mb */
    public static PackageInfo m7049mb(C9837ox c9837ox) {
        DownloadInfo downloadInfo;
        if (c9837ox == null || (downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(c9837ox.mo7479m())) == null) {
            return null;
        }
        try {
            return C10085b.m6908mb(C9940x.getContext(), downloadInfo, downloadInfo.getSavePath(), downloadInfo.getName());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: mb */
    public static Drawable m7052mb(Context context, String str) {
        PackageManager packageManager;
        PackageInfo packageArchiveInfo;
        if (context != null && !TextUtils.isEmpty(str) && (packageArchiveInfo = (packageManager = context.getPackageManager()).getPackageArchiveInfo(str, 0)) != null) {
            ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
            applicationInfo.sourceDir = str;
            applicationInfo.publicSourceDir = str;
            try {
                return applicationInfo.loadIcon(packageManager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: ox */
    public static int m7032ox(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return -1;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    /* renamed from: b */
    public static Drawable m7063b(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                return packageManager.getApplicationInfo(str, 0).loadIcon(packageManager);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return null;
    }

    /* renamed from: hj */
    public static boolean m7060hj(Context context, String str) {
        if (context == null) {
            context = C9940x.getContext();
        }
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: mb */
    public static C9915b m7044mb(String str, int i, String str2) {
        C9915b c9915b = new C9915b();
        if (TextUtils.isEmpty(str)) {
            return c9915b;
        }
        try {
            PackageInfo packageInfo = C9940x.getContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                c9915b.m7495ox(packageInfo.versionCode);
                c9915b.m7497mb(C9915b.f19093ox);
                InterfaceC9803lc m7367ko = C9940x.m7367ko();
                if (m7367ko != null && m7367ko.m7944mb() && !m7056mb(packageInfo.versionCode, i, packageInfo.versionName, str2)) {
                    c9915b.m7497mb(C9915b.f19091b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c9915b;
    }

    /* renamed from: mb */
    private static boolean m7056mb(int i, int i2, String str, String str2) {
        if (i2 == 0 && TextUtils.isEmpty(str2)) {
            return true;
        }
        return (i2 > 0 && i >= i2) || m7043mb(str, str2) >= 0;
    }

    /* renamed from: ox */
    public static boolean m7031ox(C9837ox c9837ox) {
        if (c9837ox == null) {
            return false;
        }
        return m7044mb(c9837ox.mo7489h(), c9837ox.m7754q(), c9837ox.m7816bv()).m7498mb();
    }

    /* renamed from: mb */
    public static boolean m7050mb(DownloadModel downloadModel) {
        if (downloadModel == null) {
            return false;
        }
        return m7044mb(downloadModel.getPackageName(), downloadModel.getVersionCode(), downloadModel.getVersionName()).m7498mb();
    }

    /* renamed from: ox */
    public static boolean m7033ox(Context context, Intent intent) {
        if (intent == null) {
            return false;
        }
        if (context == null) {
            context = C9940x.getContext();
        }
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    /* renamed from: mb */
    public static boolean m7051mb(Context context, String str, String str2) {
        PackageInfo packageArchiveInfo;
        PackageInfo packageInfo;
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            File file = new File(str);
            if (file.exists() && (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 0)) != null && packageArchiveInfo.packageName.equals(str2)) {
                int i = packageArchiveInfo.versionCode;
                try {
                    packageInfo = context.getPackageManager().getPackageInfo(str2, 0);
                } catch (PackageManager.NameNotFoundException unused) {
                    packageInfo = null;
                }
                if (packageInfo == null) {
                    return false;
                }
                return i == packageInfo.versionCode;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: h */
    public static boolean m7061h(Context context, String str) {
        PackageInfo packageArchiveInfo;
        PackageInfo packageInfo;
        if (context == null || str == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            File file = new File(str);
            if (!file.exists() || (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 0)) == null) {
                return false;
            }
            String str2 = packageArchiveInfo.packageName;
            int i = packageArchiveInfo.versionCode;
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str2, 0);
            } catch (PackageManager.NameNotFoundException unused) {
                packageInfo = null;
            }
            if (packageInfo == null) {
                return false;
            }
            return i <= packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: u */
    public static Intent m7028u(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return null;
        }
        if (!launchIntentForPackage.hasCategory("android.intent.category.LAUNCHER")) {
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(2097152);
        launchIntentForPackage.addFlags(268435456);
        return launchIntentForPackage;
    }

    /* renamed from: ko */
    public static Signature[] m7059ko(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo != null) {
                return packageInfo.signatures;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: ww */
    public static Signature[] m7027ww(Context context, String str) {
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 64);
            if (packageArchiveInfo != null) {
                return packageArchiveInfo.signatures;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: mb */
    public static boolean m7038mb(Signature[] signatureArr, Signature[] signatureArr2) {
        if (signatureArr == signatureArr2) {
            return true;
        }
        if (signatureArr == null || signatureArr2 == null || signatureArr.length != signatureArr2.length) {
            return false;
        }
        for (int i = 0; i < signatureArr.length; i++) {
            if ((signatureArr[i] == null && signatureArr2[i] != null) || (signatureArr[i] != null && !signatureArr[i].equals(signatureArr2[i]))) {
                return false;
            }
        }
        return true;
    }

    @WorkerThread
    /* renamed from: b */
    public static boolean m7062b(String str) {
        File file;
        Context context = C9940x.getContext();
        if (!TextUtils.isEmpty(str) && m7060hj(context, str)) {
            int i = context.getApplicationInfo().targetSdkVersion;
            if (C9940x.m7364lz().optInt("get_ext_dir_mode") != 0 || Build.VERSION.SDK_INT < 29 || ((i != 29 || Environment.isExternalStorageLegacy()) && i <= 29)) {
                try {
                    if (Build.VERSION.SDK_INT >= 29 && context.getApplicationInfo().targetSdkVersion >= 29 && C9940x.m7364lz().optInt("get_ext_dir_mode") == 1) {
                        file = m7058lz(context, str);
                    } else {
                        String path = Environment.getExternalStorageDirectory().getPath();
                        file = new File(path, "android/data/" + str);
                    }
                    if (file.exists()) {
                        long m6980mb = C10068u.m6980mb(file);
                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
                        if (packageInfo != null) {
                            if (packageInfo.lastUpdateTime < m6980mb) {
                                return true;
                            }
                        }
                        return false;
                    }
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: lz */
    public static File m7058lz(Context context, String str) {
        File parentFile = context.getExternalFilesDir(null).getParentFile();
        String parent = parentFile != null ? parentFile.getParent() : null;
        File file = new File(parent + File.separator + str);
        StringBuilder sb = new StringBuilder();
        sb.append("getExtDir: file.toString()-->");
        sb.append(file.toString());
        Logger.m6475d("ToolUtils", sb.toString());
        return file;
    }

    /* renamed from: mb */
    public static int m7054mb(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: mb */
    public static String m7045mb(String str, int i) {
        return i == 0 ? "" : (TextUtils.isEmpty(str) || str.length() <= i) ? str : str.substring(0, i);
    }

    /* renamed from: mb */
    public static int m7043mb(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (str.equals(str2)) {
                    return 0;
                }
                String[] split = str.split("\\.");
                String[] split2 = str2.split("\\.");
                int min = Math.min(split.length, split2.length);
                int i = 0;
                int i2 = 0;
                while (i < min) {
                    i2 = Integer.parseInt(split[i]) - Integer.parseInt(split2[i]);
                    if (i2 != 0) {
                        break;
                    }
                    i++;
                }
                if (i2 != 0) {
                    return i2 > 0 ? 1 : -1;
                }
                for (int i3 = i; i3 < split.length; i3++) {
                    if (Integer.parseInt(split[i3]) > 0) {
                        return 1;
                    }
                }
                while (i < split2.length) {
                    if (Integer.parseInt(split2[i]) > 0) {
                        return -1;
                    }
                    i++;
                }
                return 0;
            }
            return -2;
        } catch (Exception unused) {
            return -2;
        }
    }

    /* renamed from: mb */
    public static String m7036mb(String... strArr) {
        return C9779ox.m7966mb(strArr);
    }

    @NonNull
    /* renamed from: mb */
    public static <T> T m7037mb(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("args is null");
        }
        for (T t : tArr) {
            if (t != null) {
                return t;
            }
        }
        throw new IllegalArgumentException("args is null");
    }

    /* renamed from: mb */
    public static long m7055mb(long j) {
        try {
            return m7047mb(Environment.getExternalStorageDirectory(), j);
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    /* renamed from: mb */
    public static long m7047mb(File file, long j) {
        if (file == null) {
            return j;
        }
        try {
            return DownloadUtils.getAvailableSpaceBytes(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    /* renamed from: mb */
    public static long m7048mb(File file) {
        if (file == null) {
            return -1L;
        }
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getTotalBytes();
            }
            return -1L;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1L;
        }
    }

    /* renamed from: mb */
    public static boolean m7057mb() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: mb */
    public static void m7040mb(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            jSONObject.putOpt(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: ox */
    public static void m7034ox() {
        try {
            if (C9940x.m7372h().mo7900mb(C9940x.getContext(), "android.permission.REORDER_TASKS")) {
                ActivityManager activityManager = (ActivityManager) C9940x.getContext().getSystemService("activity");
                for (ActivityManager.RunningTaskInfo runningTaskInfo : activityManager.getRunningTasks(20)) {
                    if (C9940x.getContext().getPackageName().equals(runningTaskInfo.topActivity.getPackageName())) {
                        activityManager.moveTaskToFront(runningTaskInfo.id, 1);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    /* renamed from: ox */
    public static HashMap<String, String> m7029ox(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (jSONObject != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.optString(next));
                }
                return hashMap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }
}
