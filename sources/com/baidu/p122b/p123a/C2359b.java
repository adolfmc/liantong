package com.baidu.p122b.p123a;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2359b implements Serializable, Cloneable {

    /* renamed from: a */
    static final /* synthetic */ boolean f4100a = !C2359b.class.desiredAssertionStatus();

    /* renamed from: b */
    private static final ObjectStreamField[] f4101b = {new ObjectStreamField("bits", long[].class)};

    /* renamed from: c */
    private long[] f4102c;

    /* renamed from: d */
    private transient int f4103d;

    /* renamed from: e */
    private transient boolean f4104e;

    public C2359b() {
        this.f4103d = 0;
        this.f4104e = false;
        m20388g(64);
        this.f4104e = false;
    }

    public C2359b(int i) {
        this.f4103d = 0;
        this.f4104e = false;
        if (i >= 0) {
            m20388g(i);
            this.f4104e = true;
            return;
        }
        throw new NegativeArraySizeException("nbits < 0: " + i);
    }

    private C2359b(long[] jArr) {
        this.f4103d = 0;
        this.f4104e = false;
        this.f4102c = jArr;
        this.f4103d = jArr.length;
        m20396d();
    }

    /* renamed from: a */
    public static C2359b m20405a(long[] jArr) {
        int length = jArr.length;
        while (length > 0 && jArr[length - 1] == 0) {
            length--;
        }
        return new C2359b(Arrays.copyOf(jArr, length));
    }

    /* renamed from: d */
    private void m20396d() {
        int i;
        int i2;
        if (!f4100a && (i2 = this.f4103d) != 0 && this.f4102c[i2 - 1] == 0) {
            throw new AssertionError();
        }
        if (!f4100a && ((i = this.f4103d) < 0 || i > this.f4102c.length)) {
            throw new AssertionError();
        }
        if (f4100a) {
            return;
        }
        int i3 = this.f4103d;
        long[] jArr = this.f4102c;
        if (i3 != jArr.length && jArr[i3] != 0) {
            throw new AssertionError();
        }
    }

    /* renamed from: d */
    private static void m20394d(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("fromIndex < 0: " + i);
        } else if (i2 < 0) {
            throw new IndexOutOfBoundsException("toIndex < 0: " + i2);
        } else if (i <= i2) {
        } else {
            throw new IndexOutOfBoundsException("fromIndex: " + i + " > toIndex: " + i2);
        }
    }

    /* renamed from: e */
    private void m20392e() {
        int i = this.f4103d - 1;
        while (i >= 0 && this.f4102c[i] == 0) {
            i--;
        }
        this.f4103d = i + 1;
    }

    /* renamed from: f */
    private static int m20389f(int i) {
        return i >> 6;
    }

    /* renamed from: f */
    private void m20390f() {
        int i = this.f4103d;
        long[] jArr = this.f4102c;
        if (i != jArr.length) {
            this.f4102c = Arrays.copyOf(jArr, i);
            m20396d();
        }
    }

    /* renamed from: g */
    private void m20388g(int i) {
        this.f4102c = new long[m20389f(i - 1) + 1];
    }

    /* renamed from: h */
    private void m20387h(int i) {
        long[] jArr = this.f4102c;
        if (jArr.length < i) {
            this.f4102c = Arrays.copyOf(this.f4102c, Math.max(jArr.length * 2, i));
            this.f4104e = false;
        }
    }

    /* renamed from: i */
    private void m20386i(int i) {
        int i2 = i + 1;
        if (this.f4103d < i2) {
            m20387h(i2);
            this.f4103d = i2;
        }
    }

    /* renamed from: a */
    public void m20410a(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("bitIndex < 0: " + i);
        }
        int m20389f = m20389f(i);
        m20386i(m20389f);
        long[] jArr = this.f4102c;
        jArr[m20389f] = jArr[m20389f] | (1 << i);
        m20396d();
    }

    /* renamed from: a */
    public void m20409a(int i, int i2) {
        m20394d(i, i2);
        if (i == i2) {
            return;
        }
        int m20389f = m20389f(i);
        int m20389f2 = m20389f(i2 - 1);
        m20386i(m20389f2);
        long j = (-1) << i;
        long j2 = (-1) >>> (-i2);
        if (m20389f == m20389f2) {
            long[] jArr = this.f4102c;
            jArr[m20389f] = (j2 & j) | jArr[m20389f];
        } else {
            long[] jArr2 = this.f4102c;
            jArr2[m20389f] = j | jArr2[m20389f];
            while (true) {
                m20389f++;
                if (m20389f >= m20389f2) {
                    break;
                }
                this.f4102c[m20389f] = -1;
            }
            long[] jArr3 = this.f4102c;
            jArr3[m20389f2] = j2 | jArr3[m20389f2];
        }
        m20396d();
    }

    /* renamed from: a */
    public void m20408a(int i, int i2, boolean z) {
        if (z) {
            m20409a(i, i2);
        } else {
            m20402b(i, i2);
        }
    }

    /* renamed from: a */
    public void m20407a(int i, boolean z) {
        if (z) {
            m20410a(i);
        } else {
            m20403b(i);
        }
    }

    /* renamed from: a */
    public void m20406a(C2359b c2359b) {
        if (this == c2359b) {
            return;
        }
        while (true) {
            int i = this.f4103d;
            if (i <= c2359b.f4103d) {
                break;
            }
            long[] jArr = this.f4102c;
            int i2 = i - 1;
            this.f4103d = i2;
            jArr[i2] = 0;
        }
        for (int i3 = 0; i3 < this.f4103d; i3++) {
            long[] jArr2 = this.f4102c;
            jArr2[i3] = jArr2[i3] & c2359b.f4102c[i3];
        }
        m20392e();
        m20396d();
    }

    /* renamed from: a */
    public byte[] m20411a() {
        int i = this.f4103d;
        if (i == 0) {
            return new byte[0];
        }
        int i2 = i - 1;
        int i3 = i2 * 8;
        for (long j = this.f4102c[i2]; j != 0; j >>>= 8) {
            i3++;
        }
        byte[] bArr = new byte[i3];
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        for (int i4 = 0; i4 < i2; i4++) {
            order.putLong(this.f4102c[i4]);
        }
        for (long j2 = this.f4102c[i2]; j2 != 0; j2 >>>= 8) {
            order.put((byte) (255 & j2));
        }
        return bArr;
    }

    /* renamed from: b */
    public int m20404b() {
        int i = this.f4103d;
        if (i == 0) {
            return 0;
        }
        return ((i - 1) * 64) + (64 - Long.numberOfLeadingZeros(this.f4102c[i - 1]));
    }

    /* renamed from: b */
    public void m20403b(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("bitIndex < 0: " + i);
        }
        int m20389f = m20389f(i);
        if (m20389f >= this.f4103d) {
            return;
        }
        long[] jArr = this.f4102c;
        jArr[m20389f] = jArr[m20389f] & (~(1 << i));
        m20392e();
        m20396d();
    }

    /* renamed from: b */
    public void m20402b(int i, int i2) {
        int m20389f;
        m20394d(i, i2);
        if (i != i2 && (m20389f = m20389f(i)) < this.f4103d) {
            int m20389f2 = m20389f(i2 - 1);
            if (m20389f2 >= this.f4103d) {
                i2 = m20404b();
                m20389f2 = this.f4103d - 1;
            }
            long j = (-1) << i;
            long j2 = (-1) >>> (-i2);
            if (m20389f == m20389f2) {
                long[] jArr = this.f4102c;
                jArr[m20389f] = (~(j2 & j)) & jArr[m20389f];
            } else {
                long[] jArr2 = this.f4102c;
                jArr2[m20389f] = (~j) & jArr2[m20389f];
                while (true) {
                    m20389f++;
                    if (m20389f >= m20389f2) {
                        break;
                    }
                    this.f4102c[m20389f] = 0;
                }
                long[] jArr3 = this.f4102c;
                jArr3[m20389f2] = (~j2) & jArr3[m20389f2];
            }
            m20392e();
            m20396d();
        }
    }

    /* renamed from: b */
    public void m20401b(C2359b c2359b) {
        if (this == c2359b) {
            return;
        }
        int min = Math.min(this.f4103d, c2359b.f4103d);
        int i = this.f4103d;
        int i2 = c2359b.f4103d;
        if (i < i2) {
            m20387h(i2);
            this.f4103d = c2359b.f4103d;
        }
        for (int i3 = 0; i3 < min; i3++) {
            long[] jArr = this.f4102c;
            jArr[i3] = jArr[i3] | c2359b.f4102c[i3];
        }
        if (min < c2359b.f4103d) {
            System.arraycopy(c2359b.f4102c, min, this.f4102c, min, this.f4103d - min);
        }
        m20396d();
    }

    /* renamed from: c */
    public int m20400c() {
        int i = 0;
        for (int i2 = 0; i2 < this.f4103d; i2++) {
            i += Long.bitCount(this.f4102c[i2]);
        }
        return i;
    }

    /* renamed from: c */
    public C2359b m20398c(int i, int i2) {
        int i3;
        long j;
        long j2;
        m20394d(i, i2);
        m20396d();
        int m20404b = m20404b();
        int i4 = 0;
        if (m20404b <= i || i == i2) {
            return new C2359b(0);
        }
        if (i2 > m20404b) {
            i2 = m20404b;
        }
        int i5 = i2 - i;
        C2359b c2359b = new C2359b(i5);
        int m20389f = m20389f(i5 - 1) + 1;
        int m20389f2 = m20389f(i);
        int i6 = i & 63;
        boolean z = i6 == 0;
        while (true) {
            i3 = m20389f - 1;
            if (i4 >= i3) {
                break;
            }
            long[] jArr = c2359b.f4102c;
            if (z) {
                j2 = this.f4102c[m20389f2];
            } else {
                long[] jArr2 = this.f4102c;
                j2 = (jArr2[m20389f2] >>> i) | (jArr2[m20389f2 + 1] << (-i));
            }
            jArr[i4] = j2;
            i4++;
            m20389f2++;
        }
        long j3 = (-1) >>> (-i2);
        long[] jArr3 = c2359b.f4102c;
        if (((i2 - 1) & 63) < i6) {
            long[] jArr4 = this.f4102c;
            j = ((jArr4[m20389f2 + 1] & j3) << (-i)) | (jArr4[m20389f2] >>> i);
        } else {
            j = (this.f4102c[m20389f2] & j3) >>> i;
        }
        jArr3[i3] = j;
        c2359b.f4103d = m20389f;
        c2359b.m20392e();
        c2359b.m20396d();
        return c2359b;
    }

    /* renamed from: c */
    public void m20397c(C2359b c2359b) {
        int min = Math.min(this.f4103d, c2359b.f4103d);
        int i = this.f4103d;
        int i2 = c2359b.f4103d;
        if (i < i2) {
            m20387h(i2);
            this.f4103d = c2359b.f4103d;
        }
        for (int i3 = 0; i3 < min; i3++) {
            long[] jArr = this.f4102c;
            jArr[i3] = jArr[i3] ^ c2359b.f4102c[i3];
        }
        int i4 = c2359b.f4103d;
        if (min < i4) {
            System.arraycopy(c2359b.f4102c, min, this.f4102c, min, i4 - min);
        }
        m20392e();
        m20396d();
    }

    /* renamed from: c */
    public boolean m20399c(int i) {
        if (i >= 0) {
            m20396d();
            int m20389f = m20389f(i);
            return m20389f < this.f4103d && (this.f4102c[m20389f] & (1 << i)) != 0;
        }
        throw new IndexOutOfBoundsException("bitIndex < 0: " + i);
    }

    public Object clone() {
        if (!this.f4104e) {
            m20390f();
        }
        try {
            C2359b c2359b = (C2359b) super.clone();
            c2359b.f4102c = (long[]) this.f4102c.clone();
            c2359b.m20396d();
            return c2359b;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    /* renamed from: d */
    public int m20395d(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("fromIndex < 0: " + i);
        }
        m20396d();
        int m20389f = m20389f(i);
        if (m20389f >= this.f4103d) {
            return -1;
        }
        long j = this.f4102c[m20389f] & ((-1) << i);
        while (j == 0) {
            m20389f++;
            if (m20389f == this.f4103d) {
                return -1;
            }
            j = this.f4102c[m20389f];
        }
        return (m20389f * 64) + Long.numberOfTrailingZeros(j);
    }

    /* renamed from: d */
    public void m20393d(C2359b c2359b) {
        for (int min = Math.min(this.f4103d, c2359b.f4103d) - 1; min >= 0; min--) {
            long[] jArr = this.f4102c;
            jArr[min] = jArr[min] & (~c2359b.f4102c[min]);
        }
        m20392e();
        m20396d();
    }

    /* renamed from: e */
    public int m20391e(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("fromIndex < 0: " + i);
        }
        m20396d();
        int m20389f = m20389f(i);
        if (m20389f >= this.f4103d) {
            return i;
        }
        long j = (~this.f4102c[m20389f]) & ((-1) << i);
        while (j == 0) {
            m20389f++;
            int i2 = this.f4103d;
            if (m20389f == i2) {
                return i2 * 64;
            }
            j = ~this.f4102c[m20389f];
        }
        return (m20389f * 64) + Long.numberOfTrailingZeros(j);
    }

    public boolean equals(Object obj) {
        if (obj instanceof C2359b) {
            if (this == obj) {
                return true;
            }
            C2359b c2359b = (C2359b) obj;
            m20396d();
            c2359b.m20396d();
            if (this.f4103d != c2359b.f4103d) {
                return false;
            }
            for (int i = 0; i < this.f4103d; i++) {
                if (this.f4102c[i] != c2359b.f4102c[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.f4103d;
        long j = 1234;
        while (true) {
            i--;
            if (i < 0) {
                return (int) ((j >> 32) ^ j);
            }
            j ^= this.f4102c[i] * (i + 1);
        }
    }

    public String toString() {
        m20396d();
        int i = this.f4103d;
        StringBuilder sb = new StringBuilder(((i > 128 ? m20400c() : i * 64) * 6) + 2);
        sb.append('{');
        int m20395d = m20395d(0);
        if (m20395d != -1) {
            sb.append(m20395d);
            while (true) {
                m20395d = m20395d(m20395d + 1);
                if (m20395d < 0) {
                    break;
                }
                int m20391e = m20391e(m20395d);
                do {
                    sb.append(", ");
                    sb.append(m20395d);
                    m20395d++;
                } while (m20395d < m20391e);
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
