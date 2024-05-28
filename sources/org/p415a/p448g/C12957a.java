package org.p415a.p448g;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C12957a {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.g.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12958a<T> implements Iterator<T> {

        /* renamed from: a */
        private final T[] f26236a;

        /* renamed from: b */
        private int f26237b = 0;

        public C12958a(T[] tArr) {
            this.f26236a = tArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f26237b < this.f26236a.length;
        }

        @Override // java.util.Iterator
        public T next() {
            int i = this.f26237b;
            T[] tArr = this.f26236a;
            if (i != tArr.length) {
                this.f26237b = i + 1;
                return tArr[i];
            }
            throw new NoSuchElementException("Out of elements: " + this.f26237b);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove element from an Array.");
        }
    }

    /* renamed from: a */
    private static int m442a(int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 0) {
            return i3;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(" > ");
        stringBuffer.append(i2);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    /* renamed from: a */
    public static int m441a(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ bArr[length];
        }
    }

    /* renamed from: a */
    public static int m437a(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ cArr[length];
        }
    }

    /* renamed from: a */
    public static int m435a(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int length = iArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ iArr[length];
        }
    }

    /* renamed from: a */
    public static int m434a(int[] iArr, int i, int i2) {
        if (iArr == null) {
            return 0;
        }
        int i3 = i2 + 1;
        while (true) {
            i2--;
            if (i2 < 0) {
                return i3;
            }
            i3 = (i3 * 257) ^ iArr[i + i2];
        }
    }

    /* renamed from: a */
    public static int m431a(long[] jArr, int i, int i2) {
        if (jArr == null) {
            return 0;
        }
        int i3 = i2 + 1;
        while (true) {
            i2--;
            if (i2 < 0) {
                return i3;
            }
            long j = jArr[i + i2];
            i3 = (((i3 * 257) ^ ((int) j)) * 257) ^ ((int) (j >>> 32));
        }
    }

    /* renamed from: a */
    public static void m440a(byte[] bArr, byte b) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = b;
        }
    }

    /* renamed from: a */
    public static boolean m438a(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i != bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m436a(char[] cArr, char[] cArr2) {
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        for (int i = 0; i != cArr.length; i++) {
            if (cArr[i] != cArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m433a(int[] iArr, int[] iArr2) {
        if (iArr == iArr2) {
            return true;
        }
        if (iArr == null || iArr2 == null || iArr.length != iArr2.length) {
            return false;
        }
        for (int i = 0; i != iArr.length; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static byte[] m439a(byte[] bArr, int i, int i2) {
        int m442a = m442a(i, i2);
        byte[] bArr2 = new byte[m442a];
        if (bArr.length - i < m442a) {
            m442a = bArr.length - i;
        }
        System.arraycopy(bArr, i, bArr2, 0, m442a);
        return bArr2;
    }

    /* renamed from: a */
    public static long[] m432a(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        long[] jArr2 = new long[jArr.length];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        return jArr2;
    }

    /* renamed from: b */
    public static byte[] m430b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    /* renamed from: b */
    public static int[] m429b(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }
}
