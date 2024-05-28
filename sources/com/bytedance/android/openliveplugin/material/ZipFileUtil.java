package com.bytedance.android.openliveplugin.material;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ZipFileUtil {
    private static final int NUM_2048 = 2048;

    private ZipFileUtil() {
    }

    public static void unZipFolder(String str, String str2) {
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                String name = nextEntry.getName();
                if (!name.contains("..")) {
                    if (nextEntry.isDirectory()) {
                        new File(str2 + File.separator + name).mkdirs();
                    } else {
                        File file = new File(str2 + File.separator + name);
                        if (file.exists()) {
                            file.delete();
                        } else {
                            file.getParentFile().mkdirs();
                        }
                        file.createNewFile();
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                        byte[] bArr = new byte[2048];
                        while (true) {
                            int read = zipInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                    }
                }
            } else {
                zipInputStream.close();
                return;
            }
        }
    }

    public static boolean removeDir(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                return file.delete();
            }
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    removeDir(file2);
                } else {
                    file2.delete();
                }
            }
            return file.delete();
        }
        return file.delete();
    }

    public static String readTextFile(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read();
                if (read == -1) {
                    break;
                }
                sb.append((char) read);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
