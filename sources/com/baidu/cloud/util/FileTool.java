package com.baidu.cloud.util;

import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FileTool {
    public static boolean isSDMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean isExists(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    public static void deleteAllFiles(File file) {
        File[] listFiles;
        if (file == null || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        for (File file2 : listFiles) {
            if (!file2.isDirectory()) {
                file2.delete();
            }
        }
    }

    public static void deleteAllFiles(File file, String str) {
        File[] listFiles;
        if (file == null || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        for (File file2 : listFiles) {
            if (!TextUtils.equals(str, file2.getAbsolutePath())) {
                file2.delete();
            }
        }
    }

    public static void deleteFiles(List<File> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            File file = list.get(i);
            if (file != null && file.exists()) {
                deleteDir(file);
            }
        }
    }

    public static boolean deleteDir(File file) {
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!deleteDir(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.delete();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile(File file) {
        try {
            return file.delete();
        } catch (Exception unused) {
            return false;
        }
    }

    public static void unzipFile(File file, String str) throws ZipException, IOException {
        unzipFile(file, new File(str));
    }

    public static void unzipFile(File file, File file2) throws ZipException, IOException {
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String absolutePath = file2.getAbsolutePath();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            String name = nextElement.getName();
            if (!"./".equals(name) && !".".equals(name) && !name.endsWith("/")) {
                InputStream inputStream = zipFile.getInputStream(nextElement);
                File file3 = new File(absolutePath + File.separator + name);
                if (!file3.exists()) {
                    File parentFile = file3.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file3.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                byte[] bArr = new byte[10240];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }
    }
}
