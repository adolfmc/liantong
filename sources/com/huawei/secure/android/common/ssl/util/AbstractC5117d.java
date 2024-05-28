package com.huawei.secure.android.common.ssl.util;

import android.database.Cursor;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.secure.android.common.ssl.util.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC5117d {

    /* renamed from: a */
    private static final String f12113a = "IOUtil";

    /* renamed from: b */
    private static final int f12114b = 4096;

    /* renamed from: a */
    public static void m13869a(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    /* renamed from: b */
    public static byte[] m13858b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m13865a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static void m13862a(Reader reader) {
        m13868a((Closeable) reader);
    }

    /* renamed from: a */
    public static void m13861a(Writer writer) {
        m13868a((Closeable) writer);
    }

    /* renamed from: a */
    public static void m13866a(InputStream inputStream) {
        m13868a((Closeable) inputStream);
    }

    /* renamed from: a */
    public static void m13863a(OutputStream outputStream) {
        m13868a((Closeable) outputStream);
    }

    /* renamed from: a */
    public static void m13868a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                C5118e.m13854b("IOUtil", "closeSecure IOException");
            }
        }
    }

    /* renamed from: a */
    public static long m13865a(InputStream inputStream, OutputStream outputStream) throws IOException {
        return m13864a(inputStream, outputStream, new byte[4096]);
    }

    /* renamed from: a */
    public static long m13864a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += read;
        }
    }

    /* renamed from: a */
    public static InputStream m13859a(byte[] bArr) throws IOException {
        return new ByteArrayInputStream(bArr);
    }

    /* renamed from: a */
    public static void m13867a(File file) {
        if (file == null || !file.exists() || file.delete()) {
            return;
        }
        C5118e.m13854b("IOUtil", "deleteSecure exception");
    }

    /* renamed from: a */
    public static void m13860a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        m13867a(new File(str));
    }
}
