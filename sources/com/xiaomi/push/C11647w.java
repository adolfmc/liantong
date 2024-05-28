package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.w */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11647w {

    /* renamed from: a */
    public static final String[] f23809a = {"jpg", "png", "bmp", "gif", "webp"};

    /* renamed from: a */
    public static void m2271a(File file, File file2) {
        ZipOutputStream zipOutputStream;
        ZipOutputStream zipOutputStream2 = null;
        try {
            try {
                zipOutputStream = new ZipOutputStream(new FileOutputStream(file, false));
            } catch (FileNotFoundException unused) {
                m2274a(zipOutputStream2);
            } catch (IOException e) {
                e = e;
            }
            try {
                m2268a(zipOutputStream, file2, null, null);
                m2274a(zipOutputStream);
            } catch (FileNotFoundException unused2) {
                zipOutputStream2 = zipOutputStream;
                m2274a(zipOutputStream2);
            } catch (IOException e2) {
                e = e2;
                zipOutputStream2 = zipOutputStream;
                AbstractC11049b.m5282a("zip file failure + " + e.getMessage());
                m2274a(zipOutputStream2);
            } catch (Throwable th) {
                th = th;
                zipOutputStream2 = zipOutputStream;
                m2274a(zipOutputStream2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    public static void m2268a(ZipOutputStream zipOutputStream, File file, String str, FileFilter fileFilter) {
        FileInputStream fileInputStream;
        File[] listFiles;
        if (str == null) {
            str = "";
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                if (file.isDirectory()) {
                    if (fileFilter != null) {
                        listFiles = file.listFiles(fileFilter);
                    } else {
                        listFiles = file.listFiles();
                    }
                    zipOutputStream.putNextEntry(new ZipEntry(str + File.separator));
                    String str2 = TextUtils.isEmpty(str) ? "" : str + File.separator;
                    for (int i = 0; i < listFiles.length; i++) {
                        m2268a(zipOutputStream, listFiles[i], str2 + listFiles[i].getName(), null);
                    }
                    File[] listFiles2 = file.listFiles(new FileFilter() { // from class: com.xiaomi.push.w.1
                        @Override // java.io.FileFilter
                        public boolean accept(File file2) {
                            return file2.isDirectory();
                        }
                    });
                    if (listFiles2 != null) {
                        for (File file2 : listFiles2) {
                            m2268a(zipOutputStream, file2, str2 + File.separator + file2.getName(), fileFilter);
                        }
                    }
                    fileInputStream = null;
                } else {
                    if (!TextUtils.isEmpty(str)) {
                        zipOutputStream.putNextEntry(new ZipEntry(str));
                    } else {
                        Date date = new Date();
                        zipOutputStream.putNextEntry(new ZipEntry(String.valueOf(date.getTime()) + ".txt"));
                    }
                    fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read);
                        }
                    } catch (IOException e) {
                        e = e;
                        fileInputStream2 = fileInputStream;
                        AbstractC11049b.m5268d("zipFiction failed with exception:" + e.toString());
                        m2274a((Closeable) fileInputStream2);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        m2274a((Closeable) fileInputStream2);
                        throw th;
                    }
                }
                m2274a((Closeable) fileInputStream);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    public static void m2274a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static String m2273a(File file) {
        InputStreamReader inputStreamReader;
        StringWriter stringWriter = new StringWriter();
        try {
            try {
                inputStreamReader = new InputStreamReader(new BufferedInputStream(new FileInputStream(file)));
            } catch (IOException e) {
                e = e;
                inputStreamReader = null;
            } catch (Throwable th) {
                th = th;
                m2274a((Closeable) null);
                m2274a(stringWriter);
                throw th;
            }
            try {
                char[] cArr = new char[2048];
                while (true) {
                    int read = inputStreamReader.read(cArr);
                    if (read != -1) {
                        stringWriter.write(cArr, 0, read);
                    } else {
                        String stringWriter2 = stringWriter.toString();
                        m2274a(inputStreamReader);
                        m2274a(stringWriter);
                        return stringWriter2;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                AbstractC11049b.m5270c("read file :" + file.getAbsolutePath() + " failure :" + e.getMessage());
                m2274a(inputStreamReader);
                m2274a(stringWriter);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            m2274a((Closeable) null);
            m2274a(stringWriter);
            throw th;
        }
    }

    /* renamed from: a */
    public static void m2270a(File file, String str) {
        if (!file.exists()) {
            AbstractC11049b.m5270c("mkdir " + file.getAbsolutePath());
            file.getParentFile().mkdirs();
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                try {
                    bufferedWriter2.write(str);
                    m2274a(bufferedWriter2);
                } catch (IOException e) {
                    e = e;
                    bufferedWriter = bufferedWriter2;
                    AbstractC11049b.m5270c("write file :" + file.getAbsolutePath() + " failure :" + e.getMessage());
                    m2274a(bufferedWriter);
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    m2274a(bufferedWriter);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: b */
    public static void m2266b(File file, File file2) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        if (file.getAbsolutePath().equals(file2.getAbsolutePath())) {
            return;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read >= 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static byte[] m2269a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, 8192);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                m2274a((Closeable) inputStream);
                m2274a(byteArrayOutputStream);
            }
        }
    }

    /* renamed from: a */
    public static boolean m2272a(File file) {
        try {
            if (file.isDirectory()) {
                return false;
            }
            if (file.exists()) {
                return true;
            }
            File parentFile = file.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                return file.createNewFile();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static byte[] m2267a(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Exception unused) {
            return bArr;
        }
    }
}
