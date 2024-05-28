package com.baidu.p120ar.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.ZipUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ZipUtils {
    public static boolean isZipFile(String str) {
        try {
            try {
                closeZipFile(new ZipFile(str));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                closeZipFile(null);
                return false;
            }
        } catch (Throwable th) {
            closeZipFile(null);
            throw th;
        }
    }

    public static void closeZipFile(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean unzip(File file, File file2) {
        ZipInputStream zipInputStream = null;
        try {
            try {
                ZipInputStream zipInputStream2 = new ZipInputStream(new CheckedInputStream(new FileInputStream(file), new CRC32()));
                try {
                    unzip(zipInputStream2, file2);
                    IoUtils.closeQuietly(zipInputStream2);
                    return true;
                } catch (IOException e) {
                    e = e;
                    zipInputStream = zipInputStream2;
                    e.printStackTrace();
                    IoUtils.closeQuietly(zipInputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    zipInputStream = zipInputStream2;
                    IoUtils.closeQuietly(zipInputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static void unzip(ZipInputStream zipInputStream, File file) throws IOException {
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            File file2 = new File(file, nextEntry.getName());
            FileUtils.ensureParent(file2);
            if (nextEntry.isDirectory()) {
                file2.mkdirs();
            } else {
                IoUtils.copyStream(zipInputStream, file2);
            }
            zipInputStream.closeEntry();
        }
    }
}
