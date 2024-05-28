package com.liulishuo.filedownloader.util;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.liulishuo.filedownloader.DownloadTaskAdapter;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.Util;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileDownloadUtils {
    private static final String TAG = "FileDownloadUtils";
    private static String defaultSaveRootPath;

    public static String getDefaultSaveRootPath() {
        if (!TextUtils.isEmpty(defaultSaveRootPath)) {
            return defaultSaveRootPath;
        }
        if (FileDownloadHelper.getAppContext().getExternalCacheDir() == null) {
            return Environment.getDownloadCacheDirectory().getAbsolutePath();
        }
        return FileDownloadHelper.getAppContext().getExternalCacheDir().getAbsolutePath();
    }

    public static void setDefaultSaveRootPath(String str) {
        defaultSaveRootPath = str;
    }

    public static String getDefaultSaveFilePath(String str) {
        return generateFilePath(getDefaultSaveRootPath(), generateFileName(str));
    }

    public static String generateFileName(String str) {
        return md5(str);
    }

    public static String generateFilePath(String str, String str2) {
        if (str2 != null) {
            if (str != null) {
                return String.format("%s%s%s", str, File.separator, str2);
            }
            throw new IllegalStateException("can't generate real path, the directory is null");
        }
        throw new IllegalStateException("can't generate real path, the file name is null");
    }

    public static String md5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Huh, MD5 should be supported?", e2);
        }
    }

    @Nullable
    public static DownloadTaskAdapter findDownloadTaskAdapter(DownloadTask downloadTask) {
        if (downloadTask == null) {
            Util.m13738w("FileDownloadUtils", "download task is null when find DownloadTaskAdapter");
            return null;
        }
        Object tag = downloadTask.getTag(Integer.MIN_VALUE);
        if (tag == null) {
            Util.m13738w("FileDownloadUtils", "no tag with DownloadTaskAdapter.KEY_TASK_ADAPTER");
            return null;
        } else if (tag instanceof DownloadTaskAdapter) {
            return (DownloadTaskAdapter) tag;
        } else {
            Util.m13738w("FileDownloadUtils", "download task's tag is not DownloadTaskAdapter");
            return null;
        }
    }
}
