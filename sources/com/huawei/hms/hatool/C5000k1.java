package com.huawei.hms.hatool;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.zip.Deflater;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.k1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C5000k1 {
    /* renamed from: a */
    public static String m14636a(File file) {
        FileInputStream fileInputStream;
        String str;
        String str2;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException unused) {
            } catch (IOException unused2) {
            }
            try {
                C4987h c4987h = new C4987h(1024);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    c4987h.m14691a(bArr, read);
                }
                if (c4987h.m14690b() == 0) {
                    m14637a((Closeable) fileInputStream);
                    return "";
                }
                String str3 = new String(c4987h.m14692a(), "UTF-8");
                m14637a((Closeable) fileInputStream);
                return str3;
            } catch (FileNotFoundException unused3) {
                fileInputStream2 = fileInputStream;
                str = "hmsSdk";
                str2 = "getInfoFromFile(): No files need to be read";
                C5029v.m14451f(str, str2);
                m14637a((Closeable) fileInputStream2);
                return "";
            } catch (IOException unused4) {
                fileInputStream2 = fileInputStream;
                str = "hmsSdk";
                str2 = "getInfoFromFile(): stream.read or new string exception";
                C5029v.m14451f(str, str2);
                m14637a((Closeable) fileInputStream2);
                return "";
            } catch (Throwable th) {
                th = th;
                m14637a((Closeable) fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    /* renamed from: a */
    public static String m14634a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toString("UTF-8");
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } finally {
            m14637a((Closeable) byteArrayOutputStream);
        }
    }

    /* renamed from: a */
    public static void m14637a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                C5029v.m14451f("hmsSdk", "closeQuietly(): Exception when closing the closeable!");
            }
        }
    }

    /* renamed from: a */
    public static void m14635a(File file, String str) {
        String str2;
        String str3;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException unused) {
            } catch (IOException unused2) {
            } catch (Throwable th) {
                th = th;
                m14637a((Closeable) fileOutputStream2);
                throw th;
            }
            try {
                fileOutputStream.write(str.getBytes("UTF-8"));
                fileOutputStream.flush();
            } catch (FileNotFoundException unused3) {
                fileOutputStream2 = fileOutputStream;
                str2 = "hmsSdk";
                str3 = "saveInfoToFile(): No files need to be read";
                fileOutputStream = fileOutputStream2;
                C5029v.m14451f(str2, str3);
                m14637a((Closeable) fileOutputStream);
            } catch (IOException unused4) {
                fileOutputStream2 = fileOutputStream;
                str2 = "hmsSdk";
                str3 = "saveInfoToFile(): io exc from write info to file!";
                fileOutputStream = fileOutputStream2;
                C5029v.m14451f(str2, str3);
                m14637a((Closeable) fileOutputStream);
            }
            m14637a((Closeable) fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            m14637a((Closeable) fileOutputStream2);
            throw th;
        }
    }

    /* renamed from: a */
    private static void m14633a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
                C5029v.m14451f("hmsSdk", "closeStream(): Exception: close OutputStream error!");
            }
        }
    }

    /* renamed from: a */
    public static void m14632a(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.getInputStream().close();
        } catch (Exception unused) {
            C5029v.m14451f("hmsSdk", "closeQuietly(): Exception when connHttp.getInputStream()!,There may be no network, or no INTERNET permission");
        }
        httpURLConnection.disconnect();
        C5029v.m14461a("hmsSdk", " connHttp disconnect");
    }

    /* renamed from: a */
    public static byte[] m14631a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[1024];
        while (!deflater.finished()) {
            byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        deflater.end();
        m14633a((OutputStream) byteArrayOutputStream);
        return byteArray;
    }
}
