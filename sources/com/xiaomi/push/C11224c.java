package com.xiaomi.push;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* renamed from: com.xiaomi.push.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11224c {

    /* renamed from: a */
    private final int f21668a;

    /* renamed from: a */
    private final OutputStream f21669a;

    /* renamed from: a */
    private final byte[] f21670a;

    /* renamed from: b */
    private int f21671b;

    /* renamed from: a */
    public static int m4631a(boolean z) {
        return 1;
    }

    /* renamed from: c */
    public static int m4613c(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    /* renamed from: d */
    public static int m4611d(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    private C11224c(byte[] bArr, int i, int i2) {
        this.f21669a = null;
        this.f21670a = bArr;
        this.f21671b = i;
        this.f21668a = i + i2;
    }

    private C11224c(OutputStream outputStream, byte[] bArr) {
        this.f21669a = outputStream;
        this.f21670a = bArr;
        this.f21671b = 0;
        this.f21668a = bArr.length;
    }

    /* renamed from: a */
    public static C11224c m4635a(OutputStream outputStream) {
        return m4634a(outputStream, 4096);
    }

    /* renamed from: a */
    public static C11224c m4634a(OutputStream outputStream, int i) {
        return new C11224c(outputStream, new byte[i]);
    }

    /* renamed from: a */
    public static C11224c m4628a(byte[] bArr, int i, int i2) {
        return new C11224c(bArr, i, i2);
    }

    /* renamed from: a */
    public void m4650a(int i, long j) {
        m4614c(i, 0);
        m4640a(j);
    }

    /* renamed from: b */
    public void m4620b(int i, long j) {
        m4614c(i, 0);
        m4618b(j);
    }

    /* renamed from: a */
    public void m4652a(int i, int i2) {
        m4614c(i, 0);
        m4654a(i2);
    }

    /* renamed from: a */
    public void m4642a(int i, boolean z) {
        m4614c(i, 0);
        m4630a(z);
    }

    /* renamed from: a */
    public void m4644a(int i, String str) {
        m4614c(i, 2);
        m4632a(str);
    }

    /* renamed from: a */
    public void m4646a(int i, AbstractC11313e abstractC11313e) {
        m4614c(i, 2);
        m4636a(abstractC11313e);
    }

    /* renamed from: a */
    public void m4648a(int i, C11129a c11129a) {
        m4614c(i, 2);
        m4638a(c11129a);
    }

    /* renamed from: b */
    public void m4622b(int i, int i2) {
        m4614c(i, 0);
        m4624b(i2);
    }

    /* renamed from: a */
    public void m4640a(long j) {
        m4612c(j);
    }

    /* renamed from: b */
    public void m4618b(long j) {
        m4612c(j);
    }

    /* renamed from: a */
    public void m4654a(int i) {
        if (i >= 0) {
            m4610d(i);
        } else {
            m4612c(i);
        }
    }

    /* renamed from: a */
    public void m4630a(boolean z) {
        m4615c(z ? 1 : 0);
    }

    /* renamed from: a */
    public void m4632a(String str) {
        byte[] bytes = str.getBytes("UTF-8");
        m4610d(bytes.length);
        m4629a(bytes);
    }

    /* renamed from: a */
    public void m4636a(AbstractC11313e abstractC11313e) {
        m4610d(abstractC11313e.mo4063a());
        abstractC11313e.mo4059a(this);
    }

    /* renamed from: a */
    public void m4638a(C11129a c11129a) {
        byte[] m4946a = c11129a.m4946a();
        m4610d(m4946a.length);
        m4629a(m4946a);
    }

    /* renamed from: b */
    public void m4624b(int i) {
        m4610d(i);
    }

    /* renamed from: a */
    public static int m4651a(int i, long j) {
        return m4616c(i) + m4641a(j);
    }

    /* renamed from: b */
    public static int m4621b(int i, long j) {
        return m4616c(i) + m4619b(j);
    }

    /* renamed from: a */
    public static int m4653a(int i, int i2) {
        return m4616c(i) + m4655a(i2);
    }

    /* renamed from: a */
    public static int m4643a(int i, boolean z) {
        return m4616c(i) + m4631a(z);
    }

    /* renamed from: a */
    public static int m4645a(int i, String str) {
        return m4616c(i) + m4633a(str);
    }

    /* renamed from: a */
    public static int m4647a(int i, AbstractC11313e abstractC11313e) {
        return m4616c(i) + m4637a(abstractC11313e);
    }

    /* renamed from: a */
    public static int m4649a(int i, C11129a c11129a) {
        return m4616c(i) + m4639a(c11129a);
    }

    /* renamed from: b */
    public static int m4623b(int i, int i2) {
        return m4616c(i) + m4625b(i2);
    }

    /* renamed from: a */
    public static int m4641a(long j) {
        return m4613c(j);
    }

    /* renamed from: b */
    public static int m4619b(long j) {
        return m4613c(j);
    }

    /* renamed from: a */
    public static int m4655a(int i) {
        if (i >= 0) {
            return m4611d(i);
        }
        return 10;
    }

    /* renamed from: a */
    public static int m4633a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return m4611d(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    /* renamed from: a */
    public static int m4637a(AbstractC11313e abstractC11313e) {
        int mo4055b = abstractC11313e.mo4055b();
        return m4611d(mo4055b) + mo4055b;
    }

    /* renamed from: a */
    public static int m4639a(C11129a c11129a) {
        return m4611d(c11129a.m4947a()) + c11129a.m4947a();
    }

    /* renamed from: b */
    public static int m4625b(int i) {
        return m4611d(i);
    }

    /* renamed from: c */
    private void m4617c() {
        OutputStream outputStream = this.f21669a;
        if (outputStream == null) {
            throw new C11225a();
        }
        outputStream.write(this.f21670a, 0, this.f21671b);
        this.f21671b = 0;
    }

    /* renamed from: a */
    public void m4657a() {
        if (this.f21669a != null) {
            m4617c();
        }
    }

    /* renamed from: a */
    public int m4658a() {
        if (this.f21669a == null) {
            return this.f21668a - this.f21671b;
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
    }

    /* renamed from: b */
    public void m4626b() {
        if (m4658a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.c$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11225a extends IOException {
        C11225a() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    /* renamed from: a */
    public void m4656a(byte b) {
        if (this.f21671b == this.f21668a) {
            m4617c();
        }
        byte[] bArr = this.f21670a;
        int i = this.f21671b;
        this.f21671b = i + 1;
        bArr[i] = b;
    }

    /* renamed from: c */
    public void m4615c(int i) {
        m4656a((byte) i);
    }

    /* renamed from: a */
    public void m4629a(byte[] bArr) {
        m4627a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public void m4627a(byte[] bArr, int i, int i2) {
        int i3 = this.f21668a;
        int i4 = this.f21671b;
        if (i3 - i4 >= i2) {
            System.arraycopy(bArr, i, this.f21670a, i4, i2);
            this.f21671b += i2;
            return;
        }
        int i5 = i3 - i4;
        System.arraycopy(bArr, i, this.f21670a, i4, i5);
        int i6 = i + i5;
        int i7 = i2 - i5;
        this.f21671b = this.f21668a;
        m4617c();
        if (i7 <= this.f21668a) {
            System.arraycopy(bArr, i6, this.f21670a, 0, i7);
            this.f21671b = i7;
            return;
        }
        this.f21669a.write(bArr, i6, i7);
    }

    /* renamed from: c */
    public void m4614c(int i, int i2) {
        m4610d(C11355f.m3902a(i, i2));
    }

    /* renamed from: c */
    public static int m4616c(int i) {
        return m4611d(C11355f.m3902a(i, 0));
    }

    /* renamed from: d */
    public void m4610d(int i) {
        while ((i & (-128)) != 0) {
            m4615c((i & 127) | 128);
            i >>>= 7;
        }
        m4615c(i);
    }

    /* renamed from: c */
    public void m4612c(long j) {
        while (((-128) & j) != 0) {
            m4615c((((int) j) & 127) | 128);
            j >>>= 7;
        }
        m4615c((int) j);
    }
}
