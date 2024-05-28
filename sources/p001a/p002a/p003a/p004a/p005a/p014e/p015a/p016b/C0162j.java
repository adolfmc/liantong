package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1Exception;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.C0690a;

/* renamed from: a.a.a.a.a.e.a.b.j */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0162j extends FilterInputStream implements InterfaceC0259r0 {

    /* renamed from: C */
    public final int f200C;

    /* renamed from: D */
    public final boolean f201D;

    /* renamed from: E */
    public final byte[][] f202E;

    public C0162j(InputStream inputStream) {
        this(inputStream, C0177m2.m24098a(inputStream));
    }

    /* renamed from: a */
    public void m24129a(byte[] bArr) {
        if (C0690a.m22391a(this, bArr) != bArr.length) {
            throw new EOFException("EOF encountered in middle of object");
        }
    }

    /* renamed from: b */
    public int m24128b() {
        return this.f200C;
    }

    /* renamed from: c */
    public int m24125c() {
        return m24130a(this, this.f200C);
    }

    /* renamed from: d */
    public AbstractC0258r m24124d() {
        int read = read();
        if (read <= 0) {
            if (read != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int m24126b = m24126b(this, read);
        boolean z = (read & 32) != 0;
        int m24125c = m24125c();
        if (m24125c >= 0) {
            try {
                return m24134a(read, m24126b, m24125c);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception("corrupted stream detected", e);
            }
        } else if (z) {
            C0371w c0371w = new C0371w(new C0157h2(this, this.f200C), this.f200C);
            if ((read & 64) != 0) {
                return new C0133c0(m24126b, c0371w).mo23007b();
            }
            if ((read & 128) != 0) {
                return new C0254q0(true, m24126b, c0371w).mo23007b();
            }
            if (m24126b != 4) {
                if (m24126b != 8) {
                    if (m24126b != 16) {
                        if (m24126b == 17) {
                            return new C0183o0(c0371w).mo23007b();
                        }
                        throw new IOException("unknown BER object encountered");
                    }
                    return new C0175m0(c0371w).mo23007b();
                }
                return new C0126a1(c0371w).mo23007b();
            }
            return new C0159i0(c0371w).mo23007b();
        } else {
            throw new IOException("indefinite length primitive encoding encountered");
        }
    }

    public C0162j(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    /* renamed from: b */
    public static int m24126b(InputStream inputStream, int i) {
        int i2 = i & 31;
        if (i2 == 31) {
            int i3 = 0;
            int read = inputStream.read();
            if ((read & 127) != 0) {
                while (read >= 0 && (read & 128) != 0) {
                    i3 = (i3 | (read & 127)) << 7;
                    read = inputStream.read();
                }
                if (read >= 0) {
                    return i3 | (read & 127);
                }
                throw new EOFException("EOF found inside tag value.");
            }
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        return i2;
    }

    public C0162j(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    public C0162j(InputStream inputStream, int i) {
        this(inputStream, i, false);
    }

    /* renamed from: a */
    public AbstractC0258r m24134a(int i, int i2, int i3) {
        boolean z = (i & 32) != 0;
        C0147f2 c0147f2 = new C0147f2(this, i3);
        if ((i & 64) != 0) {
            return new C0299t0(z, i2, c0147f2.m24154b());
        }
        if ((i & 128) != 0) {
            return new C0371w(c0147f2).m23490b(z, i2);
        }
        if (z) {
            if (i2 == 4) {
                C0140e m24132a = m24132a(c0147f2);
                int m24172a = m24132a.m24172a();
                AbstractC0182o[] abstractC0182oArr = new AbstractC0182o[m24172a];
                for (int i4 = 0; i4 != m24172a; i4++) {
                    abstractC0182oArr[i4] = (AbstractC0182o) m24132a.m24171a(i4);
                }
                return new C0149g0(abstractC0182oArr);
            } else if (i2 != 8) {
                if (i2 == 16) {
                    if (this.f201D) {
                        return new C0165j2(c0147f2.m24154b());
                    }
                    return C0130b1.m24192a(m24132a(c0147f2));
                } else if (i2 == 17) {
                    return C0130b1.m24191b(m24132a(c0147f2));
                } else {
                    throw new IOException("unknown tag " + i2 + " encountered");
                }
            } else {
                return new C0498z0(m24132a(c0147f2));
            }
        }
        return m24133a(i2, c0147f2, this.f202E);
    }

    public C0162j(InputStream inputStream, boolean z) {
        this(inputStream, C0177m2.m24098a(inputStream), z);
    }

    public C0162j(InputStream inputStream, int i, boolean z) {
        super(inputStream);
        this.f200C = i;
        this.f201D = z;
        this.f202E = new byte[11];
    }

    /* renamed from: b */
    public static char[] m24127b(C0147f2 c0147f2) {
        int read;
        int mo24110a = c0147f2.mo24110a() / 2;
        char[] cArr = new char[mo24110a];
        for (int i = 0; i < mo24110a; i++) {
            int read2 = c0147f2.read();
            if (read2 < 0 || (read = c0147f2.read()) < 0) {
                break;
            }
            cArr[i] = (char) ((read2 << 8) | (read & 255));
        }
        return cArr;
    }

    /* renamed from: a */
    public C0140e m24135a() {
        C0140e c0140e = new C0140e();
        while (true) {
            AbstractC0258r m24124d = m24124d();
            if (m24124d == null) {
                return c0140e;
            }
            c0140e.m24170a(m24124d);
        }
    }

    /* renamed from: a */
    public C0140e m24132a(C0147f2 c0147f2) {
        return new C0162j(c0147f2).m24135a();
    }

    /* renamed from: a */
    public static int m24130a(InputStream inputStream, int i) {
        int read = inputStream.read();
        if (read >= 0) {
            if (read == 128) {
                return -1;
            }
            if (read > 127) {
                int i2 = read & 127;
                if (i2 > 4) {
                    throw new IOException("DER length more than 4 bytes: " + i2);
                }
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    int read2 = inputStream.read();
                    if (read2 < 0) {
                        throw new EOFException("EOF found reading length");
                    }
                    i3 = (i3 << 8) + read2;
                }
                if (i3 >= 0) {
                    if (i3 < i) {
                        return i3;
                    }
                    throw new IOException("corrupted stream - out of bounds length found");
                }
                throw new IOException("corrupted stream - negative length found");
            }
            return read;
        }
        throw new EOFException("EOF found when length expected");
    }

    /* renamed from: a */
    public static byte[] m24131a(C0147f2 c0147f2, byte[][] bArr) {
        int mo24110a = c0147f2.mo24110a();
        if (c0147f2.mo24110a() < bArr.length) {
            byte[] bArr2 = bArr[mo24110a];
            if (bArr2 == null) {
                bArr2 = new byte[mo24110a];
                bArr[mo24110a] = bArr2;
            }
            C0690a.m22391a(c0147f2, bArr2);
            return bArr2;
        }
        return c0147f2.m24154b();
    }

    /* renamed from: a */
    public static AbstractC0258r m24133a(int i, C0147f2 c0147f2, byte[][] bArr) {
        switch (i) {
            case 1:
                return C0372w0.m23485b(m24131a(c0147f2, bArr));
            case 2:
                return new C0166k(c0147f2.m24154b());
            case 3:
                return C0359v0.m23559a(c0147f2.mo24110a(), c0147f2);
            case 4:
                return new C0168k1(c0147f2.m24154b());
            case 5:
                return C0156h1.f189v3;
            case 6:
                return C0164j1.m24115b(m24131a(c0147f2, bArr));
            case 7:
            case 8:
            case 9:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 21:
            case 25:
            case 29:
            default:
                throw new IOException("unknown tag " + i + " encountered");
            case 10:
                return C0495y0.m22999b(m24131a(c0147f2, bArr));
            case 12:
                return new C0496y1(c0147f2.m24154b());
            case 18:
                return new C0160i1(c0147f2.m24154b());
            case 19:
                return new C0180n1(c0147f2.m24154b());
            case 20:
                return new C0300t1(c0147f2.m24154b());
            case 22:
                return new C0146f1(c0147f2.m24154b());
            case 23:
                return new C0125a0(c0147f2.m24154b());
            case 24:
                return new C0153h(c0147f2.m24154b());
            case 26:
                return new C0127a2(c0147f2.m24154b());
            case 27:
                return new C0134c1(c0147f2.m24154b());
            case 28:
                return new C0499z1(c0147f2.m24154b());
            case 30:
                return new C0340u0(m24127b(c0147f2));
        }
    }
}
