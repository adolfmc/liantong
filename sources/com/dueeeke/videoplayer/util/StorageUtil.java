package com.dueeeke.videoplayer.util;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class StorageUtil {
    private static final String INDIVIDUAL_DIR_NAME = "video-cache";
    private static final Logger LOG = LoggerFactory.getLogger("StorageUtil");

    public static File getIndividualCacheDirectory(Context context) {
        return new File(getCacheDirectory(context, true), "video-cache");
    }

    private static File getCacheDirectory(Context context, boolean z) {
        try {
            Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
        }
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            String str = context.getFilesDir().getPath() + context.getPackageName() + "/cache/";
            LOG.warn("Can't define system cache directory! '" + str + "%s' will be used.");
            return new File(str);
        }
        return externalCacheDir;
    }

    private static File getExternalCacheDir(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        LOG.warn("Unable to create external cache directory");
        return null;
    }

    public static boolean deleteFiles(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (!file2.isDirectory() && file2.exists() && !file2.delete()) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public static boolean deleteFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                return file.delete();
            }
            for (String str2 : file.list()) {
                deleteFile(str + File.separator + str2);
            }
            return file.delete();
        }
        return true;
    }
}
