package com.baidu.platform.comapi.p149b;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2982a {
    /* renamed from: a */
    public static void m18064a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static void m18063a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        if (inputStream == null || outputStream == null || bArr == null) {
            throw new IOException("copyStream : outputStream or inputStream is null");
        }
        try {
            if (!(inputStream instanceof BufferedInputStream)) {
                inputStream = new BufferedInputStream(inputStream);
            }
            if (!(outputStream instanceof BufferedOutputStream)) {
                outputStream = new BufferedOutputStream(outputStream);
            }
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(bArr, 0, read);
            }
        } finally {
            m18064a(inputStream);
            m18064a(outputStream);
        }
    }
}
