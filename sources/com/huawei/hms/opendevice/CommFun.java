package com.huawei.hms.opendevice;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.opendevice.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class CommFun {

    /* renamed from: a */
    private static String f11539a;

    /* renamed from: a */
    private static String m14388a() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/self/cmdline");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    inputStreamReader.close();
                    fileInputStream.close();
                    return "";
                }
                String trim = readLine.trim();
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
                return trim;
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            HMSLog.m14112e("CommFun", "get current app processes IOException!");
            return "";
        } catch (Exception e) {
            HMSLog.m14112e("CommFun", "get current app processes exception!" + e.getMessage());
            return "";
        }
    }

    /* renamed from: b */
    public static boolean m14386b() {
        int i = HwBuildEx.VERSION.EMUI_SDK_INT;
        HMSLog.m14115d("CommFun", "Emui Api Level:" + i);
        return i > 0;
    }

    /* renamed from: c */
    public static String m14384c(Context context) {
        String parent;
        if (Build.VERSION.SDK_INT >= 24) {
            parent = context.createDeviceProtectedStorageContext().getDataDir() + "";
        } else {
            parent = context.getFilesDir().getParent();
        }
        if (TextUtils.isEmpty(parent)) {
            HMSLog.m14112e("CommFun", "get storage root path of the current user failed.");
        }
        return parent;
    }

    /* renamed from: d */
    public static long m14383d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.huawei.android.pushagent", 16384).versionCode;
        } catch (Exception unused) {
            HMSLog.m14112e("CommFun", "get nc versionCode error");
            return -1L;
        }
    }

    /* renamed from: e */
    public static boolean m14382e(Context context) {
        return m14386b() && HwBuildEx.VERSION.EMUI_SDK_INT < 21 && m14383d(context) < 110001400;
    }

    /* renamed from: b */
    private static String m14385b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return "";
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() != 0) {
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid && runningAppProcessInfo.processName != null) {
                    HMSLog.m14110i("CommFun", "info.pid -> " + runningAppProcessInfo.pid + ", info.processName -> " + runningAppProcessInfo.processName);
                    return runningAppProcessInfo.processName;
                }
            }
            return "";
        }
        HMSLog.m14109w("CommFun", "get running app processes null!");
        return "";
    }

    /* renamed from: a */
    public static String m14387a(Context context) {
        if (!TextUtils.isEmpty(f11539a)) {
            return f11539a;
        }
        String m14385b = m14385b(context);
        f11539a = m14385b;
        if (!TextUtils.isEmpty(m14385b)) {
            return f11539a;
        }
        String m14388a = m14388a();
        f11539a = m14388a;
        return m14388a;
    }
}
