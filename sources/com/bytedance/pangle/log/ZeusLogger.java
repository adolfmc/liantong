package com.bytedance.pangle.log;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.apm.ApmUtils;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ZeusLogger {
    public static final String TAG = "Zeus_pangle";
    public static final String TAG_ACTIVITY = "Zeus/activity_pangle";
    public static final String TAG_DOWNLOAD = "Zeus/download_pangle";
    public static final String TAG_INIT = "Zeus/init_pangle";
    public static final String TAG_INSTALL = "Zeus/install_pangle";
    public static final String TAG_LOAD = "Zeus/load_pangle";
    public static final String TAG_PAM = "Zeus/pam_pangle";
    public static final String TAG_PPM = "Zeus/ppm_pangle";
    public static final String TAG_PROVIDER = "Zeus/provider_pangle";
    public static final String TAG_RECEIVER = "Zeus/receiver_pangle";
    public static final String TAG_REPORTER = "Zeus/reporter_pangle";
    public static final String TAG_RESOURCES = "Zeus/resources_pangle";
    public static final String TAG_SERVER = "Zeus/server_pangle";
    public static final String TAG_SERVICE = "Zeus/service_pangle";
    public static final String TAG_SO = "Zeus/so_pangle";
    private static boolean sDebug = true;
    private static boolean sEnableTrace;

    public static void setDebug(boolean z) {
        sDebug = z;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void setEnableTrace(boolean z) {
        sEnableTrace = z;
    }

    public static boolean isEnableTrace() {
        return sEnableTrace;
    }

    /* renamed from: v */
    public static void m16791v(String str) {
        m16790v(null, str);
    }

    /* renamed from: v */
    public static void m16790v(String str, String str2) {
        String prefixTraceInfo = prefixTraceInfo(str2);
        if (sDebug) {
            Log.v(str, prefixTraceInfo);
        } else if (GlobalParam.getInstance().getLogger() != null) {
            GlobalParam.getInstance().getLogger().mo16423v(str, prefixTraceInfo);
        }
    }

    /* renamed from: d */
    public static void m16795d(String str) {
        m16794d(null, str);
    }

    /* renamed from: d */
    public static void m16794d(String str, String str2) {
        m16792i(str, str2);
    }

    /* renamed from: i */
    public static void m16793i(String str) {
        m16792i(null, str);
    }

    /* renamed from: i */
    public static void m16792i(String str, String str2) {
        String prefixTraceInfo = prefixTraceInfo(str2);
        if (sDebug) {
            Log.i(str, prefixTraceInfo);
        } else if (GlobalParam.getInstance().getLogger() != null) {
            GlobalParam.getInstance().getLogger().mo16424i(str, prefixTraceInfo);
        }
    }

    /* renamed from: w */
    public static void m16789w(String str) {
        m16788w(null, str);
    }

    /* renamed from: w */
    public static void m16788w(String str, String str2) {
        String prefixTraceInfo = prefixTraceInfo(str2);
        if (sDebug) {
            Log.w(str, prefixTraceInfo);
        } else if (GlobalParam.getInstance().getLogger() != null) {
            GlobalParam.getInstance().getLogger().mo16422w(str, prefixTraceInfo);
        }
    }

    /* renamed from: w */
    public static void m16787w(String str, String str2, Throwable th) {
        String prefixTraceInfo = prefixTraceInfo(str2);
        if (sDebug) {
            Log.w(str, prefixTraceInfo, th);
        } else if (GlobalParam.getInstance().getLogger() != null) {
            GlobalParam.getInstance().getLogger().mo16421w(str, prefixTraceInfo, th);
        }
    }

    public static void errReport(String str, String str2) {
        RuntimeException runtimeException = new RuntimeException();
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, 1, stackTrace.length - 1));
        errReport(str, str2, runtimeException, false);
    }

    public static void errReport(String str, String str2, @NonNull Throwable th) {
        errReport(str, str2, th, true);
    }

    private static void errReport(String str, String str2, @NonNull Throwable th, boolean z) {
        if (sDebug) {
            Log.e(str, str2, z ? th : null);
        } else if (GlobalParam.getInstance().getLogger() != null) {
            GlobalParam.getInstance().getLogger().mo16425e(str, str2, z ? th : null);
        }
        ApmUtils.getApmInstance().reportError(str2, th);
    }

    private static String prefixTraceInfo(String str) {
        if (sEnableTrace) {
            return str + getTraceInfo();
        }
        return str;
    }

    private static String getTraceInfo() {
        try {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            StackTraceElement stackTraceElement = null;
            int i = 1;
            while (true) {
                if (i >= stackTrace.length) {
                    break;
                } else if (!TextUtils.equals(stackTrace[i].getClassName(), ZeusLogger.class.getName())) {
                    stackTraceElement = stackTrace[i];
                    break;
                } else {
                    i++;
                }
            }
            if (stackTraceElement != null) {
                return "\t\t[" + stackTraceElement.toString() + "]";
            }
            return "\t\t[No Trace Info]";
        } catch (Exception e) {
            e.printStackTrace();
            return "\t\t[No Trace Info]";
        }
    }
}
