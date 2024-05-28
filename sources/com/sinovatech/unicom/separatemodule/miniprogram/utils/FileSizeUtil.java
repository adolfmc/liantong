package com.sinovatech.unicom.separatemodule.miniprogram.utils;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FileSizeUtil {
    public static final int SIZETYPE_B = 1;
    public static final int SIZETYPE_GB = 4;
    public static final int SIZETYPE_KB = 2;
    public static final int SIZETYPE_MB = 3;

    public static double getFileOrFilesSize(String str, int i) {
        long j;
        File file = new File(str);
        try {
            if (file.isDirectory()) {
                j = getFileSizes(file);
            } else {
                j = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
            j = 0;
        }
        return FormetFileSize(j, i);
    }

    public static String getAutoFileOrFilesSize(String str) {
        long j;
        File file = new File(str);
        try {
            if (file.isDirectory()) {
                j = getFileSizes(file);
            } else {
                j = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
            j = 0;
        }
        return FormetFileSize(j);
    }

    private static long getFileSize(File file) throws Exception {
        if (file.exists()) {
            return new FileInputStream(file).available();
        }
        file.createNewFile();
        Log.e("获取文件大小", "文件不存在!");
        return 0L;
    }

    private static long getFileSizes(File file) throws Exception {
        long fileSize;
        File[] listFiles = file.listFiles();
        long j = 0;
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                fileSize = getFileSizes(listFiles[i]);
            } else {
                fileSize = getFileSize(listFiles[i]);
            }
            j += fileSize;
        }
        return j;
    }

    private static String FormetFileSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (j == 0) {
            return "0B";
        }
        if (j < 1024) {
            return decimalFormat.format(j) + "B";
        } else if (j < 1048576) {
            return decimalFormat.format(j / 1024.0d) + "KB";
        } else if (j < 1073741824) {
            return decimalFormat.format(j / 1048576.0d) + "MB";
        } else {
            return decimalFormat.format(j / 1.073741824E9d) + "GB";
        }
    }

    private static double FormetFileSize(long j, int i) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        switch (i) {
            case 1:
                return Double.valueOf(decimalFormat.format(j)).doubleValue();
            case 2:
                return Double.valueOf(decimalFormat.format(j / 1024.0d)).doubleValue();
            case 3:
                return Double.valueOf(decimalFormat.format(j / 1048576.0d)).doubleValue();
            case 4:
                return Double.valueOf(decimalFormat.format(j / 1.073741824E9d)).doubleValue();
            default:
                return 0.0d;
        }
    }
}
