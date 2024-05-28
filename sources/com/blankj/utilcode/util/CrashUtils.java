package com.blankj.utilcode.util;

import android.support.annotation.NonNull;
import com.blankj.utilcode.util.UtilsBridge;
import java.io.File;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class CrashUtils {
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final Thread.UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnCrashListener {
        void onCrash(CrashInfo crashInfo);
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init() {
        init("");
    }

    public static void init(@NonNull File file) {
        if (file == null) {
            throw new NullPointerException("Argument 'crashDir' of type File (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        init(file.getAbsolutePath(), (OnCrashListener) null);
    }

    public static void init(String str) {
        init(str, (OnCrashListener) null);
    }

    public static void init(OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }

    public static void init(@NonNull File file, OnCrashListener onCrashListener) {
        if (file == null) {
            throw new NullPointerException("Argument 'crashDir' of type File (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        init(file.getAbsolutePath(), onCrashListener);
    }

    public static void init(String str, OnCrashListener onCrashListener) {
        if (UtilsBridge.isSpace(str)) {
            if (UtilsBridge.isSDCardEnableByEnvironment() && Utils.getApp().getExternalFilesDir(null) != null) {
                str = Utils.getApp().getExternalFilesDir(null) + FILE_SEP + "crash" + FILE_SEP;
            } else {
                str = Utils.getApp().getFilesDir() + FILE_SEP + "crash" + FILE_SEP;
            }
        } else if (!str.endsWith(FILE_SEP)) {
            str = str + FILE_SEP;
        }
        Thread.setDefaultUncaughtExceptionHandler(getUncaughtExceptionHandler(str, onCrashListener));
    }

    private static Thread.UncaughtExceptionHandler getUncaughtExceptionHandler(final String str, final OnCrashListener onCrashListener) {
        return new Thread.UncaughtExceptionHandler() { // from class: com.blankj.utilcode.util.CrashUtils.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
                if (thread == null) {
                    throw new NullPointerException("Argument 't' of type Thread (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
                }
                if (th == null) {
                    throw new NullPointerException("Argument 'e' of type Throwable (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
                }
                String format = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss").format(new Date());
                CrashInfo crashInfo = new CrashInfo(format, th);
                OnCrashListener onCrashListener2 = OnCrashListener.this;
                if (onCrashListener2 != null) {
                    onCrashListener2.onCrash(crashInfo);
                }
                UtilsBridge.writeFileFromString(str + format + ".txt", crashInfo.toString(), true);
                if (CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(thread, th);
                }
            }
        };
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class CrashInfo {
        private UtilsBridge.FileHead mFileHeadProvider;
        private Throwable mThrowable;

        private CrashInfo(String str, Throwable th) {
            this.mThrowable = th;
            this.mFileHeadProvider = new UtilsBridge.FileHead("Crash");
            this.mFileHeadProvider.addFirst("Time Of Crash", str);
        }

        public final void addExtraHead(Map<String, String> map) {
            this.mFileHeadProvider.append(map);
        }

        public final void addExtraHead(String str, String str2) {
            this.mFileHeadProvider.append(str, str2);
        }

        public final Throwable getThrowable() {
            return this.mThrowable;
        }

        public String toString() {
            return this.mFileHeadProvider.toString() + UtilsBridge.getFullStackTrace(this.mThrowable);
        }
    }
}
