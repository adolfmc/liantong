package org.p415a.p418b;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.p415a.p448g.p450b.C12967a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12559k extends FilterInputStream {

    /* renamed from: a */
    private final int f25501a;

    /* renamed from: b */
    private final boolean f25502b;

    /* renamed from: c */
    private final byte[][] f25503c;

    public C12559k(InputStream inputStream) {
        this(inputStream, C12466bz.m1693a(inputStream));
    }

    public C12559k(InputStream inputStream, int i) {
        this(inputStream, i, false);
    }

    public C12559k(InputStream inputStream, int i, boolean z) {
        super(inputStream);
        this.f25501a = i;
        this.f25502b = z;
        this.f25503c = new byte[11];
    }

    public C12559k(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public C12559k(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m1654a(InputStream inputStream, int i) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AbstractC12570t m1655a(int i, C12459bs c12459bs, byte[][] bArr) {
        if (i != 10) {
            if (i != 12) {
                if (i != 30) {
                    switch (i) {
                        case 1:
                            return C12503d.m1678a(m1652a(c12459bs, bArr));
                        case 2:
                            return new C12560l(c12459bs.m1703b(), false);
                        case 3:
                            return AbstractC12467c.m1691a(c12459bs.mo1698a(), c12459bs);
                        case 4:
                            return new C12435az(c12459bs.m1703b());
                        case 5:
                            return C12433ax.f25141a;
                        case 6:
                            return C12563o.m1637a(m1652a(c12459bs, bArr));
                        default:
                            switch (i) {
                                case 18:
                                    return new C12434ay(c12459bs.m1703b());
                                case 19:
                                    return new C12443bc(c12459bs.m1703b());
                                case 20:
                                    return new C12448bh(c12459bs.m1703b());
                                case 21:
                                    return new C12452bl(c12459bs.m1703b());
                                case 22:
                                    return new C12432aw(c12459bs.m1703b());
                                case 23:
                                    return new C12410ab(c12459bs.m1703b());
                                case 24:
                                    return new C12558j(c12459bs.m1703b());
                                case 25:
                                    return new C12431av(c12459bs.m1703b());
                                case 26:
                                    return new C12453bm(c12459bs.m1703b());
                                case 27:
                                    return new C12430au(c12459bs.m1703b());
                                case 28:
                                    return new C12451bk(c12459bs.m1703b());
                                default:
                                    throw new IOException("unknown tag " + i + " encountered");
                            }
                    }
                }
                return new C12425ap(m1649b(c12459bs));
            }
            return new C12450bj(c12459bs.m1703b());
        }
        return C12556h.m1658a(m1652a(c12459bs, bArr));
    }

    /* renamed from: a */
    private static byte[] m1652a(C12459bs c12459bs, byte[][] bArr) {
        int mo1698a = c12459bs.mo1698a();
        if (c12459bs.mo1698a() < bArr.length) {
            byte[] bArr2 = bArr[mo1698a];
            if (bArr2 == null) {
                bArr2 = new byte[mo1698a];
                bArr[mo1698a] = bArr2;
            }
            C12967a.m403a(c12459bs, bArr2);
            return bArr2;
        }
        return c12459bs.m1703b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static int m1650b(InputStream inputStream, int i) {
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

    /* renamed from: b */
    private static char[] m1649b(C12459bs c12459bs) {
        int read;
        int mo1698a = c12459bs.mo1698a() / 2;
        char[] cArr = new char[mo1698a];
        for (int i = 0; i < mo1698a; i++) {
            int read2 = c12459bs.read();
            if (read2 < 0 || (read = c12459bs.read()) < 0) {
                break;
            }
            cArr[i] = (char) ((read2 << 8) | (read & 255));
        }
        return cArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m1657a() {
        return this.f25501a;
    }

    /* renamed from: a */
    C12555g m1653a(C12459bs c12459bs) {
        return new C12559k(c12459bs).m1648c();
    }

    /* renamed from: a */
    protected AbstractC12570t m1656a(int i, int i2, int i3) {
        boolean z = (i & 32) != 0;
        C12459bs c12459bs = new C12459bs(this, i3);
        if ((i & 64) != 0) {
            return new C12424ao(z, i2, c12459bs.m1703b());
        }
        if ((i & 128) != 0) {
            return new C12575y(c12459bs).m1599a(z, i2);
        }
        if (z) {
            if (i2 == 4) {
                C12555g m1653a = m1653a(c12459bs);
                AbstractC12565p[] abstractC12565pArr = new AbstractC12565p[m1653a.m1661a()];
                for (int i4 = 0; i4 != abstractC12565pArr.length; i4++) {
                    abstractC12565pArr[i4] = (AbstractC12565p) m1653a.m1660a(i4);
                }
                return new C12414af(abstractC12565pArr);
            } else if (i2 != 8) {
                switch (i2) {
                    case 16:
                        return this.f25502b ? new C12463bw(c12459bs.m1703b()) : C12429at.m1724a(m1653a(c12459bs));
                    case 17:
                        return C12429at.m1723b(m1653a(c12459bs));
                    default:
                        throw new IOException("unknown tag " + i2 + " encountered");
                }
            } else {
                return new C12427ar(m1653a(c12459bs));
            }
        }
        return m1655a(i2, c12459bs, this.f25503c);
    }

    /* renamed from: b */
    protected int m1651b() {
        return m1650b(this, this.f25501a);
    }

    /* renamed from: c */
    C12555g m1648c() {
        C12555g c12555g = new C12555g();
        while (true) {
            AbstractC12570t m1647d = m1647d();
            if (m1647d == null) {
                return c12555g;
            }
            c12555g.m1659a(m1647d);
        }
    }

    /* renamed from: d */
    public AbstractC12570t m1647d() {
        int read = read();
        if (read <= 0) {
            if (read != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int m1654a = m1654a(this, read);
        boolean z = (read & 32) != 0;
        int m1651b = m1651b();
        if (m1651b >= 0) {
            try {
                return m1656a(read, m1654a, m1651b);
            } catch (IllegalArgumentException e) {
                throw new C12557i("corrupted stream detected", e);
            }
        } else if (z) {
            C12575y c12575y = new C12575y(new C12461bu(this, this.f25501a), this.f25501a);
            if ((read & 64) != 0) {
                return new C12412ad(m1654a, c12575y).mo1593e();
            }
            if ((read & 128) != 0) {
                return new C12422am(true, m1654a, c12575y).mo1593e();
            }
            if (m1654a != 4) {
                if (m1654a != 8) {
                    switch (m1654a) {
                        case 16:
                            return new C12418ai(c12575y).mo1593e();
                        case 17:
                            return new C12420ak(c12575y).mo1593e();
                        default:
                            throw new IOException("unknown BER object encountered");
                    }
                }
                return new C12428as(c12575y).mo1593e();
            }
            return new C12416ag(c12575y).mo1593e();
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }
}
