package com.tydic.tydic_tracker.app;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class MemoryTools {
    public static final String MEMFREE = "MemFree";
    public static final String MEMTOTAL = "MemTotal";
    private static final String MEM_INFO_PATH = "/proc/meminfo";
    private static final String TAG = "com.tydic.tydic_tracker.app.MemoryTools";

    public static long getMemoryFree(Context context) {
        return getMemInfoIype(context, "MemFree");
    }

    public static long getMemInfoIype(Context context, String str) {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 4096);
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
            } while (!readLine.contains(str));
            bufferedReader.close();
            return Integer.valueOf(readLine.split("\\s+")[1]).intValue() * 1024;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0L;
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static String getInternalToatalSpace(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = statFs.getBlockSize();
        statFs.getAvailableBlocks();
        return Formatter.formatFileSize(context, statFs.getBlockCount() * blockSize);
    }
}
