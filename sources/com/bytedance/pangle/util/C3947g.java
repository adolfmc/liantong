package com.bytedance.pangle.util;

import android.content.Context;
import android.os.Build;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.g */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3947g {

    /* renamed from: a */
    public static String f9374a;

    /* renamed from: b */
    static String f9375b;

    /* renamed from: a */
    public static void m16632a(String str) {
        m16634a(new File(str));
    }

    /* renamed from: a */
    public static void m16634a(File file) {
        File[] listFiles;
        if (file == null || !file.exists()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                Files.walkFileTree(Paths.get(file.getAbsolutePath(), new String[0]), new SimpleFileVisitor<Path>() { // from class: com.bytedance.pangle.util.h.1
                    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                    public final /* synthetic */ FileVisitResult postVisitDirectory(Object obj, IOException iOException) {
                        return m16624b((Path) obj);
                    }

                    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                    public final /* synthetic */ FileVisitResult visitFile(Object obj, BasicFileAttributes basicFileAttributes) {
                        return m16625a((Path) obj);
                    }

                    /* renamed from: a */
                    private static FileVisitResult m16625a(Path path) {
                        if (path != null) {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException unused) {
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    /* renamed from: b */
                    private static FileVisitResult m16624b(Path path) {
                        if (path != null) {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException unused) {
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                    public final /* bridge */ /* synthetic */ FileVisitResult visitFileFailed(Object obj, IOException iOException) {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                    public final /* bridge */ /* synthetic */ FileVisitResult preVisitDirectory(Object obj, BasicFileAttributes basicFileAttributes) {
                        return FileVisitResult.CONTINUE;
                    }
                });
                return;
            } catch (IOException unused) {
                return;
            }
        }
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                m16634a(file2);
            }
        }
        file.delete();
    }

    /* renamed from: a */
    public static void m16633a(InputStream inputStream, OutputStream outputStream) {
        m16629b(inputStream, outputStream);
    }

    /* renamed from: b */
    private static void m16629b(InputStream inputStream, OutputStream outputStream) {
        if (inputStream == null || outputStream == null) {
            return;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        try {
            byte[] bArr = new byte[1048576];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    bufferedOutputStream.write(bArr, 0, read);
                } else {
                    bufferedOutputStream.flush();
                    return;
                }
            }
        } finally {
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }
    }

    /* renamed from: a */
    public static void m16635a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m16630a(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: a */
    public static String m16636a(Context context) {
        File parentFile;
        if (f9375b == null && (parentFile = context.getCacheDir().getParentFile()) != null) {
            try {
                f9375b = parentFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f9375b;
    }

    /* renamed from: a */
    public static void m16631a(String str, String str2) {
        File file = new File(str);
        File file2 = new File(str2);
        if (!file.exists()) {
            throw new Exception("文件夹不存在");
        }
        if (!file.isDirectory()) {
            throw new Exception("源文件夹不是目录");
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (!file2.isDirectory()) {
            throw new Exception("目标文件夹不是目录");
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        for (File file3 : listFiles) {
            String str3 = file2 + File.separator + file3.getName();
            if (file3.isDirectory()) {
                m16631a(file3.getAbsolutePath(), str3);
            } else if (!new File(str3).exists()) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file3));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3));
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                bufferedOutputStream.close();
                bufferedInputStream.close();
            }
        }
    }
}
