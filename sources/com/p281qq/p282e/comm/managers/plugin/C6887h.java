package com.p281qq.p282e.comm.managers.plugin;

import android.content.Context;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.managers.plugin.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6887h {

    /* renamed from: a */
    private static String f17956a = C6879b.m8259a("e_qq_com_plugin");

    /* renamed from: b */
    private static String f17957b = C6879b.m8259a("e_qq_com_dex");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static File m8245a(Context context) {
        return context.getDir(f17957b, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m8246a() {
        return "gdt_plugin";
    }

    /* renamed from: a */
    public static void m8244a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m8241a(String str, File file) throws IOException {
        OutputStreamWriter outputStreamWriter;
        if (file == null) {
            throw new IOException("Target File Can not be null in StringUtil.writeTo");
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        OutputStreamWriter outputStreamWriter2 = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        } catch (Throwable th) {
            th = th;
        }
        try {
            outputStreamWriter.write(str);
            m8244a(outputStreamWriter);
        } catch (Throwable th2) {
            th = th2;
            outputStreamWriter2 = outputStreamWriter;
            m8244a(outputStreamWriter2);
            throw th;
        }
    }

    /* renamed from: a */
    public static boolean m8243a(File file, File file2) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.renameTo(file2)) {
            return true;
        }
        try {
            return m8242a(new FileInputStream(file), file2);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m8242a(InputStream inputStream, File file) throws Throwable {
        if (inputStream == null || file == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                GDTLogger.m8234e("parent dir not exists " + parentFile.getAbsolutePath());
                return false;
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        m8244a(inputStream);
                        m8244a(fileOutputStream2);
                        return true;
                    }
                    fileOutputStream2.write(bArr, 0, read);
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                try {
                    GDTLogger.m8233e("Exception while copy from InputStream to File", th);
                    throw th;
                } finally {
                    m8244a(inputStream);
                    m8244a(fileOutputStream);
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static File m8240b(Context context) {
        return new File(context.getDir(f17956a, 0), "update_lc");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static File m8239c(Context context) {
        return new File(context.getDir(f17956a, 0), "gdt_plugin.next");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static File m8238d(Context context) {
        return new File(context.getDir(f17956a, 0), "gdt_plugin.next.sig");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static File m8237e(Context context) {
        return new File(context.getDir(f17956a, 0), "gdt_plugin.jar");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static File m8236f(Context context) {
        return new File(context.getDir(f17956a, 0), "gdt_plugin.jar.sig");
    }
}
