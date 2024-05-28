package com.baidu.p122b.p125c.p127b;

import com.baidu.p122b.p125c.p128c.C2407b;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.security.GeneralSecurityException;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2404b {

    /* renamed from: a */
    private static final int[] f4228a = m20247c(new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107});

    /* renamed from: b */
    private final int f4229b;

    public C2404b(int i) {
        this.f4229b = i;
    }

    /* renamed from: a */
    private static int m20259a(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    /* renamed from: a */
    private void m20253a(byte[] bArr, byte[] bArr2, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int remaining = byteBuffer2.remaining();
        int i = (remaining / 64) + 1;
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer m20254a = m20254a(bArr, bArr2, this.f4229b + i2);
            if (i2 == i - 1) {
                C2403a.m20261a(byteBuffer, byteBuffer2, m20254a, remaining % 64);
            } else {
                C2403a.m20261a(byteBuffer, byteBuffer2, m20254a, 64);
            }
        }
    }

    /* renamed from: a */
    static void m20252a(int[] iArr) {
        for (int i = 0; i < 10; i++) {
            m20251a(iArr, 0, 4, 8, 12);
            m20251a(iArr, 1, 5, 9, 13);
            m20251a(iArr, 2, 6, 10, 14);
            m20251a(iArr, 3, 7, 11, 15);
            m20251a(iArr, 0, 5, 10, 15);
            m20251a(iArr, 1, 6, 11, 12);
            m20251a(iArr, 2, 7, 8, 13);
            m20251a(iArr, 3, 4, 9, 14);
        }
    }

    /* renamed from: a */
    static void m20251a(int[] iArr, int i, int i2, int i3, int i4) {
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = m20259a(iArr[i4] ^ iArr[i], 16);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = m20259a(iArr[i2] ^ iArr[i3], 12);
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = m20259a(iArr[i] ^ iArr[i4], 8);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = m20259a(iArr[i2] ^ iArr[i3], 7);
    }

    /* renamed from: a */
    static void m20250a(int[] iArr, int[] iArr2) {
        int[] iArr3 = f4228a;
        System.arraycopy(iArr3, 0, iArr, 0, iArr3.length);
        System.arraycopy(iArr2, 0, iArr, f4228a.length, 8);
    }

    /* renamed from: c */
    static int[] m20247c(byte[] bArr) {
        IntBuffer asIntBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[asIntBuffer.remaining()];
        asIntBuffer.get(iArr);
        return iArr;
    }

    /* renamed from: a */
    int m20260a() {
        return 12;
    }

    /* renamed from: a */
    ByteBuffer m20254a(byte[] bArr, byte[] bArr2, int i) {
        int[] m20249a = m20249a(m20247c(bArr), m20247c(bArr2), i);
        int[] iArr = (int[]) m20249a.clone();
        m20252a(iArr);
        for (int i2 = 0; i2 < m20249a.length; i2++) {
            m20249a[i2] = m20249a[i2] + iArr[i2];
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(m20249a, 0, 16);
        return order;
    }

    /* renamed from: a */
    void m20257a(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) {
        if (byteBuffer.remaining() - m20260a() < bArr.length) {
            throw new IllegalArgumentException("data output is too small");
        }
        if (bArr2 == null) {
            bArr2 = new byte[m20260a()];
            new Random().nextBytes(bArr2);
        }
        byteBuffer.put(bArr2);
        m20253a(bArr2, C2407b.m20244a(), byteBuffer, ByteBuffer.wrap(bArr));
    }

    /* renamed from: a */
    byte[] m20258a(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() >= m20260a()) {
            byte[] bArr = new byte[m20260a()];
            byteBuffer.get(bArr);
            ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
            m20253a(bArr, C2407b.m20243b(), allocate, byteBuffer);
            return allocate.array();
        }
        throw new GeneralSecurityException("data too short");
    }

    /* renamed from: a */
    public byte[] m20256a(byte[] bArr) {
        return m20255a(bArr, (byte[]) null);
    }

    /* renamed from: a */
    public byte[] m20255a(byte[] bArr, byte[] bArr2) {
        if (bArr.length <= Integer.MAX_VALUE - m20260a()) {
            ByteBuffer allocate = ByteBuffer.allocate(m20260a() + bArr.length);
            m20257a(allocate, bArr, bArr2);
            return allocate.array();
        }
        throw new GeneralSecurityException("data too long");
    }

    /* renamed from: a */
    int[] m20249a(int[] iArr, int[] iArr2, int i) {
        if (iArr.length == m20260a() / 4) {
            int[] iArr3 = new int[16];
            m20250a(iArr3, iArr2);
            iArr3[12] = i;
            System.arraycopy(iArr, 0, iArr3, 13, iArr.length);
            return iArr3;
        }
        throw new IllegalArgumentException(String.format("need 96-bit param, but got a %d-bit param", Integer.valueOf(iArr.length * 32)));
    }

    /* renamed from: b */
    public byte[] m20248b(byte[] bArr) {
        return m20258a(ByteBuffer.wrap(bArr));
    }
}
