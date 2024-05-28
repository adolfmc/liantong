package com.example.asus.detectionandalign.utils;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LogUtils {

    /* renamed from: a */
    private static LogUtils f10081a;

    /* renamed from: f */
    private static SimpleDateFormat f10082f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

    /* renamed from: b */
    private boolean f10083b;

    /* renamed from: c */
    private final String f10084c = "/sdcard/";

    /* renamed from: d */
    private final String f10085d = "CustomSaveLog.txt";

    /* renamed from: e */
    private final int f10086e = 20971520;

    private LogUtils(boolean z) {
        this.f10083b = false;
        this.f10083b = z;
    }

    /* renamed from: a */
    public static synchronized LogUtils m15966a(boolean z) {
        LogUtils logUtils;
        synchronized (LogUtils.class) {
            if (f10081a == null) {
                synchronized (LogUtils.class) {
                    if (f10081a == null) {
                        f10081a = new LogUtils(z);
                    }
                }
            }
            logUtils = f10081a;
        }
        return logUtils;
    }

    /* renamed from: d */
    private void m15963d(String str, String str2) {
        FileOutputStream fileOutputStream;
        File file;
        if (TextUtils.isEmpty(str)) {
            str = "--Tag null--";
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    file = new File("/sdcard/", "CustomSaveLog.txt");
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(file, true);
                    try {
                    } catch (IOException e) {
                        e = e;
                        fileOutputStream2 = fileOutputStream;
                        e.printStackTrace();
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        return;
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (IOException e3) {
                e = e3;
            }
            if (fileOutputStream.getChannel().size() > 20971520) {
                fileOutputStream.close();
                file.delete();
                try {
                    fileOutputStream.close();
                    return;
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return;
                }
            }
            fileOutputStream.write((f10082f.format(new Date()) + "/" + str + "=====>>[" + str2 + "]\n").getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m15967a(String str, String str2) {
        if (this.f10083b) {
            m15963d(str + "  E:", str2);
        }
        Log.e("CustomSaveLog：" + str, str2);
    }

    /* renamed from: b */
    public void m15965b(String str, String str2) {
        if (this.f10083b) {
            m15963d(str + "  I:", str2);
        }
        Log.i("CustomSaveLog：" + str, str2);
    }

    /* renamed from: c */
    public void m15964c(String str, String str2) {
        if (this.f10083b) {
            m15963d(str + "  V:", str2);
        }
        Log.v("CustomSaveLog：" + str, str2);
    }
}
