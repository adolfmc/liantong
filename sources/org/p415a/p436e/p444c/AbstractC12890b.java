package org.p415a.p436e.p444c;

import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.c.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12890b {
    /* renamed from: a */
    private static int m717a(int i) {
        int i2 = 0;
        while ((i & 1) == 0) {
            i >>>= 1;
            i2++;
        }
        return i2;
    }

    /* renamed from: a */
    private static int m714a(int[] iArr, int[] iArr2, int i, int[] iArr3, int i2) {
        int length = iArr.length;
        int i3 = 0;
        while (iArr2[0] == 0) {
            AbstractC12891c.m675d(i, iArr2, 0);
            i3 += 32;
        }
        int m717a = m717a(iArr2[0]);
        if (m717a > 0) {
            AbstractC12891c.m687b(i, iArr2, m717a, 0);
            i3 += m717a;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if ((iArr3[0] & 1) != 0) {
                i2 += i2 < 0 ? AbstractC12891c.m698a(length, iArr, iArr3) : AbstractC12891c.m672e(length, iArr, iArr3);
            }
            AbstractC12891c.m681c(length, iArr3, i2);
        }
        return i2;
    }

    /* renamed from: a */
    private static void m715a(int[] iArr, int i, int[] iArr2, int[] iArr3) {
        if (i < 0) {
            AbstractC12891c.m697a(iArr.length, iArr2, iArr, iArr3);
        } else {
            System.arraycopy(iArr2, 0, iArr3, 0, iArr.length);
        }
    }

    /* renamed from: a */
    public static void m713a(int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        if (AbstractC12891c.m673e(length, iArr2)) {
            throw new IllegalArgumentException("'x' cannot be 0");
        }
        if (AbstractC12891c.m676d(length, iArr2)) {
            System.arraycopy(iArr2, 0, iArr3, 0, length);
            return;
        }
        int[] m706a = AbstractC12891c.m706a(length, iArr2);
        int[] m712a = AbstractC12891c.m712a(length);
        m712a[0] = 1;
        int m714a = (1 & m706a[0]) == 0 ? m714a(iArr, m706a, length, m712a, 0) : 0;
        if (AbstractC12891c.m676d(length, m706a)) {
            m715a(iArr, m714a, m712a, iArr3);
            return;
        }
        int[] m706a2 = AbstractC12891c.m706a(length, iArr);
        int[] m712a2 = AbstractC12891c.m712a(length);
        int i = 0;
        int i2 = length;
        while (true) {
            int i3 = i2 - 1;
            if (m706a[i3] == 0 && m706a2[i3] == 0) {
                i2--;
            } else if (AbstractC12891c.m679c(i2, m706a, m706a2)) {
                AbstractC12891c.m672e(i2, m706a2, m706a);
                m714a = m714a(iArr, m706a, i2, m712a, m714a + (AbstractC12891c.m672e(length, m712a2, m712a) - i));
                if (AbstractC12891c.m676d(i2, m706a)) {
                    m715a(iArr, m714a, m712a, iArr3);
                    return;
                }
            } else {
                AbstractC12891c.m672e(i2, m706a, m706a2);
                i = m714a(iArr, m706a2, i2, m712a2, i + (AbstractC12891c.m672e(length, m712a, m712a2) - m714a));
                if (AbstractC12891c.m676d(i2, m706a2)) {
                    m715a(iArr, i, m712a2, iArr3);
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public static int[] m716a(int[] iArr) {
        int length = iArr.length;
        Random random = new Random();
        int[] m712a = AbstractC12891c.m712a(length);
        int i = length - 1;
        int i2 = iArr[i];
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        int i7 = i6 | (i6 >>> 16);
        do {
            for (int i8 = 0; i8 != length; i8++) {
                m712a[i8] = random.nextInt();
            }
            m712a[i] = m712a[i] & i7;
        } while (AbstractC12891c.m679c(length, m712a, iArr));
        return m712a;
    }
}
