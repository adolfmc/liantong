package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1Exception;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: a.a.a.a.a.e.a.b.w */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0371w {

    /* renamed from: a */
    public final InputStream f985a;

    /* renamed from: b */
    public final int f986b;

    /* renamed from: c */
    public final byte[][] f987c;

    public C0371w(InputStream inputStream) {
        this(inputStream, C0177m2.m24098a(inputStream));
    }

    /* renamed from: a */
    public InterfaceC0136d m23494a(int i) {
        if (i != 4) {
            if (i != 8) {
                if (i != 16) {
                    if (i == 17) {
                        return new C0183o0(this);
                    }
                    throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(i));
                }
                return new C0175m0(this);
            }
            return new C0126a1(this);
        }
        return new C0159i0(this);
    }

    /* renamed from: b */
    public AbstractC0258r m23490b(boolean z, int i) {
        if (!z) {
            return new C0360v1(false, i, new C0168k1(((C0147f2) this.f985a).m24154b()));
        }
        C0140e m23491b = m23491b();
        if (this.f985a instanceof C0157h2) {
            if (m23491b.m24172a() == 1) {
                return new C0235p0(true, i, m23491b.m24171a(0));
            }
            return new C0235p0(false, i, C0141e0.m24168a(m23491b));
        } else if (m23491b.m24172a() == 1) {
            return new C0360v1(true, i, m23491b.m24171a(0));
        } else {
            return new C0360v1(false, i, C0130b1.m24192a(m23491b));
        }
    }

    public C0371w(InputStream inputStream, int i) {
        this.f985a = inputStream;
        this.f986b = i;
        this.f987c = new byte[11];
    }

    public C0371w(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    /* renamed from: a */
    public InterfaceC0136d m23492a(boolean z, int i) {
        InputStream inputStream = this.f985a;
        if (inputStream instanceof C0157h2) {
            if (z) {
                return m23494a(i);
            }
            throw new IOException("indefinite length primitive encoding encountered");
        }
        if (z) {
            if (i == 4) {
                return new C0159i0(this);
            }
            if (i == 16) {
                return new C0255q1(this);
            }
            if (i == 17) {
                return new C0266s1(this);
            }
        } else if (i == 4) {
            return new C0172l1((C0147f2) inputStream);
        } else {
            if (i == 16) {
                throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
            }
            if (i == 17) {
                throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
            }
        }
        throw new RuntimeException("implicit tagging not implemented");
    }

    /* renamed from: b */
    public C0140e m23491b() {
        C0140e c0140e = new C0140e();
        while (true) {
            InterfaceC0136d m23495a = m23495a();
            if (m23495a == null) {
                return c0140e;
            }
            if (m23495a instanceof InterfaceC0152g2) {
                c0140e.m24170a(((InterfaceC0152g2) m23495a).mo23007b());
            } else {
                c0140e.m24170a(m23495a.mo23015d());
            }
        }
    }

    /* renamed from: a */
    public InterfaceC0136d m23495a() {
        int read = this.f985a.read();
        if (read == -1) {
            return null;
        }
        m23493a(false);
        int m24126b = C0162j.m24126b(this.f985a, read);
        boolean z = (read & 32) != 0;
        int m24130a = C0162j.m24130a(this.f985a, this.f986b);
        if (m24130a < 0) {
            if (z) {
                C0371w c0371w = new C0371w(new C0157h2(this.f985a, this.f986b), this.f986b);
                if ((read & 64) != 0) {
                    return new C0133c0(m24126b, c0371w);
                }
                if ((read & 128) != 0) {
                    return new C0254q0(true, m24126b, c0371w);
                }
                return c0371w.m23494a(m24126b);
            }
            throw new IOException("indefinite length primitive encoding encountered");
        }
        C0147f2 c0147f2 = new C0147f2(this.f985a, m24130a);
        if ((read & 64) != 0) {
            return new C0299t0(z, m24126b, c0147f2.m24154b());
        }
        if ((read & 128) != 0) {
            return new C0254q0(z, m24126b, new C0371w(c0147f2));
        }
        if (!z) {
            if (m24126b != 4) {
                try {
                    return C0162j.m24133a(m24126b, c0147f2, this.f987c);
                } catch (IllegalArgumentException e) {
                    throw new ASN1Exception("corrupted stream detected", e);
                }
            }
            return new C0172l1(c0147f2);
        } else if (m24126b != 4) {
            if (m24126b != 8) {
                if (m24126b != 16) {
                    if (m24126b == 17) {
                        return new C0266s1(new C0371w(c0147f2));
                    }
                    throw new IOException("unknown tag " + m24126b + " encountered");
                }
                return new C0255q1(new C0371w(c0147f2));
            }
            return new C0126a1(new C0371w(c0147f2));
        } else {
            return new C0159i0(new C0371w(c0147f2));
        }
    }

    /* renamed from: a */
    private void m23493a(boolean z) {
        InputStream inputStream = this.f985a;
        if (inputStream instanceof C0157h2) {
            ((C0157h2) inputStream).m24141b(z);
        }
    }
}
