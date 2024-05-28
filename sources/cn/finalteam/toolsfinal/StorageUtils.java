package cn.finalteam.toolsfinal;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class StorageUtils {
    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String INDIVIDUAL_DIR_NAME = "cache";

    private StorageUtils() {
    }

    public static File getCacheDirectory(Context context) {
        return getCacheDirectory(context, true, "cache");
    }

    public static File getCacheDirectory(Context context, boolean z, String str) {
        return getCacheDirectory(context, true, z, str);
    }

    public static File getCacheDirectory(Context context, boolean z, boolean z2, String str) {
        String str2;
        try {
            str2 = Environment.getExternalStorageState();
        } catch (IncompatibleClassChangeError unused) {
            str2 = "";
        } catch (NullPointerException unused2) {
            str2 = "";
        }
        File externalCacheDir = (z && "mounted".equals(str2) && hasExternalStoragePermission(context)) ? getExternalCacheDir(context, z2, str) : null;
        if (externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        if (externalCacheDir == null) {
            return new File("/data/data/" + context.getPackageName() + "/" + str + "/");
        }
        return externalCacheDir;
    }

    public static File getIndividualCacheDirectory(Context context) {
        return getIndividualCacheDirectory(context, "cache");
    }

    public static File getIndividualCacheDirectory(Context context, String str) {
        File cacheDirectory = getCacheDirectory(context);
        File file = new File(cacheDirectory, str);
        return (file.exists() || file.mkdir()) ? file : cacheDirectory;
    }

    public static File getOwnCacheDirectory(Context context, String str) {
        File file = ("mounted".equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) ? new File(Environment.getExternalStorageDirectory(), str) : null;
        return (file == null || !(file.exists() || file.mkdirs())) ? context.getCacheDir() : file;
    }

    public static File getOwnCacheDirectory(Context context, String str, boolean z) {
        File file = (z && "mounted".equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) ? new File(Environment.getExternalStorageDirectory(), str) : null;
        return (file == null || !(file.exists() || file.mkdirs())) ? context.getCacheDir() : file;
    }

    private static File getExternalCacheDir(Context context, boolean z, String str) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), str);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return null;
            }
            try {
                new File(file, ".nomedia").createNewFile();
            } catch (IOException unused) {
            }
        }
        return file;
    }

    private static boolean hasExternalStoragePermission(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}
