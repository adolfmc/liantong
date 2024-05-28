package com.huawei.secure.android.common.encrypt.utils;

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
/* renamed from: com.huawei.secure.android.common.encrypt.utils.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5105a {

    /* renamed from: a */
    private static final String f11978a = "IOUtil";

    /* renamed from: b */
    private static final int f11979b = 4096;

    /* renamed from: a */
    public static void m13957a(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    /* renamed from: b */
    public static byte[] m13946b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m13953a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static void m13950a(Reader reader) {
        m13956a((Closeable) reader);
    }

    /* renamed from: a */
    public static void m13949a(Writer writer) {
        m13956a((Closeable) writer);
    }

    /* renamed from: a */
    public static void m13954a(InputStream inputStream) {
        m13956a((Closeable) inputStream);
    }

    /* renamed from: a */
    public static void m13951a(OutputStream outputStream) {
        m13956a((Closeable) outputStream);
    }

    /* renamed from: a */
    public static void m13956a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                C5106b.m13942b("IOUtil", "closeSecure IOException");
            }
        }
    }

    /* renamed from: a */
    public static long m13953a(InputStream inputStream, OutputStream outputStream) throws IOException {
        return m13952a(inputStream, outputStream, new byte[4096]);
    }

    /* renamed from: a */
    public static long m13952a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
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
    public static InputStream m13947a(byte[] bArr) throws IOException {
        return new ByteArrayInputStream(bArr);
    }

    /* renamed from: a */
    public static void m13955a(File file) {
        if (file == null || !file.exists() || file.delete()) {
            return;
        }
        C5106b.m13942b("IOUtil", "deleteSecure exception");
    }

    /* renamed from: a */
    public static void m13948a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        m13955a(new File(str));
    }
}
