package com.baidu.minivideo.arface.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FileUtil {
    private FileUtil() {
    }

    public static void ensureDir(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                file.mkdirs();
                return;
            }
            return;
        }
        file.mkdirs();
    }

    public static void ensureParent(File file) {
        File parentFile;
        if (file == null || (parentFile = file.getParentFile()) == null || parentFile.exists()) {
            return;
        }
        parentFile.mkdirs();
    }

    public static boolean ensureDirectoryExist(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        try {
            file.mkdirs();
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static void deleteDir(File file) {
        deleteDir(file, true);
    }

    public static void deleteDir(File file, boolean z) {
        if (file == null || !file.isDirectory()) {
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    deleteDir(file2, z);
                } else {
                    file2.delete();
                }
            }
        }
        if (z) {
            file.delete();
        }
    }

    public static boolean deleteFileIfExist(File file) {
        if (file != null && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static FileOutputStream openFileOutputStream(File file) throws IOException {
        deleteFileIfExist(file);
        ensureParent(file);
        file.createNewFile();
        return new FileOutputStream(file);
    }

    public static boolean existsFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean existsFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return existsFile(new File(str));
    }

    public static boolean existsDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return existsDir(new File(str));
    }

    public static boolean existsDir(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static String getFileExtention(String str) {
        int lastIndexOf;
        if (str == null || str.length() == 0 || (lastIndexOf = str.lastIndexOf(".")) <= -1 || lastIndexOf >= str.length() - 1) {
            return null;
        }
        return str.substring(lastIndexOf + 1);
    }

    public static File[] getFlleListAsPath(String str) {
        if (str == null || !new File(str).exists()) {
            return null;
        }
        return new File(str).listFiles();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.Closeable, java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public static String readFileText(File file) {
        FileInputStream fileInputStream;
        ?? existsFile = existsFile(file);
        try {
            if (existsFile != 0) {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e) {
                    e = e;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    existsFile = 0;
                    IoUtil.closeQuietly(existsFile);
                    if (existsFile != 0) {
                        try {
                            existsFile.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    String loadContent = IoUtil.loadContent(fileInputStream);
                    IoUtil.closeQuietly(fileInputStream);
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return loadContent;
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    IoUtil.closeQuietly(fileInputStream);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
