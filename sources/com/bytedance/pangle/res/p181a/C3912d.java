package com.bytedance.pangle.res.p181a;

import java.io.EOFException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.res.a.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3912d {
    /* renamed from: a */
    private static <T> T m16702a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public static void m16703a(InputStream inputStream, byte[] bArr, int i, int i2) {
        m16702a(inputStream);
        m16702a(bArr);
        int i3 = 0;
        if (i2 < 0) {
            throw new IndexOutOfBoundsException(String.format("len (%s) cannot be negative", Integer.valueOf(i2)));
        }
        int i4 = i + i2;
        int length = bArr.length;
        if (i < 0 || i4 < i || i4 > length) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(i4);
            sb.append(length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        while (i3 < i2) {
            int read = inputStream.read(bArr, i + i3, i2 - i3);
            if (read == -1) {
                break;
            }
            i3 += read;
        }
        if (i3 == i2) {
            return;
        }
        throw new EOFException("reached end of stream after reading " + i3 + " bytes; " + i2 + " bytes expected");
    }
}
