package cn.finalteam.toolsfinal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final CrashHandler INSTANCE = new CrashHandler();
    private Context mContext;
    private boolean mCrashSave;
    private String mCrashSaveTargetFolder;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Map<String, String> mDeviceInfoMap = new HashMap();
    private OnCrashListener mOnCrashListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnCrashListener {
        void onCrash(Context context, String str);
    }

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public CrashHandler init(Context context) {
        this.mContext = context;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        return this;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (!handleException(th) && (uncaughtExceptionHandler = this.mDefaultHandler) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [cn.finalteam.toolsfinal.CrashHandler$1] */
    public boolean handleException(Throwable th) {
        Thread.setDefaultUncaughtExceptionHandler(null);
        if (th == null) {
            return false;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        final String obj = stringWriter.toString();
        new Thread() { // from class: cn.finalteam.toolsfinal.CrashHandler.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Looper.prepare();
                if (CrashHandler.this.mOnCrashListener != null) {
                    CrashHandler.this.mOnCrashListener.onCrash(CrashHandler.this.mContext, obj);
                }
                Looper.loop();
            }
        }.start();
        if (this.mCrashSave) {
            collectDeviceInfo(this.mContext);
            saveCrashInfo2File(obj);
            return true;
        }
        return true;
    }

    public CrashHandler setOnCrashListener(OnCrashListener onCrashListener) {
        this.mOnCrashListener = onCrashListener;
        return this;
    }

    public CrashHandler setCrashSave(boolean z) {
        this.mCrashSave = z;
        return this;
    }

    public CrashHandler setCrashSaveTargetFolder(String str) {
        this.mCrashSaveTargetFolder = str;
        return this;
    }

    private void collectDeviceInfo(Context context) {
        Field[] declaredFields;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                this.mDeviceInfoMap.put("versionName", packageInfo.versionName == null ? "null" : packageInfo.versionName);
                this.mDeviceInfoMap.put("versionCode", packageInfo.versionCode + "");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                this.mDeviceInfoMap.put(field.getName(), field.get("").toString());
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            }
        }
    }

    private String saveCrashInfo2File(String str) {
        File file;
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.mDeviceInfoMap.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "\r\n");
        }
        stringBuffer.append(str);
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = "crash-" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "-" + currentTimeMillis + ".log";
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                if (StringUtils.isEmpty(this.mCrashSaveTargetFolder)) {
                    file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "crash");
                } else {
                    file = new File(this.mCrashSaveTargetFolder);
                }
                if (!file.exists()) {
                    file.mkdir();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(file, str2));
                fileOutputStream.write(stringBuffer.toString().getBytes());
                fileOutputStream.close();
                return str2;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
