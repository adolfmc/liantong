package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.b.t0 */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0299t0 extends AbstractC0258r {

    /* renamed from: v3 */
    public final boolean f798v3;

    /* renamed from: w3 */
    public final int f799w3;

    /* renamed from: x3 */
    public final byte[] f800x3;

    public C0299t0(boolean z, int i, byte[] bArr) {
        this.f798v3 = z;
        this.f799w3 = i;
        this.f800x3 = bArr;
    }

    /* renamed from: a */
    public static C0299t0 m23609a(Object obj) {
        if (obj != null && !(obj instanceof C0299t0)) {
            if (obj instanceof byte[]) {
                try {
                    return m23609a((Object) AbstractC0258r.m23755a((byte[]) obj));
                } catch (IOException e) {
                    throw new IllegalArgumentException("failed to construct object from byte[]: " + e.getMessage());
                }
            }
            if (obj instanceof InterfaceC0136d) {
                AbstractC0258r mo23015d = ((InterfaceC0136d) obj).mo23015d();
                if (mo23015d instanceof AbstractC0263s) {
                    return (C0299t0) mo23015d;
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        return (C0299t0) obj;
    }

    /* renamed from: b */
    private int m23608b(byte[] bArr) {
        int i = bArr[1] & 255;
        if (i != 128 && i > 127) {
            int i2 = i & 127;
            if (i2 <= 4) {
                return i2 + 2;
            }
            throw new IllegalStateException("DER length more than 4 bytes: " + i2);
        }
        return 2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        boolean z = this.f798v3;
        return ((z ? 1 : 0) ^ this.f799w3) ^ C0669a.m22472b(this.f800x3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return C0177m2.m24097b(this.f799w3) + C0177m2.m24099a(this.f800x3.length) + this.f800x3.length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return this.f798v3;
    }

    /* renamed from: m */
    public int m23607m() {
        return this.f799w3;
    }

    /* renamed from: n */
    public byte[] m23606n() {
        return this.f800x3;
    }

    /* renamed from: o */
    public AbstractC0258r m23605o() {
        return new C0162j(m23606n()).m24124d();
    }

    public C0299t0(int i, byte[] bArr) {
        this(false, i, bArr);
    }

    public C0299t0(int i, InterfaceC0136d interfaceC0136d) {
        this(true, i, interfaceC0136d);
    }

    public C0299t0(boolean z, int i, InterfaceC0136d interfaceC0136d) {
        AbstractC0258r mo23015d = interfaceC0136d.mo23015d();
        byte[] m24102a = mo23015d.m24102a("DER");
        this.f798v3 = z || (mo23015d instanceof AbstractC0338u) || (mo23015d instanceof AbstractC0263s);
        this.f799w3 = i;
        if (z) {
            this.f800x3 = m24102a;
            return;
        }
        int m23608b = m23608b(m24102a);
        int length = m24102a.length - m23608b;
        byte[] bArr = new byte[length];
        System.arraycopy(m24102a, m23608b, bArr, 0, length);
        this.f800x3 = bArr;
    }

    public C0299t0(int i, C0140e c0140e) {
        this.f799w3 = i;
        this.f798v3 = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 0; i2 != c0140e.m24172a(); i2++) {
            try {
                byteArrayOutputStream.write(((AbstractC0174m) c0140e.m24171a(i2)).m24102a("DER"));
            } catch (IOException e) {
                throw new ASN1ParsingException("malformed object: " + e, e);
            }
        }
        this.f800x3 = byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public AbstractC0258r m23611a(int i) {
        if (i < 31) {
            byte[] m24101g = m24101g();
            byte[] m23610a = m23610a(i, m24101g);
            if ((m24101g[0] & 32) != 0) {
                m23610a[0] = (byte) (m23610a[0] | 32);
            }
            return new C0162j(m23610a).m24124d();
        }
        throw new IOException("unsupported tag number");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.m23774a(this.f798v3 ? 96 : 64, this.f799w3, this.f800x3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0299t0) {
            C0299t0 c0299t0 = (C0299t0) abstractC0258r;
            return this.f798v3 == c0299t0.f798v3 && this.f799w3 == c0299t0.f799w3 && C0669a.m22499a(this.f800x3, c0299t0.f800x3);
        }
        return false;
    }

    /* renamed from: a */
    private byte[] m23610a(int i, byte[] bArr) {
        int i2;
        if ((bArr[0] & 31) == 31) {
            i2 = 2;
            int i3 = bArr[1] & 255;
            if ((i3 & 127) == 0) {
                throw new ASN1ParsingException("corrupted stream - invalid high tag number found");
            }
            while (i3 >= 0 && (i3 & 128) != 0) {
                i3 = bArr[i2] & 255;
                i2++;
            }
        } else {
            i2 = 1;
        }
        int length = (bArr.length - i2) + 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, i2, bArr2, 1, length - 1);
        bArr2[0] = (byte) i;
        return bArr2;
    }
}
