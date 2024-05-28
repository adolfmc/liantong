package com.p319ss.android.socialbase.downloader.logger;

import android.text.TextUtils;
import android.util.Log;
import com.p319ss.android.socialbase.downloader.impls.DownloadProxy;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.logger.Logger */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Logger {
    public static final boolean DEBUG = false;
    private static final String DOWNLOAD_TAG_PREFIX = "Downloader-";
    private static final String TAG = "DownloaderLogger";
    private static int mLevel = 4;
    private static ILogWritter sLogWritter;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.logger.Logger$ILogWritter */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class ILogWritter {
        public void logD(String str, String str2) {
        }

        public void logE(String str, String str2) {
        }

        public void logE(String str, String str2, Throwable th) {
        }

        public void logI(String str, String str2) {
        }

        public void logI(String str, String str2, Throwable th) {
        }

        public void logK(String str, String str2) {
        }

        public void logV(String str, String str2) {
        }

        public void logW(String str, String str2) {
        }

        public void logW(String str, String str2, Throwable th) {
        }
    }

    public static void setLogLevel(int i) {
        mLevel = i;
    }

    public static void setLogLevelInDownloaderProcess(int i) {
        if (DownloadProxy.get(true) != null) {
            mLevel = i;
        }
    }

    public static int getLogLevel() {
        return mLevel;
    }

    public static boolean debug() {
        return mLevel <= 3;
    }

    /* renamed from: v */
    public static void m6464v(String str) {
        m6463v("DownloaderLogger", str);
    }

    /* renamed from: v */
    public static void m6463v(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 2) {
            Log.v(str, str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logV(downloaderTag(str), str2);
        }
    }

    /* renamed from: v */
    public static void m6462v(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 2) {
            Log.v(str, str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            String downloaderTag = downloaderTag(str);
            iLogWritter.logV(downloaderTag, str2 + th);
        }
    }

    /* renamed from: d */
    public static void m6476d(String str) {
        m6475d("DownloaderLogger", str);
    }

    public static String downloaderTag(String str) {
        if (TextUtils.isEmpty(str)) {
            return "DownloaderLogger";
        }
        return "Downloader-" + str;
    }

    /* renamed from: d */
    public static void m6475d(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 3) {
            Log.d(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logD(downloaderTag(str), str2);
        }
    }

    /* renamed from: d */
    public static void m6474d(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 3) {
            Log.d(downloaderTag(str), str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            String downloaderTag = downloaderTag(str);
            iLogWritter.logD(downloaderTag, str2 + th);
        }
    }

    /* renamed from: i */
    public static void m6470i(String str) {
        m6469i("DownloaderLogger", str);
    }

    /* renamed from: i */
    public static void m6469i(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 4) {
            Log.i(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logI(downloaderTag(str), str2);
        }
    }

    /* renamed from: i */
    public static void m6468i(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 4) {
            Log.i(downloaderTag(str), str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logI(downloaderTag(str), str2, th);
        }
    }

    /* renamed from: w */
    public static void m6461w(String str) {
        m6460w("DownloaderLogger", str);
    }

    /* renamed from: w */
    public static void m6460w(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 5) {
            Log.w(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logW(downloaderTag(str), str2);
        }
    }

    /* renamed from: w */
    public static void m6459w(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 5) {
            Log.w(downloaderTag(str), str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logW(downloaderTag(str), str2, th);
        }
    }

    /* renamed from: e */
    public static void m6473e(String str) {
        m6472e("DownloaderLogger", str);
    }

    /* renamed from: e */
    public static void m6472e(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (mLevel <= 6) {
            Log.e(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logE(downloaderTag(str), str2);
        }
    }

    /* renamed from: e */
    public static void m6471e(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (mLevel <= 6) {
            Log.e(downloaderTag(str), str2, th);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logE(downloaderTag(str), str2, th);
        }
    }

    /* renamed from: k */
    public static void m6467k(String str) {
        m6466k("DownloaderLogger", str);
    }

    /* renamed from: k */
    public static void m6466k(String str, String str2) {
        if (mLevel <= 3) {
            Log.d(downloaderTag(str), str2);
        }
        ILogWritter iLogWritter = sLogWritter;
        if (iLogWritter != null) {
            iLogWritter.logK(downloaderTag(str), str2);
        }
    }

    /* renamed from: st */
    public static void m6465st(String str, int i) {
        try {
            throw new Exception();
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 1; i2 < Math.min(i, stackTrace.length); i2++) {
                if (i2 > 1) {
                    sb.append("\n");
                }
                sb.append(getSimpleClassName(stackTrace[i2].getClassName()));
                sb.append(".");
                sb.append(stackTrace[i2].getMethodName());
            }
            m6463v(downloaderTag(str), sb.toString());
        }
    }

    private static String getSimpleClassName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf < 0 ? str : str.substring(lastIndexOf + 1);
    }

    public static void throwException(Throwable th) {
        if (th == null) {
            return;
        }
        th.printStackTrace();
        if (debug()) {
            throw new RuntimeException("Error! Now in debug, we alert to you to correct it !", th);
        }
    }

    public static void alertErrorInfo(String str) {
        if (debug()) {
            throw new IllegalStateException(str);
        }
    }

    public static void registerLogHandler(ILogWritter iLogWritter) {
        sLogWritter = iLogWritter;
    }
}
