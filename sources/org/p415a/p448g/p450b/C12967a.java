package org.p415a.p448g.p450b;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.b.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C12967a {

    /* renamed from: a */
    private static int f26248a = 4096;

    /* renamed from: a */
    public static int m403a(InputStream inputStream, byte[] bArr) {
        return m402a(inputStream, bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static int m402a(InputStream inputStream, byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int read = inputStream.read(bArr, i + i3, i2 - i3);
            if (read < 0) {
                break;
            }
            i3 += read;
        }
        return i3;
    }

    /* renamed from: a */
    public static void m404a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[f26248a];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read < 0) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    /* renamed from: a */
    public static byte[] m405a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m404a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
