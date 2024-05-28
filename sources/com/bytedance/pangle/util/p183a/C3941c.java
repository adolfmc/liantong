package com.bytedance.pangle.util.p183a;

import android.util.Pair;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.a.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3941c {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Pair<ByteBuffer, Long> m16652a(RandomAccessFile randomAccessFile, int i) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("maxCommentSize: ".concat(String.valueOf(i)));
        }
        long length = randomAccessFile.length();
        if (length < 22) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(((int) Math.min(i, length - 22)) + 22);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        long capacity = length - allocate.capacity();
        randomAccessFile.seek(capacity);
        randomAccessFile.readFully(allocate.array(), allocate.arrayOffset(), allocate.capacity());
        int m16648b = m16648b(allocate);
        if (m16648b == -1) {
            return null;
        }
        allocate.position(m16648b);
        ByteBuffer slice = allocate.slice();
        slice.order(ByteOrder.LITTLE_ENDIAN);
        return Pair.create(slice, Long.valueOf(capacity + m16648b));
    }

    /* renamed from: b */
    private static int m16648b(ByteBuffer byteBuffer) {
        m16651a(byteBuffer);
        int capacity = byteBuffer.capacity();
        if (capacity < 22) {
            return -1;
        }
        int i = capacity - 22;
        int min = Math.min(i, 65535);
        for (int i2 = 0; i2 <= min; i2++) {
            int i3 = i - i2;
            if (byteBuffer.getInt(i3) == 101010256 && (byteBuffer.getShort(i3 + 20) & 65535) == i2) {
                return i3;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m16651a(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static long m16650a(ByteBuffer byteBuffer, int i) {
        return byteBuffer.getInt(i) & 4294967295L;
    }

    /* renamed from: a */
    public static void m16649a(ZipFile zipFile) {
        if (zipFile == null) {
            return;
        }
        try {
            zipFile.close();
        } catch (Throwable unused) {
        }
    }
}
