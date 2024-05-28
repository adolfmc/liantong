package com.sinovatech.unicom.separatemodule.videocenter.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.sinovatech.unicom.common.URLSet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LogToFile {
    private static String TAG = "LogToFile";
    private static String logPath;

    public static void init(Context context) {
        logPath = getFilePath(context) + "/Logs";
    }

    private static String getFilePath(Context context) {
        if ("mounted".equals("mounted") || !Environment.isExternalStorageRemovable()) {
            return context.getExternalFilesDir(null).getPath();
        }
        return context.getFilesDir().getPath();
    }

    public static void writeToFile(String str) {
        if (!URLSet.Debug_mode) {
            return;
        }
        if (logPath == null) {
            Log.d(TAG, "logPath == null ，未初始化LogToFile");
            return;
        }
        String str2 = logPath + "/log_hiBoardLog.log";
        String str3 = str + "\n";
        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str2, true)));
                    try {
                        bufferedWriter2.write(str3);
                        bufferedWriter2.close();
                    } catch (FileNotFoundException e) {
                        e = e;
                        bufferedWriter = bufferedWriter2;
                        e.printStackTrace();
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    } catch (IOException e2) {
                        e = e2;
                        bufferedWriter = bufferedWriter2;
                        e.printStackTrace();
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e4) {
                    e = e4;
                } catch (IOException e5) {
                    e = e5;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }
}
