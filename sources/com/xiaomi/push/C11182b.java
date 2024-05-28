package com.xiaomi.push;

import java.io.InputStream;
import java.util.Vector;

/* renamed from: com.xiaomi.push.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11182b {

    /* renamed from: a */
    private int f21569a;

    /* renamed from: a */
    private final InputStream f21570a;

    /* renamed from: a */
    private final byte[] f21571a;

    /* renamed from: b */
    private int f21572b;

    /* renamed from: c */
    private int f21573c;

    /* renamed from: d */
    private int f21574d;

    /* renamed from: e */
    private int f21575e;

    /* renamed from: f */
    private int f21576f;

    /* renamed from: g */
    private int f21577g;

    /* renamed from: h */
    private int f21578h;

    /* renamed from: i */
    private int f21579i;

    /* renamed from: a */
    public static C11182b m4776a(InputStream inputStream) {
        return new C11182b(inputStream);
    }

    /* renamed from: a */
    public static C11182b m4774a(byte[] bArr, int i, int i2) {
        return new C11182b(bArr, i, i2);
    }

    /* renamed from: a */
    public int m4787a() {
        if (m4770b()) {
            this.f21574d = 0;
            return 0;
        }
        this.f21574d = m4765d();
        int i = this.f21574d;
        if (i != 0) {
            return i;
        }
        throw C11264d.m4397d();
    }

    /* renamed from: a */
    public void m4780a(int i) {
        if (this.f21574d != i) {
            throw C11264d.m4396e();
        }
    }

    /* renamed from: a */
    public boolean m4779a(int i) {
        switch (C11355f.m3903a(i)) {
            case 0:
                m4773b();
                return true;
            case 1:
                m4764d();
                return true;
            case 2:
                m4766c(m4765d());
                return true;
            case 3:
                m4783a();
                m4780a(C11355f.m3902a(C11355f.m3901b(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                m4763e();
                return true;
            default:
                throw C11264d.m4395f();
        }
    }

    /* renamed from: a */
    public void m4783a() {
        int m4787a;
        do {
            m4787a = m4787a();
            if (m4787a == 0) {
                return;
            }
        } while (m4779a(m4787a));
    }

    /* renamed from: a */
    public long m4786a() {
        return m4767c();
    }

    /* renamed from: b */
    public long m4772b() {
        return m4767c();
    }

    /* renamed from: b */
    public int m4773b() {
        return m4765d();
    }

    /* renamed from: a */
    public boolean m4782a() {
        return m4765d() != 0;
    }

    /* renamed from: a */
    public String m4784a() {
        int m4765d = m4765d();
        int i = this.f21569a;
        int i2 = this.f21573c;
        if (m4765d <= i - i2 && m4765d > 0) {
            String str = new String(this.f21571a, i2, m4765d, "UTF-8");
            this.f21573c += m4765d;
            return str;
        }
        return new String(m4778a(m4765d), "UTF-8");
    }

    /* renamed from: a */
    public void m4777a(AbstractC11313e abstractC11313e) {
        int m4765d = m4765d();
        if (this.f21577g >= this.f21578h) {
            throw C11264d.m4394g();
        }
        int m4781a = m4781a(m4765d);
        this.f21577g++;
        abstractC11313e.mo4061a(this);
        m4780a(0);
        this.f21577g--;
        m4769b(m4781a);
    }

    /* renamed from: a */
    public C11129a m4785a() {
        int m4765d = m4765d();
        int i = this.f21569a;
        int i2 = this.f21573c;
        if (m4765d <= i - i2 && m4765d > 0) {
            C11129a m4944a = C11129a.m4944a(this.f21571a, i2, m4765d);
            this.f21573c += m4765d;
            return m4944a;
        }
        return C11129a.m4945a(m4778a(m4765d));
    }

    /* renamed from: c */
    public int m4768c() {
        return m4765d();
    }

    /* renamed from: d */
    public int m4765d() {
        byte m4788a = m4788a();
        if (m4788a >= 0) {
            return m4788a;
        }
        int i = m4788a & Byte.MAX_VALUE;
        byte m4788a2 = m4788a();
        if (m4788a2 >= 0) {
            return i | (m4788a2 << 7);
        }
        int i2 = i | ((m4788a2 & Byte.MAX_VALUE) << 7);
        byte m4788a3 = m4788a();
        if (m4788a3 >= 0) {
            return i2 | (m4788a3 << 14);
        }
        int i3 = i2 | ((m4788a3 & Byte.MAX_VALUE) << 14);
        byte m4788a4 = m4788a();
        if (m4788a4 >= 0) {
            return i3 | (m4788a4 << 21);
        }
        int i4 = i3 | ((m4788a4 & Byte.MAX_VALUE) << 21);
        byte m4788a5 = m4788a();
        int i5 = i4 | (m4788a5 << 28);
        if (m4788a5 < 0) {
            for (int i6 = 0; i6 < 5; i6++) {
                if (m4788a() >= 0) {
                    return i5;
                }
            }
            throw C11264d.m4398c();
        }
        return i5;
    }

    /* renamed from: c */
    public long m4767c() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte m4788a = m4788a();
            j |= (m4788a & Byte.MAX_VALUE) << i;
            if ((m4788a & 128) == 0) {
                return j;
            }
        }
        throw C11264d.m4398c();
    }

    /* renamed from: e */
    public int m4763e() {
        return (m4788a() & 255) | ((m4788a() & 255) << 8) | ((m4788a() & 255) << 16) | ((m4788a() & 255) << 24);
    }

    /* renamed from: d */
    public long m4764d() {
        byte m4788a = m4788a();
        byte m4788a2 = m4788a();
        return ((m4788a2 & 255) << 8) | (m4788a & 255) | ((m4788a() & 255) << 16) | ((m4788a() & 255) << 24) | ((m4788a() & 255) << 32) | ((m4788a() & 255) << 40) | ((m4788a() & 255) << 48) | ((m4788a() & 255) << 56);
    }

    private C11182b(byte[] bArr, int i, int i2) {
        this.f21576f = Integer.MAX_VALUE;
        this.f21578h = 64;
        this.f21579i = 67108864;
        this.f21571a = bArr;
        this.f21569a = i2 + i;
        this.f21573c = i;
        this.f21570a = null;
    }

    private C11182b(InputStream inputStream) {
        this.f21576f = Integer.MAX_VALUE;
        this.f21578h = 64;
        this.f21579i = 67108864;
        this.f21571a = new byte[4096];
        this.f21569a = 0;
        this.f21573c = 0;
        this.f21570a = inputStream;
    }

    /* renamed from: a */
    public int m4781a(int i) {
        if (i < 0) {
            throw C11264d.m4399b();
        }
        int i2 = i + this.f21575e + this.f21573c;
        int i3 = this.f21576f;
        if (i2 > i3) {
            throw C11264d.m4400a();
        }
        this.f21576f = i2;
        m4771b();
        return i3;
    }

    /* renamed from: b */
    private void m4771b() {
        this.f21569a += this.f21572b;
        int i = this.f21575e;
        int i2 = this.f21569a;
        int i3 = i + i2;
        int i4 = this.f21576f;
        if (i3 > i4) {
            this.f21572b = i3 - i4;
            this.f21569a = i2 - this.f21572b;
            return;
        }
        this.f21572b = 0;
    }

    /* renamed from: b */
    public void m4769b(int i) {
        this.f21576f = i;
        m4771b();
    }

    /* renamed from: b */
    public boolean m4770b() {
        return this.f21573c == this.f21569a && !m4775a(false);
    }

    /* renamed from: a */
    private boolean m4775a(boolean z) {
        int i = this.f21573c;
        int i2 = this.f21569a;
        if (i < i2) {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        }
        int i3 = this.f21575e;
        if (i3 + i2 == this.f21576f) {
            if (z) {
                throw C11264d.m4400a();
            }
            return false;
        }
        this.f21575e = i3 + i2;
        this.f21573c = 0;
        InputStream inputStream = this.f21570a;
        this.f21569a = inputStream == null ? -1 : inputStream.read(this.f21571a);
        int i4 = this.f21569a;
        if (i4 == 0 || i4 < -1) {
            throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.f21569a + "\nThe InputStream implementation is buggy.");
        } else if (i4 == -1) {
            this.f21569a = 0;
            if (z) {
                throw C11264d.m4400a();
            }
            return false;
        } else {
            m4771b();
            int i5 = this.f21575e + this.f21569a + this.f21572b;
            if (i5 > this.f21579i || i5 < 0) {
                throw C11264d.m4393h();
            }
            return true;
        }
    }

    /* renamed from: a */
    public byte m4788a() {
        if (this.f21573c == this.f21569a) {
            m4775a(true);
        }
        byte[] bArr = this.f21571a;
        int i = this.f21573c;
        this.f21573c = i + 1;
        return bArr[i];
    }

    /* renamed from: a */
    public byte[] m4778a(int i) {
        if (i < 0) {
            throw C11264d.m4399b();
        }
        int i2 = this.f21575e;
        int i3 = this.f21573c;
        int i4 = i2 + i3 + i;
        int i5 = this.f21576f;
        if (i4 > i5) {
            m4766c((i5 - i2) - i3);
            throw C11264d.m4400a();
        }
        int i6 = this.f21569a;
        if (i <= i6 - i3) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.f21571a, i3, bArr, 0, i);
            this.f21573c += i;
            return bArr;
        } else if (i < 4096) {
            byte[] bArr2 = new byte[i];
            int i7 = i6 - i3;
            System.arraycopy(this.f21571a, i3, bArr2, 0, i7);
            this.f21573c = this.f21569a;
            m4775a(true);
            while (true) {
                int i8 = i - i7;
                int i9 = this.f21569a;
                if (i8 > i9) {
                    System.arraycopy(this.f21571a, 0, bArr2, i7, i9);
                    int i10 = this.f21569a;
                    i7 += i10;
                    this.f21573c = i10;
                    m4775a(true);
                } else {
                    System.arraycopy(this.f21571a, 0, bArr2, i7, i8);
                    this.f21573c = i8;
                    return bArr2;
                }
            }
        } else {
            this.f21575e = i2 + i6;
            this.f21573c = 0;
            this.f21569a = 0;
            int i11 = i6 - i3;
            int i12 = i - i11;
            Vector vector = new Vector();
            while (i12 > 0) {
                byte[] bArr3 = new byte[Math.min(i12, 4096)];
                int i13 = 0;
                while (i13 < bArr3.length) {
                    InputStream inputStream = this.f21570a;
                    int read = inputStream == null ? -1 : inputStream.read(bArr3, i13, bArr3.length - i13);
                    if (read == -1) {
                        throw C11264d.m4400a();
                    }
                    this.f21575e += read;
                    i13 += read;
                }
                i12 -= bArr3.length;
                vector.addElement(bArr3);
            }
            byte[] bArr4 = new byte[i];
            System.arraycopy(this.f21571a, i3, bArr4, 0, i11);
            for (int i14 = 0; i14 < vector.size(); i14++) {
                byte[] bArr5 = (byte[]) vector.elementAt(i14);
                System.arraycopy(bArr5, 0, bArr4, i11, bArr5.length);
                i11 += bArr5.length;
            }
            return bArr4;
        }
    }

    /* renamed from: c */
    public void m4766c(int i) {
        if (i < 0) {
            throw C11264d.m4399b();
        }
        int i2 = this.f21575e;
        int i3 = this.f21573c;
        int i4 = i2 + i3 + i;
        int i5 = this.f21576f;
        if (i4 > i5) {
            m4766c((i5 - i2) - i3);
            throw C11264d.m4400a();
        }
        int i6 = this.f21569a;
        if (i <= i6 - i3) {
            this.f21573c = i3 + i;
            return;
        }
        int i7 = i6 - i3;
        this.f21575e = i2 + i6;
        this.f21573c = 0;
        this.f21569a = 0;
        while (i7 < i) {
            InputStream inputStream = this.f21570a;
            int skip = inputStream == null ? -1 : (int) inputStream.skip(i - i7);
            if (skip <= 0) {
                throw C11264d.m4400a();
            }
            i7 += skip;
            this.f21575e += skip;
        }
    }
}
