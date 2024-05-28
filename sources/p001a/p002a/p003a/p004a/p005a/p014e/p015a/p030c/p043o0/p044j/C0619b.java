package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.p044j;

import java.util.Hashtable;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0610d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0673e;

/* renamed from: a.a.a.a.a.e.a.c.o0.j.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0619b implements InterfaceC0620c {

    /* renamed from: h */
    public static final byte[] f1924h = {1};

    /* renamed from: i */
    public static final long f1925i = 140737488355328L;

    /* renamed from: j */
    public static final int f1926j = 262144;

    /* renamed from: k */
    public static final Hashtable f1927k;

    /* renamed from: a */
    public InterfaceC0605o f1928a;

    /* renamed from: b */
    public byte[] f1929b;

    /* renamed from: c */
    public byte[] f1930c;

    /* renamed from: d */
    public long f1931d;

    /* renamed from: e */
    public InterfaceC0610d f1932e;

    /* renamed from: f */
    public int f1933f;

    /* renamed from: g */
    public int f1934g;

    static {
        Hashtable hashtable = new Hashtable();
        f1927k = hashtable;
        hashtable.put("SHA-1", C0673e.m22455a(440));
        f1927k.put("SHA-224", C0673e.m22455a(440));
        f1927k.put("SHA-256", C0673e.m22455a(440));
        f1927k.put("SHA-512/256", C0673e.m22455a(440));
        f1927k.put("SHA-512/224", C0673e.m22455a(440));
        f1927k.put("SHA-384", C0673e.m22455a(888));
        f1927k.put("SHA-512", C0673e.m22455a(888));
    }

    public C0619b(InterfaceC0605o interfaceC0605o, int i, InterfaceC0610d interfaceC0610d, byte[] bArr, byte[] bArr2) {
        if (i <= C0621d.m22716a(interfaceC0605o)) {
            if (interfaceC0610d.mo22733b() >= i) {
                this.f1928a = interfaceC0605o;
                this.f1932e = interfaceC0610d;
                this.f1933f = i;
                this.f1934g = ((Integer) f1927k.get(interfaceC0605o.mo22748a())).intValue();
                byte[] m22715a = C0621d.m22715a(this.f1928a, C0669a.m22498a(interfaceC0610d.mo22732c(), bArr2, bArr), this.f1934g);
                this.f1929b = m22715a;
                byte[] bArr3 = new byte[m22715a.length + 1];
                System.arraycopy(m22715a, 0, bArr3, 1, m22715a.length);
                this.f1930c = C0621d.m22715a(this.f1928a, bArr3, this.f1934g);
                this.f1931d = 1L;
                return;
            }
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
    }

    /* renamed from: b */
    private byte[] m22719b(byte[] bArr) {
        this.f1928a.mo22745a(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[this.f1928a.mo22743c()];
        this.f1928a.mo22746a(bArr2, 0);
        return bArr2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.p044j.InterfaceC0620c
    /* renamed from: a */
    public int mo22717a(byte[] bArr, byte[] bArr2, boolean z) {
        long j;
        int length = bArr.length * 8;
        if (length <= 262144) {
            if (this.f1931d > 140737488355328L) {
                return -1;
            }
            if (z) {
                mo22718a(bArr2);
                bArr2 = null;
            }
            if (bArr2 != null) {
                byte[] bArr3 = this.f1929b;
                byte[] bArr4 = new byte[bArr3.length + 1 + bArr2.length];
                bArr4[0] = 2;
                System.arraycopy(bArr3, 0, bArr4, 1, bArr3.length);
                System.arraycopy(bArr2, 0, bArr4, this.f1929b.length + 1, bArr2.length);
                m22720a(this.f1929b, m22719b(bArr4));
            }
            byte[] m22721a = m22721a(this.f1929b, length);
            byte[] bArr5 = this.f1929b;
            byte[] bArr6 = new byte[bArr5.length + 1];
            System.arraycopy(bArr5, 0, bArr6, 1, bArr5.length);
            bArr6[0] = 3;
            m22720a(this.f1929b, m22719b(bArr6));
            m22720a(this.f1929b, this.f1930c);
            m22720a(this.f1929b, new byte[]{(byte) (j >> 24), (byte) (j >> 16), (byte) (j >> 8), (byte) this.f1931d});
            this.f1931d++;
            System.arraycopy(m22721a, 0, bArr, 0, bArr.length);
            return length;
        }
        throw new IllegalArgumentException("Number of bits per request limited to 262144");
    }

    /* renamed from: a */
    private void m22720a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        for (int i2 = 1; i2 <= bArr2.length; i2++) {
            int i3 = (bArr[bArr.length - i2] & 255) + (bArr2[bArr2.length - i2] & 255) + i;
            i = i3 > 255 ? 1 : 0;
            bArr[bArr.length - i2] = (byte) i3;
        }
        for (int length = bArr2.length + 1; length <= bArr.length; length++) {
            int i4 = (bArr[bArr.length - length] & 255) + i;
            i = i4 > 255 ? 1 : 0;
            bArr[bArr.length - length] = (byte) i4;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.p044j.InterfaceC0620c
    /* renamed from: a */
    public void mo22718a(byte[] bArr) {
        byte[] m22715a = C0621d.m22715a(this.f1928a, C0669a.m22497a(f1924h, this.f1929b, this.f1932e.mo22732c(), bArr), this.f1934g);
        this.f1929b = m22715a;
        byte[] bArr2 = new byte[m22715a.length + 1];
        bArr2[0] = 0;
        System.arraycopy(m22715a, 0, bArr2, 1, m22715a.length);
        this.f1930c = C0621d.m22715a(this.f1928a, bArr2, this.f1934g);
        this.f1931d = 1L;
    }

    /* renamed from: a */
    private byte[] m22721a(byte[] bArr, int i) {
        int length;
        int i2 = i / 8;
        int mo22743c = i2 / this.f1928a.mo22743c();
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        byte[] bArr3 = new byte[i2];
        for (int i3 = 0; i3 <= mo22743c; i3++) {
            byte[] m22719b = m22719b(bArr2);
            if (i2 - (m22719b.length * i3) > m22719b.length) {
                length = m22719b.length;
            } else {
                length = i2 - (m22719b.length * i3);
            }
            System.arraycopy(m22719b, 0, bArr3, m22719b.length * i3, length);
            m22720a(bArr2, f1924h);
        }
        return bArr3;
    }
}
