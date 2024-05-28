package com.baidu.cloud.videocache;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Util {
    public static final String LOCAL_NOT_CONTAINS = "http";

    public static int analyzeMediaType(Uri uri) {
        String path = uri.getPath();
        if (path == null) {
            return 0;
        }
        return analyzeMediaType(path);
    }

    public static int analyzeMediaType(Uri uri, String str) {
        if (TextUtils.isEmpty(str)) {
            return analyzeMediaType(uri);
        }
        return analyzeMediaType("." + str);
    }

    public static int analyzeMediaType(String str) {
        String lowerString = toLowerString(str);
        if (lowerString.endsWith(".m3u8")) {
            return 1;
        }
        return lowerString.endsWith(".mpd") ? 2 : 0;
    }

    public static long calculateFileSize(File file) {
        if (file == null || !file.exists()) {
            return 0L;
        }
        return getSize(file);
    }

    public static long calculateFileSize(String str) {
        return calculateFileSize(new File(str));
    }

    public static void cleanDirectory(File file) {
        File[] listFiles;
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                delete(file2);
            }
        }
    }

    private static void delete(File file) {
        if (!file.isFile() || !file.exists()) {
            cleanDirectory(file);
        }
        deleteOrThrow(file);
    }

    private static void deleteOrThrow(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException(String.format("File %s can't be deleted", file.getAbsolutePath()));
        }
    }

    public static String formetFileLengthMB(long j) {
        StringBuilder sb;
        String str;
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        if (j == 0) {
            return "0.0MB";
        }
        if (j < 1073741824) {
            sb = new StringBuilder();
            sb.append(decimalFormat.format(j / 1048576.0d));
            str = "MB";
        } else {
            sb = new StringBuilder();
            sb.append(decimalFormat.format(j / 1.073741824E9d));
            str = "GB";
        }
        sb.append(str);
        return sb.toString();
    }

    public static long getSize(File file) {
        long j = 0;
        if (file == null || !file.exists()) {
            return 0L;
        }
        if (!file.isDirectory()) {
            if (file != null) {
                return file.length();
            }
            return 0L;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return 0L;
        }
        for (File file2 : listFiles) {
            j += getSize(file2);
        }
        return j;
    }

    public static File getVideoCacheDir(Context context) {
        return new File(context.getExternalCacheDir(), "video-cache");
    }

    public static boolean isLocalFile(String str) {
        return (TextUtils.isEmpty(str) || str.contains("http")) ? false : true;
    }

    public static String toLowerString(String str) {
        return str == null ? "" : str.toLowerCase(Locale.US);
    }
}
