package com.chinaunicon.jtwifilib.core.crashreport;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CrashFileManager {
    private static final int SIZE = 4096;
    private static final String TAG = "com.chinaunicon.jtwifilib.core.crashreport.CrashFileManager";
    private static volatile CrashFileManager instance;
    private Map<String, String> infos = new HashMap();
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private CrashFileManager() {
    }

    public static CrashFileManager getInstance() {
        if (instance == null) {
            synchronized (CrashFileManager.class) {
                if (instance == null) {
                    instance = new CrashFileManager();
                }
            }
        }
        return instance;
    }

    public void collectDeviceInfo(Context context) {
        Field[] declaredFields;
        this.infos.clear();
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String charSequence = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                String str = packageInfo.versionName;
                this.infos.put("crashType", "1");
                this.infos.put("appName", charSequence);
                this.infos.put("versionName", str);
                this.infos.put("versionCode", packageInfo.versionCode + "");
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(null) instanceof String[]) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String str2 : (String[]) field.get(null)) {
                        stringBuffer.append(str2);
                    }
                    this.infos.put(field.getName(), stringBuffer.toString());
                    Log.d(TAG, field.getName() + " : " + stringBuffer.toString());
                } else {
                    this.infos.put(field.getName(), field.get(null).toString());
                    Log.d(TAG, field.getName() + " : " + field.get(null).toString());
                }
            } catch (IllegalAccessException | IllegalArgumentException unused2) {
            }
        }
    }

    public void saveCrashInfo(Throwable th, Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.infos.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "\n");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        StringBuffer stringBuffer2 = new StringBuffer();
        String obj = stringWriter.toString();
        stringBuffer.append(obj);
        stringBuffer2.append(obj);
        stringBuffer.append("\n");
        stringBuffer.append(ThreadCollector.collect(Thread.currentThread()));
        stringBuffer2.append("\n" + ThreadCollector.collect(Thread.currentThread()));
        stringBuffer.append("\n");
        stringBuffer.append(DumpSysCollector.collectMemInfo());
        stringBuffer2.append("\n" + DumpSysCollector.collectMemInfo());
        this.infos.put("crashInfo", stringBuffer2.toString());
        JtUploadLog.getInstance(context).saveToDb("2", JtGsonUtil.getInstance().toJson(this.infos), "CrashHandler");
    }
}
